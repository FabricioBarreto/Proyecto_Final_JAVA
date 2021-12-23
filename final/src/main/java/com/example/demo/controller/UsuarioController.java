package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import com.example.demo.entity.Emprendimiento;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.Voto;
import com.example.demo.repository.EmprendimientoRepository;
import com.example.demo.repository.UsuarioRepository;

import com.example.demo.repository.VotoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
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
    public ResponseEntity<?> mostrarUsuarios(
            @RequestParam(name = "ciudad", required = false) String ciudad,
            @RequestParam(name = "fecha", required = false) String fecha){
        if (ciudad != null){
            return new ResponseEntity<>(usuarioRepository.findByCiudad(ciudad), HttpStatus.OK);
        }
        if (fecha != null){
            LocalDateTime fechaABuscar = LocalDateTime.parse(fecha);
            List<Usuario> listaDeUsuarios = usuarioRepository.findAll();
            List<Usuario> listaFiltrada = listaDeUsuarios.stream()
                    .filter(p -> UsuarioController.comparadorDeFechas(fechaABuscar, p.getFechaCreacion())<=0)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(listaFiltrada, HttpStatus.OK);
        }
        return new ResponseEntity<>(usuarioRepository.findAll(), HttpStatus.OK);
    }

    public static int comparadorDeFechas (LocalDateTime fechaABuscar, LocalDateTime fechaDeCreacion){
        return fechaABuscar.compareTo(fechaDeCreacion);}

    // Modificar usuario
    @PutMapping (value = "/{id}")
    public Usuario modificarUsuario (@PathVariable("id") Long id,
                                     @RequestBody @Valid Usuario usuarioRecibido) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        usuario.setNombre(usuarioRecibido.getNombre());
        usuario.setApellido(usuarioRecibido.getApellido());
        usuario.setPassword(usuario.getPassword());
        usuario.setCiudad(usuarioRecibido.getCiudad());
        usuario.setProvincia(usuarioRecibido.getProvincia());
        usuario.setPais(usuarioRecibido.getPais());
        usuario.setUsuarioEnum(usuario.getUsuarioEnum());
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

    @PostMapping ("votar/{usuaarioId}/{empId}")
    public ResponseEntity<?> votar(@PathVariable ("empId") Long empId,
                                   @PathVariable ("usuaarioId")Long usuaarioId,
                                   @RequestBody @Valid Voto voto){
        Emprendimiento emprendimiento = emprendimientoRepository.findById(empId)
                .orElseThrow(()-> new EntityNotFoundException("Emprendimiento no encontrado"));
        Usuario usuario = usuarioRepository.findById(usuaarioId)
                .orElseThrow(()-> new EntityNotFoundException("Usuario no encontrado"));
        voto.setVotante(usuario);
        voto.setEmprendimiento(emprendimiento);
        emprendimiento.setCantidadDeVotos(emprendimiento.getCantidadDeVotos()+1);
        return new ResponseEntity<>(votoRepository.save(voto), HttpStatus.CREATED);
    }

    // Listar los Votos de Un usuario
    @GetMapping("votos/{id}")
    public ResponseEntity<?> mostrarVotosDeUnUsuario(@PathVariable("id") Long id) {
        return new ResponseEntity<>(votoRepository.findByVotanteId(id), HttpStatus.OK);
    }

}