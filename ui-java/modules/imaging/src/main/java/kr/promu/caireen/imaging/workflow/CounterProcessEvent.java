package kr.promu.caireen.imaging.workflow;

public class CounterProcessEvent {

    private final long time;
    private final int dropPosition;

    public CounterProcessEvent(final long time, final int dropPosition) {
	this.time = time;
	this.dropPosition = dropPosition;
    }

    public long getTime() {
	return time;
    }

    public int getDropPosition() {
	return dropPosition;
    }
}