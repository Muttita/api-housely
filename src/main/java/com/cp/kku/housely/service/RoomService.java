package com.cp.kku.housely.service;

import org.springframework.stereotype.Service;

import com.cp.kku.housely.model.Room;
import com.cp.kku.housely.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() { return (List<Room>) roomRepository.findAll();}

    public Optional<Room> getRoomById(Long id) { return roomRepository.findById(id);}

    public Room createRoom(Room room) { return roomRepository.save(room); }

    public Optional<Room> updateRoom(Long id, Room roomDetails) {
        return roomRepository.findById(id).map(room -> {
            room.setRoomName(roomDetails.getRoomName());
            room.setDescription(roomDetails.getDescription());
            room.setImageBase64(roomDetails.getImageBase64());
            room.setProductsInRoom(roomDetails.getProductsInRoom());
            return roomRepository.save(room);
        });
    }

    public boolean deleteRoom(Long id) {
        if (roomRepository.existsById(id)) {
            roomRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
