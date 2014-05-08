package kr.digitron.caireen.imaging.processor.image.scan.edge;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

import kr.digitron.caireen.imaging.processor.image.ImageProcessor;

public class EdgeScanImageProcessor implements ImageProcessor {

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
		int dif = (int) Math.sqrt(difx * difx + dify * dify);
		resultRaster.setSample(x, y, 0, dif);
	    }
	}
	return result;
    }
}
