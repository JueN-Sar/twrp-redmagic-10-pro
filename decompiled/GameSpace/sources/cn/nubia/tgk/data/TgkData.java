package cn.nubia.tgk.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import cn.nubia.gamecenter.settings.records.utils.HighLightsUtils;
import cn.nubia.gamelauncher.R;
import cn.nubia.tgk.TgkHelper;

/* loaded from: classes2.dex */
public class TgkData {
    public static final int CASE_STATE_CHANGED = 2;
    public static final int CASE_STATE_IN_IMPORT = 8;
    public static final int CASE_STATE_IN_PRESET = 4;
    public static final int CASE_STATE_NONE = 0;
    public static final int CASE_STATE_SELECTD = 1;
    public static final int SW_OFF = 0;
    public static final int SW_ON = 1;
    public long ID;
    public int change;
    public int isLandscape;
    public boolean mainSw;
    public int[] optionArray;
    public boolean[] optionSwArray;
    public String originalName;
    public String packageName;
    public Bitmap picture;
    public Rect[][] pointsArray;
    public int[] rapidFireCountArray;
    public int[] sensitivityArray;
    public int[] setLinkFlagArray;
    public String shotPicture;
    public String showName;
    public int state;
    public String uniqueId;
    public long updateTime;
    public boolean vibrateSw;

    public TgkData() {
        this.state = 0;
        this.mainSw = true;
        this.optionSwArray = new boolean[]{true, true, false};
        this.vibrateSw = true;
        this.sensitivityArray = new int[]{1, 1};
        this.pointsArray = new Rect[][]{new Rect[]{new Rect(216, 400, 300, 484), new Rect(216, 540, 300, 624)}, new Rect[]{new Rect(1140, 400, 1224, 484), new Rect(1140, 540, 1224, 624)}, new Rect[]{new Rect(1940, 400, 2024, 484), new Rect(1940, 540, 2024, 624)}};
        this.optionArray = new int[]{0, 0, 9};
        this.picture = null;
        this.shotPicture = "";
        this.uniqueId = "";
        this.change = 0;
        this.updateTime = 0L;
        this.isLandscape = 1;
        this.rapidFireCountArray = new int[]{4, 4, 4};
        this.setLinkFlagArray = new int[]{0, 0, 0};
    }

    public TgkData(String str, int i) {
        this.state = 0;
        this.mainSw = true;
        this.optionSwArray = new boolean[]{true, true, false};
        this.vibrateSw = true;
        this.sensitivityArray = new int[]{1, 1};
        this.pointsArray = new Rect[][]{new Rect[]{new Rect(216, 400, 300, 484), new Rect(216, 540, 300, 624)}, new Rect[]{new Rect(1140, 400, 1224, 484), new Rect(1140, 540, 1224, 624)}, new Rect[]{new Rect(1940, 400, 2024, 484), new Rect(1940, 540, 2024, 624)}};
        this.optionArray = new int[]{0, 0, 9};
        this.picture = null;
        this.shotPicture = "";
        this.uniqueId = "";
        this.change = 0;
        this.updateTime = 0L;
        this.isLandscape = 1;
        this.rapidFireCountArray = new int[]{4, 4, 4};
        this.setLinkFlagArray = new int[]{0, 0, 0};
        this.packageName = str;
        if (i == 0) {
            this.state |= 4;
            return;
        }
        if (1 == i) {
            this.state |= 8;
            return;
        }
        this.originalName = str;
        this.showName = null;
        this.optionSwArray = null;
        this.sensitivityArray = null;
        this.pointsArray = null;
        this.optionArray = null;
    }

    public static int getTableId(int i) {
        int i2 = (i & 4) > 0 ? 0 : 1;
        if ((i & 8) > 0) {
            return 1;
        }
        return i2;
    }

    private void setTgkData(String str, boolean z, boolean z2, boolean[] zArr, int[] iArr, Rect[][] rectArr, int[] iArr2) {
        this.originalName = str;
        this.showName = str;
        this.mainSw = z;
        this.vibrateSw = z2;
        if (zArr != null) {
            this.optionSwArray = zArr;
        }
        if (iArr != null) {
            this.sensitivityArray = iArr;
        }
        if (rectArr != null) {
            this.pointsArray = rectArr;
        }
        if (iArr2 != null) {
            this.optionArray = iArr2;
        }
    }

    public int getIsLandscape() {
        return this.isLandscape;
    }

    public void setCustomizedTgkData(Context context) {
        Rect[][] rectArr;
        int[] iArr;
        int i;
        Rect[][] rectArr2;
        int[] iArr2;
        boolean[] zArr;
        if (HighLightsUtils.CJZC_PACKAGE_NAME.equals(this.packageName) || "com.tencent.tmgp.pubgm".equals(this.packageName)) {
            rectArr = new Rect[][]{new Rect[]{new Rect(2066, 948, 2162, AnalyticsListener.EVENT_AUDIO_TRACK_RELEASED), new Rect(2296, 708, 2392, 792)}, new Rect[]{new Rect(2082, 765, 2166, 849), new Rect(2276, 532, 2360, 616)}, new Rect[]{new Rect(1940, 400, 2024, 484), new Rect(1940, 540, 2024, 624)}};
            iArr = new int[]{2, 1, 7};
            i = R.string.tgk_customized_case_pubgm;
        } else {
            if (HighLightsUtils.WZRY_PACKAGE_NAME.equals(this.packageName)) {
                Rect[][] rectArr3 = {new Rect[]{new Rect(216, 400, 300, 484), new Rect(216, 540, 300, 624)}, new Rect[]{new Rect(1769, 725, 1865, 809), new Rect(1949, 589, 2045, 673)}, new Rect[]{new Rect(1940, 400, 2024, 484), new Rect(1940, 540, 2024, 624)}};
                i = R.string.tgk_customized_case_sgame;
                rectArr2 = rectArr3;
                iArr2 = new int[]{0, 2, 7};
                zArr = new boolean[]{false, true, false};
                setTgkData(context.getResources().getString(i), true, true, zArr, null, rectArr2, iArr2);
            }
            if (!this.packageName.startsWith(HighLightsUtils.YS_PACKAGE_NAME)) {
                return;
            }
            rectArr = new Rect[][]{new Rect[]{new Rect(397, 573, 481, 657), new Rect(216, 540, 300, 624)}, new Rect[]{new Rect(1866, 777, 1950, 861), new Rect(1140, 540, 1224, 624)}, new Rect[]{new Rect(1940, 400, 2024, 484), new Rect(1940, 540, 2024, 624)}};
            iArr = new int[]{5, 6, 7};
            i = R.string.tgk_customized_case_yuanshen;
        }
        rectArr2 = rectArr;
        iArr2 = iArr;
        zArr = null;
        setTgkData(context.getResources().getString(i), true, true, zArr, null, rectArr2, iArr2);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0249  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x03c0  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x06fb  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0898  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0a30  */
    /* JADX WARN: Removed duplicated region for block: B:88:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setCustomizedTgkData(android.content.Context r17, int r18) {
        /*
            Method dump skipped, instructions count: 3365
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.tgk.data.TgkData.setCustomizedTgkData(android.content.Context, int):void");
    }

    public void setIsLandscape(int i) {
        this.isLandscape = i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("TgkData {ID= " + this.ID + "; state=" + this.state + "; originalName=" + this.originalName + "; showName=" + this.showName + "; packageName=" + this.packageName + "; mainSw=" + this.mainSw + "; vibrateSw=" + this.vibrateSw);
        boolean[] zArr = this.optionSwArray;
        if (zArr != null && zArr.length >= 3) {
            sb.append("; leftSw=" + this.optionSwArray[0] + "; rightSw=" + this.optionSwArray[1] + "; middleSw=" + this.optionSwArray[2]);
        }
        int[] iArr = this.optionArray;
        if (iArr != null && iArr.length >= 3) {
            sb.append("; leftOption=" + this.optionArray[0] + "; rightOption=" + this.optionArray[1] + "; middleOption=" + this.optionArray[2]);
        }
        int[] iArr2 = this.sensitivityArray;
        if (iArr2 != null && iArr2.length >= 2) {
            sb.append("; leftSensitivity=" + this.sensitivityArray[0] + "; rightSensitivity=" + this.sensitivityArray[1]);
        }
        int[] iArr3 = this.rapidFireCountArray;
        if (iArr3 != null && iArr3.length >= 3) {
            sb.append("; leftFire=" + this.rapidFireCountArray[0] + "; rightFire=" + this.rapidFireCountArray[1] + "; middleFire=" + this.rapidFireCountArray[2]);
        }
        int[] iArr4 = this.setLinkFlagArray;
        if (iArr4 != null && iArr4.length >= 3) {
            sb.append("; leftLink=" + this.setLinkFlagArray[0] + "; rightLink=" + this.setLinkFlagArray[1] + "; middleLink=" + this.setLinkFlagArray[2]);
        }
        Rect[][] rectArr = this.pointsArray;
        if (rectArr != null && rectArr.length >= 3) {
            sb.append("; leftPoints=(" + this.pointsArray[0][0].top + "," + this.pointsArray[0][0].left + "," + this.pointsArray[0][0].bottom + "," + this.pointsArray[0][0].right + "),(" + this.pointsArray[0][1].top + "," + this.pointsArray[0][1].left + "," + this.pointsArray[0][1].bottom + "," + this.pointsArray[0][1].right + "); rightPoints=(" + this.pointsArray[1][0].top + "," + this.pointsArray[1][0].left + "," + this.pointsArray[1][0].bottom + "," + this.pointsArray[1][0].right + "),(" + this.pointsArray[1][1].top + "," + this.pointsArray[1][1].left + "," + this.pointsArray[1][1].bottom + "," + this.pointsArray[1][1].right + "); middlePoints=(" + this.pointsArray[2][0].top + "," + this.pointsArray[2][0].left + "," + this.pointsArray[2][0].bottom + "," + this.pointsArray[2][0].right + "),(" + this.pointsArray[2][1].top + "," + this.pointsArray[2][1].left + "," + this.pointsArray[2][1].bottom + "," + this.pointsArray[2][1].right + ")");
        }
        sb.append("; picture='" + this.picture + "; shotPicture='" + this.shotPicture + "; uniqueId='" + this.uniqueId + "; change='" + this.change + "; updateTime='" + this.updateTime + "; isLandscape='" + this.isLandscape + '}');
        return sb.toString();
    }

    public void updateDefaultPointsArray(int i) {
        for (int i2 = 0; i2 < TgkHelper.TGK_COUNT; i2++) {
            for (int i3 = 0; i3 < 2; i3++) {
                Rect rect = this.pointsArray[i2][i3];
                if (rect != null) {
                    int i4 = rect.right - rect.left;
                    int i5 = rect.bottom - rect.top;
                    if (i3 == 0) {
                        this.pointsArray[i2][i3] = new Rect((i == 1 ? TgkHelper.DEFAULT_TGK_POINT_1_LANDSCAPE[i2][0] : TgkHelper.DEFAULT_TGK_POINT_1_PORTRAIT[i2][0]) - (i4 / 2), (i == 1 ? TgkHelper.DEFAULT_TGK_POINT_1_LANDSCAPE[i2][1] : TgkHelper.DEFAULT_TGK_POINT_1_PORTRAIT[i2][1]) - (i5 / 2), (i == 1 ? TgkHelper.DEFAULT_TGK_POINT_1_LANDSCAPE[i2][0] : TgkHelper.DEFAULT_TGK_POINT_1_PORTRAIT[i2][0]) + (i4 / 2), (i == 1 ? TgkHelper.DEFAULT_TGK_POINT_1_LANDSCAPE[i2][1] : TgkHelper.DEFAULT_TGK_POINT_1_PORTRAIT[i2][1]) + (i5 / 2));
                    } else {
                        this.pointsArray[i2][i3] = new Rect((i == 1 ? TgkHelper.DEFAULT_TGK_POINT_2_LANDSCAPE[i2][0] : TgkHelper.DEFAULT_TGK_POINT_2_PORTRAIT[i2][0]) - (i4 / 2), (i == 1 ? TgkHelper.DEFAULT_TGK_POINT_2_LANDSCAPE[i2][1] : TgkHelper.DEFAULT_TGK_POINT_2_PORTRAIT[i2][1]) - (i5 / 2), (i == 1 ? TgkHelper.DEFAULT_TGK_POINT_2_LANDSCAPE[i2][0] : TgkHelper.DEFAULT_TGK_POINT_2_PORTRAIT[i2][0]) + (i4 / 2), (i == 1 ? TgkHelper.DEFAULT_TGK_POINT_2_LANDSCAPE[i2][1] : TgkHelper.DEFAULT_TGK_POINT_2_PORTRAIT[i2][1]) + (i5 / 2));
                    }
                }
            }
        }
    }
}
