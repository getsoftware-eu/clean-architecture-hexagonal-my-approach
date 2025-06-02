package eu.getsoftware.cleanarchitecture.application.port.out.user.iportservice;

import jakarta.persistence.EntityNotFoundException;

import java.util.Collection;
import java.util.Optional;

/**
 * CCA: Only if I want to allow these getters (as a port service) without useCases
 *
 * TODO Use here  Entity,  not DTO
 * @param <T>
 */
public interface GenericOutRepositoryQueryService<T, ID> {

    Collection<T> findAll();

    Optional<T> findById(Long id);

    Optional<T> findByField(String fieldName, Object value);

    Optional<T> findByDomainId(ID id);

    public T findOrThrow(Long entityId);

    //eu: экономит бесчисленные .orElseThrow в коде!!! +++
    public T findOrThrow(ID domainId);

}