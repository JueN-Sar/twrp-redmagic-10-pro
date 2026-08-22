package androidx.transition;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import java.util.ArrayList;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
class GhostViewHolder extends FrameLayout {
    private boolean mAttached;

    @NonNull
    private ViewGroup mParent;

    @RequiresApi
    static class Api21Impl {
        @DoNotInline
        static float a(View view) {
            return view.getZ();
        }
    }

    GhostViewHolder(ViewGroup viewGroup) {
        super(viewGroup.getContext());
        setClipChildren(false);
        this.mParent = viewGroup;
        viewGroup.setTag(R.id.ghost_view_holder, this);
        this.mParent.getOverlay().add(this);
        this.mAttached = true;
    }

    static GhostViewHolder b(ViewGroup viewGroup) {
        return (GhostViewHolder) viewGroup.getTag(R.id.ghost_view_holder);
    }

    private int c(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        int childCount = getChildCount() - 1;
        int i2 = 0;
        while (i2 <= childCount) {
            int i3 = (i2 + childCount) / 2;
            d(((GhostViewPort) getChildAt(i3)).mView, arrayList2);
            if (f(arrayList, arrayList2)) {
                i2 = i3 + 1;
            } else {
                childCount = i3 - 1;
            }
            arrayList2.clear();
        }
        return i2;
    }

    private static void d(View view, ArrayList arrayList) {
        Object parent = view.getParent();
        if (parent instanceof ViewGroup) {
            d((View) parent, arrayList);
        }
        arrayList.add(view);
    }

    private static boolean e(View view, View view2) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        int childCount = viewGroup.getChildCount();
        if (Api21Impl.a(view) != Api21Impl.a(view2)) {
            return Api21Impl.a(view) > Api21Impl.a(view2);
        }
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(ViewGroupUtils.a(viewGroup, i2));
            if (childAt == view) {
                return false;
            }
            if (childAt == view2) {
                break;
            }
        }
        return true;
    }

    private static boolean f(ArrayList arrayList, ArrayList arrayList2) {
        if (arrayList.isEmpty() || arrayList2.isEmpty() || arrayList.get(0) != arrayList2.get(0)) {
            return true;
        }
        int min = Math.min(arrayList.size(), arrayList2.size());
        for (int i2 = 1; i2 < min; i2++) {
            View view = (View) arrayList.get(i2);
            View view2 = (View) arrayList2.get(i2);
            if (view != view2) {
                return e(view, view2);
            }
        }
        return arrayList2.size() == min;
    }

    void a(GhostViewPort ghostViewPort) {
        ArrayList arrayList = new ArrayList();
        d(ghostViewPort.mView, arrayList);
        int c2 = c(arrayList);
        if (c2 < 0 || c2 >= getChildCount()) {
            addView(ghostViewPort);
        } else {
            addView(ghostViewPort, c2);
        }
    }

    void g() {
        if (!this.mAttached) {
            throw new IllegalStateException("This GhostViewHolder is detached!");
        }
        this.mParent.getOverlay().remove(this);
        this.mParent.getOverlay().add(this);
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        if (!this.mAttached) {
            throw new IllegalStateException("This GhostViewHolder is detached!");
        }
        super.onViewAdded(view);
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        if ((getChildCount() == 1 && getChildAt(0) == view) || getChildCount() == 0) {
            this.mParent.setTag(R.id.ghost_view_holder, null);
            this.mParent.getOverlay().remove(this);
            this.mAttached = false;
        }
    }
}
