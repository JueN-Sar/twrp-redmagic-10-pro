package cn.nubia.gamelauncher.fragment;

import android.content.ContentUris;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.common.helper.AppUsageStatsHelper;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamecenter.settings.records.utils.HighLightsUtils;
import cn.nubia.gamelauncher.GameLauncherApplication;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.adapter.HandheldAdapter;
import cn.nubia.gamelauncher.adapter.RecommendAdapter;
import cn.nubia.gamelauncher.adapter.StreamAdapter;
import cn.nubia.gamelauncher.bean.AppListItemBean;
import cn.nubia.gamelauncher.bean.AtmosphereBean;
import cn.nubia.gamelauncher.bean.CheckStateBean;
import cn.nubia.gamelauncher.bean.CheckStateResponse;
import cn.nubia.gamelauncher.bean.GameItemBean;
import cn.nubia.gamelauncher.bean.ResponseBean;
import cn.nubia.gamelauncher.commoninterface.ConstantVariable;
import cn.nubia.gamelauncher.commoninterface.ICheckStateRequestListener;
import cn.nubia.gamelauncher.commoninterface.ICoverUrlCallback;
import cn.nubia.gamelauncher.commoninterface.IGetAppStatusDataCallBack;
import cn.nubia.gamelauncher.commoninterface.IRequestListener;
import cn.nubia.gamelauncher.commoninterface.NeoGameDBColumns;
import cn.nubia.gamelauncher.fragment.HandheldFragment;
import cn.nubia.gamelauncher.gamecenter.BusinessRequestorImp;
import cn.nubia.gamelauncher.gamecenter.CheckStateRequestorImp;
import cn.nubia.gamelauncher.gamelist.HandheldExpandDecoration;
import cn.nubia.gamelauncher.gamelist.HandheldItemDecoration;
import cn.nubia.gamelauncher.helper.CardHelper;
import cn.nubia.gamelauncher.helper.LobbySoundPoolHelper;
import cn.nubia.gamelauncher.helper.ShortCutHelper;
import cn.nubia.gamelauncher.layoutmanager.HandheldLayoutManager;
import cn.nubia.gamelauncher.model.AppAddModel;
import cn.nubia.gamelauncher.model.NeoDownloadHelper;
import cn.nubia.gamelauncher.util.CommonUtil;
import cn.nubia.gamelauncher.util.GameCenterHelper;
import cn.nubia.gamelauncher.util.NubiaTrackManager;
import cn.nubia.gamelauncher.util.Util;
import cn.nubia.gamelauncher.util.WorkThread;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class HandheldFragment extends Fragment implements AppUsageStatsHelper.AppUsageStatsChangedListener, HandheldAdapter.AppClickListener, RecommendAdapter.RecommendClickListener, IGetAppStatusDataCallBack {
    private static final String TAG = "Handheld";
    private CardHelper mCardHelper;
    private Context mContext;
    private GameAddedContentObserver mGameAddedContentObserver;
    private RecyclerView.ItemDecoration mGameDecoration;
    private RecyclerView.ItemDecoration mGameExpandDecoration;
    private TextView mGameTitle;
    HandheldAdapter mGridAdapter;
    private Runnable mInvisibleCallback;
    private View mNoRecommendView;
    private RecommendAdapter mRecommendAdapter;
    private TextView mRecommendTitle;
    private RecyclerView mRecommendView;
    private RecyclerView mRecyclerView;
    private NestedScrollView mRootScrollView;
    private RecyclerView.ItemDecoration mStreamDecoration;
    private RecyclerView.ItemDecoration mStreamExpandDecoration;
    private RecyclerView mStreamListView;
    private Runnable mVisibleCallback;
    private Handler mHandler = new Handler();
    CopyOnWriteArrayList<AppListItemBean> mList = new CopyOnWriteArrayList<>();
    private ArrayList<GameItemBean> mRecommendList = new ArrayList<>();
    private Map<String, GameItemBean> mDownloadList = new HashMap();
    private boolean mOnFirst = false;
    private Runnable updateOnce = new Runnable() { // from class: cn.nubia.gamelauncher.fragment.HandheldFragment.1
        @Override // java.lang.Runnable
        public void run() {
            Log.d("Handheld", "---->updateAllGameCard()");
            HandheldFragment.this.updateGameList();
            HandheldFragment.this.updateGameCard();
            HandheldFragment.this.updateGameTitle();
            if (HandheldFragment.this.mOnFirst) {
                HandheldFragment.this.mHandler.removeCallbacks(HandheldFragment.this.moveToFirst);
                HandheldFragment.this.mHandler.postDelayed(HandheldFragment.this.moveToFirst, 50L);
                HandheldFragment.this.mOnFirst = false;
            }
        }
    };
    private Runnable moveToFirst = new Runnable() { // from class: cn.nubia.gamelauncher.fragment.HandheldFragment.2
        @Override // java.lang.Runnable
        public void run() {
            HandheldFragment.this.focusDefault();
        }
    };
    private final ContentObserver mDownloadObserver = new ContentObserver(WorkThread.getHandler()) { // from class: cn.nubia.gamelauncher.fragment.HandheldFragment.8
        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            GameItemBean queryByAppId;
            super.onChange(z, uri);
            if (NeoDownloadHelper.EXTPROVIDEROPERATION_UPDATE.equals(uri.getQueryParameter(NeoGameDBColumns.ACTION))) {
                if (Util.isRedMagicRunOnMyOs()) {
                    String queryParameter = uri.getQueryParameter(NeoGameDBColumns.PACKAGENAME);
                    String queryParameter2 = uri.getQueryParameter(NeoGameDBColumns.PROGRESS);
                    queryByAppId = new GameItemBean(queryParameter, uri.getQueryParameter("status"), queryParameter2 != null ? Integer.parseInt(queryParameter2) : -1);
                } else {
                    try {
                        queryByAppId = HandheldFragment.this.queryByAppId(uri, ContentUris.parseId(uri));
                    } catch (NumberFormatException e) {
                        Log.i("Handheld", "onChange() e : " + e.getMessage());
                        return;
                    }
                }
                if (queryByAppId == null) {
                    return;
                }
                Iterator it = HandheldFragment.this.mRecommendList.iterator();
                while (it.hasNext()) {
                    GameItemBean gameItemBean = (GameItemBean) it.next();
                    if (gameItemBean.getPackageName().equals(queryByAppId.getPackageName())) {
                        String status = queryByAppId.getStatus();
                        if (NeoGameDBColumns.STATUS_SUCCESS.equals(status) || NeoGameDBColumns.STATUS_IN_INSTALLTION.equals(status)) {
                            HandheldFragment.this.getRecommendGameList();
                            return;
                        }
                        if (!NeoGameDBColumns.STATUS_DOWNLOADING.equals(status) || queryByAppId.getProgress() == gameItemBean.getProgress()) {
                            return;
                        }
                        gameItemBean.setProgress(queryByAppId.getProgress());
                        gameItemBean.setStatus(status);
                        HandheldFragment handheldFragment = HandheldFragment.this;
                        handheldFragment.notifyItemChanged(handheldFragment.mRecommendList.indexOf(gameItemBean), "progress");
                        return;
                    }
                }
            }
        }
    };

    /* renamed from: cn.nubia.gamelauncher.fragment.HandheldFragment$5, reason: invalid class name */
    class AnonymousClass5 implements ICheckStateRequestListener {
        AnonymousClass5() {
        }

        /* renamed from: lambda$responseInfo$0$cn-nubia-gamelauncher-fragment-HandheldFragment$5, reason: not valid java name */
        /* synthetic */ void m239x6e46f5b7() {
            HandheldFragment.this.updateGameCard();
        }

        @Override // cn.nubia.gamelauncher.commoninterface.ICheckStateRequestListener
        public void responseError(String str) {
            Log.d("Handheld", "doStateRequest() -- responseError() errorMsg : " + str);
        }

        @Override // cn.nubia.gamelauncher.commoninterface.ICheckStateRequestListener
        public void responseInfo(CheckStateResponse checkStateResponse) {
            if (checkStateResponse == null || checkStateResponse.getData() == null) {
                return;
            }
            Iterator<CheckStateBean> it = checkStateResponse.getData().iterator();
            while (it.hasNext()) {
                CheckStateBean next = it.next();
                if (next != null) {
                    int state = next.getState();
                    HandheldFragment.this.updateGameLogo(next.getPackageName(), 1 == state);
                }
            }
            HandheldFragment.this.mHandler.post(new Runnable() { // from class: cn.nubia.gamelauncher.fragment.HandheldFragment$5$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    HandheldFragment.AnonymousClass5.this.m239x6e46f5b7();
                }
            });
        }
    }

    private class GameAddedContentObserver extends ContentObserver {
        public GameAddedContentObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            HandheldFragment.this.updateAllGameCard();
        }

        public void register() {
            HandheldFragment.this.getAppContext().getContentResolver().registerContentObserver(ConstantVariable.APPADD_URI, false, this);
        }

        public void unregister() {
            HandheldFragment.this.getAppContext().getContentResolver().unregisterContentObserver(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void appendGameCover(ArrayList<AtmosphereBean> arrayList) {
        if (this.mRecommendList == null || arrayList == null || arrayList.size() < 1) {
            return;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.mRecommendList.size() && i < arrayList.size(); i2++) {
            GameItemBean gameItemBean = this.mRecommendList.get(i2);
            AtmosphereBean atmosphereBean = arrayList.get(i);
            if (gameItemBean.getPackageName().equals(atmosphereBean.getPackageName())) {
                gameItemBean.setUrl(atmosphereBean.getUrl());
                notifyItemChanged(i2, RecommendAdapter.PAYLOAD_GAME_COVER);
                i++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void appendGameDownload(ResponseBean responseBean) {
        ArrayList<GameItemBean> gameItemBean;
        if (responseBean == null || (gameItemBean = responseBean.getGameItemBean()) == null || gameItemBean.size() <= 0) {
            return;
        }
        this.mRecommendList.clear();
        for (int i = 0; i < gameItemBean.size() && this.mRecommendList.size() < 3; i++) {
            GameItemBean gameItemBean2 = gameItemBean.get(i);
            if (!Util.isAppInstall(this.mContext, gameItemBean2.getPackageName())) {
                GameItemBean gameItemBean3 = this.mDownloadList.get(gameItemBean2.getPackageName());
                if (gameItemBean3 == null) {
                    this.mRecommendList.add(gameItemBean2);
                } else {
                    String status = gameItemBean3.getStatus();
                    if (NeoGameDBColumns.STATUS_DOWNLOADING.equals(status) || NeoGameDBColumns.STATUS_PAUSE.equals(status)) {
                        gameItemBean2.setStatus(status);
                        gameItemBean2.setProgress(gameItemBean3.getProgress());
                        this.mRecommendList.add(gameItemBean2);
                    }
                }
            }
        }
        getGameCover();
    }

    private int calcItemWidth(View view) {
        int dimensionPixelOffset = this.mContext.getResources().getDimensionPixelOffset(R.dimen.host_item_card_width);
        int dimensionPixelOffset2 = this.mContext.getResources().getDimensionPixelOffset(R.dimen.host_item_card_space);
        Log.d("Handheld", "calcGridSpanCount() cardWidth : " + dimensionPixelOffset + ", cardSpace : " + dimensionPixelOffset2);
        return (dimensionPixelOffset2 * 2) + dimensionPixelOffset;
    }

    private void doStateRequest() {
        if (this.mList == null) {
            return;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<AppListItemBean> it = this.mList.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getPackageName());
        }
        Log.d("Handheld", "doStateRequest()");
        new CheckStateRequestorImp().checkTopicSoft(GameLauncherApplication.CONTEXT, arrayList, new AnonymousClass5());
    }

    private void doTrack(AppListItemBean appListItemBean, String str) {
        if (CommonUtil.isInternalVersion()) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(NubiaTrackManager.EVENT_NAME, "gravity_x_Handheld_used");
        bundle.putString("app_name", appListItemBean.getName());
        bundle.putString("app_location", str);
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", bundle);
    }

    private void doTrackHandheldUsed(boolean z) {
        if (CommonUtil.isAbroad()) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("event_name", "gravity_x_Handheld_used");
        bundle.putString("handheld_state", z ? "on" : "off");
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", bundle);
    }

    private void expand() {
        if (this.mGridAdapter == null) {
            return;
        }
        Runnable runnable = this.mInvisibleCallback;
        if (runnable != null) {
            runnable.run();
        }
        this.mGridAdapter.setExpand(true);
        this.mGameTitle.setText(R.string.handheld_all_games);
        this.mRecommendTitle.setVisibility(4);
        this.mRecommendView.setVisibility(8);
        this.mNoRecommendView.setVisibility(8);
        this.mRecyclerView.removeItemDecoration(this.mGameDecoration);
        this.mRecyclerView.addItemDecoration(this.mGameExpandDecoration);
        this.mRecyclerView.invalidateItemDecorations();
        this.mRecyclerView.requestLayout();
        updateMargin(35);
    }

    private void fillList(CopyOnWriteArrayList<AppListItemBean> copyOnWriteArrayList) {
        this.mList.clear();
        this.mList.addAll(copyOnWriteArrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean focusDefault() {
        HandheldAdapter handheldAdapter = this.mGridAdapter;
        if (handheldAdapter != null && handheldAdapter.getItemCount() > 0) {
            moveToMygamePosition(0);
            return true;
        }
        RecommendAdapter recommendAdapter = this.mRecommendAdapter;
        if (recommendAdapter == null || recommendAdapter.getItemCount() <= 0) {
            return false;
        }
        moveToRecommendPosition(0);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Context getAppContext() {
        return this.mContext.getApplicationContext();
    }

    private void getDownload() {
        int i;
        this.mDownloadList.clear();
        Cursor query = GameLauncherApplication.CONTEXT.getContentResolver().query(NeoDownloadHelper.getUri(), null, null, null, null);
        if (query == null) {
            return;
        }
        while (query.moveToNext()) {
            try {
                String string = query.getString(query.getColumnIndex(NeoGameDBColumns.PACKAGENAME));
                String string2 = query.getString(query.getColumnIndex("status"));
                if (!NeoGameDBColumns.STATUS_DOWNLOADING.equals(string2) && !NeoGameDBColumns.STATUS_PAUSE.equals(string2)) {
                    i = 0;
                    this.mDownloadList.put(string, new GameItemBean(string, string2, i));
                }
                i = query.getInt(query.getColumnIndex(NeoGameDBColumns.PROGRESS));
                this.mDownloadList.put(string, new GameItemBean(string, string2, i));
            } catch (Throwable th) {
                query.close();
                throw th;
            }
        }
        query.close();
        Log.i("Handheld", "getDownload " + this.mDownloadList);
    }

    private int getFocusRecyclerView() {
        if (this.mRecyclerView.getFocusedChild() != null) {
            return 0;
        }
        return this.mRecommendView.getFocusedChild() != null ? 1 : -1;
    }

    private void getGameCover() {
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<GameItemBean> it = this.mRecommendList.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getPackageName());
        }
        new BusinessRequestorImp().getBannersByPackageNames(GameLauncherApplication.CONTEXT, arrayList, new ICoverUrlCallback() { // from class: cn.nubia.gamelauncher.fragment.HandheldFragment.7
            @Override // cn.nubia.gamelauncher.commoninterface.ICoverUrlCallback
            public void responseError(String str) {
                Log.w("Handheld", "getGameCover " + str);
            }

            @Override // cn.nubia.gamelauncher.commoninterface.ICoverUrlCallback
            public void responseInfo(ArrayList<AtmosphereBean> arrayList2) {
                Log.d("Handheld", "getGameCover " + arrayList2);
                HandheldFragment.this.appendGameCover(arrayList2);
            }
        });
    }

    private CopyOnWriteArrayList<AppListItemBean> getGameList() {
        return new CopyOnWriteArrayList<>(AppAddModel.getInstance().getAllAddList());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getRecommendGameList() {
        getDownload();
        int size = this.mDownloadList.size();
        int i = size + 3;
        if (!Util.isRedMagicRunOnMyOs()) {
            i = size + 13;
        }
        Log.i("Handheld", "getTopicSoftList " + i);
        new BusinessRequestorImp().getTopicSoftList(GameLauncherApplication.CONTEXT, i, new IRequestListener() { // from class: cn.nubia.gamelauncher.fragment.HandheldFragment.6
            @Override // cn.nubia.gamelauncher.commoninterface.IRequestListener
            public void responseError(String str) {
                Log.w("Handheld", "getTopicSoftList " + str);
                HandheldFragment.this.mRecommendList.clear();
                HandheldFragment.this.notifyDataSetChanged();
            }

            @Override // cn.nubia.gamelauncher.commoninterface.IRequestListener
            public void responseInfo(ResponseBean responseBean) {
                Log.i("Handheld", "getTopicSoftList " + responseBean);
                HandheldFragment.this.appendGameDownload(responseBean);
                HandheldFragment.this.notifyDataSetChanged();
                if (HandheldFragment.this.mGridAdapter == null || HandheldFragment.this.mGridAdapter.getItemCount() <= 0) {
                    HandheldFragment.this.mHandler.removeCallbacks(HandheldFragment.this.moveToFirst);
                    HandheldFragment.this.mHandler.postDelayed(HandheldFragment.this.moveToFirst, 50L);
                }
            }
        });
    }

    private void initGameListView(View view) {
        HandheldLayoutManager handheldLayoutManager = new HandheldLayoutManager(this.mContext, 1);
        handheldLayoutManager.setOrientation(0);
        this.mGridAdapter = new HandheldAdapter(this.mContext, handheldLayoutManager, this.mList, this, getCardHelper());
        this.mGameDecoration = new HandheldItemDecoration();
        this.mGameExpandDecoration = new HandheldExpandDecoration();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.handheld_game_list);
        this.mRecyclerView = recyclerView;
        recyclerView.addItemDecoration(this.mGameDecoration);
        this.mRecyclerView.setLayoutManager(handheldLayoutManager);
        this.mRecyclerView.setAdapter(this.mGridAdapter);
        this.mRecyclerView.setFocusable(true);
        this.mRecyclerView.setFocusableInTouchMode(true);
        updateGameTitle();
    }

    private void initRecommendView(View view) {
        this.mNoRecommendView = view.findViewById(R.id.no_recommend);
        this.mRecommendView = (RecyclerView) view.findViewById(R.id.handheld_game_recommend);
        this.mRecommendView.setLayoutManager(new LinearLayoutManager(this.mContext, 0, false));
        this.mRecommendView.addItemDecoration(this.mGameDecoration);
        RecommendAdapter recommendAdapter = new RecommendAdapter(this.mContext, this.mRecommendList, this, getCardHelper());
        this.mRecommendAdapter = recommendAdapter;
        this.mRecommendView.setAdapter(recommendAdapter);
        this.mRecommendView.setFocusable(true);
        this.mRecommendView.setFocusableInTouchMode(true);
    }

    private void initStreamList(View view) {
        if (FeatureUtil.supportStream()) {
            HandheldLayoutManager handheldLayoutManager = new HandheldLayoutManager(this.mContext, 1);
            handheldLayoutManager.setOrientation(0);
            StreamAdapter streamAdapter = new StreamAdapter(this.mContext, getCardHelper());
            this.mStreamDecoration = new HandheldItemDecoration();
            this.mStreamExpandDecoration = new HandheldExpandDecoration();
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.handheld_stream_list);
            this.mStreamListView = recyclerView;
            recyclerView.addItemDecoration(this.mStreamDecoration);
            this.mStreamListView.setLayoutManager(handheldLayoutManager);
            this.mStreamListView.setAdapter(streamAdapter);
            this.mStreamListView.setFocusable(true);
            this.mStreamListView.setFocusableInTouchMode(true);
            view.findViewById(R.id.title_3a).setVisibility(0);
            this.mStreamListView.setVisibility(0);
        }
    }

    private void initTitle(View view) {
        this.mGameTitle = (TextView) view.findViewById(R.id.game_title);
        this.mRecommendTitle = (TextView) view.findViewById(R.id.recommend_title);
    }

    private void initView(View view) {
        Log.d("Handheld", "---->initView()");
        initTitle(view);
        initGameListView(view);
        initRecommendView(view);
        initStreamList(view);
    }

    private boolean isRecommendEmpty() {
        ArrayList<GameItemBean> arrayList = this.mRecommendList;
        return arrayList == null || arrayList.size() < 1;
    }

    private void moveToMygamePosition(final int i) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null) {
            return;
        }
        recyclerView.scrollToPosition(i);
        this.mRecyclerView.post(new Runnable() { // from class: cn.nubia.gamelauncher.fragment.HandheldFragment.3
            @Override // java.lang.Runnable
            public void run() {
                View findViewByPosition = HandheldFragment.this.mRecyclerView.getLayoutManager().findViewByPosition(i);
                if (findViewByPosition != null) {
                    findViewByPosition.requestFocus();
                }
            }
        });
    }

    private void moveToRecommendPosition(final int i) {
        RecyclerView recyclerView = this.mRecommendView;
        if (recyclerView == null) {
            return;
        }
        recyclerView.scrollToPosition(i);
        this.mRecommendView.post(new Runnable() { // from class: cn.nubia.gamelauncher.fragment.HandheldFragment.4
            @Override // java.lang.Runnable
            public void run() {
                View findViewByPosition = HandheldFragment.this.mRecommendView.getLayoutManager().findViewByPosition(i);
                if (findViewByPosition != null) {
                    findViewByPosition.requestFocus();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDataSetChanged() {
        this.mHandler.post(new Runnable() { // from class: cn.nubia.gamelauncher.fragment.HandheldFragment$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                HandheldFragment.this.m237xf913ea7c();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyItemChanged(final int i, final String str) {
        this.mHandler.post(new Runnable() { // from class: cn.nubia.gamelauncher.fragment.HandheldFragment$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                HandheldFragment.this.m238xf90ee616(i, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public GameItemBean queryByAppId(Uri uri, long j) {
        Cursor query = GameLauncherApplication.CONTEXT.getContentResolver().query(uri, null, "app_id=?", new String[]{"" + j}, null);
        GameItemBean gameItemBean = null;
        if (query == null) {
            return null;
        }
        try {
            try {
                if (query.moveToNext()) {
                    String string = query.getString(query.getColumnIndex(NeoGameDBColumns.PACKAGENAME));
                    String string2 = query.getString(query.getColumnIndex("status"));
                    gameItemBean = new GameItemBean(string, string2, (NeoGameDBColumns.STATUS_DOWNLOADING.equals(string2) || NeoGameDBColumns.STATUS_PAUSE.equals(string2)) ? query.getInt(query.getColumnIndex(NeoGameDBColumns.PROGRESS)) : 0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return gameItemBean;
        } finally {
            query.close();
        }
    }

    private void registerDownloadObserver() {
        Log.i("Handheld", "registerDownloadObserver");
        try {
            GameLauncherApplication.CONTEXT.getContentResolver().registerContentObserver(NeoDownloadHelper.getUri(), true, this.mDownloadObserver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registerGameAddedObserver() {
        if (this.mGameAddedContentObserver == null) {
            this.mGameAddedContentObserver = new GameAddedContentObserver(this.mHandler);
        }
        this.mGameAddedContentObserver.register();
    }

    private void registerListener() {
        AppAddModel.getInstance().resisterGetAppStatusDataCallBack(this);
        AppUsageStatsHelper.getInstance().registerAppUsageStatsChangedListener(this);
        registerGameAddedObserver();
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
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAllGameCard() {
        this.mHandler.removeCallbacks(this.updateOnce);
        this.mHandler.postDelayed(this.updateOnce, 150L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateGameCard() {
        HandheldAdapter handheldAdapter = this.mGridAdapter;
        if (handheldAdapter == null) {
            return;
        }
        handheldAdapter.notifyDataSetChanged();
        Log.d("Handheld", "---->updateGameCard()");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateGameLogo(String str, boolean z) {
        CopyOnWriteArrayList<AppListItemBean> copyOnWriteArrayList = this.mList;
        if (copyOnWriteArrayList == null) {
            return;
        }
        Iterator<AppListItemBean> it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            AppListItemBean next = it.next();
            if (str.equals(next.getPackageName())) {
                next.isHandheldGame = z;
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateGameTitle() {
        HandheldAdapter handheldAdapter = this.mGridAdapter;
        this.mGameTitle.setVisibility(handheldAdapter != null && handheldAdapter.getItemCount() > 0 ? 0 : 8);
    }

    private void updateMargin(int i) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mRecyclerView.getLayoutParams();
        if (marginLayoutParams == null) {
            return;
        }
        marginLayoutParams.setMarginStart(i);
        marginLayoutParams.setMarginEnd(i);
        this.mRecyclerView.setLayoutParams(marginLayoutParams);
    }

    public boolean back() {
        HandheldAdapter handheldAdapter = this.mGridAdapter;
        if (handheldAdapter == null || !handheldAdapter.isExpand()) {
            return false;
        }
        Runnable runnable = this.mVisibleCallback;
        if (runnable != null) {
            runnable.run();
        }
        this.mGridAdapter.setExpand(false);
        this.mGameTitle.setText(R.string.handheld_games);
        this.mRecommendTitle.setVisibility(0);
        this.mRecommendView.setVisibility(0);
        this.mNoRecommendView.setVisibility(isRecommendEmpty() ? 0 : 8);
        this.mRecyclerView.removeItemDecoration(this.mGameExpandDecoration);
        this.mRecyclerView.addItemDecoration(this.mGameDecoration);
        this.mRecyclerView.invalidateItemDecorations();
        this.mRecyclerView.requestLayout();
        updateMargin(0);
        focusDefault();
        return true;
    }

    public boolean doKeyDown(int i) {
        int focusRecyclerView;
        if (i == 21 || i == 22) {
            if (this.mGridAdapter.isExpand() || (focusRecyclerView = getFocusRecyclerView()) == -1) {
                return false;
            }
            int position = focusRecyclerView == 0 ? this.mRecyclerView.getLayoutManager().getPosition(this.mRecyclerView.getFocusedChild()) : this.mRecommendView.getLayoutManager().getPosition(this.mRecommendView.getFocusedChild());
            if (i != 21) {
                if (position + 1 >= (focusRecyclerView == 0 ? this.mGridAdapter.getItemCount() : this.mRecommendAdapter.getItemCount())) {
                    if (focusRecyclerView == 0) {
                        moveToMygamePosition(0);
                    } else {
                        moveToRecommendPosition(0);
                    }
                    return true;
                }
            } else if (position - 1 < 0) {
                int itemCount = (focusRecyclerView == 0 ? this.mGridAdapter.getItemCount() : this.mRecommendAdapter.getItemCount()) - 1;
                if (focusRecyclerView == 0) {
                    moveToMygamePosition(itemCount);
                } else {
                    moveToRecommendPosition(itemCount);
                }
                return true;
            }
        } else {
            if (i == 97) {
                if (!this.mGridAdapter.isExpand()) {
                    return focusDefault();
                }
                back();
                return true;
            }
            if (i == 100) {
                return true;
            }
        }
        return false;
    }

    public CardHelper getCardHelper() {
        if (this.mCardHelper == null) {
            this.mCardHelper = new CardHelper(getAppContext());
        }
        return this.mCardHelper;
    }

    /* renamed from: lambda$notifyDataSetChanged$0$cn-nubia-gamelauncher-fragment-HandheldFragment, reason: not valid java name */
    /* synthetic */ void m237xf913ea7c() {
        this.mRecommendAdapter.notifyDataSetChanged();
        this.mNoRecommendView.setVisibility(isRecommendEmpty() ? 0 : 8);
    }

    /* renamed from: lambda$notifyItemChanged$1$cn-nubia-gamelauncher-fragment-HandheldFragment, reason: not valid java name */
    /* synthetic */ void m238xf90ee616(int i, String str) {
        this.mRecommendAdapter.notifyItemChanged(i, str);
    }

    @Override // cn.nubia.gamelauncher.adapter.HandheldAdapter.AppClickListener
    public void onAppBeanClick(AppListItemBean appListItemBean, String str, boolean z) {
        if (appListItemBean == null) {
            return;
        }
        if (z) {
            expand();
            return;
        }
        try {
            String componentName = appListItemBean.getComponentName();
            Log.d("Handheld", "onAppBeanClick() hasCloneApp() : " + getCardHelper().hasCloneApp(componentName));
            if (appListItemBean.isAddItem()) {
                return;
            }
            if (appListItemBean.isShortcut()) {
                ShortCutHelper.getInstance().startShortcut(appListItemBean.getShortcutInfo());
                return;
            }
            if (getCardHelper().hasCloneApp(componentName)) {
                getCardHelper().showDialog(appListItemBean);
            } else {
                getCardHelper().startApp(appListItemBean, false);
            }
            doTrack(appListItemBean, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Log.d("Handheld", "---->onCreateView()");
        View inflate = layoutInflater.inflate(R.layout.handheld_layout, viewGroup, false);
        this.mRootScrollView = (NestedScrollView) inflate.findViewById(R.id.handheld_scrollView);
        this.mContext = getActivity();
        this.mCardHelper = new CardHelper(this.mContext);
        registerListener();
        updateGameList();
        initView(inflate);
        getRecommendGameList();
        this.mHandler.removeCallbacks(this.moveToFirst);
        this.mHandler.postDelayed(this.moveToFirst, 300L);
        doStateRequest();
        this.mOnFirst = true;
        doTrackHandheldUsed(true);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Handheld", "---->onDestroyView()");
        doTrackHandheldUsed(false);
        this.mDownloadList.clear();
        this.mCardHelper = null;
        unregisterListener();
    }

    public boolean onDispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 19 && keyEvent.getAction() == 1) {
            if (getFocusRecyclerView() == 0) {
                this.mRootScrollView.smoothScrollTo(0, 0);
            }
        } else if (keyEvent.getKeyCode() == 20 && keyEvent.getAction() == 1 && getFocusRecyclerView() == 1) {
            this.mRootScrollView.smoothScrollTo(0, HighLightsUtils.NUBIA_TWIN_USERID);
        }
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        Log.i("Handheld", "onHiddenChanged : " + z);
        if (!z) {
            focusDefault();
        }
        doTrackHandheldUsed(!z);
    }

    @Override // cn.nubia.gamelauncher.commoninterface.IGetAppStatusDataCallBack
    public void onLoadAddAppListDone(ArrayList<AppListItemBean> arrayList, int i) {
        Log.d("Handheld", "---->onLoadAddAppListDone() hasAddCount : " + i);
        updateAllGameCard();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        Log.d("Handheld", "---->onPause()");
        unRegisterDownloadObserver();
    }

    @Override // cn.nubia.gamelauncher.adapter.RecommendAdapter.RecommendClickListener
    public void onRecommendItemClick(GameItemBean gameItemBean, String str) {
        if (gameItemBean == null) {
            return;
        }
        Log.d("Handheld", "---->onItemClick()" + gameItemBean.getPackageName());
        GameCenterHelper.startAppDetail(this.mContext, gameItemBean.getPackageName());
    }

    @Override // cn.nubia.gamelauncher.adapter.RecommendAdapter.RecommendClickListener
    public void onRecommendMoreClick() {
        if (Util.isTencentAppStore()) {
            GameCenterHelper.startTencentGameRecommend(this.mContext);
        } else if (!Util.isZte() || Util.isZteZType()) {
            GameCenterHelper.startHandHeldGame(this.mContext);
        } else {
            GameCenterHelper.startZteRecommend(this.mContext);
        }
        LobbySoundPoolHelper.getInstance().play();
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "gamespace_game_recommendation_click");
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Log.d("Handheld", "---->onResume()");
        AppUsageStatsHelper.getInstance().updateAppUsageStat();
        registerDownloadObserver();
        AppAddModel.getInstance().setCurrentMode(2);
    }

    @Override // cn.nubia.common.helper.AppUsageStatsHelper.AppUsageStatsChangedListener
    public void onUsageStatsChanged(boolean z) {
        updateAllGameCard();
    }

    public void setLottieCallback(Runnable runnable, Runnable runnable2) {
        this.mVisibleCallback = runnable;
        this.mInvisibleCallback = runnable2;
    }

    public void unRegisterDownloadObserver() {
        Log.i("Handheld", "unRegisterDownloadObserver");
        try {
            GameLauncherApplication.CONTEXT.getContentResolver().unregisterContentObserver(this.mDownloadObserver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateGameList() {
        CopyOnWriteArrayList<AppListItemBean> gameList = getGameList();
        if (gameList == null) {
            return;
        }
        fillList(gameList);
        Log.d("Handheld", "---->updateGameList() mList.size() : " + this.mList.size());
    }
}
