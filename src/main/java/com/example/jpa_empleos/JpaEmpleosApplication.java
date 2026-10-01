package com.example.jpa_empleos;

import com.example.jpa_empleos.models.Categoria;
import com.example.jpa_empleos.repository.CategoriasJPARepository;
import com.example.jpa_empleos.repository.CategoriasRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class JpaEmpleosApplication implements CommandLineRunner {
    private final CategoriasRepository categoriasRepo;
    private final CategoriasJPARepository categoriasJPARepo;

    public JpaEmpleosApplication(CategoriasRepository categoriasRepo, CategoriasJPARepository categoriasJPARepo) {
        this.categoriasRepo = categoriasRepo;
        this.categoriasJPARepo = categoriasJPARepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(JpaEmpleosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        buscarTodosPaginacion();
    }

    /**
     * Método findAll - Interfaz JPARepository
     */
    private void buscarTodasJPA() {
        List<Categoria> categorias = categoriasJPARepo.findAll();
        for (Categoria categoria : categorias) {
            System.out.println(categoria.getId() + " " + categoria.getNombre());
        }
    }

    /**
     * Método deleteAllInBatch [Usar con precaución] - Interfaz JPARepository
     */
    private void borrarTodasEnBloque() {
        categoriasJPARepo.deleteAllInBatch();
    }

    /**
     * Metodo findAll [Ordenados por un campo] - Interfaz PagingAndSortingRepository
     */
    private void buscarTodosOrdenados() {
        List<Categoria> categorias = categoriasJPARepo.findAll(Sort.by("nombre"));
        for (Categoria categoria : categorias) {
            System.out.println(categoria.getId() + " " + categoria.getNombre());
        }
    }

    /**
     * Metodo findAll [Ordenados por un campo] - Interfaz PagingAndSortingRepository
     */
    private void buscarTodosOrdenadosdecendente() {
        List<Categoria> categorias = categoriasJPARepo.findAll(Sort.by("nombre").descending());
        for (Categoria categoria : categorias) {
            System.out.println(categoria.getId() + " " + categoria.getNombre());
        }
    }

    /**
     * Metodo findAll [Con Paginación] - Interfaz PagingAndSortingRepository
     */
    private void buscarTodosPaginacion() {
        //Page<Categoria> page = categoriasJPARepo.findAll(PageRequest.of(0, 5));
        //Page<Categoria> page = categoriasJPARepo.findAll(PageRequest.of(1, 5));
        //Page<Categoria> page = categoriasJPARepo.findAll(PageRequest.of(2, 5));
        Page<Categoria> page = categoriasJPARepo.findAll(PageRequest.of(3, 5));
        System.out.println("Total Registros: " + page.getTotalElements());
        System.out.println("Total Paginas: " + page.getTotalPages());
        for (Categoria c : page.getContent()) {
            System.out.println(c.getId() + " " + c.getNombre());
        }
    }
}
