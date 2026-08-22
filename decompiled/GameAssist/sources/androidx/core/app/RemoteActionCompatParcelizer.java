package androidx.core.app;

import android.app.PendingIntent;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.drawable.IconCompat;
import androidx.versionedparcelable.VersionedParcel;

@RestrictTo
/* loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(VersionedParcel versionedParcel) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        remoteActionCompat.f2822a = (IconCompat) versionedParcel.v(remoteActionCompat.f2822a, 1);
        remoteActionCompat.f2823b = versionedParcel.l(remoteActionCompat.f2823b, 2);
        remoteActionCompat.f2824c = versionedParcel.l(remoteActionCompat.f2824c, 3);
        remoteActionCompat.f2825d = (PendingIntent) versionedParcel.r(remoteActionCompat.f2825d, 4);
        remoteActionCompat.f2826e = versionedParcel.h(remoteActionCompat.f2826e, 5);
        remoteActionCompat.f2827f = versionedParcel.h(remoteActionCompat.f2827f, 6);
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, VersionedParcel versionedParcel) {
        versionedParcel.x(false, false);
        versionedParcel.M(remoteActionCompat.f2822a, 1);
        versionedParcel.D(remoteActionCompat.f2823b, 2);
        versionedParcel.D(remoteActionCompat.f2824c, 3);
        versionedParcel.H(remoteActionCompat.f2825d, 4);
        versionedParcel.z(remoteActionCompat.f2826e, 5);
        versionedParcel.z(remoteActionCompat.f2827f, 6);
    }
}
