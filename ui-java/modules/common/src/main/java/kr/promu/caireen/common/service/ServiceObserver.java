package kr.promu.caireen.common.service;

public interface ServiceObserver<E> {

    void notify(E event);
}
