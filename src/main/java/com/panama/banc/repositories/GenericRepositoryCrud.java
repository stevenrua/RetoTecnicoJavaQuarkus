package com.panama.banc.repositories;

import java.util.List;

public interface GenericRepositoryCrud<T, ID> {
    List<T> findAll();
    T findById(ID id);
}
