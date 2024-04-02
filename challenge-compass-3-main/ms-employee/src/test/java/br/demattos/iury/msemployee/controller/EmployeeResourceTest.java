package br.demattos.iury.msemployee.controller;

import br.demattos.iury.msemployee.dto.EmployeeDTO;
import br.demattos.iury.msemployee.exceptions.employee_exce.EmployeeCpfAlreadyInUseExcepetion;
import br.demattos.iury.msemployee.exceptions.employee_exce.EmployeeCpfNotFound;
import br.demattos.iury.msemployee.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static br.demattos.iury.msemployee.common.EmployeeConstants.INVALID_EMPLOYEEDTO;
import static br.demattos.iury.msemployee.common.EmployeeConstants.VALID_EMPLOYEEDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeResource.class)
class EmployeeResourceTest {
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;
  @MockBean
  private EmployeeService service;

  @Test
  void createEmployee_withCpfNotRegistered_ReturnsCreated() throws Exception {
    EmployeeDTO createdEmp = new EmployeeDTO(VALID_EMPLOYEEDTO.getCpf());
    createdEmp.setId(1L);
    when(service.createEmployee(any())).thenReturn(createdEmp);
    mockMvc.perform(post("/api/v1/employees")
                    .content(objectMapper.writeValueAsString(
                            VALID_EMPLOYEEDTO))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(createdEmp.getId()))
            .andExpect(jsonPath("$.cpf").value(createdEmp.getCpf()));
  }

  @Test
  void createEmployee_withCpfRegistered_ReturnsConflict() throws Exception {
    when(service.createEmployee(any())).thenThrow(EmployeeCpfAlreadyInUseExcepetion.class);
    mockMvc.perform(post("/api/v1/employees")
                    .content(objectMapper.writeValueAsString(
                            VALID_EMPLOYEEDTO))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isConflict());
  }

  @Test
  void createEmployee_withInvalidCpf_ReturnsBadRequest() throws Exception {
    mockMvc.perform(post("/api/v1/employees")
                    .content(objectMapper.writeValueAsString(
                            INVALID_EMPLOYEEDTO))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
  }

  @Test
  void employeeCanVote_withCpfRegistered_ReturnsTrue() throws Exception {
    when(service.employeeCanVote(VALID_EMPLOYEEDTO.getCpf())).thenReturn(true);
    mockMvc.perform(get("/api/v1/employees/{cpf}",
                    VALID_EMPLOYEEDTO.getCpf()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").value(true));
  }

  @Test
  void employeeCanVote_withCpfNotRegistered_ReturnsNotFound() throws Exception {
    when(service.employeeCanVote(VALID_EMPLOYEEDTO.getCpf()))
            .thenThrow(EmployeeCpfNotFound.class);
    mockMvc.perform(get("/api/v1/employees/{cpf}",
                    VALID_EMPLOYEEDTO.getCpf()))
            .andExpect(status().isNotFound());
  }
}