package model.Util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class TimeUtil {


	 public static LocalDate parseTimeFromInput(String input, String pattern){
		 return LocalDate.parse(input, DateTimeFormatter.ofPattern(pattern));
	 }
	 public static LocalDate getCurrentTime(){
	 	 return LocalDate.now();
	 }

}
