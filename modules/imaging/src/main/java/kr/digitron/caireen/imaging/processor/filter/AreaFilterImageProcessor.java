package kr.digitron.caireen.imaging.processor.filter;

import kr.digitron.caireen.imaging.data.ImageData;
import kr.digitron.caireen.imaging.data.pixel.PixelData;

public abstract class AreaFilterImageProcessor<T extends PixelData> extends FilterImageProcessor<T> {

    private final int aroundSize;

    public AreaFilterImageProcessor(final int aroundSize) {
	this.aroundSize = aroundSize;
    }

    protected int getAroundSize() {
	return aroundSize;
    }

    @Override
    protected boolean filter(final int x, final int y, final ImageData<T> image) {
	boolean result;
	if (x >= aroundSize && y >= aroundSize && x < image.getWidth() - aroundSize && y < image.getHeight() - aroundSize) {
	    result = areaFilter(x, y, image);
	} else {
	    result = false;
	}
	return result;
    }

    protected abstract boolean areaFilter(final int x, final int y, final ImageData<T> image);
}
