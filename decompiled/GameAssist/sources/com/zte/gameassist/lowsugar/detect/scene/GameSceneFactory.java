package com.zte.gameassist.lowsugar.detect.scene;

import android.content.Context;
import android.text.TextUtils;
import com.zte.gameassist.lowsugar.detect.scene.Genshin.GenshinScene;
import com.zte.gameassist.lowsugar.detect.scene.PubgGlobal.PubgGlobalScene;
import com.zte.gameassist.lowsugar.detect.scene.SGame.SGameScene;
import com.zte.gameassist.lowsugar.detect.scene.SGameGlobal.SGameGlobalScene;
import com.zte.gameassist.lowsugar.detect.scene.Wildrift.WildriftScene;
import com.zte.gameassist.lowsugar.utils.LowSugarUtils;
import com.zte.gameassist.utils.GaLog;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class GameSceneFactory {

    /* renamed from: a, reason: collision with root package name */
    private static final Map f16851a = new HashMap();

    public static GameBaseScene a(Context context, String str) {
        GaLog.a("GameSceneFactory", "GameBaseScene " + str);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Map map = f16851a;
        GameBaseScene gameBaseScene = (GameBaseScene) map.get(str);
        if (gameBaseScene != null) {
            return gameBaseScene;
        }
        if (LowSugarUtils.t.contains(str)) {
            GenshinScene genshinScene = new GenshinScene(context, str);
            map.put(str, genshinScene);
            return genshinScene;
        }
        if (LowSugarUtils.f17022q.contains(str)) {
            SGameScene sGameScene = new SGameScene(context, str);
            map.put(str, sGameScene);
            return sGameScene;
        }
        if (LowSugarUtils.u.contains(str)) {
            WildriftScene wildriftScene = new WildriftScene(context, str);
            map.put(str, wildriftScene);
            return wildriftScene;
        }
        if (LowSugarUtils.f17023r.contains(str)) {
            SGameGlobalScene sGameGlobalScene = new SGameGlobalScene(context, str);
            map.put(str, sGameGlobalScene);
            return sGameGlobalScene;
        }
        if (!LowSugarUtils.f17024s.contains(str)) {
            return null;
        }
        PubgGlobalScene pubgGlobalScene = new PubgGlobalScene(context, str);
        map.put(str, pubgGlobalScene);
        return pubgGlobalScene;
    }
}
