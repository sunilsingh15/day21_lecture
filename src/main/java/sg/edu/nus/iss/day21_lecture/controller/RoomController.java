package sg.edu.nus.iss.day21_lecture.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.day21_lecture.model.Room;
import sg.edu.nus.iss.day21_lecture.service.RoomService;

@RestController
@RequestMapping(path = "/api/rooms")
public class RoomController {

    @Autowired
    RoomService roomService;

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        rooms = roomService.findAll();

        if (rooms.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(rooms);
        }
    }

    @GetMapping(path = "/count")
    public ResponseEntity<Integer> getRoomCount() {
        return ResponseEntity.ok().body(roomService.count());
    }

    // http://localhost:8080/api/rooms/{room-id}
    // http://localhost:8080/api/rooms/1
    @GetMapping(path = "/{roomId}")
    public ResponseEntity<Room> getRoomById(@PathVariable int roomId) {
        Room room = roomService.findById(roomId);

        if (room == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(room);
        }

    }

    @PostMapping
    public ResponseEntity<Boolean> createRoom(@RequestBody Room room) {

        Boolean created = roomService.save(room);

        if (created) {
            return ResponseEntity.ok().body(created);
        } else {
            return ResponseEntity.internalServerError().build(); 
        }

    }

    @DeleteMapping("{room-id}")
    public ResponseEntity<Integer> deleteRoomById(@PathVariable("room-id") int roomId) {
        int deleted = roomService.deleteById(roomId);

        if (deleted == 1) {
            return ResponseEntity.ok().body(deleted);
        } else {
            return new ResponseEntity<Integer>(deleted, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Integer> updateRoom(@RequestBody Room room) {
        int updated = roomService.update(room);

        if (updated == 1) {
            return ResponseEntity.ok().body(updated);
        } else {
            return ResponseEntity.internalServerError().build();
        }

    }

}
