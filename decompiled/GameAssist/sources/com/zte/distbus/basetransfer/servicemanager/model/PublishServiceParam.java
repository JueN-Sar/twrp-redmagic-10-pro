package com.zte.distbus.basetransfer.servicemanager.model;

import com.zte.distbus.basetransfer.BaseServiceCallback;

/* loaded from: classes.dex */
public class PublishServiceParam extends ServiceParam {
    private String callBackClsName;
    private boolean enable;
    private String packageName;
    private String serviceName;
    private int version;

    public PublishServiceParam(String str, String str2, Class<? extends BaseServiceCallback> cls, String str3, int i2) {
        super(str2, str3);
        this.enable = true;
        this.serviceName = str;
        this.callBackClsName = cls == null ? null : cls.getCanonicalName();
        this.version = i2;
    }

    public String getCallBackClsName() {
        return this.callBackClsName;
    }

    public String getPackageName() {
        return this.packageName;
    }

    @Override // com.zte.distbus.basetransfer.servicemanager.model.ServiceParam
    public String getProfile() {
        return super.getProfile();
    }

    public String getServiceName() {
        return this.serviceName;
    }

    @Override // com.zte.distbus.basetransfer.servicemanager.model.ServiceParam
    public String getUuid() {
        return super.getUuid();
    }

    public int getVersion() {
        return this.version;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public void setEnable(boolean z) {
        this.enable = z;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }
}
