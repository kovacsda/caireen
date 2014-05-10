package kr.digitron.caireen.imaging.workflow;

import kr.digitron.caireen.common.service.DefaultObservableService;
import kr.digitron.caireen.imaging.interceptor.ImageInterceptor;

public abstract class DefaultWorkflow<E> extends DefaultObservableService<E> {

    private final ImageInterceptor imageInterceptor;

    public DefaultWorkflow(final ImageInterceptor imageInterceptor) {
	this.imageInterceptor = imageInterceptor;
    }

    protected ImageInterceptor getImageInterceptor() {
	return imageInterceptor;
    }
}
