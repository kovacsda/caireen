package kr.digitron.caireen.common.service;

/**
 * Interceptor to capture images.
 */
public interface ListenableService<L> {

    void addListener(L listener);

    boolean removeListener(L listener);

    void removeAllListener();
}
