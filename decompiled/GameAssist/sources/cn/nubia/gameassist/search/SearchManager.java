package cn.nubia.gameassist.search;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.Settings;
import android.widget.Toast;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.utils.CommonUtil;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.ActivityManagerWrapper;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.List;

/* loaded from: classes.dex */
public class SearchManager {

    /* renamed from: d, reason: collision with root package name */
    private static volatile SearchManager f7395d;

    /* renamed from: a, reason: collision with root package name */
    private Uri f7396a;

    /* renamed from: b, reason: collision with root package name */
    private List f7397b;

    /* renamed from: c, reason: collision with root package name */
    private String f7398c = "";

    private SearchManager() {
    }

    public static SearchManager b() {
        if (f7395d == null) {
            synchronized (SearchManager.class) {
                try {
                    if (f7395d == null) {
                        f7395d = new SearchManager();
                    }
                } finally {
                }
            }
        }
        return f7395d;
    }

    public ActivityInfo a() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.setDataAndType(Uri.parse("http://"), null);
        List<ResolveInfo> queryIntentActivities = c().queryIntentActivities(intent, 131136);
        this.f7397b = queryIntentActivities;
        if (queryIntentActivities == null || queryIntentActivities.isEmpty()) {
            GaLog.k("SearchManager", "mResolveInfoList == null or empty");
            return null;
        }
        String string = Settings.Secure.getString(GameAssistApplication.j().getContentResolver(), "default_browser");
        this.f7398c = string;
        if (string == null || string.isEmpty()) {
            this.f7398c = CommonUtil.b() ? "com.android.chrome" : Utils.y(GameAssistApplication.j(), "cn.nubia.browser") ? "cn.nubia.browser" : "com.ume.browser";
        }
        for (ResolveInfo resolveInfo : this.f7397b) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            if (activityInfo != null && this.f7398c.equals(activityInfo.packageName)) {
                return resolveInfo.activityInfo;
            }
        }
        return null;
    }

    public PackageManager c() {
        return GameAssistApplication.j().getPackageManager();
    }

    public void d(String str) {
        Uri parse;
        if (CommonUtil.b()) {
            parse = Uri.parse("https://www.google.com/search?q=" + str);
        } else {
            parse = Uri.parse("https://www.baidu.com/s?wd=" + str);
        }
        this.f7396a = parse;
        ActivityInfo a2 = a();
        List list = this.f7397b;
        if (list == null || list.size() == 0) {
            Toast.makeText(GameAssistApplication.j(), GameAssistApplication.j().getString(R.string.browser_is_not_exist), 0).show();
            return;
        }
        if (a2 == null && Utils.z(GameAssistApplication.j(), this.f7398c)) {
            Toast.makeText(GameAssistApplication.j(), GameAssistApplication.j().getString(R.string.browser_is_stopped), 0).show();
            return;
        }
        Intent intent = new Intent("android.intent.action.VIEW", this.f7396a);
        intent.addFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
        intent.setComponent(new ComponentName(a2 != null ? a2.applicationInfo.packageName : ((ResolveInfo) this.f7397b.get(0)).activityInfo.packageName, a2 != null ? a2.name : ((ResolveInfo) this.f7397b.get(0)).activityInfo.name));
        ActivityManagerWrapper.getInstance();
        ActivityManagerWrapper.startWindowFreeForm(intent, GameAssistApplication.j(), 0, 0);
    }
}
