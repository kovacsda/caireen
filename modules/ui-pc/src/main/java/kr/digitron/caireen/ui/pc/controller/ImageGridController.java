package kr.digitron.caireen.ui.pc.controller;

import javax.annotation.PostConstruct;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import kr.digitron.caireen.common.service.ServiceObserver;
import kr.digitron.caireen.imaging.workflow.stepbystep.StepByStepImageProcessEvent;
import kr.digitron.caireen.imaging.workflow.stepbystep.StepByStepWorkFlow;
import kr.digitron.caireen.ui.pc.component.ImageGridFrame;

public class ImageGridController {

    public ImageGridFrame frame;
    public StepByStepWorkFlow workFlow;

    public ImageGridController(final ImageGridFrame frame, final StepByStepWorkFlow workFlow) {
	this.frame = frame;
	this.workFlow = workFlow;
    }

    @PostConstruct
    public void init() {
	workFlow.registerObserver(new ServiceObserver<StepByStepImageProcessEvent>() {
	    @Override
	    public void notify(final StepByStepImageProcessEvent event) {
		frame.addData(String.valueOf(event.getImageId()), event.getProcessorName(), new JLabel(new ImageIcon(event.getImage())));
	    }
	});
	frame.pack();
	frame.setVisible(true);
    }
}
