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
        buscarVacantesPorEstatus();
    }

    /**
     * Query Method: Buscar Vacantes por Estatus
     */
    private void buscarVacantesPorEstatus() {
        // 2. Verifica si en tu Enum EstatusVacante es Aprobada o APROBADA
        List<Vacante> vacantesAprobadas = vacantesRepo.findByEstatus(EstatusVacante.Aprobada);
        System.out.println("Registros encontrados: " + vacantesAprobadas.size());

        for (Vacante vacante : vacantesAprobadas) {
            System.out.println(vacante.getId() + ": " + vacante.getNombre() + " - " + vacante.getEstatus());
        }
    }
}
