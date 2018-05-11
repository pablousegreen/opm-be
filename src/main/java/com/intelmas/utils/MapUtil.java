package com.intelmas.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/** Helper class to handle objects Map
 * @author Intelma
 *
 */
public class MapUtil {

	/**
	 * @param lineSplited Array string of the line splitted.
	 * @param splitBy String to split and separete attributes and values.
	 * @return Map with parameter-value after evaluate the array.
	 */
	public static Map<String, String> mappingLine(String[] lineSplited, String splitBy){
		Map<String, String> mapLine = new HashMap<String, String>();
		
		for(String line : lineSplited){
			String[] keyVal = line.split(splitBy);
			mapLine.put(keyVal[0].trim(), keyVal[1]);
		}
		
		return mapLine;
	}
	
	/**
	 * @param keyExtractor object to extract each key value.
	 * @return Method to do distinct in values on stream lambda function.
	 */
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) 
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
