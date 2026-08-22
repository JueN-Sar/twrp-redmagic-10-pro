package cn.nubia.plugin.screenextraction.common;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import cn.nubia.plugin.screenextraction.ScreenExtractionManager;
import cn.nubia.plugin.screenextraction.common.SystemSettingsObserver;
import com.zte.gameassist.utils.GaLog;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/* loaded from: classes.dex */
public class SystemSettingsObserver extends ContentObserver {

    /* renamed from: a, reason: collision with root package name */
    private final ScreenExtractionManager f8581a;

    /* renamed from: b, reason: collision with root package name */
    private final Handler f8582b;

    /* renamed from: c, reason: collision with root package name */
    private final Context f8583c;

    /* renamed from: d, reason: collision with root package name */
    private final Uri f8584d;

    /* renamed from: e, reason: collision with root package name */
    private final Uri f8585e;

    /* renamed from: f, reason: collision with root package name */
    private final List f8586f;

    /* renamed from: g, reason: collision with root package name */
    private boolean f8587g;

    /* renamed from: h, reason: collision with root package name */
    private String f8588h;

    public SystemSettingsObserver(ScreenExtractionManager screenExtractionManager) {
        super(screenExtractionManager.v());
        this.f8586f = new ArrayList();
        this.f8581a = screenExtractionManager;
        Handler v = screenExtractionManager.v();
        this.f8582b = v;
        this.f8583c = screenExtractionManager.u();
        this.f8584d = Settings.Global.getUriFor("nubia_screen_extraction_pkg_open");
        this.f8585e = Settings.Secure.getUriFor("hasWindowReply");
        v.post(new Runnable() { // from class: m.a
            @Override // java.lang.Runnable
            public final void run() {
                SystemSettingsObserver.this.e();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        ContentResolver contentResolver = this.f8583c.getContentResolver();
        contentResolver.registerContentObserver(this.f8584d, false, this);
        contentResolver.registerContentObserver(this.f8585e, false, this);
        i();
        h();
    }

    private void g(String str) {
        Settings.Global.putString(this.f8583c.getContentResolver(), "nubia_screen_extraction_pkg_open", str);
        GaLog.a("ScreenExtraction.Settings", "saveEnablePackageString " + str);
    }

    private void h() {
        String string = Settings.Secure.getString(this.f8583c.getContentResolver(), "hasWindowReply");
        boolean z = (string == null || "0".equals(string)) ? false : true;
        if (this.f8587g != z) {
            this.f8587g = z;
            ScreenExtractionManager.w().E();
        }
    }

    private void i() {
        String string = Settings.Global.getString(this.f8583c.getContentResolver(), "nubia_screen_extraction_pkg_open");
        if (string == null || string.equals(this.f8588h)) {
            return;
        }
        this.f8588h = string;
        ArrayList arrayList = new ArrayList();
        for (String str : this.f8588h.split(",")) {
            if (str != null && str.length() > 0) {
                arrayList.add(str);
            }
        }
        synchronized (this) {
            this.f8586f.clear();
            this.f8586f.addAll(arrayList);
            ScreenExtractionManager.w().O();
        }
    }

    public void b(String str) {
        String str2;
        synchronized (this) {
            try {
                if (this.f8586f.contains(str)) {
                    this.f8586f.remove(str);
                    str2 = this.f8586f.size() == 0 ? "" : (String) this.f8586f.stream().collect(Collectors.joining(","));
                } else {
                    str2 = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        g(str2);
    }

    public void c(String str) {
        String str2;
        synchronized (this) {
            try {
                if (this.f8586f.contains(str)) {
                    str2 = null;
                } else {
                    this.f8586f.add(str);
                    str2 = (String) this.f8586f.stream().collect(Collectors.joining(","));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        g(str2);
    }

    public List d() {
        ArrayList arrayList;
        synchronized (this) {
            arrayList = new ArrayList(this.f8586f);
        }
        return arrayList;
    }

    public boolean f() {
        return this.f8587g;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z, Uri uri) {
        super.onChange(z, uri);
        if (this.f8584d.equals(uri)) {
            i();
        } else if (this.f8585e.equals(uri)) {
            h();
        }
    }
}
