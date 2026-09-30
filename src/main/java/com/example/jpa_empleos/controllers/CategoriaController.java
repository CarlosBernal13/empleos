package com.example.jpa_empleos.controllers;
import com.example.jpa_empleos.models.Categoria;
import com.example.jpa_empleos.repository.CategoriasRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    private final CategoriasRepository categoriasRepo;
    public CategoriaController(CategoriasRepository categoriasRepo) {
        this.categoriasRepo = categoriasRepo;
    }
    // consultar todas las categorias
    @GetMapping
    public List<Categoria> obtenerTodas() {
        return (List<Categoria>) categoriasRepo.findAll();
    }
    //nueva cateogria
    @PostMapping
    public Categoria crearCategoria(@RequestBody Categoria categoria){
        return categoriasRepo.save(categoria);
    }
    //buscar categoria por ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerPorId(@PathVariable Integer id) {
        Optional<Categoria> categoriaBuscada = categoriasRepo.findById(id);
        if (categoriaBuscada.isPresent()) {
            return ResponseEntity.ok(categoriaBuscada.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Integer id, @RequestBody Categoria detallesCategoria) {
        Optional<Categoria> categoriaOpcional = categoriasRepo.findById(id);

        if (categoriaOpcional.isPresent()) {
            Categoria categoria = categoriaOpcional.get();
            categoria.setNombre(detallesCategoria.getNombre());
            categoria.setDescripcion(detallesCategoria.getDescripcion());
            Categoria categoriaActualizada = categoriasRepo.save(categoria);
            return ResponseEntity.ok(categoriaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //eliminar una categoria
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Integer id) {
        if (categoriasRepo.existsById(id)) {
            categoriasRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
