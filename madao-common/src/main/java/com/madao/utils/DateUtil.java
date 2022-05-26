package com.madao.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.TimeZone;

/**
 * LocalDate LocalTime LocalDateTime日期工具类
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public class DateUtil {

    private static final ZoneId CHINA_ZONE = ZoneId.of("+8");

    public static void setDefaultZone() {
        TimeZone.setDefault(TimeZone.getTimeZone(CHINA_ZONE));
    }

    /**
     * 获取时间戳
     */
    public static Long getTimestamp() {
        return Instant.now().toEpochMilli();
    }

	/**
	 * 获取指定时间的时间戳
	 */
    public static Long getTimestampOfHourAndMinute(Integer hour,Integer minute) {
        return LocalDateTime.of(
				        LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth(),
				        hour, minute, 0, 0
		        )
		        .toInstant(ZoneOffset.ofHours(8))
		        .toEpochMilli();
    }

    /**
     * 时间戳转日期
     */
    public static String timeStampToDate(Long timestamp) {
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault()));
    }

	/**
	 * 日期转时间戳
	 */
	public static long dateToTimeStamp(String date) {
		DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime localDateTime = LocalDateTime.parse(date,ftf);
		return localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
	}

	/**
	 * 不能小于当前日期
	 */
	public static boolean lessThanOrEqualTo(Long  timestamp) {
		LocalDateTime appointmentDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
		LocalDateTime currentTime = getCurrentTime();
		int isItEffective = currentTime.compareTo(appointmentDate);
		return isItEffective >= 0;
	}


	/**
	 * 时间戳转日期
	 */
	public static LocalDateTime timeStampToLocalDateTime(Long timestamp) {
		return Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
	}
    /**
     * 获取当前时间
     */
    public static LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }

	/**
	 * 根据时间戳获取与当前间隔毫秒数
	 * @param time 时间戳
	 */
	public static long betweenDuration(long time) {
		LocalDateTime currentTime = getCurrentTime();
		LocalDateTime localDateTime = DateUtil.timeStampToLocalDateTime(time);
		Duration duration = Duration.between(currentTime,  localDateTime );
		return duration.toMillis();
	}

    /**
     * 当前日期加几天，返回下一天的当前时间
     *
     * @param days 天
     * @return LocalDateTime
     */
    public static LocalDateTime getPlusDays(Integer days) {
        return LocalDate.now().plusDays(days).atTime(LocalTime.now());
    }


    /**
     * 当前日期减几天
     *
     * @param days 天
     * @return LocalDateTime
     */
    public static LocalDateTime getMinusDays(Integer days) {
        return LocalDate.now().minusDays(days).atTime(LocalTime.now());
    }


    /**
     * 当前日期加几周
     *
     * @param weeks 周
     * @return LocalDate
     */
    public static LocalDateTime getPlusWeeks(Integer weeks) {
        return LocalDate.now().plusWeeks(weeks).atTime(LocalTime.now());
    }

    /**
     * 当前日期减几周
     *
     * @param weeks 周
     * @return LocalDate
     */
    public static LocalDateTime getMinusWeeks(Integer weeks) {
        return LocalDate.now().minusWeeks(weeks).atTime(LocalTime.now());
    }

    /**
     * 当前日期加几月
     *
     * @param months 月
     * @return LocalDate
     */
    public static LocalDateTime getPlusMonths(Integer months) {
        return LocalDate.now().plusMonths(months).atTime(LocalTime.now());
    }

    /**
     * 当前日期减几月
     *
     * @param months 月
     * @return LocalDate
     */
    public static LocalDateTime getMinusMonths(Integer months) {
        return LocalDate.now().minusMonths(months).atTime(LocalTime.now());
    }

    /**
     * Date转换为LocalDateTime
     *
     * @param date new Date();
     * @return LocalDateTime
     */
    public static LocalDateTime convertDateToLdt(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }


    /**
     * LocalDateTime转换为Date
     *
     * @param time LocalDateTime
     * @return Date
     */
    public static Date convertLdtToDate(LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }


    /**
     * 获取指定日期的毫秒
     *
     * @param time LocalDateTime
     * @return Long
     */
    public static Long getMilliByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取指定日期的秒
     *
     * @param time LocalDateTime
     * @return Long
     */
    public static Long getSecondsByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    /**
     * 获取指定时间的指定格式
     *
     * @param time    LocalDateTime
     * @param pattern yyyy年MM月dd日 HH:mm
     * @return String
     */
    public static String formatTime(LocalDateTime time, String pattern) {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前时间的指定格式
     *
     * @param pattern yyyy年MM月dd日 HH:mm
     * @return
     */
    public static String formatNow(String pattern) {
        return formatTime(LocalDateTime.now(), pattern);
    }

    /**
     * 日期加上一个数,根据field不同加不同值,field为ChronoUnit.*
     *
     * @param time
     * @param number
     * @param field
     * @return
     */
    public static LocalDateTime plus(LocalDateTime time, long number, TemporalUnit field) {
        return time.plus(number, field);
    }

    /**
     * 日期减去一个数,根据field不同减不同值,field参数为ChronoUnit.*
     *
     * @param time
     * @param number
     * @param field
     * @return
     */
    public static LocalDateTime minu(LocalDateTime time, long number, TemporalUnit field) {
        return time.minus(number, field);
    }

    /**
     * 获取两个日期的差  field参数为ChronoUnit.*
     *
     * @param startTime：开始时间
     * @param endTime：结束时间
     * @param field          单位(年月日时分秒)
     * @return 间隔
     */
    public static long betweenTwoTime(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit field) {
        Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));
        if (field == ChronoUnit.YEARS) {
            return period.getYears();
        }
        if (field == ChronoUnit.MONTHS) {
            return period.getYears() * 12 + period.getMonths();
        }
        return field.between(startTime, endTime);
    }

    /**
     * 获取一天的开始时间，2017,7,22 00:00
     */
    public static LocalDateTime getDayStart(LocalDateTime time) {
        return time.withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    /**
     * 获取一天的结束时间，2017,7,22 23:59:59.999999999
     *
     * @param time
     */
    public static LocalDateTime getDayEnd(LocalDateTime time) {
        return time.withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(999999999);
    }
}
