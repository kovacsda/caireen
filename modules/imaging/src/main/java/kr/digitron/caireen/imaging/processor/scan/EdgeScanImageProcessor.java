package kr.digitron.caireen.imaging.processor.scan;

import kr.digitron.caireen.imaging.data.EdgeImageData;
import kr.digitron.caireen.imaging.data.ImageData;
import kr.digitron.caireen.imaging.data.pixel.EdgePixelData;
import kr.digitron.caireen.imaging.data.pixel.GrayscalePixelData;
import kr.digitron.caireen.imaging.processor.ImageProcessor;
import kr.digitron.caireen.imaging.util.PointUtil;
import kr.digitron.caireen.imaging.util.PointUtil.AngleClass;

public class EdgeScanImageProcessor implements ImageProcessor<GrayscalePixelData, EdgePixelData> {

    private final static int GRAD_THRESHOLD = 80;

    @Override
    public ImageData<EdgePixelData> process(final ImageData<GrayscalePixelData> image) {
	EdgeImageData result = new EdgeImageData(image.getWidth(), image.getHeight());
	for (int x = 1; x < image.getWidth() - 1; x++) {
	    for (int y = 1; y < image.getHeight() - 1; y++) {
		int difx = PointUtil.getGradX(image, x, y);
		int dify = PointUtil.getGradY(image, x, y);
		int grad = PointUtil.getGrad(difx, dify);
		grad = PointUtil.stretchColor(grad, GRAD_THRESHOLD);
		AngleClass angleClass = PointUtil.getAngleClass(difx, dify);
		result.set(x, y, new EdgePixelData(grad, angleClass));
	    }
	}
	return result;
    }
}
