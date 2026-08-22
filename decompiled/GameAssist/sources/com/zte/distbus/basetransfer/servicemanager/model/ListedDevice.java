package com.zte.distbus.basetransfer.servicemanager.model;

import androidx.annotation.NonNull;
import cn.nubia.gameassist.view.NubiaTextClock;
import com.google.gson.annotations.SerializedName;
import com.zte.distbus.basetransfer.Constants;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes.dex */
public class ListedDevice implements Serializable {

    @SerializedName("account")
    private final String account;

    @SerializedName(Constants.EXTRA_DEVICE_ID)
    private final String deviceId;

    @SerializedName("deviceType")
    private final int deviceType;

    @SerializedName("discoverType")
    private int discoverType;

    @SerializedName("name")
    private final String name;

    @SerializedName("serviceList")
    public List<ServiceItem> serviceList;

    @SerializedName("status")
    private final int status;

    @SerializedName(Constants.EXTRA_TRUST)
    private final boolean trust;

    public class ServiceItem {
        private String data;
        private boolean enable;
        private String name;
        private String uuid;
        private int version;

        public ServiceItem(String str, String str2, int i2, String str3, boolean z) {
            this.name = str;
            this.uuid = str2;
            this.version = i2;
            this.data = str3;
            this.enable = z;
        }

        public String getData() {
            return this.data;
        }

        public String getName() {
            return this.name;
        }

        public String getUuid() {
            return this.uuid;
        }

        public int getVersion() {
            return this.version;
        }

        public boolean isEnable() {
            return this.enable;
        }

        public void setData(String str) {
            this.data = str;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setUuid(String str) {
            this.uuid = str;
        }

        public void setVersion(int i2) {
            this.version = i2;
        }

        public String toString() {
            return "ServiceItem{name='" + this.name + NubiaTextClock.QUOTE + ", uuid='" + this.uuid + NubiaTextClock.QUOTE + ", version=" + this.version + ", enable=" + this.enable + ", data='" + this.data + NubiaTextClock.QUOTE + '}';
        }
    }

    @Deprecated
    public ListedDevice(@NonNull String str, @NonNull String str2, int i2, @NonNull String str3, int i3, boolean z) {
        this.name = str;
        this.deviceId = str2;
        this.status = i2;
        this.account = str3;
        this.deviceType = i3;
        this.trust = z;
        this.discoverType = DiscoverType.DISCOVER_TYPE_BLE;
    }

    public String getAccount() {
        return this.account;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public int getDeviceType() {
        return this.deviceType;
    }

    public int getDiscoverType() {
        return this.discoverType;
    }

    public String getName() {
        return this.name;
    }

    public List<ServiceItem> getServiceList() {
        return this.serviceList;
    }

    public int getStatus() {
        return this.status;
    }

    public boolean isConnected() {
        return this.status == 102;
    }

    public boolean isTrust() {
        return this.trust;
    }

    public void setDiscoverType(int i2) {
        this.discoverType = i2;
    }

    public void setServiceList(List<ServiceItem> list) {
        this.serviceList = list;
    }

    public String toString() {
        return "ListedDevice{name='" + this.name + NubiaTextClock.QUOTE + ", deviceId='" + this.deviceId + NubiaTextClock.QUOTE + ", status=" + this.status + ", trust=" + this.trust + ", account='" + this.account + NubiaTextClock.QUOTE + ", deviceType=" + this.deviceType + ", serviceList=" + this.serviceList.toString() + '}';
    }

    public ListedDevice(@NonNull String str, @NonNull String str2, int i2, @NonNull String str3, int i3, boolean z, int i4) {
        this.name = str;
        this.deviceId = str2;
        this.status = i2;
        this.account = str3;
        this.deviceType = i3;
        this.trust = z;
        this.discoverType = i4;
    }
}
