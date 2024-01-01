package com.coding.whatsapp.store;

import java.util.List;

public interface Store<E> {
    List<E> filterBy(Filter<E> filter);
}
