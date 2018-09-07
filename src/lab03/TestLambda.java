package lab03;

import java.util.function.Function;


interface T {
	String m(String s);
}

public class TestLambda {

	public static void main(String[] args) {

		// def
		Function<String,String> box = (s) -> "["+s+"]";
		Function<String,String> twice = (s) -> s+s;
		Function<String,Integer> len = (s) -> s.length();

		// target typing
		T obj = (s) -> "["+s+"]";
		System.out.println("res1 "+obj.m("Prova")); // 
		//obj = box // non sono compatibili

		// eval
		String res1 = box.apply("hello");

		System.out.println("res1 "+res1); // 

		// compo

		int res2 = box.andThen(len).apply("hello"); //prima viene applicato box andThen (e dopo) len anche l'apply è successivo ad andThen

		System.out.println("res2 "+res2);

		// compo comparison

		Function<String,String> compo1 = box.andThen(twice);
		Function<String,String> compo2 = twice.andThen(box);
		System.out.println(compo1.apply("hello")+" vs. "+compo2.apply("hello"));

		// function that returns a function - addN

		Function<Integer,Function<Integer,Integer>> addN = n -> (y -> y + n);

		Function<Integer,Integer> add5 = addN.apply(5);		

		System.out.println(add5);

		System.out.println(add5.apply(8));

	}

}