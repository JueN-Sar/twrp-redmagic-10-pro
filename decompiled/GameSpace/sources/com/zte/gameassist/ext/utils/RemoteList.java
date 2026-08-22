package com.zte.gameassist.ext.utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.zte.gameassist.aidl.ICallback;
import com.zte.gameassist.ext.common.GAControllerProxy;
import com.zte.gameassist.ext.common.MutableData;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import javax.annotation.Nonnull;

/* loaded from: classes2.dex */
public class RemoteList extends ICallback.Stub {
    public static final String TAG = "GlobalList";
    private final Handler mHandler;
    private final AtomicBoolean mIsMonitor;
    private final String mListName;
    private final List<String> mLocalValues;
    private final String mRemoteListName;
    public final MutableData<List<String>> mValues;

    public RemoteList(String str) {
        MutableData<List<String>> mutableData = new MutableData<>(new ArrayList());
        this.mValues = mutableData;
        this.mLocalValues = new ArrayList();
        this.mIsMonitor = new AtomicBoolean(false);
        this.mHandler = new Handler(Looper.myLooper() != null ? Looper.myLooper() : Looper.getMainLooper());
        this.mListName = str;
        this.mRemoteListName = str + "@" + Integer.toHexString(hashCode());
        mutableData.setData(getBaseValue());
        monitorRemoteList();
    }

    private List<String> getBaseValue() {
        try {
            return GAControllerProxy.getInstance().getMutexTags(this.mListName);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList();
        }
    }

    public RemoteList addValue(String str) {
        if (!this.mLocalValues.contains(str)) {
            this.mLocalValues.add(str);
            try {
                GAControllerProxy.getInstance().mutexTag(true, this.mListName, str, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    @Override // com.zte.gameassist.aidl.ICallback
    public void callback(@Nonnull String str, @Nonnull final Bundle bundle) throws RemoteException {
        if (this.mListName.equals(str) && bundle.containsKey("mutextNames")) {
            this.mHandler.post(new Runnable() { // from class: com.zte.gameassist.ext.utils.RemoteList$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    RemoteList.this.m458lambda$callback$0$comztegameassistextutilsRemoteList(bundle);
                }
            });
        }
    }

    protected void finalize() throws Throwable {
        super.finalize();
        release();
    }

    public String getListName() {
        return this.mListName;
    }

    public List<String> getListValues() {
        return this.mValues.getData();
    }

    public List<String> getLocalValues() {
        return new ArrayList(this.mLocalValues);
    }

    /* renamed from: lambda$callback$0$com-zte-gameassist-ext-utils-RemoteList, reason: not valid java name */
    /* synthetic */ void m458lambda$callback$0$comztegameassistextutilsRemoteList(Bundle bundle) {
        ArrayList<String> stringArrayList = bundle.getStringArrayList("mutextNames");
        if (!stringArrayList.equals(this.mValues.getData())) {
            this.mValues.setData(new ArrayList(stringArrayList));
        }
    }

    public synchronized RemoteList monitorRemoteList() {
        if (!this.mIsMonitor.compareAndSet(false, true)) {
            return this;
        }
        try {
            GAControllerProxy.getInstance().monitorMutexTag(true, this.mRemoteListName, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public void release() {
        if (this.mValues.observeCount() != 0) {
            Log.i(TAG, "release , but observeCount=" + this.mValues.observeCount());
        }
        unMonitorRemoteList();
        this.mLocalValues.forEach(new Consumer() { // from class: com.zte.gameassist.ext.utils.RemoteList$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                RemoteList.this.removeValue((String) obj);
            }
        });
    }

    public RemoteList removeValue(String str) {
        if (this.mLocalValues.remove(str)) {
            try {
                GAControllerProxy.getInstance().mutexTag(false, this.mListName, str, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public synchronized RemoteList unMonitorRemoteList() {
        if (this.mIsMonitor.compareAndSet(true, false)) {
            try {
                GAControllerProxy.getInstance().monitorMutexTag(false, this.mRemoteListName, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }
}
