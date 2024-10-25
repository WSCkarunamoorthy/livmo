package com.mvplivmo;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignUpRepo extends MongoRepository<SignUp, String>{

}
