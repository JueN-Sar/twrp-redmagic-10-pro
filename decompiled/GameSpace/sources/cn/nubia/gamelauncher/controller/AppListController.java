package cn.nubia.gamelauncher.controller;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.adapter.HasAddAdapter;
import cn.nubia.gamelauncher.bean.AppListItemBean;
import cn.nubia.gamelauncher.commoninterface.IGetAppStatusDataCallBack;
import cn.nubia.gamelauncher.helper.ShortCutHelper;
import cn.nubia.gamelauncher.model.AppAddModel;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class AppListController {
    public static final String TAG = "manager";
    private boolean isHostMode;
    private IGetAppStatusDataCallBack mCallBack;
    private Handler mHandler;
    private Context mContext = null;
    private AppAddModel mAppAddModel = null;
    private RecyclerView mAppAddList = null;
    private HasAddAdapter mAdapter = null;
    private ArrayList<AppListItemBean> mList = new ArrayList<>();

    public AppListController() {
        this.mHandler = null;
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initAdapter(int i) {
        HasAddAdapter hasAddAdapter = new HasAddAdapter();
        this.mAdapter = hasAddAdapter;
        hasAddAdapter.setOnAppAddedListener(this.mAppAddModel);
        this.mAdapter.setType(0);
        this.mAdapter.setDataList(this.mList);
        this.mAdapter.isHostMode(this.isHostMode);
        this.mAppAddList.setAdapter(this.mAdapter);
        this.mAdapter.setHasAddCount(i);
    }

    public void init(Activity activity, boolean z) {
        Log.i(TAG, "AppListController init");
        this.isHostMode = z;
        this.mContext = activity.getApplicationContext();
        RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.app_add_list);
        this.mAppAddList = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.mContext, 1, false));
        AppAddModel appAddModel = AppAddModel.getInstance();
        this.mAppAddModel = appAddModel;
        if (appAddModel.getAppAddedList() == null || this.mAppAddModel.getAppNotAddList() == null) {
            Log.i(TAG, "AppListController init wait callback");
        } else {
            this.mList.clear();
            this.mList.addAll(this.mAppAddModel.getAppAddedList());
            this.mList.addAll(this.mAppAddModel.getAppNotAddList());
            initAdapter(this.mAppAddModel.getAppAddedList().size() + ShortCutHelper.getInstance().getShortcutAddList().size());
        }
        AppAddModel appAddModel2 = this.mAppAddModel;
        IGetAppStatusDataCallBack iGetAppStatusDataCallBack = new IGetAppStatusDataCallBack() { // from class: cn.nubia.gamelauncher.controller.AppListController.1
            @Override // cn.nubia.gamelauncher.commoninterface.IGetAppStatusDataCallBack
            public void onLoadAddAppListDone(ArrayList<AppListItemBean> arrayList, int i) {
                Log.i(AppListController.TAG, "onLoadAddAppListDone() hasAddCount : " + i);
                AppListController.this.mList.clear();
                AppListController.this.mList.addAll(arrayList);
                if (AppListController.this.mAdapter == null) {
                    AppListController.this.initAdapter(i);
                } else {
                    AppListController.this.mAdapter.setHasAddCount(i);
                    AppListController.this.mAdapter.notifyDataSetChanged();
                }
            }
        };
        this.mCallBack = iGetAppStatusDataCallBack;
        appAddModel2.resisterGetAppStatusDataCallBack(iGetAppStatusDataCallBack);
    }

    public void onDestory() {
        AppAddModel.getInstance().unResisterGetAppStatusDataCallBack(this.mCallBack);
        this.mCallBack = null;
    }

    public void onPasue() {
    }

    public void onResume() {
        Log.i(TAG, "AppListController onResume");
        resetSelectStatue();
    }

    void resetSelectStatue() {
        this.mList.clear();
        ArrayList<AppListItemBean> appAddedList = this.mAppAddModel.getAppAddedList();
        if (appAddedList != null && !appAddedList.isEmpty()) {
            Iterator<AppListItemBean> it = appAddedList.iterator();
            while (it.hasNext()) {
                it.next().setSelect(true);
            }
            this.mList.addAll(appAddedList);
        }
        ArrayList<AppListItemBean> appNotAddList = this.mAppAddModel.getAppNotAddList();
        if (appNotAddList != null && !appNotAddList.isEmpty()) {
            Iterator<AppListItemBean> it2 = appNotAddList.iterator();
            while (it2.hasNext()) {
                it2.next().setSelect(false);
            }
            this.mList.addAll(appNotAddList);
        }
        if (this.mAdapter != null) {
            Log.i(TAG, "AppListController resetSelectStatue mList = " + this.mList + "   size == " + this.mList.size());
            if (appAddedList == null || appNotAddList.isEmpty()) {
                this.mAdapter.setHasAddCount(0);
            } else {
                this.mAdapter.setHasAddCount(appAddedList.size());
            }
            this.mAdapter.notifyDataSetChanged();
        }
    }
}
