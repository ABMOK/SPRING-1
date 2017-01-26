package pl.com.mnemonic.ems.commons.enums;

public enum HappeningOccurence {
	PERIODIC_1(1),
	PERIODIC_2(2),
	PERIODIC_3(3),
	PERIODIC_4(4),
	PERIODIC_5(5),
	PERIODIC_6(6),
	PERIODIC_7(7),
	PERIODIC_8(8),
	PERIODIC_9(9),
	PERIODIC_10(10),
	PERIODIC_11(11),
	PERIODIC_12(12);
	
	private Integer happeningOccurenceTypeValue;
	private String happeningOccurenceName;
	
	public String getHappeningOccurenceName() {
		return happeningOccurenceName;
	}

	public void setHappeningOccurenceName(String happeningOccurenceName) {
		this.happeningOccurenceName = happeningOccurenceName;
	}

	private HappeningOccurence(Integer happeningOccurenceTypeValue){
		this.happeningOccurenceTypeValue = happeningOccurenceTypeValue;
	}

	public Integer getHappeningOccurenceTypeValue() {
		return happeningOccurenceTypeValue;
	}

	public void setHappeningOccurenceTypeValue(Integer happeningOccurenceTypeValue) {
		this.happeningOccurenceTypeValue = happeningOccurenceTypeValue;
	}
	
	public static HappeningOccurence fromInteger(Integer happeningOccurenceTypeValue){
		switch(happeningOccurenceTypeValue){
		case 1:
			return PERIODIC_1;
		case 2:
			return PERIODIC_2;
		case 3:
			return PERIODIC_3;
		case 4:
			return PERIODIC_4;
		case 5:
			return PERIODIC_5;
		case 6:
			return PERIODIC_6;
		case 7:
			return PERIODIC_7;
		case 8:
			return PERIODIC_8;
		case 9:
			return PERIODIC_9;
		case 10:
			return PERIODIC_10;
		case 11:
			return PERIODIC_11;
		case 12:
			return PERIODIC_12;
		default:
			return null;
		}		
	}
	
	public static HappeningOccurence fromString(String happeningOccurenceName){
		if(happeningOccurenceName!=null){
			switch(happeningOccurenceName){
			case "PERIODIC_1":
				return PERIODIC_1;
			case "PERIODIC_2":
				return PERIODIC_2;
			case "PERIODIC_3":
				return PERIODIC_3;
			case "PERIODIC_4":
				return PERIODIC_4;
			case "PERIODIC_5":
				return PERIODIC_5;
			case "PERIODIC_6":
				return PERIODIC_6;
			case "PERIODIC_7":
				return PERIODIC_7;
			case "PERIODIC_8":
				return PERIODIC_8;
			case "PERIODIC_9":
				return PERIODIC_9;
			case "PERIODIC_10":
				return PERIODIC_10;
			case "PERIODIC_11":
				return PERIODIC_11;
			case "PERIODIC_12":
				return PERIODIC_12;
			default:
				return PERIODIC_12;
			}
		}
		return PERIODIC_12;
	}
}
