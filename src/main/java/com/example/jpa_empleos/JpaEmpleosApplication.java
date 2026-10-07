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
        buscarVacantesVariosEstatus();
    }

    /**
     * Query Method: Buscar Vacantes por varios Estatus (In)
     */
    private void buscarVacantesVariosEstatus() {
        EstatusVacante[] listaEstatus = new EstatusVacante[]{EstatusVacante.Eliminada, EstatusVacante.Creada};
        List<Vacante> vacantesVariosEstatus = vacantesRepo.findByEstatusIn(listaEstatus);
        System.out.println("Registros encontrados: " + vacantesVariosEstatus.size());
        for (Vacante vacante : vacantesVariosEstatus) {
            System.out.println(vacante.getId() + ": " + vacante.getNombre() + " - " + vacante.getEstatus());
        }
    }
}
