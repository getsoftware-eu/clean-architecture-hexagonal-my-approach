package eu.getsoftware.cleanarchitecture.adapter.out.persistence.repository;

import eu.getsoftware.cleanarchitecture.application.domain.model.mapper.EntityGenericMapper;
import eu.getsoftware.cleanarchitecture.application.port.out.user.iportservice.GenericRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Field;
import java.util.Optional;

@RequiredArgsConstructor
public class GenericRepositoryAdapter<T, DBEntity, ID> implements GenericRepositoryPort<T, ID> {

    private final JpaRepository<DBEntity, Long> repository;
    private final EntityGenericMapper<T, DBEntity> mapper;

    @Override
    public Optional<T> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
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

    @Override
    public void save(T entity) {
        DBEntity dbEntity = mapper.toDb(entity);
        repository.save(dbEntity);
    }
}