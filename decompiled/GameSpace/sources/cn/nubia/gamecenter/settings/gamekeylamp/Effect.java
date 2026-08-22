package cn.nubia.gamecenter.settings.gamekeylamp;

import android.util.Log;

/* loaded from: classes.dex */
public class Effect {
    int effectId = getEffectId();
    String name;
    String paletteId;
    String typeId;

    public Effect(String str, String str2, String str3) {
        this.typeId = str;
        this.paletteId = str2;
        this.name = str3;
    }

    public int getEffectId() {
        try {
            return Integer.parseInt(this.typeId.trim(), 16);
        } catch (Exception e) {
            Log.w(KeyLampHelper.TAG, "getEffectId() e : " + e.getMessage());
            return 0;
        }
    }

    public String toString() {
        return "Effect{typeId =" + this.typeId + ", paletteId =" + this.paletteId + ", name =" + this.name + ", effectId =" + this.effectId + '}';
    }
}
