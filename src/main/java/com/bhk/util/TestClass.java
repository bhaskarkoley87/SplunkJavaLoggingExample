package com.bhk.util;

public class TestClass {

	public static void main(String[] args) {
		TestClass objClass = new TestClass();
		objClass.test();
	}

	public void test() {
		Stack<String> objStack = new Stack<String>(5);
		objStack.push("A");
		objStack.push("B");
		objStack.push("C");
		objStack.push("D");
		
		System.out.println(objStack);
		System.out.println(objStack.pop());
		System.out.println(objStack.pop());
		System.out.println(objStack);
		System.out.println(objStack.pop());
	
	}
}
