package code.challenge.manager;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import code.challenge.common.ApplicationConstants;
import code.challenge.entities.ResultsData;


@Controller
public class GameController {

	@Autowired
	private GameService gameService;
	
	@GetMapping(value = "/play", produces = "application/json")
	public String playRound(HttpServletRequest request, Model model) {
		String userIP = request.getRemoteAddr();
		
		if (StringUtils.isNotBlank(userIP)) {
			ResultsData resultsData = gameService.playRound(userIP);
			model.addAttribute(ApplicationConstants.RESULTS_DATA_RESPONSE, resultsData);
		}
		
		return ApplicationConstants.INDEX_JSP;
	}
	
	@GetMapping(value = {"/", "/reset"}, produces = "application/json")
	public String resetGame(HttpServletRequest request, Model model) {
		String userIP = request.getRemoteAddr();
		
		if (StringUtils.isNotBlank(userIP)) {
			ResultsData resultsData = gameService.resetGame(userIP);
			model.addAttribute(ApplicationConstants.RESULTS_DATA_RESPONSE, resultsData);
		}
		
		return ApplicationConstants.INDEX_JSP;
	}

}
