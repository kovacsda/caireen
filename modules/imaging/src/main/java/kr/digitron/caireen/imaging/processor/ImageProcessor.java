package kr.digitron.caireen.imaging.processor;

import kr.digitron.caireen.imaging.data.ImageData;
import kr.digitron.caireen.imaging.data.pixel.PixelData;

/**
 * Process image and create a result.
 */
public abstract class ImageProcessor<I extends ImageData<? extends PixelData>, O extends ImageData<? extends PixelData>> {

    /**
     * Process image and create a result. The result can be null, if the
     * processor don't have enought inforamtion, to generate the result.
     * 
     * @param image
     *            input image
     * @return output image
     */
    public abstract O process(I image);

    public String getName() {
	return getClass().getSimpleName();
    }
}
