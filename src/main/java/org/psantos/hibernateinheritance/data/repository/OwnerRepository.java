package org.psantos.hibernateinheritance.data.repository;

import org.psantos.hibernateinheritance.data.pojo.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    List<Owner> findByVatNumber(String vatNumber);

    List<Owner> findByPhoneNumber(String phoneNumber);
}
