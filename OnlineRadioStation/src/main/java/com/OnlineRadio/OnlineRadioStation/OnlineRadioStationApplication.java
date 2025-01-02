package com.OnlineRadio.OnlineRadioStation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.OnlineRadio.OnlineRadioStation.models"})
public class OnlineRadioStationApplication {
	public static void main(String[] args) {
		SpringApplication.run(OnlineRadioStationApplication.class, args);
	}

}
