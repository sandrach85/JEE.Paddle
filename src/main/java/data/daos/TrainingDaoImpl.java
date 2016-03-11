package data.daos;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import data.entities.Court;
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
	public Training createTraining(Calendar dateIni, Calendar dateEnd, Court court, User trainer, int id){
		Training training = trainingDao.findById(id);
		if (training==null){
			Training trainingC = new Training(dateIni, dateEnd, court, trainer);
			trainingDao.save(trainingC);
			createReserves(dateIni, dateEnd, court, trainingC.getId());
			return trainingC;
		}
		else
			return training;
	}
	
	private void createReserves(Calendar dateI, Calendar dateE, Court court, int id) {
		long dateMilis = dateI.getTimeInMillis();
		long weekAdd = 1000 * 60 * 60 * 24 * 7;
		Calendar dateAdd = dateI;
		//String dateAux2 = new SimpleDateFormat("dd-MMM-yyyy HH:mm").format(dateI.getTime());
		//System.out.println("ENTRO CON"+ dateAux2);
		//String dateAux3 = new SimpleDateFormat("dd-MMM-yyyy HH:mm").format(dateE.getTime());
		//System.out.println("FINALIZO CON" + dateAux3);

		for (int i = 0; i < daysBetweenDates(dateI, dateE); i++) {
			dateAdd.setTimeInMillis(dateMilis + (weekAdd * i));
			//String dateAux = new SimpleDateFormat("dd-MMM-yyyy HH:mm").format(dateAdd.getTime());
			System.out.println("ENTRO EN CREATERESERVES CON");
			reserveDao.save(new Reserve(court, trainingDao.findIdTrainer(id),dateAdd));
			//System.out.println("------------------" + reserve);
		}
	}
	
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
		//Reserve reserve = reserveDao.findByCourtAndDate(training.getCourt(), training.getDateIni());
		//long dateMilis = training.getDateIni().getTimeInMillis();
		deleteReserves(training.getDateIni(), training.getDateEnd(), training.getCourt());
		trainingDao.delete(training.getId());
	}
	

	
	private void deleteReserves(Calendar dateI, Calendar dateE, Court court){
		Calendar dateDel=dateI;
		long dateMilis =dateI.getTimeInMillis();
		long weekDel = 1000*60*60*24*7;
		
		for (int i = 0; i<daysBetweenDates(dateI, dateE); i++){
			dateDel.setTimeInMillis(dateMilis+(weekDel*i));
			//reserve = reserveDao.findByCourtAndDate(court, dateDel);
			reserveDao.delete(reserveDao.findByCourtAndDate(court, dateDel));				
	}
		
	}

	private int daysBetweenDates(Calendar dateI, Calendar dateE) {
		long milisec = dateE.getTimeInMillis() - dateI.getTimeInMillis();
		int days = (int) (milisec / (1000*60*60*24));
		System.out.println("CUENTO-DIAS" + days);
		return days;
	}
	

}
