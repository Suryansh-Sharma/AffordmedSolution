package com.suryansh.trainserver.repository;

import com.suryansh.trainserver.document.TrainInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrainInfoRepo extends MongoRepository<TrainInfo,String> {
    TrainInfo findByTrainNumber(String trainNo);
}
