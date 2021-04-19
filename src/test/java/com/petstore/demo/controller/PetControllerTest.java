package com.petstore.demo.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petstore.demo.model.Pet;
import com.petstore.demo.repository.PetRepository;
import com.petstore.demo.service.PetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PetController.class)
class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService petServiceMock;

    @MockBean
    private PetRepository petRepo;

    private ObjectMapper objectMapper = new ObjectMapper();

    List<Pet> petList = new ArrayList<Pet>(){
        {
            add(new Pet(1, "Max", "Dog", 15));
            add(new Pet(2, "Tommy", "Dog", 25.5));
        }
    };

    @Test
    public void petController_getAllPets() throws Exception {
        when(petServiceMock.getPets(anyInt(), anyInt())).thenReturn(petList);
        RequestBuilder request = MockMvcRequestBuilders
                .get("/pets")
                .accept(MediaType.APPLICATION_JSON);
        String expectedResponse = objectMapper.writeValueAsString(petList);
        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse))
                .andReturn();
    }

    @Test
    public void petController_postPet() throws Exception {
       doNothing().when(petServiceMock).saveOrUpdate(any(Pet.class));
        Pet pet = new Pet(3, "NewDog", "Dog", 30);
        RequestBuilder request = MockMvcRequestBuilders
                .post("/pets")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(pet));
        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void petController_getPetById() throws Exception {
        Pet pet= new Pet(2, "Tommy", "Dog", 25.5);
        when(petServiceMock.getPetById(anyInt())).thenReturn(pet);
        when(petServiceMock.isPetPresent(anyInt())).thenReturn(true);
        RequestBuilder request = MockMvcRequestBuilders
                .get("/pets/1")
                .accept(MediaType.APPLICATION_JSON);
        String expectedResponse = objectMapper.writeValueAsString(petList.get(1));
        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse))
                .andReturn();
    }

    @Test
    public void petController_deletePetById() throws Exception {

        Pet pet= new Pet(2, "Tommy", "Dog", 25.5);
        doNothing().when(petServiceMock).delete(anyInt());
        when(petServiceMock.isPetPresent(anyInt())).thenReturn(true);
        RequestBuilder request = MockMvcRequestBuilders
                .delete("/pets/1")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Pet with 1 deleted successfully"))
                .andReturn();
    }
}
