package com.zte.mifavor.androidx.preference;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceViewHolder;
import com.zte.extres.R;

/* loaded from: classes2.dex */
public class PreferenceGroupAdapter extends androidx.preference.PreferenceGroupAdapter {

    /* renamed from: k, reason: collision with root package name */
    private final boolean f17120k;

    /* renamed from: l, reason: collision with root package name */
    private int f17121l;

    public PreferenceGroupAdapter(PreferenceGroup preferenceGroup) {
        super(preferenceGroup);
        this.f17120k = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x02c8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void S(androidx.preference.PreferenceViewHolder r31, int r32) {
        /*
            Method dump skipped, instructions count: 1658
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zte.mifavor.androidx.preference.PreferenceGroupAdapter.S(androidx.preference.PreferenceViewHolder, int):void");
    }

    private int T(Preference preference) {
        if (preference instanceof PreferenceZTE) {
            return ((PreferenceZTE) preference).D0();
        }
        if (preference instanceof PreferenceZTEX) {
            return ((PreferenceZTEX) preference).D0();
        }
        if (preference instanceof SwitchPreferenceZTE) {
            return ((SwitchPreferenceZTE) preference).M0();
        }
        if (preference instanceof ListPreferenceZTEX) {
            return ((ListPreferenceZTEX) preference).O0();
        }
        if (preference instanceof ListPreferenceZTE) {
            return ((ListPreferenceZTE) preference).O0();
        }
        return -2;
    }

    private Preference U(Preference preference) {
        Preference preference2 = null;
        if (preference == null) {
            return null;
        }
        try {
            PreferenceGroup y = preference.y();
            int E0 = y != null ? y.E0() : 0;
            int i2 = 0;
            boolean z = false;
            for (int i3 = 0; i3 < E0; i3++) {
                preference2 = preference.y().D0(i3);
                if (preference2.u() == preference.u()) {
                    z = true;
                    i2 = i3;
                } else {
                    int T = T(preference2);
                    if ((!z || (preference.M() && -1 != T)) && z && preference.M() && -1 != T) {
                        return preference2;
                    }
                }
            }
            PreferenceGroup y2 = preference.y();
            return y2 != null ? y2.D0(i2) : preference2;
        } catch (Exception e2) {
            Log.e("XPG#Adapter", "get Post Visible Preference error. e=", e2);
            return null;
        }
    }

    private Preference V(Preference preference) {
        Preference preference2 = null;
        try {
            PreferenceGroup y = preference.y();
            int i2 = 0;
            int E0 = y != null ? y.E0() : 0;
            Preference preference3 = null;
            int i3 = 0;
            while (true) {
                if (i3 >= E0) {
                    break;
                }
                try {
                    preference3 = preference.y().D0(i3);
                    if (preference.u() == preference3.u()) {
                        i2 = i3;
                        break;
                    }
                    i3++;
                } catch (Exception e2) {
                    e = e2;
                    preference2 = preference3;
                    Log.e("XPG#Adapter", "get Pre Visible Preference error. e=", e);
                    return preference2;
                }
            }
            if (i2 > 0) {
                for (int i4 = i2 - 1; i4 >= 0; i4--) {
                    preference2 = preference.y().D0(i4);
                    int T = T(preference2);
                    if (-1 != T && preference2.M() && -1 != T && preference2.M()) {
                        return preference2;
                    }
                }
            }
        } catch (Exception e3) {
            e = e3;
        }
        return preference2;
    }

    private boolean W(Preference preference) {
        boolean z;
        if (preference == null) {
            return false;
        }
        String u = preference.u();
        if (u != null && u.contains("_lastcategory")) {
            return true;
        }
        PreferenceGroup y = preference.y();
        int E0 = y != null ? y.E0() : 0;
        int i2 = 0;
        while (true) {
            if (i2 >= E0) {
                z = false;
                i2 = 0;
                break;
            }
            if (preference.y().D0(i2).u() == u) {
                z = true;
                break;
            }
            i2++;
        }
        if (i2 == E0 - 1) {
            return true;
        }
        if (z) {
            try {
                preference.y().D0(i2 + 1);
            } catch (Exception unused) {
                Log.w("XPG#Adapter", "index out of Bouunds. localPosition = " + i2);
                return true;
            }
        }
        return false;
    }

    private boolean X(View view, View view2, View view3) {
        if (view == null || view.getVisibility() == 8 || (view2 != null && view2.getVisibility() == 8 && view3 != null && view3.getVisibility() == 8)) {
            return false;
        }
        if (view2 != null && view2.getVisibility() != 8 && view3 != null && view3.getVisibility() != 8) {
            return true;
        }
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (!(viewGroup instanceof RelativeLayout)) {
            return false;
        }
        if (view3 == null || view3.getVisibility() == 8 || ((TextView) ((RelativeLayout) viewGroup).findViewById(R.id.status)) == null) {
            return (view2 == null || view2.getVisibility() == 8 || ((TextView) ((RelativeLayout) viewGroup).findViewById(android.R.id.summary)) == null) ? false : true;
        }
        return true;
    }

    private void Y(View view, int i2) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (i2 != layoutParams.height) {
            layoutParams.height = i2;
            view.setLayoutParams(layoutParams);
        }
        view.setVisibility(0);
    }

    @Override // androidx.preference.PreferenceGroupAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: P */
    public void A(PreferenceViewHolder preferenceViewHolder, int i2) {
        int id = preferenceViewHolder.f5252a.getId();
        super.A(preferenceViewHolder, i2);
        preferenceViewHolder.f5252a.setId(id);
        try {
            S(preferenceViewHolder, i2);
        } catch (Exception e2) {
            Log.e("XPG#Adapter", "custom error, e = ", e2);
        }
    }
}
