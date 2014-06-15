package kr.promu.caireen.imaging.workflow.counter;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.swing.JOptionPane;

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
	JOptionPane.showMessageDialog(null, "Tibi vazzeg!!!");
	byte[] imageData = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
	DataBufferByte requestData = new DataBufferByte(imageData.length + 4 * 5);
	requestData.setElem(0, 1);
	requestData.setElem(4, image.getWidth());
	requestData.setElem(8, image.getHeight());
	requestData.setElem(12, image.getWidth());
	requestData.setElem(16, 0); // format grayscale8
	for (int i = 0; i < imageData.length; i++) {
	    requestData.getData()[i + 4 * 5] = imageData[i];
	}
	byte[] result = nativeImageProcessor.request(requestData.getData());
	DataBufferByte resultData = new DataBufferByte(result, result.length);
	System.out.println(resultData.getElem(0));
	return 0;
    }

    private void fireEvent(final int dropPosition) {
	fireEvent(new CounterProcessEvent(System.currentTimeMillis(), dropPosition));
    }
}
