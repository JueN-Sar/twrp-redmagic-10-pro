package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@ShowFirstParty
@SafeParcelable.Class
/* loaded from: classes.dex */
public final class zan extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zan> CREATOR = new zao();

    /* renamed from: c, reason: collision with root package name */
    final int f11233c;

    /* renamed from: h, reason: collision with root package name */
    private final HashMap f11234h;

    /* renamed from: i, reason: collision with root package name */
    private final String f11235i;

    zan(int i2, ArrayList arrayList, String str) {
        this.f11233c = i2;
        HashMap hashMap = new HashMap();
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            zal zalVar = (zal) arrayList.get(i3);
            String str2 = zalVar.f11228h;
            HashMap hashMap2 = new HashMap();
            int size2 = ((ArrayList) Preconditions.i(zalVar.f11229i)).size();
            for (int i4 = 0; i4 < size2; i4++) {
                zam zamVar = (zam) zalVar.f11229i.get(i4);
                hashMap2.put(zamVar.f11231h, zamVar.f11232i);
            }
            hashMap.put(str2, hashMap2);
        }
        this.f11234h = hashMap;
        this.f11235i = (String) Preconditions.i(str);
        R();
    }

    public final String G() {
        return this.f11235i;
    }

    public final Map P(String str) {
        return (Map) this.f11234h.get(str);
    }

    public final void R() {
        Iterator it = this.f11234h.keySet().iterator();
        while (it.hasNext()) {
            Map map = (Map) this.f11234h.get((String) it.next());
            Iterator it2 = map.keySet().iterator();
            while (it2.hasNext()) {
                ((FastJsonResponse.Field) map.get((String) it2.next())).a0(this);
            }
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        for (String str : this.f11234h.keySet()) {
            sb.append(str);
            sb.append(":\n");
            Map map = (Map) this.f11234h.get(str);
            for (String str2 : map.keySet()) {
                sb.append("  ");
                sb.append(str2);
                sb.append(": ");
                sb.append(map.get(str2));
            }
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, this.f11233c);
        ArrayList arrayList = new ArrayList();
        for (String str : this.f11234h.keySet()) {
            arrayList.add(new zal(str, (Map) this.f11234h.get(str)));
        }
        SafeParcelWriter.q(parcel, 2, arrayList, false);
        SafeParcelWriter.m(parcel, 3, this.f11235i, false);
        SafeParcelWriter.b(parcel, a2);
    }
}
