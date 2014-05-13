package kr.promu.caireen.ui.pc.component.util;

import java.awt.Component;
import java.awt.EventQueue;

public class ComponentUtil {

    private ComponentUtil() {
    }

    public static void revalidate(final Component component, final RebuildCallBack callBack) {
	if (EventQueue.isDispatchThread()) {
	    component.invalidate();
	    callBack.call();
	    component.validate();
	} else {
	    EventQueue.invokeLater(new Runnable() {
		@Override
		public void run() {
		    revalidate(component, callBack);
		}
	    });
	}
    }

    public static interface RebuildCallBack {
	void call();
    }
}
