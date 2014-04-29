package kr.digitron.caireen.imaging.workflow;

import kr.digitron.caireen.imaging.interceptor.ImageInterceptor;
import kr.digitron.caireen.imaging.processor.image.ImageProcessor;

// TODO Extend lisenable service
// TODO Rename Observable
// TODO Use generic listener
public class DefaultWorkflow {

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
