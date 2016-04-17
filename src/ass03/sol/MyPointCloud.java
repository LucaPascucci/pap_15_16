package ass03.sol;

import ass03.P2d;
import ass03.Region;
import ass03.V2d;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;


import static java.util.stream.Collectors.toList;

public class MyPointCloud implements ExtPointCloud {
	private List<P2d> points;
	
	public MyPointCloud(P2d... plist){
		points = Arrays.asList(plist);
	}

	public MyPointCloud(List<P2d> plist){
		points = plist;
	}
	
	public void move(V2d v) {
		points = points.stream()
			.map(p -> p.sum(v))
			.collect(toList());
	}

	public List<P2d> getPointsInRegion(Region r) {
		P2d v1 = r.getUpperLeft();
		P2d v2 = r.getBottomRight();
		return points.stream()
			.filter(p -> p.getX() >= v1.getX() &&
						 p.getX() <= v2.getX() &&
						 p.getY() <= v1.getY() &&
						 p.getY() >= v2.getY())
			.collect(toList());
	}

	public Optional<P2d> nearestPoint(P2d point) {
		Optional<Pair<P2d,Double>> res = points.stream()
			.map(po -> new Pair<P2d,Double>(po,new V2d(po,point).module()))
			.reduce((p1, p2) -> {
				if (p1.getRight().doubleValue() < p2.getRight().doubleValue()) 
					return p1;
				else 
					return p2;
			});
		if (res.isPresent()){
			return Optional.of(res.get().getLeft());
		} else {
			return Optional.empty();
		}
	}

	public String toString(){
		Optional<String> res = points.stream()
			.map(P2d::toString)
			.reduce((s1,s2) -> s1+", "+s2);
		if (res.isPresent()){
			return "{ " + res.get() + " }";
		} else {
			return "";
		}
	}
	
	public List<P2d> asList(){
		return points;
	}
	
	public static String toString(List<P2d> cloud){
		return new MyPointCloud(cloud).toString();
	}


	public void apply(Consumer<P2d> t) {
		points.stream().forEach(t);
	}
}
