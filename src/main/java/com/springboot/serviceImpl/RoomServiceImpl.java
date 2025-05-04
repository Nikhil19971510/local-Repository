package com.springboot.serviceImpl;

import com.springboot.dto.RoomDto;
import com.springboot.model.Room;
import com.springboot.repo.RoomRepository;
import com.springboot.serviceI.RoomService;
import com.springboot.utils.APIResponse;
import com.springboot.utils.MessageUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;
    private static final Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);

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
        return new APIResponse(MessageUtility.ROOM_CREATED_SUCCESSFULLY, 200, roomSave);
    }

    @Override
    public List<Room> getAllRoom() {
        List<Room> allRooms = roomRepository.findAll();
        if (allRooms.isEmpty() || allRooms.stream().noneMatch(Objects::nonNull)) {
            logger.warn("No valid room data found.");
            return Collections.emptyList();
        }
        logger.warn("valid room data found.");
        return allRooms.stream().filter(Objects::nonNull).collect(Collectors.toList());
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