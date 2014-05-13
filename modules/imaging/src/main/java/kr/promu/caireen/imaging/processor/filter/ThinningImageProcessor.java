package kr.promu.caireen.imaging.processor.filter;

import kr.promu.caireen.imaging.data.ImageData;
import kr.promu.caireen.imaging.data.pixel.EdgePixelData;

public class ThinningImageProcessor extends AreaFilterImageProcessor<EdgePixelData> {

    public ThinningImageProcessor() {
	super(1);
    }

    @Override
    protected boolean areaFilter(final int x, final int y, final ImageData<EdgePixelData> image) {
	boolean result;
	int currentGrad = image.get(x, y).getGrad();
	switch (image.get(x, y).getAngleClass()) {
	case DEGREE_0:
	    result = checkNeighbours(currentGrad, image.get(x - 1, y), image.get(x + 1, y));
	    break;
	case DEGREE_45:
	    result = checkNeighbours(currentGrad, image.get(x + 1, y - 1), image.get(x - 1, y + 1));
	    break;
	case DEGREE_90:
	    result = checkNeighbours(currentGrad, image.get(x, y - 1), image.get(x, y + 1));
	    break;
	case DEGREE_135:
	    result = checkNeighbours(currentGrad, image.get(x - 1, y - 1), image.get(x + 1, y + 1));
	    break;
	default:
	    result = true;
	    break;
	}
	return result;
    }

    private boolean checkNeighbours(final int currentGrad, final EdgePixelData... pixelDatas) {
	boolean result = false;
	for (EdgePixelData pixelData : pixelDatas) {
	    if (pixelData.getGrad() > currentGrad) {
		result = true;
		break;
	    }
	}
	return result;
    }
}
