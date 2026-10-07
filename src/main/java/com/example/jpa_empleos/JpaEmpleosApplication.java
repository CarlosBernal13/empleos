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
        buscarVacantesSalario();
    }

    /**
     * Query Method: Buscar Vacantes rango de Salario (Between)
     */
    private void buscarVacantesSalario() {
        List<Vacante> vacantesSalariosEntre7000y14000 = vacantesRepo.findBySalarioBetween(7000.0, 14000.0);
        System.out.println("Registros encontrados: " + vacantesSalariosEntre7000y14000.size());
        for (Vacante vacante : vacantesSalariosEntre7000y14000) {
            System.out.println(vacante.getId() + ": " + vacante.getNombre() + " - $" + vacante.getSalario());
        } System.out.println("-".repeat(100));
        List<Vacante> vacantesSalariosEntre7000y14000Desc = vacantesRepo.findBySalarioBetweenOrderBySalarioDesc(7000.0, 14000.0);
        System.out.println("Registros encontrados: " + vacantesSalariosEntre7000y14000Desc.size());
        for (Vacante vacante : vacantesSalariosEntre7000y14000Desc) {
            System.out.println(vacante.getId() + ": " + vacante.getNombre() + " - $" + vacante.getSalario());
        }
    }
}
