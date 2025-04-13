package com.springboot.serviceI;


import com.springboot.dto.RoomDto;
import com.springboot.model.Room;
import com.springboot.utils.APIResponse;

public interface RoomService {
    public APIResponse saveRoom(RoomDto roomRequest);

}