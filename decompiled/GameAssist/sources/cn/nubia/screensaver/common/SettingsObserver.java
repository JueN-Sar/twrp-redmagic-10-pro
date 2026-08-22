package cn.nubia.screensaver.common;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import cn.nubia.screensaver.GameScreensaverManager;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class SettingsObserver extends ContentObserver {

    /* renamed from: a, reason: collision with root package name */
    private final GameScreensaverManager f9028a;

    /* renamed from: b, reason: collision with root package name */
    private final Handler f9029b;

    /* renamed from: c, reason: collision with root package name */
    private final Context f9030c;

    /* renamed from: d, reason: collision with root package name */
    private final Uri f9031d;

    /* renamed from: e, reason: collision with root package name */
    private final Uri f9032e;

    /* renamed from: f, reason: collision with root package name */
    private final Uri f9033f;

    /* renamed from: g, reason: collision with root package name */
    private final Uri f9034g;

    /* renamed from: h, reason: collision with root package name */
    private final Uri f9035h;

    /* renamed from: i, reason: collision with root package name */
    private final Uri f9036i;

    /* renamed from: j, reason: collision with root package name */
    private boolean f9037j;

    /* renamed from: k, reason: collision with root package name */
    private boolean f9038k;

    /* renamed from: l, reason: collision with root package name */
    private boolean f9039l;

    public SettingsObserver(GameScreensaverManager gameScreensaverManager) {
        super(gameScreensaverManager.K());
        this.f9028a = gameScreensaverManager;
        Handler C = gameScreensaverManager.C();
        this.f9029b = C;
        this.f9030c = gameScreensaverManager.H();
        this.f9031d = Settings.System.getUriFor("keyguard_is_showing");
        this.f9032e = Settings.Global.getUriFor("db_game_quick_info");
        this.f9033f = Settings.Global.getUriFor("virtual_game_key");
        this.f9034g = Settings.Global.getUriFor("cc_nubia_game_key");
        this.f9036i = Settings.Secure.getUriFor("default_home");
        this.f9035h = Settings.System.getUriFor("fourth_physical_key_function_value");
        C.post(new Runnable() { // from class: cn.nubia.screensaver.common.m
            @Override // java.lang.Runnable
            public final void run() {
                SettingsObserver.this.f();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        ContentResolver contentResolver = this.f9030c.getContentResolver();
        contentResolver.registerContentObserver(this.f9031d, false, this);
        contentResolver.registerContentObserver(this.f9032e, false, this);
        contentResolver.registerContentObserver(this.f9033f, false, this);
        contentResolver.registerContentObserver(this.f9034g, false, this);
        contentResolver.registerContentObserver(this.f9036i, false, this);
        contentResolver.registerContentObserver(this.f9035h, false, this);
        n();
        o();
        m();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j() {
        this.f9028a.F(this.f9039l);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k() {
        this.f9028a.o0("keyguardStatus");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l() {
        this.f9028a.o0("quickInfo");
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x002c, code lost:
    
        if ((android.provider.Settings.Global.getInt(r5.f9030c.getContentResolver(), "cc_nubia_game_key", 0) & 1) == 1) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0052, code lost:
    
        if (r0 != false) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void m() {
        /*
            r5 = this;
            android.content.Context r0 = r5.f9030c
            android.content.ContentResolver r0 = r0.getContentResolver()
            java.lang.String r1 = "fourth_physical_key_function_value"
            r2 = 2
            int r0 = android.provider.Settings.System.getInt(r0, r1, r2)
            r1 = 0
            r3 = 1
            if (r0 != r2) goto L13
            r0 = r3
            goto L14
        L13:
            r0 = r1
        L14:
            boolean r2 = cn.nubia.screensaver.common.Constants.f9024a
            if (r2 != 0) goto L30
            boolean r2 = cn.nubia.screensaver.common.Constants.f9025b
            if (r2 == 0) goto L1f
            if (r0 != 0) goto L1f
            goto L30
        L1f:
            android.content.Context r0 = r5.f9030c
            android.content.ContentResolver r0 = r0.getContentResolver()
            java.lang.String r2 = "cc_nubia_game_key"
            int r0 = android.provider.Settings.Global.getInt(r0, r2, r1)
            r0 = r0 & r3
            if (r0 != r3) goto L55
        L2e:
            r1 = r3
            goto L55
        L30:
            android.content.Context r0 = r5.f9030c
            android.content.ContentResolver r0 = r0.getContentResolver()
            java.lang.String r2 = "default_home"
            java.lang.String r0 = android.provider.Settings.Secure.getString(r0, r2)
            java.lang.String r2 = "cn.nubia.gamelauncher"
            boolean r0 = android.text.TextUtils.equals(r2, r0)
            android.content.Context r2 = r5.f9030c
            android.content.ContentResolver r2 = r2.getContentResolver()
            java.lang.String r4 = "virtual_game_key"
            int r2 = android.provider.Settings.Global.getInt(r2, r4, r1)
            r2 = r2 & r3
            if (r2 != r3) goto L52
            goto L54
        L52:
            if (r0 == 0) goto L55
        L54:
            goto L2e
        L55:
            boolean r0 = r5.f9039l
            if (r0 == r1) goto L65
            r5.f9039l = r1
            android.os.Handler r0 = r5.f9029b
            cn.nubia.screensaver.common.k r1 = new cn.nubia.screensaver.common.k
            r1.<init>()
            r0.post(r1)
        L65:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.screensaver.common.SettingsObserver.m():void");
    }

    private void n() {
        boolean z = Settings.System.getInt(this.f9030c.getContentResolver(), "keyguard_is_showing", 0) == 1;
        if (this.f9037j != z) {
            this.f9037j = z;
            this.f9029b.post(new Runnable() { // from class: cn.nubia.screensaver.common.j
                @Override // java.lang.Runnable
                public final void run() {
                    SettingsObserver.this.k();
                }
            });
        }
    }

    private void o() {
        boolean z = Settings.Global.getInt(this.f9030c.getContentResolver(), "db_game_quick_info", 1) == 1;
        if (this.f9038k != z) {
            this.f9038k = z;
            this.f9029b.post(new Runnable() { // from class: cn.nubia.screensaver.common.l
                @Override // java.lang.Runnable
                public final void run() {
                    SettingsObserver.this.l();
                }
            });
        }
    }

    public void e(FileDescriptor fileDescriptor, PrintWriter printWriter, String str) {
        printWriter.println(str + "mIsGameMode=" + this.f9039l);
        printWriter.println(str + "  ENABLE_VIRTUAL_GAME_KEY=" + Constants.f9024a);
        printWriter.println(str + "  virtualKey=" + ((Settings.Global.getInt(this.f9030c.getContentResolver(), "virtual_game_key", 0) & 1) == 1));
        printWriter.println(str + "  gameKey=" + ((Settings.Global.getInt(this.f9030c.getContentResolver(), "cc_nubia_game_key", 0) & 1) == 1));
        printWriter.println(str + "  defaultLauncher=" + Settings.Secure.getString(this.f9030c.getContentResolver(), "default_home"));
        printWriter.println(str + "mIsOpenGameQuickInfo=" + this.f9038k);
    }

    public boolean g() {
        return this.f9039l;
    }

    public boolean h() {
        return this.f9037j;
    }

    public boolean i() {
        return this.f9038k;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z, Uri uri) {
        super.onChange(z, uri);
        if (this.f9031d.equals(uri)) {
            n();
            return;
        }
        if (this.f9032e.equals(uri)) {
            o();
        } else if (this.f9034g.equals(uri) || this.f9033f.equals(uri) || this.f9036i.equals(uri) || this.f9035h.equals(uri)) {
            m();
        }
    }
}
