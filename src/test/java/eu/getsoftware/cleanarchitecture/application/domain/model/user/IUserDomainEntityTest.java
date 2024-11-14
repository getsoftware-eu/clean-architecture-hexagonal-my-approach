package eu.getsoftware.cleanarchitecture.application.domain.model.user;

import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedDBEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class IUserDomainEntityTest {

    @Test
    void given123Password_whenPasswordIsNotValid_thenIsFalse() {
        UserMappedDBEntity userEntity = new UserMappedDBEntity();
        
        // when:
        userEntity.setInitValues("Eugen", "password");
        
        // then:
        assertThat(userEntity.isPasswordValid()).isFalse();
    }
}