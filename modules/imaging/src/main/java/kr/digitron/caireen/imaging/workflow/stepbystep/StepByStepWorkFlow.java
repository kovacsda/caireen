package kr.digitron.caireen.imaging.workflow.stepbystep;

import java.awt.image.BufferedImage;

import javax.annotation.PostConstruct;

import kr.digitron.caireen.common.service.ServiceObserver;
import kr.digitron.caireen.imaging.data.ImageData;
import kr.digitron.caireen.imaging.data.RGBImageData;
import kr.digitron.caireen.imaging.data.pixel.EdgePixelData;
import kr.digitron.caireen.imaging.data.pixel.GrayscalePixelData;
import kr.digitron.caireen.imaging.data.pixel.RGBPixelData;
import kr.digitron.caireen.imaging.interceptor.ImageInterceptor;
import kr.digitron.caireen.imaging.processor.color.GrayscaleImageProcessor;
import kr.digitron.caireen.imaging.processor.scan.EdgeScanImageProcessor;
import kr.digitron.caireen.imaging.workflow.DefaultWorkflow;

public class StepByStepWorkFlow extends DefaultWorkflow<StepByStepImageProcessEvent> {

    private final GrayscaleImageProcessor grayscaleImageProcessor;
    private final EdgeScanImageProcessor edgeScanImageProcessor;

    public StepByStepWorkFlow(final ImageInterceptor imageInterceptor, final GrayscaleImageProcessor grayscaleImageProcessor,
	    final EdgeScanImageProcessor edgeScanImageProcessor) {
	super(imageInterceptor);
	this.grayscaleImageProcessor = grayscaleImageProcessor;
	this.edgeScanImageProcessor = edgeScanImageProcessor;
    }

    @PostConstruct
    public void init() {
	getImageInterceptor().registerObserver(new ServiceObserver<BufferedImage>() {

	    @Override
	    public void notify(final BufferedImage image) {
		long index = System.currentTimeMillis();
		ImageData<RGBPixelData> originalImage = new RGBImageData(image);
		fireEvent(new StepByStepImageProcessEvent(index, "Original", originalImage));
		ImageData<GrayscalePixelData> grayscaleImage = grayscaleImageProcessor.process(originalImage);
		fireEvent(new StepByStepImageProcessEvent(index, "Grayscale", grayscaleImage));
		ImageData<EdgePixelData> edgeImage = edgeScanImageProcessor.process(grayscaleImage);
		fireEvent(new StepByStepImageProcessEvent(index, "EdgeScan", edgeImage));
	    }
	});
    }
}
