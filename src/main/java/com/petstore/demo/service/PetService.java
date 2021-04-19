package com.petstore.demo.service;
import com.petstore.demo.model.Pet;
import com.petstore.demo.repository.PetRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PetService {
    final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<Pet> getPets(int limit, int skip){
         return petRepository.findAll().stream().skip(skip).limit(limit).collect(Collectors.toList());
    }

    public Pet getPetById(int id){
        return petRepository.findById(id).isPresent() ? petRepository.findById(id).get() : null;
    }

    public void saveOrUpdate(Pet pet){
        petRepository.save(pet);
    }

    public boolean isPetPresent(int id){
        if(petRepository.findById(id).isPresent()){
            return true;
        }else{
            return false;
        }
    }
    public void updatePet(int id, Pet pet){
        Pet updatePet = getPetById(id);
        updatePet.setName(pet.getName());
        updatePet.setType(pet.getType());
        updatePet.setPrice(pet.getPrice());
        petRepository.save(updatePet);
    }

    public void delete(int id) {
        petRepository.deleteById(id);
    }

}
