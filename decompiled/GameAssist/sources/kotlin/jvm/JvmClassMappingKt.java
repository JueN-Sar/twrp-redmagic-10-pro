package kotlin.jvm;

import kotlin.Metadata;
import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

@Metadata
@JvmName
/* loaded from: classes2.dex */
public final class JvmClassMappingKt {
    public static final Class a(KClass kClass) {
        Intrinsics.e(kClass, "<this>");
        Class c2 = ((ClassBasedDeclarationContainer) kClass).c();
        Intrinsics.c(c2, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-java>>");
        return c2;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    public static final Class b(KClass kClass) {
        Intrinsics.e(kClass, "<this>");
        Class c2 = ((ClassBasedDeclarationContainer) kClass).c();
        if (!c2.isPrimitive()) {
            Intrinsics.c(c2, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-javaObjectType>>");
            return c2;
        }
        String name = c2.getName();
        switch (name.hashCode()) {
            case -1325958191:
                if (name.equals("double")) {
                    c2 = Double.class;
                    break;
                }
                break;
            case 104431:
                if (name.equals("int")) {
                    c2 = Integer.class;
                    break;
                }
                break;
            case 3039496:
                if (name.equals("byte")) {
                    c2 = Byte.class;
                    break;
                }
                break;
            case 3052374:
                if (name.equals("char")) {
                    c2 = Character.class;
                    break;
                }
                break;
            case 3327612:
                if (name.equals("long")) {
                    c2 = Long.class;
                    break;
                }
                break;
            case 3625364:
                if (name.equals("void")) {
                    c2 = Void.class;
                    break;
                }
                break;
            case 64711720:
                if (name.equals("boolean")) {
                    c2 = Boolean.class;
                    break;
                }
                break;
            case 97526364:
                if (name.equals("float")) {
                    c2 = Float.class;
                    break;
                }
                break;
            case 109413500:
                if (name.equals("short")) {
                    c2 = Short.class;
                    break;
                }
                break;
        }
        Intrinsics.c(c2, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-javaObjectType>>");
        return c2;
    }

    public static final KClass c(Class cls) {
        Intrinsics.e(cls, "<this>");
        return Reflection.b(cls);
    }
}
