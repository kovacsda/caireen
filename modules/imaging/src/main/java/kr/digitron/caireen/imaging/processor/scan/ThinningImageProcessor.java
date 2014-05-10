package kr.digitron.caireen.imaging.processor.scan;

import kr.digitron.caireen.imaging.data.EdgeImageData;
import kr.digitron.caireen.imaging.data.ImageData;
import kr.digitron.caireen.imaging.data.pixel.EdgePixelData;
import kr.digitron.caireen.imaging.processor.ImageProcessor;
import kr.digitron.caireen.imaging.util.PointUtil.AngleClass;

public class ThinningImageProcessor implements ImageProcessor<EdgePixelData, EdgePixelData> {

    @Override
    public ImageData<EdgePixelData> process(final ImageData<EdgePixelData> image) {
	EdgeImageData result = new EdgeImageData(image.getWidth() - 2, image.getHeight() - 2);
	for (int x = 1; x < image.getWidth() - 1; x++) {
	    for (int y = 1; y < image.getHeight() - 1; y++) {
		AngleClass angle = image.get(x, y).getAngleClass();
		int[] neighbourGrad = new int[2];
		int currentGrad = image.get(x, y).getGrad();
		switch (angle) {
		case ANGLE_CLASS_0:
		    neighbourGrad[0] = image.get(x - 1, y).getGrad();
		    neighbourGrad[1] = image.get(x + 1, y).getGrad();
		    break;
		case ANGLE_CLASS_45:
		    neighbourGrad[0] = image.get(x + 1, y - 1).getGrad();
		    neighbourGrad[1] = image.get(x - 1, y + 1).getGrad();
		    break;
		case ANGLE_CLASS_90:
		    neighbourGrad[0] = image.get(x, y - 1).getGrad();
		    neighbourGrad[1] = image.get(x, y + 1).getGrad();
		    break;
		case ANGLE_CLASS_135:
		    neighbourGrad[0] = image.get(x - 1, y - 1).getGrad();
		    neighbourGrad[1] = image.get(x + 1, y + 1).getGrad();
		    break;
		}

		if (neighbourGrad[0] >= currentGrad || neighbourGrad[1] > currentGrad) {
		    result.set(x - 1, y - 1, new EdgePixelData(0, angle));
		} else {
		    result.set(x - 1, y - 1, new EdgePixelData(currentGrad, angle));
		}
	    }
	}
	return result;
    }
}
