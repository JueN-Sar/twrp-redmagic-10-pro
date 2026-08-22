package androidx.profileinstaller;

import androidx.annotation.RestrictTo;
import java.util.Arrays;

@RestrictTo
/* loaded from: classes.dex */
public class ProfileVersion {

    /* renamed from: a, reason: collision with root package name */
    static final byte[] f4838a = {48, 49, 53, 0};

    /* renamed from: b, reason: collision with root package name */
    static final byte[] f4839b = {48, 49, 48, 0};

    /* renamed from: c, reason: collision with root package name */
    static final byte[] f4840c = {48, 48, 57, 0};

    /* renamed from: d, reason: collision with root package name */
    static final byte[] f4841d = {48, 48, 53, 0};

    /* renamed from: e, reason: collision with root package name */
    static final byte[] f4842e = {48, 48, 49, 0};

    /* renamed from: f, reason: collision with root package name */
    static final byte[] f4843f = {48, 48, 49, 0};

    /* renamed from: g, reason: collision with root package name */
    static final byte[] f4844g = {48, 48, 50, 0};

    static String a(byte[] bArr) {
        return (Arrays.equals(bArr, f4842e) || Arrays.equals(bArr, f4841d)) ? ":" : "!";
    }
}
