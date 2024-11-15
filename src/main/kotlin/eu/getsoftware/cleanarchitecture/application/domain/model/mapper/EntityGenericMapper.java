package eu.getsoftware.cleanarchitecture.application.domain.model.mapper;

public interface EntityGenericMapper<T, DBEntity> {
    T toDomain(DBEntity entity);

    DBEntity toDb(T domain);
}
