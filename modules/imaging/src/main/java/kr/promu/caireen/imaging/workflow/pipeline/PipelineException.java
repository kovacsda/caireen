package kr.promu.caireen.imaging.workflow.pipeline;

public class PipelineException extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;

    public PipelineException() {
    }

    public PipelineException(final String s) {
	super(s);
    }

    public PipelineException(final Throwable cause) {
	super(cause);
    }

    public PipelineException(final String message, final Throwable cause) {
	super(message, cause);
    }
}
