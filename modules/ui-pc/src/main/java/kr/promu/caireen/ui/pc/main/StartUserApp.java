package kr.promu.caireen.ui.pc.main;

import java.io.IOException;

import kr.promu.caireen.ui.pc.config.ImageInterceptionSpringConfiguration;
import kr.promu.caireen.ui.pc.config.UISpringConfiguration;
import kr.promu.caireen.ui.pc.config.WorkFlowSpringConfiguration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class StartUserApp {

    public static void main(final String[] args) throws IOException {
	AbstractApplicationContext context = new AnnotationConfigApplicationContext(UISpringConfiguration.class,
		WorkFlowSpringConfiguration.class, ImageInterceptionSpringConfiguration.class);
	context.registerShutdownHook();
    }
}
