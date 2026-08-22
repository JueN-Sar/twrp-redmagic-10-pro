package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectInstance;

@Metadata
/* loaded from: classes2.dex */
public final class ReceiveChannel$onReceiveOrNull$1 implements SelectClause1<Object> {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ ReceiveChannel f19026c;

    @Override // kotlinx.coroutines.selects.SelectClause1
    public void b(SelectInstance selectInstance, Function2 function2) {
        this.f19026c.x().b(selectInstance, new ReceiveChannel$onReceiveOrNull$1$registerSelectClause1$1(function2, null));
    }
}
