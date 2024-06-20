package com.example.ClinicaORM.service;

import com.example.ClinicaORM.entity.Odontologo;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void guardarOdontologo(){
        Odontologo odontologo= new Odontologo("123FRF", "Gina","Arias");
        Odontologo odontologoGuardado= odontologoService.guardarOdontologo(odontologo);
        assertEquals(1L,odontologoGuardado.getId());
    }
    @Test
    @Order(2)
    public void buscarOdontologoPorId(){
        Long id= 1L;
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarPorId(id);
        assertNotNull(odontologoBuscado.get());
    }
    @Test
    @Order(3)
    public void actualizarOdontologo(){
        Long id= 1L;
        Odontologo odontologo= new Odontologo(id,"4587","Ana","Gonzalez");
        odontologoService.actualizarOdontologo(odontologo);
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarPorId(id);
        assertEquals("Ana", odontologoBuscado.get().getNombre());
    }
    @Test
    @Order(4)
    public void ListarTodos(){
        List<Odontologo> listaOdontologos= odontologoService.listarTodos();
        assertEquals(1,listaOdontologos.size());
    }
    @Test
    @Order(5)
    public void eliminarOdontologo(){
        odontologoService.eliminarOdontologo(1L);
        Optional<Odontologo> odontologoEliminado= odontologoService.buscarPorId(1L);
        assertFalse(odontologoEliminado.isPresent());
    }
}
