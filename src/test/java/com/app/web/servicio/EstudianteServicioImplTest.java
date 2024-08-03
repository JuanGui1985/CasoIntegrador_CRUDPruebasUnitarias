package com.app.web.servicio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.web.entidad.Estudiante;
import com.app.web.repositorio.EstudianteRepositorio;

@ExtendWith(MockitoExtension.class)
public class EstudianteServicioImplTest {

     @Mock
    private EstudianteRepositorio estudianteRepositorio;

    @InjectMocks
    private EstudianteServicioImpl estudianteServicio;
    
    @Test
    void testActualizarEstudiante() {
        // Arrange
        Estudiante estudiante = new Estudiante(1L, "Nombre", "Apellido", "email@example.com");
        when(estudianteRepositorio.findById(any(Long.class))).thenReturn(estudiante);

        // Act
        estudianteServicio.actualizarEstudiante(estudiante);

        // Assert
        assertEquals(estudiante.getNombre(), "Nombre");
        assertEquals(estudiante.getApellido(), "Apellido");
        assertEquals(estudiante.getEmail(), "email@example.com");
    }

    @Test
    void testEliminarEstudiante() {
        // Arrange
        Estudiante estudiante = new Estudiante(1L, "Nombre", "Apellido", "email@example.com");
        when(estudianteRepositorio.findById(any(Long.class))).thenReturn(estudiante);

        // Act
        estudianteServicio.eliminarEstudiante(1L);

        // Assert
        assertNull(estudianteRepositorio.findById(1L));
    }

    @Test
    void testGuardarEstudiante() {
        // Arrange
        Estudiante estudiante = new Estudiante("Nombre", "Apellido", "email@example.com");

        // Act
        estudianteServicio.guardarEstudiante(estudiante);

        // Assert
        assertNotNull(estudiante.getId());
        assertEquals(estudiante.getNombre(), "Nombre");
        assertEquals(estudiante.getApellido(), "Apellido");
        assertEquals(estudiante.getEmail(), "email@example.com");
    }

    @Test
    void testListarTodosLosEstudiantes() {
        // Arrange
        List<Estudiante> estudiantes = new ArrayList<>();
        estudiantes.add(new Estudiante(1L, "Nombre1", "Apellido1", "email1@example.com"));
        estudiantes.add(new Estudiante(2L, "Nombre2", "Apellido2", "email2@example.com"));
        when(estudianteRepositorio.findAll()).thenReturn(estudiantes);

        // Act
        List<Estudiante> estudiantesList = estudianteServicio.listarTodosLosEstudiantes();

        // Assert
        assertEquals(estudiantes.size(), 2);
        assertEquals(estudiantes.get(0).getNombre(), "Nombre1");
        assertEquals(estudiantes.get(1).getApellido(), "Apellido2");
    }

    @Test
    void testObtenerEstudiantePorId() {
        // Arrange
        Estudiante estudiante = new Estudiante(1L, "Nombre", "Apellido", "email@example.com");
        when(estudianteRepositorio.findById(any(Long.class))).thenReturn(estudiante);

        // Act
        Estudiante estudianteObtenido = estudianteServicio.obtenerEstudiantePorId(1L);

        // Assert
        assertNotNull(estudianteObtenido);
        assertEquals(estudianteObtenido.getNombre(), "Nombre");
        assertEquals(estudianteObtenido.getApellido(), "Apellido");
        assertEquals(estudianteObtenido.getEmail(), "email@example.com");
    }
}
