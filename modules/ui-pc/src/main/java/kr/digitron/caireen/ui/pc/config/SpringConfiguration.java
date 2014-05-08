package kr.digitron.caireen.ui.pc.config;

import kr.digitron.caireen.imaging.interceptor.ImageInterceptor;
import kr.digitron.caireen.imaging.interceptor.WebcamImageInterceptor;
import kr.digitron.caireen.imaging.processor.image.ImageProcessor;
import kr.digitron.caireen.imaging.processor.image.color.GrayscaleImageProcessor;
import kr.digitron.caireen.imaging.processor.image.scan.EdgeScanImageProcessor;
import kr.digitron.caireen.imaging.processor.image.scan.GrayAngleScanImageProcessor;
import kr.digitron.caireen.imaging.processor.image.scan.RGBAngleScanImageProcessor;
import kr.digitron.caireen.imaging.processor.image.subtract.SubtractImageProcessor;
import kr.digitron.caireen.imaging.workflow.stepbystep.StepByStepWorkFlow;
import kr.digitron.caireen.ui.pc.component.ImageGridFrame;
import kr.digitron.caireen.ui.pc.controller.ImageGridController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.sarxos.webcam.Webcam;

@Configuration
public class SpringConfiguration {

    @Bean
    public ImageGridController imageGridController(final ImageGridFrame imageGridFrame, final StepByStepWorkFlow stepByStepWorkFlow) {
	return new ImageGridController(imageGridFrame, stepByStepWorkFlow);
    }

    @Bean
    public ImageGridFrame imageGridFrame() {
	return new ImageGridFrame();
    }

    @Bean
    public StepByStepWorkFlow stepByStepWorkFlow(final ImageInterceptor imageInterceptor, final ImageProcessor grayScaleImageProcessor,
	    final ImageProcessor rgbAngleScanImageProcessor, final ImageProcessor subtractImageProcessor) {
	return new StepByStepWorkFlow(imageInterceptor, grayScaleImageProcessor, rgbAngleScanImageProcessor, subtractImageProcessor);
    }

    @Bean
    public ImageProcessor edgeScanImageProcessor() {
	return new EdgeScanImageProcessor();
    }

    @Bean
    public ImageProcessor rgbAngleScanImageProcessor() {
	return new RGBAngleScanImageProcessor();
    }

    @Bean
    public ImageProcessor grayAngleScanImageProcessor() {
	return new GrayAngleScanImageProcessor();
    }

    @Bean
    public ImageProcessor grayScaleImageProcessor() {
	return new GrayscaleImageProcessor();
    }

    @Bean
    public ImageProcessor subtractImageProcessor() {
	return new SubtractImageProcessor();
    }

    @Bean
    public ImageInterceptor webcamImageInterceptor(final Webcam webcam) {
	return new WebcamImageInterceptor(webcam);
    }

    @Bean
    public Webcam webcam() {
	return Webcam.getDefault();
    }
}
