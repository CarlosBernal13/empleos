package com.example.jpa_empleos;

import com.example.jpa_empleos.models.EstatusVacante;
import com.example.jpa_empleos.models.Vacante;
import com.example.jpa_empleos.repository.VacantesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class JpaEmpleosApplication implements CommandLineRunner {

    @Autowired
    private VacantesRepository vacantesRepo; // <-- 1. Faltaba inyectar el repositorio

    public static void main(String[] args) {
        SpringApplication.run(JpaEmpleosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        buscarVacantesPorDestacadoEstatus();
    }

    /**
     * Query Method: Buscar Vacantes por Destacado y Estatus Ordenado por Id Desc
     */
    private void buscarVacantesPorDestacadoEstatus() {
        List<Vacante> vacantesAprobadasYDestacadas = vacantesRepo.findByDestacadoAndEstatusOrderByIdDesc(1, EstatusVacante.Aprobada);
        System.out.println("Registros encontrados: " + vacantesAprobadasYDestacadas.size());
        for (Vacante vacante : vacantesAprobadasYDestacadas) {
            System.out.println(vacante.getId() + ": " + vacante.getNombre() + " - " + vacante.getEstatus() + ", destacada: " + vacante.getDestacado());
        }
    }
}
