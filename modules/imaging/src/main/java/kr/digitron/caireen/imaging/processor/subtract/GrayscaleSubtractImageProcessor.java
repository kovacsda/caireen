package kr.digitron.caireen.imaging.processor.subtract;

import kr.digitron.caireen.imaging.data.pixel.GrayscalePixelData;
import kr.digitron.caireen.imaging.util.PointUtil;

public class GrayscaleSubtractImageProcessor extends SubtractImageProcessor<GrayscalePixelData> {

    private static final int MIN_DIFF = 20;
    private static final int MAX_DIFF = 80;

    @Override
    protected GrayscalePixelData subtract(final GrayscalePixelData actual, final GrayscalePixelData previous) {
	return new GrayscalePixelData(PointUtil.stretchColor(Math.abs(actual.getGrayscale() - previous.getGrayscale()), MIN_DIFF, MAX_DIFF));
    }
}
