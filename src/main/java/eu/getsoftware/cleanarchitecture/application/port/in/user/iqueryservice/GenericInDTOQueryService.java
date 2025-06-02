package eu.getsoftware.cleanarchitecture.application.port.in.user.iqueryservice;

import java.util.Collection;
import java.util.Optional;

/**
 * CCA: Only if I want to allow these getters (as a port service) without useCases
 *
 * TODO Use here not Entity, but as response only Dto or limited ReadOnly Record-Queries from DB
 * @param <DTO>
 */
public interface GenericInDTOQueryService<DTO> {

    Collection<DTO> findAll();

    Optional<DTO> findById(Long id);

    Optional<DTO> findByField(String fieldName, Object value);
}