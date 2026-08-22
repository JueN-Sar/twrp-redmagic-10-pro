package androidx.lifecycle;

import androidx.lifecycle.viewmodel.CreationExtras;
import kotlin.Metadata;

@Metadata
/* loaded from: classes.dex */
public interface HasDefaultViewModelProviderFactory {
    default CreationExtras s() {
        return CreationExtras.Empty.f4421b;
    }
}
