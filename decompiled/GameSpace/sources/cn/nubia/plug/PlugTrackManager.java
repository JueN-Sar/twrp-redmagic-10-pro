package cn.nubia.plug;

import cn.nubia.gamelauncher.util.NubiaTrackManager;

/* loaded from: classes.dex */
public class PlugTrackManager {
    public static void uploadPlugName(String str) {
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", Constant.EVENT_PLUGIN_CLICKS, Constant.ARG_PLUGIN_NAME, str);
    }
}
