package api;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;

import business.api.Uris;
import business.wrapper.CourtState;
import business.wrapper.TrainingWrapper;
import business.wrapper.UserWrapper;
import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.daos.DaosService;
import data.entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceConfig.class, TestsPersistenceConfig.class })
public class TrainingResourceFuncionalITesting {

	@Autowired
	private DaosService daosService;
	
	@Test
	public void testErrorUnauthorizedCreateTraining() {
		try {
			User u1 = (User) daosService.getMap().get("u1");
			String userName = u1.getUsername();
			String pws = u1.getPassword();
			new RestBuilder<Object>(Uris.SERVLET_MAP).path(Uris.TRAINING).path(Uris.CREATE_TRAINING)
					.basicAuth(userName, pws).get().build();
			fail();
		} catch (HttpClientErrorException httpError) {
			assertEquals(HttpStatus.UNAUTHORIZED, httpError.getStatusCode());
			System.out.println("ERROR>>>>> " + httpError.getMessage());
		}
	}

	@Test
	public void testBadRequestCreateTraining() {
		try {

			Calendar date1 = Calendar.getInstance();
			Calendar date2 = Calendar.getInstance();
			date2.set(date1.get(Calendar.YEAR), date1.get(Calendar.MONTH) + 2, date1.get(Calendar.DATE),
					date1.get(Calendar.HOUR_OF_DAY), date1.get(Calendar.MINUTE));
			CourtState court = new CourtState(1, true);
			User u1 = (User) daosService.getMap().get("u6");
			UserWrapper userWrapper = new UserWrapper(u1.getUsername(), u1.getEmail(), u1.getPassword(), u1.getBirthDate());
			TrainingWrapper trainingWrapper = new TrainingWrapper(date1, date2, court, userWrapper);
			
			new RestBuilder<Object>(Uris.SERVLET_MAP).path(Uris.TRAINING).path(Uris.CREATE_TRAINING)
					.body(trainingWrapper).get().build();
			fail();
		} catch (HttpClientErrorException httpError) {
			assertEquals(HttpStatus.BAD_REQUEST, httpError.getStatusCode());
			System.out.println("ERROR>>>>> " + httpError.getMessage());
		}
	}
}