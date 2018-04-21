package czy.site.json;

import com.alibaba.fastjson.JSON;

public class JsonHelper {

	public static String ToJson(Object obj) throws Exception {
		return JSON.toJSONString(obj);
	}

	public static <T> T ToObj(String str, Class<T> clazz) throws Exception  {
		return JSON.parseObject(str, clazz);
	}
}
