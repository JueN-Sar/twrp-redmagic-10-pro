package androidx.lifecycle;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes.dex */
public class ViewModelStore {

    /* renamed from: a, reason: collision with root package name */
    private final Map f4413a = new LinkedHashMap();

    public final void a() {
        Iterator it = this.f4413a.values().iterator();
        while (it.hasNext()) {
            ((ViewModel) it.next()).a();
        }
        this.f4413a.clear();
    }

    public final ViewModel b(String key) {
        Intrinsics.e(key, "key");
        return (ViewModel) this.f4413a.get(key);
    }

    public final Set c() {
        return new HashSet(this.f4413a.keySet());
    }

    public final void d(String key, ViewModel viewModel) {
        Intrinsics.e(key, "key");
        Intrinsics.e(viewModel, "viewModel");
        ViewModel viewModel2 = (ViewModel) this.f4413a.put(key, viewModel);
        if (viewModel2 != null) {
            viewModel2.d();
        }
    }
}
