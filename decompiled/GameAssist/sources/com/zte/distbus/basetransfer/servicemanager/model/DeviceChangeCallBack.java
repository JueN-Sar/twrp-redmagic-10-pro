package com.zte.distbus.basetransfer.servicemanager.model;

import java.util.ArrayList;

/* loaded from: classes.dex */
public interface DeviceChangeCallBack {
    void onItemChange(ListedDevice listedDevice);

    void onListChange(ArrayList<ListedDevice> arrayList);
}
