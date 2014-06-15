package kr.promu.caireen.imaging.processor.color;

import kr.promu.caireen.imaging.data.GrayscaleImageData;
import kr.promu.caireen.imaging.data.ImageData;
import kr.promu.caireen.imaging.data.pixel.GrayscalePixelData;
import kr.promu.caireen.imaging.data.pixel.PixelData;
import kr.promu.caireen.imaging.processor.ImageProcessor;

public class BlackWhiteImageProcessor extends ImageProcessor<ImageData<? extends PixelData>, ImageData<GrayscalePixelData>> {

    @Override
    public ImageData<GrayscalePixelData> process(final ImageData<? extends PixelData> image) {
	GrayscaleImageData result = new GrayscaleImageData(image.getWidth(), image.getHeight());
	PixelData defaultPixel = image.getDefaultPixel();
	for (int x = 0; x < image.getWidth(); x++) {
	    for (int y = 0; y < image.getHeight(); y++) {
		result.set(x, y, blackwhite(image.get(x, y), defaultPixel));
	    }
	}
	return result;
    }

    private GrayscalePixelData blackwhite(final PixelData input, final PixelData defaultPixel) {
	return new GrayscalePixelData(input.equals(defaultPixel) ? 0 : 255);
    }
}
