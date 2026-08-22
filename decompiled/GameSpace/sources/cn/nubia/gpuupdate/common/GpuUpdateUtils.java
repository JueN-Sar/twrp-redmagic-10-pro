package cn.nubia.gpuupdate.common;

import android.content.Context;
import android.util.Log;
import cn.nubia.gamelauncher.gamecontrolpanel.SnapdragonAdrenoGpuView;

/* loaded from: classes.dex */
public class GpuUpdateUtils {
    private static final String TAG = "GpuUpdate_utils";
    private GpuUpdateInterface gpuUpdate;

    static class GpuUpdateDefault implements GpuUpdateInterface {
        GpuUpdateDefault() {
        }

        @Override // cn.nubia.gpuupdate.common.GpuUpdateUtils.GpuUpdateInterface
        public void checkUpgrade() {
            Log.e(GpuUpdateUtils.TAG, "Inter Apk Empty Implement!!!");
        }
    }

    public interface GpuUpdateInterface {
        void checkUpgrade();
    }

    public GpuUpdateUtils(Context context, SnapdragonAdrenoGpuView snapdragonAdrenoGpuView) {
        init(context.getApplicationContext(), snapdragonAdrenoGpuView);
    }

    private void init(Context context, SnapdragonAdrenoGpuView snapdragonAdrenoGpuView) {
        try {
            this.gpuUpdate = (GpuUpdateInterface) Class.forName("cn.nubia.gpuupdate.GpuUpdateImpl").getConstructor(Context.class, SnapdragonAdrenoGpuView.class).newInstance(context, snapdragonAdrenoGpuView);
            Log.d(TAG, "init china class GpuUpdateUtils");
        } catch (Exception e) {
            this.gpuUpdate = new GpuUpdateDefault();
            Log.d(TAG, "init inter class GpuUpdateUtils by error: " + e.getMessage());
        }
    }

    public GpuUpdateInterface getGpuUpdate() {
        return this.gpuUpdate;
    }
}
