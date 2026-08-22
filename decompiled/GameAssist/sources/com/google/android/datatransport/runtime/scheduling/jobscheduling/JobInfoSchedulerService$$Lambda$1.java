package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.app.job.JobParameters;

/* loaded from: classes.dex */
final /* synthetic */ class JobInfoSchedulerService$$Lambda$1 implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    private final JobInfoSchedulerService f10322c;

    /* renamed from: h, reason: collision with root package name */
    private final JobParameters f10323h;

    private JobInfoSchedulerService$$Lambda$1(JobInfoSchedulerService jobInfoSchedulerService, JobParameters jobParameters) {
        this.f10322c = jobInfoSchedulerService;
        this.f10323h = jobParameters;
    }

    public static Runnable a(JobInfoSchedulerService jobInfoSchedulerService, JobParameters jobParameters) {
        return new JobInfoSchedulerService$$Lambda$1(jobInfoSchedulerService, jobParameters);
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f10322c.jobFinished(this.f10323h, false);
    }
}
