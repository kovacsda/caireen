package kr.promu.caireen.imaging.processor.filter;

import kr.promu.caireen.imaging.data.ImageData;
import kr.promu.caireen.imaging.data.pixel.EdgePixelData;

public class DoubleThresholdImageProcessor extends AreaFilterImageProcessor<EdgePixelData> {

    private final static int GRAD_LOWER_THRESHOLD = 25;
    private final static int GRAD_HIGHER_THRESHOLD = GRAD_LOWER_THRESHOLD * 2;

    public DoubleThresholdImageProcessor() {
	super(1);
    }

    @Override
    protected boolean areaFilter(final int x, final int y, final ImageData<EdgePixelData> image) {
	boolean drop;
	int currentGrad = image.get(x, y).getGrad();
	if (currentGrad < GRAD_LOWER_THRESHOLD) {
	    drop = true;
	} else if (currentGrad < GRAD_HIGHER_THRESHOLD) {
	    drop = true;
	    for (int dx = -1; dx <= 1 && drop; dx++) {
		for (int dy = -1; dy <= 1 && drop; dy++) {
		    if (image.get(x + dx, y + dy).getGrad() >= GRAD_HIGHER_THRESHOLD) {
			drop = false;
		    }
		}
	    }
	} else {
	    drop = false;
	}
	return drop;
    }
}
