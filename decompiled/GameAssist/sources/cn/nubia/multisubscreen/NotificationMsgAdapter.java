package cn.nubia.multisubscreen;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gameassist.R;
import cn.nubia.multisubscreen.secondary.NotificationMsgData;
import cn.nubia.multisubscreen.utils.MultiSubScreenNotiMsgUtils;
import com.zte.gameassist.BaseApplication;
import com.zte.gameassist.utils.GaLog;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class NotificationMsgAdapter extends RecyclerView.Adapter<ViewHolder> {

    /* renamed from: c, reason: collision with root package name */
    private ArrayList f7894c = new ArrayList();

    static class ViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: s, reason: collision with root package name */
        View f7895s;
        ImageView t;
        TextView u;
        View v;
        TextView w;
        TextView x;
        TextView y;
        ImageView z;

        public ViewHolder(View view) {
            super(view);
            this.f7895s = view.findViewById(R.id.noti_msg_pkg_info_container);
            this.t = (ImageView) view.findViewById(R.id.noti_msg_pkg_icon);
            this.u = (TextView) view.findViewById(R.id.noti_msg_pkg_name);
            this.v = view.findViewById(R.id.noti_msg_content_container);
            this.w = (TextView) view.findViewById(R.id.noti_msg_title);
            this.x = (TextView) view.findViewById(R.id.noti_msg_content);
            this.y = (TextView) view.findViewById(R.id.noti_msg_time);
            this.z = (ImageView) view.findViewById(R.id.noti_msg_divider);
        }

        public String N(long j2) {
            long currentTimeMillis = System.currentTimeMillis() - j2;
            if (currentTimeMillis < 60000) {
                return BaseApplication.a().getString(R.string.multi_subscreen_noti_msg_time_now);
            }
            int i2 = ((int) currentTimeMillis) / 3600000;
            if (i2 == 0) {
                return (currentTimeMillis / 60000) + BaseApplication.a().getString(R.string.minute);
            }
            return i2 + BaseApplication.a().getString(R.string.hour) + ((currentTimeMillis - (i2 * 3600000)) / 60000) + BaseApplication.a().getString(R.string.minute);
        }

        public void O(String str, String str2, long j2) {
            this.f7895s.setVisibility(8);
            this.v.setVisibility(0);
            this.w.setText(str);
            this.x.setText(str2);
            this.y.setText(N(j2));
            this.z.setVisibility(0);
        }

        public void P(String str) {
            this.f7895s.setVisibility(0);
            Bitmap bitmap = (Bitmap) MultiSubScreenNotiMsgUtils.f8165b.get(str);
            if (bitmap == null) {
                this.t.setImageDrawable(MultiSubScreenNotiMsgUtils.d(str));
            } else {
                this.t.setImageBitmap(bitmap);
            }
            String str2 = (String) MultiSubScreenNotiMsgUtils.f8166c.get(str);
            if (bitmap == null) {
                this.u.setText(MultiSubScreenNotiMsgUtils.e(str));
            } else {
                this.u.setText(str2);
            }
            this.v.setVisibility(8);
            this.z.setVisibility(8);
        }
    }

    public NotificationMsgAdapter(ArrayList arrayList) {
        N(arrayList);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: L, reason: merged with bridge method [inline-methods] */
    public void A(ViewHolder viewHolder, int i2) {
        GaLog.e("MultiSubScreen_NotificationMsgAdapter", "NotificationMsgAdapter onBindViewHolder mList = " + this.f7894c);
        NotificationMsgData notificationMsgData = (NotificationMsgData) this.f7894c.get(i2);
        GaLog.e("MultiSubScreen_NotificationMsgAdapter", "NotificationMsgAdapter onBindViewHolder data = " + notificationMsgData);
        int i3 = notificationMsgData.f8042e;
        if (i3 == 0) {
            viewHolder.P(notificationMsgData.f8038a);
        } else if (i3 == 1) {
            viewHolder.O(notificationMsgData.f8039b, notificationMsgData.f8040c, notificationMsgData.f8041d);
        }
        if (i2 == this.f7894c.size() - 1) {
            viewHolder.z.setVisibility(8);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: M, reason: merged with bridge method [inline-methods] */
    public ViewHolder C(ViewGroup viewGroup, int i2) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.multi_sub_screen_noti_msg_item, viewGroup, false));
    }

    public void N(ArrayList arrayList) {
        GaLog.e("MultiSubScreen_NotificationMsgAdapter", "NotificationMsgAdapter updateData list = " + arrayList);
        this.f7894c.clear();
        if (arrayList != null && !arrayList.isEmpty()) {
            this.f7894c.addAll(arrayList);
        }
        r();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int m() {
        return this.f7894c.size();
    }
}
