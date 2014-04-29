package kr.digitron.caireen.common.service;

import java.util.ArrayList;
import java.util.List;

public abstract class DefaultListenableService<L, E> implements ListenableService<L> {

    private final List<L> listeners = new ArrayList<L>();

    @Override
    public void addListener(final L listener) {
	listeners.add(listener);
    }

    @Override
    public boolean removeListener(final L listener) {
	return listeners.remove(listener);
    }

    @Override
    public void removeAllListener() {
	listeners.clear();
    }

    protected int getListenerCount() {
	return listeners.size();
    }

    protected void fireEvent(final E event) {
	for (L listener : listeners) {
	    callListener(listener, event);
	}
    }

    protected abstract void callListener(L listener, E event);
}