//package edu.bbte.softeng.ahim2106.spring.adapter.out.persistence;
//
//import edu.bbte.softeng.ahim2106.spring.application.port.out.MovieRepository;
//import edu.bbte.softeng.ahim2106.spring.domain.Movie;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import javax.sql.DataSource;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class MovieRepositoryAdapter implements MovieRepository {
//    private final DataSource dataSource;
//
//    @Autowired
//    public MovieRepositoryImpl(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    @Override
//    public Movie save(Movie movie) {
//        String sql = "INSERT INTO movies (title, director) VALUES (?, ?)";
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
//
//            statement.setString(1, movie.getTitle());
//            statement.setString(2, movie.getDirector());
//            int affectedRows = statement.executeUpdate();
//
//            if (affectedRows > 0) {
//                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
//                    if (generatedKeys.next()) {
//                        movie.setId(generatedKeys.getLong(1));
//                    }
//                }
//            }
//            return movie;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    @Override
//    public List<Movie> findAll() {
//        List<Movie> movies = new ArrayList<>();
//        String sql = "SELECT * FROM movies";
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement statement = connection.prepareStatement(sql);
//             ResultSet resultSet = statement.executeQuery()) {
//
//            while (resultSet.next()) {
//                Movie movie = new Movie();
//                movie.setId(resultSet.getLong("id"));
//                movie.setTitle(resultSet.getString("title"));
//                movie.setDirector(resultSet.getString("director"));
//                movies.add(movie);
//            }
//            return movies;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public Optional<Movie> findById(Long id) {
//        String sql = "SELECT * FROM movies WHERE id = ?";
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//
//            statement.setLong(1, id);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    Movie movie = new Movie();
//                    movie.setId(resultSet.getLong("id"));
//                    movie.setTitle(resultSet.getString("title"));
//                    movie.setDirector(resultSet.getString("director"));
//                    return Optional.of(movie);
//                }
//                return Optional.empty();
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        String sql = "DELETE FROM movies WHERE id = ?";
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//
//            statement.setLong(1, id);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
