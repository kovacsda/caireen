package kr.digitron.caireen.imaging.processor.image.subtract;

import java.awt.image.BufferedImage;

import kr.digitron.caireen.imaging.processor.image.ImageProcessor;

public class SubtractImageProcessor implements ImageProcessor {

    private BufferedImage previous;

    @Override
    public BufferedImage process(final BufferedImage image) {
	BufferedImage result = null;
	if (previous != null) {
	    result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
	    for (int x = 0; x < image.getWidth(); x++) {
		for (int y = 0; y < image.getHeight(); y++) {
		    result.setRGB(x, y, subtractRgb(previous.getRGB(x, y), image.getRGB(x, y)));
		}
	    }
	}
	previous = image;
	return result;
    }

    private int subtractRgb(final int previousRgb, final int actualRgb) {
	return 0;
    }
}
