package com.app.web.controlador;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.app.web.entidad.*;
import com.app.web.servicio.EstudianteServicio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class EstudianteControladorTest {

    @Mock
    private EstudianteServicio estudianteServicio;

    @InjectMocks
    private EstudianteControlador estudianteControlador;

    @Test
    void testActualizarEstudiante() {
        // Arrange
        Estudiante estudiante = new Estudiante(1L, "Nombre", "Apellido", "email@example.com");
        when(estudianteServicio.actualizarEstudiante(any(Estudiante.class))).thenReturn(estudiante);

        // Act
        ResponseEntity<Estudiante> response = estudianteControlador.actualizarEstudiante(estudiante);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(estudiante.getNombre(), response.getBody().getNombre());
        assertEquals(estudiante.getApellido(), response.getBody().getApellido());
        assertEquals(estudiante.getEmail(), response.getBody().getEmail());
    }

    @Test
    void testCrearEstudianteFormulario() {
        // Arrange
        Estudiante estudiante = new Estudiante("Nombre", "Apellido", "email@example.com");

        // Act
        ResponseEntity<Estudiante> response = estudianteControlador.crearEstudianteFormulario(estudiante);

        // Assert
        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(estudiante.getNombre(), response.getBody().getNombre());
        assertEquals(estudiante.getApellido(), response.getBody().getApellido());
        assertEquals(estudiante.getEmail(), response.getBody().getEmail());
    }

    @Test
    void testEliminarEstudiante() {
        // Arrange
        Long id = 1L;
        when(estudianteServicio.eliminarEstudiante(any(Long.class))).thenReturn(true);

        // Act
        ResponseEntity<Void> response = estudianteControlador.eliminarEstudiante(id);

        // Assert
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void testGuardarEstudiante() {
        // Arrange
        Estudiante estudiante = new Estudiante("Nombre", "Apellido", "email@example.com");
        when(estudianteServicio.guardarEstudiante(any(Estudiante.class))).thenReturn(estudiante);

        // Act
        ResponseEntity<Estudiante> response = estudianteControlador.guardarEstudiante(estudiante);

        // Assert
        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(estudiante.getNombre(), response.getBody().getNombre());
        assertEquals(estudiante.getApellido(), response.getBody().getApellido());
        assertEquals(estudiante.getEmail(), response.getBody().getEmail());
    }

    @Test
    void testListarEstudiantes() {
        // Arrange
        List<Estudiante> estudiantes = new ArrayList<>();
        estudiantes.add(new Estudiante(1L, "Nombre1", "Apellido1", "email1@example.com"));
        estudiantes.add(new Estudiante(2L, "Nombre2", "Apellido2", "email2@example.com"));
        when(estudianteServicio.listarEstudiantes()).thenReturn(estudiantes);

        // Act
        ResponseEntity<List<Estudiante>> response = estudianteControlador.listarEstudiantes();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testMostrarFormularioDeEditar() {
        // Arrange
        Long id = 1L;
        Estudiante estudiante = new Estudiante(1L, "Nombre", "Apellido", "email@example.com");
        when(estudianteServicio.obtenerEstudiantePorId(any(Long.class))).thenReturn(estudiante);

        // Act
        ResponseEntity<Estudiante> response = estudianteControlador.mostrarFormularioDeEditar(id);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(estudiante.getNombre(), response.getBody().getNombre());
        assertEquals(estudiante.getApellido(), response.getBody().getApellido());
        assertEquals(estudiante.getEmail(), response.getBody().getEmail());
    }
}
