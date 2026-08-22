package cn.nubia.gameassist.power;

/* loaded from: classes.dex */
public final class PowerReason {
    public static String a(int i2) {
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? Integer.toString(i2) : "DOZING" : "DREAMING" : "AWAKE" : "ASLEEP";
    }
}
