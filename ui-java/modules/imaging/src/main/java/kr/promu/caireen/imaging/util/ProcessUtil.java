package kr.promu.caireen.imaging.util;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;

public class ProcessUtil {

    private ProcessUtil() {
    }

    public static void processGrayscaleImage(final PixelGrayscaleCallBack callBack, final BufferedImage image) {
	Raster imageRaster = image.getData();
	for (int x = 0; x < image.getWidth(); x++) {
	    for (int y = 0; y < image.getHeight(); y++) {
		callBack.call(imageRaster.getSample(x, y, 0));
	    }
	}
    }

    public static interface PixelGrayscaleCallBack {
	void call(int y);
    }
}
