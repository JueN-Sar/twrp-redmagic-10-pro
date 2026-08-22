package com.zte.gameassist.lowsugar.common;

import androidx.collection.ArrayMap;

/* loaded from: classes2.dex */
public class DetectParam extends ArrayMap<String, Object> {
    public Object o(String str, Object obj) {
        return getOrDefault(str, obj);
    }
}
