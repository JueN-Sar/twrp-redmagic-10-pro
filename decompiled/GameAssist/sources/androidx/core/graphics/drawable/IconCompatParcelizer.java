package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.os.Parcelable;
import androidx.annotation.RestrictTo;
import androidx.versionedparcelable.VersionedParcel;

@RestrictTo
/* loaded from: classes.dex */
public class IconCompatParcelizer {
    public static IconCompat read(VersionedParcel versionedParcel) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.f2973a = versionedParcel.p(iconCompat.f2973a, 1);
        iconCompat.f2975c = versionedParcel.j(iconCompat.f2975c, 2);
        iconCompat.f2976d = versionedParcel.r(iconCompat.f2976d, 3);
        iconCompat.f2977e = versionedParcel.p(iconCompat.f2977e, 4);
        iconCompat.f2978f = versionedParcel.p(iconCompat.f2978f, 5);
        iconCompat.f2979g = (ColorStateList) versionedParcel.r(iconCompat.f2979g, 6);
        iconCompat.f2981i = versionedParcel.t(iconCompat.f2981i, 7);
        iconCompat.f2982j = versionedParcel.t(iconCompat.f2982j, 8);
        iconCompat.p();
        return iconCompat;
    }

    public static void write(IconCompat iconCompat, VersionedParcel versionedParcel) {
        versionedParcel.x(true, true);
        iconCompat.q(versionedParcel.f());
        int i2 = iconCompat.f2973a;
        if (-1 != i2) {
            versionedParcel.F(i2, 1);
        }
        byte[] bArr = iconCompat.f2975c;
        if (bArr != null) {
            versionedParcel.B(bArr, 2);
        }
        Parcelable parcelable = iconCompat.f2976d;
        if (parcelable != null) {
            versionedParcel.H(parcelable, 3);
        }
        int i3 = iconCompat.f2977e;
        if (i3 != 0) {
            versionedParcel.F(i3, 4);
        }
        int i4 = iconCompat.f2978f;
        if (i4 != 0) {
            versionedParcel.F(i4, 5);
        }
        ColorStateList colorStateList = iconCompat.f2979g;
        if (colorStateList != null) {
            versionedParcel.H(colorStateList, 6);
        }
        String str = iconCompat.f2981i;
        if (str != null) {
            versionedParcel.J(str, 7);
        }
        String str2 = iconCompat.f2982j;
        if (str2 != null) {
            versionedParcel.J(str2, 8);
        }
    }
}
