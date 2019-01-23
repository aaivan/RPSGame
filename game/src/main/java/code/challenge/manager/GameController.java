package code.challenge.manager;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import code.challenge.entities.ResultsData;


@Controller
public class GameController {

	@Autowired
	private GameService gameService;
	
	@GetMapping(value = "/play", produces = "application/json")
	public String playRound(HttpServletRequest request, Model model) {
		String userIP = request.getRemoteAddr();
		
		if (StringUtils.isNotBlank(userIP)) {
			ResultsData playerRounds = gameService.playRound(userIP);
			model.addAttribute("playerRounds", playerRounds);
		}
		
		return "index";
	}
	
	@GetMapping(value = "/reset", produces = "application/json")
	public String resetGame(HttpServletRequest request, Model model) {
		String userIP = request.getRemoteAddr();
		
		if (StringUtils.isNotBlank(userIP)) {
			ResultsData playerRounds = gameService.resetGame(userIP);
			model.addAttribute("playerRounds", playerRounds);
		}
		
		return "index";
	}

}
