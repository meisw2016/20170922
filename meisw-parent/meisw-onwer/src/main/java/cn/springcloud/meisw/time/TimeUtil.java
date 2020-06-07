package cn.springcloud.meisw.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeUtil {
	
	private static final Logger log = LoggerFactory.getLogger(TimeUtil.class);
	
	public static Long date2timestamp(String date, boolean flag) throws ParseException {
		if (flag) {
			date += " 23:59:59";
		} else {
			date += " 00:00:00";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = sdf.parse(date);
		long t = d.getTime();
		log.info("日期：{}转换时间戳为{}",date,t);
		return t;
	}
	
	public static void main(String[] args) {
		try {
	        date2timestamp("2020-05-28", false);
	        date2timestamp("2020-05-29", true);
        } catch (ParseException e) {
	        e.printStackTrace();
        }
    }
}
