package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import androidx.annotation.RestrictTo;
import androidx.collection.SimpleArrayMap;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.Preference;
import com.google.android.gms.common.api.Api;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public abstract class PreferenceGroup extends Preference {
    private List S;
    private boolean T;
    private int U;
    private boolean V;
    private int W;
    private OnExpandButtonClickListener X;
    final SimpleArrayMap Y;
    private final Handler Z;
    private final Runnable a0;

    @RestrictTo
    public interface OnExpandButtonClickListener {
        void a();
    }

    public interface PreferencePositionCallback {
        int b(Preference preference);

        int i(String str);
    }

    public PreferenceGroup(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.T = true;
        this.U = 0;
        this.V = false;
        this.W = Api.BaseClientBuilder.API_PRIORITY_OTHER;
        this.X = null;
        this.Y = new SimpleArrayMap();
        this.Z = new Handler();
        this.a0 = new Runnable() { // from class: androidx.preference.PreferenceGroup.1
            @Override // java.lang.Runnable
            public void run() {
                synchronized (this) {
                    PreferenceGroup.this.Y.clear();
                }
            }
        };
        this.S = new ArrayList();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PreferenceGroup, i2, i3);
        int i4 = R.styleable.PreferenceGroup_orderingFromXml;
        this.T = TypedArrayUtils.b(obtainStyledAttributes, i4, i4, true);
        if (obtainStyledAttributes.hasValue(R.styleable.PreferenceGroup_initialExpandedChildrenCount)) {
            int i5 = R.styleable.PreferenceGroup_initialExpandedChildrenCount;
            G0(TypedArrayUtils.d(obtainStyledAttributes, i5, i5, Api.BaseClientBuilder.API_PRIORITY_OTHER));
        }
        obtainStyledAttributes.recycle();
    }

    public Preference A0(CharSequence charSequence) {
        Preference A0;
        if (TextUtils.equals(u(), charSequence)) {
            return this;
        }
        int E0 = E0();
        for (int i2 = 0; i2 < E0; i2++) {
            Preference D0 = D0(i2);
            String u = D0.u();
            if (u != null && u.equals(charSequence)) {
                return D0;
            }
            if ((D0 instanceof PreferenceGroup) && (A0 = ((PreferenceGroup) D0).A0(charSequence)) != null) {
                return A0;
            }
        }
        return null;
    }

    public int B0() {
        return this.W;
    }

    public OnExpandButtonClickListener C0() {
        return this.X;
    }

    public Preference D0(int i2) {
        return (Preference) this.S.get(i2);
    }

    public int E0() {
        return this.S.size();
    }

    protected boolean F0() {
        return true;
    }

    public void G0(int i2) {
        if (i2 != Integer.MAX_VALUE && !I()) {
            Log.e("PreferenceGroup", getClass().getSimpleName() + " should have a key defined if it contains an expandable preference");
        }
        this.W = i2;
    }

    void H0() {
        synchronized (this) {
            Collections.sort(this.S);
        }
    }

    @Override // androidx.preference.Preference
    public void O(boolean z) {
        super.O(z);
        int E0 = E0();
        for (int i2 = 0; i2 < E0; i2++) {
            D0(i2).X(this, z);
        }
    }

    @Override // androidx.preference.Preference
    public void Q() {
        super.Q();
        this.V = true;
        int E0 = E0();
        for (int i2 = 0; i2 < E0; i2++) {
            D0(i2).Q();
        }
    }

    @Override // androidx.preference.Preference
    public void U() {
        super.U();
        this.V = false;
        int E0 = E0();
        for (int i2 = 0; i2 < E0; i2++) {
            D0(i2).U();
        }
    }

    @Override // androidx.preference.Preference
    protected void Y(Parcelable parcelable) {
        if (parcelable == null || !parcelable.getClass().equals(SavedState.class)) {
            super.Y(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        this.W = savedState.f4722c;
        super.Y(savedState.getSuperState());
    }

    @Override // androidx.preference.Preference
    protected Parcelable Z() {
        return new SavedState(super.Z(), this.W);
    }

    @Override // androidx.preference.Preference
    protected void f(Bundle bundle) {
        super.f(bundle);
        int E0 = E0();
        for (int i2 = 0; i2 < E0; i2++) {
            D0(i2).f(bundle);
        }
    }

    @Override // androidx.preference.Preference
    protected void h(Bundle bundle) {
        super.h(bundle);
        int E0 = E0();
        for (int i2 = 0; i2 < E0; i2++) {
            D0(i2).h(bundle);
        }
    }

    static class SavedState extends Preference.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: androidx.preference.PreferenceGroup.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };

        /* renamed from: c, reason: collision with root package name */
        int f4722c;

        SavedState(Parcel parcel) {
            super(parcel);
            this.f4722c = parcel.readInt();
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f4722c);
        }

        SavedState(Parcelable parcelable, int i2) {
            super(parcelable);
            this.f4722c = i2;
        }
    }

    public PreferenceGroup(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public PreferenceGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }
}
