package androidx.core.hardware.fingerprint;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.os.Handler;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import androidx.annotation.RestrictTo;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.Mac;

@RestrictTo
@Deprecated
/* loaded from: classes.dex */
public class FingerprintManagerCompat {

    /* renamed from: androidx.core.hardware.fingerprint.FingerprintManagerCompat$1, reason: invalid class name */
    class AnonymousClass1 extends FingerprintManager.AuthenticationCallback {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ AuthenticationCallback f3007a;

        @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
        public void onAuthenticationError(int i2, CharSequence charSequence) {
            this.f3007a.a(i2, charSequence);
        }

        @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
        public void onAuthenticationFailed() {
            this.f3007a.b();
        }

        @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
        public void onAuthenticationHelp(int i2, CharSequence charSequence) {
            this.f3007a.c(i2, charSequence);
        }

        @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult authenticationResult) {
            this.f3007a.d(new AuthenticationResult(FingerprintManagerCompat.a(Api23Impl.b(authenticationResult))));
        }
    }

    @RequiresApi
    static class Api23Impl {
        @RequiresPermission
        @DoNotInline
        static void a(Object obj, Object obj2, CancellationSignal cancellationSignal, int i2, Object obj3, Handler handler) {
            ((FingerprintManager) obj).authenticate((FingerprintManager.CryptoObject) obj2, cancellationSignal, i2, (FingerprintManager.AuthenticationCallback) obj3, handler);
        }

        @DoNotInline
        static FingerprintManager.CryptoObject b(Object obj) {
            return ((FingerprintManager.AuthenticationResult) obj).getCryptoObject();
        }

        @DoNotInline
        public static FingerprintManager c(Context context) {
            if (context.getPackageManager().hasSystemFeature("android.hardware.fingerprint")) {
                return (FingerprintManager) context.getSystemService(FingerprintManager.class);
            }
            return null;
        }

        @RequiresPermission
        @DoNotInline
        static boolean d(Object obj) {
            return ((FingerprintManager) obj).hasEnrolledFingerprints();
        }

        @RequiresPermission
        @DoNotInline
        static boolean e(Object obj) {
            return ((FingerprintManager) obj).isHardwareDetected();
        }

        @DoNotInline
        public static CryptoObject f(Object obj) {
            FingerprintManager.CryptoObject cryptoObject = (FingerprintManager.CryptoObject) obj;
            if (cryptoObject == null) {
                return null;
            }
            if (cryptoObject.getCipher() != null) {
                return new CryptoObject(cryptoObject.getCipher());
            }
            if (cryptoObject.getSignature() != null) {
                return new CryptoObject(cryptoObject.getSignature());
            }
            if (cryptoObject.getMac() != null) {
                return new CryptoObject(cryptoObject.getMac());
            }
            return null;
        }

        @DoNotInline
        public static FingerprintManager.CryptoObject g(CryptoObject cryptoObject) {
            if (cryptoObject == null) {
                return null;
            }
            if (cryptoObject.a() != null) {
                return new FingerprintManager.CryptoObject(cryptoObject.a());
            }
            if (cryptoObject.c() != null) {
                return new FingerprintManager.CryptoObject(cryptoObject.c());
            }
            if (cryptoObject.b() != null) {
                return new FingerprintManager.CryptoObject(cryptoObject.b());
            }
            return null;
        }
    }

    public static abstract class AuthenticationCallback {
        public void a(int i2, CharSequence charSequence) {
        }

        public void b() {
        }

        public void c(int i2, CharSequence charSequence) {
        }

        public void d(AuthenticationResult authenticationResult) {
        }
    }

    public static final class AuthenticationResult {

        /* renamed from: a, reason: collision with root package name */
        private final CryptoObject f3008a;

        public AuthenticationResult(CryptoObject cryptoObject) {
            this.f3008a = cryptoObject;
        }
    }

    static CryptoObject a(FingerprintManager.CryptoObject cryptoObject) {
        return Api23Impl.f(cryptoObject);
    }

    public static class CryptoObject {

        /* renamed from: a, reason: collision with root package name */
        private final Signature f3009a;

        /* renamed from: b, reason: collision with root package name */
        private final Cipher f3010b;

        /* renamed from: c, reason: collision with root package name */
        private final Mac f3011c;

        public CryptoObject(Signature signature) {
            this.f3009a = signature;
            this.f3010b = null;
            this.f3011c = null;
        }

        public Cipher a() {
            return this.f3010b;
        }

        public Mac b() {
            return this.f3011c;
        }

        public Signature c() {
            return this.f3009a;
        }

        public CryptoObject(Cipher cipher) {
            this.f3010b = cipher;
            this.f3009a = null;
            this.f3011c = null;
        }

        public CryptoObject(Mac mac) {
            this.f3011c = mac;
            this.f3010b = null;
            this.f3009a = null;
        }
    }
}
