package kr.promu.caireen.imaging.processor.color;

import kr.promu.caireen.imaging.data.GrayscaleImageData;
import kr.promu.caireen.imaging.data.ImageData;
import kr.promu.caireen.imaging.data.pixel.GrayscalePixelData;
import kr.promu.caireen.imaging.data.pixel.RGBPixelData;
import kr.promu.caireen.imaging.processor.ImageProcessor;

public class GrayscaleImageProcessor extends ImageProcessor<ImageData<RGBPixelData>, ImageData<GrayscalePixelData>> {

    @Override
    public ImageData<GrayscalePixelData> process(final ImageData<RGBPixelData> image) {
	GrayscaleImageData result = new GrayscaleImageData(image.getWidth(), image.getHeight());
	for (int x = 0; x < image.getWidth(); x++) {
	    for (int y = 0; y < image.getHeight(); y++) {
		result.set(x, y, grayscale(image.get(x, y)));
	    }
	}
	return result;
    }

    private GrayscalePixelData grayscale(final RGBPixelData input) {
	int grayscale = (int) (input.getRed() * 0.2126 + input.getGreen() * 0.7152 + input.getBlue() * 0.0722);
	return new GrayscalePixelData(grayscale);
    }
}
