package pl.com.mnemonic.ems.commons.enums;

public enum CommonExpenseType {
CLEANING("Sprzątanie",0),
GARBAGE_COLLECTION("Wywóz śmieci",1),
INSURANCE("Ubezpieczenie",2),
MAINTENANCE("Konserwacja",3),
ADMINISTRATION("Zarządzanie",4),
EMERGENCY("Pogotowie awaryjne",5),
RENOVATION("Remonty",6),
ANOTHER("Inne",7); 

	private Integer commonExpenseTypeValue;
	private String commonExpenseTypeName;
	
	private CommonExpenseType(String commonExpenseTypeName, Integer commonExpenseTypeValue){
		this.commonExpenseTypeName = commonExpenseTypeName;
		this.commonExpenseTypeValue = commonExpenseTypeValue;
	}

	public Integer getCommonExpenseTypeValue() {
		return commonExpenseTypeValue;
	}

	public void setCommonExpenseTypeValue(Integer commonExpenseTypeValue) {
		this.commonExpenseTypeValue = commonExpenseTypeValue;
	}

	public String getCommonExpenseTypeName() {
		return commonExpenseTypeName;
	}

	public void setCommonExpenseTypeName(String commonExpenseTypeName) {
		this.commonExpenseTypeName = commonExpenseTypeName;
	}
	
	public static CommonExpenseType fromInteger(Integer commonExpenseTypeValue){
		
		switch(commonExpenseTypeValue){
		case 0:
			return CLEANING;
		case 1:
			return GARBAGE_COLLECTION;
		case 2:
			return INSURANCE;
		case 3:
			return MAINTENANCE;
		case 4:
			return ADMINISTRATION;
		case 5:
			return EMERGENCY;
		case 6:
			return RENOVATION;
		case 7:
			return ANOTHER;
		default:
			return ANOTHER;
		}		
	}
	
	public static CommonExpenseType fromName(String commonExpenseTypeName){
		switch(commonExpenseTypeName){
		case "Sprzątanie":
			return CLEANING;
		case "Wywóz śmieci":
			return GARBAGE_COLLECTION;
		case "Ubezpieczenie":
			return INSURANCE;
		case "Konserwacja":
			return MAINTENANCE;
		case "Zarządzanie":
			return ADMINISTRATION;
		case "Pogotowie awaryjne":
			return EMERGENCY;
		case "Remonty":
			return RENOVATION;
		case "Inne":
			return ANOTHER;
		default:
			return null;
		}	
	}

}
