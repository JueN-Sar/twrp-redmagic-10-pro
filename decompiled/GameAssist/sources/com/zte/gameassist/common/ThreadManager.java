package com.zte.gameassist.common;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes2.dex */
public class ThreadManager {

    /* renamed from: j, reason: collision with root package name */
    private static volatile ThreadManager f16579j;

    /* renamed from: k, reason: collision with root package name */
    public static final Handler f16580k = new Handler(Looper.getMainLooper());

    /* renamed from: a, reason: collision with root package name */
    private HandlerThread f16581a;

    /* renamed from: b, reason: collision with root package name */
    private HandlerThread f16582b;

    /* renamed from: c, reason: collision with root package name */
    private HandlerThread f16583c;

    /* renamed from: d, reason: collision with root package name */
    private HandlerThread f16584d;

    /* renamed from: e, reason: collision with root package name */
    private HandlerThread f16585e;

    /* renamed from: f, reason: collision with root package name */
    private HandlerThread f16586f;

    /* renamed from: g, reason: collision with root package name */
    private HandlerThread f16587g;

    /* renamed from: h, reason: collision with root package name */
    private HandlerThread f16588h;

    /* renamed from: i, reason: collision with root package name */
    private ExecutorService f16589i;

    public interface Action<T> {
        Object a();
    }

    private static final class ActionTask<T> extends WaitTask<T> {

        /* renamed from: h, reason: collision with root package name */
        private final Action f16590h;

        /* renamed from: i, reason: collision with root package name */
        private Object f16591i;

        @Override // com.zte.gameassist.common.ThreadManager.WaitTask, java.lang.Runnable
        public void run() {
            try {
                this.f16591i = this.f16590h.a();
            } finally {
                super.run();
            }
        }
    }

    private static final class VoidTask extends WaitTask<Boolean> {

        /* renamed from: h, reason: collision with root package name */
        private final Runnable f16592h;

        @Override // com.zte.gameassist.common.ThreadManager.WaitTask, java.lang.Runnable
        public void run() {
            try {
                this.f16592h.run();
            } finally {
                super.run();
            }
        }
    }

    private static abstract class WaitTask<T> implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        protected boolean f16593c;

        @Override // java.lang.Runnable
        public void run() {
            synchronized (this) {
                this.f16593c = true;
                notifyAll();
            }
        }
    }

    private ThreadManager() {
        HandlerThread handlerThread = new HandlerThread("TileThread", -2);
        this.f16581a = handlerThread;
        handlerThread.start();
        HandlerThread handlerThread2 = new HandlerThread("ToolThread", -2);
        this.f16582b = handlerThread2;
        handlerThread2.start();
        HandlerThread handlerThread3 = new HandlerThread("DatabaseThread", -2);
        this.f16583c = handlerThread3;
        handlerThread3.start();
        HandlerThread handlerThread4 = new HandlerThread("TileControl", -4);
        this.f16584d = handlerThread4;
        handlerThread4.start();
        HandlerThread handlerThread5 = new HandlerThread("background", -4);
        this.f16585e = handlerThread5;
        handlerThread5.start();
        HandlerThread handlerThread6 = new HandlerThread("MultiSubScreen", -2);
        this.f16586f = handlerThread6;
        handlerThread6.start();
        HandlerThread handlerThread7 = new HandlerThread("LowSugarGameplay", -2);
        this.f16587g = handlerThread7;
        handlerThread7.start();
        HandlerThread handlerThread8 = new HandlerThread("Network", -2);
        this.f16588h = handlerThread8;
        handlerThread8.start();
        this.f16589i = Executors.newCachedThreadPool();
    }

    public static ThreadManager c() {
        if (f16579j == null) {
            synchronized (ThreadManager.class) {
                try {
                    if (f16579j == null) {
                        f16579j = new ThreadManager();
                    }
                } finally {
                }
            }
        }
        return f16579j;
    }

    public Looper a() {
        return this.f16585e.getLooper();
    }

    public Looper b() {
        return this.f16583c.getLooper();
    }

    public Looper d() {
        return this.f16587g.getLooper();
    }

    public Looper e() {
        return Looper.getMainLooper();
    }

    public Looper f() {
        return this.f16586f.getLooper();
    }

    public Looper g() {
        return this.f16588h.getLooper();
    }

    public Looper h() {
        return this.f16584d.getLooper();
    }

    public Looper i() {
        return this.f16581a.getLooper();
    }

    public Looper j() {
        return this.f16582b.getLooper();
    }
}
