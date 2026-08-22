package com.google.android.material.transition.platform;

import android.app.Activity;
import android.app.SharedElementCallback;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.transition.Transition;
import android.view.View;
import android.view.Window;
import androidx.annotation.RequiresApi;
import androidx.core.graphics.BlendModeColorFilterCompat;
import androidx.core.graphics.BlendModeCompat;
import com.google.android.material.R;
import com.google.android.material.internal.ContextUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

@RequiresApi
/* loaded from: classes.dex */
public class MaterialContainerTransformSharedElementCallback extends SharedElementCallback {

    /* renamed from: f, reason: collision with root package name */
    private static WeakReference f15730f;

    /* renamed from: a, reason: collision with root package name */
    private boolean f15731a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f15732b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f15733c;

    /* renamed from: d, reason: collision with root package name */
    private Rect f15734d;

    /* renamed from: e, reason: collision with root package name */
    private ShapeProvider f15735e;

    public interface ShapeProvider {
        ShapeAppearanceModel a(View view);
    }

    public static class ShapeableViewShapeProvider implements ShapeProvider {
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback.ShapeProvider
        public ShapeAppearanceModel a(View view) {
            if (view instanceof Shapeable) {
                return ((Shapeable) view).getShapeAppearanceModel();
            }
            return null;
        }
    }

    private static Drawable e(Window window) {
        return window.getDecorView().getBackground();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void f(Window window) {
        Drawable e2 = e(window);
        if (e2 == null) {
            return;
        }
        e2.mutate().setColorFilter(BlendModeColorFilterCompat.a(0, BlendModeCompat.CLEAR));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void g(Window window) {
        Drawable e2 = e(window);
        if (e2 == null) {
            return;
        }
        e2.mutate().clearColorFilter();
    }

    private void h(final Window window) {
        Transition sharedElementEnterTransition = window.getSharedElementEnterTransition();
        if (sharedElementEnterTransition instanceof MaterialContainerTransform) {
            MaterialContainerTransform materialContainerTransform = (MaterialContainerTransform) sharedElementEnterTransition;
            if (!this.f15733c) {
                window.setSharedElementReenterTransition(null);
            }
            if (this.f15732b) {
                j(window, materialContainerTransform);
                materialContainerTransform.addListener(new TransitionListenerAdapter() { // from class: com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback.1
                    @Override // com.google.android.material.transition.platform.TransitionListenerAdapter, android.transition.Transition.TransitionListener
                    public void onTransitionEnd(Transition transition) {
                        MaterialContainerTransformSharedElementCallback.g(window);
                    }

                    @Override // com.google.android.material.transition.platform.TransitionListenerAdapter, android.transition.Transition.TransitionListener
                    public void onTransitionStart(Transition transition) {
                        MaterialContainerTransformSharedElementCallback.f(window);
                    }
                });
            }
        }
    }

    private void i(final Activity activity, final Window window) {
        Transition sharedElementReturnTransition = window.getSharedElementReturnTransition();
        if (sharedElementReturnTransition instanceof MaterialContainerTransform) {
            MaterialContainerTransform materialContainerTransform = (MaterialContainerTransform) sharedElementReturnTransition;
            materialContainerTransform.m(true);
            materialContainerTransform.addListener(new TransitionListenerAdapter() { // from class: com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback.2
                @Override // com.google.android.material.transition.platform.TransitionListenerAdapter, android.transition.Transition.TransitionListener
                public void onTransitionEnd(Transition transition) {
                    View view;
                    if (MaterialContainerTransformSharedElementCallback.f15730f != null && (view = (View) MaterialContainerTransformSharedElementCallback.f15730f.get()) != null) {
                        view.setAlpha(1.0f);
                        WeakReference unused = MaterialContainerTransformSharedElementCallback.f15730f = null;
                    }
                    activity.finish();
                    activity.overridePendingTransition(0, 0);
                }
            });
            if (this.f15732b) {
                j(window, materialContainerTransform);
                materialContainerTransform.addListener(new TransitionListenerAdapter() { // from class: com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback.3
                    @Override // com.google.android.material.transition.platform.TransitionListenerAdapter, android.transition.Transition.TransitionListener
                    public void onTransitionStart(Transition transition) {
                        MaterialContainerTransformSharedElementCallback.f(window);
                    }
                });
            }
        }
    }

    private static void j(Window window, MaterialContainerTransform materialContainerTransform) {
        if (materialContainerTransform.getDuration() >= 0) {
            window.setTransitionBackgroundFadeDuration(materialContainerTransform.getDuration());
        }
    }

    @Override // android.app.SharedElementCallback
    public Parcelable onCaptureSharedElementSnapshot(View view, Matrix matrix, RectF rectF) {
        f15730f = new WeakReference(view);
        return super.onCaptureSharedElementSnapshot(view, matrix, rectF);
    }

    @Override // android.app.SharedElementCallback
    public View onCreateSnapshotView(Context context, Parcelable parcelable) {
        WeakReference weakReference;
        View view;
        ShapeAppearanceModel a2;
        View onCreateSnapshotView = super.onCreateSnapshotView(context, parcelable);
        if (onCreateSnapshotView != null && (weakReference = f15730f) != null && this.f15735e != null && (view = (View) weakReference.get()) != null && (a2 = this.f15735e.a(view)) != null) {
            onCreateSnapshotView.setTag(R.id.mtrl_motion_snapshot_view, a2);
        }
        return onCreateSnapshotView;
    }

    @Override // android.app.SharedElementCallback
    public void onMapSharedElements(List list, Map map) {
        View view;
        Activity a2;
        if (list.isEmpty() || map.isEmpty() || (view = (View) map.get(list.get(0))) == null || (a2 = ContextUtils.a(view.getContext())) == null) {
            return;
        }
        Window window = a2.getWindow();
        if (this.f15731a) {
            h(window);
        } else {
            i(a2, window);
        }
    }

    @Override // android.app.SharedElementCallback
    public void onSharedElementEnd(List list, List list2, List list3) {
        if (!list2.isEmpty() && (((View) list2.get(0)).getTag(R.id.mtrl_motion_snapshot_view) instanceof View)) {
            ((View) list2.get(0)).setTag(R.id.mtrl_motion_snapshot_view, null);
        }
        if (!this.f15731a && !list2.isEmpty()) {
            this.f15734d = TransitionUtils.j((View) list2.get(0));
        }
        this.f15731a = false;
    }

    @Override // android.app.SharedElementCallback
    public void onSharedElementStart(List list, List list2, List list3) {
        if (!list2.isEmpty() && !list3.isEmpty()) {
            ((View) list2.get(0)).setTag(R.id.mtrl_motion_snapshot_view, list3.get(0));
        }
        if (this.f15731a || list2.isEmpty() || this.f15734d == null) {
            return;
        }
        View view = (View) list2.get(0);
        view.measure(View.MeasureSpec.makeMeasureSpec(this.f15734d.width(), WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME), View.MeasureSpec.makeMeasureSpec(this.f15734d.height(), WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME));
        Rect rect = this.f15734d;
        view.layout(rect.left, rect.top, rect.right, rect.bottom);
    }
}
