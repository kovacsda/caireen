package kr.promu.caireen.imaging.workflow.pipeline;

import java.awt.image.BufferedImage;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import kr.promu.caireen.common.service.ServiceObserver;
import kr.promu.caireen.imaging.data.ImageData;
import kr.promu.caireen.imaging.data.RGBImageData;
import kr.promu.caireen.imaging.data.pixel.EdgePixelData;
import kr.promu.caireen.imaging.data.pixel.GrayscalePixelData;
import kr.promu.caireen.imaging.data.pixel.PixelData;
import kr.promu.caireen.imaging.data.pixel.RGBPixelData;
import kr.promu.caireen.imaging.interceptor.ImageInterceptor;
import kr.promu.caireen.imaging.processor.ImageProcessor;
import kr.promu.caireen.imaging.processor.color.BlackWhiteImageProcessor;
import kr.promu.caireen.imaging.processor.color.GrayscaleImageProcessor;
import kr.promu.caireen.imaging.processor.filter.DoubleThresholdImageProcessor;
import kr.promu.caireen.imaging.processor.filter.IslandImageProcessor;
import kr.promu.caireen.imaging.processor.filter.OneAngleImageProcessor;
import kr.promu.caireen.imaging.processor.filter.ThinningImageProcessor;
import kr.promu.caireen.imaging.processor.scan.EdgeScanImageProcessor;
import kr.promu.caireen.imaging.processor.subtract.EdgeSubtractImageProcessor;
import kr.promu.caireen.imaging.processor.subtract.GrayscaleSubtractImageProcessor;
import kr.promu.caireen.imaging.workflow.DefaultWorkflow;
import kr.promu.caireen.imaging.workflow.ImageProcessEvent;

public class PipelineWorkFlow extends DefaultWorkflow<ImageProcessEvent> {

    @Resource
    private GrayscaleImageProcessor grayscaleImageProcessor;
    @Resource
    private EdgeScanImageProcessor edgeScanImageProcessor;
    @Resource
    private ThinningImageProcessor thinningImageProcessor;
    @Resource
    private DoubleThresholdImageProcessor doubleThresholdImageProcessor;
    @Resource
    private EdgeSubtractImageProcessor edgeSubtractImageProcessor;
    @Resource
    private GrayscaleSubtractImageProcessor grayscaleSubtractImageProcessor;
    @Resource
    private OneAngleImageProcessor oneAngleImageProcessor;
    @Resource
    private IslandImageProcessor<GrayscalePixelData> oneIslandImageProcessor;
    @Resource
    private IslandImageProcessor<GrayscalePixelData> smallIslandImageProcessor;
    @Resource
    private IslandImageProcessor<GrayscalePixelData> bigIslandImageProcessor;
    @Resource
    private BlackWhiteImageProcessor blackWhiteImageProcessor;

    public PipelineWorkFlow(final ImageInterceptor imageInterceptor) {
	super(imageInterceptor);
    }

    @PostConstruct
    public void init() {
	getImageInterceptor().registerObserver(new ServiceObserver<BufferedImage>() {

	    @Override
	    public void notify(final BufferedImage image) {
		process(new RGBImageData(image));
	    }
	});
    }

    private void process(final ImageData<RGBPixelData> image) {
	fireEvent("Original", image);

	ImageData<GrayscalePixelData> grayImage;
	ImageData<EdgePixelData> edgeImage;
	try {
	    grayImage = step("01", grayscaleImageProcessor, image);
	    edgeImage = step("02", edgeScanImageProcessor, grayImage);
	    edgeImage = step("03", oneAngleImageProcessor, edgeImage);
	    edgeImage = step("04", doubleThresholdImageProcessor, edgeImage);
	    edgeImage = step("05", thinningImageProcessor, edgeImage);
	    grayImage = step("06", blackWhiteImageProcessor, edgeImage);
	    grayImage = step("07", oneIslandImageProcessor, grayImage);
	    grayImage = step("08", smallIslandImageProcessor, grayImage);
	    grayImage = step("09", bigIslandImageProcessor, grayImage);
	    grayImage = step("10", grayscaleSubtractImageProcessor, grayImage);
	    grayImage = step("11", oneIslandImageProcessor, grayImage);
	    grayImage = step("12", smallIslandImageProcessor, grayImage);
	    grayImage = step("13", bigIslandImageProcessor, grayImage);
	    edgeImage = step("14", edgeScanImageProcessor, grayImage);
	    edgeImage = step("15", oneAngleImageProcessor, edgeImage);
	    edgeImage = step("16", doubleThresholdImageProcessor, edgeImage);
	    edgeImage = step("17", thinningImageProcessor, edgeImage);
	    grayImage = step("18", blackWhiteImageProcessor, edgeImage);
	    grayImage = step("19", oneIslandImageProcessor, grayImage);
	    grayImage = step("20", smallIslandImageProcessor, grayImage);
	    grayImage = step("21", bigIslandImageProcessor, grayImage);
	} catch (PipelineException exception) {
	    exception.printStackTrace(System.out);
	}
    }

    private <I extends ImageData<? extends PixelData>, O extends ImageData<? extends PixelData>> O step(final String prefix,
	    final ImageProcessor<I, O> processor, final I input) {
	O result = processor.process(input);
	if (result == null) {
	    throw new PipelineException();
	}
	fireEvent(prefix + " - " + processor.getName(), result);
	return result;
    }

    private void fireEvent(final String processor, final ImageData<?> originalImage) {
	fireEvent(new ImageProcessEvent(System.currentTimeMillis(), processor, originalImage));
    }
}
