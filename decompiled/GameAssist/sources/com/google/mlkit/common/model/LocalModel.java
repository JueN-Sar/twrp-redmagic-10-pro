package com.google.mlkit.common.model;

import android.net.Uri;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzq;
import com.google.android.gms.internal.mlkit_common.zzr;

/* loaded from: classes.dex */
public class LocalModel {

    /* renamed from: a, reason: collision with root package name */
    private final String f15910a;

    /* renamed from: b, reason: collision with root package name */
    private final String f15911b;

    /* renamed from: c, reason: collision with root package name */
    private final Uri f15912c;

    /* renamed from: d, reason: collision with root package name */
    private final boolean f15913d;

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private String f15914a = null;

        /* renamed from: b, reason: collision with root package name */
        private String f15915b = null;

        /* renamed from: c, reason: collision with root package name */
        private Uri f15916c = null;

        /* renamed from: d, reason: collision with root package name */
        private boolean f15917d = false;

        public LocalModel a() {
            String str = this.f15914a;
            boolean z = true;
            if ((str == null || this.f15915b != null || this.f15916c != null) && ((str != null || this.f15915b == null || this.f15916c != null) && (str != null || this.f15915b != null || this.f15916c == null))) {
                z = false;
            }
            Preconditions.b(z, "Set one of filePath, assetFilePath and URI.");
            return new LocalModel(this.f15914a, this.f15915b, this.f15916c, this.f15917d, null);
        }

        public Builder b(String str) {
            Preconditions.g(str, "Model Source file path can not be empty");
            boolean z = false;
            if (this.f15915b == null && this.f15916c == null && !this.f15917d) {
                z = true;
            }
            Preconditions.b(z, "A local model source is from absolute file path, asset file path or URI, you can only set one of them.");
            this.f15914a = str;
            return this;
        }

        public Builder c(String str) {
            Preconditions.g(str, "Manifest file path can not be empty");
            boolean z = false;
            if (this.f15915b == null && this.f15916c == null && (this.f15914a == null || this.f15917d)) {
                z = true;
            }
            Preconditions.b(z, "A local model source is from absolute file path, asset file path or URI, you can only set one of them.");
            this.f15914a = str;
            this.f15917d = true;
            return this;
        }
    }

    /* synthetic */ LocalModel(String str, String str2, Uri uri, boolean z, zzc zzcVar) {
        this.f15910a = str;
        this.f15911b = str2;
        this.f15912c = uri;
        this.f15913d = z;
    }

    public String a() {
        return this.f15910a;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LocalModel)) {
            return false;
        }
        LocalModel localModel = (LocalModel) obj;
        return Objects.a(this.f15910a, localModel.f15910a) && Objects.a(this.f15911b, localModel.f15911b) && Objects.a(this.f15912c, localModel.f15912c) && this.f15913d == localModel.f15913d;
    }

    public int hashCode() {
        return Objects.b(this.f15910a, this.f15911b, this.f15912c, Boolean.valueOf(this.f15913d));
    }

    public String toString() {
        zzq a2 = zzr.a(this);
        a2.a("absoluteFilePath", this.f15910a);
        a2.a("assetFilePath", this.f15911b);
        a2.a("uri", this.f15912c);
        a2.b("isManifestFile", this.f15913d);
        return a2.toString();
    }
}
