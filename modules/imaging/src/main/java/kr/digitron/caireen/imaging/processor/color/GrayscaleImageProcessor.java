package kr.digitron.caireen.imaging.processor.color;

import kr.digitron.caireen.imaging.data.GrayscaleImageData;
import kr.digitron.caireen.imaging.data.ImageData;
import kr.digitron.caireen.imaging.data.pixel.GrayscalePixelData;
import kr.digitron.caireen.imaging.data.pixel.RGBPixelData;
import kr.digitron.caireen.imaging.processor.ImageProcessor;

public class GrayscaleImageProcessor implements ImageProcessor<RGBPixelData, GrayscalePixelData> {

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
