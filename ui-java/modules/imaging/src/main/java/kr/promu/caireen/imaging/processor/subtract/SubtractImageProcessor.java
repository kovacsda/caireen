package kr.promu.caireen.imaging.processor.subtract;

import kr.promu.caireen.imaging.data.GrayscaleImageData;
import kr.promu.caireen.imaging.data.ImageData;
import kr.promu.caireen.imaging.data.pixel.GrayscalePixelData;
import kr.promu.caireen.imaging.data.pixel.PixelData;
import kr.promu.caireen.imaging.processor.ImageProcessor;

public abstract class SubtractImageProcessor<T extends PixelData> extends ImageProcessor<ImageData<T>, ImageData<GrayscalePixelData>> {

    private ImageData<T> previous;

    @Override
    public ImageData<GrayscalePixelData> process(final ImageData<T> image) {
	ImageData<GrayscalePixelData> result = null;
	if (previous != null) {
	    result = new GrayscaleImageData(image.getWidth(), image.getHeight());
	    for (int x = 0; x < image.getWidth(); x++) {
		for (int y = 0; y < image.getHeight(); y++) {
		    result.set(x, y, subtract(image.get(x, y), previous.get(x, y)));
		}
	    }
	}
	previous = image;
	return result;
    }

    protected abstract GrayscalePixelData subtract(final T actual, final T previous);
}
