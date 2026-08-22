package cn.nubia.gameassist.plugin.config;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings;
import android.util.Xml;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.view.NubiaTextClock;
import com.google.android.gms.common.api.Api;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class UpdatePluginConfigService extends IntentService {

    /* renamed from: c, reason: collision with root package name */
    private Context f7223c;

    /* renamed from: cn.nubia.gameassist.plugin.config.UpdatePluginConfigService$1ItemConfig, reason: invalid class name */
    class C1ItemConfig {

        /* renamed from: a, reason: collision with root package name */
        protected final String f7224a;

        /* renamed from: b, reason: collision with root package name */
        protected final int f7225b;

        /* renamed from: c, reason: collision with root package name */
        protected final boolean f7226c;

        /* renamed from: d, reason: collision with root package name */
        protected List f7227d = new ArrayList();

        /* renamed from: e, reason: collision with root package name */
        protected List f7228e = new ArrayList();

        C1ItemConfig(String str, int i2, boolean z) {
            this.f7224a = str;
            this.f7225b = i2;
            this.f7226c = z;
        }

        private String a(List list) {
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < list.size(); i2++) {
                sb.append((String) list.get(i2));
                if (i2 != list.size() - 1) {
                    sb.append(",");
                }
            }
            return sb.toString();
        }

        public void b() {
            if (((PluginConfig.j() ? 1 : 2) & this.f7225b) == 0) {
                GaLog.e("PluginConfig", "Version does not match");
                return;
            }
            UpdatePluginConfigService.k(UpdatePluginConfigService.this.f7223c, this.f7224a, this.f7225b);
            UpdatePluginConfigService.j(UpdatePluginConfigService.this.f7223c, this.f7224a, this.f7226c);
            UpdatePluginConfigService.l(UpdatePluginConfigService.this.f7223c, this.f7224a, a(this.f7227d));
            UpdatePluginConfigService.h(UpdatePluginConfigService.this.f7223c, this.f7224a, a(this.f7228e));
            if (PluginConfig.f7220b) {
                GaLog.e("PluginConfig", this.f7224a + " enable=" + PluginConfig.k(UpdatePluginConfigService.this.f7223c, this.f7224a));
                GaLog.e("PluginConfig", this.f7224a + " space=" + PluginConfig.h(UpdatePluginConfigService.this.f7223c, this.f7224a));
                GaLog.e("PluginConfig", "  WhiteList");
                for (String str : PluginConfig.i(UpdatePluginConfigService.this.f7223c, this.f7224a)) {
                    GaLog.e("PluginConfig", "    " + str);
                }
                GaLog.e("PluginConfig", "  BlackList");
                for (String str2 : PluginConfig.d(UpdatePluginConfigService.this.f7223c, this.f7224a)) {
                    GaLog.e("PluginConfig", "    " + str2);
                }
            }
        }

        public String toString() {
            return "ItemConfig{mName='" + this.f7224a + NubiaTextClock.QUOTE + ", mSpaceTrigger=" + this.f7225b + ", mWhiteList=" + this.f7227d + ", mBlackList=" + this.f7228e + '}';
        }
    }

    public static final class Dict {
    }

    public UpdatePluginConfigService() {
        super("UpdatePluginConfig");
        GaLog.e("PluginConfig", "create UpdatePluginConfigService");
    }

    public static void c(final Context context) {
        n(context);
        String e2 = PluginConfig.e(context);
        final boolean isPluginNeedRemove = ZteFeature.isPluginNeedRemove();
        boolean z = PluginConfig.m(e2) < PluginConfig.m("v26.03.25");
        GaLog.e("PluginConfig", e2 + " < XML_CONFIG_VERSION updateDefaultConfig= " + z);
        if (e2 == null || e2.length() == 0 || z) {
            AsyncTask.execute(new Runnable() { // from class: cn.nubia.gameassist.plugin.config.a
                @Override // java.lang.Runnable
                public final void run() {
                    UpdatePluginConfigService.e(isPluginNeedRemove, context);
                }
            });
        }
    }

    private boolean d(String str, String str2, String str3, String str4) {
        int i2 = Build.VERSION.SDK_INT;
        int parseInt = str2 != null ? Integer.parseInt(str2) : 0;
        int parseInt2 = str != null ? Integer.parseInt(str) : Api.BaseClientBuilder.API_PRIORITY_OTHER;
        if (i2 < parseInt || i2 > parseInt2) {
            if (!PluginConfig.f7220b) {
                return false;
            }
            GaLog.e("PluginConfig", "sdk : " + parseInt + " < " + i2 + " < " + parseInt2);
            return false;
        }
        String lowerCase = Build.MODEL.trim().toLowerCase();
        boolean z = true;
        if (str3 != null) {
            boolean z2 = false;
            for (String str5 : str3.trim().toLowerCase().split(":")) {
                if (lowerCase.contains(str5)) {
                    z2 = true;
                }
            }
            return z2;
        }
        if (str4 != null) {
            String[] split = str4.trim().toLowerCase().split(":");
            for (String str6 : split) {
                if (lowerCase.contains(str6)) {
                    z = false;
                }
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void e(boolean z, Context context) {
        try {
            Uri parse = z ? Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.redmagic_default_plugin_config) : Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.default_plugin_config);
            Intent intent = new Intent("cn.nubia.gameassist.CONFIG_UPDATE_ACTION");
            intent.addFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_COLOR_SPACE_AGNOSTIC);
            intent.setData(Uri.parse("dispatcher:cn.nubia.gameassist.config.UPDATE_PLUGIN_CONFIG"));
            intent.putExtra("uri", parse.toString());
            context.sendBroadcast(intent);
            GaLog.e("PluginConfig", "send PluginConfig Broadcast");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (PluginConfig.f7220b) {
            try {
                Intent intent2 = new Intent();
                intent2.setAction("cn.nubia.jobdispatcher.broadcast");
                intent2.setData(Uri.parse("dispatcher:cn.nubia.gameassist.config.UPDATE_PLUGIN_CONFIG"));
                List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent2, 0);
                if (queryBroadcastReceivers == null || queryBroadcastReceivers.size() <= 0) {
                    return;
                }
                for (ResolveInfo resolveInfo : queryBroadcastReceivers) {
                    GaLog.e("PluginConfig", "package=" + resolveInfo.activityInfo.packageName + " info=" + resolveInfo.activityInfo.toString());
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:3|(1:(3:15|16|(1:19)))(3:24|25|(2:29|(1:(1:(1:(1:38))(1:(1:42)))(1:(1:46)))(1:47))(11:48|49|50|51|52|53|(2:55|56)|7|8|10|11))|6|7|8|10|11) */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x013c, code lost:
    
        r0 = e;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void f(org.xmlpull.v1.XmlPullParser r17) {
        /*
            Method dump skipped, instructions count: 364
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gameassist.plugin.config.UpdatePluginConfigService.f(org.xmlpull.v1.XmlPullParser):void");
    }

    private void g(InputStream inputStream) {
        GaLog.e("PluginConfig", "parserXml in=" + inputStream);
        XmlPullParser newPullParser = Xml.newPullParser();
        try {
            newPullParser.setInput(inputStream, "UTF-8");
            f(newPullParser);
            inputStream.close();
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
        }
    }

    public static void h(Context context, String str, String str2) {
        Settings.Global.putString(context.getContentResolver(), "game_assist_black_list_" + str, str2);
    }

    public static boolean i(Context context, String str, boolean z) {
        String e2 = PluginConfig.e(context);
        int m2 = PluginConfig.m(str);
        int m3 = PluginConfig.m(e2);
        GaLog.e("PluginConfig", "currentVersion= " + m2 + " oldVersion= " + m3);
        if (m2 <= m3 && e2 != null && e2.length() != 0) {
            return false;
        }
        if (z) {
            Settings.Global.putString(context.getContentResolver(), "game_assist_plugin_config_version", str);
        }
        Settings.Global.putLong(context.getContentResolver(), "game_assist_plugin_config_update_time", System.currentTimeMillis());
        return true;
    }

    public static void j(Context context, String str, boolean z) {
        Settings.Global.putInt(context.getContentResolver(), "game_assist_enable_plugin_" + str, z ? 1 : 0);
    }

    public static void k(Context context, String str, int i2) {
        Settings.Global.putInt(context.getContentResolver(), "game_assist_trigger_space_" + str, i2);
    }

    public static void l(Context context, String str, String str2) {
        Settings.Global.putString(context.getContentResolver(), "game_assist_white_list_" + str, str2);
    }

    public static void n(Context context) {
        PluginConfig.f7220b = Settings.Global.getInt(context.getContentResolver(), "debug_plugin_config", 0) == 1;
    }

    public void m(Context context, String str) {
        this.f7223c = context;
        if (str != null) {
            try {
                try {
                    g(context.getContentResolver().openInputStream(Uri.parse(str)));
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } finally {
                stopSelf();
            }
        }
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String stringExtra = intent.getStringExtra("uri");
            if (PluginConfig.f7220b) {
                GaLog.e("PluginConfig", "onHandleIntent uri=" + stringExtra);
            }
            if (stringExtra != null) {
                m(getApplicationContext(), stringExtra);
            }
        }
    }

    @Override // android.app.IntentService, android.app.Service
    public void onStart(Intent intent, int i2) {
        super.onStart(intent, i2);
        n(this);
    }
}
