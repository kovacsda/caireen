package kr.digitron.caireen.imaging.processor;

import kr.digitron.caireen.imaging.data.ImageData;
import kr.digitron.caireen.imaging.data.pixel.PixelData;

/**
 * Process image and create a result.
 */
public interface ImageProcessor<I extends PixelData, O extends PixelData> {

    /**
     * Process image and create a result. The result can be null, if the
     * processor don't have enought inforamtion, to generate the result.
     * 
     * @param image
     *            input image
     * @return output image
     */
    ImageData<O> process(ImageData<I> image);
}
