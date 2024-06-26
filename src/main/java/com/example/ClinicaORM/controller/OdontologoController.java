package com.example.ClinicaORM.controller;

import com.example.ClinicaORM.entity.Odontologo;
import com.example.ClinicaORM.exception.ResourceNotFoundException;
import com.example.ClinicaORM.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    @Autowired
    private OdontologoService odontologoService;

    @PostMapping //--> nos permite persistir los datos que vienen desde la vista
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarOdontologo(@PathVariable Long id, @RequestBody Odontologo odontologo) {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(id);
        if (odontologoBuscado.isPresent()) {
            Odontologo odontologoActualizado = odontologoBuscado.get();
            odontologoActualizado.setNombre(odontologo.getNombre());
            odontologoActualizado.setApellido(odontologo.getApellido());
            odontologoActualizado.setMatricula(odontologo.getMatricula());

            odontologoService.actualizarOdontologo(odontologoActualizado);
            return ResponseEntity.ok("Odontólogo actualizado con éxito");
        } else {
            return ResponseEntity.badRequest().body("Odontólogo no encontrado");
        }
    }

    @GetMapping("/buscar/id/{id}")
    public ResponseEntity<Optional<Odontologo>> buscarPorOdontologo(@PathVariable Long id){
        return ResponseEntity.ok(odontologoService.buscarPorId(id));
    }
    @GetMapping("/buscar/matricula/{matricula}")
    public ResponseEntity<Optional<Odontologo>> buscarPorMatricula(@PathVariable String matricula){
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarPorMatricula(matricula);
        if(odontologoBuscado.isPresent()){
            return ResponseEntity.ok(odontologoBuscado);
        }else{
            return  ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarTodos(){
        return ResponseEntity.ok(odontologoService.listarTodos());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(id);
        if (odontologoBuscado.isPresent()) {
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok("odontologo eliminado con éxito");
        } else {
            throw new ResourceNotFoundException("Odontologo no encontrado");
            //return ResponseEntity.notFound().build();

        }
    }
}
