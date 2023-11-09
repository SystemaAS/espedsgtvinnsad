package no.systema.tvinn.sad.digitollv2.enums;



/**
 * X -- Executing a special case
 *
 * @author oscardelatorre
 *
 */
public enum EnumSadmomfStatus {
	EXECUTING("X"),
	EMPTY("");
	
	EnumSadmomfStatus(String value) {
        this.value = value;
    }

    private String value;
    public String toString() {
        return value;
    }
}
