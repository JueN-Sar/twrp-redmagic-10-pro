package cn.nubia.gameassist.meditationmode.danmu.view;

import cn.nubia.gameassist.meditationmode.danmu.DanmuNotificationBean;
import cn.nubia.gameassist.meditationmode.danmu.model.BarrageConfig;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public interface IBarrageView {
    void a();

    void b(PrintWriter printWriter);

    void c(int i2);

    void d();

    void e(DanmuNotificationBean danmuNotificationBean);

    default void f() {
    }

    BarrageConfig getBarrageConfig();
}
