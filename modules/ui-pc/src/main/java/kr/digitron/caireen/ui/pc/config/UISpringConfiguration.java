package kr.digitron.caireen.ui.pc.config;

import kr.digitron.caireen.imaging.workflow.stepbystep.StepByStepWorkFlow;
import kr.digitron.caireen.ui.pc.component.frame.ImageFrame;
import kr.digitron.caireen.ui.pc.component.frame.ImageGridFrame;
import kr.digitron.caireen.ui.pc.component.frame.ImageTileFrame;
import kr.digitron.caireen.ui.pc.controller.ImageFrameController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UISpringConfiguration {

    @Bean
    public ImageFrameController imageGridController(final ImageFrame imageTileFrame, final StepByStepWorkFlow stepByStepWorkFlow) {
	return new ImageFrameController(imageTileFrame, stepByStepWorkFlow);
    }

    @Bean
    public ImageTileFrame imageTileFrame() {
	return new ImageTileFrame(3);
    }

    @Bean
    public ImageGridFrame imageGridFrame() {
	return new ImageGridFrame();
    }
}
