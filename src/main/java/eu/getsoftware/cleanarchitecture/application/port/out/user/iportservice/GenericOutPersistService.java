package eu.getsoftware.cleanarchitecture.application.port.out.user.iportservice;

import lombok.RequiredArgsConstructor;

/**
 * only update methods
 * @param <T>
 * @param <ID>
 */
public interface GenericOutPersistService<T, ID> {

    public void convertAndPersist(T entity);
}
