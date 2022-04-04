package pe.com.project2.ms.shared;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static LocalDateTime fromStringToLocalDateTime(String date, String format) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return LocalDateTime.parse(date, formatter);
	}

	public static LocalDateTime fromStringToStartLocalDateTime(String date) {
		return LocalDate.parse(date, formatter).atStartOfDay();
	}
	
	public static LocalDateTime fromStringToEndLocalDateTime(String date) {
		return LocalDate.parse(date, formatter).atTime(LocalTime.MAX);
	}
}
