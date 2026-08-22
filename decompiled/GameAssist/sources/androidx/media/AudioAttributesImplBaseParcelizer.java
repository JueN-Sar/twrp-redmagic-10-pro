package androidx.media;

import androidx.annotation.RestrictTo;
import androidx.versionedparcelable.VersionedParcel;

@RestrictTo
/* loaded from: classes.dex */
public final class AudioAttributesImplBaseParcelizer {
    public static AudioAttributesImplBase read(VersionedParcel versionedParcel) {
        AudioAttributesImplBase audioAttributesImplBase = new AudioAttributesImplBase();
        audioAttributesImplBase.f4499a = versionedParcel.p(audioAttributesImplBase.f4499a, 1);
        audioAttributesImplBase.f4500b = versionedParcel.p(audioAttributesImplBase.f4500b, 2);
        audioAttributesImplBase.f4501c = versionedParcel.p(audioAttributesImplBase.f4501c, 3);
        audioAttributesImplBase.f4502d = versionedParcel.p(audioAttributesImplBase.f4502d, 4);
        return audioAttributesImplBase;
    }

    public static void write(AudioAttributesImplBase audioAttributesImplBase, VersionedParcel versionedParcel) {
        versionedParcel.x(false, false);
        versionedParcel.F(audioAttributesImplBase.f4499a, 1);
        versionedParcel.F(audioAttributesImplBase.f4500b, 2);
        versionedParcel.F(audioAttributesImplBase.f4501c, 3);
        versionedParcel.F(audioAttributesImplBase.f4502d, 4);
    }
}
