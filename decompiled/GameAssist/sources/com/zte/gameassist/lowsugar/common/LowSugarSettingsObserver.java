package com.zte.gameassist.lowsugar.common;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import com.zte.gameassist.lowsugar.LowSugarGameplayController;
import com.zte.gameassist.lowsugar.ai.LowSugarAiMgr;
import com.zte.gameassist.lowsugar.common.LowSugarSettingsObserver;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes2.dex */
public class LowSugarSettingsObserver extends ContentObserver {

    /* renamed from: a, reason: collision with root package name */
    private final Handler f16794a;

    /* renamed from: b, reason: collision with root package name */
    private final Context f16795b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f16796c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f16797d;

    public LowSugarSettingsObserver(Handler handler, Context context) {
        super(handler);
        this.f16794a = handler;
        this.f16795b = context;
        handler.post(new Runnable() { // from class: r.a
            @Override // java.lang.Runnable
            public final void run() {
                LowSugarSettingsObserver.this.b();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        ContentResolver contentResolver = this.f16795b.getContentResolver();
        contentResolver.registerContentObserver(Constants.f16792a, false, this);
        contentResolver.registerContentObserver(Constants.f16793b, false, this);
        e();
    }

    private void e() {
        GaLog.a("LowSugarGameplay.Settings", "updateScreenExtraction");
        boolean z = false;
        boolean z2 = Settings.Global.getInt(this.f16795b.getContentResolver(), "nubia_low_sugar_gameplay_pkg_open", 0) == 1;
        boolean z3 = Settings.Global.getInt(this.f16795b.getContentResolver(), "nubia_low_sugar_automatic_recognition_task_pkg_open", 0) == 1;
        if (z2) {
            GaLog.a("LowSugarGameplay.Settings", "updateScreenExtraction bind sentry mode servce!");
            LowSugarAiMgr.F().y();
        } else {
            GaLog.a("LowSugarGameplay.Settings", "updateScreenExtraction unbind sentry mode servce!");
            LowSugarAiMgr.F().Z();
        }
        if (z2 != this.f16796c) {
            this.f16796c = z2;
            z = true;
        }
        if (z3 != this.f16797d) {
            this.f16797d = z3;
        } else if (!z) {
            return;
        }
        LowSugarGameplayController.l().w("settings");
    }

    public boolean c() {
        return this.f16797d;
    }

    public boolean d() {
        return this.f16796c;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z, Uri uri) {
        super.onChange(z, uri);
        if (Constants.f16792a.equals(uri) || Constants.f16793b.equals(uri)) {
            e();
        }
    }
}
