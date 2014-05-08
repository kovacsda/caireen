package kr.digitron.caireen.imaging.processor.image.color;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

import kr.digitron.caireen.imaging.processor.image.ImageProcessor;

public class GrayscaleImageProcessor implements ImageProcessor {

    @Override
    public BufferedImage process(final BufferedImage image) {
	ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
	ColorConvertOp op = new ColorConvertOp(cs, null);
	return op.filter(image, null);
    }
}
