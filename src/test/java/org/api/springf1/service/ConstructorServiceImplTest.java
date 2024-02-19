package org.api.springf1.service;

import org.api.springf1.dto.ConstructorDTO;
import org.api.springf1.dto.ConstructorResponse;
import org.api.springf1.dto.DriverDTO;
import org.api.springf1.dto.DriverResponse;
import org.api.springf1.model.Constructor;
import org.api.springf1.model.Driver;
import org.api.springf1.repository.ConstructorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConstructorServiceImplTest {

    @Mock
    private ConstructorRepository constructorRepository;

    @InjectMocks
    private ConstructorServiceImpl constructorService;

    Constructor constructor;

    ConstructorDTO constructorDTO;

    @BeforeEach
    void setup(){
        constructor = Constructor.builder().id(1L).ref("AAA").name("aaaa").nationality("oooo").build();
        constructorDTO = ConstructorDTO.builder().id(1L).ref("AAA").name("aaaa").build();
    }

    @Test
    void shouldReturnConstructorDTOWhenCreateConstructor(){
        when(constructorRepository.save(any(Constructor.class))).thenReturn(constructor);

        ConstructorDTO saveConstructor = constructorService.saveConstructor(constructor);

        assertNotNull(saveConstructor);
        assertEquals("AAA", saveConstructor.ref());

        verify(constructorRepository, times(1)).save(any(Constructor.class));
    }

    @Test
    void shouldReturnConstructorDTOWhenFindConstructorByCode(){
        when(constructorRepository.findByRefIgnoreCase("AAA")).thenReturn(java.util.Optional.of(constructor));

        ConstructorDTO constructorByCode = constructorService.getConstructorByRef("AAA");

        assertNotNull(constructorByCode);
        assertEquals("AAA", constructorByCode.ref());
    }

    /*@Test
    void shouldReturnConstructorDTOWhenUpdateConstructor(){
        when(constructorRepository.findById(1L)).thenReturn(java.util.Optional.of(constructor));
        when(constructorRepository.save(any(Constructor.class))).thenReturn(constructor);

        ConstructorDTO updateConstructor = constructorService.updateConstructor(constructor);

        assertEquals("AAA", updateConstructor.ref());

        verify(constructorRepository, times(1)).findById(1L);
        verify(constructorRepository, times(1)).save(any(Constructor.class));
    }*/

/*
    @Test
    void shouldReturnConstructorResponseWhenFindAllConstructors(){
        when(constructorRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(constructor)));

        List<ConstructorDTO> constructors = constructorService.getConstructors();

        assertEquals(List.of(constructorDTO), constructors);

        verify(constructorRepository, times(1)).findAll(PageRequest.of(0, 10));
    }*/
}