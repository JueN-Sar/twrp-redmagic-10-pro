package androidx.profileinstaller;

import android.content.res.AssetManager;
import android.os.Build;
import androidx.annotation.RestrictTo;
import androidx.profileinstaller.ProfileInstaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.Executor;

@RestrictTo
/* loaded from: classes.dex */
public class DeviceProfileWriter {

    /* renamed from: a, reason: collision with root package name */
    private final AssetManager f4802a;

    /* renamed from: b, reason: collision with root package name */
    private final Executor f4803b;

    /* renamed from: c, reason: collision with root package name */
    private final ProfileInstaller.DiagnosticsCallback f4804c;

    /* renamed from: e, reason: collision with root package name */
    private final File f4806e;

    /* renamed from: f, reason: collision with root package name */
    private final String f4807f;

    /* renamed from: g, reason: collision with root package name */
    private final String f4808g;

    /* renamed from: h, reason: collision with root package name */
    private final String f4809h;

    /* renamed from: j, reason: collision with root package name */
    private DexProfileData[] f4811j;

    /* renamed from: k, reason: collision with root package name */
    private byte[] f4812k;

    /* renamed from: i, reason: collision with root package name */
    private boolean f4810i = false;

    /* renamed from: d, reason: collision with root package name */
    private final byte[] f4805d = d();

    public DeviceProfileWriter(AssetManager assetManager, Executor executor, ProfileInstaller.DiagnosticsCallback diagnosticsCallback, String str, String str2, String str3, File file) {
        this.f4802a = assetManager;
        this.f4803b = executor;
        this.f4804c = diagnosticsCallback;
        this.f4807f = str;
        this.f4808g = str2;
        this.f4809h = str3;
        this.f4806e = file;
    }

    private DeviceProfileWriter b(DexProfileData[] dexProfileDataArr, byte[] bArr) {
        InputStream h2;
        try {
            h2 = h(this.f4802a, this.f4809h);
        } catch (FileNotFoundException e2) {
            this.f4804c.a(9, e2);
        } catch (IOException e3) {
            this.f4804c.a(7, e3);
        } catch (IllegalStateException e4) {
            this.f4811j = null;
            this.f4804c.a(8, e4);
        }
        if (h2 == null) {
            if (h2 != null) {
                h2.close();
            }
            return null;
        }
        try {
            this.f4811j = ProfileTranscoder.r(h2, ProfileTranscoder.p(h2, ProfileTranscoder.f4826b), bArr, dexProfileDataArr);
            h2.close();
            return this;
        } catch (Throwable th) {
            try {
                h2.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private void c() {
        if (!this.f4810i) {
            throw new IllegalStateException("This device doesn't support aot. Did you call deviceSupportsAotProfile()?");
        }
    }

    private static byte[] d() {
        return ProfileVersion.f4838a;
    }

    private InputStream f(AssetManager assetManager) {
        try {
            return h(assetManager, this.f4808g);
        } catch (FileNotFoundException e2) {
            this.f4804c.a(6, e2);
            return null;
        } catch (IOException e3) {
            this.f4804c.a(7, e3);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g(int i2, Object obj) {
        this.f4804c.a(i2, obj);
    }

    private InputStream h(AssetManager assetManager, String str) {
        try {
            return assetManager.openFd(str).createInputStream();
        } catch (FileNotFoundException e2) {
            String message = e2.getMessage();
            if (message != null && message.contains("compressed")) {
                this.f4804c.b(5, null);
            }
            return null;
        }
    }

    private DexProfileData[] j(InputStream inputStream) {
        try {
            try {
                try {
                    try {
                        DexProfileData[] x = ProfileTranscoder.x(inputStream, ProfileTranscoder.p(inputStream, ProfileTranscoder.f4825a), this.f4807f);
                        try {
                            inputStream.close();
                            return x;
                        } catch (IOException e2) {
                            this.f4804c.a(7, e2);
                            return x;
                        }
                    } catch (IOException e3) {
                        this.f4804c.a(7, e3);
                        return null;
                    }
                } catch (IllegalStateException e4) {
                    this.f4804c.a(8, e4);
                    inputStream.close();
                    return null;
                }
            } catch (IOException e5) {
                this.f4804c.a(7, e5);
                inputStream.close();
                return null;
            }
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (IOException e6) {
                this.f4804c.a(7, e6);
            }
            throw th;
        }
    }

    private static boolean k() {
        return true;
    }

    private void l(final int i2, final Object obj) {
        this.f4803b.execute(new Runnable() { // from class: androidx.profileinstaller.a
            @Override // java.lang.Runnable
            public final void run() {
                DeviceProfileWriter.this.g(i2, obj);
            }
        });
    }

    public boolean e() {
        if (this.f4805d == null) {
            l(3, Integer.valueOf(Build.VERSION.SDK_INT));
            return false;
        }
        if (!this.f4806e.exists()) {
            try {
                if (!this.f4806e.createNewFile()) {
                    l(4, null);
                    return false;
                }
            } catch (IOException unused) {
                l(4, null);
                return false;
            }
        } else if (!this.f4806e.canWrite()) {
            l(4, null);
            return false;
        }
        this.f4810i = true;
        return true;
    }

    public DeviceProfileWriter i() {
        DeviceProfileWriter b2;
        c();
        if (this.f4805d == null) {
            return this;
        }
        InputStream f2 = f(this.f4802a);
        if (f2 != null) {
            this.f4811j = j(f2);
        }
        DexProfileData[] dexProfileDataArr = this.f4811j;
        return (dexProfileDataArr == null || !k() || (b2 = b(dexProfileDataArr, this.f4805d)) == null) ? this : b2;
    }

    public DeviceProfileWriter m() {
        ByteArrayOutputStream byteArrayOutputStream;
        DexProfileData[] dexProfileDataArr = this.f4811j;
        byte[] bArr = this.f4805d;
        if (dexProfileDataArr != null && bArr != null) {
            c();
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    ProfileTranscoder.F(byteArrayOutputStream, bArr);
                } catch (Throwable th) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (IOException e2) {
                this.f4804c.a(7, e2);
            } catch (IllegalStateException e3) {
                this.f4804c.a(8, e3);
            }
            if (!ProfileTranscoder.C(byteArrayOutputStream, bArr, dexProfileDataArr)) {
                this.f4804c.a(5, null);
                this.f4811j = null;
                byteArrayOutputStream.close();
                return this;
            }
            this.f4812k = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            this.f4811j = null;
        }
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean n() {
        byte[] bArr = this.f4812k;
        if (bArr == null) {
            return false;
        }
        c();
        try {
            try {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(this.f4806e);
                    try {
                        FileChannel channel = fileOutputStream.getChannel();
                        try {
                            FileLock tryLock = channel.tryLock();
                            try {
                                Encoding.l(byteArrayInputStream, fileOutputStream, tryLock);
                                l(1, null);
                                if (tryLock != null) {
                                    tryLock.close();
                                }
                                channel.close();
                                fileOutputStream.close();
                                byteArrayInputStream.close();
                                return true;
                            } finally {
                            }
                        } finally {
                        }
                    } finally {
                    }
                } catch (Throwable th) {
                    try {
                        byteArrayInputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (FileNotFoundException e2) {
                l(6, e2);
                return false;
            } catch (IOException e3) {
                l(7, e3);
                return false;
            }
        } finally {
            this.f4812k = null;
            this.f4811j = null;
        }
    }
}
