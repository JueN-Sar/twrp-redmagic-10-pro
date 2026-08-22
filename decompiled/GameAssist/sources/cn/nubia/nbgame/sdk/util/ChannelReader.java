package cn.nubia.nbgame.sdk.util;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class ChannelReader {

    /* renamed from: a, reason: collision with root package name */
    public static String f8311a = "ChannelReader";

    public static ChannelInfo a(File file) {
        Map b2 = b(file);
        if (b2 == null) {
            NeoLog.l(f8311a, "getchannel getMap(apkFile) result is null");
            return null;
        }
        String str = (String) b2.get("channel");
        b2.remove("channel");
        return new ChannelInfo(str, b2);
    }

    public static Map b(File file) {
        try {
            String c2 = c(file);
            NeoLog.f("getchannel getMap rawString is:" + c2);
            if (c2 == null) {
                NeoLog.l(f8311a, "getchannel getMap rawString == nul");
                return null;
            }
            JSONObject jSONObject = new JSONObject(c2);
            Iterator<String> keys = jSONObject.keys();
            HashMap hashMap = new HashMap();
            while (keys.hasNext()) {
                String obj = keys.next().toString();
                hashMap.put(obj, jSONObject.getString(obj));
            }
            return hashMap;
        } catch (JSONException e2) {
            PackageUtil.f8324b = true;
            e2.printStackTrace();
            return null;
        }
    }

    public static String c(File file) {
        return PayloadReader.d(file, 1903654775);
    }
}
