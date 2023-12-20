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
public class Dog extends Pet {
    private String fur;
    private String ears;

    public Dog(Long id, String name, Owner owner, String fur, String ears) {
        super(id, name, owner);
        this.fur = fur;
        this.ears = ears;
    }
}
