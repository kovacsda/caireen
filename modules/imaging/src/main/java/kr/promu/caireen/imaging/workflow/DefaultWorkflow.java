package kr.promu.caireen.imaging.workflow;

import kr.promu.caireen.common.service.DefaultObservableService;
import kr.promu.caireen.imaging.interceptor.ImageInterceptor;

public abstract class DefaultWorkflow<E> extends DefaultObservableService<E> {

    private final ImageInterceptor imageInterceptor;

    public DefaultWorkflow(final ImageInterceptor imageInterceptor) {
	this.imageInterceptor = imageInterceptor;
    }

    protected ImageInterceptor getImageInterceptor() {
	return imageInterceptor;
    }
}
