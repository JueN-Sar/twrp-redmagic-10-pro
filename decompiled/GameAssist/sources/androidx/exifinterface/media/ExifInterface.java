package androidx.exifinterface.media;

import android.util.Log;
import androidx.annotation.RestrictTo;
import com.google.mlkit.common.MlKitException;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class ExifInterface {
    private static final HashMap[] A;
    private static final HashMap[] B;
    private static final HashSet C;
    private static final HashMap D;
    static final Charset E;
    static final byte[] F;
    private static final Pattern G;
    private static final Pattern H;

    /* renamed from: i, reason: collision with root package name */
    private static SimpleDateFormat f3881i;

    /* renamed from: m, reason: collision with root package name */
    private static final ExifTag[] f3885m;

    /* renamed from: n, reason: collision with root package name */
    private static final ExifTag[] f3886n;

    /* renamed from: o, reason: collision with root package name */
    private static final ExifTag[] f3887o;

    /* renamed from: p, reason: collision with root package name */
    private static final ExifTag[] f3888p;

    /* renamed from: q, reason: collision with root package name */
    private static final ExifTag[] f3889q;

    /* renamed from: r, reason: collision with root package name */
    private static final ExifTag f3890r;

    /* renamed from: s, reason: collision with root package name */
    private static final ExifTag[] f3891s;
    private static final ExifTag[] t;
    private static final ExifTag[] u;
    private static final ExifTag[] v;
    static final ExifTag[][] w;
    private static final ExifTag[] x;
    private static final ExifTag y;
    private static final ExifTag z;

    /* renamed from: a, reason: collision with root package name */
    private static final List f3873a = Arrays.asList(1, 6, 3, 8);

    /* renamed from: b, reason: collision with root package name */
    private static final List f3874b = Arrays.asList(2, 7, 4, 5);

    /* renamed from: c, reason: collision with root package name */
    public static final int[] f3875c = {8, 8, 8};

    /* renamed from: d, reason: collision with root package name */
    public static final int[] f3876d = {4};

    /* renamed from: e, reason: collision with root package name */
    public static final int[] f3877e = {8};

    /* renamed from: f, reason: collision with root package name */
    static final byte[] f3878f = {-1, -40, -1};

    /* renamed from: g, reason: collision with root package name */
    private static final byte[] f3879g = {79, 76, 89, 77, 80, 0};

    /* renamed from: h, reason: collision with root package name */
    private static final byte[] f3880h = {79, 76, 89, 77, 80, 85, 83, 0, 73, 73};

    /* renamed from: j, reason: collision with root package name */
    static final String[] f3882j = {"", "BYTE", "STRING", "USHORT", "ULONG", "URATIONAL", "SBYTE", "UNDEFINED", "SSHORT", "SLONG", "SRATIONAL", "SINGLE", "DOUBLE"};

    /* renamed from: k, reason: collision with root package name */
    static final int[] f3883k = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8, 1};

    /* renamed from: l, reason: collision with root package name */
    static final byte[] f3884l = {65, 83, 67, 73, 73, 0, 0, 0};

    private static class ByteOrderedDataOutputStream extends FilterOutputStream {

        /* renamed from: c, reason: collision with root package name */
        private final OutputStream f3898c;

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr) {
            this.f3898c.write(bArr);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr, int i2, int i3) {
            this.f3898c.write(bArr, i2, i3);
        }
    }

    private static class ExifAttribute {

        /* renamed from: a, reason: collision with root package name */
        public final int f3899a;

        /* renamed from: b, reason: collision with root package name */
        public final byte[] f3900b;

        public String toString() {
            return "(" + ExifInterface.f3882j[this.f3899a] + ", data length:" + this.f3900b.length + ")";
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface IfdType {
    }

    private static class Rational {

        /* renamed from: a, reason: collision with root package name */
        public final long f3905a;

        /* renamed from: b, reason: collision with root package name */
        public final long f3906b;

        public String toString() {
            return this.f3905a + "/" + this.f3906b;
        }
    }

    static {
        ExifTag[] exifTagArr = {new ExifTag("NewSubfileType", 254, 4), new ExifTag("SubfileType", 255, 4), new ExifTag("ImageWidth", 256, 3, 4), new ExifTag("ImageLength", 257, 3, 4), new ExifTag("BitsPerSample", 258, 3), new ExifTag("Compression", 259, 3), new ExifTag("PhotometricInterpretation", 262, 3), new ExifTag("ImageDescription", 270, 2), new ExifTag("Make", 271, 2), new ExifTag("Model", 272, 2), new ExifTag("StripOffsets", 273, 3, 4), new ExifTag("Orientation", 274, 3), new ExifTag("SamplesPerPixel", 277, 3), new ExifTag("RowsPerStrip", 278, 3, 4), new ExifTag("StripByteCounts", 279, 3, 4), new ExifTag("XResolution", 282, 5), new ExifTag("YResolution", 283, 5), new ExifTag("PlanarConfiguration", 284, 3), new ExifTag("ResolutionUnit", 296, 3), new ExifTag("TransferFunction", MlKitException.LOW_LIGHT_IMAGE_CAPTURE_PROCESSING_FAILURE, 3), new ExifTag("Software", 305, 2), new ExifTag("DateTime", 306, 2), new ExifTag("Artist", 315, 2), new ExifTag("WhitePoint", 318, 5), new ExifTag("PrimaryChromaticities", 319, 5), new ExifTag("SubIFDPointer", 330, 4), new ExifTag("JPEGInterchangeFormat", 513, 4), new ExifTag("JPEGInterchangeFormatLength", 514, 4), new ExifTag("YCbCrCoefficients", 529, 5), new ExifTag("YCbCrSubSampling", 530, 3), new ExifTag("YCbCrPositioning", 531, 3), new ExifTag("ReferenceBlackWhite", 532, 5), new ExifTag("Copyright", 33432, 2), new ExifTag("ExifIFDPointer", 34665, 4), new ExifTag("GPSInfoIFDPointer", 34853, 4), new ExifTag("SensorTopBorder", 4, 4), new ExifTag("SensorLeftBorder", 5, 4), new ExifTag("SensorBottomBorder", 6, 4), new ExifTag("SensorRightBorder", 7, 4), new ExifTag("ISO", 23, 3), new ExifTag("JpgFromRaw", 46, 7)};
        f3885m = exifTagArr;
        ExifTag[] exifTagArr2 = {new ExifTag("ExposureTime", 33434, 5), new ExifTag("FNumber", 33437, 5), new ExifTag("ExposureProgram", 34850, 3), new ExifTag("SpectralSensitivity", 34852, 2), new ExifTag("PhotographicSensitivity", 34855, 3), new ExifTag("OECF", 34856, 7), new ExifTag("ExifVersion", 36864, 2), new ExifTag("DateTimeOriginal", 36867, 2), new ExifTag("DateTimeDigitized", 36868, 2), new ExifTag("ComponentsConfiguration", 37121, 7), new ExifTag("CompressedBitsPerPixel", 37122, 5), new ExifTag("ShutterSpeedValue", 37377, 10), new ExifTag("ApertureValue", 37378, 5), new ExifTag("BrightnessValue", 37379, 10), new ExifTag("ExposureBiasValue", 37380, 10), new ExifTag("MaxApertureValue", 37381, 5), new ExifTag("SubjectDistance", 37382, 5), new ExifTag("MeteringMode", 37383, 3), new ExifTag("LightSource", 37384, 3), new ExifTag("Flash", 37385, 3), new ExifTag("FocalLength", 37386, 5), new ExifTag("SubjectArea", 37396, 3), new ExifTag("MakerNote", 37500, 7), new ExifTag("UserComment", 37510, 7), new ExifTag("SubSecTime", 37520, 2), new ExifTag("SubSecTimeOriginal", 37521, 2), new ExifTag("SubSecTimeDigitized", 37522, 2), new ExifTag("FlashpixVersion", 40960, 7), new ExifTag("ColorSpace", 40961, 3), new ExifTag("PixelXDimension", 40962, 3, 4), new ExifTag("PixelYDimension", 40963, 3, 4), new ExifTag("RelatedSoundFile", 40964, 2), new ExifTag("InteroperabilityIFDPointer", 40965, 4), new ExifTag("FlashEnergy", 41483, 5), new ExifTag("SpatialFrequencyResponse", 41484, 7), new ExifTag("FocalPlaneXResolution", 41486, 5), new ExifTag("FocalPlaneYResolution", 41487, 5), new ExifTag("FocalPlaneResolutionUnit", 41488, 3), new ExifTag("SubjectLocation", 41492, 3), new ExifTag("ExposureIndex", 41493, 5), new ExifTag("SensingMethod", 41495, 3), new ExifTag("FileSource", 41728, 7), new ExifTag("SceneType", 41729, 7), new ExifTag("CFAPattern", 41730, 7), new ExifTag("CustomRendered", 41985, 3), new ExifTag("ExposureMode", 41986, 3), new ExifTag("WhiteBalance", 41987, 3), new ExifTag("DigitalZoomRatio", 41988, 5), new ExifTag("FocalLengthIn35mmFilm", 41989, 3), new ExifTag("SceneCaptureType", 41990, 3), new ExifTag("GainControl", 41991, 3), new ExifTag("Contrast", 41992, 3), new ExifTag("Saturation", 41993, 3), new ExifTag("Sharpness", 41994, 3), new ExifTag("DeviceSettingDescription", 41995, 7), new ExifTag("SubjectDistanceRange", 41996, 3), new ExifTag("ImageUniqueID", 42016, 2), new ExifTag("DNGVersion", 50706, 1), new ExifTag("DefaultCropSize", 50720, 3, 4)};
        f3886n = exifTagArr2;
        ExifTag[] exifTagArr3 = {new ExifTag("GPSVersionID", 0, 1), new ExifTag("GPSLatitudeRef", 1, 2), new ExifTag("GPSLatitude", 2, 5), new ExifTag("GPSLongitudeRef", 3, 2), new ExifTag("GPSLongitude", 4, 5), new ExifTag("GPSAltitudeRef", 5, 1), new ExifTag("GPSAltitude", 6, 5), new ExifTag("GPSTimeStamp", 7, 5), new ExifTag("GPSSatellites", 8, 2), new ExifTag("GPSStatus", 9, 2), new ExifTag("GPSMeasureMode", 10, 2), new ExifTag("GPSDOP", 11, 5), new ExifTag("GPSSpeedRef", 12, 2), new ExifTag("GPSSpeed", 13, 5), new ExifTag("GPSTrackRef", 14, 2), new ExifTag("GPSTrack", 15, 5), new ExifTag("GPSImgDirectionRef", 16, 2), new ExifTag("GPSImgDirection", 17, 5), new ExifTag("GPSMapDatum", 18, 2), new ExifTag("GPSDestLatitudeRef", 19, 2), new ExifTag("GPSDestLatitude", 20, 5), new ExifTag("GPSDestLongitudeRef", 21, 2), new ExifTag("GPSDestLongitude", 22, 5), new ExifTag("GPSDestBearingRef", 23, 2), new ExifTag("GPSDestBearing", 24, 5), new ExifTag("GPSDestDistanceRef", 25, 2), new ExifTag("GPSDestDistance", 26, 5), new ExifTag("GPSProcessingMethod", 27, 7), new ExifTag("GPSAreaInformation", 28, 7), new ExifTag("GPSDateStamp", 29, 2), new ExifTag("GPSDifferential", 30, 3)};
        f3887o = exifTagArr3;
        ExifTag[] exifTagArr4 = {new ExifTag("InteroperabilityIndex", 1, 2)};
        f3888p = exifTagArr4;
        ExifTag[] exifTagArr5 = {new ExifTag("NewSubfileType", 254, 4), new ExifTag("SubfileType", 255, 4), new ExifTag("ThumbnailImageWidth", 256, 3, 4), new ExifTag("ThumbnailImageLength", 257, 3, 4), new ExifTag("BitsPerSample", 258, 3), new ExifTag("Compression", 259, 3), new ExifTag("PhotometricInterpretation", 262, 3), new ExifTag("ImageDescription", 270, 2), new ExifTag("Make", 271, 2), new ExifTag("Model", 272, 2), new ExifTag("StripOffsets", 273, 3, 4), new ExifTag("Orientation", 274, 3), new ExifTag("SamplesPerPixel", 277, 3), new ExifTag("RowsPerStrip", 278, 3, 4), new ExifTag("StripByteCounts", 279, 3, 4), new ExifTag("XResolution", 282, 5), new ExifTag("YResolution", 283, 5), new ExifTag("PlanarConfiguration", 284, 3), new ExifTag("ResolutionUnit", 296, 3), new ExifTag("TransferFunction", MlKitException.LOW_LIGHT_IMAGE_CAPTURE_PROCESSING_FAILURE, 3), new ExifTag("Software", 305, 2), new ExifTag("DateTime", 306, 2), new ExifTag("Artist", 315, 2), new ExifTag("WhitePoint", 318, 5), new ExifTag("PrimaryChromaticities", 319, 5), new ExifTag("SubIFDPointer", 330, 4), new ExifTag("JPEGInterchangeFormat", 513, 4), new ExifTag("JPEGInterchangeFormatLength", 514, 4), new ExifTag("YCbCrCoefficients", 529, 5), new ExifTag("YCbCrSubSampling", 530, 3), new ExifTag("YCbCrPositioning", 531, 3), new ExifTag("ReferenceBlackWhite", 532, 5), new ExifTag("Copyright", 33432, 2), new ExifTag("ExifIFDPointer", 34665, 4), new ExifTag("GPSInfoIFDPointer", 34853, 4), new ExifTag("DNGVersion", 50706, 1), new ExifTag("DefaultCropSize", 50720, 3, 4)};
        f3889q = exifTagArr5;
        f3890r = new ExifTag("StripOffsets", 273, 3);
        ExifTag[] exifTagArr6 = {new ExifTag("ThumbnailImage", 256, 7), new ExifTag("CameraSettingsIFDPointer", 8224, 4), new ExifTag("ImageProcessingIFDPointer", 8256, 4)};
        f3891s = exifTagArr6;
        ExifTag[] exifTagArr7 = {new ExifTag("PreviewImageStart", 257, 4), new ExifTag("PreviewImageLength", 258, 4)};
        t = exifTagArr7;
        ExifTag[] exifTagArr8 = {new ExifTag("AspectFrame", 4371, 3)};
        u = exifTagArr8;
        ExifTag[] exifTagArr9 = {new ExifTag("ColorSpace", 55, 3)};
        v = exifTagArr9;
        ExifTag[][] exifTagArr10 = {exifTagArr, exifTagArr2, exifTagArr3, exifTagArr4, exifTagArr5, exifTagArr, exifTagArr6, exifTagArr7, exifTagArr8, exifTagArr9};
        w = exifTagArr10;
        x = new ExifTag[]{new ExifTag("SubIFDPointer", 330, 4), new ExifTag("ExifIFDPointer", 34665, 4), new ExifTag("GPSInfoIFDPointer", 34853, 4), new ExifTag("InteroperabilityIFDPointer", 40965, 4), new ExifTag("CameraSettingsIFDPointer", 8224, 1), new ExifTag("ImageProcessingIFDPointer", 8256, 1)};
        y = new ExifTag("JPEGInterchangeFormat", 513, 4);
        z = new ExifTag("JPEGInterchangeFormatLength", 514, 4);
        A = new HashMap[exifTagArr10.length];
        B = new HashMap[exifTagArr10.length];
        C = new HashSet(Arrays.asList("FNumber", "DigitalZoomRatio", "ExposureTime", "SubjectDistance", "GPSTimeStamp"));
        D = new HashMap();
        Charset forName = Charset.forName("US-ASCII");
        E = forName;
        F = "Exif\u0000\u0000".getBytes(forName);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        f3881i = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        int i2 = 0;
        while (true) {
            ExifTag[][] exifTagArr11 = w;
            if (i2 >= exifTagArr11.length) {
                HashMap hashMap = D;
                ExifTag[] exifTagArr12 = x;
                hashMap.put(Integer.valueOf(exifTagArr12[0].f3901a), 5);
                hashMap.put(Integer.valueOf(exifTagArr12[1].f3901a), 1);
                hashMap.put(Integer.valueOf(exifTagArr12[2].f3901a), 2);
                hashMap.put(Integer.valueOf(exifTagArr12[3].f3901a), 3);
                hashMap.put(Integer.valueOf(exifTagArr12[4].f3901a), 7);
                hashMap.put(Integer.valueOf(exifTagArr12[5].f3901a), 8);
                G = Pattern.compile(".*[1-9].*");
                H = Pattern.compile("^([0-9][0-9]):([0-9][0-9]):([0-9][0-9])$");
                return;
            }
            A[i2] = new HashMap();
            B[i2] = new HashMap();
            for (ExifTag exifTag : exifTagArr11[i2]) {
                A[i2].put(Integer.valueOf(exifTag.f3901a), exifTag);
                B[i2].put(exifTag.f3902b, exifTag);
            }
            i2++;
        }
    }

    private static class ByteOrderedDataInputStream extends InputStream implements DataInput {

        /* renamed from: k, reason: collision with root package name */
        private static final ByteOrder f3892k = ByteOrder.LITTLE_ENDIAN;

        /* renamed from: l, reason: collision with root package name */
        private static final ByteOrder f3893l = ByteOrder.BIG_ENDIAN;

        /* renamed from: c, reason: collision with root package name */
        private DataInputStream f3894c;

        /* renamed from: h, reason: collision with root package name */
        private ByteOrder f3895h;

        /* renamed from: i, reason: collision with root package name */
        final int f3896i;

        /* renamed from: j, reason: collision with root package name */
        int f3897j;

        @Override // java.io.InputStream
        public int available() {
            return this.f3894c.available();
        }

        @Override // java.io.InputStream
        public int read() {
            this.f3897j++;
            return this.f3894c.read();
        }

        @Override // java.io.DataInput
        public boolean readBoolean() {
            this.f3897j++;
            return this.f3894c.readBoolean();
        }

        @Override // java.io.DataInput
        public byte readByte() {
            int i2 = this.f3897j + 1;
            this.f3897j = i2;
            if (i2 > this.f3896i) {
                throw new EOFException();
            }
            int read = this.f3894c.read();
            if (read >= 0) {
                return (byte) read;
            }
            throw new EOFException();
        }

        @Override // java.io.DataInput
        public char readChar() {
            this.f3897j += 2;
            return this.f3894c.readChar();
        }

        @Override // java.io.DataInput
        public double readDouble() {
            return Double.longBitsToDouble(readLong());
        }

        @Override // java.io.DataInput
        public float readFloat() {
            return Float.intBitsToFloat(readInt());
        }

        @Override // java.io.DataInput
        public void readFully(byte[] bArr, int i2, int i3) {
            int i4 = this.f3897j + i3;
            this.f3897j = i4;
            if (i4 > this.f3896i) {
                throw new EOFException();
            }
            if (this.f3894c.read(bArr, i2, i3) != i3) {
                throw new IOException("Couldn't read up to the length of buffer");
            }
        }

        @Override // java.io.DataInput
        public int readInt() {
            int i2 = this.f3897j + 4;
            this.f3897j = i2;
            if (i2 > this.f3896i) {
                throw new EOFException();
            }
            int read = this.f3894c.read();
            int read2 = this.f3894c.read();
            int read3 = this.f3894c.read();
            int read4 = this.f3894c.read();
            if ((read | read2 | read3 | read4) < 0) {
                throw new EOFException();
            }
            ByteOrder byteOrder = this.f3895h;
            if (byteOrder == f3892k) {
                return (read4 << 24) + (read3 << 16) + (read2 << 8) + read;
            }
            if (byteOrder == f3893l) {
                return (read << 24) + (read2 << 16) + (read3 << 8) + read4;
            }
            throw new IOException("Invalid byte order: " + this.f3895h);
        }

        @Override // java.io.DataInput
        public String readLine() {
            Log.d("ExifInterface", "Currently unsupported");
            return null;
        }

        @Override // java.io.DataInput
        public long readLong() {
            int i2 = this.f3897j + 8;
            this.f3897j = i2;
            if (i2 > this.f3896i) {
                throw new EOFException();
            }
            int read = this.f3894c.read();
            int read2 = this.f3894c.read();
            int read3 = this.f3894c.read();
            int read4 = this.f3894c.read();
            int read5 = this.f3894c.read();
            int read6 = this.f3894c.read();
            int read7 = this.f3894c.read();
            int read8 = this.f3894c.read();
            if ((read | read2 | read3 | read4 | read5 | read6 | read7 | read8) < 0) {
                throw new EOFException();
            }
            ByteOrder byteOrder = this.f3895h;
            if (byteOrder == f3892k) {
                return (read8 << 56) + (read7 << 48) + (read6 << 40) + (read5 << 32) + (read4 << 24) + (read3 << 16) + (read2 << 8) + read;
            }
            if (byteOrder == f3893l) {
                return (read << 56) + (read2 << 48) + (read3 << 40) + (read4 << 32) + (read5 << 24) + (read6 << 16) + (read7 << 8) + read8;
            }
            throw new IOException("Invalid byte order: " + this.f3895h);
        }

        @Override // java.io.DataInput
        public short readShort() {
            int i2;
            int i3 = this.f3897j + 2;
            this.f3897j = i3;
            if (i3 > this.f3896i) {
                throw new EOFException();
            }
            int read = this.f3894c.read();
            int read2 = this.f3894c.read();
            if ((read | read2) < 0) {
                throw new EOFException();
            }
            ByteOrder byteOrder = this.f3895h;
            if (byteOrder == f3892k) {
                i2 = (read2 << 8) + read;
            } else {
                if (byteOrder != f3893l) {
                    throw new IOException("Invalid byte order: " + this.f3895h);
                }
                i2 = (read << 8) + read2;
            }
            return (short) i2;
        }

        @Override // java.io.DataInput
        public String readUTF() {
            this.f3897j += 2;
            return this.f3894c.readUTF();
        }

        @Override // java.io.DataInput
        public int readUnsignedByte() {
            this.f3897j++;
            return this.f3894c.readUnsignedByte();
        }

        @Override // java.io.DataInput
        public int readUnsignedShort() {
            int i2 = this.f3897j + 2;
            this.f3897j = i2;
            if (i2 > this.f3896i) {
                throw new EOFException();
            }
            int read = this.f3894c.read();
            int read2 = this.f3894c.read();
            if ((read | read2) < 0) {
                throw new EOFException();
            }
            ByteOrder byteOrder = this.f3895h;
            if (byteOrder == f3892k) {
                return (read2 << 8) + read;
            }
            if (byteOrder == f3893l) {
                return (read << 8) + read2;
            }
            throw new IOException("Invalid byte order: " + this.f3895h);
        }

        @Override // java.io.DataInput
        public int skipBytes(int i2) {
            int min = Math.min(i2, this.f3896i - this.f3897j);
            int i3 = 0;
            while (i3 < min) {
                i3 += this.f3894c.skipBytes(min - i3);
            }
            this.f3897j += i3;
            return i3;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i2, int i3) {
            int read = this.f3894c.read(bArr, i2, i3);
            this.f3897j += read;
            return read;
        }

        @Override // java.io.DataInput
        public void readFully(byte[] bArr) {
            int length = this.f3897j + bArr.length;
            this.f3897j = length;
            if (length <= this.f3896i) {
                if (this.f3894c.read(bArr, 0, bArr.length) != bArr.length) {
                    throw new IOException("Couldn't read up to the length of buffer");
                }
                return;
            }
            throw new EOFException();
        }
    }

    static class ExifTag {

        /* renamed from: a, reason: collision with root package name */
        public final int f3901a;

        /* renamed from: b, reason: collision with root package name */
        public final String f3902b;

        /* renamed from: c, reason: collision with root package name */
        public final int f3903c;

        /* renamed from: d, reason: collision with root package name */
        public final int f3904d;

        ExifTag(String str, int i2, int i3) {
            this.f3902b = str;
            this.f3901a = i2;
            this.f3903c = i3;
            this.f3904d = -1;
        }

        ExifTag(String str, int i2, int i3, int i4) {
            this.f3902b = str;
            this.f3901a = i2;
            this.f3903c = i3;
            this.f3904d = i4;
        }
    }
}
