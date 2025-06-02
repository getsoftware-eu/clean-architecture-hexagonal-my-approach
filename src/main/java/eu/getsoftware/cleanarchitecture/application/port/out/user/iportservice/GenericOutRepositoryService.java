package eu.getsoftware.cleanarchitecture.application.port.out.user.iportservice;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class GenericOutRepositoryService<T, ID> {

    private final GenericOutPersistService<T, ID> outPersistService;
    private final GenericOutRepositoryQueryService<T, ID> outQueryService;
    
    public T findOrThrow(Long entityId) {
        return outQueryService.findById(entityId)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found for ID: " + entityId));
    }    
    
    //eu: экономит бесчисленные .orElseThrow в коде!!! +++
    public T findOrThrow(ID domainId) {
        return outQueryService.findByDomainId(domainId)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found for ID: " + domainId));
    } 

    public Optional<T> findByField(String fieldName, Object value) {
        return outQueryService.findByField(fieldName, value);
    }

    public void saveToDb(T entity) {
        outPersistService.convertAndPersist(entity);
    }
}
