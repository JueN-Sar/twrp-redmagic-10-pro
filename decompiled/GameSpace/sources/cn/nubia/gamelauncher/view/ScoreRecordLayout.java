package cn.nubia.gamelauncher.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.adapter.ScoreWinAdapter;
import cn.nubia.gamelauncher.bean.DailyScoreBean;
import cn.nubia.gamelauncher.bean.ScoreOneBean;
import cn.nubia.gamelauncher.layoutmanager.ScrollLinearLayoutManager;
import cn.nubia.gamelauncher.util.CommonUtil;
import cn.nubia.gamelauncher.util.NubiaTrackManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class ScoreRecordLayout extends ConstraintLayout implements ScoreWinAdapter.IWindRateListener {
    String TAG;
    private String app_name;
    private String app_package_name;
    private ImageView iv_score_settings;
    private ConstraintLayout layout_score_info_2;
    private ScrollLinearLayoutManager linearLayoutManager;
    private ScoreLineChartView linecharview;
    private Context mContext;
    private boolean mGameIsEnable;
    private RecyclerView mRecyclerView;
    private boolean mSupportPart1;
    private ScoreWinAdapter scoreWinAdapter;
    private Group score_group;
    private Group score_winrate_group;
    private Group score_wins_group;
    private TextView tv_cps;
    private TextView tv_mpm;
    private TextView tv_score_disable;
    private TextView tv_score_disable_cancle;
    private TextView tv_score_enable;
    private TextView tv_score_tips;
    private View view_winrate_line;
    private WinRateChartView winrateview;

    public ScoreRecordLayout(Context context) {
        this(context, null);
    }

    public ScoreRecordLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = "ScoreRecord";
        this.mContext = context;
        initChild(context);
    }

    private void initChild(Context context) {
        LayoutInflater.from(context).inflate(R.layout.score_record_layout, this);
        this.iv_score_settings = (ImageView) findViewById(R.id.iv_score_settings);
        this.tv_score_tips = (TextView) findViewById(R.id.tv_score_tips);
        this.tv_score_enable = (TextView) findViewById(R.id.tv_score_enable);
        this.tv_score_disable = (TextView) findViewById(R.id.tv_score_disable);
        this.tv_score_disable_cancle = (TextView) findViewById(R.id.tv_score_disable_cancle);
        this.mRecyclerView = (RecyclerView) findViewById(R.id.rv_score_info_1);
        this.layout_score_info_2 = (ConstraintLayout) findViewById(R.id.layout_score_info_2);
        this.linecharview = (ScoreLineChartView) findViewById(R.id.linecharview);
        this.score_wins_group = (Group) findViewById(R.id.score_wins_group);
        this.score_group = (Group) findViewById(R.id.score_group);
        this.score_winrate_group = (Group) findViewById(R.id.score_winrate_group);
        this.tv_cps = (TextView) findViewById(R.id.tv_cps);
        this.tv_mpm = (TextView) findViewById(R.id.tv_mpm);
        this.winrateview = (WinRateChartView) findViewById(R.id.winrateview);
        this.view_winrate_line = findViewById(R.id.view_winrate_line);
        ScrollLinearLayoutManager scrollLinearLayoutManager = new ScrollLinearLayoutManager(context);
        this.linearLayoutManager = scrollLinearLayoutManager;
        this.mRecyclerView.setLayoutManager(scrollLinearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context, 1);
        dividerItemDecoration.setDrawable(new ColorDrawable(ContextCompat.getColor(context, R.color.thumbColor)));
        this.mRecyclerView.addItemDecoration(dividerItemDecoration);
        ScoreWinAdapter scoreWinAdapter = new ScoreWinAdapter(this.mContext, this);
        this.scoreWinAdapter = scoreWinAdapter;
        this.mRecyclerView.setAdapter(scoreWinAdapter);
    }

    private void showDateLayout() {
        this.mRecyclerView.setVisibility(part1HasData() ? 0 : 8);
        this.layout_score_info_2.setVisibility(part2HasData() ? 0 : 8);
        this.score_wins_group.setVisibility(this.mSupportPart1 ? 0 : 8);
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.tv_cps.getLayoutParams();
        layoutParams.horizontalBias = this.mSupportPart1 ? 0.4715f : 0.2783f;
        this.tv_cps.setLayoutParams(layoutParams);
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) this.tv_mpm.getLayoutParams();
        layoutParams2.horizontalBias = this.mSupportPart1 ? 0.7886f : 0.6116f;
        this.tv_mpm.setLayoutParams(layoutParams2);
        this.iv_score_settings.setVisibility(0);
        if (part1HasData() || part2HasData()) {
            this.tv_score_tips.setVisibility(8);
        } else {
            this.tv_score_tips.setVisibility(0);
            this.tv_score_tips.setText(this.mContext.getString(R.string.score_record_no_data));
        }
        this.tv_score_enable.setVisibility(4);
        this.tv_score_disable.setVisibility(8);
        this.tv_score_disable_cancle.setVisibility(8);
    }

    @Override // cn.nubia.gamelauncher.adapter.ScoreWinAdapter.IWindRateListener
    public void onHideWinRateView() {
        this.score_group.setVisibility(0);
        this.score_winrate_group.setVisibility(8);
        this.linecharview.setVisibility(0);
        this.winrateview.setVisibility(8);
        this.scoreWinAdapter.notifyDataSetChanged();
    }

    @Override // cn.nubia.gamelauncher.adapter.ScoreWinAdapter.IWindRateListener
    public void onShowWinRateView(List<Float> list, boolean z) {
        this.score_group.setVisibility(8);
        this.score_winrate_group.setVisibility(0);
        this.linecharview.setVisibility(8);
        this.winrateview.setVisibility(0);
        this.winrateview.setRateList(list, z);
        this.view_winrate_line.setBackgroundColor(Color.parseColor(z ? "#FF5B4D" : "#9EB5FF"));
        this.scoreWinAdapter.notifyDataSetChanged();
        if (CommonUtil.isInternalVersion() || this.app_package_name == null || this.app_name == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("app_name", this.app_name);
        bundle.putString("app_package_name", this.app_package_name);
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "win_rate_curve_view", bundle);
    }

    public boolean part1HasData() {
        return this.scoreWinAdapter.getItemCount() > 0;
    }

    public boolean part2HasData() {
        return this.linecharview.getDateSize() > 0;
    }

    public void setCurEnable(boolean z) {
        this.mGameIsEnable = z;
    }

    public void setGamePackageName(String str, String str2) {
        this.app_package_name = str;
        this.app_name = str2;
    }

    public void setPart1Data(ArrayList<ScoreOneBean> arrayList) {
        this.linearLayoutManager.setScrollEnable(false);
        this.scoreWinAdapter.clearList();
        this.scoreWinAdapter.notifyDataSetChanged();
        this.scoreWinAdapter.setList(arrayList);
        this.scoreWinAdapter.notifyDataSetChanged();
        this.linearLayoutManager.setScrollEnable(true);
    }

    public void setPart2Data(ArrayList<DailyScoreBean> arrayList) {
        this.linecharview.setDateList(arrayList);
    }

    public void setSupportPart1(boolean z) {
        this.mSupportPart1 = z;
        this.linecharview.setSupportPart1(z);
    }

    public void setSupportWinRate(boolean z) {
        Log.d(this.TAG, "setSupportWinRate " + z);
        this.scoreWinAdapter.setSupportWinRate(z);
        this.scoreWinAdapter.notifyDataSetChanged();
    }

    public void showDisableLayout() {
        this.iv_score_settings.setVisibility(0);
        this.mRecyclerView.setVisibility(8);
        this.layout_score_info_2.setVisibility(8);
        this.tv_score_tips.setVisibility(0);
        this.tv_score_enable.setVisibility(4);
        this.tv_score_disable.setVisibility(0);
        this.tv_score_disable_cancle.setVisibility(0);
        this.tv_score_tips.setText(this.mContext.getString(R.string.score_record_close_tips));
    }

    public void showEnableLayout() {
        this.iv_score_settings.setVisibility(4);
        this.mRecyclerView.setVisibility(8);
        this.layout_score_info_2.setVisibility(8);
        this.tv_score_tips.setVisibility(0);
        this.tv_score_enable.setVisibility(0);
        this.tv_score_disable.setVisibility(8);
        this.tv_score_disable_cancle.setVisibility(8);
        this.tv_score_tips.setText(this.mContext.getString(R.string.score_record_open_tips));
    }

    public void updateLayout() {
        this.score_group.setVisibility(0);
        this.score_winrate_group.setVisibility(8);
        this.linecharview.setVisibility(0);
        this.winrateview.setVisibility(8);
        if (this.mGameIsEnable) {
            showDateLayout();
        } else {
            showEnableLayout();
        }
    }
}
