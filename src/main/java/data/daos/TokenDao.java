package data.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import data.entities.Token;
import data.entities.User;

public interface TokenDao extends JpaRepository<Token, Integer> {

    Token findByUser(User user);

    @Query(value="delete * from Token where createdDate.isValid()=false", nativeQuery=true)
    List<Token> deleteAllTokenExpired();
}
