package kr.digitron.caireen.common.service;

/**
 * Interceptor to capture images.
 */
public interface ObservableService<E> {

    void registerObserver(ServiceObserver<E> listener);

    boolean unregisterObserver(ServiceObserver<E> listener);

    void unregisterAllObserver();
}
