package kr.digitron.caireen.ui.pc.config;

import kr.digitron.caireen.imaging.interceptor.ImageInterceptor;
import kr.digitron.caireen.imaging.processor.color.GrayscaleImageProcessor;
import kr.digitron.caireen.imaging.processor.scan.EdgeScanImageProcessor;
import kr.digitron.caireen.imaging.workflow.stepbystep.StepByStepWorkFlow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkFlowSpringConfiguration {

    @Bean
    public StepByStepWorkFlow stepByStepWorkFlow(final ImageInterceptor imageInterceptor,
	    final GrayscaleImageProcessor grayscaleImageProcessor, final EdgeScanImageProcessor edgeScanImageProcessor) {
	return new StepByStepWorkFlow(imageInterceptor, grayscaleImageProcessor, edgeScanImageProcessor);
    }

    @Bean
    public EdgeScanImageProcessor edgeScanImageProcessor() {
	return new EdgeScanImageProcessor();
    }

    @Bean
    public GrayscaleImageProcessor grayScaleImageProcessor() {
	return new GrayscaleImageProcessor();
    }

    // @Bean
    // public SubtractImageProcessor subtractImageProcessor() {
    // return new SubtractImageProcessor();
    // }
}
