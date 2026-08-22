package androidx.activity.result;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.random.Random;

/* loaded from: classes.dex */
public abstract class ActivityResultRegistry {

    /* renamed from: a, reason: collision with root package name */
    private final Map f97a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    final Map f98b = new HashMap();

    /* renamed from: c, reason: collision with root package name */
    private final Map f99c = new HashMap();

    /* renamed from: d, reason: collision with root package name */
    ArrayList f100d = new ArrayList();

    /* renamed from: e, reason: collision with root package name */
    final transient Map f101e = new HashMap();

    /* renamed from: f, reason: collision with root package name */
    final Map f102f = new HashMap();

    /* renamed from: g, reason: collision with root package name */
    final Bundle f103g = new Bundle();

    private static class CallbackAndContract<O> {

        /* renamed from: a, reason: collision with root package name */
        final ActivityResultCallback f114a;

        /* renamed from: b, reason: collision with root package name */
        final ActivityResultContract f115b;

        CallbackAndContract(ActivityResultCallback activityResultCallback, ActivityResultContract activityResultContract) {
            this.f114a = activityResultCallback;
            this.f115b = activityResultContract;
        }
    }

    private static class LifecycleContainer {

        /* renamed from: a, reason: collision with root package name */
        final Lifecycle f116a;

        /* renamed from: b, reason: collision with root package name */
        private final ArrayList f117b = new ArrayList();

        LifecycleContainer(Lifecycle lifecycle) {
            this.f116a = lifecycle;
        }

        void a(LifecycleEventObserver lifecycleEventObserver) {
            this.f116a.a(lifecycleEventObserver);
            this.f117b.add(lifecycleEventObserver);
        }

        void b() {
            Iterator it = this.f117b.iterator();
            while (it.hasNext()) {
                this.f116a.c((LifecycleEventObserver) it.next());
            }
            this.f117b.clear();
        }
    }

    private void a(int i2, String str) {
        this.f97a.put(Integer.valueOf(i2), str);
        this.f98b.put(str, Integer.valueOf(i2));
    }

    private void d(String str, int i2, Intent intent, CallbackAndContract callbackAndContract) {
        if (callbackAndContract == null || callbackAndContract.f114a == null || !this.f100d.contains(str)) {
            this.f102f.remove(str);
            this.f103g.putParcelable(str, new ActivityResult(i2, intent));
        } else {
            callbackAndContract.f114a.a(callbackAndContract.f115b.c(i2, intent));
            this.f100d.remove(str);
        }
    }

    private int e() {
        int i2 = Random.Default.i(2147418112);
        while (true) {
            int i3 = i2 + 65536;
            if (!this.f97a.containsKey(Integer.valueOf(i3))) {
                return i3;
            }
            i2 = Random.Default.i(2147418112);
        }
    }

    private void k(String str) {
        if (((Integer) this.f98b.get(str)) != null) {
            return;
        }
        a(e(), str);
    }

    public final boolean b(int i2, int i3, Intent intent) {
        String str = (String) this.f97a.get(Integer.valueOf(i2));
        if (str == null) {
            return false;
        }
        d(str, i3, intent, (CallbackAndContract) this.f101e.get(str));
        return true;
    }

    public final boolean c(int i2, Object obj) {
        ActivityResultCallback activityResultCallback;
        String str = (String) this.f97a.get(Integer.valueOf(i2));
        if (str == null) {
            return false;
        }
        CallbackAndContract callbackAndContract = (CallbackAndContract) this.f101e.get(str);
        if (callbackAndContract == null || (activityResultCallback = callbackAndContract.f114a) == null) {
            this.f103g.remove(str);
            this.f102f.put(str, obj);
            return true;
        }
        if (!this.f100d.remove(str)) {
            return true;
        }
        activityResultCallback.a(obj);
        return true;
    }

    public abstract void f(int i2, ActivityResultContract activityResultContract, Object obj, ActivityOptionsCompat activityOptionsCompat);

    public final void g(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        ArrayList<Integer> integerArrayList = bundle.getIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS");
        ArrayList<String> stringArrayList = bundle.getStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS");
        if (stringArrayList == null || integerArrayList == null) {
            return;
        }
        this.f100d = bundle.getStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS");
        this.f103g.putAll(bundle.getBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT"));
        for (int i2 = 0; i2 < stringArrayList.size(); i2++) {
            String str = stringArrayList.get(i2);
            if (this.f98b.containsKey(str)) {
                Integer num = (Integer) this.f98b.remove(str);
                if (!this.f103g.containsKey(str)) {
                    this.f97a.remove(num);
                }
            }
            a(integerArrayList.get(i2).intValue(), stringArrayList.get(i2));
        }
    }

    public final void h(Bundle bundle) {
        bundle.putIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS", new ArrayList<>(this.f98b.values()));
        bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS", new ArrayList<>(this.f98b.keySet()));
        bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS", new ArrayList<>(this.f100d));
        bundle.putBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT", (Bundle) this.f103g.clone());
    }

    public final ActivityResultLauncher i(final String str, final ActivityResultContract activityResultContract, ActivityResultCallback activityResultCallback) {
        k(str);
        this.f101e.put(str, new CallbackAndContract(activityResultCallback, activityResultContract));
        if (this.f102f.containsKey(str)) {
            Object obj = this.f102f.get(str);
            this.f102f.remove(str);
            activityResultCallback.a(obj);
        }
        ActivityResult activityResult = (ActivityResult) this.f103g.getParcelable(str);
        if (activityResult != null) {
            this.f103g.remove(str);
            activityResultCallback.a(activityResultContract.c(activityResult.b(), activityResult.a()));
        }
        return new ActivityResultLauncher<Object>() { // from class: androidx.activity.result.ActivityResultRegistry.3
            @Override // androidx.activity.result.ActivityResultLauncher
            public void b(Object obj2, ActivityOptionsCompat activityOptionsCompat) {
                Integer num = (Integer) ActivityResultRegistry.this.f98b.get(str);
                if (num != null) {
                    ActivityResultRegistry.this.f100d.add(str);
                    try {
                        ActivityResultRegistry.this.f(num.intValue(), activityResultContract, obj2, activityOptionsCompat);
                        return;
                    } catch (Exception e2) {
                        ActivityResultRegistry.this.f100d.remove(str);
                        throw e2;
                    }
                }
                throw new IllegalStateException("Attempting to launch an unregistered ActivityResultLauncher with contract " + activityResultContract + " and input " + obj2 + ". You must ensure the ActivityResultLauncher is registered before calling launch().");
            }

            @Override // androidx.activity.result.ActivityResultLauncher
            public void c() {
                ActivityResultRegistry.this.l(str);
            }
        };
    }

    public final ActivityResultLauncher j(final String str, LifecycleOwner lifecycleOwner, final ActivityResultContract activityResultContract, final ActivityResultCallback activityResultCallback) {
        Lifecycle a2 = lifecycleOwner.a();
        if (a2.b().d(Lifecycle.State.STARTED)) {
            throw new IllegalStateException("LifecycleOwner " + lifecycleOwner + " is attempting to register while current state is " + a2.b() + ". LifecycleOwners must call register before they are STARTED.");
        }
        k(str);
        LifecycleContainer lifecycleContainer = (LifecycleContainer) this.f99c.get(str);
        if (lifecycleContainer == null) {
            lifecycleContainer = new LifecycleContainer(a2);
        }
        lifecycleContainer.a(new LifecycleEventObserver() { // from class: androidx.activity.result.ActivityResultRegistry.1
            @Override // androidx.lifecycle.LifecycleEventObserver
            public void c(LifecycleOwner lifecycleOwner2, Lifecycle.Event event) {
                if (!Lifecycle.Event.ON_START.equals(event)) {
                    if (Lifecycle.Event.ON_STOP.equals(event)) {
                        ActivityResultRegistry.this.f101e.remove(str);
                        return;
                    } else {
                        if (Lifecycle.Event.ON_DESTROY.equals(event)) {
                            ActivityResultRegistry.this.l(str);
                            return;
                        }
                        return;
                    }
                }
                ActivityResultRegistry.this.f101e.put(str, new CallbackAndContract(activityResultCallback, activityResultContract));
                if (ActivityResultRegistry.this.f102f.containsKey(str)) {
                    Object obj = ActivityResultRegistry.this.f102f.get(str);
                    ActivityResultRegistry.this.f102f.remove(str);
                    activityResultCallback.a(obj);
                }
                ActivityResult activityResult = (ActivityResult) ActivityResultRegistry.this.f103g.getParcelable(str);
                if (activityResult != null) {
                    ActivityResultRegistry.this.f103g.remove(str);
                    activityResultCallback.a(activityResultContract.c(activityResult.b(), activityResult.a()));
                }
            }
        });
        this.f99c.put(str, lifecycleContainer);
        return new ActivityResultLauncher<Object>() { // from class: androidx.activity.result.ActivityResultRegistry.2
            @Override // androidx.activity.result.ActivityResultLauncher
            public void b(Object obj, ActivityOptionsCompat activityOptionsCompat) {
                Integer num = (Integer) ActivityResultRegistry.this.f98b.get(str);
                if (num != null) {
                    ActivityResultRegistry.this.f100d.add(str);
                    try {
                        ActivityResultRegistry.this.f(num.intValue(), activityResultContract, obj, activityOptionsCompat);
                        return;
                    } catch (Exception e2) {
                        ActivityResultRegistry.this.f100d.remove(str);
                        throw e2;
                    }
                }
                throw new IllegalStateException("Attempting to launch an unregistered ActivityResultLauncher with contract " + activityResultContract + " and input " + obj + ". You must ensure the ActivityResultLauncher is registered before calling launch().");
            }

            @Override // androidx.activity.result.ActivityResultLauncher
            public void c() {
                ActivityResultRegistry.this.l(str);
            }
        };
    }

    final void l(String str) {
        Integer num;
        if (!this.f100d.contains(str) && (num = (Integer) this.f98b.remove(str)) != null) {
            this.f97a.remove(num);
        }
        this.f101e.remove(str);
        if (this.f102f.containsKey(str)) {
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + str + ": " + this.f102f.get(str));
            this.f102f.remove(str);
        }
        if (this.f103g.containsKey(str)) {
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + str + ": " + this.f103g.getParcelable(str));
            this.f103g.remove(str);
        }
        LifecycleContainer lifecycleContainer = (LifecycleContainer) this.f99c.get(str);
        if (lifecycleContainer != null) {
            lifecycleContainer.b();
            this.f99c.remove(str);
        }
    }
}
