package cn.nubia.gamelauncher.neostore;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.gamelauncher.bean.AppListItemBean;
import cn.nubia.gamelauncher.bean.RelevantBean;
import cn.nubia.gamelauncher.helper.Controller;
import cn.nubia.gamelauncher.observer.OperationKeyObserver;
import cn.nubia.gamelauncher.util.GameCenterHelper;
import cn.nubia.gamelauncher.util.LogUtil;
import cn.nubia.neostore.api.callback.ICallback;
import cn.nubia.neostore.api.model.CallbackInfo;
import cn.nubia.neostore.api.model.ErrorMsg;
import cn.nubia.neostore.data.bean.RevelantAppInfoBean;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class OperationHelper implements OperationKeyObserver.Callback {
    final String LINK;
    final String TAG = "Full";
    boolean isOperationKeyClosed;
    OperationCallback mCallback;
    Context mContext;
    private Handler mHandler;
    CopyOnWriteArrayList<AppListItemBean> mList;
    AppListItemBean mSelectedItem;
    private HandlerThread mWorkThread;

    public interface OperationCallback {
        void onGiftChanged(boolean z);

        void onOperationKeyChanged(boolean z);

        void onRelevantChanged(List<RelevantBean> list);

        void onVipChanged(boolean z);
    }

    public OperationHelper(Context context, OperationCallback operationCallback) {
        this.isOperationKeyClosed = true;
        this.LINK = CommonUtil.isNubia() ? "ndl://cn.nubia.neogamecenter/appgiftlist/" : "ndl://com.zte.quickgame/appgiftlist/";
        this.mList = new CopyOnWriteArrayList<>();
        this.mContext = context;
        this.mCallback = operationCallback;
        OperationKeyObserver.getInstance(context).addCallback(this);
        this.isOperationKeyClosed = OperationKeyObserver.getInstance(context).isOperationKeyClose();
        initWorkHandler();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doLoad() {
        String packageName = this.mSelectedItem.getPackageName();
        LogUtil.d("Full", "WorkThread - doLoad() pkg : " + packageName);
        NeoHelper.initIfNeed();
        getRelevantAppByPackageName(packageName, 3);
        isVipGame(packageName);
        getGiftListForPackage(packageName, 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AppListItemBean findBeanInList(String str) {
        CopyOnWriteArrayList<AppListItemBean> copyOnWriteArrayList = this.mList;
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.size() <= 0) {
            return null;
        }
        Iterator<AppListItemBean> it = this.mList.iterator();
        while (it.hasNext()) {
            AppListItemBean next = it.next();
            if (next.getPackageName().equals(str)) {
                return next;
            }
        }
        return null;
    }

    private void initWorkHandler() {
        HandlerThread handlerThread = new HandlerThread("OperationThread");
        this.mWorkThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mWorkThread.getLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSameItem(String str, AppListItemBean appListItemBean) {
        if (str == null || appListItemBean == null) {
            return false;
        }
        return str.equals(appListItemBean.getPackageName());
    }

    private void runOnWorkThread(Runnable runnable) {
        if (Process.myTid() == this.mWorkThread.getThreadId()) {
            runnable.run();
        } else {
            this.mHandler.post(runnable);
        }
    }

    public void clickGift(AppListItemBean appListItemBean) {
        if (appListItemBean == null) {
            return;
        }
        GameCenterHelper.startOperation(this.mContext, this.LINK + appListItemBean.getPackageName());
    }

    public void clickRelevantApp(AppListItemBean appListItemBean) {
        RelevantBean currentRelevant;
        if (appListItemBean == null || (currentRelevant = appListItemBean.getCurrentRelevant()) == null) {
            return;
        }
        GameCenterHelper.startOperation(this.mContext, "gameplacesdk://appdetail?packageName=" + currentRelevant.pkg);
    }

    public void clickVip(AppListItemBean appListItemBean) {
        GameCenterHelper.startVip(this.mContext);
    }

    public void exit() {
        OperationKeyObserver.getInstance(this.mContext).removeCallback(this);
        this.mCallback = null;
    }

    public void getGiftListForPackage(final String str, int i) {
        NeoHelper.getGiftListForPackage(null, str, i, new ICallback<CallbackInfo>() { // from class: cn.nubia.gamelauncher.neostore.OperationHelper.2
            @Override // cn.nubia.neostore.api.callback.ICallback
            public void onError(ErrorMsg errorMsg) {
                LogUtil.d("Full", "Gift - onError(" + str + ") errCode : " + errorMsg.errCode + ", errMsg : " + errorMsg.errMsg);
            }

            @Override // cn.nubia.neostore.api.callback.ICallback
            public void onSuccess(CallbackInfo callbackInfo) {
                LogUtil.d("Full", "Gift - onSuccess(" + str + ") callback : " + callbackInfo);
                if (OperationHelper.this.mCallback == null || callbackInfo == null) {
                    return;
                }
                LogUtil.d("Full", "Gift - onSuccess(" + str + ") data : " + callbackInfo.getData());
                String tag = callbackInfo.getTag();
                boolean z = ((List) callbackInfo.getData()).size() > 0;
                OperationHelper operationHelper = OperationHelper.this;
                if (operationHelper.isSameItem(tag, operationHelper.mSelectedItem)) {
                    OperationHelper.this.mSelectedItem.hasGift = z;
                    OperationHelper.this.mCallback.onGiftChanged(z);
                } else {
                    AppListItemBean findBeanInList = OperationHelper.this.findBeanInList(tag);
                    if (findBeanInList == null) {
                        return;
                    }
                    findBeanInList.hasGift = z;
                }
            }
        });
    }

    public void getRelevantAppByPackageName(final String str, int i) {
        NeoHelper.getRevelantAppByPackageName(str, i, new ICallback<CallbackInfo>() { // from class: cn.nubia.gamelauncher.neostore.OperationHelper.1
            @Override // cn.nubia.neostore.api.callback.ICallback
            public void onError(ErrorMsg errorMsg) {
                LogUtil.d("Full", "Relevant - onError(" + str + ") errCode : " + errorMsg.errCode + ", errMsg : " + errorMsg.errMsg);
            }

            @Override // cn.nubia.neostore.api.callback.ICallback
            public void onSuccess(CallbackInfo callbackInfo) {
                LogUtil.d("Full", "Relevant - onSuccess(" + str + ") info : " + callbackInfo);
                if (OperationHelper.this.mCallback == null || callbackInfo == null) {
                    return;
                }
                LogUtil.d("Full", "Relevant - onSuccess(" + str + ") data : " + callbackInfo.getData());
                String tag = callbackInfo.getTag();
                List<RevelantAppInfoBean> list = (List) callbackInfo.getData();
                ArrayList arrayList = new ArrayList();
                for (RevelantAppInfoBean revelantAppInfoBean : list) {
                    RelevantBean relevantBean = new RelevantBean();
                    relevantBean.pkg = revelantAppInfoBean.getPackageName();
                    relevantBean.url = revelantAppInfoBean.getIconUrl();
                    if (relevantBean.pkg != null && relevantBean.url != null) {
                        arrayList.add(relevantBean);
                    }
                }
                OperationHelper operationHelper = OperationHelper.this;
                if (operationHelper.isSameItem(tag, operationHelper.mSelectedItem)) {
                    OperationHelper.this.mSelectedItem.relevantList = arrayList;
                    OperationHelper.this.mCallback.onRelevantChanged(arrayList);
                    Controller.getInstance().relevantChanged(tag);
                } else {
                    AppListItemBean findBeanInList = OperationHelper.this.findBeanInList(tag);
                    if (findBeanInList == null) {
                        return;
                    }
                    findBeanInList.relevantList = arrayList;
                }
            }
        });
    }

    public boolean isOperationKeyClosed() {
        return this.isOperationKeyClosed;
    }

    public void isVipGame(final String str) {
        NeoHelper.isVipGame(str, new ICallback<CallbackInfo>() { // from class: cn.nubia.gamelauncher.neostore.OperationHelper.3
            @Override // cn.nubia.neostore.api.callback.ICallback
            public void onError(ErrorMsg errorMsg) {
                LogUtil.d("Full", "Vip - onError(" + str + ") errCode : " + errorMsg.errCode + ", errMsg : " + errorMsg.errMsg);
            }

            @Override // cn.nubia.neostore.api.callback.ICallback
            public void onSuccess(CallbackInfo callbackInfo) {
                LogUtil.d("Full", "Vip - onSuccess(" + str + ") callback : " + callbackInfo);
                if (OperationHelper.this.mCallback == null || callbackInfo == null) {
                    return;
                }
                LogUtil.d("Full", "Vip - onSuccess(" + str + ") data : " + callbackInfo.getData());
                String tag = callbackInfo.getTag();
                Boolean bool = (Boolean) callbackInfo.getData();
                OperationHelper operationHelper = OperationHelper.this;
                if (operationHelper.isSameItem(tag, operationHelper.mSelectedItem)) {
                    OperationHelper.this.mSelectedItem.isVip = bool.booleanValue();
                    OperationHelper.this.mCallback.onVipChanged(bool.booleanValue());
                } else {
                    AppListItemBean findBeanInList = OperationHelper.this.findBeanInList(tag);
                    if (findBeanInList == null) {
                        return;
                    }
                    findBeanInList.isVip = bool.booleanValue();
                }
            }
        });
    }

    public void loadOperation(AppListItemBean appListItemBean) {
        if (appListItemBean == null || this.isOperationKeyClosed || NeoHelper.isNotSupportNubiaNgcApi()) {
            return;
        }
        LogUtil.d("Full", "loadOperation() pkg : " + appListItemBean.getPackageName());
        if (this.mList.contains(appListItemBean)) {
            return;
        }
        this.mList.add(appListItemBean);
        this.mSelectedItem = appListItemBean;
        runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.neostore.OperationHelper$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                OperationHelper.this.doLoad();
            }
        });
    }

    @Override // cn.nubia.gamelauncher.observer.OperationKeyObserver.Callback
    public void onOperationKeyChanged(boolean z) {
        LogUtil.d("Full", "---->onOperationKeyChanged() isClose : " + z);
        this.isOperationKeyClosed = z;
        OperationCallback operationCallback = this.mCallback;
        if (operationCallback == null) {
            return;
        }
        operationCallback.onOperationKeyChanged(z);
    }
}
