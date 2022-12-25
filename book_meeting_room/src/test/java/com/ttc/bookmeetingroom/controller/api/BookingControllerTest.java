package com.ttc.bookmeetingroom.controller.api;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
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

import com.ttc.bookmeetingroom.model.Booking;
import com.ttc.bookmeetingroom.model.Room;
import com.ttc.bookmeetingroom.repository.BookingRepository;
import com.ttc.bookmeetingroom.repository.UserRepository;
import com.ttc.bookmeetingroom.service.BookingService;
import com.ttc.bookmeetingroom.service.UserService;
import com.ttc.bookmeetingroom.service.impl.BookingServiceImpl;
import com.ttc.bookmeetingroom.service.impl.UserServiceImpl;


@RunWith(SpringRunner.class)
@WebMvcTest(BookingController.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class BookingControllerTest {
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);
	
	@TestConfiguration
    public static class ServiceTestConfiguration {
        @Bean
        BookingService bookingService() {
            return new BookingServiceImpl();
        }    
        @Bean
        UserService userService() {
            return new UserServiceImpl();
        }
    }
	
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private BookingRepository BookingRepository;

    @MockBean
    private UserRepository userRepository;
	
    private final String jsonUpdateRequest = "{\n" +
            "            \"check_in\": \"2021-08-04 23:27:57.000\",\n" +
            "            \"check_out\": \"2021-08-06 23:27:57.000\",\n"+
            "			 \"description\": \"we are one!\", \n"+
            "			 \"participants\": 15,	\n"+
            "			 \"title\": \"Booking08-6\", \n"+
            "			 \"room\":{\n"+
            "			 \"id\": 45}"	+
            "        }";
    
    @Test
    public void testGetListBookSuccess() throws Exception{
    	Booking b = new Booking(45,Timestamp.valueOf("2021-08-04 22:27:57.000"),
    			Timestamp.valueOf("2021-08-04 23:27:57.0"),"Booking112",12,null,null,null);
    	Booking b1 = new Booking(55,Timestamp.valueOf("2021-08-04 22:27:57.000"),
    			Timestamp.valueOf("2021-08-04 23:27:57.0"),"Booking111",12,null,null,null);
    	
    	Room r = new Room();
    	r.setId(5);
    	Booking b2 = new Booking(85,null,null,"test",null,null,null,r);
    	
    	List<Booking> list = new ArrayList();
    	list.add(b); list.add(b1); list.add(b2);
    	
    	Pageable pageable = PageRequest.of(0, 20);
        Page<Booking> result = new PageImpl<>(list, pageable, list.size());

        given(BookingRepository.getAllByCondition(null,null,null,null,null,null,null,null,pageable)).willReturn(result);
        
        mockMvc.perform(get("/booking/get-by-conditions"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("R_200"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.total_items").value("3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].id").value("45"));
    }
    
    @Test
    public void testCreateBookingSuccess() throws Exception {
        given(BookingRepository.save(isA(Booking.class))).willAnswer(i -> i.getArgument(0));
        //common value
        String jsonCreateRequest = "{\n" +
                "            \"check_in\": \"2021-08-05 23:27:57.000\",\n" +
                "            \"check_out\": \"2021-08-08 23:27:57.000\",\n"+
                "			 \"description\": \"we are one!\", \n"+
                "			 \"participants\": 15,	\n"+
                "			 \"title\": \"Booking08-5\", \n"+
                "			 \"room\":{\n"+
                "			 \"id\": 5}"	+
                "        }";
        mockMvc.perform(post("/booking/create")
                .contentType(APPLICATION_JSON_UTF8).content(jsonCreateRequest))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("R_201"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("CREATED"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.item.title").value("Booking08-5"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.item.room.id").value("5"));
    }
    
    @Test
    public void testUpdateBookingFalseWithBookingNotExists() throws Exception {
    	int id = -10;
        given(BookingRepository.findById(id)).willReturn(null);
        mockMvc.perform(put("/booking/update/{id}", id).contentType(APPLICATION_JSON_UTF8)
                .content(jsonUpdateRequest)).andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("R_404"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Not found booking"));
    }
    
    @Test
    public void testUpdateBookingSuccess() throws Exception {
        Room r = new Room();
        r.setId(5);
//        --------
        Booking b = new Booking();
        b.setId(85);
        b.setTitle("test");
        b.setRoom(r);
//        --------
        given(BookingRepository.findById(85)).willReturn(b);
        given(BookingRepository.save(isA(Booking.class))).willAnswer(i -> i.getArgument(0));
        mockMvc.perform(put("/booking/update/{id}", 85).contentType(APPLICATION_JSON_UTF8)
                .content(jsonUpdateRequest)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("R_200"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("OK"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.item.title").value("Booking08-6"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.item.room.id").value("45"));
    }
    
    @Test
    public void testDeleteBookingFalseWithBookingNotExists() throws Exception {
    	given(BookingRepository.findById(-10)).willReturn(null);
        mockMvc.perform(delete("/booking/delete/{id}", -10).contentType(APPLICATION_JSON_UTF8)
                .content(jsonUpdateRequest)).andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("R_404"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Not found booking"));
    }
    
    @Test
    public void testDeleteRoomSuccess() throws Exception {
    	Room r = new Room();
        r.setId(5);
//        --------
        Booking b = new Booking();
        b.setId(85);
        b.setTitle("test");
        b.setRoom(r);
        
        given(BookingRepository.findById(85)).willReturn(b);
        given(BookingRepository.save(isA(Booking.class))).willAnswer(i -> i.getArgument(0));
        mockMvc.perform(delete("/booking/delete/{id}", 85).contentType(APPLICATION_JSON_UTF8)
                .content(jsonUpdateRequest)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("R_200"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("OK"));
    }
}
