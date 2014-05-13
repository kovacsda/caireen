package kr.promu.caireen.ui.pc.config;

import kr.promu.caireen.imaging.workflow.pipeline.PipelineWorkFlow;
import kr.promu.caireen.ui.pc.component.frame.ImageFrame;
import kr.promu.caireen.ui.pc.component.frame.ImageGridFrame;
import kr.promu.caireen.ui.pc.component.frame.ImageTileFrame;
import kr.promu.caireen.ui.pc.controller.ImageFrameController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UISpringConfiguration {

    @Bean
    public ImageFrameController imageGridController(final ImageFrame imageTileFrame, final PipelineWorkFlow pipelineWorkFlow) {
	return new ImageFrameController(imageTileFrame, pipelineWorkFlow);
    }

    @Bean
    public ImageTileFrame imageTileFrame() {
	return new ImageTileFrame(20, true);
    }

    @Bean
    public ImageGridFrame imageGridFrame() {
	return new ImageGridFrame();
    }
}
