package androidx.media;

import android.media.session.MediaSessionManager;
import android.util.Log;
import androidx.media.MediaSessionManagerImplApi28;

/* loaded from: classes.dex */
public final class MediaSessionManager {

    /* renamed from: a, reason: collision with root package name */
    static final boolean f4619a = Log.isLoggable("MediaSessionManager", 3);

    /* renamed from: b, reason: collision with root package name */
    private static final Object f4620b = new Object();

    interface MediaSessionManagerImpl {
    }

    interface RemoteUserInfoImpl {
    }

    public static final class RemoteUserInfo {

        /* renamed from: a, reason: collision with root package name */
        RemoteUserInfoImpl f4621a;

        public RemoteUserInfo(String str, int i2, int i3) {
            this.f4621a = new MediaSessionManagerImplApi28.RemoteUserInfoImplApi28(str, i2, i3);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof RemoteUserInfo) {
                return this.f4621a.equals(((RemoteUserInfo) obj).f4621a);
            }
            return false;
        }

        public int hashCode() {
            return this.f4621a.hashCode();
        }

        public RemoteUserInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            this.f4621a = new MediaSessionManagerImplApi28.RemoteUserInfoImplApi28(remoteUserInfo);
        }
    }
}
