package ass05.sol.ex1;

import java.util.Random;

public abstract class DistCalc {

	abstract protected P2d getMinDistFromCentroid(P2d[] points);

	public void doTest(int size) {
		Chrono chrono = new Chrono();
		System.out.println("Generating test case - size: "+size);
		chrono.reset();
		P2d[] testCase = getTestSet(size);
		System.out.println("Elapsed time for generation: "+chrono.getTimeElapsed());
		
		chrono.reset();
		P2d p = getMinDistFromCentroid(testCase);
		long dt = chrono.getTimeElapsed();
		System.out.println("Point with min dist from centroid: "+p+" - elapsed time: "+dt);
	}

	private P2d[] getTestSet(int n){
		Random rand = new Random();
		P2d[] list = new P2d[n];
		for (int i = 0; i < n; i++){
			double x = rand.nextDouble();
			double y = rand.nextDouble();
			double z = rand.nextDouble();
			list[i] = new P2d(x,y);
		}
		return list;
	}	
}
