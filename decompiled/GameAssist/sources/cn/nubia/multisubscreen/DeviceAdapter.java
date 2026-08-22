package cn.nubia.multisubscreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gameassist.R;
import cn.nubia.multisubscreen.mgr.ConnectCodeMgr;
import cn.nubia.multisubscreen.mgr.DistributeBusMgr;
import cn.nubia.multisubscreen.utils.MultiSubScreenUtils;
import com.zte.distbus.basetransfer.servicemanager.model.ListedDevice;
import com.zte.gameassist.utils.GaLog;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class DeviceAdapter extends RecyclerView.Adapter<ViewHolder> {

    /* renamed from: c, reason: collision with root package name */
    private List f7889c = new ArrayList();

    /* renamed from: d, reason: collision with root package name */
    public ListedDevice f7890d;

    public static class DeviceItem {

        /* renamed from: a, reason: collision with root package name */
        public CastStatus f7891a;

        /* renamed from: b, reason: collision with root package name */
        public ListedDevice f7892b;

        public DeviceItem(CastStatus castStatus, ListedDevice listedDevice) {
            this.f7891a = castStatus;
            this.f7892b = listedDevice;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: s, reason: collision with root package name */
        TextView f7893s;
        View t;
        View u;
        View v;
        TextView w;

        public ViewHolder(View view) {
            super(view);
            this.f7893s = (TextView) view.findViewById(R.id.device_name);
            this.t = view.findViewById(R.id.devices_container);
            this.u = view.findViewById(R.id.connect_container);
            this.v = view.findViewById(R.id.pgb_connecting);
            this.w = (TextView) view.findViewById(R.id.text_connecting);
        }
    }

    public DeviceAdapter(List list) {
        S(list);
    }

    private void M(ListedDevice listedDevice) {
        this.f7890d = listedDevice;
        DistributeBusMgr.getInstance().connectDevice(listedDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void N(ListedDevice listedDevice, View view) {
        Q(view.getContext(), listedDevice);
    }

    private void Q(Context context, ListedDevice listedDevice) {
        GaLog.a("MultiSubScreen_DeviceAdapter", "onDeviceClicked device = " + listedDevice);
        synchronized (MultiSubScreenUtils.class) {
            try {
                if (MultiSubScreenUtils.f8174d != 1) {
                    M(listedDevice);
                } else if (this.f7890d != null && !listedDevice.getDeviceId().equalsIgnoreCase(this.f7890d.getDeviceId())) {
                    R(listedDevice);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void R(ListedDevice listedDevice) {
        if (MultiSubScreenUtils.v()) {
            DistributeBusMgr.getInstance().disConnectDevice(MultiSubScreenUtils.k());
        } else {
            ConnectCodeMgr.h().x("SINK_REQUIRED_DISCONNECT_CODE");
        }
        M(listedDevice);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: O, reason: merged with bridge method [inline-methods] */
    public void A(ViewHolder viewHolder, int i2) {
        CastStatus castStatus = ((DeviceItem) this.f7889c.get(i2)).f7891a;
        final ListedDevice listedDevice = ((DeviceItem) this.f7889c.get(i2)).f7892b;
        viewHolder.f7893s.setText(listedDevice.getName());
        viewHolder.t.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.multisubscreen.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceAdapter.this.N(listedDevice, view);
            }
        });
        if (CastStatus.STATUS_BLE_CONNECTING == castStatus) {
            viewHolder.u.setVisibility(0);
            viewHolder.v.setVisibility(0);
            viewHolder.w.setText(R.string.multi_subscreen_connecting_device);
        } else {
            if (CastStatus.STATUS_DEFAULT == castStatus) {
                viewHolder.u.setVisibility(8);
                return;
            }
            if (CastStatus.STATUS_BLE_CONNECT_FAIL == castStatus) {
                viewHolder.u.setVisibility(0);
                viewHolder.v.setVisibility(8);
                viewHolder.w.setText(R.string.multi_subscreen_connect_failure);
            } else if (CastStatus.STATUS_BLE_CONNECTED == castStatus) {
                viewHolder.u.setVisibility(0);
                viewHolder.v.setVisibility(8);
                viewHolder.w.setText(R.string.multi_subscreen_connect_device);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: P, reason: merged with bridge method [inline-methods] */
    public ViewHolder C(ViewGroup viewGroup, int i2) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.multi_sub_screen_device_item, viewGroup, false));
    }

    public void S(List list) {
        this.f7889c.clear();
        if (list != null && !list.isEmpty()) {
            this.f7889c.addAll(list);
        }
        r();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int m() {
        return this.f7889c.size();
    }
}
