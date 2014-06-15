package kr.promu.caireen.ui.pc.controller;

import javax.annotation.PostConstruct;
import javax.swing.JFrame;

import kr.promu.caireen.common.service.ServiceObserver;
import kr.promu.caireen.imaging.workflow.CounterProcessEvent;
import kr.promu.caireen.imaging.workflow.counter.CounterWorkFlow;
import kr.promu.caireen.ui.pc.component.frame.CounterFrame;

public class CounterFrameController {

    public CounterFrame frame;
    public CounterWorkFlow workFlow;

    public CounterFrameController(final CounterFrame frame, final CounterWorkFlow workFlow) {
	this.frame = frame;
	this.workFlow = workFlow;
    }

    @PostConstruct
    public void init() {
	workFlow.registerObserver(new ServiceObserver<CounterProcessEvent>() {
	    @Override
	    public void notify(final CounterProcessEvent event) {
		frame.addData(event.getTime(), event.getDropPosition());
	    }
	});
	frame.setVisible(true);
	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
