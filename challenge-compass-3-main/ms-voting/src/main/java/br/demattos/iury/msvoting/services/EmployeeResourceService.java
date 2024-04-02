package br.demattos.iury.msvoting.services;

import br.demattos.iury.msvoting.exceptions.employee_exce.EmployeeCpfNotFound;
import br.demattos.iury.msvoting.proxies.EmployeeResourceProxy;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class EmployeeResourceService {
  private final EmployeeResourceProxy employeeResourceProxy;

  public EmployeeResourceService(EmployeeResourceProxy employeeResourceProxy) {
    this.employeeResourceProxy = employeeResourceProxy;
  }

  public Boolean canEmployeeVoteBoolean(String cpf) {
    try {
      return employeeResourceProxy.canVote(cpf).getBody();
    } catch (FeignException.FeignClientException e) {
      if (HttpStatus.NOT_FOUND.value() == e.status()) {
        throw new EmployeeCpfNotFound("Não encontrado");
      }
    }
    return null;
  }

  public String canEmployeeVoteString(String cpf) {
    try {
      return Boolean.TRUE.equals(employeeResourceProxy
              .canVote(cpf).getBody()) ?
              "pode_votar" :
              "nao_pode_votar";
    } catch (FeignException.FeignClientException e) {
      if (HttpStatus.NOT_FOUND.value() == e.status()) {
        throw new EmployeeCpfNotFound("Não encontrado");
      }
    }
    return null;
  }
}

