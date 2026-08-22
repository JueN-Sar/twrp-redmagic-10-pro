package kotlinx.coroutines.debug;

import android.annotation.SuppressLint;
import java.lang.instrument.ClassFileTransformer;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlinx.coroutines.debug.internal.DebugProbesImpl;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

@Metadata
@SuppressLint({"all"})
@IgnoreJRERequirement
/* loaded from: classes2.dex */
public final class AgentPremain {

    /* renamed from: a, reason: collision with root package name */
    public static final AgentPremain f19031a = new AgentPremain();

    /* renamed from: b, reason: collision with root package name */
    private static final boolean f19032b;

    @Metadata
    public static final class DebugProbesTransformer implements ClassFileTransformer {

        /* renamed from: a, reason: collision with root package name */
        public static final DebugProbesTransformer f19033a = new DebugProbesTransformer();

        private DebugProbesTransformer() {
        }
    }

    static {
        Object b2;
        try {
            Result.Companion companion = Result.Companion;
            String property = System.getProperty("kotlinx.coroutines.debug.enable.creation.stack.trace");
            b2 = Result.b(property != null ? Boolean.valueOf(Boolean.parseBoolean(property)) : null);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            b2 = Result.b(ResultKt.a(th));
        }
        Boolean bool = (Boolean) (Result.f(b2) ? null : b2);
        f19032b = bool != null ? bool.booleanValue() : DebugProbesImpl.f19070a.e();
    }

    private AgentPremain() {
    }
}
