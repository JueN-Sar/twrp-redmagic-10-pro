package cn.nubia.gameassist.dessert.policy.performancemonitor.present;

import cn.nubia.gameassist.dessert.policy.performancemonitor.model.GameAppInfo;
import cn.nubia.gameassist.dessert.policy.performancemonitor.model.GameDurationInfo;
import java.util.List;

/* loaded from: classes.dex */
public interface PresenterCallback {
    void onFaied();

    void onLoaded(GameDurationInfo[] gameDurationInfoArr, List<List<GameAppInfo>> list);
}
