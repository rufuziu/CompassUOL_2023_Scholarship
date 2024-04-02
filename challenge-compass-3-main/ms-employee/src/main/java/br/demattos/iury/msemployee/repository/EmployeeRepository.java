package br.demattos.iury.msemployee.repository;

import br.demattos.iury.msemployee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
  Boolean existsByCpf(String cpf);
}
