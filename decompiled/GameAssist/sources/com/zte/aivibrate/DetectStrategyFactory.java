package com.zte.aivibrate;

import android.content.Context;
import android.os.Looper;
import cn.nubia.gamelab.IToyService;
import cn.nubia.yolox.SkillReleaseModel;
import com.zte.aivibrate.scene.BaseScene;
import com.zte.aivibrate.scene.GameLabBaseScene;
import com.zte.aivibrate.scene.I4DVibrateScene;
import com.zte.aivibrate.scene.SkillScene;
import com.zte.aivibrate.scene.legends.LegendsDetectStrategy;
import com.zte.aivibrate.scene.legends.LegendsGameLabScene;
import com.zte.aivibrate.scene.lol.LolDetectStrategy;
import com.zte.aivibrate.scene.lol.LolGameLabScene;
import com.zte.aivibrate.scene.sgame.SGameDetectStrategy;
import com.zte.aivibrate.scene.sgame.SGameGameLabScene;
import com.zte.aivibrate.scene.yuanshen.YuanShenDetectStrategy;
import com.zte.aivibrate.scene.yuanshen.YuanShenGameLabScene;
import com.zte.aivibrate.scene.yuanshen.YuanShenSkillScene;
import com.zte.aivibrate.util.AIVibrateLog;

/* loaded from: classes.dex */
public class DetectStrategyFactory {
    public static GameLabBaseScene a(Context context, IToyService iToyService, I4DVibrateScene i4DVibrateScene, String str, Looper looper) {
        AIVibrateLog.a("game lab scene " + str);
        str.hashCode();
        switch (str) {
            case "com.tencent.tmgp.sgame":
                return new SGameGameLabScene(iToyService, i4DVibrateScene);
            case "com.dfjz.moba":
            case "com.mobile.legends.usa":
            case "com.mobile.legends":
                return new LegendsGameLabScene(iToyService, i4DVibrateScene, str);
            case "com.miHoYo.Yuanshen":
                return new YuanShenGameLabScene(iToyService, i4DVibrateScene);
            case "com.tencent.lolm":
                return new LolGameLabScene(iToyService, i4DVibrateScene);
            default:
                return null;
        }
    }

    public static BaseScene b(Context context, IYoloDataProvider iYoloDataProvider, SkillReleaseModel skillReleaseModel, IDetectStrategy iDetectStrategy, String str, Looper looper) {
        str.hashCode();
        switch (str) {
            case "com.tencent.tmgp.sgame":
            case "com.dfjz.moba":
            case "com.mobile.legends.usa":
            case "com.mobile.legends":
            case "com.tencent.lolm":
                return new SkillScene(context, iYoloDataProvider, skillReleaseModel, iDetectStrategy, str, looper);
            case "com.miHoYo.Yuanshen":
                return new YuanShenSkillScene(context, iYoloDataProvider, skillReleaseModel, iDetectStrategy, looper);
            default:
                return null;
        }
    }

    public static IDetectStrategy c(Context context, String str) {
        AIVibrateLog.a("strategy " + str);
        str.hashCode();
        switch (str) {
            case "com.tencent.tmgp.sgame":
                return new SGameDetectStrategy(context);
            case "com.dfjz.moba":
            case "com.mobile.legends.usa":
            case "com.mobile.legends":
                return new LegendsDetectStrategy(context);
            case "com.miHoYo.Yuanshen":
                return new YuanShenDetectStrategy(context);
            case "com.tencent.lolm":
                return new LolDetectStrategy(context);
            default:
                return null;
        }
    }

    public static boolean d(String str) {
        str.hashCode();
        switch (str) {
            case "com.tencent.tmgp.sgame":
            case "com.dfjz.moba":
            case "com.mobile.legends.usa":
            case "com.mobile.legends":
            case "com.miHoYo.Yuanshen":
            case "com.tencent.lolm":
                return true;
            default:
                return false;
        }
    }
}
