package cn.nubia.gamelauncher.gamecontrolpanel.superresolution;

import java.util.HashMap;

/* loaded from: classes.dex */
public class ItemData {
    private String mDescription;
    private HashMap mHashMap;
    private boolean mSelected;
    private String mSuperGear;

    public String getDescription() {
        return this.mDescription;
    }

    public HashMap getHashMap() {
        return this.mHashMap;
    }

    public String getSuperGear() {
        return this.mSuperGear;
    }

    public boolean isSelected() {
        return this.mSelected;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public void setHashMap(HashMap hashMap) {
        this.mHashMap = hashMap;
    }

    public void setSelected(boolean z) {
        this.mSelected = z;
    }

    public void setSuperGear(String str) {
        this.mSuperGear = str;
    }
}
