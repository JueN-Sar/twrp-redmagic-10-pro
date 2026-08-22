package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.PersistableBundle;
import android.util.Base64;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.util.PriorityMapping;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.zip.Adler32;

@RequiresApi
/* loaded from: classes.dex */
public class JobInfoScheduler implements WorkScheduler {

    /* renamed from: a, reason: collision with root package name */
    private final Context f10319a;

    /* renamed from: b, reason: collision with root package name */
    private final EventStore f10320b;

    /* renamed from: c, reason: collision with root package name */
    private final SchedulerConfig f10321c;

    public JobInfoScheduler(Context context, EventStore eventStore, SchedulerConfig schedulerConfig) {
        this.f10319a = context;
        this.f10320b = eventStore;
        this.f10321c = schedulerConfig;
    }

    private boolean c(JobScheduler jobScheduler, int i2, int i3) {
        for (JobInfo jobInfo : jobScheduler.getAllPendingJobs()) {
            int i4 = jobInfo.getExtras().getInt("attemptNumber");
            if (jobInfo.getId() == i2) {
                return i4 >= i3;
            }
        }
        return false;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler
    public void a(TransportContext transportContext, int i2) {
        b(transportContext, i2, false);
    }

    @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler
    public void b(TransportContext transportContext, int i2, boolean z) {
        ComponentName componentName = new ComponentName(this.f10319a, (Class<?>) JobInfoSchedulerService.class);
        JobScheduler jobScheduler = (JobScheduler) this.f10319a.getSystemService("jobscheduler");
        int jobId = getJobId(transportContext);
        if (!z && c(jobScheduler, jobId, i2)) {
            Logging.a("JobInfoScheduler", "Upload for context %s is already scheduled. Returning...", transportContext);
            return;
        }
        long K = this.f10320b.K(transportContext);
        JobInfo.Builder c2 = this.f10321c.c(new JobInfo.Builder(jobId, componentName), transportContext.d(), K, i2);
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putInt("attemptNumber", i2);
        persistableBundle.putString("backendName", transportContext.b());
        persistableBundle.putInt("priority", PriorityMapping.a(transportContext.d()));
        if (transportContext.c() != null) {
            persistableBundle.putString("extras", Base64.encodeToString(transportContext.c(), 0));
        }
        c2.setExtras(persistableBundle);
        Logging.b("JobInfoScheduler", "Scheduling upload for context %s with jobId=%d in %dms(Backend next call timestamp %d). Attempt %d", transportContext, Integer.valueOf(jobId), Long.valueOf(this.f10321c.g(transportContext.d(), K, i2)), Long.valueOf(K), Integer.valueOf(i2));
        jobScheduler.schedule(c2.build());
    }

    @VisibleForTesting
    int getJobId(TransportContext transportContext) {
        Adler32 adler32 = new Adler32();
        adler32.update(this.f10319a.getPackageName().getBytes(Charset.forName("UTF-8")));
        adler32.update(transportContext.b().getBytes(Charset.forName("UTF-8")));
        adler32.update(ByteBuffer.allocate(4).putInt(PriorityMapping.a(transportContext.d())).array());
        if (transportContext.c() != null) {
            adler32.update(transportContext.c());
        }
        return (int) adler32.getValue();
    }
}
