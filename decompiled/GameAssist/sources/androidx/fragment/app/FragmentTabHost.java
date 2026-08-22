package androidx.fragment.app;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TabHost;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

@Deprecated
/* loaded from: classes.dex */
public class FragmentTabHost extends TabHost implements TabHost.OnTabChangeListener {
    private boolean mAttached;
    private int mContainerId;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private TabInfo mLastTab;
    private TabHost.OnTabChangeListener mOnTabChangeListener;
    private FrameLayout mRealTabContent;
    private final ArrayList<TabInfo> mTabs;

    static class DummyTabFactory implements TabHost.TabContentFactory {

        /* renamed from: a, reason: collision with root package name */
        private final Context f4144a;

        @Override // android.widget.TabHost.TabContentFactory
        public View createTabContent(String str) {
            View view = new View(this.f4144a);
            view.setMinimumWidth(0);
            view.setMinimumHeight(0);
            return view;
        }
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: androidx.fragment.app.FragmentTabHost.SavedState.1
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
        String f4145c;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "FragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + this.f4145c + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeString(this.f4145c);
        }

        SavedState(Parcel parcel) {
            super(parcel);
            this.f4145c = parcel.readString();
        }
    }

    static final class TabInfo {

        /* renamed from: a, reason: collision with root package name */
        final String f4146a;

        /* renamed from: b, reason: collision with root package name */
        final Class f4147b;

        /* renamed from: c, reason: collision with root package name */
        final Bundle f4148c;

        /* renamed from: d, reason: collision with root package name */
        Fragment f4149d;
    }

    @Deprecated
    public FragmentTabHost(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTabs = new ArrayList<>();
        c(context, attributeSet);
    }

    private FragmentTransaction a(String str, FragmentTransaction fragmentTransaction) {
        Fragment fragment;
        TabInfo b2 = b(str);
        if (this.mLastTab != b2) {
            if (fragmentTransaction == null) {
                fragmentTransaction = this.mFragmentManager.p();
            }
            TabInfo tabInfo = this.mLastTab;
            if (tabInfo != null && (fragment = tabInfo.f4149d) != null) {
                fragmentTransaction.l(fragment);
            }
            if (b2 != null) {
                Fragment fragment2 = b2.f4149d;
                if (fragment2 == null) {
                    Fragment a2 = this.mFragmentManager.x0().a(this.mContext.getClassLoader(), b2.f4147b.getName());
                    b2.f4149d = a2;
                    a2.J1(b2.f4148c);
                    fragmentTransaction.c(this.mContainerId, b2.f4149d, b2.f4146a);
                } else {
                    fragmentTransaction.g(fragment2);
                }
            }
            this.mLastTab = b2;
        }
        return fragmentTransaction;
    }

    private TabInfo b(String str) {
        int size = this.mTabs.size();
        for (int i2 = 0; i2 < size; i2++) {
            TabInfo tabInfo = this.mTabs.get(i2);
            if (tabInfo.f4146a.equals(str)) {
                return tabInfo;
            }
        }
        return null;
    }

    private void c(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr.inflatedId}, 0, 0);
        this.mContainerId = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        super.setOnTabChangedListener(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        String currentTabTag = getCurrentTabTag();
        int size = this.mTabs.size();
        FragmentTransaction fragmentTransaction = null;
        for (int i2 = 0; i2 < size; i2++) {
            TabInfo tabInfo = this.mTabs.get(i2);
            Fragment l0 = this.mFragmentManager.l0(tabInfo.f4146a);
            tabInfo.f4149d = l0;
            if (l0 != null && !l0.n0()) {
                if (tabInfo.f4146a.equals(currentTabTag)) {
                    this.mLastTab = tabInfo;
                } else {
                    if (fragmentTransaction == null) {
                        fragmentTransaction = this.mFragmentManager.p();
                    }
                    fragmentTransaction.l(tabInfo.f4149d);
                }
            }
        }
        this.mAttached = true;
        FragmentTransaction a2 = a(currentTabTag, fragmentTransaction);
        if (a2 != null) {
            a2.h();
            this.mFragmentManager.h0();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mAttached = false;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setCurrentTabByTag(savedState.f4145c);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f4145c = getCurrentTabTag();
        return savedState;
    }

    @Override // android.widget.TabHost.OnTabChangeListener
    public void onTabChanged(String str) {
        FragmentTransaction a2;
        if (this.mAttached && (a2 = a(str, null)) != null) {
            a2.h();
        }
        TabHost.OnTabChangeListener onTabChangeListener = this.mOnTabChangeListener;
        if (onTabChangeListener != null) {
            onTabChangeListener.onTabChanged(str);
        }
    }

    @Override // android.widget.TabHost
    @Deprecated
    public void setOnTabChangedListener(@Nullable TabHost.OnTabChangeListener onTabChangeListener) {
        this.mOnTabChangeListener = onTabChangeListener;
    }

    @Override // android.widget.TabHost
    public void setup() {
        throw new IllegalStateException("Must call setup() that takes a Context and FragmentManager");
    }
}
