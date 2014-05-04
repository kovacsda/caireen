package kr.digitron.caireen.ui.pc.config;

import kr.digitron.caireen.imaging.interceptor.ImageInterceptor;
import kr.digitron.caireen.imaging.interceptor.WebcamImageInterceptor;
import kr.digitron.caireen.imaging.processor.image.ImageProcessor;
import kr.digitron.caireen.imaging.processor.image.color.GrayScaleImageProcessor;
import kr.digitron.caireen.imaging.workflow.DefaultWorkflow;
import kr.digitron.caireen.imaging.workflow.stepbystep.StepByStepImageProcessEvent;
import kr.digitron.caireen.imaging.workflow.stepbystep.StepByStepWorkFlow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.sarxos.webcam.Webcam;

@Configuration
public class SpringConfiguration {

    @Bean
    public DefaultWorkflow<StepByStepImageProcessEvent> byStepWorkFlow(final ImageInterceptor imageInterceptor,
	    final ImageProcessor... imageProcessors) {
	return new StepByStepWorkFlow(imageInterceptor, imageProcessors);
    }

    @Bean
    public ImageProcessor imageProcessor() {
	return new GrayScaleImageProcessor();
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
