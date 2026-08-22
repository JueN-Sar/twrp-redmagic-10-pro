package cn.nubia.componentsdk.pay;

import java.util.HashMap;

/* loaded from: classes.dex */
public class HashParam<K, V> extends HashMap<Object, Object> {
    private static final long serialVersionUID = 1;

    /* JADX WARN: Multi-variable type inference failed */
    public Object a(Object obj, Object obj2) {
        if (obj == 0) {
            return null;
        }
        return obj2 == 0 ? put(obj, "") : put(obj, obj2);
    }
}
