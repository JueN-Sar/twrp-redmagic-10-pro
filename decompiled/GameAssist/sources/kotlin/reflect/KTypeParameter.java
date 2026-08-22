package kotlin.reflect;

import java.util.List;
import kotlin.Metadata;
import kotlin.SinceKotlin;

@SinceKotlin
@Metadata
/* loaded from: classes2.dex */
public interface KTypeParameter extends KClassifier {
    KVariance a();

    String getName();

    List getUpperBounds();
}
