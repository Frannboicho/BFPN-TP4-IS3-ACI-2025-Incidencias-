package com.ejemplo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class Application {
    
    @Autowired
    private SaludoRepository saludoRepository;
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> holaMundo() {
        Map<String, Object> response = new HashMap<>();
        
        // Contar total de saludos en la BD
        long totalSaludos = saludoRepository.count();
        
        response.put("mensaje", "¡Hola Mundo desde Spring Boot con Docker y PostgreSQL!");
        response.put("estado", "Backend funcionando correctamente");
        response.put("tecnologia", "Spring Boot + Java 17 + Docker + PostgreSQL");
        response.put("totalSaludos", totalSaludos);
        response.put("timestamp", java.time.LocalDateTime.now().toString());
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/api/saludo/{nombre}")
    public ResponseEntity<Map<String, Object>> saludar(@PathVariable String nombre) {
        
        // ✅ INSERTAR EN LA BASE DE DATOS
        String mensajeSaludo = "¡Hola " + nombre + " desde el backend con BD!";
        Saludo nuevoSaludo = new Saludo(nombre, mensajeSaludo);
        
        // Guardar en PostgreSQL
        Saludo saludoGuardado = saludoRepository.save(nuevoSaludo);
        
        // Contar total de saludos
        long totalSaludos = saludoRepository.count();
        
        Map<String, Object> response = new HashMap<>();
        response.put("saludo", mensajeSaludo);
        response.put("saludoId", saludoGuardado.getId());
        response.put("fechaCreacion", saludoGuardado.getFechaCreacion().toString());
        response.put("totalSaludos", totalSaludos);
        response.put("mensaje", "✅ Saludo guardado en PostgreSQL");
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/api/saludos")
    public ResponseEntity<Map<String, Object>> obtenerTodosSaludos() {
        List<Saludo> saludos = saludoRepository.findAll();
        
        Map<String, Object> response = new HashMap<>();
        response.put("saludos", saludos);
        response.put("total", saludos.size());
        response.put("mensaje", "Lista de todos los saludos desde PostgreSQL");
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/api/saludos/{nombre}")
    public ResponseEntity<Map<String, Object>> obtenerSaludosPorNombre(@PathVariable String nombre) {
        List<Saludo> saludos = saludoRepository.findByNombre(nombre);
        
        Map<String, Object> response = new HashMap<>();
        response.put("saludos", saludos);
        response.put("total", saludos.size());
        response.put("nombre", nombre);
        response.put("mensaje", "Saludos para " + nombre + " desde PostgreSQL");
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/api/status")
    public ResponseEntity<Map<String, Object>> status() {
        long totalSaludos = saludoRepository.count();
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("database", "PostgreSQL conectado ✅");
        response.put("totalRegistros", totalSaludos);
        response.put("version", "1.0.0");
        response.put("timestamp", java.time.LocalDateTime.now().toString());
        
        return ResponseEntity.ok(response);
    }
}