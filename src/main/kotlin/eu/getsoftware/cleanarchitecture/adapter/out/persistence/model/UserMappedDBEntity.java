package eu.getsoftware.cleanarchitecture.adapter.out.persistence.model;

import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainId;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainEntity;
import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * DB-Mapping of DOMAIN Entity
 * Constructor will be filled manuell from UserDTO (dto from higher Level, but no IUser interface implementation here!)
 */

@Entity
@Table(name = "user", schema = "userschema") //eu: Every bounded context stores its entities in its own DB schema
//@Getter 
//@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserMappedDBEntity extends UserDomainEntity  
{
    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long userId;

    @Embedded @Column
    public UserDomainId getDomainEntityId() {return domainEntityId;}
    
    @Column(name = "name", nullable = false)
    public String getName() {return name;};
    
    @NonNull
    @Column
    public String getPassword() {return password;};

//    @OneToOne
//    @JoinColumn(name = "address_id", nullable = false)
    @Embedded
    public AddressEmbeddableObject address;
    
    @Column
    @NonNull
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

//    public UserMappedDBEntity(){
//        super();
//    }
}
