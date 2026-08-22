package com.zte.gameassist.common;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import com.zte.gameassist.utils.GaLog;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class ObserverManager {

    /* renamed from: d, reason: collision with root package name */
    private static volatile ObserverManager f16534d;

    /* renamed from: a, reason: collision with root package name */
    private HashMap f16535a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    private HashMap f16536b = new HashMap();

    /* renamed from: c, reason: collision with root package name */
    private Object f16537c = new Object();

    public interface SettingCallback {
        void w(boolean z, Uri uri);
    }

    private ObserverManager() {
    }

    public static ObserverManager c() {
        if (f16534d == null) {
            synchronized (ThreadManager.class) {
                try {
                    if (f16534d == null) {
                        f16534d = new ObserverManager();
                    }
                } finally {
                }
            }
        }
        return f16534d;
    }

    public void b(Context context, Uri uri, SettingCallback settingCallback) {
        ArrayList arrayList;
        synchronized (this.f16537c) {
            try {
                if (this.f16535a.keySet().contains(uri)) {
                    arrayList = (ArrayList) this.f16535a.get(uri);
                } else {
                    ArrayList arrayList2 = new ArrayList();
                    ContentObserver contentObserver = new ContentObserver(new Handler(ThreadManager.c().b())) { // from class: com.zte.gameassist.common.ObserverManager.1
                        @Override // android.database.ContentObserver
                        public void onChange(boolean z, Uri uri2) {
                            super.onChange(z, uri2);
                            if (ObserverManager.this.f16535a.get(uri2) != null) {
                                Iterator it = new ArrayList((Collection) ObserverManager.this.f16535a.get(uri2)).iterator();
                                while (it.hasNext()) {
                                    SettingCallback settingCallback2 = (SettingCallback) it.next();
                                    if (settingCallback2 != null) {
                                        settingCallback2.w(z, uri2);
                                    } else {
                                        GaLog.a("ObserverManager", uri2 + ",callback is null");
                                    }
                                }
                            }
                        }
                    };
                    this.f16535a.put(uri, arrayList2);
                    context.getContentResolver().registerContentObserver(uri, true, contentObserver);
                    this.f16536b.put(uri, contentObserver);
                    arrayList = arrayList2;
                }
                arrayList.add(settingCallback);
                settingCallback.w(true, uri);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void d(Context context, Uri uri, SettingCallback settingCallback) {
        synchronized (this.f16537c) {
            try {
                if (this.f16535a.keySet().contains(uri)) {
                    ArrayList arrayList = (ArrayList) this.f16535a.get(uri);
                    if (arrayList.remove(settingCallback) && arrayList.size() == 0) {
                        context.getContentResolver().unregisterContentObserver((ContentObserver) this.f16536b.get(uri));
                        this.f16536b.remove(uri);
                        this.f16535a.remove(uri);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
