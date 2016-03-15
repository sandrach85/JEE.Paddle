package api;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;

import business.api.Uris;
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
	public void testAuthorizedOKCreateTraining() {
		User u1 = (User) daosService.getMap().get("u6");
		String userName = u1.getUsername();
		String pws = u1.getPassword();
		Object response = new RestBuilder<Object>(Uris.SERVLET_MAP).path(Uris.TRAINING).path(Uris.CREATE_TRAINING)
				.basicAuth(userName, pws).clazz(Object.class).get().build();

		System.out.println("INFO >>>>> " + u1.toString());
		System.out.println("INFO >>>>> " + response);
	}
}