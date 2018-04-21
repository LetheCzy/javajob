package czy.site.convert;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.util.StringUtils;

public class ConvertHelper {

	public static boolean IsNullOrEmpty(Object obj) {
		return obj == null || StringUtils.isEmpty(String.valueOf(obj).trim());
	}

	public static String ToString(Object obj) {
		if (IsNullOrEmpty(obj)) {
			return "";
		}
		return String.valueOf(obj);
	}

	/// <summary>
	/// 对象转整型,默认为 int32
	/// </summary>
	/// <param name="obj">待转换对象</param>
	/// <param name="defaultValue">默认值</param>
	/// <returns>转换后的整数</returns>
	public static int ToInt(Object obj, int defaultValue) {
		if (IsNullOrEmpty(obj))
			return defaultValue;
		if (obj.getClass().isEnum()) {
			return (int) obj;
		}

		int value = defaultValue;
		try {
			value = Integer.parseInt(obj.toString());
		} catch (Exception e) {
			// value = defaultValue;
		}

		return value;
	}

	/// <summary>
	/// 对象转长整型,默认为 int64
	/// </summary>
	/// <param name="obj">待转换对象</param>
	/// <param name="defaultValue">默认值</param>
	/// <returns>转换后的整数</returns>
	public static long ToLong(Object obj, long defaultValue) {
		if (IsNullOrEmpty(obj))
			return defaultValue;
		if (obj.getClass().isEnum()) {
			return (int) obj;
		}
		long value = defaultValue;
		try {
			value = Long.parseLong(obj.toString());
		} catch (Exception e) {
			// value = defaultValue;
		}

		return value;
	}

	public static Date ToDateTime(Object obj) throws Exception {
		try {
			return new SimpleDateFormat().parse(obj.toString());
		} catch (Exception e) {
			throw e;
			// return cal.getTime();
		}
	}

	/// <summary>
	///
	/// </summary>
	/// <param name="obj"></param>
	/// <param name="defaultDateTime">默认值</param>
	/// <returns></returns>
	public static Date ToDateTime(Object obj, Date defaultDateTime) {
		Calendar cal = Calendar.getInstance();
		cal.set(1900, 1, 1);

		try {
			return ToDateTime(obj);
		} catch (Exception e) {
			return defaultDateTime;
		}
	}
	
	public static String FormatDate(Date obj, String defaultFormat) {
		
		if(ConvertHelper.IsNullOrEmpty(defaultFormat)) {
			defaultFormat = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat format = new SimpleDateFormat(defaultFormat);
		return format.format(obj);
	}

	public static BigDecimal ToDecimal(Object obj) {
		try {
			BigDecimal d = new BigDecimal(obj.toString());
			return d;
		} catch (Exception e) {
			return new BigDecimal("0");
		}
	}

	// public static String ByteToString(Object data)
	// {
	// return Encoding.UTF8.GetString((byte[]) data);
	// }

	public static boolean ToBoolean(Object obj) throws Exception {
		try {
			return Boolean.parseBoolean(obj.toString());
		} catch (Exception e) {
			throw e;
			// TODO: handle exception
		}
	}

}
