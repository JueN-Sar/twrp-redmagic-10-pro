package cn.nubia.gamecenter.settings.compatible;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gamecenter.settings.GcsAnimationUtil;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.utils.LogUtil;

/* loaded from: classes.dex */
public class AnimationPreferenceFragment extends PreferenceFragment {
    private static final String TAG = "AnimationPreferenceFragment";
    private RecyclerView mDashboard;
    private View rootView;

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        LogUtil.d(TAG, "CWL onHiddenChanged hidden = " + z);
        if (z) {
            new Handler().post(new Runnable() { // from class: cn.nubia.gamecenter.settings.compatible.AnimationPreferenceFragment.1
                @Override // java.lang.Runnable
                public void run() {
                    AnimationPreferenceFragment.this.rootView.setAlpha(0.0f);
                }
            });
        } else {
            setAnimation(this.mDashboard);
        }
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        View view2 = getView();
        this.rootView = view2;
        view2.setAlpha(0.0f);
        RecyclerView recyclerView = (RecyclerView) this.rootView.findViewById(R.id.recycler_view);
        this.mDashboard = recyclerView;
        setAnimation(recyclerView);
    }

    public void setAnimation(final RecyclerView recyclerView) {
        if (recyclerView == null || recyclerView.getAdapter() == null) {
            return;
        }
        new Handler().post(new Runnable() { // from class: cn.nubia.gamecenter.settings.compatible.AnimationPreferenceFragment.2
            @Override // java.lang.Runnable
            public void run() {
                if (recyclerView.getAdapter() == null) {
                    return;
                }
                for (int i = 0; i < recyclerView.getAdapter().getItemCount(); i++) {
                    GcsAnimationUtil.setGcsRedItemAlpha(AnimationPreferenceFragment.this.rootView);
                    GcsAnimationUtil.setGcsItemTranslationX(recyclerView.getChildAt(i), i);
                }
            }
        });
    }
}
