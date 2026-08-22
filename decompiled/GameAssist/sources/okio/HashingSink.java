package okio;

import java.security.MessageDigest;
import javax.crypto.Mac;

/* loaded from: classes2.dex */
public final class HashingSink extends ForwardingSink {

    /* renamed from: h, reason: collision with root package name */
    private final MessageDigest f19597h;

    /* renamed from: i, reason: collision with root package name */
    private final Mac f19598i;

    @Override // okio.ForwardingSink, okio.Sink
    public void w(Buffer buffer, long j2) {
        Util.b(buffer.f19572h, 0L, j2);
        Segment segment = buffer.f19571c;
        long j3 = 0;
        while (j3 < j2) {
            int min = (int) Math.min(j2 - j3, segment.f19642c - segment.f19641b);
            MessageDigest messageDigest = this.f19597h;
            if (messageDigest != null) {
                messageDigest.update(segment.f19640a, segment.f19641b, min);
            } else {
                this.f19598i.update(segment.f19640a, segment.f19641b, min);
            }
            j3 += min;
            segment = segment.f19645f;
        }
        super.w(buffer, j2);
    }
}
