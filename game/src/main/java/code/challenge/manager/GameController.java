package code.challenge.manager;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import code.challenge.entities.ResultsData;


@RestController
public class GameController {

	@Autowired
	private GameService gameService;

	@GetMapping(value = "/")
	public String showTitle() {

		return "Wellcome to ROCK-PAPER-SCISSORS Game!!";
	}

	@GetMapping(value = "/play", produces = "application/json")
	public ResponseEntity<ResultsData> playRound(HttpServletRequest request) {
		
		return new ResponseEntity<>(gameService.playRound(request.getRemoteAddr()), HttpStatus.OK);

	}
	
	@GetMapping(value = "/reset", produces = "application/json")
	public ResponseEntity<ResultsData> resetGame(HttpServletRequest request) {
		
		return new ResponseEntity<>(gameService.resetGame(request.getRemoteAddr()), HttpStatus.OK);
		
	}

}
