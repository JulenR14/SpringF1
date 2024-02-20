package org.api.springf1.repository;

import org.api.springf1.model.Driver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class DriverRepositoryTest  {

    @Autowired DriverRepository driverRepository;

    Driver driver;

    @BeforeEach
    void setup(){
        driver = Driver.builder().code("AAA").forename("aaaa").surname("oooo").build();
    }

    @Test
    void shouldReturnsaveDriverWhenSave(){
        Driver driverSaved = driverRepository.save(driver);

        assertThat(driverSaved).isNotNull();
        assertThat(driverSaved.getId()).isGreaterThan(0);
    }

    @Test
    void shouldReturnMoreThanOneDriverWhenSaveTwoDrivers(){
        Driver driverSaved = driverRepository.save(driver);
        Driver driverSaved2 = driverRepository.save(driver);

        assertThat(driverSaved).isNotNull();
        assertThat(driverSaved2).isNotNull();
        assertThat(driverSaved.getId()).isGreaterThan(0);
        assertThat(driverSaved2.getId()).isGreaterThan(0);
    }

    @Test
    void shouldReturnDriverNotNullWhenUpdateDriver(){
        Driver driverSaved = driverRepository.save(driver);
        driverSaved.setForename("bbbb");
        Driver driverUpdated = driverRepository.save(driverSaved);

        assertThat(driverUpdated).isNotNull();
        assertThat(driverUpdated.getId()).isGreaterThan(0);
        assertThat(driverUpdated.getForename()).isEqualTo("bbbb");
    }

    @Test
    void shouldReturnNullDriverWhenDelete(){
        Driver driverSaved = driverRepository.save(driver);
        driverRepository.delete(driverSaved);
        Driver driverDeleted = driverRepository.findById(driverSaved.getId()).orElse(null);

        assertThat(driverDeleted).isNull();
    }

}
