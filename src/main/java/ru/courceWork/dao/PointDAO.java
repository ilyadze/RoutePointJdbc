package ru.courceWork.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.courceWork.models.Point;

import java.util.List;

@Component
public class PointDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PointDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Point> index() {
        return jdbcTemplate.query("SELECT * FROM Point", new BeanPropertyRowMapper<>(Point.class));
    }

    public Point show(int id) {
        return jdbcTemplate.query("SELECT * FROM Point WHERE point_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Point.class))
                .stream().findAny().orElse(null);
    }

    public void save(Point point) {
        jdbcTemplate.update("INSERT INTO Point VALUES( ?, ?, ?)", point.getName(), point.getLatitude(),
                point.getLongitude());
    }

    public void update(int id, Point updatedPoint) {
        jdbcTemplate.update("UPDATE Point SET name=?, latitude=?, longitude=? WHERE point_id=?", updatedPoint.getName(),
                updatedPoint.getLatitude(), updatedPoint.getLongitude(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Point WHERE point_id=?", id);
    }

//    private static final String URL = ;
//    private static final String USERNAME = ;
//    private static final String PASSWORD = ;
//
//    private static Connection connection;
//
//
//    static {
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public List<Point> index() {
//        List<Point> points = new ArrayList<>();
//
//        try {
//            Statement statement = connection.createStatement();
//            String SQL = "SELECT * FROM Point";
//            ResultSet resultSet = statement.executeQuery(SQL);
//
//            while (resultSet.next()) {
//                Point point = new Point();
//
//                point.setName(resultSet.getString("name"));
//                point.setFirstCoordinate(resultSet.getDouble("firstcoordinate"));
//                point.setSecondCoordinate(resultSet.getDouble("secondcoordinate"));
//
//                points.add(point);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return points;
//    }
//
//    public Point show(int id) {
//        Point point = null;
//        try {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("SELECT * FROM Point WHERE id = ?");
//
//            preparedStatement.setInt(1 , id);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            resultSet.next();
//
//            point = new Point();
//            point.setName(resultSet.getString("name"));
//            point.setFirstCoordinate(resultSet.getDouble("firstcoordinate"));
//            point.setSecondCoordinate(resultSet.getDouble("secondcoordinate"));
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return point;
//    }
//
//    public void save(Point point) {
//        try {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("INSERT INTO Point VALUES(1, ?, ?, ?)");
//            preparedStatement.setString(1, point.getName());
//            preparedStatement.setDouble(2, point.getFirstCoordinate());
//            preparedStatement.setDouble(3, point.getSecondCoordinate());
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void update(int id, Point updatedPoint) {
//        try {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("UPDATE Point SET name = ?, firstcoordinate=?, secondcoordinate=? WHERE id=?");
//            preparedStatement.setString(1, updatedPoint.getName());
//            preparedStatement.setDouble(2, updatedPoint.getFirstCoordinate());
//            preparedStatement.setDouble(3, updatedPoint.getSecondCoordinate());
//
//            preparedStatement.setInt(4, id);
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void delete(int id) {
//        PreparedStatement preparedStatement = null;
//        try {
//            preparedStatement =
//                    connection.prepareStatement("DELETE FROM Point WHERE id = ?");
//            preparedStatement.setInt(1, id);
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//

}
