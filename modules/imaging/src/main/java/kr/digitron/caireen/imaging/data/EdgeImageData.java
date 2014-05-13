package kr.digitron.caireen.imaging.data;

import java.awt.image.BufferedImage;

import kr.digitron.caireen.imaging.data.pixel.EdgePixelData;
import kr.digitron.caireen.imaging.util.PointUtil.AngleClass;

public class EdgeImageData extends ImageData<EdgePixelData> {

    private final static EdgePixelData DEFAULT_PIXEL = new EdgePixelData(0, AngleClass.UNDEFINED);

    public EdgeImageData(final int width, final int height) {
	super(width, height);
    }

    @Override
    protected int getImageType() {
	return BufferedImage.TYPE_INT_RGB;
    }

    @Override
    public ImageData<EdgePixelData> cloneEmpty() {
	return new EdgeImageData(getWidth(), getHeight());
    }

    @Override
    public EdgePixelData getDefaultPixel() {
	return DEFAULT_PIXEL;
    }
}
