package com.example.ClinicaORM.service;

import com.example.ClinicaORM.entity.Domicilio;
import com.example.ClinicaORM.entity.Paciente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class PacienteIntegracionTest {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private  ObjectMapper objectMapper;

    private void cargarDatos(){
        Paciente paciente= pacienteService.guardarPaciente(new Paciente(1L,"Jorgito","pereyra","11111", LocalDate.of(2024,6,20),new Domicilio(1L,"calle falsa",123,"La Rioja","Argentina"),"jorge.pereyra@digitalhouse.com"));
    }
    @Test
    public void ListarTodosPacientesTest() throws Exception{
        cargarDatos();
        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.get("/pacientes").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }
    @Test
    public void RegistrarPacientesTest() throws Exception {
        Paciente paciente= new Paciente("Jorgito","pereyra","11111", LocalDate.of(2024,6,20),new Domicilio("calle falsa",123,"La Rioja","Argentina"),"jorge.pereyra@digitalhouse.com");

        String pacienteJson = objectMapper.writeValueAsString(paciente);

        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.post("/pacientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pacienteJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }

}
