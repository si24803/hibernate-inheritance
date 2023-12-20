package org.psantos.hibernateinheritance.data.pojo;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Exclude;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pets")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@NoArgsConstructor(access = AccessLevel.PACKAGE)
// @EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Exclude
    protected Long id;

    @Column
    protected String name;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    protected Owner owner;

    public Pet(Long id, String name, Owner owner) {
        // super(id, null, null);
        this.id = id;
        this.name = name;
        this.owner = owner;
    }
}
