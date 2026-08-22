package androidx.media;

import android.media.VolumeProvider;
import androidx.annotation.RequiresApi;

@RequiresApi
/* loaded from: classes.dex */
class VolumeProviderCompatApi21 {

    public interface Delegate {
        void a(int i2);

        void b(int i2);
    }

    public static Object a(int i2, int i3, int i4, final Delegate delegate) {
        return new VolumeProvider(i2, i3, i4) { // from class: androidx.media.VolumeProviderCompatApi21.1
            @Override // android.media.VolumeProvider
            public void onAdjustVolume(int i5) {
                delegate.b(i5);
            }

            @Override // android.media.VolumeProvider
            public void onSetVolumeTo(int i5) {
                delegate.a(i5);
            }
        };
    }
}
