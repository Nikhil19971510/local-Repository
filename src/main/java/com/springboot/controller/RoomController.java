package com.springboot.controller;

import com.springboot.dto.RoomDto;
import com.springboot.model.Room;
import com.springboot.serviceI.RoomService;
import com.springboot.utils.APIResponse;
import com.springboot.utils.MessageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.* ;

import java.util.List;

@SuppressWarnings("All")
@RestController
@RequestMapping("room-API")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping(value = "/saveRoom", produces = MediaType.APPLICATION_JSON_VALUE)
    public APIResponse saveRoom(@RequestBody RoomDto roomDto) {
        try {
            APIResponse apiResponse = roomService.saveRoom(roomDto);
            System.out.println("apiResponse : " + apiResponse);
            return apiResponse;
        } catch (Exception e) {
            return new APIResponse(MessageUtility.HttpStatus_INTERNAL_SERVER_ERROR, 500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getAllRoom", produces = MediaType.APPLICATION_JSON_VALUE)
    public APIResponse getAllRoom() {
        try {
            List<Room> rooms = roomService.getAllRoom();
            System.out.println("rooms : " + rooms);
            return new APIResponse("Rooms fetched successfully", HttpStatus.OK, rooms);
        } catch (Exception e) {
            return new APIResponse("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }


}
