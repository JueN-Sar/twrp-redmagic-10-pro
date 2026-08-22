package cn.nubia.plug;

import java.io.Serializable;

/* loaded from: classes.dex */
public class PlugData implements Serializable {
    public static final int PLUG_DIMENSION_RATING_SIZE = 4;
    private int[] mAdaptedDevices;
    private int mContentId;
    private int[] mDimensionRatings;
    private int[] mDrawables;
    private int mPreId;
    private int[] mSupportGames;
    private String mTag;
    private int mTitleId;
    private int mTrackId;
    private boolean mVideoPre;

    public PlugData(int[] iArr, int i, int i2, int i3, int[] iArr2, int[] iArr3, int[] iArr4, int i4, String str) {
        this(iArr, i, i2, i3, iArr2, iArr3, iArr4, i4, PlugUtil.isPreVideoType(), str);
    }

    public PlugData(int[] iArr, int i, int i2, int i3, int[] iArr2, int[] iArr3, int[] iArr4, int i4, boolean z, String str) {
        this.mDrawables = iArr;
        this.mTitleId = i;
        this.mContentId = i2;
        this.mTrackId = i3;
        this.mSupportGames = iArr2;
        this.mAdaptedDevices = iArr3;
        this.mDimensionRatings = iArr4;
        this.mPreId = i4;
        this.mVideoPre = z;
        this.mTag = str;
    }

    public int[] getAdaptedDevices() {
        return this.mAdaptedDevices;
    }

    public int getContentId() {
        return this.mContentId;
    }

    public int[] getDimensionRatings() {
        return this.mDimensionRatings;
    }

    public int[] getDrawables() {
        return this.mDrawables;
    }

    public String[] getNormalColorString() {
        return PlugUtil.getNormalColors();
    }

    public int getPreId() {
        return this.mPreId;
    }

    public String[] getSelectorColors(int i) {
        return PlugUtil.getSelectorColors(this.mTag, i);
    }

    public int[] getSupportGames() {
        return this.mSupportGames;
    }

    public String getTag() {
        return this.mTag;
    }

    public int getTitleId() {
        return this.mTitleId;
    }

    public int getTrackId() {
        return this.mTrackId;
    }

    public boolean isVideoPre() {
        return this.mVideoPre;
    }
}
