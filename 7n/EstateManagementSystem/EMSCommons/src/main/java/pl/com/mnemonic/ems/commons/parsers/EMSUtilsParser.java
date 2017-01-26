package pl.com.mnemonic.ems.commons.parsers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class EMSUtilsParser {

	public static Double parseToDouble(String doubleString){
		Number number = null;
		Double stringValue = 0.00d;
		if (doubleString != null && doubleString.trim().length() > 0) {
			NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
			try{
				 number = format.parse(doubleString);	
			} catch (Exception ex) {
				
			} 
		    		
			if (number != null && number.doubleValue() > 0){
				stringValue = number.doubleValue();
				return round(stringValue, 2);
			}				
		}
		return stringValue;
	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
}
