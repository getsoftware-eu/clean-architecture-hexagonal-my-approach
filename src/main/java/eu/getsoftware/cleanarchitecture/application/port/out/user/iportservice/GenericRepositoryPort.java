package eu.getsoftware.cleanarchitecture.application.port.out.user.iportservice;

import java.util.Optional;

public interface GenericRepositoryPort<T, ID> {

//    Collection<T> findAll(); 
//
//    Optional<T> findById(Long id);
//
//    Optional<T> findByField(String fieldName, Object value);

    Optional<T> findByDomainId(ID id);

    void convertAndPersist(T entity);
}
