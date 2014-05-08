package kr.digitron.caireen.imaging.processor.image.subtract;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

import kr.digitron.caireen.imaging.processor.image.ImageProcessor;

public class SubtractImageProcessor implements ImageProcessor {

    private static final int MIN_DIFF = 20;
    private static final int MAX_DIFF = 80;

    private BufferedImage previous;

    @Override
    public BufferedImage process(final BufferedImage image) {
	BufferedImage result = null;
	if (previous != null) {
	    Raster previousRaster = previous.getData();
	    Raster actualRaster = image.getData();
	    result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
	    WritableRaster resultRaster = result.getRaster();
	    for (int x = 0; x < image.getWidth(); x++) {
		for (int y = 0; y < image.getHeight(); y++) {
		    int subtractR = subtractR(previousRaster.getSample(x, y, 0), actualRaster.getSample(x, y, 0));
		    resultRaster.setSample(x, y, 0, subtractR);
		}
	    }
	}
	previous = image;
	return result;
    }

    private int subtractR(final int previousGrayscale, final int actualGrayscale) {
	int differentR = Math.abs(previousGrayscale - actualGrayscale);
	return Math.max(0, Math.min((differentR - MIN_DIFF) * 255 / (MAX_DIFF - MIN_DIFF), 255));
    }
}
