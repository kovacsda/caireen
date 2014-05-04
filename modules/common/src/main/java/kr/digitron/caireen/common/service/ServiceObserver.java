package kr.digitron.caireen.common.service;

public interface ServiceObserver<E> {

    void notify(E event);
}
