package cn.nubia.multisubscreen.secondary;

import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gameassist.R;
import cn.nubia.multisubscreen.NotificationMsgAdapter;
import cn.nubia.multisubscreen.utils.MultiSubScreenNotiMsgUtils;
import com.zte.gameassist.utils.GaLog;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class DisplayThreeHolder {

    /* renamed from: a, reason: collision with root package name */
    private RecyclerView f8031a;

    /* renamed from: b, reason: collision with root package name */
    private NotificationMsgAdapter f8032b;

    /* renamed from: c, reason: collision with root package name */
    private TextView f8033c;

    public DisplayThreeHolder(ViewGroup viewGroup) {
        this.f8031a = (RecyclerView) viewGroup.findViewById(R.id.multi_sub_screen_noti_msg_view);
        this.f8031a.setLayoutManager(new LinearLayoutManager(viewGroup.getContext()));
        this.f8033c = (TextView) viewGroup.findViewById(R.id.multi_sub_screen_noti_msg_empty);
    }

    public void a(ArrayList arrayList) {
        GaLog.e("MultiSubScreen_DisplayThreeHolder", "DisplayThreeHolder removeNotiMsgView list = " + arrayList);
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        ArrayList h2 = MultiSubScreenNotiMsgUtils.h(arrayList);
        if (h2 == null || h2.isEmpty()) {
            this.f8031a.setVisibility(8);
            NotificationMsgAdapter notificationMsgAdapter = this.f8032b;
            if (notificationMsgAdapter != null) {
                notificationMsgAdapter.N(null);
            }
            this.f8033c.setVisibility(0);
            return;
        }
        this.f8031a.setVisibility(0);
        this.f8033c.setVisibility(8);
        NotificationMsgAdapter notificationMsgAdapter2 = this.f8032b;
        if (notificationMsgAdapter2 != null) {
            notificationMsgAdapter2.N(h2);
            return;
        }
        NotificationMsgAdapter notificationMsgAdapter3 = new NotificationMsgAdapter(h2);
        this.f8032b = notificationMsgAdapter3;
        this.f8031a.setAdapter(notificationMsgAdapter3);
    }

    public void b(ArrayList arrayList) {
        GaLog.e("MultiSubScreen_DisplayThreeHolder", "DisplayThreeHolder updateNotiMsgView list = " + arrayList);
        if (arrayList != null && !arrayList.isEmpty()) {
            this.f8033c.setVisibility(8);
            this.f8031a.setVisibility(0);
        }
        NotificationMsgAdapter notificationMsgAdapter = this.f8032b;
        if (notificationMsgAdapter != null) {
            notificationMsgAdapter.N(MultiSubScreenNotiMsgUtils.i(arrayList));
            this.f8031a.l1(0);
        } else {
            NotificationMsgAdapter notificationMsgAdapter2 = new NotificationMsgAdapter(MultiSubScreenNotiMsgUtils.i(arrayList));
            this.f8032b = notificationMsgAdapter2;
            this.f8031a.setAdapter(notificationMsgAdapter2);
        }
    }

    public boolean c() {
        ArrayList arrayList = MultiSubScreenNotiMsgUtils.f8168e;
        if (arrayList == null || arrayList.isEmpty()) {
            this.f8033c.setVisibility(0);
            this.f8031a.setVisibility(8);
            return false;
        }
        this.f8033c.setVisibility(8);
        this.f8031a.setVisibility(0);
        NotificationMsgAdapter notificationMsgAdapter = this.f8032b;
        if (notificationMsgAdapter != null) {
            notificationMsgAdapter.N(MultiSubScreenNotiMsgUtils.f8168e);
            this.f8031a.l1(0);
            return true;
        }
        NotificationMsgAdapter notificationMsgAdapter2 = new NotificationMsgAdapter(MultiSubScreenNotiMsgUtils.f8168e);
        this.f8032b = notificationMsgAdapter2;
        this.f8031a.setAdapter(notificationMsgAdapter2);
        return true;
    }
}
