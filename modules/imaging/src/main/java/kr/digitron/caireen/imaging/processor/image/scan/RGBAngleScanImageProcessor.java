package kr.digitron.caireen.imaging.processor.image.scan;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

import kr.digitron.caireen.imaging.processor.image.ImageProcessor;
import kr.digitron.caireen.imaging.util.PointUtil;

public class RGBAngleScanImageProcessor implements ImageProcessor {

    @Override
    public BufferedImage process(final BufferedImage image) {
	Raster imageRaster = image.getData();
	BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
	for (int x = 1; x < image.getWidth() - 1; x++) {
	    for (int y = 1; y < image.getHeight() - 1; y++) {
		int difx = PointUtil.getGradX(imageRaster, x, y);
		int dify = PointUtil.getGradY(imageRaster, x, y);
		int grad = PointUtil.getGrad(difx, dify);
		int angleClass = PointUtil.getAngleClass(difx, dify);
		result.setRGB(x, y, getClassColor(grad, angleClass).getRGB());
	    }
	}
	return result;
    }

    private Color getClassColor(final int grad, final int angleClass) {
	Color color;
	switch (angleClass) {
	case 0:
	    color = new Color(grad, grad, 0);
	    break;
	case 1:
	    color = new Color(0, grad, 0);
	    break;
	case 2:
	    color = new Color(0, 0, grad);
	    break;
	case 3:
	    color = new Color(grad, 0, 0);
	    break;
	default:
	    color = new Color(grad, grad, grad);
	    break;
	}
	return color;
    }
}
