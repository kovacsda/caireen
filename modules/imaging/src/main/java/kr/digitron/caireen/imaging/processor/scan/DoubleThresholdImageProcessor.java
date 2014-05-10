package kr.digitron.caireen.imaging.processor.scan;

import kr.digitron.caireen.imaging.data.EdgeImageData;
import kr.digitron.caireen.imaging.data.ImageData;
import kr.digitron.caireen.imaging.data.pixel.EdgePixelData;
import kr.digitron.caireen.imaging.processor.ImageProcessor;
import kr.digitron.caireen.imaging.util.PointUtil.AngleClass;

public class DoubleThresholdImageProcessor implements ImageProcessor<EdgePixelData, EdgePixelData> {

    private final static int GRAD_LOWER_THRESHOLD = 40;
    private final static int GRAD_HIGHER_THRESHOLD = (int) (GRAD_LOWER_THRESHOLD * 2.5);

    @Override
    public ImageData<EdgePixelData> process(final ImageData<EdgePixelData> image) {
	EdgeImageData result = new EdgeImageData(image.getWidth() - 2, image.getHeight() - 2);
	for (int x = 1; x < image.getWidth() - 1; x++) {
	    for (int y = 1; y < image.getHeight() - 1; y++) {
		AngleClass angle = image.get(x, y).getAngleClass();
		int currentGrad = image.get(x, y).getGrad();
		boolean drop;
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
		if (drop) {
		    result.set(x - 1, y - 1, new EdgePixelData(0, angle));
		} else {
		    result.set(x - 1, y - 1, new EdgePixelData(currentGrad, angle));
		}
	    }
	}
	return result;
    }
}
