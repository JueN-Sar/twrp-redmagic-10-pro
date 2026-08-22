package androidx.fragment.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.R;
import androidx.fragment.app.strictmode.FragmentStrictMode;

/* loaded from: classes.dex */
class FragmentLayoutInflaterFactory implements LayoutInflater.Factory2 {

    /* renamed from: c, reason: collision with root package name */
    final FragmentManager f4038c;

    FragmentLayoutInflaterFactory(FragmentManager fragmentManager) {
        this.f4038c = fragmentManager;
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    @Override // android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        final FragmentStateManager y;
        if (FragmentContainerView.class.getName().equals(str)) {
            return new FragmentContainerView(context, attributeSet, this.f4038c);
        }
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Fragment);
        if (attributeValue == null) {
            attributeValue = obtainStyledAttributes.getString(R.styleable.Fragment_android_name);
        }
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.Fragment_android_id, -1);
        String string = obtainStyledAttributes.getString(R.styleable.Fragment_android_tag);
        obtainStyledAttributes.recycle();
        if (attributeValue == null || !FragmentFactory.b(context.getClassLoader(), attributeValue)) {
            return null;
        }
        int id = view != null ? view.getId() : 0;
        if (id == -1 && resourceId == -1 && string == null) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + attributeValue);
        }
        Fragment k0 = resourceId != -1 ? this.f4038c.k0(resourceId) : null;
        if (k0 == null && string != null) {
            k0 = this.f4038c.l0(string);
        }
        if (k0 == null && id != -1) {
            k0 = this.f4038c.k0(id);
        }
        if (k0 == null) {
            k0 = this.f4038c.x0().a(context.getClassLoader(), attributeValue);
            k0.u = true;
            k0.D = resourceId != 0 ? resourceId : id;
            k0.E = id;
            k0.F = string;
            k0.v = true;
            FragmentManager fragmentManager = this.f4038c;
            k0.z = fragmentManager;
            k0.A = fragmentManager.A0();
            k0.P0(this.f4038c.A0().r(), attributeSet, k0.f3975h);
            y = this.f4038c.j(k0);
            if (FragmentManager.N0(2)) {
                Log.v("FragmentManager", "Fragment " + k0 + " has been inflated via the <fragment> tag: id=0x" + Integer.toHexString(resourceId));
            }
        } else {
            if (k0.v) {
                throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + attributeValue);
            }
            k0.v = true;
            FragmentManager fragmentManager2 = this.f4038c;
            k0.z = fragmentManager2;
            k0.A = fragmentManager2.A0();
            k0.P0(this.f4038c.A0().r(), attributeSet, k0.f3975h);
            y = this.f4038c.y(k0);
            if (FragmentManager.N0(2)) {
                Log.v("FragmentManager", "Retained Fragment " + k0 + " has been re-attached via the <fragment> tag: id=0x" + Integer.toHexString(resourceId));
            }
        }
        ViewGroup viewGroup = (ViewGroup) view;
        FragmentStrictMode.i(k0, viewGroup);
        k0.N = viewGroup;
        y.m();
        y.j();
        View view2 = k0.O;
        if (view2 == null) {
            throw new IllegalStateException("Fragment " + attributeValue + " did not create a view.");
        }
        if (resourceId != 0) {
            view2.setId(resourceId);
        }
        if (k0.O.getTag() == null) {
            k0.O.setTag(string);
        }
        k0.O.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: androidx.fragment.app.FragmentLayoutInflaterFactory.1
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view3) {
                Fragment k2 = y.k();
                y.m();
                SpecialEffectsController.n((ViewGroup) k2.O.getParent(), FragmentLayoutInflaterFactory.this.f4038c).j();
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view3) {
            }
        });
        return k0.O;
    }
}
