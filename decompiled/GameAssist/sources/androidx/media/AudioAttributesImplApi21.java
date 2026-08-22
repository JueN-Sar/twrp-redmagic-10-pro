package androidx.media;

import android.annotation.TargetApi;
import android.media.AudioAttributes;
import com.zte.distbus.basetransfer.Status;

@TargetApi(Status.ERROR_STREAM_REMOTE_FAILED)
/* loaded from: classes.dex */
class AudioAttributesImplApi21 implements AudioAttributesImpl {

    /* renamed from: a, reason: collision with root package name */
    AudioAttributes f4497a;

    /* renamed from: b, reason: collision with root package name */
    int f4498b = -1;

    AudioAttributesImplApi21() {
    }

    public boolean equals(Object obj) {
        if (obj instanceof AudioAttributesImplApi21) {
            return this.f4497a.equals(((AudioAttributesImplApi21) obj).f4497a);
        }
        return false;
    }

    public int hashCode() {
        return this.f4497a.hashCode();
    }

    public String toString() {
        return "AudioAttributesCompat: audioattributes=" + this.f4497a;
    }
}
