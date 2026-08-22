package androidx.fragment.app.strictmode;

import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes.dex */
public final class WrongFragmentContainerViolation extends Violation {

    @NotNull
    private final ViewGroup container;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WrongFragmentContainerViolation(@NotNull Fragment fragment, @NotNull ViewGroup container) {
        super(fragment, "Attempting to add fragment " + fragment + " to container " + container + " which is not a FragmentContainerView");
        Intrinsics.e(fragment, "fragment");
        Intrinsics.e(container, "container");
        this.container = container;
    }
}
