package ass05.sol.ex1;

public class TestMinDistFromCentroidStreamSeq {
	public static void main(String[] args) {
		int size = Integer.parseInt(args[0]);
		DistCalc calc = new DistCalcStreamSeq();
		calc.doTest(size);
	}
}
