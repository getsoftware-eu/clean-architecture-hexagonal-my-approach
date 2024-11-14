package eu.getsoftware.cleanarchitecture.adapter.out.persistence.model;

import lombok.*;
import eu.getsoftware.cleanarchitecture.application.domain.model.AddressValueObject;

import jakarta.persistence.*;

/**
 * DB-Mapping of DOMAIN Entity
 * Constructor will be filled manuell from UserDTO (dto from higher Level, but no IUser interface implementation here!)
 */

@Embeddable
//@Table(name = "address")
@Getter 
@Setter
@NoArgsConstructor
//@RequiredArgsConstructor - never for Entity!!!
public class AddressEmbeddableObject extends AddressValueObject //(no, manuell fill from DTO!!!)
{
//    @Id
//    @Setter(AccessLevel.PROTECTED)
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    private Long addressId;

    @NonNull
//    @Column
    public String getStreet() {return street;};
    
    @NonNull
//    @Column
    public String getCity() {return city;};

}
