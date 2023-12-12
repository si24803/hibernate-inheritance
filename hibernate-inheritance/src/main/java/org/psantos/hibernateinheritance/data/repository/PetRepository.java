package org.psantos.hibernateinheritance.data.repository;

import org.psantos.hibernateinheritance.data.pojo.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

}