package androidx.savedstate;

import androidx.lifecycle.LifecycleOwner;
import kotlin.Metadata;

@Metadata
/* loaded from: classes.dex */
public interface SavedStateRegistryOwner extends LifecycleOwner {
    SavedStateRegistry i();
}
