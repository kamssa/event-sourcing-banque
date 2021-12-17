package ci.kamsa.banque.query.utilitaire;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ci.kamsa.banque.query.service.ReplayerService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/query/account/")
@AllArgsConstructor
public class ReplayControllers {
	
 private ReplayerService replayerService;
 
 @GetMapping("/replayEvents")
 public String replay() {
	 replayerService.replay();
	 return "Success playing event";
 }
}
