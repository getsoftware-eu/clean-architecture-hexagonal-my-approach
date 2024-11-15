package eu.getsoftware.cleanarchitecture.application.domain.model.mapper;

public interface DtoGenericMapper<T, DBEntity> {
    T toEntity(DBEntity entity);

    DBEntity toDto(T domain);
}
