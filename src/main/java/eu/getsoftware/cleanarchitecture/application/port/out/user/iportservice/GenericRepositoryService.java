package eu.getsoftware.cleanarchitecture.application.port.out.user.iportservice;

import eu.getsoftware.cleanarchitecture.application.port.in.user.iqueryservice.GenericQueryPortService;
import eu.getsoftware.cleanarchitecture.application.port.out.user.iportservice.gateways.UserGatewayService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class GenericRepositoryService<T, ID> {

    private final GenericRepositoryPort<T, ID> repositoryPort;
    private final GenericQueryPortService<T> genericQueryPortService;
    
//    public GenericService(GenericRepositoryPort<T, ID> repositoryPort) {
//        this.repositoryPort = repositoryPort;
//    }
    
    public T findOrThrow(Long entityId) {
        return genericQueryPortService.findById(entityId)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found for ID: " + entityId));
    }    
    
    //eu: экономит бесчисленные .orElseThrow в коде!!! +++
    public T findOrThrow(ID domainId) {
        return repositoryPort.findByDomainId(domainId)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found for ID: " + domainId));
    } 

    public Optional<T> findByField(String fieldName, Object value) {
        return genericQueryPortService.findByField(fieldName, value);
    }

    public void saveToDb(T entity) {
        repositoryPort.convertAndPersist(entity);
    }
}
