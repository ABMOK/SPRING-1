package pl.com.mnemonic.ems.commons.enums;

public enum SystemUserType {
	ADMIN("Administrator",0),
	USER("Użytkownik",1),
	OWNER("Właściciel",2),
	RENTER("Najemca",3),
	PUBLIC("Publiczny",4);
	
	
	private Integer userTypeValue;
	private String userTypeName;
	
	private SystemUserType(String userTypeName, Integer userTypeValue){
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

public static SystemUserType fromInteger(Integer userTypeValue){
		
		switch(userTypeValue){
		case 0:
			return ADMIN;
		case 1:
			return USER;
		case 2:
			return OWNER;
		case 3:
			return RENTER;
		default:
			return null;
		}		
	}
	
	public static SystemUserType fromString(String userTypeName){
		
		switch(userTypeName){
		case "ROLE_ADMIN":
			return ADMIN;
		case "ROLE_USER":
			return USER;
		case "ROLE_OWNER":
			return OWNER;
		case "ROLE_RENTER":
			return RENTER;
		default:
			return null;
		}		
	}
}
