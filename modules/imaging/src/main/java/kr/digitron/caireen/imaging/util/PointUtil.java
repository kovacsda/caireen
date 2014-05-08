package kr.digitron.caireen.imaging.util;

import java.awt.image.Raster;

public class PointUtil {

    private static final float TAN022_5 = (float) Math.tan(22.5 / 180.0 * Math.PI);
    private static final float TAN067_5 = (float) Math.tan(67.5 / 180.0 * Math.PI);

    private PointUtil() {
    }

    public static int stretchColor(final int color, final int min, final int max) {
	return Math.max(0, Math.min((color - min) * 255 / (max - min), 255));
    }

    public static int getGrad(final int difx, final int dify) {
	return (int) Math.min(Math.sqrt(difx * difx + dify * dify), 255);
    }

    public static int getGradY(final Raster imageRaster, final int x, final int y) {
	int dify = imageRaster.getSample(x - 1, y - 1, 0) + 2 * imageRaster.getSample(x - 1, y, 0) + imageRaster.getSample(x - 1, y + 1, 0);
	dify -= imageRaster.getSample(x + 1, y - 1, 0) + 2 * imageRaster.getSample(x + 1, y, 0) + imageRaster.getSample(x + 1, y + 1, 0);
	return dify;
    }

    public static int getGradX(final Raster imageRaster, final int x, final int y) {
	int difx = imageRaster.getSample(x - 1, y - 1, 0) + 2 * imageRaster.getSample(x, y - 1, 0) + imageRaster.getSample(x + 1, y - 1, 0);
	difx -= imageRaster.getSample(x - 1, y + 1, 0) + 2 * imageRaster.getSample(x, y + 1, 0) + imageRaster.getSample(x + 1, y + 1, 0);
	return difx;
    }

    public static int getAngleClass(int difx, final int dify) {
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
