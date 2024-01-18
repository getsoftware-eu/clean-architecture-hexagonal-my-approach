package eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.model;

import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IAddressEntityData;
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserEntityData;
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.model.UserAddresValueObject;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * DB-Mapping of DOMAIN Entity
 * Constructor will be filled manuell from UserDTO (dto from higher Level, but no IUser interface implementation here!)
 */

@Entity
@Table(name = "address")
@Getter @Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class AddressDataMapperEntity implements IAddressEntityData //(no, manuell fill from DTO!!!)
{
    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long addressId;
    
    @NonNull
    private String street;
    
    @NonNull
    private String city;

    @Override
    public String street() {
        return street;
    }

    @Override
    public String city() {
        return city;
    }
}
