package lab05.liveness;

public class Test0 {
	public static void main(String[] args) {
		LeftRightDeadlock res = new LeftRightDeadlock();
		new ThreadA(res).start();
		new ThreadB(res).start();
	}

}
