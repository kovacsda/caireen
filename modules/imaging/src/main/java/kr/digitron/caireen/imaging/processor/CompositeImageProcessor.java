package kr.digitron.caireen.imaging.processor;

import java.awt.image.BufferedImage;

public class CompositeImageProcessor implements ImageProcessor {

    private final ImageProcessor[] processors;

    public CompositeImageProcessor(final ImageProcessor... processors) {
	this.processors = processors;
    }

    @Override
    public BufferedImage process(final BufferedImage image) {
	BufferedImage work = image;
	for (ImageProcessor processor : processors) {
	    work = processor.process(work);
	    if (work == null) {
		break;
	    }
	}
	return work;
    }
}
