package com.ttc.bookmeetingroom.controller.api;

import com.ttc.bookmeetingroom.model.Room;
import com.ttc.bookmeetingroom.repository.RoomRepository;
import com.ttc.bookmeetingroom.repository.UserRepository;
import com.ttc.bookmeetingroom.service.RoomService;
import com.ttc.bookmeetingroom.service.UserService;
import com.ttc.bookmeetingroom.service.impl.RoomServiceImpl;
import com.ttc.bookmeetingroom.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RoomController.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class RoomControllerTest {
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    @TestConfiguration
    public static class ServiceTestConfiguration {
        @Bean
        RoomService roomService() {
            return new RoomServiceImpl();
        }

        @Bean
        UserService userService() {
            return new UserServiceImpl();
        }
    }

    @Autowired
    private MockMvc mockMvc;

    //repository
    @MockBean
    private RoomRepository roomRepository;

    @MockBean
    private UserRepository userRepository;

    private final String jsonUpdateRequest = "{\n" +
            "            \"name\": \"abc\",\n" +
            "            \"location\":{\n" +
            "            \"id\": 1\n" +
            "            },\n" +
            "            \"capacity\": \"15\",\n" +
            "            \"description\": \"abc\",\n" +
            "            \"image\": \"abc\"\n" +
            "        }";
    Integer id = 1;

    @Test
    public void testGetListRoomSuccess() throws Exception {
        List<Room> companies = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Room room = new Room().setId(i).setName("Công ty " + i).setCapacity(i).setDescription("de" + i);
            companies.add(room);
        }
        Pageable pageable = PageRequest.of(0, 20);
        Page<Room> result = new PageImpl<>(companies, pageable, companies.size());

        given(roomRepository.getAllByCondition(null, null, null, null, null, null, pageable)).willReturn(result);
        mockMvc.perform(get("/room/get-by-conditions"))
                .andExpect(status().isOk())

                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("R_200"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.total_items").value("5"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].id").value("0"));
    }

    @Test
    public void testCreateRoomSuccess() throws Exception {
        given(roomRepository.save(isA(Room.class))).willAnswer(i -> i.getArgument(0));
        //common value
        String jsonCreateRequest = "{\n" +
                "            \"name\": \"abc\",\n" +
                "            \"location\":{\n" +
                "            \"id\": 1\n" +
                "            },\n" +
                "            \"capacity\": \"15\",\n" +
                "            \"description\": \"abc\",\n" +
                "            \"image\": \"abc\"\n" +
                "        }";
        mockMvc.perform(post("/room/create")
                .contentType(APPLICATION_JSON_UTF8).content(jsonCreateRequest))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("R_201"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("CREATED"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.item.name").value("abc"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.item.location.id").value("1"));
    }

    @Test
    public void testUpdateRoomFalseWithRoomNotExists() throws Exception {

        given(roomRepository.findById(id)).willReturn(null);
        mockMvc.perform(put("/room/update/{id}", id).contentType(APPLICATION_JSON_UTF8)
                .content(jsonUpdateRequest)).andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("R_404"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Not found room"));
    }

    @Test
    public void testUpdateRoomSuccess() throws Exception {
        Room room1 = new Room().setId(1).setName("name");
        given(roomRepository.findById(id)).willReturn(room1);
        given(roomRepository.save(isA(Room.class))).willAnswer(i -> i.getArgument(0));
        mockMvc.perform(put("/room/update/{id}", id).contentType(APPLICATION_JSON_UTF8)
                .content(jsonUpdateRequest)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("R_200"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("OK"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.item.name").value("abc"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.item.location.id").value("1"));
    }

    @Test
    public void testDeleteRoomFalseWithRoomNotExists() throws Exception {

        given(roomRepository.findById(id)).willReturn(null);
        mockMvc.perform(delete("/room/delete/{id}", id).contentType(APPLICATION_JSON_UTF8)
                .content(jsonUpdateRequest)).andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("R_404"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Not found room"));
    }

    @Test
    public void testDeleteRoomSuccess() throws Exception {
        Room room1 = new Room().setId(1).setName("name");
        given(roomRepository.findById(id)).willReturn(room1);
        given(roomRepository.save(isA(Room.class))).willAnswer(i -> i.getArgument(0));
        mockMvc.perform(delete("/room/delete/{id}", id).contentType(APPLICATION_JSON_UTF8)
                .content(jsonUpdateRequest)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("R_200"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("OK"));
    }


}
