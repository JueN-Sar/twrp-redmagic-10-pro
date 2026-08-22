package cn.nubia.tgk.data;

import android.graphics.Bitmap;
import android.util.Log;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class TgkGameInfo implements Cloneable {
    public static final int CUSTOM_CASE_COUNT = 5;
    public static final String TAG = "TgkGameInfo";
    public String gameName;
    public boolean topVisualEffectSw = true;
    public boolean centerVisualEffectSw = true;
    public int centerVisualEffectTransparency = 100;
    public int selectedTableId = 0;
    public int selectedCasePosition = 0;
    public Bitmap picture = null;
    public ArrayList<TgkData> presetTableList = null;
    public ArrayList<TgkData> importTableList = null;
    public int isLandscape = 1;

    public TgkGameInfo(String str) {
        this.gameName = str;
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public TgkGameInfo m339clone() {
        try {
            return (TgkGameInfo) super.clone();
        } catch (CloneNotSupportedException unused) {
            Log.d(TAG, "clone failed!");
            return null;
        }
    }

    public int getIsLandscape() {
        return this.isLandscape;
    }

    public TgkData getSelectedCaseData() {
        if (1 == this.selectedTableId) {
            ArrayList<TgkData> arrayList = this.importTableList;
            if (arrayList != null) {
                return arrayList.get(this.selectedCasePosition);
            }
        } else {
            ArrayList<TgkData> arrayList2 = this.presetTableList;
            if (arrayList2 != null) {
                return arrayList2.get(this.selectedCasePosition);
            }
        }
        return null;
    }

    public void setGameMoreInfo(boolean z, boolean z2, int i) {
        this.topVisualEffectSw = z;
        this.centerVisualEffectSw = z2;
        this.centerVisualEffectTransparency = i;
    }

    public void setIsLandscape(int i) {
        this.isLandscape = i;
    }

    public String toString() {
        return "TgkGameInfo{gameName=" + this.gameName + ", topVisualEffectSw='" + this.topVisualEffectSw + "', centerVisualEffectSw='" + this.centerVisualEffectSw + "', centerVsEftTransparency='" + this.centerVisualEffectTransparency + "', selectedTableId='" + this.selectedTableId + "', selectedCasePosition='" + this.selectedCasePosition + "'}";
    }
}
