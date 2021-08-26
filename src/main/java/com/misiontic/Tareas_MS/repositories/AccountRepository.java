package com.misiontic.Tareas_MS.repositories;

import com.misiontic.Tareas_MS.models.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository <Account, String>{
}
