package com.petstore.demo.service;

import com.petstore.demo.model.Pet;
import com.petstore.demo.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
class PetServiceTest {

    @Autowired
    private PetService petService;

    @MockBean
    private PetRepository petRepositoryMock;

    List<Pet> petList = new ArrayList<Pet>(){
        {
            add(new Pet("Tommy", "Dog", 15));
            add(new Pet("Lucky", "Cat", 10));
            add(new Pet("Jimmy", "Dog", 50));
            add(new Pet("Meow", "Cat", 10));
            add(new Pet("Tiger", "Dog", 20));
            add(new Pet("John", "Dog", 10));
        }
    };

    @Test
    public void contextLoads() throws Exception {
        assertThat(petService).isNotNull();
    }

    @Test
    void getPets_checkLimit() {
        mockFindAllMethod();
        List<Pet> pets = petService.getPets(5, 0);
        assertEquals(5, pets.size());
    }

    @Test
    void getPets_checkSkip() {
        mockFindAllMethod();
        List<Pet> pets = petService.getPets(5, 3);
        assertEquals(3, pets.size());
    }

    @Test
    void getPetById() {
        when(petRepositoryMock.findById(anyInt())).thenReturn(petList.stream().filter(x->x.getId() == 1).findFirst());
        Pet pet = petList.get(0);
        assertEquals("Tommy", pet.getName());
    }

    @Test
    void saveOrUpdate() {
        when(petRepositoryMock.save(any(Pet.class))).thenReturn(any(Pet.class));
        Pet newPet = new Pet(3, "NewDog", "Dog", 30);
        petService.saveOrUpdate(newPet);
        verify(petRepositoryMock, times(1)).save(newPet);
        assertEquals(3, newPet.getId());
    }

    @Test
    void delete() {
        doNothing().when(petRepositoryMock).deleteById(anyInt());
        petService.delete(1);
        verify(petRepositoryMock, times(1)).deleteById(1);
    }

    private void mockFindAllMethod() {
        when(petRepositoryMock.findAll()).thenReturn(petList);
    }
}
