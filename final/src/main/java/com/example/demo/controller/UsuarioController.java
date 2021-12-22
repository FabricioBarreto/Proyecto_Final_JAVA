package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import com.example.demo.entity.Emprendimiento;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.EmprendimientoRepository;
import com.example.demo.repository.UsuarioRepository;

import com.example.demo.repository.VotoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController{

    private final UsuarioRepository usuarioRepository;
    private final VotoRepository votoRepository;
    private final EmprendimientoRepository emprendimientoRepository;

    public UsuarioController(UsuarioRepository usuarioRepository, VotoRepository votoRepository, EmprendimientoRepository emprendimientoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.votoRepository = votoRepository;
        this.emprendimientoRepository = emprendimientoRepository;
    }

    // crear un nuevo usuarios
    @PostMapping
    public ResponseEntity<?> nuevoUsuario(@RequestBody @Valid  Usuario usuario) {
        return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }

    // Listar todos los usuarios
    @GetMapping
    public ResponseEntity<?> obtenerTodos(){
        return new ResponseEntity<>(usuarioRepository.findAll(), HttpStatus.OK);
    }

    // Modificar usuario
    @PutMapping (value = "/{usuarioId}/{activar}/{emprendimientoId}")
    public Usuario modificarUsuario (@PathVariable("usuarioId") Long usuarioId,
                                    @PathVariable(name = "emprendimientoId")Long empId,
                                     @PathVariable boolean activar,
                                     @RequestBody @Valid Usuario usuarioRecibido) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        usuario.setNombre(usuarioRecibido.getNombre());
        usuario.setApellido(usuarioRecibido.getApellido());
        usuario.setCiudad(usuarioRecibido.getCiudad());
        usuario.setProvincia(usuarioRecibido.getProvincia());
        usuario.setPais(usuarioRecibido.getPais());
        usuario.setActivo(activar);
        if (empId != null) {
            Emprendimiento emprendimiento = emprendimientoRepository.findById(empId)
                    .orElseThrow(() -> new EntityNotFoundException("Emprendimiemto no encontrado"));
            usuario.setOwner(emprendimiento);
        }
        return usuarioRepository.save(usuario);
    }

    // Eliminar usuario
    @DeleteMapping(value = "{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        try {
            usuarioRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("No se encuentro el usuario a eliminar");
        }
    }

    @GetMapping (value = "/{ciudad}")
    public ResponseEntity<?> buscarPorCiudad (@PathVariable String ciudad){
        return new ResponseEntity<>(usuarioRepository.findByCiudad(ciudad), HttpStatus.OK);
    }

    @GetMapping (value = "/{fecha}")
    public ResponseEntity<?> buscarPorFecha (@PathVariable String fecha){
        LocalDateTime fechaABuscar = LocalDateTime.parse(fecha);
        List<Usuario> listaDeUsuarios = usuarioRepository.findAll();
        List<Usuario> listaFiltrada = listaDeUsuarios.stream()
                .filter(p -> UsuarioController.comparadorDeFechas(fechaABuscar, p.getFechaCreacion())<=0)
                .collect(Collectors.toList());
        return new ResponseEntity<>(listaFiltrada, HttpStatus.OK);
    }

    public static int comparadorDeFechas (LocalDateTime fechaABuscar, LocalDateTime fechaDeCreacion){
        return fechaABuscar.compareTo(fechaDeCreacion);
    }

    // Listar los Votos de Un usuario
    @GetMapping("votos/{id}")
    public ResponseEntity<?> votosDeUnUsuario(@PathVariable("id") Long id) {
        return new ResponseEntity<>(votoRepository.findByVotanteId(id), HttpStatus.OK);
    }
}