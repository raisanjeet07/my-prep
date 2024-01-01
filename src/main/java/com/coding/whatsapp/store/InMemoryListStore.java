package com.coding.whatsapp.store;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class InMemoryListStore<E> extends AbstractStore<E> {

    List<E> data = new ArrayList<>();
    @Override
    public Stream<E> getStream() {
        return data.stream();
    }

    public void add(E e){
        data.add(e);
    }
}
