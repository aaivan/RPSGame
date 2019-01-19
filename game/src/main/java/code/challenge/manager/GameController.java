package code.challenge.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import code.challenge.entities.ResultsData;


@RestController
public class GameController {

	@Autowired
	private GameService gameService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showTitle() {

		return "Wellcome to ROCK-PAPER-SCISSORS Game!!";
	}

	@RequestMapping(value = "/play", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<ResultsData> playRound() {
		
		return new ResponseEntity<>(gameService.playRound(), HttpStatus.OK);

	}
	
	@RequestMapping(value = "/reset", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<ResultsData> resetGame() {
		
		return new ResponseEntity<>(gameService.resetGame(), HttpStatus.OK);
		
	}

}
