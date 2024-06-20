package com.example.ClinicaORM.service;

import com.example.ClinicaORM.entity.Domicilio;
import com.example.ClinicaORM.entity.Odontologo;
import com.example.ClinicaORM.entity.Paciente;
import com.example.ClinicaORM.entity.Turno;
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
public class TurnoServiceTest {
    @Autowired
    private TurnoService turnoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void registrarTurno() {
        Paciente paciente = new Paciente("John", "Doe", "12345678", LocalDate.of(2020, 1, 1),
                new Domicilio("Calle 123", 456, "Ciudad", "País"), "john.doe@example.com");
        paciente = pacienteService.guardarPaciente(paciente);

        Odontologo odontologo = new Odontologo("AB1234", "Dr. Ana", "Smith");
        odontologo = odontologoService.guardarOdontologo(odontologo);

        Turno turno = new Turno();
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        turno.setFecha(LocalDate.of(2024, 6, 25));

        Turno turnoGuardado = turnoService.registrarTurno(turno);

        assertNotNull(turnoGuardado);
        assertEquals(turno.getFecha(), turnoGuardado.getFecha());
        assertEquals(turno.getPaciente().getId(), turnoGuardado.getPaciente().getId());
        assertEquals(turno.getOdontologo().getId(), turnoGuardado.getOdontologo().getId());
    }

    @Test
    @Order(2)
    public void buscarTurnoPorId() {
        Long id = 1L;
        Optional<Turno> turnoBuscado = turnoService.buscarPorId(id);
        assertTrue(turnoBuscado.isPresent());
    }

    @Test
    @Order(3)
    public void actualizarTurno() {
        Long id = 1L;
        Turno turnoExistente = turnoService.buscarPorId(id).orElseThrow();

        LocalDate nuevaFecha = LocalDate.of(2024, 6, 26);
        turnoExistente.setFecha(nuevaFecha);

        turnoService.actualizarTurno(turnoExistente);

        Turno turnoActualizado = turnoService.buscarPorId(id).orElseThrow();
        assertEquals(nuevaFecha, turnoActualizado.getFecha());
    }

    @Test
    @Order(4)
    public void listarTodos() {
        List<Turno> listaTurnos = turnoService.listarTodos();
        assertFalse(listaTurnos.isEmpty());
    }

    @Test
    @Order(5)
    public void eliminarTurno() {
        Long id = 1L;
        turnoService.eliminarTurno(id);
        Optional<Turno> turnoEliminado = turnoService.buscarPorId(id);
        assertFalse(turnoEliminado.isPresent());
    }
}
