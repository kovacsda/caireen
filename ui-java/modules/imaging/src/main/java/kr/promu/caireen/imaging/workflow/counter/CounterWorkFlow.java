package kr.promu.caireen.imaging.workflow.counter;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import kr.promu.caireen.common.interceptor.ImageInterceptor;
import kr.promu.caireen.common.service.ServiceObserver;
import kr.promu.caireen.common.workflow.DefaultWorkflow;
import kr.promu.caireen.imaging.workflow.CounterProcessEvent;
import kr.promu.caireen.jni.service.NativeImageProcessor;

public class CounterWorkFlow extends DefaultWorkflow<CounterProcessEvent> {

    @Resource
    public NativeImageProcessor nativeImageProcessor;

    public CounterWorkFlow(final ImageInterceptor imageInterceptor) {
	super(imageInterceptor);
    }

    @PostConstruct
    public void init() {
	getImageInterceptor().registerObserver(new ServiceObserver<BufferedImage>() {
	    @Override
	    public void notify(final BufferedImage image) {
		int dropCount = getDropCount(image);
		System.out.println(dropCount);
		fireEvent(dropCount);
	    }
	});
    }

    private int getDropCount(final BufferedImage image) {
	return nativeImageProcessor.processImage(((DataBufferByte) image.getRaster().getDataBuffer()).getData(), image.getWidth(),
		image.getHeight(), 0);
    }

    private void fireEvent(final int dropPosition) {
	fireEvent(new CounterProcessEvent(System.currentTimeMillis(), dropPosition));
    }
}
