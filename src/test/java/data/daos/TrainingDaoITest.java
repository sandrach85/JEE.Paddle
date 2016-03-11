package data.daos;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.entities.Training;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class TrainingDaoITest {
	
	@Autowired
	private TrainingDao trainingDao;
	
    @Autowired
    private DaosService daosService;
	
	@Test
	public void testFindById(){
		Training training = (Training) daosService.getMap().get("uT5");
		System.out.println("------TRAINING------"+training);
		assertEquals(training , trainingDao.findById(training.getId()));
		
	}

}
