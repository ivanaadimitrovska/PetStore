package com.example.petstore.model;

import com.example.petstore.enumerations.TypeEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Customer  owner;
    private String description;
    private TypeEnum type;
    private LocalDate dateOfBirth;
    private Integer rating;
    private String name;

    @ManyToOne
    private Money price;

    public Pet(Customer  owner, String description, LocalDate dateOfBirth, Money price, TypeEnum type, String name) {
        this.owner = owner;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
        this.price = price;
        this.type = type;
        this.name = name;
    }

    public Pet() {

    }

    public Pet(Customer  owner, String description, LocalDate dateOfBirth, Money price, TypeEnum type, Integer rating, String name) {
        this.owner = owner;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
        this.price = price;
        this.type = type;
        this.rating = rating;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Customer  getOwner() {
        return owner;
    }

    public String getDescription() {
        return description;
    }

    public TypeEnum getType() {
        return type;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Integer getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setRating(Integer rating){
        this.rating=rating;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Pet pet = (Pet) o;
        return getId() != null && Objects.equals(getId(), pet.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }

    public void setOwner(Customer owner){
        this.owner=owner;
    }
}
