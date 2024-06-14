package com.example.ClinicaORM.controller;

import com.example.ClinicaORM.entity.Odontologo;
import com.example.ClinicaORM.entity.Paciente;
import com.example.ClinicaORM.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController //para trabajar sin tecnologia de vista
// @Controller<-- es controller pq vamos a usar una tecnologia de vista
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @PostMapping //--> nos permite persistir los datos que vienen desde la vista
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarPaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(id);
        if (pacienteBuscado.isPresent()) {
            Paciente pacienteActualizado = pacienteBuscado.get();
            pacienteActualizado.setNombre(paciente.getNombre());
            pacienteActualizado.setApellido(paciente.getApellido());
            pacienteActualizado.setCedula(paciente.getCedula());
            pacienteActualizado.setFechaIngreso(paciente.getFechaIngreso());
            pacienteActualizado.setDomicilio(paciente.getDomicilio());
            pacienteActualizado.setEmail(paciente.getEmail());

            pacienteService.actualizarPaciente(pacienteActualizado);
            return ResponseEntity.ok("Paciente actualizado con éxito");
        } else {
            return ResponseEntity.badRequest().body("Paciente no encontrado");
        }
    }

    @GetMapping("/buscar/id/{id}")
    public ResponseEntity<Optional<Paciente>> buscarPorPaciente(@PathVariable Long id){
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }
    @GetMapping("/buscar/email/{email}")
    public ResponseEntity<Optional<Paciente>> buscarPorEmail(@PathVariable String email){
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorEmail(email);
        if(pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado);
        }else{
            return  ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodos(){
        return ResponseEntity.ok(pacienteService.listarTodos());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(id);
        if (pacienteBuscado.isPresent()) {
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("Paciente eliminado con éxito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
