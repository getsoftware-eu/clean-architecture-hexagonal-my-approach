package eu.getsoftware.cleanarchitecture.application.port.out.user.iportservice;

/**
 * only update methods
 * @param <T>
 * @param <ID>
 */
public interface GenericOutPersistService<T, ID> {
    
    void convertAndPersist(T entity);
}
