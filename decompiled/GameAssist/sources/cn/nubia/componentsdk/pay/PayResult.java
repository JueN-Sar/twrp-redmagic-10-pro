package cn.nubia.componentsdk.pay;

import android.text.TextUtils;
import com.zte.distbus.basetransfer.Constants;

/* loaded from: classes.dex */
public class PayResult {

    /* renamed from: a, reason: collision with root package name */
    private String f6018a;

    /* renamed from: b, reason: collision with root package name */
    private String f6019b;

    /* renamed from: c, reason: collision with root package name */
    private String f6020c;

    public PayResult(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        for (String str2 : str.split(";")) {
            if (str2.startsWith("resultStatus")) {
                this.f6018a = a(str2, "resultStatus");
            }
            if (str2.startsWith(Constants.EXTRA_RESULT)) {
                this.f6019b = a(str2, Constants.EXTRA_RESULT);
            }
            if (str2.startsWith("memo")) {
                this.f6020c = a(str2, "memo");
            }
        }
    }

    private String a(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str.indexOf(str3) + str3.length(), str.lastIndexOf("}"));
    }

    public String b() {
        return this.f6018a;
    }

    public String toString() {
        return "resultStatus={" + this.f6018a + "};memo={" + this.f6020c + "};result={" + this.f6019b + "}";
    }
}
