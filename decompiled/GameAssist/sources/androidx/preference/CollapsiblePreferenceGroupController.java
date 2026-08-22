package androidx.preference;

import android.content.Context;
import android.text.TextUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.google.android.gms.common.api.Api;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
final class CollapsiblePreferenceGroupController {

    /* renamed from: a, reason: collision with root package name */
    final PreferenceGroupAdapter f4641a;

    /* renamed from: b, reason: collision with root package name */
    private final Context f4642b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f4643c = false;

    static class ExpandButton extends Preference {
        private long S;

        ExpandButton(Context context, List list, long j2) {
            super(context);
            A0();
            B0(list);
            this.S = j2 + 1000000;
        }

        private void A0() {
            n0(R.layout.expand_button);
            l0(R.drawable.ic_arrow_down_24dp);
            s0(R.string.expand_button_title);
            q0(999);
        }

        private void B0(List list) {
            ArrayList arrayList = new ArrayList();
            Iterator it = list.iterator();
            CharSequence charSequence = null;
            while (it.hasNext()) {
                Preference preference = (Preference) it.next();
                CharSequence G = preference.G();
                boolean z = preference instanceof PreferenceGroup;
                if (z && !TextUtils.isEmpty(G)) {
                    arrayList.add((PreferenceGroup) preference);
                }
                if (arrayList.contains(preference.y())) {
                    if (z) {
                        arrayList.add((PreferenceGroup) preference);
                    }
                } else if (!TextUtils.isEmpty(G)) {
                    charSequence = charSequence == null ? G : l().getString(R.string.summary_collapsed_preference_list, charSequence, G);
                }
            }
            r0(charSequence);
        }

        @Override // androidx.preference.Preference
        public void R(PreferenceViewHolder preferenceViewHolder) {
            super.R(preferenceViewHolder);
            preferenceViewHolder.Q(false);
        }

        @Override // androidx.preference.Preference
        public long s() {
            return this.S;
        }
    }

    CollapsiblePreferenceGroupController(PreferenceGroup preferenceGroup, PreferenceGroupAdapter preferenceGroupAdapter) {
        this.f4641a = preferenceGroupAdapter;
        this.f4642b = preferenceGroup.l();
    }

    private ExpandButton a(final PreferenceGroup preferenceGroup, List list) {
        ExpandButton expandButton = new ExpandButton(this.f4642b, list, preferenceGroup.s());
        expandButton.p0(new Preference.OnPreferenceClickListener() { // from class: androidx.preference.CollapsiblePreferenceGroupController.1
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public boolean a(Preference preference) {
                preferenceGroup.G0(Api.BaseClientBuilder.API_PRIORITY_OTHER);
                CollapsiblePreferenceGroupController.this.f4641a.e(preference);
                PreferenceGroup.OnExpandButtonClickListener C0 = preferenceGroup.C0();
                if (C0 == null) {
                    return true;
                }
                C0.a();
                return true;
            }
        });
        return expandButton;
    }

    private List b(PreferenceGroup preferenceGroup) {
        this.f4643c = false;
        boolean z = preferenceGroup.B0() != Integer.MAX_VALUE;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int E0 = preferenceGroup.E0();
        int i2 = 0;
        for (int i3 = 0; i3 < E0; i3++) {
            Preference D0 = preferenceGroup.D0(i3);
            if (D0.M()) {
                if (!z || i2 < preferenceGroup.B0()) {
                    arrayList.add(D0);
                } else {
                    arrayList2.add(D0);
                }
                if (D0 instanceof PreferenceGroup) {
                    PreferenceGroup preferenceGroup2 = (PreferenceGroup) D0;
                    if (preferenceGroup2.F0()) {
                        List<Preference> b2 = b(preferenceGroup2);
                        if (z && this.f4643c) {
                            throw new IllegalArgumentException("Nested expand buttons are not supported!");
                        }
                        for (Preference preference : b2) {
                            if (!z || i2 < preferenceGroup.B0()) {
                                arrayList.add(preference);
                            } else {
                                arrayList2.add(preference);
                            }
                            i2++;
                        }
                    } else {
                        continue;
                    }
                } else {
                    i2++;
                }
            }
        }
        if (z && i2 > preferenceGroup.B0()) {
            arrayList.add(a(preferenceGroup, arrayList2));
        }
        this.f4643c |= z;
        return arrayList;
    }

    public List c(PreferenceGroup preferenceGroup) {
        return b(preferenceGroup);
    }
}
