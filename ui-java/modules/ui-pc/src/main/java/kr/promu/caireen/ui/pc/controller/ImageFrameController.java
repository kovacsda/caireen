package kr.promu.caireen.ui.pc.controller;

import javax.annotation.PostConstruct;
import javax.swing.JFrame;

import kr.promu.caireen.common.service.ServiceObserver;
import kr.promu.caireen.imaging.workflow.ImageProcessEvent;
import kr.promu.caireen.imaging.workflow.pipeline.PipelineWorkFlow;
import kr.promu.caireen.ui.pc.component.frame.ImageFrame;

public class ImageFrameController {

    public ImageFrame frame;
    public PipelineWorkFlow workFlow;

    public ImageFrameController(final ImageFrame frame, final PipelineWorkFlow workFlow) {
	this.frame = frame;
	this.workFlow = workFlow;
    }

    @PostConstruct
    public void init() {
	workFlow.registerObserver(new ServiceObserver<ImageProcessEvent>() {
	    @Override
	    public void notify(final ImageProcessEvent event) {
		frame.addData(event.getTime(), event.getProcessorName(), event.getImage());
	    }
	});
	frame.setVisible(true);
	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
