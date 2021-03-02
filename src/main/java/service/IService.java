package service;

import java.util.List;

public interface IService<E> {

    List<E> findAll();

    E findOne(Long id);

    E save(E customer);

    List<E> save(List<E> customers);

    boolean exists(Long id);

    List<E> findAll(List<Long> ids);

    long count();

    void delete(Long id);

    void delete(E customer);

    void delete(List<E> customers);

    void deleteAll();

}
