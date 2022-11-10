package eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "user")
@Getter @Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class UserDataMapperEntity
{
    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long userId;
    
    @NonNull
    private String name;
    
    @NonNull
    private String password;
    
    @NonNull
    private LocalDateTime creationTime;
}
