package com.zte.distbus.basetransfer;

import android.content.Intent;
import com.zte.distbus.basetransfer.model.ConnectionParam;

/* loaded from: classes.dex */
public interface BusCallback {
    void acceptConnection(String str);

    void establishConnection(ConnectionParam connectionParam);

    void onConnectionChange(Intent intent);

    void onConnectionInitiated(String str);

    void processNotification(ConnectionParam connectionParam);

    void rejectConnection();

    void sendNotification(ConnectionParam connectionParam);
}
