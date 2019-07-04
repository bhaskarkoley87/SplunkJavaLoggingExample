package com.bhk.util;

public class Stack<T> {

	private T[] obj;
	private int size;
	private int top;
	
	private Stack() {
		this(10);
	}
	
	public Stack(int size) {
		if(size < 0)
			throw new IllegalArgumentException();
		obj = (T[]) new Object[size];
		this.size = size;
		this.top = -1;
	}
	
	public boolean push(T obj) {
		this.top++;
		if(this.top <= this.size-1 && this.size > 0) {
			this.obj[this.top] = obj;	
			return true;
		}else {
			return false;
		}
	}
	
	public T pop() {
		if(!isEmpty())
			return this.obj[this.top--];
		else
			throw new ArrayIndexOutOfBoundsException();
	}
	
	public boolean hasElement() {
		if(this.top >= 0)
			return true;
		else
			return false;
	}
	
	public boolean isEmpty() {
		if(this.top == -1)
			return true;
		else
			return false;
	}
	
	public String toString() {
		StringBuffer strData = new StringBuffer();
		for(int index = (this.size - 1 ); index > this.top; index--) {
			strData.append("| "+ " "+" |");
			strData.append("\n");
			strData.append("+---+");
			strData.append("\n");
		}
		
		for(int index = this.top; index >= 0; index--) {
			if(index < 0) {				
				strData.append("| "+(this.obj[index]==null ? " " : this.obj[index])+" |");
				strData.append("\n");
				strData.append("+---+");
				strData.append("\n");
			}
			else {
				strData.append("| "+(this.obj[index]==null ? " " : this.obj[index])+" |");
				strData.append("\n");
				strData.append("+---+");
				strData.append("\n");
			}
		}
		//strData.append("]");
		return strData.toString();
	}
}
