package api;

import java.util.Calendar;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import data.daos.DaosService;

public class TrainingResourceFuncionalITesting {
    
    @Autowired
    private DaosService daosService;
    
    @Test
    public void testCreateTraining(){
      /*  Calendar date1 = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();
        date2.set(date1.get(Calendar.YEAR), date1.get(Calendar.MONTH)+2, date1.get(Calendar.DATE), date1.get(Calendar.HOUR_OF_DAY), date1.get(Calendar.MINUTE));
        CourtState court = new CourtState(1, true);
        Training training = (Training) daosService.getMap().get("u62");
        UserWrapper user = (UserWrapper) daosService.getMap().get("u2");
        TrainingWrapper trainingWrapper = new TrainingWrapper(date1,date2,court,user);
        assertTrue();*/
        
    }

}
