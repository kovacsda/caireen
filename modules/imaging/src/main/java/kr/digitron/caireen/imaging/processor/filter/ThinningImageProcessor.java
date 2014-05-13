package kr.digitron.caireen.imaging.processor.filter;

import kr.digitron.caireen.imaging.data.ImageData;
import kr.digitron.caireen.imaging.data.pixel.EdgePixelData;
import kr.digitron.caireen.imaging.util.PointUtil.AngleClass;

public class ThinningImageProcessor extends AreaFilterImageProcessor<EdgePixelData> {

    public ThinningImageProcessor() {
	super(1);
    }

    @Override
    protected boolean areaFilter(final int x, final int y, final ImageData<EdgePixelData> image) {
	boolean result;
	AngleClass angle = image.get(x, y).getAngleClass();
	int[] neighbourGrad = new int[2];
	int currentGrad = image.get(x, y).getGrad();
	switch (angle) {
	case DEGREE_0:
	    neighbourGrad[0] = image.get(x - 1, y).getGrad();
	    neighbourGrad[1] = image.get(x + 1, y).getGrad();
	    break;
	case DEGREE_45:
	    neighbourGrad[0] = image.get(x + 1, y - 1).getGrad();
	    neighbourGrad[1] = image.get(x - 1, y + 1).getGrad();
	    break;
	case DEGREE_90:
	    neighbourGrad[0] = image.get(x, y - 1).getGrad();
	    neighbourGrad[1] = image.get(x, y + 1).getGrad();
	    break;
	case DEGREE_135:
	    neighbourGrad[0] = image.get(x - 1, y - 1).getGrad();
	    neighbourGrad[1] = image.get(x + 1, y + 1).getGrad();
	    break;
	}
	result = neighbourGrad[0] >= currentGrad || neighbourGrad[1] > currentGrad;
	return result;
    }
}
