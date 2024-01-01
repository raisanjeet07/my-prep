package com.coding.whatsapp.store;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractStore<E> implements Store<E> {

    @Override
    public List<E> filterBy(Filter<E> filter) {
        return getStream().filter(filter::apply).collect(Collectors.toList());
    }

    public abstract Stream<E> getStream();

}
