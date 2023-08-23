package com.suryansh.trainserver.controller;

import com.suryansh.trainserver.document.TrainInfo;
import com.suryansh.trainserver.service.TrainService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/trains/")
@CrossOrigin("*")
public class TrainController {
    private final TrainService trainService;

    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @PostMapping("add-multiple-trains")
    public List<TrainInfo> addMultipleTrains(@RequestBody List<TrainInfo>body){
        return trainService.saveMultipleTrains(body);
    }

    @GetMapping("all")
    public List<TrainInfo>getMultipleTrains(){
        return trainService.getAllTrains();
    }

    @GetMapping("particular-by-train-number/{train_no}")
    public TrainInfo getParticularByNumber(@PathVariable String train_no){
        return trainService.getSingleTrain(train_no);
    }
}
