package androidx.media;

import android.text.TextUtils;
import androidx.core.util.ObjectsCompat;
import androidx.media.MediaSessionManager;

/* loaded from: classes.dex */
class MediaSessionManagerImplBase implements MediaSessionManager.MediaSessionManagerImpl {

    /* renamed from: a, reason: collision with root package name */
    private static final boolean f4623a = MediaSessionManager.f4619a;

    static class RemoteUserInfoImplBase implements MediaSessionManager.RemoteUserInfoImpl {

        /* renamed from: a, reason: collision with root package name */
        private String f4624a;

        /* renamed from: b, reason: collision with root package name */
        private int f4625b;

        /* renamed from: c, reason: collision with root package name */
        private int f4626c;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof RemoteUserInfoImplBase)) {
                return false;
            }
            RemoteUserInfoImplBase remoteUserInfoImplBase = (RemoteUserInfoImplBase) obj;
            return TextUtils.equals(this.f4624a, remoteUserInfoImplBase.f4624a) && this.f4625b == remoteUserInfoImplBase.f4625b && this.f4626c == remoteUserInfoImplBase.f4626c;
        }

        public int hashCode() {
            return ObjectsCompat.b(this.f4624a, Integer.valueOf(this.f4625b), Integer.valueOf(this.f4626c));
        }
    }
}
