package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import kotlin.Metadata;

@Metadata
/* loaded from: classes.dex */
public interface LifecycleEventObserver extends LifecycleObserver {
    void c(LifecycleOwner lifecycleOwner, Lifecycle.Event event);
}
