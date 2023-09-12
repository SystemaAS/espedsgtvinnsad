package no.systema.tvinn.sad.digitollv2.controller;

import no.systema.tvinn.sad.digitollv2.util.RedirectCleaner;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String core = "{description=Customs declaration is not valid, pointer={messageElementPath=consignmentHouseLevel.previousDocuments[0]}}";
		RedirectCleaner cleaner = new RedirectCleaner();
				
		System.out.println(cleaner.clean(core));

	}

}
