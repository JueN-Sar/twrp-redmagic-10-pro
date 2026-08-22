package com.google.android.datatransport.cct.internal;

import com.google.auto.value.AutoValue;
import com.google.firebase.encoders.DataEncoder;
import com.google.firebase.encoders.annotations.Encodable;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import java.util.List;

@AutoValue
@Encodable
/* loaded from: classes.dex */
public abstract class BatchedLogRequest {
    public static BatchedLogRequest a(List list) {
        return new AutoValue_BatchedLogRequest(list);
    }

    public static DataEncoder b() {
        return new JsonDataEncoderBuilder().g(AutoBatchedLogRequestEncoder.f10079a).h(true).f();
    }

    public abstract List c();
}
