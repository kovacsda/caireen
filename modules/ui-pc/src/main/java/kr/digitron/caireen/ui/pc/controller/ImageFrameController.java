package kr.digitron.caireen.ui.pc.controller;

import javax.annotation.PostConstruct;
import javax.swing.JFrame;

import kr.digitron.caireen.common.service.ServiceObserver;
import kr.digitron.caireen.imaging.workflow.ImageProcessEvent;
import kr.digitron.caireen.imaging.workflow.pipeline.PipelineWorkFlow;
import kr.digitron.caireen.ui.pc.component.frame.ImageFrame;

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
