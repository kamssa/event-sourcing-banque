package ci.kamsa.banque.query.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CompteHistoriqueDTO {
private CompteDTO compteDTO;
private List<OperationDTO> operations;
}
