package test;

class Ta {}

class Tb extends Ta {}

class A {
	/*
	public void m(Ta p){
		System.out.println("m in A");
	}*/
	
	public Ta m(){ 
		System.out.println("m in A");
		return null; 
	}
}

class B extends A {
	public Tb m(){
		System.out.println("m in B");
		return null;
	}
}



public class TestCov {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		A obj = new B();
		Ta x = obj.m();	}

}
