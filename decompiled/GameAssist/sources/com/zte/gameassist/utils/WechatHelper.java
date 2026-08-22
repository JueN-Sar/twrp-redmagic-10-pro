package com.zte.gameassist.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.LauncherApps;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.os.UserHandle;
import android.text.TextUtils;
import com.zte.gameassist.BaseApplication;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* loaded from: classes2.dex */
public class WechatHelper {

    /* renamed from: a, reason: collision with root package name */
    Context f17054a;

    /* renamed from: b, reason: collision with root package name */
    LauncherApps f17055b;

    /* renamed from: c, reason: collision with root package name */
    private PackageChangedCallback f17056c;

    /* renamed from: d, reason: collision with root package name */
    private final HashMap f17057d;

    static class PackageChangedCallback extends LauncherApps.Callback {
        PackageChangedCallback() {
        }

        @Override // android.content.pm.LauncherApps.Callback
        public void onPackageAdded(String str, UserHandle userHandle) {
        }

        @Override // android.content.pm.LauncherApps.Callback
        public void onPackageChanged(String str, UserHandle userHandle) {
        }

        @Override // android.content.pm.LauncherApps.Callback
        public void onPackageRemoved(String str, UserHandle userHandle) {
        }

        @Override // android.content.pm.LauncherApps.Callback
        public void onPackagesAvailable(String[] strArr, UserHandle userHandle, boolean z) {
        }

        @Override // android.content.pm.LauncherApps.Callback
        public void onPackagesUnavailable(String[] strArr, UserHandle userHandle, boolean z) {
        }

        @Override // android.content.pm.LauncherApps.Callback
        public void onShortcutsChanged(String str, List list, UserHandle userHandle) {
            GaLog.e("WechatHelper", "onShortcutsChanged() packageName : " + str + ", shortcuts : " + list.size());
            super.onShortcutsChanged(str, list, userHandle);
            if ("com.tencent.mm".equals(str)) {
                WechatHelper.a().j();
            }
        }
    }

    private static class WechatHelperHolder {

        /* renamed from: a, reason: collision with root package name */
        public static final WechatHelper f17058a = new WechatHelper();
    }

    public static WechatHelper a() {
        return WechatHelperHolder.f17058a;
    }

    public static String d(String str) {
        if (str == null) {
            return str;
        }
        if (str.contains("@")) {
            str = str.substring(str.indexOf("@") + 1);
        }
        GaLog.a("WechatHelper", "getWechatHashCode() packageName = " + str);
        return str;
    }

    public static boolean i(String str) {
        GaLog.a("WechatHelper", "isWechatGameApp packageName : " + str);
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.contains("@")) {
            int indexOf = str.indexOf("@");
            boolean equals = "com.tencent.mm".equals(str.substring(0, indexOf));
            if (!TextUtils.isEmpty(str.substring(indexOf - 1, str.length() - 1))) {
                z = equals;
            }
        }
        GaLog.a("WechatHelper", "isWechatGameApp rtn : " + z);
        return z;
    }

    private void k() {
        j();
    }

    public Drawable b(String str) {
        ShortcutInfo shortcutInfo;
        if (TextUtils.isEmpty(str) || this.f17057d.isEmpty() || (shortcutInfo = (ShortcutInfo) this.f17057d.get(str)) == null) {
            return null;
        }
        return this.f17055b.getShortcutIconDrawable(shortcutInfo, 0);
    }

    public Drawable c(String str, boolean z) {
        if (TextUtils.isEmpty(str) || this.f17057d.isEmpty()) {
            return null;
        }
        if (z) {
            str = d(str);
        }
        return b(str);
    }

    public String e(String str) {
        ShortcutInfo shortcutInfo;
        if (TextUtils.isEmpty(str) || this.f17057d.isEmpty() || (shortcutInfo = (ShortcutInfo) this.f17057d.get(str)) == null) {
            return null;
        }
        return shortcutInfo.getShortLabel().toString();
    }

    public String f(String str, boolean z) {
        if (TextUtils.isEmpty(str) || this.f17057d.isEmpty()) {
            return null;
        }
        if (z) {
            str = d(str);
        }
        return e(str);
    }

    public void g() {
        GaLog.a("WechatHelper", "init");
        this.f17054a = BaseApplication.a();
        h();
        k();
    }

    void h() {
        GaLog.e("WechatHelper", "initLauncherApps() - run())");
        try {
            LauncherApps launcherApps = (LauncherApps) this.f17054a.getSystemService("launcherapps");
            this.f17055b = launcherApps;
            boolean hasShortcutHostPermission = launcherApps.hasShortcutHostPermission();
            LauncherApps launcherApps2 = this.f17055b;
            PackageChangedCallback packageChangedCallback = new PackageChangedCallback();
            this.f17056c = packageChangedCallback;
            launcherApps2.registerCallback(packageChangedCallback);
            GaLog.e("WechatHelper", "initLauncherApps() - hasPermission : " + hasShortcutHostPermission);
        } catch (Exception e2) {
            GaLog.b("WechatHelper", "initLauncherApps() - e : " + e2.getMessage());
        }
    }

    public void j() {
        if (Process.myUid() / 100000 != 0) {
            GaLog.a("WechatHelper", "listShortcutsFromLauncher userId not 0 and Process.myUid() = " + Process.myUid());
            return;
        }
        this.f17057d.clear();
        GaLog.a("WechatHelper", "listShortcutsFromLauncher()");
        Bundle bundle = null;
        try {
            bundle = this.f17054a.getContentResolver().call(Uri.parse("content://com.zte.mifavor.launcher.dynamicshowhiddenapps"), "getWechatShortcut", (String) null, (Bundle) null);
        } catch (Exception e2) {
            GaLog.b("WechatHelper", "listShortcutsFromLauncher() e : " + e2.getMessage());
        }
        GaLog.a("WechatHelper", "listShortcutsFromLauncher() result : " + bundle);
        if (bundle == null) {
            return;
        }
        ArrayList parcelableArrayList = bundle.getParcelableArrayList("shortcutInfoList");
        if (parcelableArrayList.isEmpty()) {
            return;
        }
        Iterator it = parcelableArrayList.iterator();
        while (it.hasNext()) {
            ShortcutInfo shortcutInfo = (ShortcutInfo) it.next();
            if (!TextUtils.isEmpty(shortcutInfo.getShortLabel())) {
                this.f17057d.put(Integer.toString(shortcutInfo.getShortLabel().hashCode()), shortcutInfo);
            }
        }
    }

    public void l(String str) {
        if (TextUtils.isEmpty(str) || this.f17057d.isEmpty()) {
            return;
        }
        ShortcutInfo shortcutInfo = (ShortcutInfo) this.f17057d.get(str);
        String id = shortcutInfo.getId();
        ComponentName activity = shortcutInfo.getActivity();
        CharSequence shortLabel = shortcutInfo.getShortLabel();
        Objects.requireNonNull(shortLabel);
        GaLog.a("WechatHelper", "startShortcut(" + id + ") activity : " + activity + ", label : " + shortLabel.toString() + ", componentName : " + (shortcutInfo.getPackage() + "," + shortcutInfo.getActivity().getClassName()));
        this.f17055b.startShortcut(shortcutInfo.getPackage(), shortcutInfo.getId(), null, null, shortcutInfo.getUserHandle());
    }

    public void m(String str, boolean z) {
        if (TextUtils.isEmpty(str) || this.f17057d.isEmpty()) {
            return;
        }
        if (z) {
            str = d(str);
        }
        l(str);
    }

    private WechatHelper() {
        this.f17055b = null;
        this.f17056c = null;
        this.f17057d = new HashMap();
    }
}
