package org.api.springf1.service;

import org.api.springf1.dto.DriverDTO;
import org.api.springf1.dto.DriverResponse;
import org.api.springf1.model.Driver;
import org.api.springf1.repository.DriverRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.postgresql.hostchooser.HostRequirement.any;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class DriverServiceImplTest {

    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private DriverServiceImpl driverService;

    Driver driver;
    DriverDTO driverDTO;

    @BeforeEach
    void setup(){
        driver = Driver.builder().id(1L).code("AAA").forename("aaaa").surname("oooo").build();
        driverDTO = DriverDTO.builder().id(1L).code("AAA").forename("aaaa").surname("oooo").build();
    }

    @Test
    void shouldReturnDriverDTOWhenCreateDriver(){
        when(driverRepository.save(any(Driver.class))).thenReturn(driver);

        DriverDTO saveDriver = driverService.saveDriver(driver);

        assertNotNull(saveDriver);
        assertEquals("AAA", saveDriver.code());

        verify(driverRepository, times(1)).save(any(Driver.class));
    }

    @Test
    void shouldReturnDriverDTOWhenFindDriverByCode(){
        when(driverRepository.findByCodeIgnoreCase("AAA")).thenReturn(java.util.Optional.of(driver));

        DriverDTO driverByCode = driverService.getDriverByCode("AAA");

        assertNotNull(driverByCode);
        assertEquals("AAA", driverByCode.code());
    }

    @Test
    void shouldReturnDriverDTOWhenUpdateDriver(){
        when(driverRepository.findById(1L)).thenReturn(java.util.Optional.of(driver));
        when(driverRepository.save(any(Driver.class))).thenReturn(driver);

        DriverDTO updateDriver = driverService.updateDriver(driver);

        assertNotNull(updateDriver);
        assertEquals("AAA", updateDriver.code());

        verify(driverRepository, times(1)).findById(1L);
        verify(driverRepository, times(1)).save(any(Driver.class));
    }

    @Test
    void shouldReturnDriverResponseWhenFindAllDrivers(){
        when(driverRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(driver)));

        DriverResponse drivers = driverService.getDrivers(0, 10);

        assertEquals(List.of(driverDTO), drivers.content());

        verify(driverRepository, times(1)).findAll(PageRequest.of(0, 10));
    }
}
