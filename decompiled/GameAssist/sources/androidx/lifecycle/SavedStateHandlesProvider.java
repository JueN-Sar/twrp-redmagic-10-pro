package androidx.lifecycle;

import android.os.Bundle;
import androidx.savedstate.SavedStateRegistry;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class SavedStateHandlesProvider implements SavedStateRegistry.SavedStateProvider {

    /* renamed from: a, reason: collision with root package name */
    private final SavedStateRegistry f4372a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f4373b;

    /* renamed from: c, reason: collision with root package name */
    private Bundle f4374c;

    /* renamed from: d, reason: collision with root package name */
    private final Lazy f4375d;

    public SavedStateHandlesProvider(SavedStateRegistry savedStateRegistry, final ViewModelStoreOwner viewModelStoreOwner) {
        Lazy a2;
        Intrinsics.e(savedStateRegistry, "savedStateRegistry");
        Intrinsics.e(viewModelStoreOwner, "viewModelStoreOwner");
        this.f4372a = savedStateRegistry;
        a2 = LazyKt__LazyJVMKt.a(new Function0<SavedStateHandlesVM>() { // from class: androidx.lifecycle.SavedStateHandlesProvider$viewModel$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public final SavedStateHandlesVM a() {
                return SavedStateHandleSupport.e(ViewModelStoreOwner.this);
            }
        });
        this.f4375d = a2;
    }

    private final SavedStateHandlesVM c() {
        return (SavedStateHandlesVM) this.f4375d.getValue();
    }

    @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
    public Bundle a() {
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.f4374c;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
        for (Map.Entry entry : c().f().entrySet()) {
            String str = (String) entry.getKey();
            Bundle a2 = ((SavedStateHandle) entry.getValue()).e().a();
            if (!Intrinsics.a(a2, Bundle.EMPTY)) {
                bundle.putBundle(str, a2);
            }
        }
        this.f4373b = false;
        return bundle;
    }

    public final Bundle b(String key) {
        Intrinsics.e(key, "key");
        d();
        Bundle bundle = this.f4374c;
        Bundle bundle2 = bundle != null ? bundle.getBundle(key) : null;
        Bundle bundle3 = this.f4374c;
        if (bundle3 != null) {
            bundle3.remove(key);
        }
        Bundle bundle4 = this.f4374c;
        if (bundle4 != null && bundle4.isEmpty()) {
            this.f4374c = null;
        }
        return bundle2;
    }

    public final void d() {
        if (this.f4373b) {
            return;
        }
        Bundle b2 = this.f4372a.b("androidx.lifecycle.internal.SavedStateHandlesProvider");
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.f4374c;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
        if (b2 != null) {
            bundle.putAll(b2);
        }
        this.f4374c = bundle;
        this.f4373b = true;
        c();
    }
}
