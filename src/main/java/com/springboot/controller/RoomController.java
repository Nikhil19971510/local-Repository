package com.springboot.controller;

import com.springboot.dto.RoomDto;
import com.springboot.request.UserRequest;
import com.springboot.serviceI.RoomService;
import com.springboot.utils.APIResponse;
import com.springboot.utils.MessageUtilty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("room-API")
public class RoomController {

    @Autowired
    private RoomService roomService ;

    @PostMapping(value = "/saveRoom", produces = MediaType.APPLICATION_JSON_VALUE)
    public APIResponse saveRoom(@RequestBody RoomDto roomDto) {
        try {
        APIResponse apiResponse = roomService.saveRoom(roomDto);
        System.out.println("apiResponse : " + apiResponse);
        return apiResponse;
    } catch (Exception e) {
        return new APIResponse(MessageUtilty.HttpStatus_INTERNAL_SERVER_ERROR, 500, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
}
