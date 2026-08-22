package androidx.lifecycle;

import android.content.Context;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.startup.AppInitializer;
import androidx.startup.Initializer;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes.dex */
public final class ProcessLifecycleInitializer implements Initializer<LifecycleOwner> {
    @Override // androidx.startup.Initializer
    public List a() {
        return CollectionsKt__CollectionsKt.g();
    }

    @Override // androidx.startup.Initializer
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public LifecycleOwner create(Context context) {
        Intrinsics.e(context, "context");
        AppInitializer e2 = AppInitializer.e(context);
        Intrinsics.d(e2, "getInstance(context)");
        if (!e2.g(ProcessLifecycleInitializer.class)) {
            throw new IllegalStateException("ProcessLifecycleInitializer cannot be initialized lazily.\n               Please ensure that you have:\n               <meta-data\n                   android:name='androidx.lifecycle.ProcessLifecycleInitializer'\n                   android:value='androidx.startup' />\n               under InitializationProvider in your AndroidManifest.xml".toString());
        }
        LifecycleDispatcher.a(context);
        ProcessLifecycleOwner.Companion companion = ProcessLifecycleOwner.f4340o;
        companion.b(context);
        return companion.a();
    }
}
