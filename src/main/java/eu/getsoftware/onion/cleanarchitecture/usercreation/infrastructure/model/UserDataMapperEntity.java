package eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * DB-Mapping of DOMAIN Entity
 * Constructor will be filled manuell from UserDTO (dto from higher Level, but no IUser interface implementation here!)
 */

@Entity
@Table(name = "user")
@Getter @Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class UserDataMapperEntity implements IUserEntity //(no, manuell fill from DTO!!!)
{
    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long userId;
    
    @NonNull
    private String name;
    
    @NonNull
    private String password;

    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    private AddressDataMapperEntity address;
    
    @NonNull
    private LocalDateTime creationTime;

    @Override
    public void setInitValues(String name) {
        this.setName(name);
        this.setCreationTime(LocalDateTime.now());
    }
}
