package kr.digitron.caireen.imaging.processor.filter;

import kr.digitron.caireen.imaging.data.ImageData;
import kr.digitron.caireen.imaging.data.pixel.PixelData;
import kr.digitron.caireen.imaging.processor.ImageProcessor;

public abstract class FilterImageProcessor<T extends PixelData> extends ImageProcessor<ImageData<T>, ImageData<T>> {

    @Override
    public ImageData<T> process(final ImageData<T> image) {
	ImageData<T> result = image.cloneEmpty();
	T defaultPixel = image.getDefaultPixel();
	for (int x = 0; x < image.getWidth(); x++) {
	    for (int y = 0; y < image.getHeight(); y++) {
		if (!defaultPixel.equals(image.get(x, y))) {
		    if (filter(x, y, image)) {
			result.set(x, y, defaultPixel);
		    } else {
			result.set(x, y, image.get(x, y));
		    }
		}
	    }
	}
	return result;
    }

    protected abstract boolean filter(int x, int y, ImageData<T> image);
}
