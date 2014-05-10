package kr.digitron.caireen.ui.pc.main;

import kr.digitron.caireen.ui.pc.config.ImageInterceptionSpringConfiguration;
import kr.digitron.caireen.ui.pc.config.UISpringConfiguration;
import kr.digitron.caireen.ui.pc.config.WorkFlowSpringConfiguration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StartApp {

    public static void main(final String[] args) {
	ApplicationContext context = new AnnotationConfigApplicationContext(UISpringConfiguration.class, WorkFlowSpringConfiguration.class,
		ImageInterceptionSpringConfiguration.class);
    }
}
