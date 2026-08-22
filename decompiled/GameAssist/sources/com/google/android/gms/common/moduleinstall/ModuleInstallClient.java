package com.google.android.gms.common.moduleinstall;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.HasApiKey;
import com.google.android.gms.tasks.Task;

/* loaded from: classes.dex */
public interface ModuleInstallClient extends HasApiKey<Api.ApiOptions.NoOptions> {
    Task b(ModuleInstallRequest moduleInstallRequest);
}
