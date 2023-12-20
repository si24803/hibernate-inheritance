package org.psantos.hibernateinheritance;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.psantos.hibernateinheritance.data.pojo.Cat;
import org.psantos.hibernateinheritance.data.pojo.Dog;
import org.psantos.hibernateinheritance.data.pojo.Owner;
import org.psantos.hibernateinheritance.data.pojo.Pet;
import org.psantos.hibernateinheritance.data.repository.OwnerRepository;
import org.psantos.hibernateinheritance.data.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({ "unit-tests" })
@Profile({ "unit-tests" })
class HibernateInheritanceApplicationTests {

	@Autowired
	private ApplicationContext appCtx;

	@Autowired
	private OwnerRepository ownerRepo;

	@Autowired
	private PetRepository petRepo;

	@Test
	void contextLoads() {
		assertNotNull(appCtx);
	}

	@Test
	void addOwners() {
		List<Owner> owners = new ArrayList<>();
		owners.add(new Owner("Pedro", "Rua Direita 3", "919191919", "111222333", null));
		owners.add(new Owner("Pedro", "Rua Direita 30", "919191919", "444555666", null));

		owners.forEach(x -> ownerRepo.save(x));

		assertTrue(owners.stream().allMatch(x -> x.getId() != null));
	}

	@Test
	void addPets() {
		Owner owner = ownerRepo.save(new Owner("Pedro", "Rua Direita 3", "919191919", "222333444", null));
		assertNotNull(owner);

		List<Pet> pets = new ArrayList<>();
		pets.add(new Cat(null, "Miss", owner, "Scottish", "Fold"));
		pets.add(new Dog(null, "Diego", owner, "short", "straight"));

		pets.stream().forEach(p -> petRepo.save(p));

		Owner ownerAfter = ownerRepo.findByVatNumber("222333444").get(0);
		assertNotNull(ownerAfter.getPets());

		// ownerRepo.save(owner);

		assertTrue(pets.stream().allMatch(p -> p.getId() != null));
	}
}
