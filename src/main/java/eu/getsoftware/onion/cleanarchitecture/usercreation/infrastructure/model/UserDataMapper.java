package eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "user")
@Getter @Setter
public class UserDataMapper {

    @Id
    String name;

    String password;

    LocalDateTime creationTime;

    public UserDataMapper() {
    }

    public UserDataMapper(String name, String password, LocalDateTime creationTime) {
        super();
        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
    }

   
}
