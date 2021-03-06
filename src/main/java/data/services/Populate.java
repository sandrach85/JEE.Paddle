package data.services;

import java.util.GregorianCalendar;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import config.ResourceNames;
import data.daos.AuthorizationDao;
import data.daos.UserDao;
import data.entities.Authorization;
import data.entities.Role;
import data.entities.User;

@Service
@Transactional
@PropertySource(ResourceNames.PROPERTIES)
public class Populate {

    @Autowired
    private Environment environment;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthorizationDao authorizationDao;

    @PostConstruct
    public void createDefaultAdmin() {
        User admin = new User(environment.getProperty("admin.username"), environment.getProperty("admin.email"),
                environment.getProperty("admin.password"), new GregorianCalendar(1979, 07, 22));
        User adminSaved = userDao.findByUsernameOrEmail(admin.getUsername());
        if (adminSaved == null) {
            userDao.save(admin);
            authorizationDao.save(new Authorization(admin, Role.ADMIN));
        }
    }
    
    public void createDefaultTrainer() {
        User trainer = new User(environment.getProperty("trainer.username"), environment.getProperty("trainer.email"),
                environment.getProperty("trainer.password"), new GregorianCalendar(1979, 07, 22));
        User trainerSaved = userDao.findByUsernameOrEmail(trainer.getUsername());
        if (trainerSaved == null) {
            userDao.save(trainer);
            authorizationDao.save(new Authorization(trainer, Role.TRAINER));
        }
    }

}
