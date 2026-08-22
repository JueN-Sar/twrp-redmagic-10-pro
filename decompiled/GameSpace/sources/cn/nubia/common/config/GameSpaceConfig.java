package cn.nubia.common.config;

import android.app.ActivityManager;
import android.util.Log;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.common.util.FeatureUtil;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

/* loaded from: classes.dex */
public class GameSpaceConfig {
    private static final String CONFIG_CUT = "0";
    private static final String CONFIG_NUBIA_ABROAD_DEFAULT = "0:1,1:1,2:0,3:0,4:0,5:0,6:1,7:1,8:1,9:1,a:1,b:1,c:1,d:0,e:0,f:0,g:1,h:0,i:1,j:0";
    private static final String CONFIG_NUBIA_CHANNA_DEFAULT = "0:1,1:1,2:1,3:1,4:1,5:1,6:1,7:1,8:1,9:1,a:1,b:1,c:1,d:1,e:0,f:1,g:1,h:0,i:1,j:0";
    private static final String CONFIG_ZTE_ABROAD_DEFAULT = "0:0,1:0,2:0,3:0,4:0,5:0,6:1,7:1,8:0,9:0,a:0,b:0,c:1,d:0,e:0,f:0,g:1,h:0,i:1,j:0";
    private static final String CONFIG_ZTE_CHANNA_DEFAULT = "0:0,1:0,2:0,3:0,4:0,5:0,6:1,7:1,8:0,9:0,a:0,b:0,c:1,d:1,e:0,f:1,g:1,h:0,i:1,j:0";
    public static final String SUPPORT_BASE = "c";
    public static final String SUPPORT_CUSTOMIZED_BGM = "j";
    public static final String SUPPORT_EXTERN_DEVICE = "3";
    public static final String SUPPORT_FAN = "b";
    public static final String SUPPORT_GAME_KEY = "0";
    public static final String SUPPORT_GAME_RECOMMEND = "4";
    public static final String SUPPORT_GIFT_BAG = "5";
    public static final String SUPPORT_HOST_MODE = "h";
    public static final String SUPPORT_IDENTIFY = "i";
    public static final String SUPPORT_INDICATOR = "f";
    public static final String SUPPORT_LIVE_ATMOSPHERE = "d";
    public static final String SUPPORT_MORA = "1";
    public static final String SUPPORT_NEWS = "e";
    public static final String SUPPORT_NOTES = "6";
    public static final String SUPPORT_PLAY_HANDLE = "9";
    public static final String SUPPORT_PLAY_MIRROR = "a";
    public static final String SUPPORT_PLAY_MOUSE = "8";
    public static final String SUPPORT_RED_TIME = "7";
    public static final String SUPPORT_SINGLE_RECOMMEND = "2";
    public static final String SUPPORT_WECHAT_SHORTCUT = "g";
    private static final String TAG = "GameSpaceConfig";
    private static final String ZTE_FEATURE_GAMESPACE_CONFIG = "ZTE_FEATURE_GAMESPACE_CONFIG";
    private static Map<String, String> mConfigMap = new ConcurrentHashMap();

    private static void appendDefault(String str) {
        String[] split = str.split(",");
        if (split == null) {
            return;
        }
        for (String str2 : split) {
            String[] split2 = str2.split(":");
            if (split2 != null && split2.length >= 2 && !mConfigMap.containsKey(split2[0])) {
                mConfigMap.put(split2[0], split2[1]);
            }
        }
        mConfigMap.forEach(new BiConsumer() { // from class: cn.nubia.common.config.GameSpaceConfig$$ExternalSyntheticLambda0
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                Log.d(GameSpaceConfig.TAG, "key : " + ((String) obj) + ", value : " + ((String) obj2));
            }
        });
    }

    private static void initConfigMap() {
        String str = CommonUtil.isNubia() ? CommonUtil.isInter() ? CONFIG_NUBIA_ABROAD_DEFAULT : CONFIG_NUBIA_CHANNA_DEFAULT : CommonUtil.isInter() ? CONFIG_ZTE_ABROAD_DEFAULT : CONFIG_ZTE_CHANNA_DEFAULT;
        String str2 = FeatureUtil.get(ZTE_FEATURE_GAMESPACE_CONFIG, str);
        Log.d(TAG, "initConfigMap config: " + str2 + "\n defaultConfig : " + str);
        mConfigMap.clear();
        String[] split = str2.split(",");
        if (split == null) {
            return;
        }
        for (String str3 : split) {
            String[] split2 = str3.split(":");
            if (split2 != null && split2.length >= 2) {
                mConfigMap.put(split2[0], split2[1]);
            }
        }
        appendDefault(str);
    }

    private static boolean isFeatureCut(String str) {
        Map<String, String> map = mConfigMap;
        if (map == null || map.isEmpty()) {
            initConfigMap();
        }
        if (mConfigMap.isEmpty() || !mConfigMap.containsKey(str)) {
            return true;
        }
        return "0".equals(mConfigMap.get(str));
    }

    public static boolean supportBase() {
        return !isFeatureCut(SUPPORT_BASE);
    }

    public static boolean supportCustomizedBgm() {
        return !isFeatureCut(SUPPORT_CUSTOMIZED_BGM);
    }

    public static boolean supportExperienceHostMode() {
        return FeatureUtil.supportHostMode();
    }

    public static boolean supportExternDevice() {
        if (CommonUtil.isAppInstalled("cn.nubia.externdevice")) {
            return !isFeatureCut("3");
        }
        Log.d(TAG, "supportExternDevice() false, not installed !");
        return false;
    }

    public static boolean supportFan() {
        return !isFeatureCut(SUPPORT_FAN);
    }

    public static boolean supportGameKey() {
        return !isFeatureCut("0");
    }

    public static boolean supportGiftBag() {
        return (isFeatureCut("5") || CommonUtil.isInter()) ? false : true;
    }

    public static boolean supportIdentify() {
        return !isFeatureCut("i");
    }

    public static boolean supportIndicator() {
        return (isFeatureCut(SUPPORT_INDICATOR) || CommonUtil.isInter()) ? false : true;
    }

    public static boolean supportLiveAtmosphere() {
        return (isFeatureCut("d") || ActivityManager.isUserAMonkey() || !supportRedTime()) ? false : true;
    }

    public static boolean supportMora() {
        return !isFeatureCut("1");
    }

    public static boolean supportNews() {
        return (isFeatureCut(SUPPORT_NEWS) || CommonUtil.isInter()) ? false : true;
    }

    public static boolean supportNotes() {
        return CommonUtil.isAppInstalled("cn.nubia.gamenotes");
    }

    public static boolean supportPlayByHandle() {
        return !isFeatureCut(SUPPORT_PLAY_HANDLE);
    }

    public static boolean supportPlayByMirror() {
        return !isFeatureCut("a");
    }

    public static boolean supportPlayByMouse() {
        return !isFeatureCut(SUPPORT_PLAY_MOUSE);
    }

    public static boolean supportPlayMode() {
        return supportPlayByMouse() || supportPlayByHandle() || supportPlayByMirror();
    }

    public static boolean supportRedTime() {
        return CommonUtil.isAppInstalled("cn.nubia.gamehighlights");
    }

    public static boolean supportRelevant() {
        return (isFeatureCut("4") || CommonUtil.isInter()) ? false : true;
    }

    public static boolean supportSingleRecommend() {
        return (isFeatureCut("2") || CommonUtil.isInter()) ? false : true;
    }

    public static boolean supportWechatShortcut() {
        return !isFeatureCut(SUPPORT_WECHAT_SHORTCUT);
    }
}
