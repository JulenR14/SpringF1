package org.api.springf1.service;

import org.api.springf1.dto.DriverDTO;
import org.api.springf1.model.Driver;
import org.api.springf1.repository.DriverRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DriverServiceImplTest {

    @Mock
    DriverRepository driverRepository;

    @InjectMocks
    DriverServiceImpl driverService;

    Driver driver;

    DriverDTO driverDTO;

    @BeforeEach
    public void setup(){
        driver = Driver.builder().id(1L).code("AAA").forename("Ayrton").surname("Senna").build();
        driverDTO = DriverDTO.builder().id(1L).code("AAA").forename("Ayrton").surname("Senna").build();
    }

    @Test
    public void shouldReturnDriverDTOWhenCreateDriver(){

        when(driverRepository.save(any(Driver.class))).thenReturn(driver);

        DriverDTO savedDriver = driverService.saveDriver(driver);

        assertNotNull(savedDriver);
        assertEquals("AAA", savedDriver.code());

        verify(driverRepository, times(1)).save(driver);
    }

}