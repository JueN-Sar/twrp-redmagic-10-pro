package androidx.core.app;

import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobServiceEngine;
import android.app.job.JobWorkItem;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.PowerManager;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.HashMap;

@Deprecated
/* loaded from: classes.dex */
public abstract class JobIntentService extends Service {

    /* renamed from: n, reason: collision with root package name */
    static final Object f2647n = new Object();

    /* renamed from: o, reason: collision with root package name */
    static final HashMap f2648o = new HashMap();

    /* renamed from: c, reason: collision with root package name */
    CompatJobEngine f2649c;

    /* renamed from: h, reason: collision with root package name */
    WorkEnqueuer f2650h;

    /* renamed from: i, reason: collision with root package name */
    CommandProcessor f2651i;

    /* renamed from: j, reason: collision with root package name */
    boolean f2652j = false;

    /* renamed from: k, reason: collision with root package name */
    boolean f2653k = false;

    /* renamed from: l, reason: collision with root package name */
    boolean f2654l = false;

    /* renamed from: m, reason: collision with root package name */
    final ArrayList f2655m = null;

    final class CommandProcessor extends AsyncTask<Void, Void, Void> {
        CommandProcessor() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            while (true) {
                GenericWorkItem a2 = JobIntentService.this.a();
                if (a2 == null) {
                    return null;
                }
                JobIntentService.this.d(a2.getIntent());
                a2.a();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onCancelled(Void r1) {
            JobIntentService.this.f();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Void r1) {
            JobIntentService.this.f();
        }
    }

    interface CompatJobEngine {
        IBinder a();

        GenericWorkItem b();
    }

    static final class CompatWorkEnqueuer extends WorkEnqueuer {

        /* renamed from: a, reason: collision with root package name */
        private final PowerManager.WakeLock f2657a;

        /* renamed from: b, reason: collision with root package name */
        private final PowerManager.WakeLock f2658b;

        /* renamed from: c, reason: collision with root package name */
        boolean f2659c;

        /* renamed from: d, reason: collision with root package name */
        boolean f2660d;

        @Override // androidx.core.app.JobIntentService.WorkEnqueuer
        public void a() {
            synchronized (this) {
                try {
                    if (this.f2660d) {
                        if (this.f2659c) {
                            this.f2657a.acquire(60000L);
                        }
                        this.f2660d = false;
                        this.f2658b.release();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // androidx.core.app.JobIntentService.WorkEnqueuer
        public void b() {
            synchronized (this) {
                try {
                    if (!this.f2660d) {
                        this.f2660d = true;
                        this.f2658b.acquire(600000L);
                        this.f2657a.release();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // androidx.core.app.JobIntentService.WorkEnqueuer
        public void c() {
            synchronized (this) {
                this.f2659c = false;
            }
        }
    }

    final class CompatWorkItem implements GenericWorkItem {

        /* renamed from: a, reason: collision with root package name */
        final Intent f2661a;

        /* renamed from: b, reason: collision with root package name */
        final int f2662b;

        CompatWorkItem(Intent intent, int i2) {
            this.f2661a = intent;
            this.f2662b = i2;
        }

        @Override // androidx.core.app.JobIntentService.GenericWorkItem
        public void a() {
            JobIntentService.this.stopSelf(this.f2662b);
        }

        @Override // androidx.core.app.JobIntentService.GenericWorkItem
        public Intent getIntent() {
            return this.f2661a;
        }
    }

    interface GenericWorkItem {
        void a();

        Intent getIntent();
    }

    @RequiresApi
    static final class JobServiceEngineImpl extends JobServiceEngine implements CompatJobEngine {

        /* renamed from: a, reason: collision with root package name */
        final JobIntentService f2664a;

        /* renamed from: b, reason: collision with root package name */
        final Object f2665b;

        /* renamed from: c, reason: collision with root package name */
        JobParameters f2666c;

        final class WrapperWorkItem implements GenericWorkItem {

            /* renamed from: a, reason: collision with root package name */
            final JobWorkItem f2667a;

            WrapperWorkItem(JobWorkItem jobWorkItem) {
                this.f2667a = jobWorkItem;
            }

            @Override // androidx.core.app.JobIntentService.GenericWorkItem
            public void a() {
                synchronized (JobServiceEngineImpl.this.f2665b) {
                    try {
                        JobParameters jobParameters = JobServiceEngineImpl.this.f2666c;
                        if (jobParameters != null) {
                            jobParameters.completeWork(this.f2667a);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }

            @Override // androidx.core.app.JobIntentService.GenericWorkItem
            public Intent getIntent() {
                return this.f2667a.getIntent();
            }
        }

        JobServiceEngineImpl(JobIntentService jobIntentService) {
            super(jobIntentService);
            this.f2665b = new Object();
            this.f2664a = jobIntentService;
        }

        @Override // androidx.core.app.JobIntentService.CompatJobEngine
        public IBinder a() {
            return getBinder();
        }

        @Override // androidx.core.app.JobIntentService.CompatJobEngine
        public GenericWorkItem b() {
            synchronized (this.f2665b) {
                try {
                    JobParameters jobParameters = this.f2666c;
                    if (jobParameters == null) {
                        return null;
                    }
                    JobWorkItem dequeueWork = jobParameters.dequeueWork();
                    if (dequeueWork == null) {
                        return null;
                    }
                    dequeueWork.getIntent().setExtrasClassLoader(this.f2664a.getClassLoader());
                    return new WrapperWorkItem(dequeueWork);
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // android.app.job.JobServiceEngine
        public boolean onStartJob(JobParameters jobParameters) {
            this.f2666c = jobParameters;
            this.f2664a.c(false);
            return true;
        }

        @Override // android.app.job.JobServiceEngine
        public boolean onStopJob(JobParameters jobParameters) {
            boolean b2 = this.f2664a.b();
            synchronized (this.f2665b) {
                this.f2666c = null;
            }
            return b2;
        }
    }

    @RequiresApi
    static final class JobWorkEnqueuer extends WorkEnqueuer {
    }

    static abstract class WorkEnqueuer {
        public void a() {
        }

        public void b() {
        }

        public void c() {
        }
    }

    GenericWorkItem a() {
        CompatJobEngine compatJobEngine = this.f2649c;
        if (compatJobEngine != null) {
            return compatJobEngine.b();
        }
        synchronized (this.f2655m) {
            try {
                if (this.f2655m.size() <= 0) {
                    return null;
                }
                return (GenericWorkItem) this.f2655m.remove(0);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    boolean b() {
        CommandProcessor commandProcessor = this.f2651i;
        if (commandProcessor != null) {
            commandProcessor.cancel(this.f2652j);
        }
        this.f2653k = true;
        return e();
    }

    void c(boolean z) {
        if (this.f2651i == null) {
            this.f2651i = new CommandProcessor();
            WorkEnqueuer workEnqueuer = this.f2650h;
            if (workEnqueuer != null && z) {
                workEnqueuer.b();
            }
            this.f2651i.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    protected abstract void d(Intent intent);

    public boolean e() {
        return true;
    }

    void f() {
        ArrayList arrayList = this.f2655m;
        if (arrayList != null) {
            synchronized (arrayList) {
                try {
                    this.f2651i = null;
                    ArrayList arrayList2 = this.f2655m;
                    if (arrayList2 != null && arrayList2.size() > 0) {
                        c(false);
                    } else if (!this.f2654l) {
                        this.f2650h.a();
                    }
                } finally {
                }
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(@NonNull Intent intent) {
        CompatJobEngine compatJobEngine = this.f2649c;
        if (compatJobEngine != null) {
            return compatJobEngine.a();
        }
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.f2649c = new JobServiceEngineImpl(this);
        this.f2650h = null;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        ArrayList arrayList = this.f2655m;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.f2654l = true;
                this.f2650h.a();
            }
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        if (this.f2655m == null) {
            return 2;
        }
        this.f2650h.c();
        synchronized (this.f2655m) {
            ArrayList arrayList = this.f2655m;
            if (intent == null) {
                intent = new Intent();
            }
            arrayList.add(new CompatWorkItem(intent, i3));
            c(true);
        }
        return 3;
    }
}
