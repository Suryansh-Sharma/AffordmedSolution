package com.suryansh.trainserver.service;

import com.suryansh.trainserver.document.TrainInfo;
import com.suryansh.trainserver.repository.TrainInfoRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {
    private final TrainInfoRepo trainInfoRepo;

    public TrainService(TrainInfoRepo trainInfoRepo) {
        this.trainInfoRepo = trainInfoRepo;
    }

    public List<TrainInfo> saveMultipleTrains(List<TrainInfo> body) {
        for (TrainInfo t:body){
            TrainInfo.DepartureTime departureTime = TrainInfo.DepartureTime
                    .builder()
                    .Hours(t.getDepartureTime().getHours())
                    .Minutes(t.getDepartureTime().getMinutes())
                    .Seconds(t.getDepartureTime().getSeconds())
                    .build();
            TrainInfo.SeatsAvailable seatsAvailable = TrainInfo.SeatsAvailable
                    .builder()
                    .AC(t.getSeatsAvailable().getAC())
                    .sleeper(t.getSeatsAvailable().getSleeper())
                    .build();
            TrainInfo.Price price = TrainInfo.Price
                    .builder()
                    .sleeper(t.getPrice().getSleeper())
                    .AC(t.getPrice().getAC())
                    .build();
            TrainInfo trainInfo = TrainInfo.builder()
                    .trainName(t.getTrainName())
                    .trainNumber(t.getTrainNumber())
                    .delayedBy(t.getDelayedBy())
                    .departureTime(departureTime)
                    .seatsAvailable(seatsAvailable)
                    .price(price)
                    .build();
            try {
                trainInfoRepo.save(trainInfo);
                System.out.println("Train saved of id "+t.getTrainNumber());
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
        return body;
    }

    public List<TrainInfo> getAllTrains() {
        return trainInfoRepo.findAll();
    }

    public TrainInfo getSingleTrain(String trainNo) {
        return trainInfoRepo.findByTrainNumber(trainNo);
    }
}
