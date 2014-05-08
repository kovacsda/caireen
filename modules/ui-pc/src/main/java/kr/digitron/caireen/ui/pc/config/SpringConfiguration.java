package kr.digitron.caireen.ui.pc.config;

import kr.digitron.caireen.imaging.interceptor.ImageInterceptor;
import kr.digitron.caireen.imaging.interceptor.WebcamImageInterceptor;
import kr.digitron.caireen.imaging.processor.image.ImageProcessor;
import kr.digitron.caireen.imaging.processor.image.color.GrayScaleImageProcessor;
import kr.digitron.caireen.imaging.processor.image.scan.angle.GrayAngleScanImageProcessor;
import kr.digitron.caireen.imaging.processor.image.scan.edge.EdgeScanImageProcessor;
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
	    final ImageProcessor angleScanImageProcessor, final ImageProcessor subtractImageProcessor) {
	return new StepByStepWorkFlow(imageInterceptor, grayScaleImageProcessor, angleScanImageProcessor, subtractImageProcessor);
    }

    @Bean
    public ImageProcessor edgeScanImageProcessor() {
	return new EdgeScanImageProcessor();
    }

    @Bean
    public ImageProcessor angleScanImageProcessor() {
	return new GrayAngleScanImageProcessor();
    }

    @Bean
    public ImageProcessor grayScaleImageProcessor() {
	return new GrayScaleImageProcessor();
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
