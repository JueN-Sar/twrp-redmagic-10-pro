package kotlinx.coroutines.selects;

import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
public final class WhileSelectKt {
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0058 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0061  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object a(kotlin.jvm.functions.Function1 r4, kotlin.coroutines.Continuation r5) {
        /*
            boolean r0 = r5 instanceof kotlinx.coroutines.selects.WhileSelectKt$whileSelect$1
            if (r0 == 0) goto L13
            r0 = r5
            kotlinx.coroutines.selects.WhileSelectKt$whileSelect$1 r0 = (kotlinx.coroutines.selects.WhileSelectKt$whileSelect$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.selects.WhileSelectKt$whileSelect$1 r0 = new kotlinx.coroutines.selects.WhileSelectKt$whileSelect$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r4 = r0.L$0
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4
            kotlin.ResultKt.b(r5)
            goto L59
        L2d:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L35:
            kotlin.ResultKt.b(r5)
        L38:
            r0.L$0 = r4
            r0.label = r3
            kotlinx.coroutines.selects.SelectBuilderImpl r5 = new kotlinx.coroutines.selects.SelectBuilderImpl
            r5.<init>(r0)
            r4.c(r5)     // Catch: java.lang.Throwable -> L45
            goto L49
        L45:
            r2 = move-exception
            r5.j0(r2)
        L49:
            java.lang.Object r5 = r5.i0()
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            if (r5 != r2) goto L56
            kotlin.coroutines.jvm.internal.DebugProbesKt.c(r0)
        L56:
            if (r5 != r1) goto L59
            return r1
        L59:
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 != 0) goto L38
            kotlin.Unit r4 = kotlin.Unit.f18288a
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.selects.WhileSelectKt.a(kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
