package com.deamon.main;

import java.io.UnsupportedEncodingException;

public abstract class Main {

	public abstract void s();

	public static void main(String[] args) throws UnsupportedEncodingException {
		pri(new test() {

			@Override
			public void a() {
				// TODO Auto-generated method stub
				System.out.println("ss");
			}

			@Override
			public String toString() {
				return "to str";
			}

		});
	}

	private static void pri(test t) {
		System.out.println(t.toString());
	}
}

interface test {
	void a();

}