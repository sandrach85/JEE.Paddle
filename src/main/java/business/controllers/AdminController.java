package business.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import data.services.GenericService;

@Controller
public class AdminController {
    
    private GenericService genericService;
    
    @Autowired
    public void setGenericService(GenericService genericService) {
        this.genericService = genericService;
    }

    public void deleteAllExceptAdmin() {
        genericService.deleteAllExceptAdmin();
    }

}
