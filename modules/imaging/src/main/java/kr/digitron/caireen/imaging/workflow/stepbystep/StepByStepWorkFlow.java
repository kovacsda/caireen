package kr.digitron.caireen.imaging.workflow.stepbystep;

import java.awt.image.BufferedImage;

import javax.annotation.PostConstruct;

import kr.digitron.caireen.common.service.DefaultListenableService;
import kr.digitron.caireen.imaging.interceptor.CaptureImageListener;
import kr.digitron.caireen.imaging.interceptor.ImageInterceptor;
import kr.digitron.caireen.imaging.processor.image.ImageProcessor;

public class StepByStepWorkFlow extends DefaultListenableService<StepByStepImageProcessListener, StepByStepImageProcessEvent> {

    private final ImageInterceptor imageInterceptor;
    private final ImageProcessor[] imageProcessors;

    public StepByStepWorkFlow(final ImageInterceptor imageInterceptor, final ImageProcessor... imageProcessors) {
	this.imageInterceptor = imageInterceptor;
	this.imageProcessors = imageProcessors;
    }

    @PostConstruct
    public void init() {
	imageInterceptor.addListener(new CaptureImageListener() {
	    private int index = 0;

	    @Override
	    public void captureImage(final BufferedImage image) {
		BufferedImage work = image;
		for (ImageProcessor processor : imageProcessors) {
		    work = processor.process(work);
		    if (work == null) {
			break;
		    } else {
			fireEvent(new StepByStepImageProcessEvent(index, processor.getClass().getName(), work));
		    }
		}
		index++;
	    }
	});
    }

    @Override
    protected void callListener(final StepByStepImageProcessListener listener, final StepByStepImageProcessEvent event) {
	listener.processFinished(event);
    }
}
