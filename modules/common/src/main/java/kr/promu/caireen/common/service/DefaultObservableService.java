package kr.promu.caireen.common.service;

import java.util.ArrayList;
import java.util.List;

public abstract class DefaultObservableService<E> implements ObservableService<E> {

    private final List<ServiceObserver<E>> listeners = new ArrayList<ServiceObserver<E>>();

    @Override
    public void registerObserver(final ServiceObserver<E> listener) {
	listeners.add(listener);
    }

    @Override
    public boolean unregisterObserver(final ServiceObserver<E> listener) {
	return listeners.remove(listener);
    }

    @Override
    public void unregisterAllObserver() {
	listeners.clear();
    }

    protected int getListenerCount() {
	return listeners.size();
    }

    protected void fireEvent(final E event) {
	for (ServiceObserver<E> listener : listeners) {
	    listener.notify(event);
	}
    }
}