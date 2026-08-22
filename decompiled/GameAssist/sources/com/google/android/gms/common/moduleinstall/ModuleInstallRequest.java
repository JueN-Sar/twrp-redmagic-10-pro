package com.google.android.gms.common.moduleinstall;

import com.google.android.gms.common.api.OptionalModuleApi;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public final class ModuleInstallRequest {

    /* renamed from: a, reason: collision with root package name */
    private final List f11139a;

    /* renamed from: b, reason: collision with root package name */
    private final InstallStatusListener f11140b;

    /* renamed from: c, reason: collision with root package name */
    private final Executor f11141c;

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private final List f11142a = new ArrayList();

        /* renamed from: b, reason: collision with root package name */
        private InstallStatusListener f11143b;

        /* renamed from: c, reason: collision with root package name */
        private Executor f11144c;

        public Builder a(OptionalModuleApi optionalModuleApi) {
            this.f11142a.add(optionalModuleApi);
            return this;
        }

        public ModuleInstallRequest b() {
            return new ModuleInstallRequest(this.f11142a, this.f11143b, this.f11144c, true, null);
        }
    }

    /* synthetic */ ModuleInstallRequest(List list, InstallStatusListener installStatusListener, Executor executor, boolean z, zac zacVar) {
        Preconditions.j(list, "APIs must not be null.");
        Preconditions.b(!list.isEmpty(), "APIs must not be empty.");
        if (executor != null) {
            Preconditions.j(installStatusListener, "Listener must not be null when listener executor is set.");
        }
        this.f11139a = list;
        this.f11140b = installStatusListener;
        this.f11141c = executor;
    }

    public static Builder d() {
        return new Builder();
    }

    public List a() {
        return this.f11139a;
    }

    public InstallStatusListener b() {
        return this.f11140b;
    }

    public Executor c() {
        return this.f11141c;
    }
}
