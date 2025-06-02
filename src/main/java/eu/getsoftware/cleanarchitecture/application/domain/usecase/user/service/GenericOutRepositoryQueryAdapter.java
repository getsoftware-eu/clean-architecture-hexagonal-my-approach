package eu.getsoftware.cleanarchitecture.application.domain.usecase.user.service;

import eu.getsoftware.cleanarchitecture.application.domain.model.mapper.EntityGenericMapper;
import eu.getsoftware.cleanarchitecture.application.port.out.user.iportservice.GenericOutRepositoryQueryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Optional;

/**
 * Interface segragation???
 * @param <T>
 * @param <DBEntity>
 * @param <ID>
 */
@RequiredArgsConstructor
public class GenericOutRepositoryQueryAdapter<T, DBEntity, ID> implements GenericOutRepositoryQueryService<T, ID> {

    private final JpaRepository<DBEntity, Long> repository;
    private final EntityGenericMapper<T, DBEntity> mapper;

    @Override
    public Optional<T> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }    
   
    @Override
    public Collection<T> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }    
    
    @Override
    public Optional<T> findByDomainId(ID domainId) {
       return this.findByField("domainId", domainId);
    }

    @Override
    public Optional<T> findByField(String fieldName, Object value) {
        try {
            for (DBEntity entity : repository.findAll()) {
                Field field = entity.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                if (value.equals(field.get(entity))) {
                    return Optional.of(mapper.toDomain(entity));
                }
            }
        } catch (Exception e) {
            throw new EntityNotFoundException("Error during findByField", e);
        }
        return Optional.empty();
    }

    public T findOrThrow(Long entityId) {
        return this.findById(entityId)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found for ID: " + entityId));
    }    

    //eu: экономит бесчисленные .orElseThrow в коде!!! +++
    public T findOrThrow(ID domainId) {
        return this.findByDomainId(domainId)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found for ID: " + domainId));
    } 

//    public Optional<T> findByField(String fieldName, Object value) {
//        return this.findByField(fieldName, value);
//    }    
    
//    @Override
//    @Transactional // eu : but not in Domain interface!!! write to db
//    public void convertAndPersist(T entity) {
//        DBEntity dbEntity = mapper.toDb(entity);
//        repository.save(dbEntity);
//    }
}