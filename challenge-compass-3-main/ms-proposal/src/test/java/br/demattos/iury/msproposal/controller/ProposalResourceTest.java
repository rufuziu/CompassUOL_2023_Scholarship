package br.demattos.iury.msproposal.controller;

import br.demattos.iury.msproposal.dtos.ProposalNewDTO;
import br.demattos.iury.msproposal.exceptions.proposal_exce.ProposalAlreadyExistsException;
import br.demattos.iury.msproposal.services.ProposalService;
import br.demattos.iury.msproposal.services.VoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static br.demattos.iury.msproposal.common.ProposalConstants.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProposalResource.class)
class ProposalResourceTest {
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;
  @MockBean
  private ProposalService proposalService;
  @MockBean
  private VoteService voteService;

  @Test
  void createProposal_withValidData_ReturnsCreatedProposalDTO() throws
          Exception {
    ProposalNewDTO createProp = VALID_PROPOSALDTO_1;
    createProp.setId(1L);
    when(proposalService.createProposal(any())).thenReturn(createProp);
    mockMvc.perform(post("/api/v1/proposals")
                    .content(objectMapper.writeValueAsString(VALID_PROPOSALDTO_1))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(createProp.getId()))
            .andExpect(jsonPath("$.description").value(createProp.getDescription()))
            .andExpect(jsonPath("$.closeTime").isNotEmpty());
  }

  @Test
  void createProposal_withRepeatedData_ReturnsConflict() throws
          Exception {
    when(proposalService.createProposal(any())).thenThrow(ProposalAlreadyExistsException.class);
    mockMvc.perform(post("/api/v1/proposals")
                    .content(objectMapper.writeValueAsString(VALID_PROPOSALDTO_1))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isConflict());
  }

  @Test
  void createProposal_withInvalidData_ReturnsBadRequest() throws
          Exception {
    mockMvc.perform(post("/api/v1/proposals")
                    .content(objectMapper.writeValueAsString(INVALID_PROPOSALDTO))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
  }

  @Test
  void getAllPollingProposals_ReturnsProposalDTOList() throws Exception {
    List<ProposalNewDTO> list = new ArrayList<>();
    list.add(VALID_PROPOSALDTO_1);
    list.add(VALID_PROPOSALDTO_1);
    list.add(VALID_PROPOSALDTO_1);
    list.add(VALID_PROPOSALDTO_1);
    when(proposalService.getPollingProposals()).thenReturn(list);
    mockMvc.perform(get("/api/v1/proposals"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isNotEmpty());
  }
}