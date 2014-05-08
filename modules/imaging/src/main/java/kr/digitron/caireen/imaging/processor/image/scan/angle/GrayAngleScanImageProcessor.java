package kr.digitron.caireen.imaging.processor.image.scan.angle;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

import kr.digitron.caireen.imaging.processor.image.ImageProcessor;

public class GrayAngleScanImageProcessor implements ImageProcessor {

    private static final float TAN022_5 = (float) Math.tan(22.5 / 180.0 * Math.PI);
    private static final float TAN067_5 = (float) Math.tan(67.5 / 180.0 * Math.PI);

    @Override
    public BufferedImage process(final BufferedImage image) {
	Raster imageRaster = image.getData();
	BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
	WritableRaster resultRaster = result.getRaster();
	for (int x = 1; x < image.getWidth() - 1; x++) {
	    for (int y = 1; y < image.getHeight() - 1; y++) {
		int difx = imageRaster.getSample(x - 1, y - 1, 0) + 2 * imageRaster.getSample(x, y - 1, 0)
			+ imageRaster.getSample(x + 1, y - 1, 0);
		difx -= imageRaster.getSample(x - 1, y + 1, 0) + 2 * imageRaster.getSample(x, y + 1, 0)
			+ imageRaster.getSample(x + 1, y + 1, 0);
		int dify = imageRaster.getSample(x - 1, y - 1, 0) + 2 * imageRaster.getSample(x - 1, y, 0)
			+ imageRaster.getSample(x - 1, y + 1, 0);
		dify -= imageRaster.getSample(x + 1, y - 1, 0) + 2 * imageRaster.getSample(x + 1, y, 0)
			+ imageRaster.getSample(x + 1, y + 1, 0);
		// int dif = Math.min(Math.abs(difx) + Math.abs(dify), 255);
		int angle = angle(difx, dify) * 50;
		int grad = (int) Math.sqrt(difx * difx + dify * dify);
		resultRaster.setSample(x, y, 0, grad < 40 ? 0 : (angle + 2) * 50);
	    }
	}
	return result;
    }

    private int angle(int difx, final int dify) {
	if (dify == 0) {
	    return 0;
	}
	if (difx == 0) {
	    return 2;
	}

	float h = (float) dify / (float) difx; // normalize difx to 1
	if (dify < 0) {
	    difx = -difx; /* h=-h; */
	} // rotate 180 if required

	if (difx < 0) { // left side
	    h = -h;
	    if (h < TAN022_5) {
		return 0;
	    }
	    if (h < TAN067_5) {
		return 3;
	    }
	    return 2;
	} else { // right side
	    if (h > TAN067_5) {
		return 2;
	    } else if (h > TAN022_5) {
		return 1;
	    }
	    return 0;
	}
    }
}
