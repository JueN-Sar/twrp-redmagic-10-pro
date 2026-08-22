package com.zte.gameassist.ext.utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.NonNull;
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

    public RemoteList(@NonNull String str) {
        MutableData<List<String>> mutableData = new MutableData<>(new ArrayList());
        this.mValues = mutableData;
        this.mLocalValues = new ArrayList();
        this.mIsMonitor = new AtomicBoolean(false);
        this.mHandler = new Handler(Looper.myLooper() != null ? Looper.myLooper() : Looper.getMainLooper());
        this.mListName = str;
        this.mRemoteListName = str + "@" + Integer.toHexString(hashCode());
        mutableData.g(getBaseValue());
        monitorRemoteList();
    }

    private List<String> getBaseValue() {
        try {
            return GAControllerProxy.c().d(this.mListName);
        } catch (Exception e2) {
            e2.printStackTrace();
            return new ArrayList();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$callback$0(Bundle bundle) {
        ArrayList<String> stringArrayList = bundle.getStringArrayList("mutextNames");
        if (!stringArrayList.equals(this.mValues.b())) {
            this.mValues.g(new ArrayList(stringArrayList));
        }
    }

    public RemoteList addValue(@NonNull String str) {
        if (!this.mLocalValues.contains(str)) {
            this.mLocalValues.add(str);
            try {
                GAControllerProxy.c().g(true, this.mListName, str, this);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return this;
    }

    @Override // com.zte.gameassist.aidl.ICallback
    public void callback(@Nonnull String str, @Nonnull final Bundle bundle) {
        if (this.mListName.equals(str) && bundle.containsKey("mutextNames")) {
            this.mHandler.post(new Runnable() { // from class: com.zte.gameassist.ext.utils.b
                @Override // java.lang.Runnable
                public final void run() {
                    RemoteList.this.lambda$callback$0(bundle);
                }
            });
        }
    }

    protected void finalize() {
        super.finalize();
        release();
    }

    public String getListName() {
        return this.mListName;
    }

    public List<String> getListValues() {
        return (List) this.mValues.b();
    }

    public List<String> getLocalValues() {
        return new ArrayList(this.mLocalValues);
    }

    public synchronized RemoteList monitorRemoteList() {
        if (!this.mIsMonitor.compareAndSet(false, true)) {
            return this;
        }
        try {
            GAControllerProxy.c().f(true, this.mRemoteListName, this);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return this;
    }

    public void release() {
        if (this.mValues.f() != 0) {
            Log.i(TAG, "release , but observeCount=" + this.mValues.f());
        }
        unMonitorRemoteList();
        this.mLocalValues.forEach(new Consumer() { // from class: com.zte.gameassist.ext.utils.a
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                RemoteList.this.removeValue((String) obj);
            }
        });
    }

    public RemoteList removeValue(@NonNull String str) {
        if (this.mLocalValues.remove(str)) {
            try {
                GAControllerProxy.c().g(false, this.mListName, str, this);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return this;
    }

    public synchronized RemoteList unMonitorRemoteList() {
        if (this.mIsMonitor.compareAndSet(true, false)) {
            try {
                GAControllerProxy.c().f(false, this.mRemoteListName, this);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return this;
    }
}
