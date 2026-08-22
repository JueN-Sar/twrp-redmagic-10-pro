package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.ScopeCoroutine;

@Metadata
/* loaded from: classes2.dex */
public final class SafeCollector_commonKt {
    public static final void a(final SafeCollector safeCollector, CoroutineContext coroutineContext) {
        if (((Number) coroutineContext.e0(0, new Function2<Integer, CoroutineContext.Element, Integer>() { // from class: kotlinx.coroutines.flow.internal.SafeCollector_commonKt$checkContext$result$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            public final Integer d(int i2, CoroutineContext.Element element) {
                CoroutineContext.Key key = element.getKey();
                CoroutineContext.Element c2 = safeCollector.collectContext.c(key);
                if (key != Job.f18898f) {
                    return Integer.valueOf(element != c2 ? Integer.MIN_VALUE : i2 + 1);
                }
                Job job = (Job) c2;
                Job b2 = SafeCollector_commonKt.b((Job) element, job);
                if (b2 == job) {
                    if (job != null) {
                        i2++;
                    }
                    return Integer.valueOf(i2);
                }
                throw new IllegalStateException(("Flow invariant is violated:\n\t\tEmission from another coroutine is detected.\n\t\tChild of " + b2 + ", expected child of " + job + ".\n\t\tFlowCollector is not thread-safe and concurrent emissions are prohibited.\n\t\tTo mitigate this restriction please use 'channelFlow' builder instead of 'flow'").toString());
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object y(Object obj, Object obj2) {
                return d(((Number) obj).intValue(), (CoroutineContext.Element) obj2);
            }
        })).intValue() == safeCollector.collectContextSize) {
            return;
        }
        throw new IllegalStateException(("Flow invariant is violated:\n\t\tFlow was collected in " + safeCollector.collectContext + ",\n\t\tbut emission happened in " + coroutineContext + ".\n\t\tPlease refer to 'flow' documentation or use 'flowOn' instead").toString());
    }

    public static final Job b(Job job, Job job2) {
        while (job != null) {
            if (job == job2 || !(job instanceof ScopeCoroutine)) {
                return job;
            }
            job = ((ScopeCoroutine) job).g1();
        }
        return null;
    }
}
