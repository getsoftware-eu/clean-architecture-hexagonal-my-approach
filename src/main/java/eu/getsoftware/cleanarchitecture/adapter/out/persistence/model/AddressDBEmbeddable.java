package eu.getsoftware.cleanarchitecture.adapter.out.persistence.model;

import lombok.*;

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
public class AddressDBEmbeddable //(no, manuell fill from DTO!!!)
{
    private @NonNull String street;
    private @NonNull String city;
    
//    @Id
//    @Setter(AccessLevel.PROTECTED)
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    private Long addressId;

//    @NonNull
////    @Column
//    public String getStreet() {return street;};
//
//    @NonNull
////    @Column
//    public String getCity() {return city;};

}
