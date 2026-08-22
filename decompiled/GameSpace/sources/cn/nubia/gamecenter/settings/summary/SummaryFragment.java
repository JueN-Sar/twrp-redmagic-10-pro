package cn.nubia.gamecenter.settings.summary;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import cn.nubia.gamecenter.settings.BaseFragment;
import cn.nubia.gamecenter.settings.CategoryInfo;
import cn.nubia.gamecenter.settings.FragmentInterface;
import cn.nubia.gamecenter.settings.GcsAnimationUtil;
import cn.nubia.gamecenter.settings.R;

/* loaded from: classes.dex */
public class SummaryFragment extends BaseFragment implements FragmentInterface {
    private static final String TAG = "SummaryFragment";
    private SummaryFragmentHelper mSummaryFragmentHelper;
    private View mSummaryStatusView;
    private boolean m_bWeekMode = false;

    public static CategoryInfo getCategoryInfo() {
        return new CategoryInfo(SummaryFragment.class, R.drawable.user_login, R.string.gcs_gamecenter_menu_game_status);
    }

    @Override // cn.nubia.gamecenter.settings.BaseFragment
    protected View createMainView() {
        if (this.m_activity == null) {
            return null;
        }
        View inflate = View.inflate(this.m_activity, R.layout.gcs_gamecenter_fragment_summary, null);
        SummaryFragmentHelper summaryFragmentHelper = new SummaryFragmentHelper(getActivity().getApplicationContext(), this, inflate);
        this.mSummaryFragmentHelper = summaryFragmentHelper;
        summaryFragmentHelper.setWeekMode(this.m_bWeekMode);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        this.mSummaryFragmentHelper.releaseAnimatorRes();
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            new Handler().post(new Runnable() { // from class: cn.nubia.gamecenter.settings.summary.SummaryFragment.1
                @Override // java.lang.Runnable
                public void run() {
                    SummaryFragment.this.mSummaryStatusView.setAlpha(0.0f);
                }
            });
        } else {
            GcsAnimationUtil.setGcsItemTranslationY(this.mSummaryStatusView);
            GcsAnimationUtil.setGcsItemAlpha(this.mSummaryStatusView);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mSummaryFragmentHelper.stopLoadGameParms();
    }

    @Override // cn.nubia.gamecenter.settings.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        SummaryFragmentHelper summaryFragmentHelper = this.mSummaryFragmentHelper;
        if (summaryFragmentHelper != null) {
            summaryFragmentHelper.onResume();
        }
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        View findViewById = view.findViewById(R.id.pager);
        this.mSummaryStatusView = findViewById;
        GcsAnimationUtil.setGcsItemTranslationY(findViewById);
    }

    @Override // cn.nubia.gamecenter.settings.BaseFragment, cn.nubia.gamecenter.settings.FragmentInterface
    public void setTestMode() {
        this.mSummaryFragmentHelper.setTestMode();
    }

    public void setWeekMode(boolean z) {
        SummaryFragmentHelper summaryFragmentHelper = this.mSummaryFragmentHelper;
        if (summaryFragmentHelper == null) {
            this.m_bWeekMode = z;
        } else {
            summaryFragmentHelper.setWeekMode(z);
        }
    }
}
