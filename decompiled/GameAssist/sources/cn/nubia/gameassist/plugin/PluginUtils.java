package cn.nubia.gameassist.plugin;

import android.content.Context;
import android.provider.Settings;
import android.util.Xml;
import cn.nubia.gameassist.utils.JsonUtil;
import cn.nubia.gameassist.view.NubiaTextClock;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.SharedPreferencesUtil;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes.dex */
public class PluginUtils {

    /* renamed from: f, reason: collision with root package name */
    private static volatile PluginUtils f7210f;

    /* renamed from: a, reason: collision with root package name */
    private final Context f7211a;

    /* renamed from: b, reason: collision with root package name */
    private final JsonUtil f7212b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f7213c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f7214d;

    /* renamed from: e, reason: collision with root package name */
    private boolean f7215e;

    class ResolutionConfig {

        /* renamed from: a, reason: collision with root package name */
        protected String f7216a;

        /* renamed from: b, reason: collision with root package name */
        protected List f7217b = new ArrayList();

        ResolutionConfig(String str) {
            this.f7216a = str;
        }

        public void a(String str, List list) {
            GaLog.e("PluginUtils", "saveConfigToLocal: config = " + str + " , packageList = " + list);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                SharedPreferencesUtil.k(PluginUtils.this.f7211a).P("magic_config_resolution", PluginUtils.this.f7212b.a((String) it.next(), "magic_config_resolution", str));
            }
            PluginUtils.this.f7215e = true;
        }

        public String toString() {
            return "ResolutionConfig{mResolution='" + this.f7216a + NubiaTextClock.QUOTE + ", mPackageList=" + this.f7217b + '}';
        }
    }

    private PluginUtils(Context context) {
        this.f7211a = context;
        this.f7212b = new JsonUtil(context);
    }

    public static PluginUtils f(Context context) {
        if (f7210f == null) {
            synchronized (PluginUtils.class) {
                try {
                    if (f7210f == null) {
                        f7210f = new PluginUtils(context);
                    }
                } finally {
                }
            }
        }
        return f7210f;
    }

    private Map g(String str) {
        HashMap hashMap = new HashMap();
        try {
            String string = Settings.Global.getString(this.f7211a.getContentResolver(), "game_gfrc_mode");
            if (string != null && !string.isEmpty()) {
                for (String str2 : string.split(",")) {
                    String[] split = str2.split("\\+");
                    if (split.length == 2) {
                        String str3 = split[0];
                        String str4 = split[1];
                        if (str == null || !str.equals(str3)) {
                            hashMap.put(str3, str4);
                        }
                    }
                }
            }
        } catch (Exception e2) {
            GaLog.b("PluginUtils", "Exception e = " + e2.getMessage());
        }
        return hashMap;
    }

    private void n() {
        if (ZteFeature.isSuperResolutionDetachEnable()) {
            this.f7214d = false;
            this.f7213c = false;
            String v = SystemMgr.v();
            Object b2 = this.f7212b.b(v, "magic_config_resolution");
            if (b2 != null) {
                this.f7214d = true;
                this.f7213c = String.valueOf(b2).split(",").length > 1;
            }
            GaLog.e("PluginUtils", "parseResolutionJsonToData: packageName = " + v + " , object = " + b2 + " , mIsSupportResolutionInXml = " + this.f7214d + " , mIsSupportResolutionSettingsInXml = " + this.f7213c);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0082 A[Catch: Exception -> 0x0030, TryCatch #0 {Exception -> 0x0030, blocks: (B:3:0x0003, B:11:0x0017, B:14:0x0027, B:16:0x00a3, B:21:0x0033, B:31:0x0062, B:33:0x006a, B:34:0x006d, B:36:0x0082, B:38:0x004a, B:41:0x0054), top: B:2:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void o(org.xmlpull.v1.XmlPullParser r11) {
        /*
            r10 = this;
            java.lang.String r0 = "PluginUtils"
            r1 = 0
            int r2 = r11.getEventType()     // Catch: java.lang.Exception -> L30
            r3 = 0
            r4 = r3
            r5 = r4
        La:
            r6 = 1
            if (r2 == r6) goto Lb0
            r7 = 2
            java.lang.String r8 = "config"
            if (r2 == r7) goto L33
            r6 = 3
            if (r2 == r6) goto L17
            goto La3
        L17:
            java.lang.String r2 = r11.getName()     // Catch: java.lang.Exception -> L30
            java.lang.String r2 = r2.trim()     // Catch: java.lang.Exception -> L30
            boolean r2 = r8.equals(r2)     // Catch: java.lang.Exception -> L30
            if (r2 == 0) goto La3
            if (r4 == 0) goto La3
            java.lang.String r2 = r4.f7216a     // Catch: java.lang.Exception -> L30
            r4.a(r2, r5)     // Catch: java.lang.Exception -> L30
            r4 = r3
            r5 = r4
            goto La3
        L30:
            r11 = move-exception
            goto La9
        L33:
            java.lang.String r2 = r11.getName()     // Catch: java.lang.Exception -> L30
            java.lang.String r2 = r2.trim()     // Catch: java.lang.Exception -> L30
            int r7 = r2.hashCode()     // Catch: java.lang.Exception -> L30
            r9 = -1354792126(0xffffffffaf3f8342, float:-1.7417981E-10)
            if (r7 == r9) goto L54
            r8 = 3242771(0x317b13, float:4.54409E-39)
            if (r7 == r8) goto L4a
            goto L5c
        L4a:
            java.lang.String r7 = "item"
            boolean r2 = r2.equals(r7)     // Catch: java.lang.Exception -> L30
            if (r2 == 0) goto L5c
            r2 = r6
            goto L5d
        L54:
            boolean r2 = r2.equals(r8)     // Catch: java.lang.Exception -> L30
            if (r2 == 0) goto L5c
            r2 = r1
            goto L5d
        L5c:
            r2 = -1
        L5d:
            if (r2 == 0) goto L82
            if (r2 == r6) goto L62
            goto La3
        L62:
            java.lang.String r2 = "package_name"
            java.lang.String r2 = r11.getAttributeValue(r3, r2)     // Catch: java.lang.Exception -> L30
            if (r5 == 0) goto L6d
            r5.add(r2)     // Catch: java.lang.Exception -> L30
        L6d:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L30
            r6.<init>()     // Catch: java.lang.Exception -> L30
            java.lang.String r7 = "parseResolutionXmlInfo: package_name = "
            r6.append(r7)     // Catch: java.lang.Exception -> L30
            r6.append(r2)     // Catch: java.lang.Exception -> L30
            java.lang.String r2 = r6.toString()     // Catch: java.lang.Exception -> L30
            com.zte.gameassist.utils.GaLog.e(r0, r2)     // Catch: java.lang.Exception -> L30
            goto La3
        L82:
            java.lang.String r2 = "resolution_config"
            java.lang.String r2 = r11.getAttributeValue(r3, r2)     // Catch: java.lang.Exception -> L30
            cn.nubia.gameassist.plugin.PluginUtils$ResolutionConfig r4 = new cn.nubia.gameassist.plugin.PluginUtils$ResolutionConfig     // Catch: java.lang.Exception -> L30
            r4.<init>(r2)     // Catch: java.lang.Exception -> L30
            java.util.List r5 = r4.f7217b     // Catch: java.lang.Exception -> L30
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L30
            r6.<init>()     // Catch: java.lang.Exception -> L30
            java.lang.String r7 = "parseResolutionXmlInfo: config = "
            r6.append(r7)     // Catch: java.lang.Exception -> L30
            r6.append(r2)     // Catch: java.lang.Exception -> L30
            java.lang.String r2 = r6.toString()     // Catch: java.lang.Exception -> L30
            com.zte.gameassist.utils.GaLog.e(r0, r2)     // Catch: java.lang.Exception -> L30
        La3:
            int r2 = r11.next()     // Catch: java.lang.Exception -> L30
            goto La
        La9:
            r10.f7215e = r1
            java.lang.String r10 = "parseResolutionXmlInfo exception!"
            com.zte.gameassist.utils.GaLog.c(r0, r10, r11)
        Lb0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gameassist.plugin.PluginUtils.o(org.xmlpull.v1.XmlPullParser):void");
    }

    public void d(String str, int i2) {
        if (i2 == 0) {
            Map g2 = g(str);
            StringBuilder sb = new StringBuilder();
            for (Map.Entry entry : g2.entrySet()) {
                sb.append((String) entry.getKey());
                sb.append("+");
                sb.append((String) entry.getValue());
                sb.append(",");
            }
            if (sb.length() > 0) {
                sb.setLength(sb.length() - 1);
            }
            GaLog.a("PluginUtils", "checkSuperResolutionEnable total data = " + ((Object) sb));
            Settings.Global.putString(this.f7211a.getContentResolver(), "game_gfrc_mode", sb.toString());
        }
    }

    public int e(String str) {
        try {
            int intValue = ((Integer) Class.forName("com.zte.performance.mindsync.MindSyncManager$Trigger").getMethod("getGfrcCapByPkg", String.class).invoke(null, str)).intValue();
            GaLog.a("PluginUtils", "supportResolution pkg = " + str + ",cap = " + intValue);
            d(str, intValue);
            return intValue;
        } catch (Exception e2) {
            GaLog.b("PluginUtils", "getGfrcCapByPkg e = " + e2);
            return 0;
        }
    }

    public boolean h() {
        return !ZteFeature.isSuperResolutionDetachEnable() ? ZteFeature.isSupportSuperResolutionOld() : this.f7214d;
    }

    public boolean i() {
        return !ZteFeature.isSuperResolutionDetachEnable() ? ZteFeature.isSupportSuperResolutionSettings() : this.f7213c;
    }

    public void j() {
        try {
            f(this.f7211a).m();
        } catch (Exception e2) {
            GaLog.c("PluginUtils", "parseResolutionConfig e：", e2);
        }
        n();
    }

    public void k() {
        this.f7214d = false;
        this.f7213c = false;
    }

    public void l() {
        n();
    }

    public void m() {
        if (!ZteFeature.isSuperResolutionDetachEnable() || this.f7215e) {
            return;
        }
        InputStream inputStream = null;
        try {
            try {
                SharedPreferencesUtil.k(this.f7211a).P("magic_config_resolution", "");
                XmlPullParser newPullParser = Xml.newPullParser();
                inputStream = Files.newInputStream(Paths.get("data/gamemagic/config/magic_config.xml", new String[0]), new OpenOption[0]);
                newPullParser.setInput(inputStream, "UTF-8");
                o(newPullParser);
                if (inputStream == null) {
                    return;
                }
            } catch (Exception e2) {
                GaLog.c("PluginUtils", "parseResolutionConfig exception!", e2);
                if (inputStream == null) {
                    return;
                }
            }
            inputStream.close();
        } catch (Throwable th) {
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    public boolean p(String str) {
        return e(str) != 0;
    }
}
