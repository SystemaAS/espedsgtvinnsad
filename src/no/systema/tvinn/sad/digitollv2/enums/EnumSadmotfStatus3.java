package no.systema.tvinn.sad.digitollv2.enums;



/**
 * 
 * @author oscardelatorre
 * Oct 2023
 * 
 */
public enum EnumSadmotfStatus3 {
	PENDING("P"),
	EMPTY("");
	
	EnumSadmotfStatus3(String value) {
        this.value = value;
    }

    private String value;
    public String toString() {
        return value;
    }
}
