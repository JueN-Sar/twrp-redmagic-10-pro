package androidx.media3.extractor.mp4;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.SniffFailure;
import java.io.IOException;

/* loaded from: classes.dex */
final class Sniffer {
    public static final int BRAND_HEIC = 1751476579;
    public static final int BRAND_QUICKTIME = 1903435808;
    private static final int[] COMPATIBLE_BRANDS = {1769172845, 1769172786, 1769172787, 1769172788, 1769172789, 1769172790, 1769172793, Atom.TYPE_avc1, Atom.TYPE_hvc1, Atom.TYPE_hev1, Atom.TYPE_av01, 1836069937, 1836069938, 862401121, 862401122, 862417462, 862417718, 862414134, 862414646, 1295275552, 1295270176, 1714714144, 1801741417, 1295275600, BRAND_QUICKTIME, 1297305174, 1684175153, 1769172332, 1885955686};
    private static final int SEARCH_LENGTH = 4096;

    private Sniffer() {
    }

    private static boolean isCompatibleBrand(int i, boolean z) {
        if ((i >>> 8) == 3368816) {
            return true;
        }
        if (i == 1751476579 && z) {
            return true;
        }
        for (int i2 : COMPATIBLE_BRANDS) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public static SniffFailure sniffFragmented(ExtractorInput extractorInput) throws IOException {
        return sniffInternal(extractorInput, true, false);
    }

    private static SniffFailure sniffInternal(ExtractorInput extractorInput, boolean z, boolean z2) throws IOException {
        int i;
        int i2;
        int i3;
        boolean z3;
        int[] iArr;
        long length = extractorInput.getLength();
        long j = -1;
        long j2 = 4096;
        if (length != -1 && length <= 4096) {
            j2 = length;
        }
        int i4 = (int) j2;
        ParsableByteArray parsableByteArray = new ParsableByteArray(64);
        int i5 = 0;
        int i6 = 0;
        boolean z4 = false;
        while (i6 < i4) {
            parsableByteArray.reset(8);
            if (!extractorInput.peekFully(parsableByteArray.getData(), i5, 8, true)) {
                break;
            }
            long readUnsignedInt = parsableByteArray.readUnsignedInt();
            int readInt = parsableByteArray.readInt();
            if (readUnsignedInt == 1) {
                extractorInput.peekFully(parsableByteArray.getData(), 8, 8);
                i2 = 16;
                parsableByteArray.setLimit(16);
                readUnsignedInt = parsableByteArray.readLong();
            } else {
                if (readUnsignedInt == 0) {
                    long length2 = extractorInput.getLength();
                    if (length2 != j) {
                        readUnsignedInt = (length2 - extractorInput.getPeekPosition()) + 8;
                    }
                }
                i2 = 8;
            }
            long j3 = readUnsignedInt;
            long j4 = i2;
            if (j3 < j4) {
                return new AtomSizeTooSmallSniffFailure(readInt, j3, i2);
            }
            i6 += i2;
            if (readInt == 1836019574) {
                i4 += (int) j3;
                if (length != -1 && i4 > length) {
                    i4 = (int) length;
                }
            } else {
                if (readInt == 1836019558 || readInt == 1836475768) {
                    i = 1;
                    break;
                }
                long j5 = length;
                if (readInt == 1835295092) {
                    z4 = true;
                }
                if ((i6 + j3) - j4 >= i4) {
                    i = 0;
                    break;
                }
                int i7 = (int) (j3 - j4);
                i6 += i7;
                if (readInt != 1718909296) {
                    i3 = 0;
                    if (i7 != 0) {
                        extractorInput.advancePeekPosition(i7);
                    }
                } else {
                    if (i7 < 8) {
                        return new AtomSizeTooSmallSniffFailure(readInt, i7, 8);
                    }
                    parsableByteArray.reset(i7);
                    i3 = 0;
                    extractorInput.peekFully(parsableByteArray.getData(), 0, i7);
                    int readInt2 = parsableByteArray.readInt();
                    if (isCompatibleBrand(readInt2, z2)) {
                        z4 = true;
                    }
                    parsableByteArray.skipBytes(4);
                    int bytesLeft = parsableByteArray.bytesLeft() / 4;
                    if (!z4 && bytesLeft > 0) {
                        iArr = new int[bytesLeft];
                        int i8 = 0;
                        while (true) {
                            if (i8 >= bytesLeft) {
                                z3 = z4;
                                break;
                            }
                            int readInt3 = parsableByteArray.readInt();
                            iArr[i8] = readInt3;
                            if (isCompatibleBrand(readInt3, z2)) {
                                z3 = true;
                                break;
                            }
                            i8++;
                        }
                    } else {
                        z3 = z4;
                        iArr = null;
                    }
                    if (!z3) {
                        return new UnsupportedBrandsSniffFailure(readInt2, iArr);
                    }
                    z4 = z3;
                }
                i5 = i3;
                length = j5;
            }
            j = -1;
        }
        i = i5;
        if (!z4) {
            return NoDeclaredBrandSniffFailure.INSTANCE;
        }
        if (z != i) {
            return i != 0 ? IncorrectFragmentationSniffFailure.FILE_FRAGMENTED : IncorrectFragmentationSniffFailure.FILE_NOT_FRAGMENTED;
        }
        return null;
    }

    public static SniffFailure sniffUnfragmented(ExtractorInput extractorInput, boolean z) throws IOException {
        return sniffInternal(extractorInput, false, z);
    }
}
