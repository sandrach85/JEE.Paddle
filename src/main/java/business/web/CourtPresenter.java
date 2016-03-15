package business.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import business.controllers.CourtController;
import business.wrapper.CourtState;

@Controller
public class CourtPresenter {
	
	@Autowired
	private CourtController courtController;
	
	
	public CourtPresenter(){
		
	}

	@RequestMapping("/list-courts")
	public String listCourts(Model model){
        model.addAttribute("courtList", courtController.showCourts());
        return "showCourtList";
	}
	
	@RequestMapping(value ="/create-court", method = RequestMethod.GET)
	public String createCourt(Model model){
		int numCourts = courtController.showCourts().size();
        model.addAttribute("court", new CourtState(numCourts+1, true));
        return "createCourt";
	}
	
	@RequestMapping(value = "/create-court", method = RequestMethod.POST)
	public String createUserSubmit(@ModelAttribute(value="court") CourtState court, Model model){
		courtController.createCourt(court.getCourtId());
		int numCourts = courtController.showCourts().size();
        model.addAttribute("court", new CourtState(numCourts+1, true));
		return "createCourt";
		
	}
}
