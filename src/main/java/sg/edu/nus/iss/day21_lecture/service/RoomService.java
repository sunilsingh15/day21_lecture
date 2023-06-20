package sg.edu.nus.iss.day21_lecture.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.day21_lecture.model.Room;
import sg.edu.nus.iss.day21_lecture.repo.RoomRepository;

@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepo;

    public int count() {
        return roomRepo.count();
    }

    public Boolean save(Room room) {
        return roomRepo.save(room);
    }

    public List<Room> findAll() {
        return roomRepo.getAllRooms();
    }

    public Room findById(int id) {
        return roomRepo.getRoombyId(id);
    }

    public int update(Room room) {
        return roomRepo.update(room);
    }

    public int deleteById(int id) {
        return roomRepo.deleteRoomById(id);
    }

    
}
