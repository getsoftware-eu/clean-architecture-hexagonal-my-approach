package eu.getsoftware.onion.cleanarchitecture.usercreation.application;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class ApplicationIntegrationTest
{
  @Test
  void contextLoads(ApplicationContext context) {
    assertThat(context).isNotNull();
  }
}
