package com.cp.kku.housely.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cp.kku.housely.model.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
    // Additional query methods can be defined here if needed
}
