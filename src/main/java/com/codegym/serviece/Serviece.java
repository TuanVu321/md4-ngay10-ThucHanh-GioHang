package com.codegym.serviece;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Serviece<T>{
    Page<T> showAll(Pageable pageable);
    T findById(Long id);
    void save(T model);
    void delete(Long id);
}
