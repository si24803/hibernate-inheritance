package org.psantos.hibernateinheritance.controller;

import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.psantos.hibernateinheritance.data.pojo.Cat;
import org.psantos.hibernateinheritance.data.pojo.Owner;
import org.psantos.hibernateinheritance.data.pojo.Pet;
import org.psantos.hibernateinheritance.data.repository.OwnerRepository;
import org.psantos.hibernateinheritance.data.repository.PetRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DataController {

    private final OwnerRepository owners;

    private final PetRepository pets;

    // #region owners

    @GetMapping("/owners")
    public ResponseEntity<List<Owner>> getAllOwners() {
        return ResponseEntity.ok(owners.findAll());
    }

    @PostMapping("/owners")
    public ResponseEntity<Owner> addOwner(@NonNull @RequestBody Owner entity) {
        return ResponseEntity.ok(owners.save(entity));
    }

    @GetMapping("owners/{id}")
    public ResponseEntity<Owner> getOwner(@PathVariable("id") Long id) {
        Owner owner = owners.findById(id).orElse(null);
        if (owner == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(owner);
    }

    @PutMapping("owners/{id}")
    public ResponseEntity<Owner> updatOwner(@PathVariable Long id, @RequestBody Owner entity) {

        Owner owner = owners.findById(id).orElse(null);
        if (owner == null)
            return ResponseEntity.notFound().build();

        if (Strings.isBlank(entity.getAddress()))
            owner.setAddress(entity.getAddress());
        if (Strings.isBlank(entity.getName()))
            owner.setName(entity.getName());
        if (entity.getPets() != null)
            owner.setPets(entity.getPets());
        if (Strings.isBlank(entity.getPhoneNumber()))
            owner.setPhoneNumber(entity.getPhoneNumber());
        if (Strings.isBlank(entity.getVatNumber()))
            owner.setVatNumber(entity.getVatNumber());

        return ResponseEntity.ok(owners.save(owner));
    }

    // #endregion

    // #region Pets
    @GetMapping("/owners/{id}/pets")
    public ResponseEntity<List<Pet>> getAllOwnerPets(@PathVariable("id") Long id) {
        Owner owner = owners.findById(id).orElse(null);
        if (owner == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(owner.getPets());
    }

    @PostMapping("/owners/{id}/pets")
    public ResponseEntity<Owner> addPet(@PathVariable("id") Long id, @NonNull @RequestBody Pet entity) {
        Owner owner = owners.findById(id).orElse(null);
        if (owner == null)
            return ResponseEntity.notFound().build();

        owner.getPets().add(entity);

        return ResponseEntity.ok(owners.save(owner));
    }

    @GetMapping("pets/{id}")
    public ResponseEntity<Pet> getPet(@PathVariable("id") Long id) {
        Pet pet = pets.findById(id).orElse(null);
        if (pet == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(pet);
    }

    @PutMapping("pets/{id}")
    public ResponseEntity<Cat> updatCat(@PathVariable("id") Long id, @RequestBody Cat entity) {

        Pet pet = pets.findById(id).orElse(null);
        if (pet == null)
            return ResponseEntity.notFound().build();

        Cat cat = (Cat) pet;
        if (Strings.isBlank(entity.getBreed()))
            cat.setBreed(entity.getBreed());
        if (Strings.isBlank(entity.getName()))
            cat.setName(entity.getName());
        if (Strings.isBlank(cat.getVariant()))
            cat.setVariant(entity.getVariant());

        return ResponseEntity.ok(pets.save(cat));
    }
    // #endregion
}
