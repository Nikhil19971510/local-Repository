package com.springboot.serviceImpl;

import com.springboot.dto.RoomDto;
import com.springboot.model.Room;
import com.springboot.repo.RoomRepository;
import com.springboot.serviceI.RoomService;
import com.springboot.utils.APIResponse;
import com.springboot.utils.MessageUtilty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;


    @Override
    public APIResponse saveRoom(RoomDto roomRequest) {
        if (roomRequest == null) {
            return null;
        }
        Room room = new Room();
        room.setRoomname(roomRequest.getName());
        room.setPrice(roomRequest.getPrice());
        room.setRoomtype(roomRequest.getType());
        room.setIsavailable(roomRequest.isAvailable());
        Room roomSave = roomRepository.save(room);
        return new APIResponse(MessageUtilty.ROOM_CREATED_SUCCESSFULLY, 200, roomSave);
    }
//
//    @Override
//    public Room getRoomById(Long id) {
//        Optional<Room> room = roomRepository.findById(id);
//        return room.orElse(null);
//    }
//
//    @Override
//    public List<Room> getAllRooms() {
//        return roomRepository.findAll();
//    }
//
//    @Override
//    public List<Room> getAvailableRooms() {
//        return roomRepository.findByAvailableTrue();
//    }
//
//    @Override
//    public void deleteRoom(Long id) {
//        roomRepository.deleteById(id);
//    }

}