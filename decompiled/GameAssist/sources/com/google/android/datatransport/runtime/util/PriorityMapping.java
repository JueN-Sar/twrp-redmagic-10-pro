package com.google.android.datatransport.runtime.util;

import android.util.SparseArray;
import com.google.android.datatransport.Priority;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class PriorityMapping {

    /* renamed from: a, reason: collision with root package name */
    private static SparseArray f10437a = new SparseArray();

    /* renamed from: b, reason: collision with root package name */
    private static HashMap f10438b;

    static {
        HashMap hashMap = new HashMap();
        f10438b = hashMap;
        hashMap.put(Priority.DEFAULT, 0);
        f10438b.put(Priority.VERY_LOW, 1);
        f10438b.put(Priority.HIGHEST, 2);
        for (Priority priority : f10438b.keySet()) {
            f10437a.append(((Integer) f10438b.get(priority)).intValue(), priority);
        }
    }

    public static int a(Priority priority) {
        Integer num = (Integer) f10438b.get(priority);
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("PriorityMapping is missing known Priority value " + priority);
    }

    public static Priority b(int i2) {
        Priority priority = (Priority) f10437a.get(i2);
        if (priority != null) {
            return priority;
        }
        throw new IllegalArgumentException("Unknown Priority for value " + i2);
    }
}
