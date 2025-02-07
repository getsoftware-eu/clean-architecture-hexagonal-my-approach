package eu.getsoftware.cleanarchitecture.application.port.in.user.iqueryservice;

import java.util.Collection;
import java.util.Optional;

public interface GenericQueryPort<T> {

    Collection<T> findAll();

    Optional<T> findById(Long id);

    Optional<T> findByField(String fieldName, Object value);
}