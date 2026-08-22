package com.zte.mifavor.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.widget.TextViewCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.zte.extres.R;

/* loaded from: classes2.dex */
public class TabLayoutZTE extends TabLayout {
    private static final int COUNT_2 = 2;
    private static final int COUNT_3 = 3;
    private static final int COUNT_4 = 4;
    private static final int COUNT_5 = 5;
    private static final String TAG = "TabLayoutZTE";

    @Nullable
    PagerAdapter mAdapter;
    private final int mAutoSizeMaxTextSize;
    private final int mAutoSizeMinTextSize;
    private DataSetObserver pagerAdapterIconTabObserver;

    public interface IconTabInterface {
        default int a(int i2) {
            return 0;
        }

        int b(int i2);
    }

    private class PagerAdapterIconTabObserver extends DataSetObserver {
        PagerAdapterIconTabObserver() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            TabLayoutZTE.this.Z();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            TabLayoutZTE.this.Z();
        }
    }

    public TabLayoutZTE(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAdapter = null;
        this.mAutoSizeMinTextSize = getResources().getDimensionPixelSize(R.dimen.mfvc_bottom_tab_text_min_size);
        this.mAutoSizeMaxTextSize = getResources().getDimensionPixelSize(R.dimen.mfvc_bottom_tab_text_max_size);
        Y();
    }

    private void Y() {
        a0();
        setSelectedTabIndicatorHeight(0);
        if (Utils.f17815b) {
            setForceDarkAllowed(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Z() {
        PagerAdapter pagerAdapter = this.mAdapter;
        if (pagerAdapter == null) {
            Log.d(TAG, "set TabLayout Padding error, mAdapter is null.");
        } else {
            setTabLayoutPaddingByCount(pagerAdapter.e());
        }
    }

    private void a0() {
        h(new TabLayout.OnTabSelectedListener() { // from class: com.zte.mifavor.widget.TabLayoutZTE.1
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void a(TabLayout.Tab tab) {
                TabLayoutZTE.this.b0(tab, true);
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void b(TabLayout.Tab tab) {
                TabLayoutZTE.this.b0(tab, false);
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void c(TabLayout.Tab tab) {
                TabLayoutZTE.this.b0(tab, true);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View X(int i2, PagerAdapter pagerAdapter, boolean z) {
        Log.d(TAG, "getTabView in. position = " + i2 + ", select = " + z);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.tab_item_layout_zte, (ViewGroup) null);
        if (inflate == null) {
            return null;
        }
        ((TextView) inflate.findViewById(R.id.tabTextView)).setText(pagerAdapter.g(i2));
        ImageView imageView = (ImageView) inflate.findViewById(R.id.tabImageView);
        if (z) {
            imageView.setSelected(true);
        } else {
            imageView.setSelected(false);
        }
        IconTabInterface iconTabInterface = (IconTabInterface) pagerAdapter;
        imageView.setImageResource(iconTabInterface.b(i2));
        TextView textView = (TextView) inflate.findViewById(R.id.tabConnerTextView);
        int a2 = iconTabInterface.a(i2);
        if (a2 == 0) {
            textView.setVisibility(4);
        } else {
            textView.setVisibility(0);
            textView.setText(String.valueOf(a2));
        }
        return inflate;
    }

    public void b0(TabLayout.Tab tab, boolean z) {
        View e2 = tab.e();
        if (e2 == null || this.mAdapter == null) {
            return;
        }
        TextView textView = (TextView) e2.findViewById(R.id.tabTextView);
        if (z) {
            textView.setTextAppearance(R.style.mfvc_bottom_tab_focused_font);
        } else {
            textView.setTextAppearance(R.style.mfvc_bottom_tab_normal_font);
        }
        TextViewCompat.g(textView, this.mAutoSizeMinTextSize, this.mAutoSizeMaxTextSize, 1, 1);
        ImageView imageView = (ImageView) e2.findViewById(R.id.tabImageView);
        if (z) {
            imageView.setSelected(true);
        } else {
            imageView.setSelected(false);
        }
    }

    @Override // com.google.android.material.tabs.TabLayout
    public void k(TabLayout.Tab tab, boolean z) {
        super.k(tab, z);
        Log.d(TAG, "add Tab in. setSelected = " + z + ", mAutoSizeMinTextSize = " + this.mAutoSizeMinTextSize + ", mAutoSizeMaxTextSize = " + this.mAutoSizeMaxTextSize);
        if (tab == null || this.mAdapter == null) {
            return;
        }
        View X = X(tab.g(), this.mAdapter, z);
        tab.o(X);
        if (X == null) {
            return;
        }
        TextView textView = (TextView) X.findViewById(R.id.tabTextView);
        if (z) {
            textView.setTextAppearance(R.style.mfvc_bottom_tab_focused_font);
        } else {
            textView.setTextAppearance(R.style.mfvc_bottom_tab_normal_font);
        }
        TextViewCompat.g(textView, this.mAutoSizeMinTextSize, this.mAutoSizeMaxTextSize, 1, 1);
    }

    public void setTabLayoutPadding(int i2) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            Log.d(TAG, "set TabLayout Padding lpView is LinearLayout.LayoutParams. padding = " + i2);
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams2.setMarginStart(i2);
            layoutParams2.setMarginEnd(i2);
            setLayoutParams(layoutParams2);
            return;
        }
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            Log.d(TAG, "set TabLayout Padding lpView is FrameLayout.LayoutParams. padding = " + i2);
            FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) layoutParams;
            layoutParams3.setMarginStart(i2);
            layoutParams3.setMarginEnd(i2);
            setLayoutParams(layoutParams3);
            return;
        }
        if (!(layoutParams instanceof RelativeLayout.LayoutParams)) {
            Log.w(TAG, "set TabLayout Padding error. lpView is " + layoutParams + ", padding = " + i2);
            return;
        }
        Log.d(TAG, "set TabLayout Padding lpView is RelativeLayout.LayoutParams. padding = " + i2);
        RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) layoutParams;
        layoutParams4.setMarginStart(i2);
        layoutParams4.setMarginEnd(i2);
        setLayoutParams(layoutParams4);
    }

    public void setTabLayoutPaddingByCount(int i2) {
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.mfvc_bottom_tab_padding04);
        if (i2 == 2) {
            dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.mfvc_bottom_tab_padding01);
        } else if (i2 == 3) {
            dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.mfvc_bottom_tab_padding02);
        } else if (i2 == 4) {
            dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.mfvc_bottom_tab_padding03);
        } else if (i2 == 5) {
            dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.mfvc_bottom_tab_padding04);
        } else {
            Log.d(TAG, "set TabLayout Padding lpView is LinearLayout.LayoutParams. padding = " + dimensionPixelSize + ", tabCount = " + i2);
        }
        setTabLayoutPadding(dimensionPixelSize);
    }

    public void setViewPager(ViewPager viewPager) {
        DataSetObserver dataSetObserver;
        PagerAdapter pagerAdapter = this.mAdapter;
        if (pagerAdapter != null && (dataSetObserver = this.pagerAdapterIconTabObserver) != null) {
            pagerAdapter.u(dataSetObserver);
        }
        PagerAdapter adapter = viewPager.getAdapter();
        this.mAdapter = adapter;
        if (adapter != null) {
            if (this.pagerAdapterIconTabObserver == null) {
                this.pagerAdapterIconTabObserver = new PagerAdapterIconTabObserver();
            }
            this.mAdapter.m(this.pagerAdapterIconTabObserver);
        }
        if (this.mAdapter == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        if (viewPager.getAdapter() instanceof IconTabInterface) {
            Q(viewPager, true);
            Z();
        }
    }

    public TabLayoutZTE(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mAdapter = null;
        this.mAutoSizeMinTextSize = getResources().getDimensionPixelSize(R.dimen.mfvc_bottom_tab_text_min_size);
        this.mAutoSizeMaxTextSize = getResources().getDimensionPixelSize(R.dimen.mfvc_bottom_tab_text_max_size);
        Y();
    }
}
