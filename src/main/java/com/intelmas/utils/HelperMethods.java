package com.intelmas.utils;

/** Helper class 
 * @author Intelma
 *
 */
public class HelperMethods {

	public static String getVendor(String vendorRequest){
		String vendor = "";
		
		if("ericsson".equalsIgnoreCase(vendorRequest)){
			vendor = Constants.ERICSSON;
		}
		
		return vendor;
	}
}
