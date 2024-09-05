package eu.getsoftware.cleanarchitecture.adapter.out.persistence.model;

import lombok.*;
import eu.getsoftware.cleanarchitecture.application.domain.model.IAddressDomain;

import jakarta.persistence.*;

/**
 * DB-Mapping of DOMAIN Entity
 * Constructor will be filled manuell from UserDTO (dto from higher Level, but no IUser interface implementation here!)
 */

@Entity
@Table(name = "address")
@Getter @Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class AddressMappedEntity implements IAddressDomain //(no, manuell fill from DTO!!!)
{
    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long addressId;
    
    @NonNull
    private String street;
    
    @NonNull
    private String city;

}
