package cn.nubia.screensaver.common;

import android.os.Handler;
import android.text.TextUtils;
import android.util.ArrayMap;
import com.zte.gameassist.utils.GaLog;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class ActionEvent {

    /* renamed from: a, reason: collision with root package name */
    private final Map f9012a = new ArrayMap();

    /* renamed from: b, reason: collision with root package name */
    private final Handler f9013b;

    /* JADX INFO: Access modifiers changed from: private */
    class Action {

        /* renamed from: a, reason: collision with root package name */
        private final String f9014a;

        /* renamed from: b, reason: collision with root package name */
        private final int f9015b;

        /* renamed from: c, reason: collision with root package name */
        private Runnable f9016c;

        /* renamed from: d, reason: collision with root package name */
        private Handler f9017d;

        public Action(ActionEvent actionEvent, int i2, Runnable runnable, Handler handler, String str) {
            this.f9015b = i2;
            this.f9014a = str;
            this.f9016c = runnable;
            this.f9017d = handler;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d() {
            if (this.f9017d.getLooper().isCurrentThread()) {
                this.f9016c.run();
            } else {
                this.f9017d.post(this.f9016c);
            }
        }

        public boolean equals(Object obj) {
            return obj instanceof Action ? this.f9016c == ((Action) obj).f9016c : super.equals(obj);
        }

        public String toString() {
            return this.f9014a + ":" + this.f9015b;
        }
    }

    public ActionEvent(Handler handler) {
        this.f9013b = handler;
    }

    private void e(final Action action) {
        if (!h(action.f9015b)) {
            throw new IllegalAccessError("key is err:" + action.f9015b);
        }
        GaLog.a("Screensaver.AEvent", "addAction keys=[" + i(action.f9015b) + "] " + action.f9014a);
        f(new Runnable() { // from class: cn.nubia.screensaver.common.b
            @Override // java.lang.Runnable
            public final void run() {
                ActionEvent.this.j(action);
            }
        });
    }

    private void f(Runnable runnable) {
        if (this.f9013b.getLooper().isCurrentThread()) {
            runnable.run();
        } else {
            this.f9013b.post(runnable);
        }
    }

    public static boolean h(int i2) {
        return (i2 & 2047) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j(Action action) {
        if (!h(action.f9015b)) {
            throw new AssertionError("key is not valid");
        }
        for (int i2 = 1024; i2 > 0; i2 >>= 1) {
            if ((action.f9015b & i2) != 0) {
                List list = (List) this.f9012a.get(Integer.valueOf(i2));
                if (list == null) {
                    list = new ArrayList();
                    this.f9012a.put(Integer.valueOf(i2), list);
                }
                if (list.contains(action)) {
                    GaLog.a("Screensaver.AEvent", "addAction already , keys=[" + i(i2) + "] 0x" + Integer.toHexString(i2));
                } else {
                    list.add(action);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k(int i2) {
        List<Action> list;
        if (!h(i2)) {
            throw new AssertionError("key is not valid");
        }
        for (int i3 = 1024; i3 > 0; i3 >>= 1) {
            if ((i3 & i2) != 0 && (list = (List) this.f9012a.remove(Integer.valueOf(i3))) != null && list.size() > 0) {
                for (Action action : list) {
                    GaLog.e("Screensaver.AEvent", "invoken action, key=" + i(i3) + ", name=" + action.f9014a);
                    action.d();
                }
                list.clear();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l(String str) {
        int i2 = 0;
        for (int i3 = 1024; i3 > 0; i3 >>= 1) {
            List list = (List) this.f9012a.get(Integer.valueOf(i3));
            if (list != null && list.size() > 0) {
                int size = list.size();
                int i4 = 0;
                while (i4 < size) {
                    if (TextUtils.equals(((Action) list.get(i4)).f9014a, str)) {
                        list.remove(i4);
                        i4--;
                        size--;
                        i2++;
                    }
                    i4++;
                }
            }
        }
        GaLog.a("Screensaver.AEvent", "removeAction no has name=" + str + " count=" + i2);
    }

    public void d(int i2, String str, Runnable runnable) {
        e(new Action(this, i2, runnable, this.f9013b, str));
    }

    public void g(final int i2) {
        f(new Runnable() { // from class: cn.nubia.screensaver.common.a
            @Override // java.lang.Runnable
            public final void run() {
                ActionEvent.this.k(i2);
            }
        });
    }

    public String i(int i2) {
        StringBuilder sb = new StringBuilder();
        if ((i2 & 2047) != 0) {
            if ((i2 & 2) != 0) {
                sb.append("WAKE_UP,");
            }
            if ((i2 & 4) != 0) {
                sb.append("GO_TO_SLEEP,");
            }
            if ((i2 & 8) != 0) {
                sb.append("POWER_MODE_OFF,");
            }
            if ((i2 & 16) != 0) {
                sb.append("POWER_MODE_ON,");
            }
            if ((i2 & 32) != 0) {
                sb.append("POWER_MODE_DOZE,");
            }
            if ((i2 & 512) != 0) {
                sb.append("SHOW_SCREENSAVER,");
            }
            if ((i2 & 1024) != 0) {
                sb.append("HIDE_SCREENSAVER,");
            }
            if ((i2 & 1) != 0) {
                sb.append("KEYGUARD_EXIT");
            }
        }
        String sb2 = sb.toString();
        if (sb2.length() > 0) {
            return sb2;
        }
        return "0X" + Integer.toHexString(i2);
    }

    public void m(final String str) {
        f(new Runnable() { // from class: cn.nubia.screensaver.common.c
            @Override // java.lang.Runnable
            public final void run() {
                ActionEvent.this.l(str);
            }
        });
    }
}
