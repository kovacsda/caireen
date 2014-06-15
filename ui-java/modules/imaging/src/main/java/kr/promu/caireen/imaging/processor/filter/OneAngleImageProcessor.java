package kr.promu.caireen.imaging.processor.filter;

import kr.promu.caireen.imaging.data.ImageData;
import kr.promu.caireen.imaging.data.pixel.EdgePixelData;
import kr.promu.caireen.imaging.util.PointUtil.AngleClass;

public class OneAngleImageProcessor extends FilterImageProcessor<EdgePixelData> {

    private final AngleClass oneWay;

    public OneAngleImageProcessor(final AngleClass oneWay) {
	this.oneWay = oneWay;
    }

    @Override
    protected boolean filter(final int x, final int y, final ImageData<EdgePixelData> image) {
	return image.get(x, y).getAngleClass() != oneWay;
    }

    @Override
    public String getName() {
	return super.getName() + " (" + oneWay + ")";
    }
}
