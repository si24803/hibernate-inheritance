package org.psantos.hibernateinheritance.data.pojo;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(callSuper = true)
public class Cat extends Pet {
    private String breed;
    private String variant;

    public Cat(Long id, String name, Owner owner, String breed, String variant) {
        super(id, name, owner);
        this.breed = breed;
        this.variant = variant;
    }
}
