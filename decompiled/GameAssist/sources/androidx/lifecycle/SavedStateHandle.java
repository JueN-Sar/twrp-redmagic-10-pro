package androidx.lifecycle;

import android.os.Binder;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import androidx.core.os.BundleKt;
import androidx.savedstate.SavedStateRegistry;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.MutableStateFlow;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class SavedStateHandle {

    /* renamed from: f, reason: collision with root package name */
    public static final Companion f4356f = new Companion(null);

    /* renamed from: g, reason: collision with root package name */
    private static final Class[] f4357g = {Boolean.TYPE, boolean[].class, Double.TYPE, double[].class, Integer.TYPE, int[].class, Long.TYPE, long[].class, String.class, String[].class, Binder.class, Bundle.class, Byte.TYPE, byte[].class, Character.TYPE, char[].class, CharSequence.class, CharSequence[].class, ArrayList.class, Float.TYPE, float[].class, Parcelable.class, Parcelable[].class, Serializable.class, Short.TYPE, short[].class, SparseArray.class, Size.class, SizeF.class};

    /* renamed from: a, reason: collision with root package name */
    private final Map f4358a;

    /* renamed from: b, reason: collision with root package name */
    private final Map f4359b;

    /* renamed from: c, reason: collision with root package name */
    private final Map f4360c;

    /* renamed from: d, reason: collision with root package name */
    private final Map f4361d;

    /* renamed from: e, reason: collision with root package name */
    private final SavedStateRegistry.SavedStateProvider f4362e;

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public final SavedStateHandle a(Bundle bundle, Bundle bundle2) {
            if (bundle == null) {
                if (bundle2 == null) {
                    return new SavedStateHandle();
                }
                HashMap hashMap = new HashMap();
                for (String key : bundle2.keySet()) {
                    Intrinsics.d(key, "key");
                    hashMap.put(key, bundle2.get(key));
                }
                return new SavedStateHandle(hashMap);
            }
            ArrayList parcelableArrayList = bundle.getParcelableArrayList("keys");
            ArrayList parcelableArrayList2 = bundle.getParcelableArrayList("values");
            if (parcelableArrayList == null || parcelableArrayList2 == null || parcelableArrayList.size() != parcelableArrayList2.size()) {
                throw new IllegalStateException("Invalid bundle passed as restored state".toString());
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            int size = parcelableArrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                Object obj = parcelableArrayList.get(i2);
                Intrinsics.c(obj, "null cannot be cast to non-null type kotlin.String");
                linkedHashMap.put((String) obj, parcelableArrayList2.get(i2));
            }
            return new SavedStateHandle(linkedHashMap);
        }

        public final boolean b(Object obj) {
            if (obj == null) {
                return true;
            }
            for (Class cls : SavedStateHandle.f4357g) {
                Intrinsics.b(cls);
                if (cls.isInstance(obj)) {
                    return true;
                }
            }
            return false;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata
    public static final class SavingStateLiveData<T> extends MutableLiveData<T> {

        /* renamed from: l, reason: collision with root package name */
        private String f4363l;

        /* renamed from: m, reason: collision with root package name */
        private SavedStateHandle f4364m;

        @Override // androidx.lifecycle.MutableLiveData, androidx.lifecycle.LiveData
        public void o(Object obj) {
            SavedStateHandle savedStateHandle = this.f4364m;
            if (savedStateHandle != null) {
                savedStateHandle.f4358a.put(this.f4363l, obj);
                MutableStateFlow mutableStateFlow = (MutableStateFlow) savedStateHandle.f4361d.get(this.f4363l);
                if (mutableStateFlow != null) {
                    mutableStateFlow.setValue(obj);
                }
            }
            super.o(obj);
        }
    }

    public SavedStateHandle(Map initialState) {
        Intrinsics.e(initialState, "initialState");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.f4358a = linkedHashMap;
        this.f4359b = new LinkedHashMap();
        this.f4360c = new LinkedHashMap();
        this.f4361d = new LinkedHashMap();
        this.f4362e = new SavedStateRegistry.SavedStateProvider() { // from class: androidx.lifecycle.d
            @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
            public final Bundle a() {
                Bundle f2;
                f2 = SavedStateHandle.f(SavedStateHandle.this);
                return f2;
            }
        };
        linkedHashMap.putAll(initialState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Bundle f(SavedStateHandle this$0) {
        Map k2;
        Intrinsics.e(this$0, "this$0");
        k2 = MapsKt__MapsKt.k(this$0.f4359b);
        for (Map.Entry entry : k2.entrySet()) {
            this$0.g((String) entry.getKey(), ((SavedStateRegistry.SavedStateProvider) entry.getValue()).a());
        }
        Set<String> keySet = this$0.f4358a.keySet();
        ArrayList arrayList = new ArrayList(keySet.size());
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        for (String str : keySet) {
            arrayList.add(str);
            arrayList2.add(this$0.f4358a.get(str));
        }
        return BundleKt.a(TuplesKt.a("keys", arrayList), TuplesKt.a("values", arrayList2));
    }

    public final SavedStateRegistry.SavedStateProvider e() {
        return this.f4362e;
    }

    public final void g(String key, Object obj) {
        Intrinsics.e(key, "key");
        if (!f4356f.b(obj)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Can't put value with type ");
            Intrinsics.b(obj);
            sb.append(obj.getClass());
            sb.append(" into saved state");
            throw new IllegalArgumentException(sb.toString());
        }
        Object obj2 = this.f4360c.get(key);
        MutableLiveData mutableLiveData = obj2 instanceof MutableLiveData ? (MutableLiveData) obj2 : null;
        if (mutableLiveData != null) {
            mutableLiveData.o(obj);
        } else {
            this.f4358a.put(key, obj);
        }
        MutableStateFlow mutableStateFlow = (MutableStateFlow) this.f4361d.get(key);
        if (mutableStateFlow == null) {
            return;
        }
        mutableStateFlow.setValue(obj);
    }

    public SavedStateHandle() {
        this.f4358a = new LinkedHashMap();
        this.f4359b = new LinkedHashMap();
        this.f4360c = new LinkedHashMap();
        this.f4361d = new LinkedHashMap();
        this.f4362e = new SavedStateRegistry.SavedStateProvider() { // from class: androidx.lifecycle.d
            @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
            public final Bundle a() {
                Bundle f2;
                f2 = SavedStateHandle.f(SavedStateHandle.this);
                return f2;
            }
        };
    }
}
