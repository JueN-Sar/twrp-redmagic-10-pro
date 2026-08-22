package kotlin.enums;

import java.lang.Enum;
import java.util.List;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.markers.KMappedMarker;

@SinceKotlin
@Metadata
@ExperimentalStdlibApi
/* loaded from: classes2.dex */
public interface EnumEntries<E extends Enum<E>> extends List<E>, KMappedMarker {
}
