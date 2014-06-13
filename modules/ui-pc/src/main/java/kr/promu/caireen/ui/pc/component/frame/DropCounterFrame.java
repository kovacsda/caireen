package kr.promu.caireen.ui.pc.component.frame;

import java.awt.GridLayout;

import javax.swing.JLabel;

public class DropCounterFrame extends CounterFrame {

    private static final long serialVersionUID = 1L;

    private int lastPosition;
    private long lastDrop;

    private JLabel dropPosition;
    private JLabel dropCount;

    public DropCounterFrame() {
	init();
	build();
    }

    private void init() {
	dropPosition = new JLabel();
	dropCount = new JLabel();
    }

    private void build() {
	getContentPane().setLayout(new GridLayout(2, 2));
	getContentPane().add(new JLabel("Drop Position : "));
	getContentPane().add(dropPosition);
	getContentPane().add(new JLabel("Drop Count : "));
	getContentPane().add(dropCount);
    }

    @Override
    public void addData(final long time, final int dropPosition) {
	this.dropPosition.setText(dropPosition + " px");
	if (lastPosition > dropPosition) {
	    dropCount.setText(60000 / (time - lastDrop) + " drop / min");
	    lastDrop = time;
	}
	lastPosition = dropPosition;
    }
}
