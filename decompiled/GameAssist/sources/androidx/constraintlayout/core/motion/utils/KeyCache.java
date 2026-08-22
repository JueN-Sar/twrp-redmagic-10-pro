package androidx.constraintlayout.core.motion.utils;

import java.util.Arrays;
import java.util.HashMap;

/* loaded from: classes.dex */
public class KeyCache {

    /* renamed from: a, reason: collision with root package name */
    HashMap f1771a = new HashMap();

    public float a(Object obj, String str, int i2) {
        HashMap hashMap;
        float[] fArr;
        if (this.f1771a.containsKey(obj) && (hashMap = (HashMap) this.f1771a.get(obj)) != null && hashMap.containsKey(str) && (fArr = (float[]) hashMap.get(str)) != null && fArr.length > i2) {
            return fArr[i2];
        }
        return Float.NaN;
    }

    public void b(Object obj, String str, int i2, float f2) {
        if (!this.f1771a.containsKey(obj)) {
            HashMap hashMap = new HashMap();
            float[] fArr = new float[i2 + 1];
            fArr[i2] = f2;
            hashMap.put(str, fArr);
            this.f1771a.put(obj, hashMap);
            return;
        }
        HashMap hashMap2 = (HashMap) this.f1771a.get(obj);
        if (hashMap2 == null) {
            hashMap2 = new HashMap();
        }
        if (!hashMap2.containsKey(str)) {
            float[] fArr2 = new float[i2 + 1];
            fArr2[i2] = f2;
            hashMap2.put(str, fArr2);
            this.f1771a.put(obj, hashMap2);
            return;
        }
        float[] fArr3 = (float[]) hashMap2.get(str);
        if (fArr3 == null) {
            fArr3 = new float[0];
        }
        if (fArr3.length <= i2) {
            fArr3 = Arrays.copyOf(fArr3, i2 + 1);
        }
        fArr3[i2] = f2;
        hashMap2.put(str, fArr3);
    }
}
