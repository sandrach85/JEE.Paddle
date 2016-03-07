package data.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import data.entities.Token;
import data.entities.Training;

public interface TrainingDao  extends JpaRepository<Token, Integer> {

	@Query(value="select * from Training", nativeQuery=true)
	List<Training> findAllTraining();
	
	@Query("delete t.user from Training t where t.user= ?1")
	void deleteUser(int id);
	
	
	Training findById(int id);
	
}
