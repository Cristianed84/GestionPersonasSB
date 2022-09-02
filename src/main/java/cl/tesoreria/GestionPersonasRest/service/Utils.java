package cl.tesoreria.GestionPersonasRest.service;

import java.util.Base64;

public class Utils {
	
	public String b64Encode(String val) {
		
		String encodedString = Base64.getEncoder().encodeToString(val.getBytes());
		return encodedString;
	}
	
}
