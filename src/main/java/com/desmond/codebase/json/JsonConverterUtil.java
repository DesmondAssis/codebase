package com.desmond.codebase.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by zhanghuyi on 15/7/31.
 */
public class JsonConverterUtil {

    private static final Logger LOG = LoggerFactory.getLogger(JsonConverterUtil.class);

    public static final ObjectMapper objectMapper = new ObjectMapper();

    public static String objectToJson(Object o){
        try {
            //objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            LOG.error(e.getMessage(),e);
        }
        return "";
    }

    public static <T> T jsonToObject(String s,Class<T> c){
        try {
            return objectMapper.readValue(s, c);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    public static <T> T jsonToObject(String s, TypeReference valueTypeRef){
        try {
            return objectMapper.readValue(s, valueTypeRef);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    public static <T> T jsonToObject(String s, JavaType javaType) {
        try {
            return objectMapper.readValue(s, javaType);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return null;
    }

    public static JavaType getJaveType(Class<?> collectionClass, Class<?>...elementClasses){
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    public static JavaType getJaveType(Class<?> elementClass){
        return objectMapper.getTypeFactory().uncheckedSimpleType(elementClass);
    }

    public static <T> T objectToObject(Object o,Class<T> c){
        if (o == null) {
            return null;
        }
        return jsonToObject(objectToJson(o),c);
    }
}
