package kr.digitron.caireen.imaging.data;

import java.awt.image.BufferedImage;

import kr.digitron.caireen.imaging.data.pixel.GrayscalePixelData;

public class GrayscaleImageData extends ImageData<GrayscalePixelData> {

    public GrayscaleImageData(final int width, final int height) {
	super(width, height);
    }

    @Override
    protected int getImageType() {
	return BufferedImage.TYPE_BYTE_GRAY;
    }
}
