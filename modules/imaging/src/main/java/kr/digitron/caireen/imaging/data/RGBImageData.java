package kr.digitron.caireen.imaging.data;

import java.awt.image.BufferedImage;

import kr.digitron.caireen.imaging.data.pixel.RGBPixelData;

public class RGBImageData extends ImageData<RGBPixelData> {

    private final static RGBPixelData DEFAULT_PIXEL = new RGBPixelData(0);

    public RGBImageData(final BufferedImage bufferedImage) {
	super(bufferedImage.getWidth(), bufferedImage.getHeight());
	for (int x = 0; x < bufferedImage.getWidth(); x++) {
	    for (int y = 0; y < bufferedImage.getHeight(); y++) {
		set(x, y, new RGBPixelData(bufferedImage.getRGB(x, y)));
	    }
	}
    }

    public RGBImageData(final int width, final int height) {
	super(width, height);
    }

    @Override
    protected int getImageType() {
	return BufferedImage.TYPE_INT_RGB;
    }

    @Override
    public ImageData<RGBPixelData> cloneEmpty() {
	return new RGBImageData(getWidth(), getHeight());
    }

    @Override
    public RGBPixelData getDefaultPixel() {
	return DEFAULT_PIXEL;
    }
}
