package cn.nubia.screensaver.system;

/* loaded from: classes.dex */
public interface ISnapshotKeyguard {

    public interface Callback {
        void a(KeyguardShade keyguardShade);
    }

    void b(Callback callback);
}
