package com.google.firebase.encoders;

import java.io.Writer;

/* loaded from: classes.dex */
public interface DataEncoder {
    void a(Object obj, Writer writer);

    String b(Object obj);
}
