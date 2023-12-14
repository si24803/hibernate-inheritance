package org.psantos.hibernateinheritance;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.psantos.hibernateinheritance.data.pojo.Owner;
import org.psantos.hibernateinheritance.data.repository.OwnerRepository;
import org.psantos.hibernateinheritance.data.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(profiles = {"unit-tests"})
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
void addOwners(){
	List<Owner> owners = new ArrayList<>();
	owners.add(new Owner(null, "Pedro", "Rua Direita 3", "919191919", "111222333", null));
	owners.add(new Owner(null, "Pedro", "Rua Direita 30", "919191919", "444555666", null));

	owners.forEach(x -> ownerRepo.save(x));

	assertTrue(owners.stream().allMatch(x->x.getId()!=null));
}

}
