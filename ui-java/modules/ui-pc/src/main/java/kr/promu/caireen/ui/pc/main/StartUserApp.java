package kr.promu.caireen.ui.pc.main;

import java.io.IOException;

import kr.promu.caireen.ui.pc.config.CounterUISpringConfiguration;
import kr.promu.caireen.ui.pc.config.CounterWorkFlowSpringConfiguration;
import kr.promu.caireen.ui.pc.config.ImageInterceptionSpringConfiguration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class StartUserApp {

    public static void main(final String[] args) throws IOException {
	AbstractApplicationContext context = new AnnotationConfigApplicationContext(ImageInterceptionSpringConfiguration.class,
		CounterWorkFlowSpringConfiguration.class, CounterUISpringConfiguration.class);
	context.registerShutdownHook();
    }
}
