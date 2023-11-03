package no.systema.tvinn.sad.digitollv2.model;

import java.util.Comparator;

import lombok.Data;

@Data
public class GenericDropDownDto implements Comparable<GenericDropDownDto> {
	private String code;
	private String txt1;
	private String txt2;
	private String txt3;
	
	@Override
	public int compareTo(GenericDropDownDto record) {
		//NOT IMPLEMENTED 
		return 0;
	}
	
	public static class OrderByCode implements Comparator<GenericDropDownDto> {
        @Override
        public int compare(GenericDropDownDto o1, GenericDropDownDto o2) {
        		return o1.code.compareTo(o2.code);
        }	
	}
}
