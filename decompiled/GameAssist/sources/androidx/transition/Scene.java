package androidx.transition;

import android.view.ViewGroup;

/* loaded from: classes.dex */
public class Scene {

    /* renamed from: a, reason: collision with root package name */
    private ViewGroup f5504a;

    /* renamed from: b, reason: collision with root package name */
    private Runnable f5505b;

    public static Scene b(ViewGroup viewGroup) {
        return (Scene) viewGroup.getTag(R.id.transition_current_scene);
    }

    static void c(ViewGroup viewGroup, Scene scene) {
        viewGroup.setTag(R.id.transition_current_scene, scene);
    }

    public void a() {
        Runnable runnable;
        if (b(this.f5504a) != this || (runnable = this.f5505b) == null) {
            return;
        }
        runnable.run();
    }
}
