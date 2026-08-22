package com.google.android.gms.common.server.response;

import android.os.Parcel;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.common.util.MapUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@ShowFirstParty
@KeepForSdk
/* loaded from: classes.dex */
public abstract class FastJsonResponse {

    @SafeParcelable.Class
    @ShowFirstParty
    @KeepForSdk
    public static class Field<I, O> extends AbstractSafeParcelable {
        public static final zaj CREATOR = new zaj();

        /* renamed from: c, reason: collision with root package name */
        private final int f11195c;

        /* renamed from: h, reason: collision with root package name */
        protected final int f11196h;

        /* renamed from: i, reason: collision with root package name */
        protected final boolean f11197i;

        /* renamed from: j, reason: collision with root package name */
        protected final int f11198j;

        /* renamed from: k, reason: collision with root package name */
        protected final boolean f11199k;

        /* renamed from: l, reason: collision with root package name */
        protected final String f11200l;

        /* renamed from: m, reason: collision with root package name */
        protected final int f11201m;

        /* renamed from: n, reason: collision with root package name */
        protected final Class f11202n;

        /* renamed from: o, reason: collision with root package name */
        protected final String f11203o;

        /* renamed from: p, reason: collision with root package name */
        private zan f11204p;

        /* renamed from: q, reason: collision with root package name */
        private final FieldConverter f11205q;

        Field(int i2, int i3, boolean z, int i4, boolean z2, String str, int i5, String str2, com.google.android.gms.common.server.converter.zaa zaaVar) {
            this.f11195c = i2;
            this.f11196h = i3;
            this.f11197i = z;
            this.f11198j = i4;
            this.f11199k = z2;
            this.f11200l = str;
            this.f11201m = i5;
            if (str2 == null) {
                this.f11202n = null;
                this.f11203o = null;
            } else {
                this.f11202n = SafeParcelResponse.class;
                this.f11203o = str2;
            }
            if (zaaVar == null) {
                this.f11205q = null;
            } else {
                this.f11205q = zaaVar.P();
            }
        }

        public int G() {
            return this.f11201m;
        }

        final com.google.android.gms.common.server.converter.zaa P() {
            FieldConverter fieldConverter = this.f11205q;
            if (fieldConverter == null) {
                return null;
            }
            return com.google.android.gms.common.server.converter.zaa.G(fieldConverter);
        }

        public final Object T(Object obj) {
            Preconditions.i(this.f11205q);
            return this.f11205q.d(obj);
        }

        final String W() {
            String str = this.f11203o;
            if (str == null) {
                return null;
            }
            return str;
        }

        public final Map Y() {
            Preconditions.i(this.f11203o);
            Preconditions.i(this.f11204p);
            return (Map) Preconditions.i(this.f11204p.P(this.f11203o));
        }

        public final void a0(zan zanVar) {
            this.f11204p = zanVar;
        }

        public final boolean e0() {
            return this.f11205q != null;
        }

        public final String toString() {
            Objects.ToStringHelper a2 = Objects.c(this).a("versionCode", Integer.valueOf(this.f11195c)).a("typeIn", Integer.valueOf(this.f11196h)).a("typeInArray", Boolean.valueOf(this.f11197i)).a("typeOut", Integer.valueOf(this.f11198j)).a("typeOutArray", Boolean.valueOf(this.f11199k)).a("outputFieldName", this.f11200l).a("safeParcelFieldId", Integer.valueOf(this.f11201m)).a("concreteTypeName", W());
            Class cls = this.f11202n;
            if (cls != null) {
                a2.a("concreteType.class", cls.getCanonicalName());
            }
            FieldConverter fieldConverter = this.f11205q;
            if (fieldConverter != null) {
                a2.a("converterName", fieldConverter.getClass().getCanonicalName());
            }
            return a2.toString();
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i2) {
            int i3 = this.f11195c;
            int a2 = SafeParcelWriter.a(parcel);
            SafeParcelWriter.g(parcel, 1, i3);
            SafeParcelWriter.g(parcel, 2, this.f11196h);
            SafeParcelWriter.c(parcel, 3, this.f11197i);
            SafeParcelWriter.g(parcel, 4, this.f11198j);
            SafeParcelWriter.c(parcel, 5, this.f11199k);
            SafeParcelWriter.m(parcel, 6, this.f11200l, false);
            SafeParcelWriter.g(parcel, 7, G());
            SafeParcelWriter.m(parcel, 8, W(), false);
            SafeParcelWriter.l(parcel, 9, P(), i2, false);
            SafeParcelWriter.b(parcel, a2);
        }
    }

    @ShowFirstParty
    public interface FieldConverter<I, O> {
        Object d(Object obj);
    }

    protected static final Object i(Field field, Object obj) {
        return field.f11205q != null ? field.T(obj) : obj;
    }

    private static final void k(StringBuilder sb, Field field, Object obj) {
        int i2 = field.f11196h;
        if (i2 == 11) {
            Class cls = field.f11202n;
            Preconditions.i(cls);
            sb.append(((FastJsonResponse) cls.cast(obj)).toString());
        } else {
            if (i2 != 7) {
                sb.append(obj);
                return;
            }
            sb.append("\"");
            sb.append(JsonUtils.a((String) obj));
            sb.append("\"");
        }
    }

    public abstract Map a();

    protected Object b(Field field) {
        String str = field.f11200l;
        if (field.f11202n == null) {
            return d(str);
        }
        Preconditions.n(d(str) == null, "Concrete field shouldn't be value object: %s", field.f11200l);
        try {
            return getClass().getMethod("get" + Character.toUpperCase(str.charAt(0)) + str.substring(1), null).invoke(this, null);
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    protected abstract Object d(String str);

    protected boolean f(Field field) {
        if (field.f11198j != 11) {
            return g(field.f11200l);
        }
        if (field.f11199k) {
            throw new UnsupportedOperationException("Concrete type arrays not supported");
        }
        throw new UnsupportedOperationException("Concrete types not supported");
    }

    protected abstract boolean g(String str);

    public String toString() {
        Map a2 = a();
        StringBuilder sb = new StringBuilder(100);
        for (String str : a2.keySet()) {
            Field field = (Field) a2.get(str);
            if (f(field)) {
                Object i2 = i(field, b(field));
                if (sb.length() == 0) {
                    sb.append("{");
                } else {
                    sb.append(",");
                }
                sb.append("\"");
                sb.append(str);
                sb.append("\":");
                if (i2 != null) {
                    switch (field.f11198j) {
                        case 8:
                            sb.append("\"");
                            sb.append(Base64Utils.a((byte[]) i2));
                            sb.append("\"");
                            break;
                        case 9:
                            sb.append("\"");
                            sb.append(Base64Utils.b((byte[]) i2));
                            sb.append("\"");
                            break;
                        case 10:
                            MapUtils.a(sb, (HashMap) i2);
                            break;
                        default:
                            if (field.f11197i) {
                                ArrayList arrayList = (ArrayList) i2;
                                sb.append("[");
                                int size = arrayList.size();
                                for (int i3 = 0; i3 < size; i3++) {
                                    if (i3 > 0) {
                                        sb.append(",");
                                    }
                                    Object obj = arrayList.get(i3);
                                    if (obj != null) {
                                        k(sb, field, obj);
                                    }
                                }
                                sb.append("]");
                                break;
                            } else {
                                k(sb, field, i2);
                                break;
                            }
                    }
                } else {
                    sb.append("null");
                }
            }
        }
        if (sb.length() > 0) {
            sb.append("}");
        } else {
            sb.append("{}");
        }
        return sb.toString();
    }
}
