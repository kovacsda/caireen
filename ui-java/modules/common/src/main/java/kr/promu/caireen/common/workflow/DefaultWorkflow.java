package kr.promu.caireen.common.workflow;

import kr.promu.caireen.common.interceptor.ImageInterceptor;
import kr.promu.caireen.common.service.DefaultObservableService;

public abstract class DefaultWorkflow<E> extends DefaultObservableService<E> {

    private final ImageInterceptor imageInterceptor;

    public DefaultWorkflow(final ImageInterceptor imageInterceptor) {
	this.imageInterceptor = imageInterceptor;
    }

    protected ImageInterceptor getImageInterceptor() {
	return imageInterceptor;
    }
}
