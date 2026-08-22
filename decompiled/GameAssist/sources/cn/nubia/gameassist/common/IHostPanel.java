package cn.nubia.gameassist.common;

/* loaded from: classes.dex */
public interface IHostPanel {

    public interface PanelCallback {
        default void a() {
        }

        default void b(boolean z) {
        }

        default void c() {
        }
    }

    void b(String str);
}
