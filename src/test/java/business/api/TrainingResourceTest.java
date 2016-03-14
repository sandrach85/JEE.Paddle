package business.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;

import business.wrapper.TrainingWrapper;
import api.RestBuilder;
import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.daos.DaosService;
import data.entities.Training;
import data.entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceConfig.class, TestsPersistenceConfig.class })
public class TrainingResourceTest {

	@Autowired
	private DaosService daosService;

	@Test
	public void testErrorUnauthorizedCreateTraining() {
		try {
			User u1 = (User) daosService.getMap().get("u1");
			String userName = u1.getUsername();
			String pws = u1.getPassword();
			Training training = (Training) daosService.getMap().get("u62");
			TrainingWrapper trainingWrapper = new TrainingWrapper(training);
			new RestBuilder<Object>(Uris.SERVLET_MAP).path(Uris.TRAINING).path(Uris.CREATE_TRAINING)
					.body(trainingWrapper).basicAuth(userName, pws).get().build();
			fail();
		} catch (HttpClientErrorException httpError) {
			assertEquals(HttpStatus.UNAUTHORIZED, httpError.getStatusCode());
			System.out.println("ERROR>>>>> " + httpError.getMessage());
		}
	}

	@Test
	public void testAuthorizedOKCreateTraining() {
		User u1 = (User) daosService.getMap().get("u5");
		String userName = u1.getUsername();
		String pws = u1.getPassword();
		Training training = (Training) daosService.getMap().get("u62");
		TrainingWrapper trainingWrapper = new TrainingWrapper(training);
		Object response = new RestBuilder<Object>(Uris.SERVLET_MAP).path(Uris.TRAINING).path(Uris.CREATE_TRAINING)
				.body(trainingWrapper).basicAuth(userName, pws).clazz(Object.class).get().build();

		System.out.println("INFO >>>>> " + u1.toString());
		System.out.println("INFO >>>>> " + response);
	}
}
