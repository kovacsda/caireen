package kr.digitron.caireen.imaging.processor.image.color;

import java.awt.image.BufferedImage;

import kr.digitron.caireen.imaging.processor.image.ImageProcessor;

public class GrayScaleImageProcessor implements ImageProcessor {

    @Override
    public BufferedImage process(final BufferedImage image) {
	BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
	for (int x = 0; x < image.getWidth(); x++) {
	    for (int y = 0; y < image.getWidth(); y++) {
		int rgb = image.getRGB(x, y);
		int g = (rgb & 0xFF + rgb >> 8 & 0xFF + rgb >> 16 & 0xFF) / 3;
		result.setRGB(x, y, g);
	    }
	}
	return result;
    }
}
