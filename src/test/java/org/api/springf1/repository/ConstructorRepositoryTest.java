package org.api.springf1.repository;

import org.api.springf1.dto.ConstructorDTO;
import org.api.springf1.model.Constructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Testcontainers
@DataJpaTest
public class ConstructorRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15.5");

    @Autowired
    ConstructorRepository constructorRepository;

    Constructor constructor;

    @BeforeEach
    void setup(){
        constructor = Constructor.builder().id(1L).ref("AAA").name("aaaa").nationality("oooo").build();
    }

    @Test
    void shouldReturnConstructorNotNullWhenFindConstructorByRef(){
        Constructor constructorSaved = constructorRepository.save(constructor);
        Constructor constructorFound = constructorRepository.findByRefIgnoreCase("AAA").orElse(null);

        assertThat(constructorFound).isNotNull();
        assertThat(constructorFound.getId()).isEqualTo(constructorSaved.getId());
    }

    @Test
    void shouldReturnConstructorNotNullWhenDeleteConstructorByRef(){
        Constructor constructorSaved = constructorRepository.save(constructor);
        constructorRepository.delete(constructorSaved);
        Constructor constructorDeleted = constructorRepository.findByRefIgnoreCase("AAA").orElse(null);

        assertThat(constructorDeleted).isNull();
    }


}
