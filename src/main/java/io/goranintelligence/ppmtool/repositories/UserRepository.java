package io.goranintelligence.ppmtool.repositories;

import io.goranintelligence.ppmtool.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    User findByUsername(String username);
    User getById(Long id);



}
