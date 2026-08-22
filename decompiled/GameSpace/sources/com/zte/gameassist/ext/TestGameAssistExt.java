package com.zte.gameassist.ext;

import android.app.ActivityThread;
import android.app.Application;
import android.content.ComponentName;
import android.util.Log;
import com.zte.gameassist.ext.common.MutableData;
import com.zte.gameassist.ext.system.TopActivityMonitor;
import com.zte.gameassist.ext.utils.ExtendUtils;
import com.zte.gameassist.ext.utils.RemoteList;
import com.zte.gameassist.ext.utils.SettingsHelper;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

/* loaded from: classes2.dex */
public class TestGameAssistExt {
    public static final String TAG = "GameAssistExtTest";
    private final Runnable[] testAll = {new Runnable() { // from class: com.zte.gameassist.ext.TestGameAssistExt$$ExternalSyntheticLambda9
        @Override // java.lang.Runnable
        public final void run() {
            TestGameAssistExt.this.testExtendUtils();
        }
    }, new Runnable() { // from class: com.zte.gameassist.ext.TestGameAssistExt$$ExternalSyntheticLambda10
        @Override // java.lang.Runnable
        public final void run() {
            TestGameAssistExt.this.testGameAssistChannel();
        }
    }, new Runnable() { // from class: com.zte.gameassist.ext.TestGameAssistExt$$ExternalSyntheticLambda11
        @Override // java.lang.Runnable
        public final void run() {
            TestGameAssistExt.this.testRemoteList();
        }
    }, new Runnable() { // from class: com.zte.gameassist.ext.TestGameAssistExt$$ExternalSyntheticLambda1
        @Override // java.lang.Runnable
        public final void run() {
            TestGameAssistExt.this.testSettingsHelper();
        }
    }, new Runnable() { // from class: com.zte.gameassist.ext.TestGameAssistExt$$ExternalSyntheticLambda2
        @Override // java.lang.Runnable
        public final void run() {
            TestGameAssistExt.this.testTopActivityMonitor();
        }
    }};
    private final RemoteList remoteList = new RemoteList("game_test");

    public static void testAll() {
        Stream.of((Object[]) new TestGameAssistExt().testAll).forEach(new Consumer() { // from class: com.zte.gameassist.ext.TestGameAssistExt$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Runnable) obj).run();
            }
        });
    }

    public void testExtendUtils() {
        ExtendUtils.fileExists("/mnt", new Consumer() { // from class: com.zte.gameassist.ext.TestGameAssistExt$$ExternalSyntheticLambda3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Log.w(TestGameAssistExt.TAG, "xlan fileExists " + ((Boolean) obj));
            }
        });
    }

    public void testGameAssistChannel() {
        GameAssistChannel.sendToGameAssist("hello world");
    }

    public void testRemoteList() {
        this.remoteList.mValues.observe(true, new MutableData.Observer() { // from class: com.zte.gameassist.ext.TestGameAssistExt$$ExternalSyntheticLambda4
            @Override // com.zte.gameassist.ext.common.MutableData.Observer
            public final void onChanged(Object obj) {
                Log.w(TestGameAssistExt.TAG, "testRemoteList " + ((List) obj));
            }
        });
        this.remoteList.addValue("xlan");
    }

    public void testSettingsHelper() {
        Application currentApplication = ActivityThread.currentApplication();
        SettingsHelper.putSecureSettings(currentApplication, TAG, "xlan");
        SettingsHelper.putSystemSettings(currentApplication, TAG, "xlan");
        SettingsHelper.putGlobalSettings(currentApplication, TAG, "xlan");
    }

    public void testTopActivityMonitor() {
        TopActivityMonitor.registerFullActivityResumedCallback(new TopActivityMonitor.FullActivityResumedCallback() { // from class: com.zte.gameassist.ext.TestGameAssistExt$$ExternalSyntheticLambda5
            @Override // com.zte.gameassist.ext.system.TopActivityMonitor.FullActivityResumedCallback
            public final void onFullActivityResumed(ComponentName componentName) {
                Log.w(TestGameAssistExt.TAG, "FullActivityResumed " + componentName);
            }
        });
        TopActivityMonitor.registerFullActivityFirstCreateCallback(new TopActivityMonitor.FullActivityFirstCreateCallback() { // from class: com.zte.gameassist.ext.TestGameAssistExt$$ExternalSyntheticLambda6
            @Override // com.zte.gameassist.ext.system.TopActivityMonitor.FullActivityFirstCreateCallback
            public final void onFullActivityFirstCreate(ComponentName componentName) {
                Log.w(TestGameAssistExt.TAG, "FullActivityFirstCreate " + componentName);
            }
        });
        TopActivityMonitor.registerFocusWindowCallback(new TopActivityMonitor.FocusWindowCallback() { // from class: com.zte.gameassist.ext.TestGameAssistExt$$ExternalSyntheticLambda7
            @Override // com.zte.gameassist.ext.system.TopActivityMonitor.FocusWindowCallback
            public final void onFocusWindowChanged(String str) {
                Log.w(TestGameAssistExt.TAG, "FocusWindow " + str);
            }
        });
        TopActivityMonitor.unregisterSystemWindowCallback(new TopActivityMonitor.SystemWindowCallback() { // from class: com.zte.gameassist.ext.TestGameAssistExt$$ExternalSyntheticLambda8
            @Override // com.zte.gameassist.ext.system.TopActivityMonitor.SystemWindowCallback
            public final void onSystemWindowChanged(boolean z, String str) {
                Log.w(TestGameAssistExt.TAG, "System " + z + " " + str);
            }
        });
    }
}
