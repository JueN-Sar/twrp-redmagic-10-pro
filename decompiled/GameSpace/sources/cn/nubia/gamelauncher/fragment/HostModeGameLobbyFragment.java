package cn.nubia.gamelauncher.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.common.config.GameSpaceConfig;
import cn.nubia.common.helper.AppUsageStatsHelper;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.adapter.HostGameAdapter;
import cn.nubia.gamelauncher.bean.AppListItemBean;
import cn.nubia.gamelauncher.commoninterface.ConstantVariable;
import cn.nubia.gamelauncher.commoninterface.IGetAppStatusDataCallBack;
import cn.nubia.gamelauncher.helper.CardHelper;
import cn.nubia.gamelauncher.helper.ShortCutHelper;
import cn.nubia.gamelauncher.layoutmanager.AutoAdjustColumnGridLayoutManager;
import cn.nubia.gamelauncher.model.AppAddModel;
import cn.nubia.gamelauncher.receiver.HomeWatcherReceiver;
import cn.nubia.gamelauncher.util.CommonUtil;
import cn.nubia.gamelauncher.util.NubiaTrackManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class HostModeGameLobbyFragment extends Fragment implements AppUsageStatsHelper.AppUsageStatsChangedListener, HostGameAdapter.AppClickListener, IGetAppStatusDataCallBack, View.OnClickListener {
    private static final String TAG = "HostMode";
    Animation mArrowAnim;
    private CardHelper mCardHelper;
    TextView mCardNamePc;
    TextView mCardNameStream;
    CardView mCardPc;
    CardView mCardStream;
    private Context mContext;
    private GameAddedContentObserver mGameAddedContentObserver;
    HostGameAdapter mGridAdapter;
    private AutoAdjustColumnGridLayoutManager mGridManager;
    private HomeWatcherReceiver mHomeWatcherReceiver;
    ImageView mHostNullArrow;
    TextView mHostNullText;
    RecyclerView mRecyclerView;
    View mSelectAll;
    View mSelectStream;
    TextView mTitleAll;
    TextView mTitleStream;
    private Handler mHandler = new Handler();
    private Boolean isDestroyView = false;
    ArrayList<AppListItemBean> mList = new ArrayList<>();
    ArrayList<View> mRecent = new ArrayList<>();
    boolean isStreamMode = false;

    private class GameAddedContentObserver extends ContentObserver {
        public GameAddedContentObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            HostModeGameLobbyFragment.this.updateAllGameCard();
        }

        public void register() {
            HostModeGameLobbyFragment.this.getAppContext().getContentResolver().registerContentObserver(ConstantVariable.APPADD_URI, false, this);
        }

        public void unregister() {
            HostModeGameLobbyFragment.this.getAppContext().getContentResolver().unregisterContentObserver(this);
        }
    }

    private int calcItemWidth(View view) {
        int dimensionPixelOffset = this.mContext.getResources().getDimensionPixelOffset(R.dimen.host_item_card_width);
        int dimensionPixelOffset2 = this.mContext.getResources().getDimensionPixelOffset(R.dimen.host_item_card_space);
        Log.d(TAG, "calcGridSpanCount() cardWidth : " + dimensionPixelOffset + ", cardSpace : " + dimensionPixelOffset2);
        return (dimensionPixelOffset2 * 2) + dimensionPixelOffset;
    }

    private void doArrowAnimIfNeed() {
        int visibility = this.mHostNullArrow.getVisibility();
        if (this.mHostNullArrow == null || visibility != 0) {
            return;
        }
        if (this.mArrowAnim == null) {
            this.mArrowAnim = AnimationUtils.loadAnimation(this.mContext, R.anim.host_null_arrow_anim);
        }
        this.mHostNullArrow.startAnimation(this.mArrowAnim);
    }

    private void doTrack(AppListItemBean appListItemBean, String str) {
        if (CommonUtil.isInternalVersion()) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(NubiaTrackManager.EVENT_NAME, "host_mode_lobby_used");
        bundle.putString("app_name", appListItemBean.getName());
        bundle.putString("app_location", str);
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", bundle);
    }

    private void fillList(ArrayList<AppListItemBean> arrayList) {
        this.mList.clear();
        this.mList.addAll(arrayList);
        this.mList.add(getAddGameItemBean());
    }

    private AppListItemBean getAddGameItemBean() {
        return new AppListItemBean(this.mContext.getResources().getString(R.string.add_game), "cn.nubia.gamelauncher,cn.nubia.gamelauncher.activity.AppAddActivity", "android.resource://cn.nubia.gamelauncher/mipmap/host_add_game.png", null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Context getAppContext() {
        return this.mContext.getApplicationContext();
    }

    private ArrayList<AppListItemBean> getGameList() {
        return AppAddModel.getInstance().getAllAddList();
    }

    private int getMirrorDisplayId() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), "app_mirror_displayid", 0);
    }

    private void initFan(View view) {
        if (GameSpaceConfig.supportFan()) {
            return;
        }
        view.findViewById(R.id.cooling_fan_text).setVisibility(8);
    }

    private void initGameListView(View view) {
        this.mGridManager = new AutoAdjustColumnGridLayoutManager(this.mContext, calcItemWidth(view));
        this.mGridAdapter = new HostGameAdapter(this.mContext, this.mList, this, getCardHelper());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.host_game_list);
        this.mRecyclerView = recyclerView;
        recyclerView.setLayoutManager(this.mGridManager);
        this.mRecyclerView.setAdapter(this.mGridAdapter);
    }

    private void initRecent(View view) {
        this.mRecent.add(view.findViewById(R.id.host_recent_item_first));
        this.mRecent.add(view.findViewById(R.id.host_recent_item_second));
        this.mRecent.add(view.findViewById(R.id.host_recent_item_third));
        updateRecent();
    }

    private void initStream(View view) {
        TextView textView = (TextView) view.findViewById(R.id.host_game_list_title_text);
        this.mTitleAll = textView;
        textView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: cn.nubia.gamelauncher.fragment.HostModeGameLobbyFragment.1
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view2, boolean z) {
                if (z) {
                    HostModeGameLobbyFragment.this.switchToAll();
                }
            }
        });
        TextView textView2 = (TextView) view.findViewById(R.id.host_game_stream);
        this.mTitleStream = textView2;
        textView2.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: cn.nubia.gamelauncher.fragment.HostModeGameLobbyFragment.2
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view2, boolean z) {
                if (z) {
                    HostModeGameLobbyFragment.this.switchToStream();
                }
            }
        });
        this.mSelectAll = view.findViewById(R.id.host_title_all_select);
        this.mSelectStream = view.findViewById(R.id.host_title_stream_select);
        this.mCardStream = (CardView) view.findViewById(R.id.host_item_stream);
        this.mCardPc = (CardView) view.findViewById(R.id.host_item_pc);
        this.mCardStream.setBackgroundResource(R.drawable.card_bg_normal);
        this.mCardPc.setBackgroundResource(R.drawable.card_bg_normal);
        this.mCardStream.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: cn.nubia.gamelauncher.fragment.HostModeGameLobbyFragment.3
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view2, boolean z) {
                view2.setBackgroundResource(z ? R.drawable.card_bg_focused : R.drawable.card_bg_normal);
                view2.animate().scaleX(z ? 1.05f : 1.0f).scaleY(z ? 1.05f : 1.0f).setDuration(150L).start();
            }
        });
        this.mCardPc.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: cn.nubia.gamelauncher.fragment.HostModeGameLobbyFragment.4
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view2, boolean z) {
                view2.setBackgroundResource(z ? R.drawable.card_bg_focused : R.drawable.card_bg_normal);
                view2.animate().scaleX(z ? 1.05f : 1.0f).scaleY(z ? 1.05f : 1.0f).setDuration(150L).start();
            }
        });
        this.mCardNameStream = (TextView) view.findViewById(R.id.host_item_stream_title);
        this.mCardNamePc = (TextView) view.findViewById(R.id.host_item_pc_title);
        this.mTitleAll.setOnClickListener(this);
        this.mTitleStream.setOnClickListener(this);
        this.mCardStream.setOnClickListener(this);
        this.mCardPc.setOnClickListener(this);
        if (FeatureUtil.supportStream()) {
            updateDefaultSelect();
        } else {
            updateToGameOnly();
        }
    }

    private void initView(View view) {
        Log.d(TAG, "---->initView()");
        this.mHostNullArrow = (ImageView) view.findViewById(R.id.host_null_arrow);
        this.mHostNullText = (TextView) view.findViewById(R.id.host_null_games_text);
        initGameListView(view);
        initRecent(view);
        initFan(view);
        initStream(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onHomePressed() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null) {
            return;
        }
        recyclerView.scrollToPosition(0);
        Log.d(TAG, "onHomePressed()---->scrollToPosition(0)");
    }

    private void registerGameAddedObserver() {
        GameAddedContentObserver gameAddedContentObserver = new GameAddedContentObserver(this.mHandler);
        this.mGameAddedContentObserver = gameAddedContentObserver;
        gameAddedContentObserver.register();
    }

    private void registerHomeKeyReceiver(Context context) {
        if (this.mHomeWatcherReceiver == null) {
            this.mHomeWatcherReceiver = new HomeWatcherReceiver();
        }
        context.registerReceiver(this.mHomeWatcherReceiver, new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS"), 2);
        this.mHomeWatcherReceiver.setRunnable(new Runnable() { // from class: cn.nubia.gamelauncher.fragment.HostModeGameLobbyFragment$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                HostModeGameLobbyFragment.this.onHomePressed();
            }
        });
    }

    private void registerListener() {
        AppAddModel.getInstance().resisterGetAppStatusDataCallBack(this);
        AppUsageStatsHelper.getInstance().registerAppUsageStatsChangedListener(this);
        registerGameAddedObserver();
        registerHomeKeyReceiver(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchToAll() {
        this.isStreamMode = false;
        this.mTitleAll.setTextColor(getResources().getColor(R.color.title_color, null));
        this.mTitleStream.setTextColor(getResources().getColor(R.color.host_title_unselect_color, null));
        this.mSelectAll.setVisibility(0);
        this.mSelectStream.setVisibility(8);
        this.mRecyclerView.setVisibility(0);
        this.mCardStream.setVisibility(8);
        this.mCardPc.setVisibility(8);
        this.mCardNameStream.setVisibility(8);
        this.mCardNamePc.setVisibility(8);
        updateArrow();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchToStream() {
        this.isStreamMode = true;
        this.mTitleStream.setTextColor(getResources().getColor(R.color.title_color, null));
        this.mTitleAll.setTextColor(getResources().getColor(R.color.host_title_unselect_color, null));
        this.mSelectStream.setVisibility(0);
        this.mSelectAll.setVisibility(8);
        this.mRecyclerView.setVisibility(8);
        this.mCardStream.setVisibility(0);
        this.mCardPc.setVisibility(0);
        this.mCardNameStream.setVisibility(0);
        this.mCardNamePc.setVisibility(0);
        updateArrow();
    }

    private void unRegisterHomeKeyReceiver(Context context) {
        HomeWatcherReceiver homeWatcherReceiver = this.mHomeWatcherReceiver;
        if (homeWatcherReceiver != null) {
            homeWatcherReceiver.setRunnable(null);
            context.unregisterReceiver(this.mHomeWatcherReceiver);
            this.mHomeWatcherReceiver = null;
        }
    }

    private void unregisterGameAddedObserver() {
        GameAddedContentObserver gameAddedContentObserver = this.mGameAddedContentObserver;
        if (gameAddedContentObserver != null) {
            gameAddedContentObserver.unregister();
        }
    }

    private void unregisterListener() {
        AppAddModel.getInstance().unResisterGetAppStatusDataCallBack(this);
        AppUsageStatsHelper.getInstance().unregisterAppUsageStatsChangedListener(this);
        unregisterGameAddedObserver();
        unRegisterHomeKeyReceiver(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAllGameCard() {
        Log.d(TAG, "---->updateAllGameCard()");
        updateGameList();
        updateRecent();
        updateGameCard();
        updateArrow();
    }

    private void updateArrow() {
        if (this.mHostNullArrow == null) {
            return;
        }
        int i = (this.mList.size() != 1 || this.isStreamMode) ? 8 : 0;
        Log.d(TAG, "updateArrow() visibility : " + i);
        this.mHostNullText.setVisibility(i);
        this.mHostNullArrow.setVisibility(i);
        if (i != 8) {
            doArrowAnimIfNeed();
        } else if (this.mArrowAnim != null) {
            this.mHostNullArrow.clearAnimation();
        }
    }

    private void updateDefaultSelect() {
        switchToAll();
    }

    private void updateGameCard() {
        HostGameAdapter hostGameAdapter = this.mGridAdapter;
        if (hostGameAdapter == null) {
            return;
        }
        hostGameAdapter.notifyDataSetChanged();
    }

    private void updateRecent() {
        Log.d(TAG, "---->updateRecent() mRecent : " + this.mRecent);
        if (this.mRecent == null) {
            return;
        }
        int i = 0;
        while (i < this.mRecent.size()) {
            AppListItemBean appListItemBean = i < this.mList.size() ? this.mList.get(i) : null;
            Log.d(TAG, "---->updateRecent() i : " + i);
            updateRecentItem(this.mRecent.get(i), appListItemBean);
            i++;
        }
    }

    private void updateRecentItem(View view, final AppListItemBean appListItemBean) {
        if (view == null || this.isDestroyView.booleanValue()) {
            return;
        }
        Log.d(TAG, "---->updateRecentItem() bean : " + appListItemBean);
        ImageView imageView = (ImageView) view.findViewById(R.id.host_recent_game_banner);
        TextView textView = (TextView) view.findViewById(R.id.host_recent_game_name);
        TextView textView2 = (TextView) view.findViewById(R.id.host_recent_game_play_time);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.host_recent_center_icon);
        ImageView imageView3 = (ImageView) view.findViewById(R.id.host_recent_left_icon);
        ImageView imageView4 = (ImageView) view.findViewById(R.id.host_recent_game_name_bg);
        if (appListItemBean == null || appListItemBean.isAddItem()) {
            textView.setVisibility(8);
            imageView4.setVisibility(8);
            textView2.setVisibility(8);
            imageView2.setVisibility(8);
            imageView3.setVisibility(8);
            imageView.setImageResource(R.mipmap.host_recent_item_null);
            Log.d(TAG, "---->updateRecentItem() setImageResource null");
            return;
        }
        imageView.setImageResource(R.mipmap.host_recent_item_default);
        textView.setVisibility(0);
        imageView4.setVisibility(0);
        textView2.setVisibility(0);
        imageView2.setVisibility(0);
        imageView3.setVisibility(0);
        textView.setText(appListItemBean.getName());
        Bitmap icon = appListItemBean.getIcon();
        imageView2.setImageBitmap(icon);
        imageView3.setImageBitmap(icon);
        textView2.setText(getCardHelper().getTotalString(appListItemBean));
        String atmosphereUrl = appListItemBean.getAtmosphereUrl();
        imageView2.setTag(atmosphereUrl);
        Log.d(TAG, "host recent name : " + textView + ", url : " + atmosphereUrl);
        getCardHelper().fillCardView(this.mContext, imageView, imageView2, atmosphereUrl, R.mipmap.host_recent_item_default, 6, DiskCacheStrategy.SOURCE);
        view.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.fragment.HostModeGameLobbyFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                HostModeGameLobbyFragment.this.onAppBeanClick(appListItemBean, "recently_list");
            }
        });
    }

    private void updateToGameOnly() {
        this.isStreamMode = false;
        this.mTitleStream.setVisibility(8);
        this.mTitleStream.setTextColor(R.color.title_color);
        this.mSelectAll.setVisibility(8);
        this.mSelectStream.setVisibility(8);
        this.mCardStream.setVisibility(8);
        this.mCardPc.setVisibility(8);
        this.mCardNameStream.setVisibility(8);
        this.mCardNamePc.setVisibility(8);
    }

    public CardHelper getCardHelper() {
        if (this.mCardHelper == null) {
            this.mCardHelper = new CardHelper(getAppContext());
        }
        return this.mCardHelper;
    }

    @Override // cn.nubia.gamelauncher.adapter.HostGameAdapter.AppClickListener
    public void onAppBeanClick(AppListItemBean appListItemBean, String str) {
        if (appListItemBean == null) {
            return;
        }
        try {
            String componentName = appListItemBean.getComponentName();
            Log.d(TAG, "onAppBeanClick() hasCloneApp() : " + getCardHelper().hasCloneApp(componentName));
            if (appListItemBean.isAddItem()) {
                Intent intent = new Intent();
                intent.setComponent(CommonUtil.createComponentName(componentName));
                intent.putExtra(TAG, true);
                this.mContext.startActivity(intent);
                return;
            }
            if (appListItemBean.isShortcut()) {
                ShortCutHelper.getInstance().startShortcut(appListItemBean.getShortcutInfo(), getMirrorDisplayId());
            } else if (getCardHelper().hasCloneApp(componentName)) {
                getCardHelper().showDialog(appListItemBean);
            } else {
                getCardHelper().startApp(appListItemBean, false);
            }
            doTrack(appListItemBean, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.host_game_list_title_text /* 2131362546 */:
                switchToAll();
                break;
            case R.id.host_game_stream /* 2131362549 */:
                switchToStream();
                break;
            case R.id.host_item_pc /* 2131362552 */:
                cn.nubia.common.util.CommonUtil.startPcPlay(this.mContext);
                break;
            case R.id.host_item_stream /* 2131362554 */:
                cn.nubia.common.util.CommonUtil.startStreamPlay(this.mContext);
                break;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Log.d(TAG, "---->onCreateView()");
        View inflate = layoutInflater.inflate(R.layout.game_lobby_host_mode, viewGroup, false);
        this.mContext = getActivity();
        this.mCardHelper = new CardHelper(this.mContext);
        registerListener();
        updateGameList();
        initView(inflate);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "---->onDestroyView()");
        unregisterListener();
        this.mCardHelper = null;
        this.isDestroyView = true;
        unregisterListener();
    }

    @Override // cn.nubia.gamelauncher.commoninterface.IGetAppStatusDataCallBack
    public void onLoadAddAppListDone(ArrayList<AppListItemBean> arrayList, int i) {
        Log.d(TAG, "---->onLoadAddAppListDone() hasAddCount : " + i);
        updateAllGameCard();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Log.d(TAG, "---->onResume()");
        AppUsageStatsHelper.getInstance().updateAppUsageStat();
        doArrowAnimIfNeed();
    }

    @Override // cn.nubia.common.helper.AppUsageStatsHelper.AppUsageStatsChangedListener
    public void onUsageStatsChanged(boolean z) {
        updateAllGameCard();
    }

    public void updateGameList() {
        ArrayList<AppListItemBean> gameList = getGameList();
        if (gameList == null) {
            return;
        }
        fillList(gameList);
        updateArrow();
        Log.d(TAG, "---->updateGameList() mList.size() : " + this.mList.size());
    }
}
