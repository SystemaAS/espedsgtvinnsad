package no.systema.main.util;

public class Tester {

	public static void main(String[] args) {
		StringManager strMgr = new StringManager();
		String s= strMgr.leadingStringWithNumericFiller("5000160", 9, "0");
		System.out.println(s);
	}

}
