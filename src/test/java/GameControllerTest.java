import basecamp.config.WebConfig;
import basecamp.service.GameService;
import basecamp.wire.CreateNumberResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.annotation.DirtiesContext.MethodMode.BEFORE_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class GameControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private GameService gameService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .build();
    }

    @Test
    public void givenValidURL_whenPerformPost_thenVerifyResponse() throws Exception {

        //given
        final String URL = "/game/create";

        //when
        ResultActions result = mockMvc.perform(post(URL));

        //then
        result.andExpect(content().contentType("application/json;charset=UTF-8"));
        result.andExpect(status().isOk());

    }


    @Test
    @DirtiesContext(methodMode = BEFORE_METHOD)
    public void givenValidURL_whenPerformGet_thenVerifyResponse() throws Exception {

        CreateNumberResponse response = gameService.createNumber();
        int id = response.getId();
        int num = response.getNumber();

        //given
        final String URL = "/game/play/" + id + "/" + num;

        //when
        ResultActions result = mockMvc.perform(get(URL));

        //then
        result.andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result")
                        .value("Winner!"));

    }

    @Test
    public void givenInvalidFirstParameterInURL_whenPerformGet_thenVerifyExceptionMessage() throws Exception {

        //given
        final String URL = "/game/play/ /50";

        //when
        ResultActions result = mockMvc.perform(get(URL));

        //then
        result.andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value("Id must not be blank!"));
    }

    @Test
    public void givenInvalidSecondParameterInURL_whenPerformGet_thenVerifyExceptionMessage() throws Exception {

        //given
        final String URL = "/game/play/1/500";

        //when
        ResultActions result = mockMvc.perform(get(URL));

        //then
        result.andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value("Invalid number range! Range = [0:100]"));
    }
}
