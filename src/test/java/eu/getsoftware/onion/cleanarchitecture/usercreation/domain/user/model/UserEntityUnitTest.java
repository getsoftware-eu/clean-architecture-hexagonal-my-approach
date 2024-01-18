package eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.model;

import static org.assertj.core.api.Assertions.assertThat;

import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.model.UserDataMapperEntity;
import org.junit.jupiter.api.Test;

class UserEntityUnitTest
{

    @Test
    void given123Password_whenPasswordIsNotValid_thenIsFalse() {
        UserDataMapperEntity userEntity = new UserDataMapperEntity();
        userEntity.setName("Eugen");
        userEntity.setPassword("123"); 
        assertThat(userEntity.isPasswordValid()).isFalse();
    }
}
