package cn.nubia.gamelauncher.atmosphere;

import android.os.FileObserver;
import android.util.Log;

/* loaded from: classes.dex */
public class LiveAtmosphereObserver extends FileObserver {
    public LiveAtmosphereObserver(String str) {
        super(str, 960);
    }

    private void notifyDynamicUpdate(String str, boolean z) {
        Log.d(LiveAtmosphereManager.TAG, "notifyDynamicUpdate(" + str + ") is not hi " + str);
        if (LiveAtmosphereManager.getInstance().isHighLightPath(str)) {
            LiveAtmosphereManager.getInstance().startTraversalDirectory(z);
        }
    }

    @Override // android.os.FileObserver
    public void onEvent(int i, String str) {
        if (str != null) {
            if (i == 64) {
                Log.d(LiveAtmosphereManager.TAG, "FileObserver.MOVED_FROM : " + str);
                notifyDynamicUpdate(str, false);
                return;
            }
            if (i == 128) {
                Log.d(LiveAtmosphereManager.TAG, "FileObserver.MOVED_TO : " + str);
                notifyDynamicUpdate(str, false);
            } else if (i == 256) {
                Log.d(LiveAtmosphereManager.TAG, "FileObserver.CREATE : " + str);
                notifyDynamicUpdate(str, true);
            } else {
                if (i != 512) {
                    return;
                }
                Log.d(LiveAtmosphereManager.TAG, "FileObserver.DELETE : " + str);
                notifyDynamicUpdate(str, true);
            }
        }
    }
}
