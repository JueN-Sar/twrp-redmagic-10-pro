package com.google.android.gms.common.server.response;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.common.util.MapUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@KeepForSdk
@SafeParcelable.Class
/* loaded from: classes.dex */
public class SafeParcelResponse extends FastSafeParcelableJsonResponse {

    @NonNull
    @KeepForSdk
    public static final Parcelable.Creator<SafeParcelResponse> CREATOR = new zaq();

    /* renamed from: c, reason: collision with root package name */
    private final int f11220c;

    /* renamed from: h, reason: collision with root package name */
    private final Parcel f11221h;

    /* renamed from: i, reason: collision with root package name */
    private final int f11222i = 2;

    /* renamed from: j, reason: collision with root package name */
    private final zan f11223j;

    /* renamed from: k, reason: collision with root package name */
    private final String f11224k;

    /* renamed from: l, reason: collision with root package name */
    private int f11225l;

    /* renamed from: m, reason: collision with root package name */
    private int f11226m;

    SafeParcelResponse(int i2, Parcel parcel, zan zanVar) {
        this.f11220c = i2;
        this.f11221h = (Parcel) Preconditions.i(parcel);
        this.f11223j = zanVar;
        this.f11224k = zanVar == null ? null : zanVar.G();
        this.f11225l = 2;
    }

    private final void m(StringBuilder sb, Map map, Parcel parcel) {
        SparseArray sparseArray = new SparseArray();
        for (Map.Entry entry : map.entrySet()) {
            sparseArray.put(((FastJsonResponse.Field) entry.getValue()).G(), entry);
        }
        sb.append('{');
        int F = SafeParcelReader.F(parcel);
        boolean z = false;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            Map.Entry entry2 = (Map.Entry) sparseArray.get(SafeParcelReader.u(y));
            if (entry2 != null) {
                if (z) {
                    sb.append(",");
                }
                String str = (String) entry2.getKey();
                FastJsonResponse.Field field = (FastJsonResponse.Field) entry2.getValue();
                sb.append("\"");
                sb.append(str);
                sb.append("\":");
                if (field.e0()) {
                    int i2 = field.f11198j;
                    switch (i2) {
                        case 0:
                            o(sb, field, FastJsonResponse.i(field, Integer.valueOf(SafeParcelReader.A(parcel, y))));
                            break;
                        case 1:
                            o(sb, field, FastJsonResponse.i(field, SafeParcelReader.c(parcel, y)));
                            break;
                        case 2:
                            o(sb, field, FastJsonResponse.i(field, Long.valueOf(SafeParcelReader.B(parcel, y))));
                            break;
                        case 3:
                            o(sb, field, FastJsonResponse.i(field, Float.valueOf(SafeParcelReader.x(parcel, y))));
                            break;
                        case 4:
                            o(sb, field, FastJsonResponse.i(field, Double.valueOf(SafeParcelReader.w(parcel, y))));
                            break;
                        case 5:
                            o(sb, field, FastJsonResponse.i(field, SafeParcelReader.a(parcel, y)));
                            break;
                        case 6:
                            o(sb, field, FastJsonResponse.i(field, Boolean.valueOf(SafeParcelReader.v(parcel, y))));
                            break;
                        case 7:
                            o(sb, field, FastJsonResponse.i(field, SafeParcelReader.o(parcel, y)));
                            break;
                        case 8:
                        case 9:
                            o(sb, field, FastJsonResponse.i(field, SafeParcelReader.g(parcel, y)));
                            break;
                        case 10:
                            Bundle f2 = SafeParcelReader.f(parcel, y);
                            HashMap hashMap = new HashMap();
                            for (String str2 : f2.keySet()) {
                                hashMap.put(str2, (String) Preconditions.i(f2.getString(str2)));
                            }
                            o(sb, field, FastJsonResponse.i(field, hashMap));
                            break;
                        case 11:
                            throw new IllegalArgumentException("Method does not accept concrete type.");
                        default:
                            throw new IllegalArgumentException("Unknown field out type = " + i2);
                    }
                } else if (field.f11199k) {
                    sb.append("[");
                    switch (field.f11198j) {
                        case 0:
                            ArrayUtils.e(sb, SafeParcelReader.j(parcel, y));
                            break;
                        case 1:
                            ArrayUtils.g(sb, SafeParcelReader.d(parcel, y));
                            break;
                        case 2:
                            ArrayUtils.f(sb, SafeParcelReader.k(parcel, y));
                            break;
                        case 3:
                            ArrayUtils.d(sb, SafeParcelReader.i(parcel, y));
                            break;
                        case 4:
                            ArrayUtils.c(sb, SafeParcelReader.h(parcel, y));
                            break;
                        case 5:
                            ArrayUtils.g(sb, SafeParcelReader.b(parcel, y));
                            break;
                        case 6:
                            ArrayUtils.h(sb, SafeParcelReader.e(parcel, y));
                            break;
                        case 7:
                            ArrayUtils.i(sb, SafeParcelReader.p(parcel, y));
                            break;
                        case 8:
                        case 9:
                        case 10:
                            throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
                        case 11:
                            Parcel[] m2 = SafeParcelReader.m(parcel, y);
                            int length = m2.length;
                            for (int i3 = 0; i3 < length; i3++) {
                                if (i3 > 0) {
                                    sb.append(",");
                                }
                                m2[i3].setDataPosition(0);
                                m(sb, field.Y(), m2[i3]);
                            }
                            break;
                        default:
                            throw new IllegalStateException("Unknown field type out.");
                    }
                    sb.append("]");
                } else {
                    switch (field.f11198j) {
                        case 0:
                            sb.append(SafeParcelReader.A(parcel, y));
                            break;
                        case 1:
                            sb.append(SafeParcelReader.c(parcel, y));
                            break;
                        case 2:
                            sb.append(SafeParcelReader.B(parcel, y));
                            break;
                        case 3:
                            sb.append(SafeParcelReader.x(parcel, y));
                            break;
                        case 4:
                            sb.append(SafeParcelReader.w(parcel, y));
                            break;
                        case 5:
                            sb.append(SafeParcelReader.a(parcel, y));
                            break;
                        case 6:
                            sb.append(SafeParcelReader.v(parcel, y));
                            break;
                        case 7:
                            String o2 = SafeParcelReader.o(parcel, y);
                            sb.append("\"");
                            sb.append(JsonUtils.a(o2));
                            sb.append("\"");
                            break;
                        case 8:
                            byte[] g2 = SafeParcelReader.g(parcel, y);
                            sb.append("\"");
                            sb.append(Base64Utils.a(g2));
                            sb.append("\"");
                            break;
                        case 9:
                            byte[] g3 = SafeParcelReader.g(parcel, y);
                            sb.append("\"");
                            sb.append(Base64Utils.b(g3));
                            sb.append("\"");
                            break;
                        case 10:
                            Bundle f3 = SafeParcelReader.f(parcel, y);
                            Set<String> keySet = f3.keySet();
                            sb.append("{");
                            boolean z2 = true;
                            for (String str3 : keySet) {
                                if (!z2) {
                                    sb.append(",");
                                }
                                sb.append("\"");
                                sb.append(str3);
                                sb.append("\":\"");
                                sb.append(JsonUtils.a(f3.getString(str3)));
                                sb.append("\"");
                                z2 = false;
                            }
                            sb.append("}");
                            break;
                        case 11:
                            Parcel l2 = SafeParcelReader.l(parcel, y);
                            l2.setDataPosition(0);
                            m(sb, field.Y(), l2);
                            break;
                        default:
                            throw new IllegalStateException("Unknown field type out");
                    }
                }
                z = true;
            }
        }
        if (parcel.dataPosition() == F) {
            sb.append('}');
            return;
        }
        throw new SafeParcelReader.ParseException("Overread allowed size end=" + F, parcel);
    }

    private static final void n(StringBuilder sb, int i2, Object obj) {
        switch (i2) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                sb.append(obj);
                return;
            case 7:
                sb.append("\"");
                sb.append(JsonUtils.a(Preconditions.i(obj).toString()));
                sb.append("\"");
                return;
            case 8:
                sb.append("\"");
                sb.append(Base64Utils.a((byte[]) obj));
                sb.append("\"");
                return;
            case 9:
                sb.append("\"");
                sb.append(Base64Utils.b((byte[]) obj));
                sb.append("\"");
                return;
            case 10:
                MapUtils.a(sb, (HashMap) Preconditions.i(obj));
                return;
            case 11:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                throw new IllegalArgumentException("Unknown type = " + i2);
        }
    }

    private static final void o(StringBuilder sb, FastJsonResponse.Field field, Object obj) {
        if (!field.f11197i) {
            n(sb, field.f11196h, obj);
            return;
        }
        ArrayList arrayList = (ArrayList) obj;
        sb.append("[");
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (i2 != 0) {
                sb.append(",");
            }
            n(sb, field.f11196h, arrayList.get(i2));
        }
        sb.append("]");
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final Map a() {
        zan zanVar = this.f11223j;
        if (zanVar == null) {
            return null;
        }
        return zanVar.P((String) Preconditions.i(this.f11224k));
    }

    @Override // com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse, com.google.android.gms.common.server.response.FastJsonResponse
    public final Object d(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    @Override // com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse, com.google.android.gms.common.server.response.FastJsonResponse
    public final boolean g(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    public final Parcel l() {
        int i2 = this.f11225l;
        if (i2 == 0) {
            int a2 = SafeParcelWriter.a(this.f11221h);
            this.f11226m = a2;
            SafeParcelWriter.b(this.f11221h, a2);
            this.f11225l = 2;
        } else if (i2 == 1) {
            SafeParcelWriter.b(this.f11221h, this.f11226m);
            this.f11225l = 2;
        }
        return this.f11221h;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final String toString() {
        Preconditions.j(this.f11223j, "Cannot convert to JSON on client side.");
        Parcel l2 = l();
        l2.setDataPosition(0);
        StringBuilder sb = new StringBuilder(100);
        m(sb, (Map) Preconditions.i(this.f11223j.P((String) Preconditions.i(this.f11224k))), l2);
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int i3 = this.f11220c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, i3);
        SafeParcelWriter.k(parcel, 2, l(), false);
        int i4 = this.f11222i;
        SafeParcelWriter.l(parcel, 3, i4 != 0 ? i4 != 1 ? this.f11223j : this.f11223j : null, i2, false);
        SafeParcelWriter.b(parcel, a2);
    }
}
