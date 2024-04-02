package br.demattos.iury.msvoting.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "ms-employee")
public interface EmployeeResourceProxy {
  @GetMapping("/api/v1/employees/{cpf}")
  public ResponseEntity<Boolean> canVote
          (@PathVariable("cpf") String cpf);
}
