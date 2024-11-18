package eu.getsoftware.cleanarchitecture.adapter.out.persistence.model;

import eu.getsoftware.cleanarchitecture.application.domain.model.mapper.IMappedDBEntity;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainId;
import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * DB-Mapping of DOMAIN Entity
 * Constructor will be filled manuell from UserDTO (dto from higher Level, but no IUser interface implementation here!)
 */

@Entity
@Data
@Table(name = "user" /*, schema = "userschema"*/) //eu: Every bounded context stores its entities in its own DB schema
@Getter 
//@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC) // Для Hibernate или других ORM
//@AllArgsConstructor
public class UserMappedDBEntity implements IMappedDBEntity 
{
    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Embedded @Column(unique = true, nullable = false)
    private UserDomainId domainEntityId;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @NonNull
    @Column
    private String password;

    // @OneToOne @JoinColumn(name = "address_id", nullable = false)
    @Embedded @Column
    public AddressDBEmbeddable address;
    
    @Column
    @NonNull
    private LocalDateTime creationTime;  

}
