package com.google.gson.internal.reflect;

import com.google.gson.JsonIOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public class ReflectionHelper {
    private ReflectionHelper() {
    }

    private static String constructorToString(Constructor<?> constructor) {
        StringBuilder sb = new StringBuilder(constructor.getDeclaringClass().getName());
        sb.append('#');
        sb.append(constructor.getDeclaringClass().getSimpleName());
        sb.append('(');
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        for (int i2 = 0; i2 < parameterTypes.length; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            sb.append(parameterTypes[i2].getSimpleName());
        }
        sb.append(')');
        return sb.toString();
    }

    public static void makeAccessible(Field field) {
        try {
            field.setAccessible(true);
        } catch (Exception e2) {
            throw new JsonIOException("Failed making field '" + field.getDeclaringClass().getName() + "#" + field.getName() + "' accessible; either change its visibility or write a custom TypeAdapter for its declaring type", e2);
        }
    }

    public static String tryMakeAccessible(Constructor<?> constructor) {
        try {
            constructor.setAccessible(true);
            return null;
        } catch (Exception e2) {
            return "Failed making constructor '" + constructorToString(constructor) + "' accessible; either change its visibility or write a custom InstanceCreator or TypeAdapter for its declaring type: " + e2.getMessage();
        }
    }
}
