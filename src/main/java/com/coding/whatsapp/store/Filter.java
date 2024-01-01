package com.coding.whatsapp.store;

public interface Filter<E> {
    boolean apply(E e);
}
