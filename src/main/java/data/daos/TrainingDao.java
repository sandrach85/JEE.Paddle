package data.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import data.entities.Token;
import data.entities.Training;
import data.entities.User;

public interface TrainingDao  extends JpaRepository<Token, Integer>, TrainingExtended {

	@Query(value="select * from Training", nativeQuery=true)
	List<Training> findAllTraining();
	
	@Query("select t.users from Training t where t.id= ?1")
	List<User> findUsersTraining(int id);
	
	Training findById(int id);
//	
//	@Query("delete t from Training t where t.id= ?1")
//	void deleteTraining(int id);
	
}
