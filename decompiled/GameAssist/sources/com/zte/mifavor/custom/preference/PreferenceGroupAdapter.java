package com.zte.mifavor.custom.preference;

import android.content.res.Resources;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.zte.extres.R;
import com.zte.mifavor.custom.Config;

/* loaded from: classes2.dex */
public class PreferenceGroupAdapter {
    private static final String TAG = "PreferenceGroupAdapter";

    private static View addDivider(View view, Preference preference) {
        if (preference instanceof PreferenceCategory) {
            Log.d(TAG, "addDivider pref is PreferenceCategory");
            return view;
        }
        view.findViewById(R.id.title_bottom_divider);
        if (view.findViewById(R.id.divider) != null) {
            return view;
        }
        LinearLayout linearLayout = new LinearLayout(view.getContext());
        linearLayout.setOrientation(1);
        linearLayout.addView(view, new LinearLayout.LayoutParams(-1, -2, 1.0f));
        View view2 = new View(view.getContext());
        view2.setId(R.id.divider);
        view2.setBackgroundColor(view.getContext().getColor(R.color.mfv_common_divl));
        int dimensionPixelSize = view.getContext().getResources().getDimensionPixelSize(R.dimen.mfvc_divider_line_height);
        int dimensionPixelSize2 = view.getContext().getResources().getDimensionPixelSize(R.dimen.mfvc_large_padding);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, dimensionPixelSize);
        layoutParams.setMarginStart(dimensionPixelSize2);
        layoutParams.setMarginEnd(dimensionPixelSize2);
        linearLayout.addView(view2, layoutParams);
        if (view.getMinimumHeight() - dimensionPixelSize > 0) {
            view.setMinimumHeight(view.getMinimumHeight() - dimensionPixelSize);
        }
        return linearLayout;
    }

    private static void customDividerAndHeightInternal(Preference preference, View view, boolean z, int i2) {
        boolean z2;
        int i3;
        View findViewById;
        Log.d(TAG, "customDividerAndHeightInternal position is " + i2 + " title is " + ((Object) preference.getTitle()));
        if (preference instanceof PreferenceCategory) {
            Log.d(TAG, "customDividerAndHeightInternal is PreferenceCategory");
            View findViewById2 = view.findViewById(R.id.title_bottom_divider);
            if (findViewById2 != null) {
                findViewById2.setVisibility(8);
            }
            if (i2 != 0 || (findViewById = view.findViewById(R.id.group_divider)) == null) {
                return;
            }
            findViewById.setVisibility(8);
            return;
        }
        Log.d(TAG, "customDividerAndHeightInternal is not PreferenceCategory");
        View findViewById3 = view.findViewById(R.id.divider);
        if (findViewById3 == null) {
            return;
        }
        findViewById3.setVisibility(z ? 0 : 4);
        ImageView imageView = (ImageView) view.findViewById(android.R.id.icon);
        Resources resources = view.getContext().getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.mfvc_xlarge_padding);
        if (imageView == null || imageView.getVisibility() == 8 || imageView.getDrawable() == null) {
            z2 = false;
            i3 = 0;
        } else {
            int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.mfvc_list_avatar_txt_left_padding);
            int i4 = dimensionPixelSize2 - (dimensionPixelSize * 2);
            z2 = (imageView.getDrawable().getMinimumWidth() >= i4) & (imageView.getMaxWidth() >= i4);
            if (!z2) {
                dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.mfvc_1line_list_with_avatar_height_edge);
            }
            i3 = dimensionPixelSize2 - dimensionPixelSize;
        }
        View findViewById4 = view.findViewById(android.R.id.icon_frame);
        if (findViewById4 != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) findViewById4.getLayoutParams();
            if (i3 <= 0) {
                layoutParams.width = -2;
            } else {
                layoutParams.width = i3;
            }
            findViewById4.setLayoutParams(layoutParams);
            int i5 = i3 + dimensionPixelSize;
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) findViewById3.getLayoutParams();
            if (i5 != layoutParams2.getMarginStart()) {
                layoutParams2.setMarginStart(i5);
                findViewById3.setLayoutParams(layoutParams2);
            }
        }
        int i6 = R.dimen.mfvc_1line_list_height;
        if (isNeedDoubleRow(view.findViewById(android.R.id.title), view.findViewById(android.R.id.summary), view.findViewById(R.id.status))) {
            i6 = R.dimen.mfvc_2line_list_height;
        } else if (z2) {
            i6 = R.dimen.mfvc_1line_list_with_avatar_height;
        }
        view.setMinimumHeight(view.getContext().getResources().getDimensionPixelSize(i6));
        View findViewById5 = view.findViewById(R.id.indicator);
        if (findViewById5 == null || findViewById5.getVisibility() == 8) {
            return;
        }
        view.setPaddingRelative(0, 0, resources.getDimensionPixelSize(R.dimen.mfvc_xlarge_padding_02) - resources.getDimensionPixelSize(R.dimen.mfvc_xlarge_padding), 0);
    }

    public static Object getView_handlePreferenceView(Object[] objArr) {
        BaseAdapter baseAdapter = (BaseAdapter) objArr[0];
        int intValue = ((Integer) objArr[1]).intValue();
        View view = (View) objArr[2];
        if (!Config.isMifavorTheme(view.getContext())) {
            return view;
        }
        Preference preference = (Preference) baseAdapter.getItem(intValue);
        View addDivider = addDivider(view, preference);
        customDividerAndHeightInternal(preference, addDivider, false, intValue);
        return addDivider;
    }

    private static boolean isNeedDoubleRow(View view, View view2, View view3) {
        if (view == null || view.getVisibility() == 8) {
            Log.w(TAG, "is Need Double Row error, return false. ");
            return false;
        }
        if (view2 != null && view2.getVisibility() == 8 && view3 != null && view3.getVisibility() == 8) {
            Log.d(TAG, "is Need Double Row out, summary and status is invisiable, return false, titleView = " + ((Object) ((TextView) view).getText()));
            return false;
        }
        if (view2 != null && view2.getVisibility() != 8 && view3 != null && view3.getVisibility() != 8) {
            Log.d(TAG, "is Need Double Row out, summary and status is visiable, return true, titleView = " + ((Object) ((TextView) view).getText()));
            return true;
        }
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (!(viewGroup instanceof RelativeLayout)) {
            Log.w(TAG, "is Need Double Row out, the parent isn't RelativeLayout.");
            return false;
        }
        if (view3 != null && view3.getVisibility() != 8 && ((TextView) ((RelativeLayout) viewGroup).findViewById(R.id.status)) != null) {
            Log.d(TAG, "is Need Double Row out, title and status is visiable, return true, titleView = " + ((Object) ((TextView) view).getText()));
            return true;
        }
        if (view2 == null || view2.getVisibility() == 8 || ((TextView) ((RelativeLayout) viewGroup).findViewById(android.R.id.summary)) == null) {
            Log.d(TAG, "is Need Double Row other, return false, titleView = " + ((Object) ((TextView) view).getText()));
            return false;
        }
        Log.d(TAG, "is Need Double Row out, title and summary is visiable, return true, titleView = " + ((Object) ((TextView) view).getText()));
        return true;
    }

    private static boolean shouldShowDividerBelow(BaseAdapter baseAdapter, int i2) {
        if (((Preference) baseAdapter.getItem(i2)) instanceof PreferenceCategory) {
            return false;
        }
        return i2 >= baseAdapter.getCount() || !(((Preference) baseAdapter.getItem(i2 + 1)) instanceof PreferenceCategory);
    }
}
