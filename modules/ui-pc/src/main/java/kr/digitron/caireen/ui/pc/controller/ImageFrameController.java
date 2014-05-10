package kr.digitron.caireen.ui.pc.controller;

import javax.annotation.PostConstruct;
import javax.swing.JFrame;

import kr.digitron.caireen.common.service.ServiceObserver;
import kr.digitron.caireen.imaging.workflow.stepbystep.StepByStepImageProcessEvent;
import kr.digitron.caireen.imaging.workflow.stepbystep.StepByStepWorkFlow;
import kr.digitron.caireen.ui.pc.component.frame.ImageFrame;

public class ImageFrameController {

    public ImageFrame frame;
    public StepByStepWorkFlow workFlow;

    public ImageFrameController(final ImageFrame frame, final StepByStepWorkFlow workFlow) {
	this.frame = frame;
	this.workFlow = workFlow;
    }

    @PostConstruct
    public void init() {
	workFlow.registerObserver(new ServiceObserver<StepByStepImageProcessEvent>() {
	    @Override
	    public void notify(final StepByStepImageProcessEvent event) {
		frame.addData(event.getImageId(), event.getProcessorName(), event.getImage());
	    }
	});
	frame.setVisible(true);
	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
