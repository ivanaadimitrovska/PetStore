package com.example.petstore.repository;

import com.example.petstore.model.Money;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoneyRepository extends JpaRepository<Money, Long> {
}
