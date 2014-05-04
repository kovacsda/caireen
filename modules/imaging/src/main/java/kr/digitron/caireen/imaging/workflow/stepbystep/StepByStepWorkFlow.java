package kr.digitron.caireen.imaging.workflow.stepbystep;

import java.awt.image.BufferedImage;

import javax.annotation.PostConstruct;

import kr.digitron.caireen.common.service.ServiceObserver;
import kr.digitron.caireen.imaging.interceptor.ImageInterceptor;
import kr.digitron.caireen.imaging.processor.image.ImageProcessor;
import kr.digitron.caireen.imaging.workflow.DefaultWorkflow;

public class StepByStepWorkFlow extends DefaultWorkflow<StepByStepImageProcessEvent> {

    public StepByStepWorkFlow(final ImageInterceptor imageInterceptor, final ImageProcessor... imageProcessors) {
	super(imageInterceptor, imageProcessors);
    }

    @PostConstruct
    public void init() {
	getImageInterceptor().registerObserver(new ServiceObserver<BufferedImage>() {

	    private int index = 0;

	    @Override
	    public void notify(final BufferedImage image) {
		BufferedImage work = image;
		for (ImageProcessor processor : getImageProcessors()) {
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
}
