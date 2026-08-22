package com.google.android.gms.common.moduleinstall.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.OptionalModuleApi;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.moduleinstall.ModuleInstallRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

@KeepForSdk
@SafeParcelable.Class
/* loaded from: classes.dex */
public class ApiFeatureRequest extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<ApiFeatureRequest> CREATOR = new zac();

    /* renamed from: k, reason: collision with root package name */
    private static final Comparator f11155k = new Comparator() { // from class: com.google.android.gms.common.moduleinstall.internal.zab
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            Feature feature = (Feature) obj;
            Feature feature2 = (Feature) obj2;
            Parcelable.Creator<ApiFeatureRequest> creator = ApiFeatureRequest.CREATOR;
            return !feature.G().equals(feature2.G()) ? feature.G().compareTo(feature2.G()) : (feature.P() > feature2.P() ? 1 : (feature.P() == feature2.P() ? 0 : -1));
        }
    };

    /* renamed from: c, reason: collision with root package name */
    private final List f11156c;

    /* renamed from: h, reason: collision with root package name */
    private final boolean f11157h;

    /* renamed from: i, reason: collision with root package name */
    private final String f11158i;

    /* renamed from: j, reason: collision with root package name */
    private final String f11159j;

    public ApiFeatureRequest(List list, boolean z, String str, String str2) {
        Preconditions.i(list);
        this.f11156c = list;
        this.f11157h = z;
        this.f11158i = str;
        this.f11159j = str2;
    }

    public static ApiFeatureRequest G(ModuleInstallRequest moduleInstallRequest) {
        return R(moduleInstallRequest.a(), true);
    }

    static ApiFeatureRequest R(List list, boolean z) {
        TreeSet treeSet = new TreeSet(f11155k);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Collections.addAll(treeSet, ((OptionalModuleApi) it.next()).e());
        }
        return new ApiFeatureRequest(new ArrayList(treeSet), z, null, null);
    }

    public List P() {
        return this.f11156c;
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ApiFeatureRequest)) {
            return false;
        }
        ApiFeatureRequest apiFeatureRequest = (ApiFeatureRequest) obj;
        return this.f11157h == apiFeatureRequest.f11157h && Objects.a(this.f11156c, apiFeatureRequest.f11156c) && Objects.a(this.f11158i, apiFeatureRequest.f11158i) && Objects.a(this.f11159j, apiFeatureRequest.f11159j);
    }

    public final int hashCode() {
        return Objects.b(Boolean.valueOf(this.f11157h), this.f11156c, this.f11158i, this.f11159j);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.q(parcel, 1, P(), false);
        SafeParcelWriter.c(parcel, 2, this.f11157h);
        SafeParcelWriter.m(parcel, 3, this.f11158i, false);
        SafeParcelWriter.m(parcel, 4, this.f11159j, false);
        SafeParcelWriter.b(parcel, a2);
    }
}
