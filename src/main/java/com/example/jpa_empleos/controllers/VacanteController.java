package com.example.jpa_empleos.controllers;

import com.example.jpa_empleos.models.EstatusVacante;
import com.example.jpa_empleos.models.Vacante;
import com.example.jpa_empleos.repository.VacantesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jpa-vacante")
public class VacanteController {

    @Autowired
    private VacantesRepository vacantesRepo;

    @GetMapping("/estatus-varios")
    public List<Vacante> buscarPorVariosEstatus(@RequestParam("estatus") List<EstatusVacante> estatus) {
        return vacantesRepo.findByEstatusIn(estatus);
    }

    @GetMapping("/salario")
    public List<Vacante> buscarPorRangoSalario(
            @RequestParam("minimo") Double minimo,
            @RequestParam("maximo") Double maximo) {
        return vacantesRepo.findBySalarioBetween(minimo, maximo);
    }

    @GetMapping("/salario/desc")
    public List<Vacante> buscarPorRangoSalarioDesc(
            @RequestParam("minimo") Double minimo,
            @RequestParam("maximo") Double maximo) {
        return vacantesRepo.findBySalarioBetweenOrderBySalarioDesc(minimo, maximo);
    }

    @GetMapping("/destacadas")
    public List<Vacante> buscarDestacadasYEstatus(
            @RequestParam("destacado") int destacado,
            @RequestParam("estatus") EstatusVacante estatus) {
        return vacantesRepo.findByDestacadoAndEstatusOrderByIdDesc(destacado, estatus);
    }

    @GetMapping("/estatus")
    public List<Vacante> buscarPorEstatus(@RequestParam("estatus") EstatusVacante estatus) {
        return vacantesRepo.findByEstatus(estatus);
    }
}