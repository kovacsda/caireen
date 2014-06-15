package kr.promu.caireen.ui.pc.config;

import kr.promu.caireen.imaging.workflow.counter.CounterWorkFlow;
import kr.promu.caireen.ui.pc.component.frame.CounterFrame;
import kr.promu.caireen.ui.pc.component.frame.DropCounterFrame;
import kr.promu.caireen.ui.pc.controller.CounterFrameController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CounterUISpringConfiguration {

    @Bean
    public CounterFrameController imageGridController(final CounterFrame dropCounterFrame, final CounterWorkFlow counterWorkFlow) {
	return new CounterFrameController(dropCounterFrame, counterWorkFlow);
    }

    @Bean
    public CounterFrame dropCounterFrame() {
	return new DropCounterFrame();
    }
}
