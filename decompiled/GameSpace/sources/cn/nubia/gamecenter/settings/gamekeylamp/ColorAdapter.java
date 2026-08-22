package cn.nubia.gamecenter.settings.gamekeylamp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.gamekeylamp.ColorAdapter;
import java.util.List;

/* loaded from: classes.dex */
public class ColorAdapter extends RecyclerView.Adapter {
    public static final String PAYLOAD_SELECT_CHANGE = "select_change";
    private static BitmapDrawable sCode201Drawable;
    private final ColorClickListener mClickListener;

    public interface ColorClickListener {
        void onItemClick(int i);
    }

    class ColorHolder extends RecyclerView.ViewHolder {
        public ImageView mLampColor;

        public ColorHolder(View view) {
            super(view);
            ImageView imageView = (ImageView) view.findViewById(R.id.lamp_color);
            this.mLampColor = imageView;
            imageView.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamecenter.settings.gamekeylamp.ColorAdapter$ColorHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    ColorAdapter.ColorHolder.this.m206x5f261fea(view2);
                }
            });
        }

        /* renamed from: lambda$new$0$cn-nubia-gamecenter-settings-gamekeylamp-ColorAdapter$ColorHolder, reason: not valid java name */
        /* synthetic */ void m206x5f261fea(View view) {
            view.setSelected(true);
            int intValue = ((Integer) view.getTag()).intValue();
            if (ColorAdapter.this.isSelected(intValue) || ColorAdapter.this.mClickListener == null) {
                return;
            }
            ColorAdapter.this.mClickListener.onItemClick(intValue);
        }
    }

    public ColorAdapter(ColorClickListener colorClickListener) {
        this.mClickListener = colorClickListener;
    }

    private static void ensureCode201Drawable(ImageView imageView) {
        if (sCode201Drawable != null) {
            return;
        }
        try {
            Bitmap decodeResource = BitmapFactory.decodeResource(imageView.getResources(), R.drawable.lamp_color);
            if (decodeResource == null) {
                return;
            }
            if (decodeResource.getHeight() <= 12) {
                sCode201Drawable = new BitmapDrawable(imageView.getResources(), decodeResource);
            } else {
                sCode201Drawable = new BitmapDrawable(imageView.getResources(), Bitmap.createBitmap(decodeResource, 0, 6, decodeResource.getWidth(), decodeResource.getHeight() - 12));
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSelected(int i) {
        boolean z = KeyLampHelper.getInstance().getSelectedColorPosition() == i;
        Log.i(KeyLampHelper.TAG, "ColorAdapter ----- isSelected() position : " + i + " isSelected : " + z);
        return z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return KeyLampHelper.getInstance().getCurrentColors().size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Log.i(KeyLampHelper.TAG, "ColorAdapter ----- onBindViewHolder(2) position : " + i);
        ImageView imageView = ((ColorHolder) viewHolder).mLampColor;
        imageView.setTag(Integer.valueOf(i));
        imageView.setSelected(isSelected(i));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        if (!KeyLampHelper.getInstance().usesLampColorDrawableAt(i)) {
            int[] colorsByPosition = KeyLampHelper.getInstance().getColorsByPosition(i);
            if (colorsByPosition == null || colorsByPosition.length == 0) {
                return;
            }
            imageView.setImageDrawable(new GradientSegmentDrawable(colorsByPosition, imageView.getResources().getDimension(R.dimen.colorful_light_color_image_radius)));
            return;
        }
        ensureCode201Drawable(imageView);
        BitmapDrawable bitmapDrawable = sCode201Drawable;
        if (bitmapDrawable != null) {
            imageView.setImageDrawable(bitmapDrawable);
        } else {
            imageView.setImageResource(R.drawable.lamp_color);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
        if (list.isEmpty()) {
            onBindViewHolder(viewHolder, i);
            return;
        }
        Log.i(KeyLampHelper.TAG, "ColorAdapter ----- onBindViewHolder(3) position : " + i + " payload : " + list.get(0));
        ColorHolder colorHolder = (ColorHolder) viewHolder;
        if (PAYLOAD_SELECT_CHANGE.equals((String) list.get(0))) {
            colorHolder.mLampColor.setSelected(KeyLampHelper.getInstance().getSelectedColorPosition() == i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ColorHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lamp_color_item, viewGroup, false));
    }
}
