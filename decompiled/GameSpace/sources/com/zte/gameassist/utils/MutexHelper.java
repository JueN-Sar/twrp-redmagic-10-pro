package com.zte.gameassist.utils;

import android.os.Bundle;
import android.os.RemoteException;
import com.zte.gameassist.aidl.ICallback;
import com.zte.gameassist.aidl.IGameAssistController;
import com.zte.shared.wrapper.GameAssistControllerWrapper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class MutexHelper extends ICallback.Stub {
    private final MutexCallback mCallback;
    private final String mMonitorMutexName;
    private List<String> mMonitorTags = new ArrayList();
    private final String mMutexGroupName;
    private final String mMutexName;

    public interface MutexCallback {
        void onMutexChanged(List<String> list);
    }

    public MutexHelper(String str, String str2, MutexCallback mutexCallback) {
        this.mMutexGroupName = str;
        this.mMutexName = str2;
        this.mCallback = mutexCallback;
        this.mMonitorMutexName = str + "@" + Integer.toHexString(hashCode());
        this.mMonitorTags.addAll(getTags());
    }

    public MutexHelper addMutexTag() {
        try {
            getGameAssistController().mutexTag(true, this.mMutexGroupName, this.mMutexName, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override // com.zte.gameassist.aidl.ICallback
    public void callback(String str, Bundle bundle) throws RemoteException {
        if (this.mMutexGroupName.equals(str) && bundle.containsKey("mutextNames")) {
            ArrayList arrayList = new ArrayList();
            Iterator<String> it = bundle.getStringArrayList("mutextNames").iterator();
            boolean z = false;
            while (it.hasNext()) {
                String next = it.next();
                if (!this.mMutexName.equals(next)) {
                    arrayList.add(next);
                    if (!this.mMonitorTags.contains(next)) {
                        z = true;
                    }
                }
            }
            if (this.mMonitorTags.size() == arrayList.size() && !z) {
                return;
            }
            this.mMonitorTags.clear();
            this.mMonitorTags.addAll(arrayList);
            MutexCallback mutexCallback = this.mCallback;
            if (mutexCallback != null) {
                mutexCallback.onMutexChanged(this.mMonitorTags);
            }
        }
    }

    protected IGameAssistController getGameAssistController() {
        return GameAssistControllerWrapper.getGameAssistController();
    }

    public String getMutexGroupName() {
        return this.mMutexGroupName;
    }

    public String getMutexName() {
        return this.mMutexName;
    }

    public List<String> getTags() {
        try {
            return getGameAssistController().getMutexTags(this.mMutexGroupName);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList();
        }
    }

    public boolean hasMutexTags() {
        Iterator<String> it = getTags().iterator();
        while (it.hasNext()) {
            if (!this.mMutexName.equals(it.next())) {
                return true;
            }
        }
        return false;
    }

    public MutexHelper monitorMutexTag() {
        try {
            getGameAssistController().monitorMutexTag(true, this.mMonitorMutexName, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public MutexHelper removeMutexTag() {
        try {
            getGameAssistController().mutexTag(false, this.mMutexGroupName, this.mMutexName, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public MutexHelper unmonitorMutexTag() {
        try {
            getGameAssistController().monitorMutexTag(false, this.mMonitorMutexName, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
}
