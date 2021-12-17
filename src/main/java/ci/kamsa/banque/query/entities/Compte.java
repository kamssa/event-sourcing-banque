package ci.kamsa.banque.query.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import ci.kamsa.banque.coreapi.enumeted.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Compte {

  
	@Id
    private String id;
    private BigDecimal solde;
    private String devise;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    
}
