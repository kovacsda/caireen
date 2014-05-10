package kr.digitron.caireen.imaging.data;

import java.awt.image.BufferedImage;

import kr.digitron.caireen.imaging.data.pixel.EdgePixelData;

public class EdgeImageData extends ImageData<EdgePixelData> {

    public EdgeImageData(final int width, final int height) {
	super(width, height);
    }

    @Override
    protected int getImageType() {
	return BufferedImage.TYPE_INT_ARGB;
    }

}
