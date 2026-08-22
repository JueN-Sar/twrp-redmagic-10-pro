package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class EncodedPayload {

    /* renamed from: a, reason: collision with root package name */
    private final Encoding f10220a;

    /* renamed from: b, reason: collision with root package name */
    private final byte[] f10221b;

    public EncodedPayload(Encoding encoding, byte[] bArr) {
        if (encoding == null) {
            throw new NullPointerException("encoding is null");
        }
        if (bArr == null) {
            throw new NullPointerException("bytes is null");
        }
        this.f10220a = encoding;
        this.f10221b = bArr;
    }

    public byte[] a() {
        return this.f10221b;
    }

    public Encoding b() {
        return this.f10220a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EncodedPayload)) {
            return false;
        }
        EncodedPayload encodedPayload = (EncodedPayload) obj;
        if (this.f10220a.equals(encodedPayload.f10220a)) {
            return Arrays.equals(this.f10221b, encodedPayload.f10221b);
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(this.f10221b) ^ ((this.f10220a.hashCode() ^ 1000003) * 1000003);
    }

    public String toString() {
        return "EncodedPayload{encoding=" + this.f10220a + ", bytes=[...]}";
    }
}
