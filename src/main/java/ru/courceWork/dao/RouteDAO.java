package ru.courceWork.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import ru.courceWork.models.Point;
import ru.courceWork.models.Route;
//import ru.alishev.springcourse.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RouteDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RouteDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
//SELECT * FROM Route\n" +
//                "    JOIN Route_Point on route.route_id = route_point.route_id\n" +
//                "    JOIN Point on route_point.point_id = point.point_id\n" +
//                "    JOIN Category on Route.category_id = Category.category_id\n" +
//                "    JOIN Person on Route.user_id = Person.user_id;
    public List<Route> index() {//Route.name, Point.name, Point.latitude, Point.longitude, Route.distance, Route.description, Category.name, Person.email
//        String sql = "SELECT Route.name as route_name, Point.name as point_name, Point.latitude as latitude, Point.longitude as longitude, Route.distance as distance, Route.description as description, Category.name as category, Person.email as user" +
//                "FROM Route JOIN Route_Point on route.route_id = route_point.route_id JOIN Point on route_point.point_id = point.point_id JOIN Category on Route.category_id = Category.category_id\n JOIN Person on Route.user_id = Person.user_id;";
        String sql = "SELECT Route.route_id as id, Route.name as route_name, Point.name as point_name, Point.latitude as latitude, Point.longitude as longitude, Route.distance as distance, Route.description as description, Category.name as category, Person.email as email FROM Route JOIN Route_Point on route.route_id = route_point.route_id JOIN Point on route_point.point_id = point.point_id JOIN Category on Route.category_id = Category.category_id JOIN Person on Route.user_id = Person.user_id";
        List<Route> routes = jdbcTemplate.query(sql, new ResultSetExtractor<List<Route>>() {
            Map<Long, Route> routeMap = new HashMap<>();
                    @Override
                    public List<Route> extractData(ResultSet rs) throws SQLException, DataAccessException {
                        while (rs.next()) {
                            long id = rs.getLong("id");
                            System.out.println(id + "----------------------------------");
                            Route route = routeMap.get(id);
                            if (route == null) {
                                route = new Route();
                                route.setName(rs.getString("route_name"));
                                route.setDistance(rs.getDouble("distance"));
                                route.setDescription(rs.getString("description"));
                                route.setCategory(rs.getString("category"));
                                route.setUser(rs.getString("email"));
                                route.setPoints(new ArrayList<>());
                                routeMap.put(id, route);
                            }
                            Point point = new Point();
                            point.setName(rs.getString("point_name"));
                            point.setLatitude(rs.getDouble("latitude"));
                            point.setLongitude(rs.getDouble("longitude"));
                            route.getPoints().add(point);
                        }
                        return new ArrayList<>(routeMap.values());
                    }
                });

        return routes;
//        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Route.class));
    }

//    public Route show(int id) {
//        return jdbcTemplate.query("SELECT * FROM Route WHERE route_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Route.class))
//                .stream().findAny().orElse(null);
//    }

    public Route show(int id) {
        String sql = "SELECT Route.route_id as id, Route.name as route_name, Point.name as point_name, Point.latitude as latitude, Point.longitude as longitude, Route.distance as distance, Route.description as description, Category.name as category, Person.email as email FROM Route JOIN Route_Point on route.route_id = route_point.route_id JOIN Point on route_point.point_id = point.point_id JOIN Category on Route.category_id = Category.category_id JOIN Person on Route.user_id = Person.user_id ";
        List<Route> routes = jdbcTemplate.query(sql, new ResultSetExtractor<List<Route>>() {
            Map<Long, Route> routeMap = new HashMap<>();
            @Override
            public List<Route> extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    long id = rs.getLong("id");
                    Route route = routeMap.get(id);
                    if (route == null) {
                        route = new Route();
                        route.setName(rs.getString("route_name"));
                        route.setDistance(rs.getDouble("distance"));
                        route.setDescription(rs.getString("description"));
                        route.setCategory(rs.getString("category"));
                        route.setUser(rs.getString("email"));
                        route.setPoints(new ArrayList<>());
                        routeMap.put(id, route);
                    }
                    Point point = new Point();
                    point.setName(rs.getString("point_name"));
                    point.setLatitude(rs.getDouble("latitude"));
                    point.setLongitude(rs.getDouble("longitude"));
                    route.getPoints().add(point);
                }
                return new ArrayList<>(routeMap.values());
            }
        });

        return routes.get(id);
    }

    public void save(Route route) {
        jdbcTemplate.update("INSERT INTO Route VALUES(?,?)", route.getName(), route.getDistance());
    }

    public void update(int id, Route updatedRoute) {
        jdbcTemplate.update("UPDATE Route SET name=?, route_length=? WHERE point_id=?", updatedRoute.getName(),
                updatedRoute.getDistance(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Route WHERE route _id=?", id);
    }


}
