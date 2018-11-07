/**
 * 
 */
package com.cloud.iot.constants;

import java.text.SimpleDateFormat;

/**
 * @author VIVEK
 *
 */
public class Constants {

	public static final String URI = "/kone";
	public static final String VERSION = "/v1";
	public static final String HEALTH = "/health";
	public static final String SUCCESS = "Health Check Passed";
	public static final String SEARCH = "/equipment/search";
	public static final String EQUIPMENT = "/equipment";
	public static final String SEARCH_BY_UNIQUE_ID = "/equipment/{id}";
	
	public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
	public static final SimpleDateFormat SIMPLE_DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);
	
}
