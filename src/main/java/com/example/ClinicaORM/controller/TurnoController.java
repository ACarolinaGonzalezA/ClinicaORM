package com.example.ClinicaORM.controller;

import com.example.ClinicaORM.entity.Turno;
import com.example.ClinicaORM.entity.Paciente;
import com.example.ClinicaORM.entity.Odontologo;
import com.example.ClinicaORM.exception.BadRequestException;
import com.example.ClinicaORM.service.OdontologoService;
import com.example.ClinicaORM.service.PacienteService;
import com.example.ClinicaORM.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<Turno> guardarTurno(@RequestBody Turno turno) throws BadRequestException  {
        // Validación inicial para asegurarnos de que los IDs de paciente y odontólogo no son nulos
        if (turno.getPaciente() == null || turno.getPaciente().getId() == null) {
            throw new BadRequestException("El campo 'paciente' o 'id' del paciente no puede ser nulo.");
        }
        if (turno.getOdontologo() == null || turno.getOdontologo().getId() == null) {
            throw new BadRequestException("El campo 'odontologo' o 'id' del odontólogo no puede ser nulo.");
        }
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorId(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarPorId(turno.getOdontologo().getId());

        if(pacienteBuscado.isPresent()&&odontologoBuscado.isPresent()){
            turno.setPaciente(pacienteBuscado.get());
            turno.setOdontologo(odontologoBuscado.get());
            return ResponseEntity.ok(turnoService.registrarTurno(turno));
        }else{
            throw new BadRequestException("El paciente o el odontólogo no existen.");
            //return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping
    public ResponseEntity<List<Turno>> buscarTodos(){
        return ResponseEntity.ok(turnoService.listarTodos());
    }

    @GetMapping("/buscar/id/{id}")
    public ResponseEntity<Optional<Turno>> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(turnoService.buscarPorId(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id){
        Optional<Turno> turnoBuscado= turnoService.buscarPorId(id);
        if(turnoBuscado.isPresent()){
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Turno eliminado con exito");
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarTurno(@PathVariable Long id, @RequestBody Turno turno) {
        Optional<Turno> turnoBuscado = turnoService.buscarPorId(id);
        if (turnoBuscado.isPresent()) {
            Turno turnoExistente = turnoBuscado.get();

            // Validar y asignar Paciente
            Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(turno.getPaciente().getId());
            if (pacienteBuscado.isPresent()) {
                turnoExistente.setPaciente(pacienteBuscado.get());
            } else {
                return ResponseEntity.badRequest().body("Paciente no encontrado");
            }

            // Validar y asignar Odontologo
            Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(turno.getOdontologo().getId());
            if (odontologoBuscado.isPresent()) {
                turnoExistente.setOdontologo(odontologoBuscado.get());
            } else {
                return ResponseEntity.badRequest().body("Odontólogo no encontrado");
            }

            // Asignar otros campos actualizables
            turnoExistente.setFecha(turno.getFecha());

            // Guardar la actualización
            turnoService.actualizarTurno(turnoExistente);
            return ResponseEntity.ok("Turno actualizado con éxito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
