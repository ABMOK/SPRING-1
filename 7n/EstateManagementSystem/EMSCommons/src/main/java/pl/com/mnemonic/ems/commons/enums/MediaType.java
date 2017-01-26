package pl.com.mnemonic.ems.commons.enums;

public enum MediaType {
	HOT_WATER("Gorąca woda", 0),
	COLD_WATER("Zimna woda", 1),
	HEATING("Centralne ogrzewanie",2),
	ELECTRICITY("Prąd", 3),
	PETROL("Paliwo (np. gaz)", 4),
	COOLING("Klimatyzacja", 5);
	
	private Integer mediaTypeValue;
	private String mediaTypeName;
	
	private MediaType(String mediaTypeName, Integer mediaTypeValue){
		this.mediaTypeName = mediaTypeName;
		this.mediaTypeValue = mediaTypeValue;
	}

	public Integer getMediaTypeValue() {
		return mediaTypeValue;
	}

	public void setMediaTypeValue(Integer mediaTypeValue) {
		this.mediaTypeValue = mediaTypeValue;
	}

	public String getMediaTypeName() {
		return mediaTypeName;
	}

	public void setMediaTypeName(String mediaTypeName) {
		this.mediaTypeName = mediaTypeName;
	}
	
	public static MediaType fromInteger(Integer mediaTypeValue){
		switch(mediaTypeValue){
		case 0:
			return HOT_WATER;
		case 1:
			return COLD_WATER;
		case 2:
			return HEATING;
		case 3:
			return ELECTRICITY;
		case 4:
			return PETROL;
		case 5:
			return COOLING;
		default:
			return null;
		}		
	}

}
