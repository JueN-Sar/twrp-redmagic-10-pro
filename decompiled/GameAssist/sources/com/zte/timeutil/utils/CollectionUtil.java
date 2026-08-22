package com.zte.timeutil.utils;

import java.util.Collection;
import java.util.Map;

/* loaded from: classes2.dex */
public class CollectionUtil {
    public static boolean a(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean b(Map map) {
        return map == null || map.isEmpty();
    }

    public static boolean c(Collection collection) {
        return !a(collection);
    }
}
