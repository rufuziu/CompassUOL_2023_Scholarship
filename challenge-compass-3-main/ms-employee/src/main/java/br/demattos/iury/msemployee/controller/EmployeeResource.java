package br.demattos.iury.msemployee.controller;

import br.demattos.iury.msemployee.dto.EmployeeDTO;
import br.demattos.iury.msemployee.service.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/")
@Validated
public class EmployeeResource {
  private final EmployeeService service;

  public EmployeeResource(EmployeeService service) {
    this.service = service;
  }

  @PostMapping("v1/employees")
  public ResponseEntity<EmployeeDTO> create(@RequestBody
                                            @Valid EmployeeDTO employeeDTO) {
    employeeDTO.setCpf(
            employeeDTO.getCpf()
                    .replaceAll("[^0-9]", ""));
    EmployeeDTO createdEmp = service.createEmployee(employeeDTO);
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(createdEmp);
  }

  @GetMapping("v1/employees/{cpf}")
  public ResponseEntity<Boolean>
  canVote(@PathVariable(name = "cpf")
          @NotBlank(message = "CPF can't be blank") @CPF String cpf) {
    return ResponseEntity.ok(service.employeeCanVote(cpf));
  }

}
