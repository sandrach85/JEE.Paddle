package business.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import business.controllers.TrainingController;
import business.wrapper.TrainingWrapper;


@Controller
public class TrainingPresenter {

	@Autowired
	private TrainingController trainingController;
		
	public TrainingPresenter(){
		
	}
	



	@RequestMapping("/training/list-trainings")
	public String listTraining(Model model){
		model.addAttribute("trainingList", trainingController.showTraining());
		return "training/showTrainingList";
	}
	
	@RequestMapping(value ="/training/create-training", method = RequestMethod.GET)
	public String createTraining(Model model){
        model.addAttribute("training", new TrainingWrapper());
        return "training/createTraining";
	}
	
	@RequestMapping(value = "/training/create-training", method = RequestMethod.POST)
	public String createTrainingSubmit(@ModelAttribute(value="training") TrainingWrapper trainingWrapper, Model model){
		trainingController.createTraining(trainingWrapper);
		//int numCourts = courtController.showCourts().size();
        //model.addAttribute("court", new CourtState(numCourts+1, true));
		return this.createTraining(model);
		
	}
}
