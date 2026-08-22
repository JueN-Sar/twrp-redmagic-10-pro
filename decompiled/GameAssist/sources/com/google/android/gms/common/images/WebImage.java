package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Locale;

@SafeParcelable.Class
/* loaded from: classes.dex */
public final class WebImage extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<WebImage> CREATOR = new zah();

    /* renamed from: c, reason: collision with root package name */
    final int f10931c;

    /* renamed from: h, reason: collision with root package name */
    private final Uri f10932h;

    /* renamed from: i, reason: collision with root package name */
    private final int f10933i;

    /* renamed from: j, reason: collision with root package name */
    private final int f10934j;

    WebImage(int i2, Uri uri, int i3, int i4) {
        this.f10931c = i2;
        this.f10932h = uri;
        this.f10933i = i3;
        this.f10934j = i4;
    }

    public int G() {
        return this.f10934j;
    }

    public Uri P() {
        return this.f10932h;
    }

    public int R() {
        return this.f10933i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof WebImage)) {
            WebImage webImage = (WebImage) obj;
            if (Objects.a(this.f10932h, webImage.f10932h) && this.f10933i == webImage.f10933i && this.f10934j == webImage.f10934j) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return Objects.b(this.f10932h, Integer.valueOf(this.f10933i), Integer.valueOf(this.f10934j));
    }

    public String toString() {
        return String.format(Locale.US, "Image %dx%d %s", Integer.valueOf(this.f10933i), Integer.valueOf(this.f10934j), this.f10932h.toString());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int i3 = this.f10931c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, i3);
        SafeParcelWriter.l(parcel, 2, P(), i2, false);
        SafeParcelWriter.g(parcel, 3, R());
        SafeParcelWriter.g(parcel, 4, G());
        SafeParcelWriter.b(parcel, a2);
    }
}
