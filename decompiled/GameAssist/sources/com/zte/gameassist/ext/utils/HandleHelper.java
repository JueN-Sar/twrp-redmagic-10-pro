package com.zte.gameassist.ext.utils;

/* loaded from: classes2.dex */
public class HandleHelper {

    public interface Action<T> {
        Object a();
    }

    public static final class ActionTask<T> extends WaitTask<T> {

        /* renamed from: h, reason: collision with root package name */
        private final Action f16680h;

        /* renamed from: i, reason: collision with root package name */
        private Object f16681i;

        @Override // com.zte.gameassist.ext.utils.HandleHelper.WaitTask, java.lang.Runnable
        public void run() {
            try {
                this.f16681i = this.f16680h.a();
            } finally {
                super.run();
            }
        }
    }

    private static final class VoidTask extends WaitTask<Boolean> {

        /* renamed from: h, reason: collision with root package name */
        private final Runnable f16682h;

        @Override // com.zte.gameassist.ext.utils.HandleHelper.WaitTask, java.lang.Runnable
        public void run() {
            try {
                this.f16682h.run();
            } finally {
                super.run();
            }
        }
    }

    public static abstract class WaitTask<T> implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        protected boolean f16683c;

        @Override // java.lang.Runnable
        public void run() {
            synchronized (this) {
                this.f16683c = true;
                notifyAll();
            }
        }
    }
}
