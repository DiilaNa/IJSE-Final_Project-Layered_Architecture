package gdse71.project.animalhospital.bo.Custom.impl;

import gdse71.project.animalhospital.bo.Custom.PetBO;
import gdse71.project.animalhospital.dao.DaoFactory;
import gdse71.project.animalhospital.dao.custom.PetDao;
import gdse71.project.animalhospital.dto.Petdto;
import gdse71.project.animalhospital.entity.Pet;

import java.util.ArrayList;

public class PetBOImpl implements PetBO {
    PetDao petDao = (PetDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.PET);


    @Override
    public ArrayList<Petdto> getAllPets() throws Exception {
        ArrayList<Petdto> petdtos = new ArrayList<>();
        ArrayList<Pet> pets = petDao.getAll();
        for (Pet pet : pets) {
            petdtos.add(new Petdto(pet.getPetId(),pet.getPetName(),pet.getPetBreed(),pet.getPetOwnerId(),pet.getPetType()));
        }
        return petdtos;
    }

    @Override
    public boolean deletePets(String petId) {
        try {
           return petDao.delete(petId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updatePets(Petdto petdto) throws Exception {
        return petDao.update(new Pet(petdto.getPetId(),petdto.getPetName(),petdto.getPetBreed(), petdto.getPetOwnerId(), petdto.getPetOwnerId()));
    }
}
