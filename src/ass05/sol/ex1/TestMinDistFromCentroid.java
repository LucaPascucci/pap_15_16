package ass05.sol.ex1;

public class TestMinDistFromCentroid {
	public static void main(String[] args) {
		int size = Integer.parseInt(args[0]);
		DistCalc calc = new DistCalcConcur();
		calc.doTest(size);
	}

}
