package com.intelmas.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


/** Class with methods and static variables to handle datetime objects
 * @author Intelma
 *
 */
public class TimeManager {

	public static DateTimeFormatter datehourfmt = DateTimeFormat.forPattern("yyyyMMdd.HHmm");
	public static java.time.format.DateTimeFormatter dateformat = java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd");
	public static java.time.format.DateTimeFormatter dateformatDb = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssZ");
	public static java.time.format.DateTimeFormatter FEFormat = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	//not used
	public static DateFormat dateFormatDb = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static final
	String SEPARATOR = ".";
	
	public static final
	String REGEXSEPARATOR = "\\.";
	
	public static final String dateDefault = "20170101.";
	
	public static
	DateTimeFormatter hourfmt 		= DateTimeFormat.forPattern("HHmm");
	
	
	/**
	 * @param startDate String with date to start the range
	 * @param endDate String with date to end the range
	 * @return List of objects LocalDate with all dates in the range given
	 */
	public static List<LocalDate> getDatesFromRange(String startDate, String endDate){
		
		List<LocalDate> dates = new ArrayList<LocalDate>();
		LocalDate start = LocalDate.parse(startDate, dateformat);
		LocalDate end = LocalDate.parse(endDate, dateformat);
		LocalDate tempDateTime = LocalDate.from( start );
		 System.out.println("from: "+ start + " to: " + end);
		 
		long days = tempDateTime.until( end, ChronoUnit.DAYS);
		tempDateTime = tempDateTime.plusDays( days );
		 System.out.println("DAYS BETWEEN: "+ days);
		for (int i = 0; i <= days; i++) {
			LocalDate d = start.plusDays(i);
			dates.add(d);
			System.out.println(d);
		}
		 
		return dates;
	}
	
	/**
	 * @param resolution int number to handle as a resolution
	 * @return Set object with times with resolution diference
	 */
	public static Set<String> getTimesByResolution(int resolution){
		Set<String> times = new HashSet<String>();;
		ZonedDateTime time = null;
		LocalDateTime now = LocalDateTime.now();
		int nowMinuts = LocalDateTime.now().getMinute();
		
		if (nowMinuts >= 0 && nowMinuts < 15){
			nowMinuts = 0;
		} else if (nowMinuts >= 15 && nowMinuts < 30){
			nowMinuts = 15;
		} else if (nowMinuts >= 30 && nowMinuts < 45){
			nowMinuts = 30;
		} else if (nowMinuts >= 45 && nowMinuts < 59){
			nowMinuts = 45;
		}
		
		time = ZonedDateTime.of(now.withMinute(nowMinuts).withSecond(0), Constants.TIMEZONE);
		times.add(time.format(dateformatDb));
		
		ZonedDateTime timeTarget = ZonedDateTime.of(now.withMinute(nowMinuts).withSecond(0).minusHours(resolution), Constants.TIMEZONE);
		
		while (!time.equals(timeTarget)) {
			time = time.minusMinutes(15);
			System.out.println(time.format(dateformatDb));
			times.add(time.format(dateformatDb));
		}
		
		
		return times;
	}
	
	/**
	 * @param minus int variable to get latest time minus this variable
	 * @return String with latest time minus the variable given
	 */
	public static String getLatest(int minus){
		
		ZonedDateTime time = null;
		LocalDateTime now = LocalDateTime.now();
		int nowMinuts = LocalDateTime.now().getMinute();
		
		
//		System.out.println("Minut: "+nowMinuts);
		if (nowMinuts >= 0 && nowMinuts < 15){
			nowMinuts = 0;
		} else if (nowMinuts >= 15 && nowMinuts < 30){
			nowMinuts = 15;
		} else if (nowMinuts >= 30 && nowMinuts < 45){
			nowMinuts = 30;
		} else if (nowMinuts >= 45 && nowMinuts <=
				59){
			nowMinuts = 45;
		}
		
			time = ZonedDateTime.of(now.withMinute(nowMinuts).minusMinutes(minus).withSecond(0), Constants.TIMEZONE);
		
		
		return time.format(dateformatDb);
	}
}
