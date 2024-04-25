package eu.getsoftware.onion.cleanarchitecture.domain.user.model;

import static org.assertj.core.api.Assertions.assertThat;

import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedEntity;
import org.junit.jupiter.api.Test;

class UserEntityUnitTest
{

    @Test
    void given123Password_whenPasswordIsNotValid_thenIsFalse() {
        UserMappedEntity userEntity = new UserMappedEntity();
        userEntity.setName("Eugen");
        userEntity.setPassword("123"); 
        assertThat(userEntity.isPasswordValid()).isFalse();
    }
}
