package com.suryansh.trainserver.document;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Train_Info")
@Data
@Builder
public class TrainInfo {
    @Id
    private String trainNumber;
    private String trainName;
    private DepartureTime departureTime;
    private SeatsAvailable seatsAvailable;
    private Price price;
    private int delayedBy;

    @Data
    @Builder
    public static class DepartureTime {
        private int Hours;
        private int Minutes;
        private int Seconds;
    }

    @Data
    @Builder
    public static class SeatsAvailable {
        private int sleeper;
        private int AC;
    }

    @Data
    @Builder
    public static class Price {
        private int sleeper;
        private int AC;
    }
}
