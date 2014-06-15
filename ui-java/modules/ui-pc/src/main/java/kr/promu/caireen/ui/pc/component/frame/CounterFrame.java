package kr.promu.caireen.ui.pc.component.frame;

import javax.swing.JFrame;

public abstract class CounterFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    public abstract void addData(final long time, final int dropPosition);
}
