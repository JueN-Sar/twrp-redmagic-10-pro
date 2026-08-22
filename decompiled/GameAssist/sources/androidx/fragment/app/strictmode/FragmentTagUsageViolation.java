package androidx.fragment.app.strictmode;

import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata
/* loaded from: classes.dex */
public final class FragmentTagUsageViolation extends Violation {

    @Nullable
    private final ViewGroup parentContainer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentTagUsageViolation(@NotNull Fragment fragment, @Nullable ViewGroup viewGroup) {
        super(fragment, "Attempting to use <fragment> tag to add fragment " + fragment + " to container " + viewGroup);
        Intrinsics.e(fragment, "fragment");
        this.parentContainer = viewGroup;
    }
}
