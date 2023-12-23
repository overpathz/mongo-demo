package org.pathz.jsonconverterexample;

import lombok.SneakyThrows;

import java.lang.reflect.Field;

public class MyJsonConverter {
    public static String toJson(Object obj) {
        Class<?> aClass = obj.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        StringBuilder jsonBuilder = new StringBuilder("{\n");
        int fieldCount = 0;
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            if (fieldCount > 0) {
                jsonBuilder.append(",\n");
            }
            jsonBuilder.append('\t');
            addKeyValue(declaredField, jsonBuilder, obj);
            fieldCount++;
        }
        return jsonBuilder.append('\n').append('}').toString();
    }

    private static void addKeyValue(Field dField, StringBuilder builder, Object obj) {
        String template = "\"%s\": %s";
        String key = dField.getName();
        String value = getValueDependOnType(dField, obj);
        builder.append(String.format(template, key, value));
    }

    @SneakyThrows
    private static String getValueDependOnType(Field dField, Object obj) {
        try {
            Object object = dField.get(obj);
            if (dField.getType().isAssignableFrom(String.class)) {
                return addQuotesToValue(object);
            } else if (dField.get(obj) instanceof Number) {
                return dField.get(obj).toString();
            } else {
                return obj.toString();
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static String addQuotesToValue(Object object) {
        return String.format("\"%s\"", object);
    }
}

