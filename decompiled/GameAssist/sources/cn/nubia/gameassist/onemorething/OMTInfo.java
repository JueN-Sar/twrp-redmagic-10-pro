package cn.nubia.gameassist.onemorething;

import android.content.Context;
import cn.nubia.gameassist.view.NubiaTextClock;
import com.google.gson.annotations.SerializedName;
import com.zte.gameassist.config.ZteFeature;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Locale;

/* loaded from: classes.dex */
public class OMTInfo implements Serializable {
    public static final int HOT_LEVEL_ONE = 1;
    public static final int HOT_LEVEL_THREE = 3;
    public static final int HOT_LEVEL_TWO = 2;
    public static final int HOT_LEVEL_ZERO = 0;

    @SerializedName("en_note")
    public String enNote;
    public String[] games;
    public int hasVote;

    @SerializedName("hot_level")
    public int hotLevel;
    public int id;

    @SerializedName("minimum_app_version")
    public String minimumAppVersion;
    public String note;
    public String[] scenario;

    @SerializedName("tr_note")
    public String trNote;

    public OMTInfo() {
    }

    public String a(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        return ZteFeature.IS_INTER_VERSION ? this.note : "en".equals(language) ? this.enNote : ("zh".equals(language) && "TW".equals(locale.getCountry())) ? (this.trNote.isEmpty() || this.trNote.length() == 0) ? this.note : this.trNote : this.note;
    }

    public boolean b() {
        return this.hasVote == 1;
    }

    public String toString() {
        return "OMTInfo{id=" + this.id + ", note='" + this.note + NubiaTextClock.QUOTE + ", enNote='" + this.enNote + NubiaTextClock.QUOTE + ", trNote='" + this.trNote + NubiaTextClock.QUOTE + ", games=" + Arrays.toString(this.games) + ", scenario=" + Arrays.toString(this.scenario) + ", minimumAppVersion='" + this.minimumAppVersion + NubiaTextClock.QUOTE + ", hotLevel=" + this.hotLevel + ", hasVote=" + this.hasVote + '}';
    }

    public OMTInfo(String str) {
        this.note = str;
    }

    public OMTInfo(int i2, String str, String str2, String str3, String[] strArr, String[] strArr2, String str4, int i3, int i4) {
        this.id = i2;
        this.note = str;
        this.enNote = str2;
        this.trNote = str3;
        this.games = strArr;
        this.scenario = strArr2;
        this.minimumAppVersion = str4;
        this.hotLevel = i3;
        this.hasVote = i4;
    }
}
