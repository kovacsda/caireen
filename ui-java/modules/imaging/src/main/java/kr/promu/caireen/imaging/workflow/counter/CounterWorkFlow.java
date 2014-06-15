package kr.promu.caireen.imaging.workflow.counter;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.DataBufferByte;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import kr.promu.caireen.common.interceptor.ImageInterceptor;
import kr.promu.caireen.common.service.ServiceObserver;
import kr.promu.caireen.common.workflow.DefaultWorkflow;
import kr.promu.caireen.imaging.workflow.CounterProcessEvent;
import kr.promu.caireen.jni.data.ByteArray;
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
	byte[] imageData = getGrayScaleImage(image);
	ByteArray requestData = generateRequest(image, imageData);
	byte[] result = nativeImageProcessor.request(requestData.getDataArray());
	DataBufferByte resultData = new DataBufferByte(result, result.length);
	System.out.println(resultData.getElem(0));
	return 0;
    }

    private ByteArray generateRequest(final BufferedImage image, final byte[] imageData) {
	ByteArray requestData = new ByteArray(imageData.length + 20);
	requestData.setData(0, 1);
	requestData.setData(4, image.getWidth());
	requestData.setData(8, image.getHeight());
	requestData.setData(12, image.getWidth());
	requestData.setData(16, 0); // format gray scale
	requestData.setData(20, imageData);
	return requestData;
    }

    private byte[] getGrayScaleImage(final BufferedImage image) {
	ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
	ColorConvertOp op = new ColorConvertOp(cs, null);
	BufferedImage grayScaleImage = op.filter(image, null);
	byte[] imageData = ((DataBufferByte) grayScaleImage.getRaster().getDataBuffer()).getData();
	return imageData;
    }

    private void fireEvent(final int dropPosition) {
	fireEvent(new CounterProcessEvent(System.currentTimeMillis(), dropPosition));
    }
}
