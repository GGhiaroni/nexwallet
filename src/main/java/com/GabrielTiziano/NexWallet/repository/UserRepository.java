package com.GabrielTiziano.NexWallet.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.GabrielTiziano.NexWallet.model.UserModel;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String> {

}
