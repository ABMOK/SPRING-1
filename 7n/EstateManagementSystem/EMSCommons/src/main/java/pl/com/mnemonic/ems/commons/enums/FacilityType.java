package pl.com.mnemonic.ems.commons.enums;

public enum FacilityType {
	STANDALONE_HOME("Dom jednorodzinny",0),
	MULTI_FAMILY_HOME("Dom wielorodzinny",1),
	BLOCK_OF_FLATS("Blok mieszkalny",2),
	STANDALONE_OFFICE("Samodzielne biuro",3),
	OFFICE_BUILDING("Biurowiec",4);
	
	private Integer facilityTypeValue;
	private String facilityTypeName;
	
	private FacilityType(String facilityTypeName, Integer facilityTypeValue){
		this.facilityTypeName = facilityTypeName;
		this.facilityTypeValue = facilityTypeValue;
	}
	
/*	private FacilityType(Integer facilityTypeValue){
		this.facilityTypeValue = facilityTypeValue;
	}
	
	private FacilityType(String facilityTypeName){
		this.facilityTypeName = facilityTypeName;
	}
*/
	public Integer getFacilityTypeValue() {
		return facilityTypeValue;
	}

	public void setFacilityTypeValue(Integer facilityTypeValue) {
		this.facilityTypeValue = facilityTypeValue;
	}
	
	public String getFacilityTypeName() {
		return facilityTypeName;
	}

	public void setFacilityTypeName(String facilityTypeName) {
		this.facilityTypeName = facilityTypeName;
	}

	public static FacilityType fromInteger(Integer facilityTypeValue){
		
		switch(facilityTypeValue){
		case 0:
			return STANDALONE_HOME;
		case 1:
			return MULTI_FAMILY_HOME;
		case 2:
			return BLOCK_OF_FLATS;
		case 3:
			return STANDALONE_OFFICE;
		case 4:
			return OFFICE_BUILDING;
		default:
			return null;
		}		
	}
	
	public static FacilityType fromString(String facilityTypeName){
		
		switch(facilityTypeName){
		case "STANDALONE_HOME":
			return STANDALONE_HOME;
		case "MULTI_FAMILY_HOME":
			return MULTI_FAMILY_HOME;
		case "BLOCK_OF_FLATS":
			return BLOCK_OF_FLATS;
		case "STANDALONE_OFFICE":
			return STANDALONE_OFFICE;
		case "OFFICE_BUILDING":
			return OFFICE_BUILDING;
		default:
			return null;
		}		
	}
	
	
}
