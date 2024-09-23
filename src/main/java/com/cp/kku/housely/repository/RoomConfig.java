package com.cp.kku.housely.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cp.kku.housely.service.RoomService;

@Configuration
public class RoomConfig {
    @Bean
	public RoomService roomService(RoomRepository roomRepository) {
		return new RoomService(roomRepository);
	}
}
