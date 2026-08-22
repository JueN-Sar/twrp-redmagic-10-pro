package androidx.media3.exoplayer.source;

import androidx.media3.common.util.Assertions;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.mp3.Mp3Extractor;
import java.io.IOException;

/* loaded from: classes.dex */
public final class BundledExtractorsAdapter implements ProgressiveMediaExtractor {
    private Extractor extractor;
    private ExtractorInput extractorInput;
    private final ExtractorsFactory extractorsFactory;

    public BundledExtractorsAdapter(ExtractorsFactory extractorsFactory) {
        this.extractorsFactory = extractorsFactory;
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public void disableSeekingOnMp3Streams() {
        Extractor extractor = this.extractor;
        if (extractor == null) {
            return;
        }
        Extractor underlyingImplementation = extractor.getUnderlyingImplementation();
        if (underlyingImplementation instanceof Mp3Extractor) {
            ((Mp3Extractor) underlyingImplementation).disableSeeking();
        }
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public long getCurrentInputPosition() {
        ExtractorInput extractorInput = this.extractorInput;
        if (extractorInput != null) {
            return extractorInput.getPosition();
        }
        return -1L;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0057, code lost:
    
        if (r6.getPosition() != r11) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x007c, code lost:
    
        r2 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0079, code lost:
    
        if (r6.getPosition() != r11) goto L38;
     */
    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void init(androidx.media3.common.DataReader r8, android.net.Uri r9, java.util.Map<java.lang.String, java.util.List<java.lang.String>> r10, long r11, long r13, androidx.media3.extractor.ExtractorOutput r15) throws java.io.IOException {
        /*
            r7 = this;
            androidx.media3.extractor.DefaultExtractorInput r6 = new androidx.media3.extractor.DefaultExtractorInput
            r0 = r6
            r1 = r8
            r2 = r11
            r4 = r13
            r0.<init>(r1, r2, r4)
            r7.extractorInput = r6
            androidx.media3.extractor.Extractor r8 = r7.extractor
            if (r8 == 0) goto L10
            return
        L10:
            androidx.media3.extractor.ExtractorsFactory r8 = r7.extractorsFactory
            androidx.media3.extractor.Extractor[] r8 = r8.createExtractors(r9, r10)
            int r10 = r8.length
            com.google.common.collect.ImmutableList$Builder r10 = com.google.common.collect.ImmutableList.builderWithExpectedSize(r10)
            int r13 = r8.length
            r14 = 0
            r0 = 1
            if (r13 != r0) goto L26
            r8 = r8[r14]
            r7.extractor = r8
            goto L8c
        L26:
            int r13 = r8.length
            r1 = r14
        L28:
            if (r1 >= r13) goto L88
            r2 = r8[r1]
            boolean r3 = r2.sniff(r6)     // Catch: java.lang.Throwable -> L5a java.io.EOFException -> L6f
            if (r3 == 0) goto L46
            r7.extractor = r2     // Catch: java.lang.Throwable -> L5a java.io.EOFException -> L6f
            if (r2 != 0) goto L3e
            long r1 = r6.getPosition()
            int r11 = (r1 > r11 ? 1 : (r1 == r11 ? 0 : -1))
            if (r11 != 0) goto L3f
        L3e:
            r14 = r0
        L3f:
            androidx.media3.common.util.Assertions.checkState(r14)
            r6.resetPeekPosition()
            goto L88
        L46:
            java.util.List r2 = r2.getSniffFailureDetails()     // Catch: java.lang.Throwable -> L5a java.io.EOFException -> L6f
            r10.addAll(r2)     // Catch: java.lang.Throwable -> L5a java.io.EOFException -> L6f
            androidx.media3.extractor.Extractor r2 = r7.extractor
            if (r2 != 0) goto L7e
            long r2 = r6.getPosition()
            int r2 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r2 != 0) goto L7c
            goto L7e
        L5a:
            r8 = move-exception
            androidx.media3.extractor.Extractor r7 = r7.extractor
            if (r7 != 0) goto L67
            long r9 = r6.getPosition()
            int r7 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r7 != 0) goto L68
        L67:
            r14 = r0
        L68:
            androidx.media3.common.util.Assertions.checkState(r14)
            r6.resetPeekPosition()
            throw r8
        L6f:
            androidx.media3.extractor.Extractor r2 = r7.extractor
            if (r2 != 0) goto L7e
            long r2 = r6.getPosition()
            int r2 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r2 != 0) goto L7c
            goto L7e
        L7c:
            r2 = r14
            goto L7f
        L7e:
            r2 = r0
        L7f:
            androidx.media3.common.util.Assertions.checkState(r2)
            r6.resetPeekPosition()
            int r1 = r1 + 1
            goto L28
        L88:
            androidx.media3.extractor.Extractor r11 = r7.extractor
            if (r11 == 0) goto L92
        L8c:
            androidx.media3.extractor.Extractor r7 = r7.extractor
            r7.init(r15)
            return
        L92:
            androidx.media3.exoplayer.source.UnrecognizedInputFormatException r7 = new androidx.media3.exoplayer.source.UnrecognizedInputFormatException
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r12 = "None of the available extractors ("
            r11.<init>(r12)
            java.lang.String r12 = ", "
            com.google.common.base.Joiner r12 = com.google.common.base.Joiner.on(r12)
            com.google.common.collect.ImmutableList r8 = com.google.common.collect.ImmutableList.copyOf(r8)
            androidx.media3.exoplayer.source.BundledExtractorsAdapter$$ExternalSyntheticLambda0 r13 = new androidx.media3.exoplayer.source.BundledExtractorsAdapter$$ExternalSyntheticLambda0
            r13.<init>()
            java.util.List r8 = com.google.common.collect.Lists.transform(r8, r13)
            java.lang.String r8 = r12.join(r8)
            java.lang.StringBuilder r8 = r11.append(r8)
            java.lang.String r11 = ") could read the stream."
            java.lang.StringBuilder r8 = r8.append(r11)
            java.lang.String r8 = r8.toString()
            java.lang.Object r9 = androidx.media3.common.util.Assertions.checkNotNull(r9)
            android.net.Uri r9 = (android.net.Uri) r9
            com.google.common.collect.ImmutableList r10 = r10.build()
            r7.<init>(r8, r9, r10)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.source.BundledExtractorsAdapter.init(androidx.media3.common.DataReader, android.net.Uri, java.util.Map, long, long, androidx.media3.extractor.ExtractorOutput):void");
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public int read(PositionHolder positionHolder) throws IOException {
        return ((Extractor) Assertions.checkNotNull(this.extractor)).read((ExtractorInput) Assertions.checkNotNull(this.extractorInput), positionHolder);
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public void release() {
        Extractor extractor = this.extractor;
        if (extractor != null) {
            extractor.release();
            this.extractor = null;
        }
        this.extractorInput = null;
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public void seek(long j, long j2) {
        ((Extractor) Assertions.checkNotNull(this.extractor)).seek(j, j2);
    }
}
