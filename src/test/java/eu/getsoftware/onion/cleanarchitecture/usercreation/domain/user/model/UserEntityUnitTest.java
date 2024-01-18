package eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserEntityDataRules;

class UserEntityUnitTest
{

    @Test
    void given123Password_whenPasswordIsNotValid_thenIsFalse() {
        IUserEntityDataRules userEntity = new UserEntityImpl("Baeldung", "123");

        assertThat(userEntity.passwordIsValid()).isFalse();
    }
}
