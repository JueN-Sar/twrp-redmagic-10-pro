package cn.nubia.screensaver.util;

import android.content.Context;
import android.content.pm.LauncherApps;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import cn.nubia.gameassist.GameAssistApplication;
import com.zte.gameassist.common.GameCheck;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/* loaded from: classes.dex */
public class ShortCutUtil {

    /* renamed from: a, reason: collision with root package name */
    private Context f9166a;

    /* renamed from: b, reason: collision with root package name */
    private LauncherApps f9167b;

    private static class ShortCutUtilHolder {

        /* renamed from: a, reason: collision with root package name */
        public static final ShortCutUtil f9168a = new ShortCutUtil();
    }

    public static ShortCutUtil b() {
        return ShortCutUtilHolder.f9168a;
    }

    private void d() {
        GameAssistApplication j2 = GameAssistApplication.j();
        this.f9166a = j2;
        this.f9167b = (LauncherApps) j2.getSystemService("launcherapps");
    }

    public ShortcutInfo a(String str, String str2, ArrayList arrayList) {
        if (str != null && str2 != null && !arrayList.isEmpty()) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ShortcutInfo shortcutInfo = (ShortcutInfo) it.next();
                CharSequence shortLabel = shortcutInfo.getShortLabel();
                Objects.requireNonNull(shortLabel);
                String charSequence = shortLabel.toString();
                if (shortcutInfo.getId().equals(str) && charSequence.equals(str2)) {
                    return shortcutInfo;
                }
            }
        }
        return null;
    }

    public Drawable c(GameCheck.WechatMiniAppInfo wechatMiniAppInfo) {
        ShortcutInfo a2;
        try {
            ArrayList e2 = b().e();
            if (wechatMiniAppInfo == null || (a2 = a(wechatMiniAppInfo.h(), wechatMiniAppInfo.g(), e2)) == null) {
                return null;
            }
            return this.f9167b.getShortcutIconDrawable(a2, 0);
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public ArrayList e() {
        Bundle bundle;
        try {
            bundle = this.f9166a.getContentResolver().call(Uri.parse("content://com.zte.mifavor.launcher.dynamicshowhiddenapps"), "getWechatShortcut", (String) null, (Bundle) null);
        } catch (Exception e2) {
            e2.printStackTrace();
            bundle = null;
        }
        if (bundle == null) {
            return null;
        }
        return bundle.getParcelableArrayList("shortcutInfoList");
    }

    private ShortCutUtil() {
        d();
    }
}
