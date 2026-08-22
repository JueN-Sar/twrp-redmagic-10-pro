package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import com.google.mlkit.common.MlKitException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

@Metadata
@DebugMetadata(c = "androidx.lifecycle.PausingDispatcherKt$whenStateAtLeast$2", f = "PausingDispatcher.kt", l = {MlKitException.CODE_SCANNER_APP_NAME_UNAVAILABLE}, m = "invokeSuspend")
/* loaded from: classes.dex */
final class PausingDispatcherKt$whenStateAtLeast$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<Object>, Object> {
    final /* synthetic */ Function2<CoroutineScope, Continuation<Object>, Object> $block;
    final /* synthetic */ Lifecycle.State $minState;
    final /* synthetic */ Lifecycle $this_whenStateAtLeast;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    PausingDispatcherKt$whenStateAtLeast$2(Lifecycle lifecycle, Lifecycle.State state, Function2<? super CoroutineScope, ? super Continuation<Object>, ? extends Object> function2, Continuation<? super PausingDispatcherKt$whenStateAtLeast$2> continuation) {
        super(2, continuation);
        this.$this_whenStateAtLeast = lifecycle;
        this.$minState = state;
        this.$block = function2;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: A, reason: merged with bridge method [inline-methods] */
    public final Object y(CoroutineScope coroutineScope, Continuation continuation) {
        return ((PausingDispatcherKt$whenStateAtLeast$2) o(coroutineScope, continuation)).v(Unit.f18288a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation o(Object obj, Continuation continuation) {
        PausingDispatcherKt$whenStateAtLeast$2 pausingDispatcherKt$whenStateAtLeast$2 = new PausingDispatcherKt$whenStateAtLeast$2(this.$this_whenStateAtLeast, this.$minState, this.$block, continuation);
        pausingDispatcherKt$whenStateAtLeast$2.L$0 = obj;
        return pausingDispatcherKt$whenStateAtLeast$2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object v(Object obj) {
        Object d2;
        LifecycleController lifecycleController;
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.b(obj);
            Job job = (Job) ((CoroutineScope) this.L$0).K().c(Job.f18898f);
            if (job == null) {
                throw new IllegalStateException("when[State] methods should have a parent job".toString());
            }
            PausingDispatcher pausingDispatcher = new PausingDispatcher();
            LifecycleController lifecycleController2 = new LifecycleController(this.$this_whenStateAtLeast, this.$minState, pausingDispatcher.f4339i, job);
            try {
                Function2<CoroutineScope, Continuation<Object>, Object> function2 = this.$block;
                this.L$0 = lifecycleController2;
                this.label = 1;
                obj = BuildersKt.c(pausingDispatcher, function2, this);
                if (obj == d2) {
                    return d2;
                }
                lifecycleController = lifecycleController2;
            } catch (Throwable th) {
                th = th;
                lifecycleController = lifecycleController2;
                lifecycleController.b();
                throw th;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            lifecycleController = (LifecycleController) this.L$0;
            try {
                ResultKt.b(obj);
            } catch (Throwable th2) {
                th = th2;
                lifecycleController.b();
                throw th;
            }
        }
        lifecycleController.b();
        return obj;
    }
}
