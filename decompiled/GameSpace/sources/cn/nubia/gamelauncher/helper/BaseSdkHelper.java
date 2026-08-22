package cn.nubia.gamelauncher.helper;

import android.content.Context;
import cn.nubia.gamelauncher.bean.AppListItemBean;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public abstract class BaseSdkHelper {
    protected CopyOnWriteArrayList<Callback> mCallbacks = new CopyOnWriteArrayList<>();
    protected ArrayList<AppListItemBean> mOperationList = new ArrayList<>();

    public interface Callback {
        void onOperationResult(ArrayList<AppListItemBean> arrayList);
    }

    public interface RedDotCallback {
        void onRedDotResult(String str);
    }

    public void addCallback(Callback callback) {
        if (this.mCallbacks.contains(callback)) {
            return;
        }
        this.mCallbacks.add(callback);
    }

    public void doGetGameListRequest(ArrayList<String> arrayList, int i) {
    }

    public void removeCallback(Callback callback) {
        if (this.mCallbacks.contains(callback)) {
            this.mCallbacks.remove(callback);
        }
    }

    public void requestHasRedDot(Context context, RedDotCallback redDotCallback) {
    }

    public void requestOperationList(Context context, Callback callback) {
    }

    public void requestRedDotClick(Context context, String str) {
    }
}
