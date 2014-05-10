package kr.digitron.caireen.ui.pc.config;

import kr.digitron.caireen.imaging.interceptor.ImageInterceptor;
import kr.digitron.caireen.imaging.processor.color.GrayscaleImageProcessor;
import kr.digitron.caireen.imaging.processor.scan.DoubleThresholdImageProcessor;
import kr.digitron.caireen.imaging.processor.scan.EdgeScanImageProcessor;
import kr.digitron.caireen.imaging.processor.scan.ThinningImageProcessor;
import kr.digitron.caireen.imaging.workflow.stepbystep.StepByStepWorkFlow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkFlowSpringConfiguration {

    @Bean
    public StepByStepWorkFlow stepByStepWorkFlow(final ImageInterceptor imageInterceptor,
	    final GrayscaleImageProcessor grayscaleImageProcessor, final EdgeScanImageProcessor edgeScanImageProcessor,
	    final ThinningImageProcessor thinningImageProcessor, final DoubleThresholdImageProcessor doubleThresholdImageProcessor) {
	return new StepByStepWorkFlow(imageInterceptor, grayscaleImageProcessor, edgeScanImageProcessor, thinningImageProcessor,
		doubleThresholdImageProcessor);
    }

    @Bean
    public DoubleThresholdImageProcessor doubleThresholdImageProcessor() {
	return new DoubleThresholdImageProcessor();
    }

    @Bean
    public ThinningImageProcessor thinningImageProcessor() {
	return new ThinningImageProcessor();
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
