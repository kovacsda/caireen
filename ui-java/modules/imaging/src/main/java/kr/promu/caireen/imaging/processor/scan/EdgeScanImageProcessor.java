package kr.promu.caireen.imaging.processor.scan;

import kr.promu.caireen.imaging.data.EdgeImageData;
import kr.promu.caireen.imaging.data.ImageData;
import kr.promu.caireen.imaging.data.pixel.EdgePixelData;
import kr.promu.caireen.imaging.data.pixel.GrayscalePixelData;
import kr.promu.caireen.imaging.processor.ImageProcessor;
import kr.promu.caireen.imaging.util.PointUtil;
import kr.promu.caireen.imaging.util.PointUtil.AngleClass;

public class EdgeScanImageProcessor extends ImageProcessor<ImageData<GrayscalePixelData>, ImageData<EdgePixelData>> {

    private final static int GRAD_THRESHOLD = 0;

    @Override
    public ImageData<EdgePixelData> process(final ImageData<GrayscalePixelData> image) {
	EdgeImageData result = new EdgeImageData(image.getWidth() - 2, image.getHeight() - 2);
	for (int x = 1; x < image.getWidth() - 1; x++) {
	    for (int y = 1; y < image.getHeight() - 1; y++) {
		int difx = PointUtil.getGradX(image, x, y);
		int dify = PointUtil.getGradY(image, x, y);
		int grad = PointUtil.getGrad(difx, dify);
		grad = PointUtil.stretchColor(grad, GRAD_THRESHOLD);
		AngleClass angleClass = PointUtil.getAngleClass(difx, dify);
		result.set(x - 1, y - 1, new EdgePixelData(grad, angleClass));
	    }
	}
	return result;
    }
}
