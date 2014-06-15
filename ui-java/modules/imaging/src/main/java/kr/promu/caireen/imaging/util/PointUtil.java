package kr.promu.caireen.imaging.util;

import kr.promu.caireen.imaging.data.ImageData;
import kr.promu.caireen.imaging.data.pixel.GrayscalePixelData;

public class PointUtil {

    private final static float TAN022_5 = (float) Math.tan(22.5 / 180.0 * Math.PI);
    private final static float TAN067_5 = (float) Math.tan(67.5 / 180.0 * Math.PI);

    private final static int COLOR_MAX = 255;

    private PointUtil() {
    }

    public static int stretchColor(final int color, final int min) {
	return stretchColor(color, min, COLOR_MAX);
    }

    public static int stretchColor(final int color, final int min, final int max) {
	return Math.max(0, Math.min((color - min) * COLOR_MAX / (max - min), COLOR_MAX));
    }

    public static int getGrad(final int difx, final int dify) {
	return (int) Math.min(Math.sqrt(difx * difx + dify * dify), COLOR_MAX);
    }

    public static int getGradY(final ImageData<GrayscalePixelData> image, final int x, final int y) {
	int dify = image.get(x - 1, y - 1).getGrayscale() + 2 * image.get(x - 1, y).getGrayscale() + image.get(x - 1, y + 1).getGrayscale();
	dify -= image.get(x + 1, y - 1).getGrayscale() + 2 * image.get(x + 1, y).getGrayscale() + image.get(x + 1, y + 1).getGrayscale();
	return dify;
    }

    public static int getGradX(final ImageData<GrayscalePixelData> image, final int x, final int y) {
	int difx = image.get(x - 1, y - 1).getGrayscale() + 2 * image.get(x, y - 1).getGrayscale() + image.get(x + 1, y - 1).getGrayscale();
	difx -= image.get(x - 1, y + 1).getGrayscale() + 2 * image.get(x, y + 1).getGrayscale() + image.get(x + 1, y + 1).getGrayscale();
	return difx;
    }

    public static AngleClass getAngleClass(int difx, final int dify) {
	if (dify == 0) {
	    return AngleClass.DEGREE_90;
	}
	if (difx == 0) {
	    return AngleClass.DEGREE_45;
	}

	float h = (float) dify / (float) difx; // normalize difx to 1
	if (dify < 0) {
	    difx = -difx; /* h=-h; */
	} // rotate 180 if required

	if (difx < 0) { // left side
	    h = -h;
	    if (h < TAN022_5) {
		return AngleClass.DEGREE_90;
	    }
	    if (h < TAN067_5) {
		return AngleClass.DEGREE_0;
	    }
	    return AngleClass.DEGREE_45;
	} else { // right side
	    if (h > TAN067_5) {
		return AngleClass.DEGREE_45;
	    } else if (h > TAN022_5) {
		return AngleClass.DEGREE_135;
	    }
	    return AngleClass.DEGREE_90;
	}
    }

    public static enum AngleClass {
	DEGREE_0, DEGREE_45, DEGREE_90, DEGREE_135, UNDEFINED;
    }
}
