package com.caovan.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonUtil {

	private static ObjectMapper objectMapper = new ObjectMapper();

	private static Logger logger = Logger.getLogger(JsonUtil.class);

	/**
	 * 
	* @MethodName:  toJson
	* @Description: 将对象转换为json
	* @author Van
	* @date 2017年6月13日 下午7:12:56
	 */
	public static String toJson(Object object) {

		String value = null;
		try {
		    objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")); 
			value = objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			logger.info("JsonProcessingException: " + e.toString());
		}
		return value;
	}

	/**
	 * 
	* @MethodName:  toObject
	* @Description: 将json转换为实体对象
	* @author Van
	* @date 2017年6月13日 下午7:13:19
	 */
	public static <T> T toObject(String jsonString, Class<T> classType) {
		if (null == jsonString)
			return null;
		try {
		    objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			return (T) objectMapper.readValue(jsonString, classType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {

		}

		return null;
	}

	/**
	* @MethodName:  toObjectList
	* @Description: 将json转换为list实体对象
	* @author Van
	* @date 2017年6月13日 下午7:13:49
	 */
	public static <T> List<T> toObjectList(String jsonString, Class<?> classType) {
		if (null == jsonString)
			return null;
		JavaType javaType = objectMapper.getTypeFactory().constructParametrizedType(ArrayList.class, ArrayList.class,
				classType);
		try {
		    objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			return objectMapper.readValue(jsonString, javaType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		return null;
	}

}
