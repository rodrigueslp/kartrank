package br.com.amil.kartrank.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


public class DateUtil {
	private DateUtil(){}
	
	public static LocalTime convertToLocalTime(String time, String pattern) throws Exception{
		SimpleDateFormat dt = new SimpleDateFormat(pattern);
	    Date date = dt.parse(time);
	    Instant instant = Instant.ofEpochMilli(date.getTime());
	    LocalTime res = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();
	    
	    return res;
	}
	
	
	public static LocalTime between(LocalTime init, LocalTime end){
	    LocalTime localTime = end.minusHours(init.getHour())
				.minusMinutes(init.getMinute())
				.minusSeconds(init.getSecond())
				.minusNanos(init.getNano());
	    System.out.println("FINAL:" + localTime);
		return localTime;
	}
	
	
	public static LocalTime plusTime(List<LocalTime> tempos) throws Exception{
		LocalTime localTime = LocalTime.of(0, 0, 0, 0);
		
		for(LocalTime time : tempos){
			localTime = localTime.plusHours(time.getHour())
					.plusMinutes(time.getMinute())
					.plusSeconds(time.getSecond())
					.plusNanos(time.getNano());
		}
		
		return localTime;
	}
}