package kr.digitron.caireen.ui.pc.config;

import kr.digitron.caireen.imaging.data.pixel.GrayscalePixelData;
import kr.digitron.caireen.imaging.interceptor.ImageInterceptor;
import kr.digitron.caireen.imaging.processor.color.BlackWhiteImageProcessor;
import kr.digitron.caireen.imaging.processor.color.GrayscaleImageProcessor;
import kr.digitron.caireen.imaging.processor.filter.DoubleThresholdImageProcessor;
import kr.digitron.caireen.imaging.processor.filter.IslandImageProcessor;
import kr.digitron.caireen.imaging.processor.filter.OneAngleImageProcessor;
import kr.digitron.caireen.imaging.processor.filter.ThinningImageProcessor;
import kr.digitron.caireen.imaging.processor.scan.EdgeScanImageProcessor;
import kr.digitron.caireen.imaging.processor.subtract.EdgeSubtractImageProcessor;
import kr.digitron.caireen.imaging.processor.subtract.GrayscaleSubtractImageProcessor;
import kr.digitron.caireen.imaging.util.PointUtil.AngleClass;
import kr.digitron.caireen.imaging.workflow.pipeline.PipelineWorkFlow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkFlowSpringConfiguration {

    @Bean
    public PipelineWorkFlow pipelineWorkFlow(final ImageInterceptor imageInterceptor) {
	return new PipelineWorkFlow(imageInterceptor);
    }

    @Bean
    public BlackWhiteImageProcessor blackWhiteImageProcessor() {
	return new BlackWhiteImageProcessor();
    }

    @Bean
    public IslandImageProcessor<GrayscalePixelData> bigIslandImageProcessor() {
	return new IslandImageProcessor<GrayscalePixelData>(5, 5);
    }

    @Bean
    public IslandImageProcessor<GrayscalePixelData> smallIslandImageProcessor() {
	return new IslandImageProcessor<GrayscalePixelData>(3, 3);
    }

    @Bean
    public IslandImageProcessor<GrayscalePixelData> oneIslandImageProcessor() {
	return new IslandImageProcessor<GrayscalePixelData>(1, 1);
    }

    @Bean
    public OneAngleImageProcessor oneAngleImageProcessor() {
	return new OneAngleImageProcessor(AngleClass.DEGREE_90);
    }

    @Bean
    public EdgeSubtractImageProcessor edgeSubtractImageProcessor() {
	return new EdgeSubtractImageProcessor();
    }

    @Bean
    public GrayscaleSubtractImageProcessor grayscaleSubtractImageProcessor() {
	return new GrayscaleSubtractImageProcessor();
    }

    @Bean
    public DoubleThresholdImageProcessor doubleThresholdImageProcessor() {
	return new DoubleThresholdImageProcessor();
    }

    @Bean
    public ThinningImageProcessor thinningImageProcessor() {
	return new ThinningImageProcessor();
    }

    @Bean
    public EdgeScanImageProcessor edgeScanImageProcessor() {
	return new EdgeScanImageProcessor();
    }

    @Bean
    public GrayscaleImageProcessor grayScaleImageProcessor() {
	return new GrayscaleImageProcessor();
    }
}
