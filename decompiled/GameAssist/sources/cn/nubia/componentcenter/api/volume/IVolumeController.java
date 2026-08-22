package cn.nubia.componentcenter.api.volume;

import com.zte.gameassist.common.ISliderProxy;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public interface IVolumeController extends ISliderProxy<VolumeListener> {
    void dump(PrintWriter printWriter, String str);

    String getHeadsetType();

    int getMaxVolume();

    int getVolume();

    void setVolume(int i2);
}
