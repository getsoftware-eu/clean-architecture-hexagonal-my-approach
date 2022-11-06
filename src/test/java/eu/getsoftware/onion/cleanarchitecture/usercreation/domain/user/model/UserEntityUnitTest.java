package eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.UserEntity;

class UserEntityUnitTest
{

    @Test
    void given123Password_whenPasswordIsNotValid_thenIsFalse() {
        UserEntity userEntity = new CommonUserEntityEntity("Baeldung", "123");

        assertThat(userEntity.passwordIsValid()).isFalse();
    }
}
