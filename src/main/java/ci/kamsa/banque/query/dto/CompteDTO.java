package ci.kamsa.banque.query.dto;

import java.math.BigDecimal;
import ci.kamsa.banque.coreapi.enumeted.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CompteDTO {
	
    private String id;
    private BigDecimal solde;
    private String devise;
    private AccountStatus status;
}
