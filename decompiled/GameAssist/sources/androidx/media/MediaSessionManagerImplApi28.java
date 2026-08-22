package androidx.media;

import android.media.session.MediaSessionManager;
import androidx.annotation.RequiresApi;
import androidx.core.util.ObjectsCompat;
import androidx.media.MediaSessionManager;

@RequiresApi
/* loaded from: classes.dex */
class MediaSessionManagerImplApi28 extends MediaSessionManagerImplApi21 {

    static final class RemoteUserInfoImplApi28 implements MediaSessionManager.RemoteUserInfoImpl {

        /* renamed from: a, reason: collision with root package name */
        final MediaSessionManager.RemoteUserInfo f4622a;

        RemoteUserInfoImplApi28(String str, int i2, int i3) {
            this.f4622a = new MediaSessionManager.RemoteUserInfo(str, i2, i3);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof RemoteUserInfoImplApi28) {
                return this.f4622a.equals(((RemoteUserInfoImplApi28) obj).f4622a);
            }
            return false;
        }

        public int hashCode() {
            return ObjectsCompat.b(this.f4622a);
        }

        RemoteUserInfoImplApi28(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            this.f4622a = remoteUserInfo;
        }
    }
}
