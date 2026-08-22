package cn.nubia.gamecenter.settings.summary;

import android.text.TextUtils;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.TextView;
import androidx.recyclerview.widget.ItemTouchHelper;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.helper.AnimatorHelper;
import cn.nubia.gamecenter.settings.records.utils.HighLightsUtils;
import cn.nubia.gamecenter.settings.summary.SummaryPageHelper;
import cn.nubia.gamecenter.settings.widget.ClipImageView;
import cn.nubia.gamecenter.settings.widget.GradientTextView;
import cn.nubia.gamecenter.settings.widget.MultiStateInterpolator;

/* loaded from: classes.dex */
public class SummaryPercentHelper extends SummaryPageHelper {
    private static final String TAG = "SummaryPercentHelper";
    private final AnimatorHelper.Item[] ITEMs;
    private final AnimatorHelper.Item[] ITEMs_indicator;
    private int PIE_DELAY;
    private int PIE_TIME;
    private boolean m_bFirstUpdate;
    private AnimatorHelper m_helper;
    private GradientTextView m_hourTime;
    private AnimatorHelper.Item m_hour_number;
    private AnimatorHelper m_indicator;
    private GradientTextView m_miniteTime;
    private AnimatorHelper.Item m_minite_number;
    private TextView m_percent1Summary;
    private TextView m_percent1Title;
    private TextView m_percent2Summary;
    private TextView m_percent2Title;
    private TextView m_percent3Summary;
    private TextView m_percent3Title;
    private TextView m_percentOtherSummary;
    private TextView m_percentOtherTitle;
    private AnimatorHelper.Item m_pie_1;
    private AnimatorHelper.Item m_pie_2;
    private AnimatorHelper.Item m_pie_3;
    private AnimatorHelper.Item m_pie_4;
    private Interpolator m_scrollTipInterpolator;
    private AnimatorHelper.Item m_scroll_tip_alpha;
    private AnimatorHelper.Item m_scroll_tip_move;
    private static final float[] INDICATOR_CURVE = {0.2f, 0.08f, 0.49f, 1.0f};
    private static final int[] petId = {R.id.summary_percent_1, R.id.summary_percent_2, R.id.summary_percent_3, R.id.summary_percent_4};
    private static final float[] COMMON_CURVE = {0.42f, 0.0f, 0.58f, 1.0f};

    SummaryPercentHelper(View view, SummaryPageHelper.Callback callback, int i) {
        super(view, callback, i);
        this.m_bFirstUpdate = true;
        this.m_pie_1 = new AnimatorHelper.Item(R.id.summary_percent_1, AnimatorHelper.Item.CUST_PERCENT_END, new float[]{0.0f, 360.0f}, null, 600, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
        this.m_pie_2 = new AnimatorHelper.Item(R.id.summary_percent_2, AnimatorHelper.Item.CUST_PERCENT_END, new float[]{0.0f, 360.0f}, null, 600, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
        this.m_pie_3 = new AnimatorHelper.Item(R.id.summary_percent_3, AnimatorHelper.Item.CUST_PERCENT_END, new float[]{0.0f, 360.0f}, null, 600, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
        this.m_pie_4 = new AnimatorHelper.Item(R.id.summary_percent_4, AnimatorHelper.Item.CUST_PERCENT_END, new float[]{0.0f, 360.0f}, null, 600, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
        this.m_hour_number = new AnimatorHelper.Item(R.id.percent_hour, AnimatorHelper.Item.CUST_NUMBER_GROW, new float[]{0.0f, 0.0f}, null, 750, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
        this.m_minite_number = new AnimatorHelper.Item(R.id.percent_minite, AnimatorHelper.Item.CUST_NUMBER_GROW, new float[]{0.0f, 0.0f}, null, 750, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
        float[] fArr = COMMON_CURVE;
        this.ITEMs = new AnimatorHelper.Item[]{new AnimatorHelper.Item(R.id.percent_lighting, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 350, 0), new AnimatorHelper.Item(R.id.percent_lighting, AnimatorHelper.Item.SCALEX, new float[]{0.0f, 1.0f}, fArr, 350, 0), new AnimatorHelper.Item(R.id.percent_total_time, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 300, 200), new AnimatorHelper.Item(R.id.percent_list_title, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 300, 200), new AnimatorHelper.Item(R.id.percent_1, AnimatorHelper.Item.TRANSLATIONX, new float[]{50.0f, 0.0f}, fArr, 300, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION), new AnimatorHelper.Item(R.id.percent_1, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 300, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION), new AnimatorHelper.Item(R.id.percent_2, AnimatorHelper.Item.TRANSLATIONX, new float[]{50.0f, 0.0f}, fArr, 300, 350), new AnimatorHelper.Item(R.id.percent_2, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 300, 350), new AnimatorHelper.Item(R.id.percent_3, AnimatorHelper.Item.TRANSLATIONX, new float[]{50.0f, 0.0f}, fArr, 300, 450), new AnimatorHelper.Item(R.id.percent_3, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 300, 450), new AnimatorHelper.Item(R.id.percent_4, AnimatorHelper.Item.TRANSLATIONX, new float[]{50.0f, 0.0f}, fArr, 300, 550), new AnimatorHelper.Item(R.id.percent_4, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 300, 550), this.m_pie_1, this.m_pie_2, this.m_pie_3, this.m_pie_4, new AnimatorHelper.Item(R.id.summary_percent_bg_1, AnimatorHelper.Item.SCALEX, new float[]{0.95f, 1.0f}, fArr, 300, 0), new AnimatorHelper.Item(R.id.summary_percent_bg_1, AnimatorHelper.Item.SCALEY, new float[]{0.95f, 1.0f}, fArr, 300, 0), new AnimatorHelper.Item(R.id.summary_percent_bg_1, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 300, 50), new AnimatorHelper.Item(R.id.summary_percent_bg_2, AnimatorHelper.Item.SCALEX, new float[]{1.05f, 1.0f}, fArr, 300, 0), new AnimatorHelper.Item(R.id.summary_percent_bg_2, AnimatorHelper.Item.SCALEY, new float[]{1.05f, 1.0f}, fArr, 300, 0), new AnimatorHelper.Item(R.id.summary_percent_bg_2, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 300, 50), new AnimatorHelper.Item(R.id.summary_percent_bg_3, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 300, 200), new AnimatorHelper.Item(R.id.summary_percent_bg_circle, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 300, 200), this.m_hour_number, this.m_minite_number};
        this.m_scroll_tip_move = new AnimatorHelper.Item(R.id.summary_scroll_tip, AnimatorHelper.Item.TRANSLATIONY, new float[]{0.0f, 20.0f}, null, HighLightsUtils.NORMAL_WIDTH, 2000);
        AnimatorHelper.Item item = new AnimatorHelper.Item(R.id.summary_scroll_tip, AnimatorHelper.Item.ALPHA, new float[]{1.0f, 0.6f}, null, HighLightsUtils.NORMAL_WIDTH, 2000);
        this.m_scroll_tip_alpha = item;
        this.ITEMs_indicator = new AnimatorHelper.Item[]{this.m_scroll_tip_move, item};
        this.PIE_TIME = 600;
        this.PIE_DELAY = ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
        init();
    }

    private void adjustPercent(int[] iArr) {
        if (iArr == null || iArr.length != 5) {
            return;
        }
        int i = iArr[0];
        if (i == 0) {
            iArr[1] = 0;
            iArr[2] = 0;
            iArr[3] = 0;
            iArr[4] = 0;
            return;
        }
        int i2 = iArr[1];
        if (i2 == 0) {
            iArr[0] = 100;
            iArr[2] = 0;
            iArr[3] = 0;
            iArr[4] = 0;
            return;
        }
        int i3 = iArr[2];
        if (i3 == 0) {
            int i4 = (100 - i) - i2;
            int i5 = i4 / 2;
            iArr[0] = i + (i4 % 2) + i5;
            iArr[1] = i2 + i5;
            iArr[3] = 0;
            iArr[4] = 0;
            return;
        }
        int i6 = iArr[3];
        if (i6 == 0) {
            int i7 = ((100 - i) - i2) - i3;
            int i8 = i7 / 3;
            iArr[0] = i + (i7 % 3) + i8;
            iArr[1] = i2 + i8;
            iArr[2] = i3 + i8;
            iArr[4] = 0;
            return;
        }
        if (iArr[4] == 0) {
            int i9 = (((100 - i) - i2) - i3) - i6;
            int i10 = i9 / 4;
            iArr[0] = i + (i9 % 4) + i10;
            iArr[1] = i2 + i10;
            iArr[2] = i3 + i10;
            iArr[3] = i6 + i10;
            return;
        }
        int i11 = (((100 - i) - i2) - i3) - i6;
        if (i11 <= 0) {
            int i12 = i11 / 4;
            iArr[0] = i + (i11 % 4) + i12;
            iArr[1] = i2 + i12;
            iArr[2] = i3 + i12;
            iArr[3] = i6 + i12;
            iArr[4] = 0;
        }
    }

    private void adjustPieParams(int i, int i2, int i3) {
        int safePercent = safePercent(i);
        adjustPieParams(this.m_pie_1, getPie(0), 0, safePercent);
        int safePercent2 = safePercent(i2 + safePercent);
        adjustPieParams(this.m_pie_2, getPie(1), safePercent, safePercent2);
        int safePercent3 = safePercent(i3 + safePercent2);
        adjustPieParams(this.m_pie_3, getPie(2), safePercent2, safePercent3);
        if (safePercent3 == 0 || safePercent3 > 100) {
            safePercent3 = 100;
        }
        adjustPieParams(this.m_pie_4, getPie(3), safePercent3, 100);
        ClipImageView pie = getPie(0);
        if (safePercent != 0 || safePercent2 != 0 || safePercent3 != 100) {
            pie.setAlpha(1.0f);
        } else {
            adjustPieParams(this.m_pie_1, pie, 0, 100);
            pie.setAlpha(0.6f);
        }
    }

    private void adjustPieParams(AnimatorHelper.Item item, ClipImageView clipImageView, int i, int i2) {
        int i3 = (i * 360) / 100;
        int i4 = (i2 * 360) / 100;
        int i5 = this.PIE_TIME;
        int i6 = (i5 * i3) / 360;
        clipImageView.setDegree(i3, i3);
        item.setParams(i3, i4);
        item.setDuration(((i5 * i4) / 360) - i6);
        item.setDelay(this.PIE_DELAY + i6);
    }

    private void adjustTimeNumber(int i, int i2) {
        this.m_hour_number.setParams(new float[]{0.0f, i});
        this.m_minite_number.setParams(new float[]{0.0f, i2});
    }

    private GradientTextView getHourTime() {
        if (this.m_hourTime == null) {
            this.m_hourTime = (GradientTextView) this.m_root.findViewById(R.id.percent_hour);
        }
        return this.m_hourTime;
    }

    private GradientTextView getMiniteTime() {
        if (this.m_miniteTime == null) {
            this.m_miniteTime = (GradientTextView) this.m_root.findViewById(R.id.percent_minite);
        }
        return this.m_miniteTime;
    }

    private TextView getPercent1Summary() {
        if (this.m_percent1Summary == null) {
            this.m_percent1Summary = (TextView) this.m_root.findViewById(R.id.percent_1_summary);
        }
        return this.m_percent1Summary;
    }

    private TextView getPercent1Title() {
        if (this.m_percent1Title == null) {
            this.m_percent1Title = (TextView) this.m_root.findViewById(R.id.percent_1_title);
        }
        return this.m_percent1Title;
    }

    private TextView getPercent2Summary() {
        if (this.m_percent2Summary == null) {
            this.m_percent2Summary = (TextView) this.m_root.findViewById(R.id.percent_2_summary);
        }
        return this.m_percent2Summary;
    }

    private TextView getPercent2Title() {
        if (this.m_percent2Title == null) {
            this.m_percent2Title = (TextView) this.m_root.findViewById(R.id.percent_2_title);
        }
        return this.m_percent2Title;
    }

    private TextView getPercent3Summary() {
        if (this.m_percent3Summary == null) {
            this.m_percent3Summary = (TextView) this.m_root.findViewById(R.id.percent_3_summary);
        }
        return this.m_percent3Summary;
    }

    private TextView getPercent3Title() {
        if (this.m_percent3Title == null) {
            this.m_percent3Title = (TextView) this.m_root.findViewById(R.id.percent_3_title);
        }
        return this.m_percent3Title;
    }

    private TextView getPercentOtherSummary() {
        if (this.m_percentOtherSummary == null) {
            this.m_percentOtherSummary = (TextView) this.m_root.findViewById(R.id.percent_4_summary);
        }
        return this.m_percentOtherSummary;
    }

    private TextView getPercentOtherTitle() {
        if (this.m_percentOtherTitle == null) {
            this.m_percentOtherTitle = (TextView) this.m_root.findViewById(R.id.percent_4_title);
        }
        return this.m_percentOtherTitle;
    }

    private ClipImageView getPie(int i) {
        if (i < 0) {
            return null;
        }
        int[] iArr = petId;
        if (i >= iArr.length) {
            return null;
        }
        return (ClipImageView) this.m_root.findViewById(iArr[i]);
    }

    private Interpolator getScrollTipInterpolator() {
        if (this.m_scrollTipInterpolator == null) {
            float[] fArr = INDICATOR_CURVE;
            this.m_scrollTipInterpolator = new MultiStateInterpolator(new PathInterpolator(fArr[0], fArr[1], fArr[2], fArr[3]), 0.47916666f, 0.8333333f);
        }
        return this.m_scrollTipInterpolator;
    }

    private void init() {
        int dimensionPixelSize = this.m_root.getResources().getDimensionPixelSize(R.dimen.gcs_summary_percent_number);
        if (getHourTime() != null) {
            getHourTime().setTextSize(dimensionPixelSize);
            getHourTime().setTextColor(-1);
        }
        if (getMiniteTime() != null) {
            getMiniteTime().setTextSize(dimensionPixelSize);
            getMiniteTime().setTextColor(-1);
        }
    }

    private String percentToString(int i) {
        return i > 0 ? Integer.toString(i) + "%" : "0%";
    }

    private void resetPie() {
        getPie(0).setDegree(0, 0);
        getPie(1).setDegree(0, 0);
        getPie(2).setDegree(0, 0);
        getPie(3).setDegree(0, 0);
    }

    private void resetTotalTime() {
        if (getHourTime() != null) {
            getHourTime().setNumber(0.0f);
        }
        if (getMiniteTime() != null) {
            getMiniteTime().setNumber(0.0f);
        }
    }

    private int safePercent(int i) {
        if (i > 100) {
            return 100;
        }
        return i;
    }

    private void startAllAnimations(int i, int[] iArr) {
        View view;
        if (this.m_root == null || (view = this.m_root) == null) {
            return;
        }
        if (iArr != null && iArr.length >= 3) {
            adjustPieParams(iArr[0], iArr[1], iArr[2]);
        }
        adjustTimeNumber(i / 60, i % 60);
        if (this.m_helper == null) {
            this.m_helper = new AnimatorHelper(view, this.ITEMs);
        }
        this.m_helper.start();
    }

    private void startIndicatorAnimation(View view) {
        if (this.m_indicator == null) {
            this.m_scroll_tip_move.setRepeatCount(-1);
            this.m_scroll_tip_alpha.setRepeatCount(-1);
            this.m_scroll_tip_move.setInterpolator(getScrollTipInterpolator());
            this.m_scroll_tip_alpha.setInterpolator(getScrollTipInterpolator());
            this.m_indicator = new AnimatorHelper(view, this.ITEMs_indicator);
        }
        this.m_indicator.start();
    }

    private void updateRanks(int i, String str, int i2, String str2, int i3, String str3, int i4, String str4, int i5, String str5) {
        if (getPercent1Title() != null) {
            getPercent1Title().setText(percentToString(i));
        }
        if (getPercent1Summary() != null) {
            TextView percent1Summary = getPercent1Summary();
            if (i == 0) {
                str = "- -";
            }
            percent1Summary.setText(str);
        }
        if (getPercent2Title() != null) {
            getPercent2Title().setText(percentToString(i2));
        }
        if (getPercent2Summary() != null) {
            TextView percent2Summary = getPercent2Summary();
            if (i2 == 0) {
                str2 = "- -";
            }
            percent2Summary.setText(str2);
        }
        if (getPercent3Title() != null) {
            getPercent3Title().setText(percentToString(i3));
        }
        if (getPercent3Summary() != null) {
            TextView percent3Summary = getPercent3Summary();
            if (i3 == 0) {
                str3 = "- -";
            }
            percent3Summary.setText(str3);
        }
        int i6 = ((100 - i) - i2) - i3;
        if (i6 == 100) {
            i6 = 0;
        }
        if (!TextUtils.isEmpty(str5) || i4 == 0) {
            if (getPercentOtherTitle() != null) {
                getPercentOtherTitle().setText(percentToString(i6));
            }
            if (getPercentOtherSummary() != null) {
                getPercentOtherSummary().setText(i6 != 0 ? getString(R.string.gcs_other) : "- -");
                return;
            }
            return;
        }
        if (getPercentOtherTitle() != null) {
            getPercentOtherTitle().setText(percentToString(i4));
        }
        if (getPercentOtherSummary() != null) {
            getPercentOtherSummary().setText(str4);
        }
    }

    @Override // cn.nubia.gamecenter.settings.summary.SummaryPageHelper
    protected String getString(int i) {
        return this.m_root.getResources().getString(i);
    }

    public void releaseAnimatorRes() {
        AnimatorHelper animatorHelper = this.m_helper;
        if (animatorHelper != null) {
            animatorHelper.cancel();
        }
        AnimatorHelper animatorHelper2 = this.m_indicator;
        if (animatorHelper2 != null) {
            animatorHelper2.cancel();
        }
    }

    @Override // cn.nubia.gamecenter.settings.summary.SummaryPageHelper
    public void update(SummaryDataHelper summaryDataHelper) {
        if (this.m_bFirstUpdate) {
            if (!isCurrentPage()) {
                return;
            } else {
                this.m_bFirstUpdate = false;
            }
        }
        int[] iArr = {summaryDataHelper.getRankPercent(1), summaryDataHelper.getRankPercent(2), summaryDataHelper.getRankPercent(3), summaryDataHelper.getRankPercent(4), summaryDataHelper.getRankPercent(5)};
        adjustPercent(iArr);
        resetTotalTime();
        updateRanks(iArr[0], summaryDataHelper.getRankName(1), iArr[1], summaryDataHelper.getRankName(2), iArr[2], summaryDataHelper.getRankName(3), iArr[3], summaryDataHelper.getRankName(4), iArr[4], summaryDataHelper.getRankName(5));
        resetPie();
        startAllAnimations(summaryDataHelper.getTotalTime(), iArr);
        startIndicatorAnimation(this.m_root);
    }
}
