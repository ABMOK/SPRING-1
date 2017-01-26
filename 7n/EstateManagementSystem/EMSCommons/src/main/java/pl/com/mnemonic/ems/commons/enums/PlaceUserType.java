package pl.com.mnemonic.ems.commons.enums;

public enum PlaceUserType {
	OWNER("Właściciel",2),
	RENTER("Najemca",3);
	
	
	private Integer userTypeValue;
	private String userTypeName;
	
	private PlaceUserType(String userTypeName, Integer userTypeValue){
		this.userTypeValue = userTypeValue;
		this.userTypeName = userTypeName;
	}

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public Integer getUserTypeValue() {
		return userTypeValue;
	}

	public void setUserTypeValue(Integer userTypeValue) {
		this.userTypeValue = userTypeValue;
	}

public static PlaceUserType fromInteger(Integer userTypeValue){
		
		switch(userTypeValue){
		case 2:
			return OWNER;
		case 3:
			return RENTER;
		default:
			return null;
		}		
	}
	
	public static PlaceUserType fromString(String userTypeName){
		
		switch(userTypeName){
		case "ROLE_OWNER":
			return OWNER;
		case "ROLE_RENTER":
			return RENTER;
		default:
			return null;
		}		
	}
}