package no.systema.tvinn.sad.digitollv2.model;

public class TesterSpace {

	public static void main(String[] args) {
		String str = " FXN 477 155 ";
		System.out.println(str.length());
		str = str.trim();
		System.out.println("After trim:" + str.length());
		str = str.replaceAll(" ", "");
		System.out.println("After replace all:" + str.length());
		System.out.println("final:" + str);

	}

}
