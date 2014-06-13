package kr.promu.caireen.ui.pc.main;

import java.io.IOException;

import kr.promu.caireen.ui.pc.config.ImageInterceptionSpringConfiguration;
import kr.promu.caireen.ui.pc.config.ImageUISpringConfiguration;
import kr.promu.caireen.ui.pc.config.PipelineWorkFlowSpringConfiguration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class StartDebugApp {

    public static void main(final String[] args) throws IOException {
	AbstractApplicationContext context = new AnnotationConfigApplicationContext(ImageUISpringConfiguration.class,
		PipelineWorkFlowSpringConfiguration.class, ImageInterceptionSpringConfiguration.class);
	context.registerShutdownHook();
    }
}
