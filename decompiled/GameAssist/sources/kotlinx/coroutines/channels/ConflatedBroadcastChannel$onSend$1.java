package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectInstance;

@Metadata
/* loaded from: classes2.dex */
public final class ConflatedBroadcastChannel$onSend$1 implements SelectClause2<Object, SendChannel<Object>> {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ ConflatedBroadcastChannel f19021c;

    @Override // kotlinx.coroutines.selects.SelectClause2
    public void v(SelectInstance selectInstance, Object obj, Function2 function2) {
        this.f19021c.h(selectInstance, obj, function2);
    }
}
