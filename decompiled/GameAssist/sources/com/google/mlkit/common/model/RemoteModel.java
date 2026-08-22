package com.google.mlkit.common.model;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.mlkit_common.zzq;
import com.google.android.gms.internal.mlkit_common.zzr;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.model.BaseModel;
import java.util.EnumMap;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class RemoteModel {

    /* renamed from: e, reason: collision with root package name */
    private static final Map f15918e = new EnumMap(BaseModel.class);

    @NonNull
    @VisibleForTesting
    public static final Map zza = new EnumMap(BaseModel.class);

    /* renamed from: a, reason: collision with root package name */
    private final String f15919a;

    /* renamed from: b, reason: collision with root package name */
    private final BaseModel f15920b;

    /* renamed from: c, reason: collision with root package name */
    private final ModelType f15921c;

    /* renamed from: d, reason: collision with root package name */
    private String f15922d;

    public String a() {
        return this.f15922d;
    }

    public String b() {
        return this.f15919a;
    }

    public String c() {
        String str = this.f15919a;
        if (str != null) {
            return str;
        }
        return (String) zza.get(this.f15920b);
    }

    public ModelType d() {
        return this.f15921c;
    }

    public String e() {
        String str = this.f15919a;
        if (str != null) {
            return str;
        }
        return "COM.GOOGLE.BASE_".concat(String.valueOf((String) zza.get(this.f15920b)));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RemoteModel)) {
            return false;
        }
        RemoteModel remoteModel = (RemoteModel) obj;
        return Objects.a(this.f15919a, remoteModel.f15919a) && Objects.a(this.f15920b, remoteModel.f15920b) && Objects.a(this.f15921c, remoteModel.f15921c);
    }

    public int hashCode() {
        return Objects.b(this.f15919a, this.f15920b, this.f15921c);
    }

    public String toString() {
        zzq b2 = zzr.b("RemoteModel");
        b2.a("modelName", this.f15919a);
        b2.a("baseModel", this.f15920b);
        b2.a("modelType", this.f15921c);
        return b2.toString();
    }
}
