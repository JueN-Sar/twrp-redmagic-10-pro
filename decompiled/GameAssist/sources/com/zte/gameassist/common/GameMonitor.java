package com.zte.gameassist.common;

import android.content.ComponentName;
import com.zte.gameassist.AbsGameAssistToken;
import com.zte.gameassist.common.GameCheck;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public interface GameMonitor {

    /* renamed from: a, reason: collision with root package name */
    public static final List f16515a = new ArrayList();

    public interface Callback {
        default void onFocuesWindowChanged(AbsGameAssistToken.FocuesWindow focuesWindow) {
        }

        default void onFullscreenActivityChange(ComponentName componentName) {
        }

        /* renamed from: onGameSceneStateChanged */
        default void m0(boolean z) {
        }

        /* renamed from: onGameStart */
        default void y() {
        }

        /* renamed from: onGameStop */
        default void z() {
        }

        /* renamed from: onGameUpdate */
        default void A() {
        }

        default void onLauncherFirstPackage(String str) {
        }

        default void onProjectionActivityResumed(ComponentName componentName, int i2) {
        }

        default void onResumeFullscreenActivityPidChanged() {
        }

        default void onShowTipAnimation(GameCheck.GameAppInfo gameAppInfo) {
        }
    }

    default void a(boolean z) {
        ArrayList arrayList = new ArrayList();
        List list = f16515a;
        synchronized (list) {
            arrayList.addAll(list);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((Callback) it.next()).m0(z);
        }
    }

    default void b() {
        ArrayList arrayList = new ArrayList();
        List list = f16515a;
        synchronized (list) {
            arrayList.addAll(list);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((Callback) it.next()).A();
        }
    }

    default void c() {
        ArrayList<Callback> arrayList = new ArrayList();
        List list = f16515a;
        synchronized (list) {
            arrayList.addAll(list);
        }
        for (Callback callback : arrayList) {
            if (g()) {
                callback.y();
            } else {
                callback.z();
            }
        }
    }

    default void d(ComponentName componentName) {
        ArrayList arrayList = new ArrayList();
        List list = f16515a;
        synchronized (list) {
            arrayList.addAll(list);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((Callback) it.next()).onFullscreenActivityChange(componentName);
        }
    }

    default void e() {
        GameCheck.GameAppInfo d2 = GameCheck.d(SystemMgr.w, SystemMgr.A);
        ArrayList arrayList = new ArrayList();
        List list = f16515a;
        synchronized (list) {
            arrayList.addAll(list);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((Callback) it.next()).onShowTipAnimation(d2);
        }
    }

    default void f(AbsGameAssistToken.FocuesWindow focuesWindow) {
        ArrayList arrayList = new ArrayList();
        List list = f16515a;
        synchronized (list) {
            arrayList.addAll(list);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((Callback) it.next()).onFocuesWindowChanged(focuesWindow);
        }
    }

    boolean g();

    default void h(Callback callback) {
        List list = f16515a;
        synchronized (list) {
            try {
                if (!list.contains(callback)) {
                    list.add(callback);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (g()) {
            callback.y();
        } else {
            callback.z();
        }
    }

    default void i(Callback callback) {
        List list = f16515a;
        synchronized (list) {
            try {
                if (list.contains(callback)) {
                    list.remove(callback);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    default void j(String str) {
        ArrayList arrayList = new ArrayList();
        List list = f16515a;
        synchronized (list) {
            arrayList.addAll(list);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((Callback) it.next()).onLauncherFirstPackage(str);
        }
    }

    default void k(ComponentName componentName, int i2) {
        ArrayList arrayList = new ArrayList();
        List list = f16515a;
        synchronized (list) {
            arrayList.addAll(list);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((Callback) it.next()).onProjectionActivityResumed(componentName, i2);
        }
    }

    default void l() {
        ArrayList arrayList = new ArrayList();
        List list = f16515a;
        synchronized (list) {
            arrayList.addAll(list);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((Callback) it.next()).onResumeFullscreenActivityPidChanged();
        }
    }
}
