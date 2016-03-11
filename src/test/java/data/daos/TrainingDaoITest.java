package data.daos;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.entities.Training;
import data.entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class TrainingDaoITest {
	
	@Autowired
	private TrainingDao trainingDao;
	
    @Autowired
    private DaosService daosService;
    
    @Autowired
    private UserDao userDao;
	
	@Test
	public void testFindById(){
		Training training = (Training) daosService.getMap().get("uT41");
		assertTrue(training.getId() == trainingDao.findById(training.getId()).getId());
	}

	@Test
	public void testDeleteTrainingAndReserves(){
		Training training = (Training) daosService.getMap().get("uT41");
		int id = training.getId();
		trainingDao.deleteTrainingAndReserves(training.getId());
		assertNull(trainingDao.findById(id));
	}
	
	@Test
	public void testDeleteUserTraining(){
		Training training = (Training) daosService.getMap().get("uT41");
		List<User> usersBef = trainingDao.findUsersTraining(training.getId());
		trainingDao.deleteUserTraining(training.getId(), 2);
		List<User> usersAft = trainingDao.findUsersTraining(training.getId());
		assertTrue(usersBef!=usersAft);
	}
	
	@Test
	public void testFindIdTrainer(){
		Training training = (Training) daosService.getMap().get("uT41");
		assertTrue(training.getTrainer().getId()==6);
	}
	
	@Test
	public void testCreateTraining(){
		Training training = (Training) daosService.getMap().get("uT41");
		Training trainingC = trainingDao.createTraining(training.getDateIni(), training.getDateEnd(), training.getCourt(), training.getTrainer(), 12);
		assertEquals(4,trainingC.getId());
	}
	
}