package kr.promu.caireen.imaging.data;

import java.awt.image.BufferedImage;

import kr.promu.caireen.imaging.data.pixel.GrayscalePixelData;

public class GrayscaleImageData extends ImageData<GrayscalePixelData> {

    private final static GrayscalePixelData DEFAULT_PIXEL = new GrayscalePixelData(0);

    public GrayscaleImageData(final int width, final int height) {
	super(width, height);
    }

    @Override
    protected int getImageType() {
	return BufferedImage.TYPE_BYTE_GRAY;
    }

    @Override
    public ImageData<GrayscalePixelData> cloneEmpty() {
	return new GrayscaleImageData(getWidth(), getHeight());
    }

    @Override
    public GrayscalePixelData getDefaultPixel() {
	return DEFAULT_PIXEL;
    }
}
