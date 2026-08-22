package androidx.fragment.app;

import android.R;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

/* loaded from: classes.dex */
public class ListFragment extends Fragment {
    private final Handler i0 = new Handler();
    private final Runnable j0 = new Runnable() { // from class: androidx.fragment.app.ListFragment.1
        @Override // java.lang.Runnable
        public void run() {
            ListView listView = ListFragment.this.m0;
            listView.focusableViewAvailable(listView);
        }
    };
    private final AdapterView.OnItemClickListener k0 = new AdapterView.OnItemClickListener() { // from class: androidx.fragment.app.ListFragment.2
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
            ListFragment.this.b2((ListView) adapterView, view, i2, j2);
        }
    };
    ListAdapter l0;
    ListView m0;
    View n0;
    TextView o0;
    View p0;
    View q0;
    CharSequence r0;
    boolean s0;

    private void a2() {
        if (this.m0 != null) {
            return;
        }
        View h0 = h0();
        if (h0 == null) {
            throw new IllegalStateException("Content view not yet created");
        }
        if (h0 instanceof ListView) {
            this.m0 = (ListView) h0;
        } else {
            TextView textView = (TextView) h0.findViewById(16711681);
            this.o0 = textView;
            if (textView == null) {
                this.n0 = h0.findViewById(R.id.empty);
            } else {
                textView.setVisibility(8);
            }
            this.p0 = h0.findViewById(16711682);
            this.q0 = h0.findViewById(16711683);
            View findViewById = h0.findViewById(R.id.list);
            if (!(findViewById instanceof ListView)) {
                if (findViewById != null) {
                    throw new RuntimeException("Content has view with id attribute 'android.R.id.list' that is not a ListView class");
                }
                throw new RuntimeException("Your content must have a ListView whose id attribute is 'android.R.id.list'");
            }
            ListView listView = (ListView) findViewById;
            this.m0 = listView;
            View view = this.n0;
            if (view != null) {
                listView.setEmptyView(view);
            } else {
                CharSequence charSequence = this.r0;
                if (charSequence != null) {
                    this.o0.setText(charSequence);
                    this.m0.setEmptyView(this.o0);
                }
            }
        }
        this.s0 = true;
        this.m0.setOnItemClickListener(this.k0);
        ListAdapter listAdapter = this.l0;
        if (listAdapter != null) {
            this.l0 = null;
            c2(listAdapter);
        } else if (this.p0 != null) {
            d2(false, false);
        }
        this.i0.post(this.j0);
    }

    private void d2(boolean z, boolean z2) {
        a2();
        View view = this.p0;
        if (view == null) {
            throw new IllegalStateException("Can't be used with a custom content view");
        }
        if (this.s0 == z) {
            return;
        }
        this.s0 = z;
        if (z) {
            if (z2) {
                view.startAnimation(AnimationUtils.loadAnimation(z(), R.anim.fade_out));
                this.q0.startAnimation(AnimationUtils.loadAnimation(z(), R.anim.fade_in));
            } else {
                view.clearAnimation();
                this.q0.clearAnimation();
            }
            this.p0.setVisibility(8);
            this.q0.setVisibility(0);
            return;
        }
        if (z2) {
            view.startAnimation(AnimationUtils.loadAnimation(z(), R.anim.fade_in));
            this.q0.startAnimation(AnimationUtils.loadAnimation(z(), R.anim.fade_out));
        } else {
            view.clearAnimation();
            this.q0.clearAnimation();
        }
        this.p0.setVisibility(0);
        this.q0.setVisibility(8);
    }

    @Override // androidx.fragment.app.Fragment
    public View H0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Context D1 = D1();
        FrameLayout frameLayout = new FrameLayout(D1);
        LinearLayout linearLayout = new LinearLayout(D1);
        linearLayout.setId(16711682);
        linearLayout.setOrientation(1);
        linearLayout.setVisibility(8);
        linearLayout.setGravity(17);
        linearLayout.addView(new ProgressBar(D1, null, R.attr.progressBarStyleLarge), new FrameLayout.LayoutParams(-2, -2));
        frameLayout.addView(linearLayout, new FrameLayout.LayoutParams(-1, -1));
        FrameLayout frameLayout2 = new FrameLayout(D1);
        frameLayout2.setId(16711683);
        TextView textView = new TextView(D1);
        textView.setId(16711681);
        textView.setGravity(17);
        frameLayout2.addView(textView, new FrameLayout.LayoutParams(-1, -1));
        ListView listView = new ListView(D1);
        listView.setId(R.id.list);
        listView.setDrawSelectorOnTop(false);
        frameLayout2.addView(listView, new FrameLayout.LayoutParams(-1, -1));
        frameLayout.addView(frameLayout2, new FrameLayout.LayoutParams(-1, -1));
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        return frameLayout;
    }

    @Override // androidx.fragment.app.Fragment
    public void K0() {
        this.i0.removeCallbacks(this.j0);
        this.m0 = null;
        this.s0 = false;
        this.q0 = null;
        this.p0 = null;
        this.n0 = null;
        this.o0 = null;
        super.K0();
    }

    @Override // androidx.fragment.app.Fragment
    public void a1(View view, Bundle bundle) {
        super.a1(view, bundle);
        a2();
    }

    public void b2(ListView listView, View view, int i2, long j2) {
    }

    public void c2(ListAdapter listAdapter) {
        boolean z = this.l0 != null;
        this.l0 = listAdapter;
        ListView listView = this.m0;
        if (listView != null) {
            listView.setAdapter(listAdapter);
            if (this.s0 || z) {
                return;
            }
            d2(true, E1().getWindowToken() != null);
        }
    }
}
