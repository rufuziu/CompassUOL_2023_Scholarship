package br.demattos.iury.msproposal.common;

import br.demattos.iury.msproposal.dtos.ProposalNewDTO;
import br.demattos.iury.msproposal.dtos.ProposalDTO;
import br.demattos.iury.msproposal.enums.EResult;

import java.time.LocalDateTime;

public class ProposalConstants {
  private static LocalDateTime start = LocalDateTime.of(
          2023, 10, 13, 22, 0);
  private static LocalDateTime end = LocalDateTime.of(
          2023, 10, 13,
          22, 1);
  public static final ProposalNewDTO INVALID_PROPOSALDTO =
          new ProposalNewDTO();
  public static final ProposalNewDTO VALID_PROPOSALDTO_1 =
          new ProposalNewDTO("proposal 1", end);
  public static final ProposalNewDTO VALID_PROPOSALDTO_2 =
          new ProposalNewDTO("proposal 2", end);
  public static final ProposalNewDTO VALID_PROPOSALDTO_3 =
          new ProposalNewDTO("proposal 3", end);
  public static final ProposalNewDTO VALID_PROPOSALDTO_4 =
          new ProposalNewDTO("proposal 4", end);
  public static final ProposalNewDTO VALID_PROPOSALDTO_WITHOUTCLOSETIME =
          new ProposalNewDTO("proposal 5");
  public static final ProposalDTO POLLING_PROPOSALDTO =
          new ProposalDTO("proposal approved",
                  0L, 0L, start, end, EResult.POLLING);
  public static final ProposalDTO APPROVED_PROPOSALDTO =
          new ProposalDTO("proposal approved",
                  2L, 1L, start, end, EResult.APPROVED);
  public static final ProposalDTO REJECTED_PROPOSALDTO =
          new ProposalDTO("proposal rejected",
                  1L, 2L, start, end, EResult.REJECTED);
  public static final ProposalDTO DRAW_PROPOSALDTO =
          new ProposalDTO("proposal draw",
                  2L, 2L, start, end, EResult.DRAW);
}
