package com.onsystem.wscapp.pantheon.api.interfaces.mapper;

@FunctionalInterface
public interface From<T, A> {
    T from(A a);
}
