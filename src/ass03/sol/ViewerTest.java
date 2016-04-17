package ass03.sol;

import ass03.P2d;

public class ViewerTest {

	public static void main(String[] args) {

		ExtPointCloud cloud = new MyPointCloud(
				new P2d(0,1),
				new P2d(0.5,1),
				new P2d(1,1),
				new P2d(1,0.5),
				new P2d(1,0),
				new P2d(1,-0.5),
				new P2d(1,-1),
				new P2d(0.5,-1),
				new P2d(0,-1),
				new P2d(-0.5,-1),
				new P2d(-1,-1),
				new P2d(-1,-0.5),
				new P2d(-1,0),
				new P2d(-1,0.5),
				new P2d(-1,1),
				new P2d(-0.5,1));

		Viewer viewer = new Viewer(cloud);
		viewer.display();
	}

}
