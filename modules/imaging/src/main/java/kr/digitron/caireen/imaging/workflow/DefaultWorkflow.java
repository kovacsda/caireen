package kr.digitron.caireen.imaging.workflow;

import kr.digitron.caireen.common.service.DefaultObservableService;
import kr.digitron.caireen.imaging.interceptor.ImageInterceptor;
import kr.digitron.caireen.imaging.processor.image.ImageProcessor;

public abstract class DefaultWorkflow<E> extends DefaultObservableService<E> {

    private final ImageInterceptor imageInterceptor;
    private final ImageProcessor[] imageProcessors;

    public DefaultWorkflow(final ImageInterceptor imageInterceptor, final ImageProcessor... imageProcessors) {
	this.imageInterceptor = imageInterceptor;
	this.imageProcessors = imageProcessors;
    }

    protected ImageInterceptor getImageInterceptor() {
	return imageInterceptor;
    }

    protected ImageProcessor[] getImageProcessors() {
	return imageProcessors;
    }
}
