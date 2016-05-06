package test;

interface T {
	int eval();
}

public class TestCapturing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int value = 1;
		
		T closure = () -> {
			return value + 1;
		};
		
		int v = closure.eval();
		
		System.out.println(v);
		
	}

}
