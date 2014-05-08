package kr.digitron.caireen.imaging.processor.image.scan;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

import kr.digitron.caireen.imaging.processor.image.ImageProcessor;
import kr.digitron.caireen.imaging.util.PointUtil;

public class EdgeScanImageProcessor implements ImageProcessor {

    @Override
    public BufferedImage process(final BufferedImage image) {
	Raster imageRaster = image.getData();
	BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
	WritableRaster resultRaster = result.getRaster();
	for (int x = 1; x < image.getWidth() - 1; x++) {
	    for (int y = 1; y < image.getHeight() - 1; y++) {
		int difx = PointUtil.getGradX(imageRaster, x, y);
		int dify = PointUtil.getGradY(imageRaster, x, y);
		int grad = PointUtil.getGrad(difx, dify);
		resultRaster.setSample(x, y, 0, grad);
	    }
	}
	return result;
    }
}
