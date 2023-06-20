package sg.edu.nus.iss.day21_lecture.repo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day21_lecture.model.Room;

@Repository
public class RoomRepository {

    @Autowired
    JdbcTemplate template;

    private String countSQL = "select count(*) from room";
    private String findAllSQL = "select * from room";
    private String findByIdSQL = "select * from room where id = ?";
    private String deleteByIdSQL = "delete from room where id = ?";
    private String insertSQL = "insert into room (room_type, price) values (?, ?)";
    private String updateSQL = "update room set price = ? where id = ?";

    public int count() {
        return template.queryForObject(countSQL, Integer.class);
    }

    public List<Room> getAllRooms() {
        return template.query(findAllSQL, BeanPropertyRowMapper.newInstance(Room.class));
    }

    public Room getRoombyId(Integer id) {
        return template.queryForObject(findByIdSQL, BeanPropertyRowMapper.newInstance(Room.class), id);
    }

    public Boolean save(Room room) {
        Boolean saved = false;

        saved = template.execute(insertSQL, new PreparedStatementCallback<Boolean>() {

            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, room.getRoomType());
                ps.setInt(2, room.getPrice());
                return ps.execute();
            }

        });

        return saved;
    }

    public int update(Room room) {
        return template.update(updateSQL, room.getPrice(), room.getId());
    }

    public int deleteRoomById(Integer id) {
        return template.update(deleteByIdSQL, id);
    }



}
