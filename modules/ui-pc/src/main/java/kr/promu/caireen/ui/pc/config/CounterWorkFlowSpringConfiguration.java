package kr.promu.caireen.ui.pc.config;

import kr.promu.caireen.common.interceptor.ImageInterceptor;
import kr.promu.caireen.imaging.workflow.counter.CounterWorkFlow;
import kr.promu.caireen.jni.service.NativeImageProcessor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CounterWorkFlowSpringConfiguration {

    @Bean
    public CounterWorkFlow pipelineWorkFlow(final ImageInterceptor imageInterceptor) {
	return new CounterWorkFlow(imageInterceptor);
    }

    @Bean
    public NativeImageProcessor nativeImageProcessor() {
	return new NativeImageProcessor();
    }
}
