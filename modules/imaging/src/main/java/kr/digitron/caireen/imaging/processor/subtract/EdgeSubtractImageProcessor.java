package kr.digitron.caireen.imaging.processor.subtract;

import kr.digitron.caireen.imaging.data.pixel.EdgePixelData;
import kr.digitron.caireen.imaging.data.pixel.GrayscalePixelData;
import kr.digitron.caireen.imaging.util.PointUtil;

public class EdgeSubtractImageProcessor extends SubtractImageProcessor<EdgePixelData> {

    private static final int MIN_DIFF = 20;
    private static final int MAX_DIFF = 80;

    @Override
    protected GrayscalePixelData subtract(final EdgePixelData actual, final EdgePixelData previous) {
	return new GrayscalePixelData(PointUtil.stretchColor(Math.abs(actual.getGrad() - previous.getGrad()), MIN_DIFF, MAX_DIFF));
    }
}
