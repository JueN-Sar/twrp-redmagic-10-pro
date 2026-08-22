package androidx.startup;

/* loaded from: classes.dex */
public final class StartupException extends RuntimeException {
    public StartupException(String str) {
        super(str);
    }

    public StartupException(String str, Throwable th) {
        super(str, th);
    }

    public StartupException(Throwable th) {
        super(th);
    }
}
