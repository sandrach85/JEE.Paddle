package data.daos;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import data.entities.Reserve;
import data.entities.Training;
import data.entities.User;

@Repository
public class TrainingDaoImpl implements TrainingExtended{
	
	@Autowired
	private TrainingDao trainingDao;
	@Autowired
	private ReserveDao reserveDao;
	
	@Override
	public void deleteUserTraining(int idT, int idU) {
		List<User> listUsers = trainingDao.findUsersTraining(idT);
		Training training = trainingDao.findById(idT);
		for(int i=0; i<listUsers.size();i++){
			if (listUsers.get(i).getId()==idU){
				training.deleteUser(idU);
				trainingDao.save(training);
			}
		}
	}
	
	
	@Override
	public void deleteTrainingAndReserves(int id) {
		Training training = trainingDao.findById(id);
		Reserve reserve = reserveDao.findByCourtAndDate(training.getCourt(), training.getDateIni());
		long dateMilis = training.getDateIni().getTimeInMillis();
		long weekDel = 1000*60*60*24*7;
		Calendar dateDel=training.getDateIni();
		for (int i = 0; i<training.daysBetweenDates(training.getDateIni(), training.getDateEnd()); i++){
				dateDel.setTimeInMillis(dateMilis+(weekDel*i));
				reserve = reserveDao.findByCourtAndDate(training.getCourt(), dateDel);
				reserveDao.delete(reserve);				
		}
		trainingDao.delete(training.getId());
	}

	

}
