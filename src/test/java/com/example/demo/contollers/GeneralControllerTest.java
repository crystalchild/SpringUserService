package com.example.demo.contollers;

import com.example.demo.controllers.GeneralController;
import com.example.demo.services.UserService;
import com.example.demo.models.User;
import com.google.gson.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.lang.reflect.Type;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GeneralController.class)
public class GeneralControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    // Gson instance with custom serializer for Optional<Date>
    Gson gson = new GsonBuilder()
            .registerTypeAdapter(Optional.class, new JsonSerializer<Optional<Date>>() {
                @Override
                public JsonElement serialize(Optional<Date> src, Type typeOfSrc, JsonSerializationContext context) {
                    return src.map(context::serialize).orElseGet(JsonNull::new);
                }
            })
            .create();

    private final User testUser = new User(UUID.randomUUID(), "testuser", Optional.of(Date.from(Instant.now())));

    @Test
    @WithMockUser(username = "user", password = "password")
    public void createUser_ok() throws Exception {
        when(userService.createUser(testUser)).thenReturn(testUser);
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .content(gson.toJson(testUser)))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(testUser)));
    }
}
