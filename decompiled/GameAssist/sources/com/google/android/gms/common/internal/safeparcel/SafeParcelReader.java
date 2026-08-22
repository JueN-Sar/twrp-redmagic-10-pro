package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class SafeParcelReader {

    public static class ParseException extends RuntimeException {
        public ParseException(@NonNull String str, @NonNull Parcel parcel) {
            super(str + " Parcel: pos=" + parcel.dataPosition() + " size=" + parcel.dataSize());
        }
    }

    public static int A(Parcel parcel, int i2) {
        H(parcel, i2, 4);
        return parcel.readInt();
    }

    public static long B(Parcel parcel, int i2) {
        H(parcel, i2, 8);
        return parcel.readLong();
    }

    public static Long C(Parcel parcel, int i2) {
        int D = D(parcel, i2);
        if (D == 0) {
            return null;
        }
        G(parcel, i2, D, 8);
        return Long.valueOf(parcel.readLong());
    }

    public static int D(Parcel parcel, int i2) {
        return (i2 & (-65536)) != -65536 ? (char) (i2 >> 16) : parcel.readInt();
    }

    public static void E(Parcel parcel, int i2) {
        parcel.setDataPosition(parcel.dataPosition() + D(parcel, i2));
    }

    public static int F(Parcel parcel) {
        int y = y(parcel);
        int D = D(parcel, y);
        int u = u(y);
        int dataPosition = parcel.dataPosition();
        if (u != 20293) {
            throw new ParseException("Expected object header. Got 0x".concat(String.valueOf(Integer.toHexString(y))), parcel);
        }
        int i2 = D + dataPosition;
        if (i2 >= dataPosition && i2 <= parcel.dataSize()) {
            return i2;
        }
        throw new ParseException("Size read is invalid start=" + dataPosition + " end=" + i2, parcel);
    }

    private static void G(Parcel parcel, int i2, int i3, int i4) {
        if (i3 == i4) {
            return;
        }
        throw new ParseException("Expected size " + i4 + " got " + i3 + " (0x" + Integer.toHexString(i3) + ")", parcel);
    }

    private static void H(Parcel parcel, int i2, int i3) {
        int D = D(parcel, i2);
        if (D == i3) {
            return;
        }
        throw new ParseException("Expected size " + i3 + " got " + D + " (0x" + Integer.toHexString(D) + ")", parcel);
    }

    public static BigDecimal a(Parcel parcel, int i2) {
        int D = D(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (D == 0) {
            return null;
        }
        byte[] createByteArray = parcel.createByteArray();
        int readInt = parcel.readInt();
        parcel.setDataPosition(dataPosition + D);
        return new BigDecimal(new BigInteger(createByteArray), readInt);
    }

    public static BigDecimal[] b(Parcel parcel, int i2) {
        int D = D(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (D == 0) {
            return null;
        }
        int readInt = parcel.readInt();
        BigDecimal[] bigDecimalArr = new BigDecimal[readInt];
        for (int i3 = 0; i3 < readInt; i3++) {
            byte[] createByteArray = parcel.createByteArray();
            bigDecimalArr[i3] = new BigDecimal(new BigInteger(createByteArray), parcel.readInt());
        }
        parcel.setDataPosition(dataPosition + D);
        return bigDecimalArr;
    }

    public static BigInteger c(Parcel parcel, int i2) {
        int D = D(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (D == 0) {
            return null;
        }
        byte[] createByteArray = parcel.createByteArray();
        parcel.setDataPosition(dataPosition + D);
        return new BigInteger(createByteArray);
    }

    public static BigInteger[] d(Parcel parcel, int i2) {
        int D = D(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (D == 0) {
            return null;
        }
        int readInt = parcel.readInt();
        BigInteger[] bigIntegerArr = new BigInteger[readInt];
        for (int i3 = 0; i3 < readInt; i3++) {
            bigIntegerArr[i3] = new BigInteger(parcel.createByteArray());
        }
        parcel.setDataPosition(dataPosition + D);
        return bigIntegerArr;
    }

    public static boolean[] e(Parcel parcel, int i2) {
        int D = D(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (D == 0) {
            return null;
        }
        boolean[] createBooleanArray = parcel.createBooleanArray();
        parcel.setDataPosition(dataPosition + D);
        return createBooleanArray;
    }

    public static Bundle f(Parcel parcel, int i2) {
        int D = D(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (D == 0) {
            return null;
        }
        Bundle readBundle = parcel.readBundle();
        parcel.setDataPosition(dataPosition + D);
        return readBundle;
    }

    public static byte[] g(Parcel parcel, int i2) {
        int D = D(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (D == 0) {
            return null;
        }
        byte[] createByteArray = parcel.createByteArray();
        parcel.setDataPosition(dataPosition + D);
        return createByteArray;
    }

    public static double[] h(Parcel parcel, int i2) {
        int D = D(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (D == 0) {
            return null;
        }
        double[] createDoubleArray = parcel.createDoubleArray();
        parcel.setDataPosition(dataPosition + D);
        return createDoubleArray;
    }

    public static float[] i(Parcel parcel, int i2) {
        int D = D(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (D == 0) {
            return null;
        }
        float[] createFloatArray = parcel.createFloatArray();
        parcel.setDataPosition(dataPosition + D);
        return createFloatArray;
    }

    public static int[] j(Parcel parcel, int i2) {
        int D = D(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (D == 0) {
            return null;
        }
        int[] createIntArray = parcel.createIntArray();
        parcel.setDataPosition(dataPosition + D);
        return createIntArray;
    }

    public static long[] k(Parcel parcel, int i2) {
        int D = D(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (D == 0) {
            return null;
        }
        long[] createLongArray = parcel.createLongArray();
        parcel.setDataPosition(dataPosition + D);
        return createLongArray;
    }

    public static Parcel l(Parcel parcel, int i2) {
        int D = D(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (D == 0) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        obtain.appendFrom(parcel, dataPosition, D);
        parcel.setDataPosition(dataPosition + D);
        return obtain;
    }

    public static Parcel[] m(Parcel parcel, int i2) {
        int D = D(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (D == 0) {
            return null;
        }
        int readInt = parcel.readInt();
        Parcel[] parcelArr = new Parcel[readInt];
        for (int i3 = 0; i3 < readInt; i3++) {
            int readInt2 = parcel.readInt();
            if (readInt2 != 0) {
                int dataPosition2 = parcel.dataPosition();
                Parcel obtain = Parcel.obtain();
                obtain.appendFrom(parcel, dataPosition2, readInt2);
                parcelArr[i3] = obtain;
                parcel.setDataPosition(dataPosition2 + readInt2);
            } else {
                parcelArr[i3] = null;
            }
        }
        parcel.setDataPosition(dataPosition + D);
        return parcelArr;
    }

    public static Parcelable n(Parcel parcel, int i2, Parcelable.Creator creator) {
        int D = D(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (D == 0) {
            return null;
        }
        Parcelable parcelable = (Parcelable) creator.createFromParcel(parcel);
        parcel.setDataPosition(dataPosition + D);
        return parcelable;
    }

    public static String o(Parcel parcel, int i2) {
        int D = D(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (D == 0) {
            return null;
        }
        String readString = parcel.readString();
        parcel.setDataPosition(dataPosition + D);
        return readString;
    }

    public static String[] p(Parcel parcel, int i2) {
        int D = D(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (D == 0) {
            return null;
        }
        String[] createStringArray = parcel.createStringArray();
        parcel.setDataPosition(dataPosition + D);
        return createStringArray;
    }

    public static ArrayList q(Parcel parcel, int i2) {
        int D = D(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (D == 0) {
            return null;
        }
        ArrayList<String> createStringArrayList = parcel.createStringArrayList();
        parcel.setDataPosition(dataPosition + D);
        return createStringArrayList;
    }

    public static Object[] r(Parcel parcel, int i2, Parcelable.Creator creator) {
        int D = D(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (D == 0) {
            return null;
        }
        Object[] createTypedArray = parcel.createTypedArray(creator);
        parcel.setDataPosition(dataPosition + D);
        return createTypedArray;
    }

    public static ArrayList s(Parcel parcel, int i2, Parcelable.Creator creator) {
        int D = D(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (D == 0) {
            return null;
        }
        ArrayList createTypedArrayList = parcel.createTypedArrayList(creator);
        parcel.setDataPosition(dataPosition + D);
        return createTypedArrayList;
    }

    public static void t(Parcel parcel, int i2) {
        if (parcel.dataPosition() == i2) {
            return;
        }
        throw new ParseException("Overread allowed size end=" + i2, parcel);
    }

    public static int u(int i2) {
        return (char) i2;
    }

    public static boolean v(Parcel parcel, int i2) {
        H(parcel, i2, 4);
        return parcel.readInt() != 0;
    }

    public static double w(Parcel parcel, int i2) {
        H(parcel, i2, 8);
        return parcel.readDouble();
    }

    public static float x(Parcel parcel, int i2) {
        H(parcel, i2, 4);
        return parcel.readFloat();
    }

    public static int y(Parcel parcel) {
        return parcel.readInt();
    }

    public static IBinder z(Parcel parcel, int i2) {
        int D = D(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (D == 0) {
            return null;
        }
        IBinder readStrongBinder = parcel.readStrongBinder();
        parcel.setDataPosition(dataPosition + D);
        return readStrongBinder;
    }
}
