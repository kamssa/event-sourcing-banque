package ci.kamsa.banque.query.queries;

import lombok.Data;

@Data
public class GetCompteOperationsQuery {
private String compteId;

public GetCompteOperationsQuery(String compteId) {
	super();
	this.compteId = compteId;
}

}
