package okio;

import java.security.MessageDigest;
import javax.crypto.Mac;

/* loaded from: classes2.dex */
public final class HashingSource extends ForwardingSource {

    /* renamed from: h, reason: collision with root package name */
    private final MessageDigest f19599h;

    /* renamed from: i, reason: collision with root package name */
    private final Mac f19600i;

    @Override // okio.ForwardingSource, okio.Source
    public long d0(Buffer buffer, long j2) {
        long d0 = super.d0(buffer, j2);
        if (d0 != -1) {
            long j3 = buffer.f19572h;
            long j4 = j3 - d0;
            Segment segment = buffer.f19571c;
            while (j3 > j4) {
                segment = segment.f19646g;
                j3 -= segment.f19642c - segment.f19641b;
            }
            while (j3 < buffer.f19572h) {
                int i2 = (int) ((segment.f19641b + j4) - j3);
                MessageDigest messageDigest = this.f19599h;
                if (messageDigest != null) {
                    messageDigest.update(segment.f19640a, i2, segment.f19642c - i2);
                } else {
                    this.f19600i.update(segment.f19640a, i2, segment.f19642c - i2);
                }
                j4 = (segment.f19642c - segment.f19641b) + j3;
                segment = segment.f19645f;
                j3 = j4;
            }
        }
        return d0;
    }
}
