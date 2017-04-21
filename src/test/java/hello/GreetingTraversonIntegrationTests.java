package hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by agup101 on 4/21/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingTraversonIntegrationTests {

    @LocalServerPort
    private int port;

    @Test
    public void envEndpointNotHidden() throws Exception {
        Traverson traverson = new Traverson(new URI("http://localhost:" + this.port + "/greeting"), MediaTypes.HAL_JSON);
        String greeting = traverson.follow("self").toObject("$.content");
        assertThat(greeting).isEqualTo("Hello, World!");
    }
}
