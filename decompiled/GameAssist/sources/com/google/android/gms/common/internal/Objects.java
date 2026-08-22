package com.google.android.gms.common.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@KeepForSdk
/* loaded from: classes.dex */
public final class Objects {

    @KeepForSdk
    public static final class ToStringHelper {

        /* renamed from: a, reason: collision with root package name */
        private final List f11020a;

        /* renamed from: b, reason: collision with root package name */
        private final Object f11021b;

        /* synthetic */ ToStringHelper(Object obj, zzai zzaiVar) {
            Preconditions.i(obj);
            this.f11021b = obj;
            this.f11020a = new ArrayList();
        }

        public ToStringHelper a(String str, Object obj) {
            Preconditions.i(str);
            this.f11020a.add(str + "=" + String.valueOf(obj));
            return this;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(100);
            sb.append(this.f11021b.getClass().getSimpleName());
            sb.append('{');
            int size = this.f11020a.size();
            for (int i2 = 0; i2 < size; i2++) {
                sb.append((String) this.f11020a.get(i2));
                if (i2 < size - 1) {
                    sb.append(", ");
                }
            }
            sb.append('}');
            return sb.toString();
        }
    }

    public static boolean a(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    public static int b(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    public static ToStringHelper c(Object obj) {
        return new ToStringHelper(obj, null);
    }
}
