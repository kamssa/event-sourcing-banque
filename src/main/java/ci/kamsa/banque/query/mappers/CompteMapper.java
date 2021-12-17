package ci.kamsa.banque.query.mappers;

import org.mapstruct.Mapper;

import ci.kamsa.banque.query.dto.CompteDTO;
import ci.kamsa.banque.query.dto.OperationDTO;
import ci.kamsa.banque.query.entities.Compte;
import ci.kamsa.banque.query.entities.Operation;

@Mapper(componentModel = "spring")
public interface CompteMapper {
CompteDTO fromCompte(Compte compte);
Compte fromCompteDTO(CompteDTO compteDTO);
OperationDTO fromOperation(Operation operation);
}
