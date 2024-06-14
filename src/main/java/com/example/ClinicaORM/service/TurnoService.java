package com.example.ClinicaORM.service;

import com.example.ClinicaORM.entity.Turno;
import com.example.ClinicaORM.repository.TurnoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;

    public Turno registrarTurno(Turno turno){
        return turnoRepository.save(turno);
    }
   public Optional<Turno> buscarPorId(Long id){
        return turnoRepository.findById(id);
   }
   public void eliminarTurno(Long id){
       Turno turno = turnoRepository.findById(id)
               .orElseThrow(() -> new EntityNotFoundException("Turno no encontrado"));

       turnoRepository.delete(turno);
    }
    public List<Turno> listarTodos(){
        return turnoRepository.findAll();
    }
    public void actualizarTurno(Turno turno){
        turnoRepository.save(turno);
    }
}
