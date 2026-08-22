package cn.nubia.gameassist.common;

import android.text.TextUtils;
import com.zte.gameassist.common.EventListener;
import com.zte.gameassist.common.GameCheck;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public abstract class BaseController {

    /* renamed from: a, reason: collision with root package name */
    private String f6112a;

    /* renamed from: cn.nubia.gameassist.common.BaseController$1, reason: invalid class name */
    class AnonymousClass1 implements EventListener {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ BaseController f6113c;

        @Override // com.zte.gameassist.common.EventListener
        public void a(int i2, Object... objArr) {
            this.f6113c.d((String) objArr[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        if (TextUtils.equals(this.f6112a, str)) {
            GaLog.e("BaseController", "onActivityChange: no change");
            return;
        }
        boolean h2 = GameCheck.h(str);
        if (h2) {
            b();
        } else {
            c();
        }
        GaLog.e("BaseController", "onActivityChange: isGame:" + h2);
        this.f6112a = str;
    }

    public abstract void b();

    public abstract void c();
}
