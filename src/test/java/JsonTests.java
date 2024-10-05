import com.fasterxml.jackson.databind.ObjectMapper;
import model.ClientData;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.io.Reader;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JsonTests {

    private ClassLoader cl = JsonTests.class.getClassLoader();

    @Test
    void jsonFileParsingTest() throws Exception {
        try (Reader reader = new InputStreamReader(
                cl.getResourceAsStream("test.json")
        )){
            ObjectMapper mapper = new ObjectMapper();
            ClientData actualData = mapper.readValue(reader, ClientData.class);

         assertThat(actualData.getClients().get(0).getName()).isEqualTo("Кексик");
         assertThat(actualData.getClients().get(0).getAge()).isEqualTo(88);
         assertThat(actualData.getClients().get(0).getAdditionally().getAddress())
                 .isEqualTo("Булочная");
         assertThat(actualData.getClients().get(0).getAdditionally().getHobbies())
                 .isEqualTo("Печь кексики");

         assertThat(actualData.getClients().get(1).getName()).isEqualTo("Котик");
         assertThat(actualData.getClients().get(1).getAge()).isEqualTo(12);
         assertThat(actualData.getClients().get(1).getAdditionally().getAddress())
                    .isEqualTo("Коробка");
         assertThat(actualData.getClients().get(1).getAdditionally().getHobbies())
                    .isEqualTo("Ронять со стола вещи");
        }
    }
}
