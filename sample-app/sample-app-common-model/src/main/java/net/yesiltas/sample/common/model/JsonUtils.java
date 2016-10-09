package net.yesiltas.sample.common.model;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utility class to provide static helper methods to convert objects to JSON
 * string or vice versa
 * 
 * @author Meltem
 *
 */
public class JsonUtils {

	private JsonUtils() {
		super();
	}

	/**
	 * Converts JSON string to Object
	 * 
	 * @param json
	 *            json string
	 * @param clazz
	 *            type of the object to convert
	 * @return converted object with the given type
	 * @throws IOException
	 */
	public static <T> T toObject(String json, Class<T> clazz) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json.getBytes(), clazz);
	}

	/**
	 * Converts JSON string to a list of objects
	 * 
	 * @param json
	 *            json string
	 * @param clazz
	 *            type of the objects expected in the list
	 * @return list of objects of the given type
	 * @throws IOException
	 */
	public static <T> List<T> toList(String json, Class<T> clazz) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
	}

	/**
	 * Converts JSON string to Map
	 * 
	 * @param json
	 *            json string
	 * @param keyClass
	 *            expected type of key objects in map
	 * @param valueClass
	 *            expected type of value objects in map
	 * @return a map of key, value pairs with the given types
	 * @throws IOException
	 */
	public static <K, V> Map<K, V> toMap(String json, Class<K> keyClass, Class<V> valueClass) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, mapper.getTypeFactory().constructMapType(Map.class, keyClass, valueClass));
	}

	/**
	 * Converts given object to JSON string
	 * 
	 * @param object
	 *            object to convert
	 * @return JSON representation of the given object
	 * @throws JsonProcessingException
	 */
	public static <T> String toJson(T object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}
}
