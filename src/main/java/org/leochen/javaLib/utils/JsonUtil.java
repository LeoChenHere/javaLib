package org.leochen.javaLib.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * @author geralt
 * @date 2022.8.30
 */
public class JsonUtil {
    static private final ObjectMapper OBJECT_MAPPER = new ObjectMapper().findAndRegisterModules();

    public static String toJsonString(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T>T parseJson(String json, Class<T> tClass) {
        try {
            return OBJECT_MAPPER.readValue(json,tClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> parseList(String json, Class<T> tClass) {
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(List.class, tClass);
        try {
            return OBJECT_MAPPER.readValue(json, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonNode string2Json(String data) {
        try {
            return OBJECT_MAPPER.readTree(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
