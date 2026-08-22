package cn.nubia.gamecenter.settings.summary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewpager.widget.PagerAdapter;
import cn.nubia.gamecenter.settings.BaseFragment;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.summary.SummaryDataHelper;
import cn.nubia.gamecenter.settings.summary.SummaryPageHelper;
import cn.nubia.gamecenter.settings.summary.entities.GameAppInfo;
import cn.nubia.gamecenter.settings.summary.entities.GameTimeInfo;
import cn.nubia.gamecenter.settings.summary.entities.OneGameTimeAndLaunchTimesInfo;
import cn.nubia.gamecenter.settings.summary.presenter.GameParmsPresenterImpl;
import cn.nubia.gamecenter.settings.summary.presenter.ICallback;
import cn.nubia.gamecenter.settings.summary.presenter.IGameParmsPresenter;
import cn.nubia.gamecenter.settings.widget.VerticalViewPager;
import cn.nubia.gamecenter.settings.widget.ViewPager;
import cn.nubia.settings.trackclient.NubiaTrackManager;
import java.util.List;

/* loaded from: classes.dex */
public class SummaryFragmentHelper implements SummaryDataHelper.ModeChangeListener, ICallback, SummaryPageHelper.Callback {
    private static final int INDEX_PAGE_KEYWORD = 0;
    private static final int INDEX_PAGE_PERCENT = 1;
    private static final int INDEX_PAGE_RANK = 2;
    private static final String TAG = "SummaryFragmentHelper";
    private IGameParmsPresenter mGameParmsPresenter;
    private PagerAdapter m_adapter;
    private final Context m_context;
    private final BaseFragment m_frag;
    private final SummaryDataHelper m_helper;
    private VerticalViewPager m_pager;
    private CheckedTextView m_panel_day;
    private CheckedTextView m_panel_week;
    private View m_root;
    private TextView m_title;
    private static final int[] PAGE_TITLE = {R.string.gcs_summary_keyword_title, R.string.gcs_summary_percent_title, R.string.gcs_summary_rank_title};
    private static final int[] PAGE_INDICATOR = {R.id.gcs_summary_indicator_1, R.id.gcs_summary_indicator_2, R.id.gcs_summary_indicator_3};
    private static final int[] PAGE_ID = {R.layout.gcs_gamecenter_fragment_summary_keyword, R.layout.gcs_gamecenter_fragment_summary_percent, R.layout.gcs_gamecenter_fragment_summary_rank};
    private static final String[] PAGE_DEMO_TEXT = {"此界面[ 周关键词 ]正在开发中，由王亚杰负责，谢谢~-~", "此界面[ 时长占比 ]正在开发中，由王亚杰负责，谢谢~-~", "此界面[ 时长排行 ]正在开发中，由王亚杰负责，谢谢~-~"};
    private int m_timeMode = 1;
    private final View.OnClickListener m_panelDayClickListener = new View.OnClickListener() { // from class: cn.nubia.gamecenter.settings.summary.SummaryFragmentHelper.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "gamespace_today_click", "switch_on", true);
            SummaryFragmentHelper.this.m_helper.setMode(1);
        }
    };
    private final View.OnClickListener m_panelWeekClickListener = new View.OnClickListener() { // from class: cn.nubia.gamecenter.settings.summary.SummaryFragmentHelper.2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "gamespace_last_seven_day_click", "switch_on", true);
            SummaryFragmentHelper.this.m_helper.setMode(2);
        }
    };
    private ViewPager.OnPageChangeListener m_pageChangeListener = new ViewPager.OnPageChangeListener() { // from class: cn.nubia.gamecenter.settings.summary.SummaryFragmentHelper.3
        @Override // cn.nubia.gamecenter.settings.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
            if (SummaryFragmentHelper.this.getViewPager() != null && i == 0) {
                SummaryFragmentHelper summaryFragmentHelper = SummaryFragmentHelper.this;
                summaryFragmentHelper.updatePage(summaryFragmentHelper.getViewPager().getCurrentItem());
            }
        }

        @Override // cn.nubia.gamecenter.settings.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }

        @Override // cn.nubia.gamecenter.settings.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            SummaryFragmentHelper.this.updateTitle();
            SummaryFragmentHelper.this.updatePageIndicator();
        }
    };

    private static class SummaryPagerAdapter extends PagerAdapter {
        private final Context m_context;
        private final SummaryDataHelper m_data;
        private final SummaryFragmentHelper m_helper;
        View[] m_items = new View[SummaryFragmentHelper.PAGE_ID.length];
        SummaryPageHelper[] m_helpers = new SummaryPageHelper[SummaryFragmentHelper.PAGE_ID.length];
        private boolean m_bFirstUpdatePercent = true;

        public SummaryPagerAdapter(SummaryFragmentHelper summaryFragmentHelper, Context context, SummaryDataHelper summaryDataHelper) {
            this.m_helper = summaryFragmentHelper;
            this.m_context = context;
            this.m_data = summaryDataHelper;
        }

        private View getPage(int i) {
            if (i < 0) {
                return null;
            }
            View[] viewArr = this.m_items;
            if (i >= viewArr.length) {
                return null;
            }
            if (viewArr[i] == null) {
                viewArr[i] = loadPage(i);
            }
            return this.m_items[i];
        }

        private View loadPage(int i) {
            View view = null;
            if (i >= 0 && i < SummaryFragmentHelper.PAGE_ID.length && (view = LayoutInflater.from(this.m_context).inflate(SummaryFragmentHelper.PAGE_ID[i], (ViewGroup) null)) != null) {
                this.m_items[i] = view;
                if (i == 0) {
                    this.m_helpers[i] = new SummaryKeywordHelper(view, this.m_helper, i);
                    this.m_helpers[i].update(this.m_data);
                } else if (1 == i) {
                    this.m_helpers[i] = new SummaryPercentHelper(view, this.m_helper, i);
                    this.m_helpers[i].update(this.m_data);
                } else if (2 == i) {
                    this.m_helpers[i] = new SummaryRankHelper(view, this.m_helper, i);
                    this.m_helpers[i].update(this.m_data);
                } else {
                    setText(view, SummaryFragmentHelper.PAGE_DEMO_TEXT[i]);
                }
            }
            return view;
        }

        private View removePage(int i) {
            if (i >= 0) {
                View[] viewArr = this.m_items;
                if (i < viewArr.length) {
                    View view = viewArr[i];
                    if (view != null) {
                        viewArr[i] = null;
                    }
                    return view;
                }
            }
            return null;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            View removePage = removePage(i);
            if (removePage != null) {
                viewGroup.removeView(removePage);
            }
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return this.m_items.length;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View page = getPage(i);
            if (page != null) {
                viewGroup.addView(page);
            }
            return page;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public void releaseAnimatorRes() {
            for (SummaryPageHelper summaryPageHelper : this.m_helpers) {
                if (summaryPageHelper != null) {
                    if (summaryPageHelper instanceof SummaryKeywordHelper) {
                        ((SummaryKeywordHelper) summaryPageHelper).releaseAnimatorRes();
                    } else if (summaryPageHelper instanceof SummaryPercentHelper) {
                        ((SummaryPercentHelper) summaryPageHelper).releaseAnimatorRes();
                    }
                }
            }
        }

        protected void setText(View view, String str) {
            View findViewById = view.findViewById(R.id.empty_text);
            if (findViewById == null || !(findViewById instanceof TextView)) {
                return;
            }
            ((TextView) findViewById).setText(str);
        }

        public void update() {
            for (SummaryPageHelper summaryPageHelper : this.m_helpers) {
                if (summaryPageHelper != null) {
                    summaryPageHelper.update(this.m_data);
                }
            }
        }

        public void update(int i) {
            SummaryPageHelper summaryPageHelper;
            SummaryPageHelper[] summaryPageHelperArr = this.m_helpers;
            if (summaryPageHelperArr == null || i >= summaryPageHelperArr.length || (summaryPageHelper = summaryPageHelperArr[i]) == null || !(summaryPageHelper instanceof SummaryPercentHelper) || !this.m_bFirstUpdatePercent) {
                return;
            }
            this.m_bFirstUpdatePercent = false;
            summaryPageHelper.update(this.m_data);
        }
    }

    public SummaryFragmentHelper(Context context, BaseFragment baseFragment, View view) {
        this.m_context = context;
        this.mGameParmsPresenter = new GameParmsPresenterImpl(context, this);
        this.m_frag = baseFragment;
        this.m_root = view;
        SummaryDataHelper summaryDataHelper = new SummaryDataHelper();
        this.m_helper = summaryDataHelper;
        summaryDataHelper.addOnChangeListener(this);
        init();
    }

    private PagerAdapter getAdapter() {
        if (this.m_adapter == null) {
            this.m_adapter = new SummaryPagerAdapter(this, this.m_frag.getContext(), this.m_helper);
        }
        return this.m_adapter;
    }

    private CheckedTextView getPanel_day() {
        if (this.m_panel_day == null) {
            CheckedTextView checkedTextView = (CheckedTextView) this.m_root.findViewById(R.id.gcs_summary_header_panel_day);
            this.m_panel_day = checkedTextView;
            if (checkedTextView != null) {
                checkedTextView.setOnClickListener(this.m_panelDayClickListener);
            }
        }
        return this.m_panel_day;
    }

    private CheckedTextView getPanel_week() {
        if (this.m_panel_week == null) {
            CheckedTextView checkedTextView = (CheckedTextView) this.m_root.findViewById(R.id.gcs_summary_header_panel_week);
            this.m_panel_week = checkedTextView;
            if (checkedTextView != null) {
                checkedTextView.setOnClickListener(this.m_panelWeekClickListener);
            }
        }
        return this.m_panel_week;
    }

    private TextView getTitleView() {
        if (this.m_title == null) {
            this.m_title = (TextView) this.m_root.findViewById(R.id.gcs_summary_title);
        }
        return this.m_title;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public VerticalViewPager getViewPager() {
        if (this.m_pager == null) {
            VerticalViewPager verticalViewPager = (VerticalViewPager) this.m_root.findViewById(R.id.pager);
            this.m_pager = verticalViewPager;
            verticalViewPager.addOnPageChangeListener(this.m_pageChangeListener);
        }
        return this.m_pager;
    }

    private void init() {
        if (getViewPager() == null) {
            return;
        }
        getViewPager().setAdapter(getAdapter());
        getViewPager().setListView(R.id.rank_list);
        this.m_helper.setMode(this.m_timeMode);
        updatePageIndicator();
        reloadData();
    }

    private void reloadData() {
        IGameParmsPresenter iGameParmsPresenter = this.mGameParmsPresenter;
        if (iGameParmsPresenter != null) {
            iGameParmsPresenter.loadGameParms();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePage(int i) {
        PagerAdapter adapter = getAdapter();
        if (adapter == null || !(adapter instanceof SummaryPagerAdapter)) {
            return;
        }
        ((SummaryPagerAdapter) adapter).update(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePageIndicator() {
        int currentItem;
        if (getViewPager() == null || (currentItem = getViewPager().getCurrentItem()) < 0 || currentItem >= PAGE_INDICATOR.length) {
            return;
        }
        int i = 0;
        while (true) {
            int[] iArr = PAGE_INDICATOR;
            if (i >= iArr.length) {
                return;
            }
            ((ImageView) this.m_root.findViewById(iArr[i])).setSelected(currentItem == i);
            i++;
        }
    }

    private void updatePages() {
        PagerAdapter adapter = getAdapter();
        if (adapter == null || !(adapter instanceof SummaryPagerAdapter)) {
            return;
        }
        ((SummaryPagerAdapter) adapter).update();
    }

    private void updatePanel(int i) {
        boolean z = i == 1;
        getPanel_day().setChecked(z);
        getPanel_week().setChecked(!z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTitle() {
        int currentItem;
        if (getViewPager() != null && (currentItem = getViewPager().getCurrentItem()) >= 0) {
            int[] iArr = PAGE_TITLE;
            if (currentItem >= iArr.length) {
                return;
            }
            String str = getString(iArr[currentItem]) + this.m_helper.getRangeText(this.m_context);
            if (getTitleView() == null) {
                return;
            }
            getTitleView().setText(str);
        }
    }

    @Override // cn.nubia.gamecenter.settings.summary.presenter.ICallback
    public void gameParms(GameTimeInfo[] gameTimeInfoArr, List<List<GameAppInfo>> list) {
        this.m_helper.setInfo(gameTimeInfoArr);
        this.m_helper.setList(list);
    }

    @Override // cn.nubia.gamecenter.settings.summary.presenter.ICallback
    public void gameParmsOneGame(OneGameTimeAndLaunchTimesInfo oneGameTimeAndLaunchTimesInfo) {
    }

    public final String getString(int i) {
        try {
            return this.m_frag.getString(i);
        } catch (IllegalStateException unused) {
            return "";
        }
    }

    @Override // cn.nubia.gamecenter.settings.summary.SummaryPageHelper.Callback
    public boolean isCurrentPage(int i) {
        return i == getViewPager().getCurrentItem();
    }

    @Override // cn.nubia.gamecenter.settings.summary.SummaryDataHelper.ModeChangeListener
    public void onChange(int i) {
        updateTitle();
        updatePanel(i);
        updatePages();
    }

    public void onResume() {
        reloadData();
    }

    public void releaseAnimatorRes() {
        PagerAdapter adapter = getAdapter();
        if (adapter == null || !(adapter instanceof SummaryPagerAdapter)) {
            return;
        }
        ((SummaryPagerAdapter) adapter).releaseAnimatorRes();
    }

    public void setTestMode() {
        SummaryDataTester summaryDataTester = new SummaryDataTester(this.m_context, this);
        this.mGameParmsPresenter = summaryDataTester;
        summaryDataTester.loadGameParms();
    }

    public void setWeekMode(boolean z) {
        int i = z ? 2 : 1;
        this.m_timeMode = i;
        this.m_helper.setMode(i);
    }

    public void stopLoadGameParms() {
        this.mGameParmsPresenter.stopLoadGameParms();
    }

    @Override // cn.nubia.gamecenter.settings.summary.SummaryPageHelper.Callback
    public void toNextPage() {
        int currentItem = getViewPager().getCurrentItem() + 1;
        if (currentItem < 0 || currentItem >= PAGE_TITLE.length) {
            return;
        }
        getViewPager().setCurrentItem(currentItem);
    }
}
