package cn.nubia.gamelauncher.helper;

import android.content.Context;
import cn.nubia.gamelauncher.atmosphere.Atmosphere;
import cn.nubia.gamelauncher.bean.AppListItemBean;
import cn.nubia.gamelauncher.helper.BaseSdkHelper;
import cn.nubia.gamelauncher.neostore.NeoHelper;
import cn.nubia.gamelauncher.util.LogUtil;
import cn.nubia.neostore.api.callback.ICallback;
import cn.nubia.neostore.api.model.ErrorMsg;
import cn.nubia.neostore.api.model.NubiaGameCardInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class SDKHelper extends BaseSdkHelper implements ICallback<List<NubiaGameCardInfo>> {
    private final String TAG;

    private static class SDKHelperHolder {
        public static final SDKHelper INSTANCE = new SDKHelper();

        private SDKHelperHolder() {
        }
    }

    private SDKHelper() {
        this.TAG = "nubia";
    }

    public static SDKHelper getInstance() {
        return SDKHelperHolder.INSTANCE;
    }

    private void notifyAllCallbacks(ArrayList<AppListItemBean> arrayList) {
        Iterator<BaseSdkHelper.Callback> it = this.mCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onOperationResult(arrayList);
        }
    }

    @Override // cn.nubia.neostore.api.callback.ICallback
    public void onError(ErrorMsg errorMsg) {
        LogUtil.d("nubia", "------>onError() errorMsg = " + errorMsg);
    }

    @Override // cn.nubia.neostore.api.callback.ICallback
    public void onSuccess(List<NubiaGameCardInfo> list) {
        LogUtil.d("nubia", "onSuccess() list = " + list);
        if (list == null) {
            return;
        }
        LogUtil.d("nubia", "onSuccess() list = " + list.size());
        this.mOperationList.clear();
        while (list.size() > 0) {
            NubiaGameCardInfo nubiaGameCardInfo = null;
            for (NubiaGameCardInfo nubiaGameCardInfo2 : list) {
                if (nubiaGameCardInfo == null || nubiaGameCardInfo2.cardPosition < nubiaGameCardInfo.cardPosition) {
                    nubiaGameCardInfo = nubiaGameCardInfo2;
                }
            }
            this.mOperationList.add(new AppListItemBean(nubiaGameCardInfo.cardName, nubiaGameCardInfo.cardId, nubiaGameCardInfo.cardPosition, nubiaGameCardInfo.cardCoverUrl, Atmosphere.TYPE_NET));
            list.remove(nubiaGameCardInfo);
            LogUtil.d("nubia", "onSuccess() list = " + list.size());
        }
        LogUtil.d("nubia", "onSuccess() list = " + this.mOperationList.size());
        notifyAllCallbacks(this.mOperationList);
    }

    @Override // cn.nubia.gamelauncher.helper.BaseSdkHelper
    public void requestOperationList(Context context, BaseSdkHelper.Callback callback) {
        LogUtil.d("nubia", "requestOperationList()");
        addCallback(callback);
        NeoHelper.initIfNeed();
    }
}
