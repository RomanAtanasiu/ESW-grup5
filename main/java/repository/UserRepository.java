package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Polis;
import model.User;

public class UserRepository extends BaseRepository {
	
	public boolean existsByUsername(String username) {
		try (PreparedStatement statement = db.prepareStatement("SELECT COUNT(*) FROM users WHERE name = ?")) {
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Check if count > 0
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
	}
	
    // Save a new user into the database
    public void save(User user) {
        String query = "INSERT INTO users (name, password, email, gender, birthdayString, polis_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail() != null ? user.getEmail() : "");
            statement.setString(4, user.getGender());
            statement.setString(5, user.getBirthdateString());
            statement.setInt(6, user.getPolis() != null ? user.getPolis().getId() : 1); // Default to ID 1 if null
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	// Find a user by their name
    public Optional<User> findByName(String name) {
        String query = "SELECT u.id, u.name, u.password, u.email, u.gender, u.birthday, u.polis_id, p.name as polis_name " +
                "FROM users u JOIN polis p ON u.polis_id = p.id WHERE u.name = ?";
        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setGender(rs.getString("gender"));
                user.setBirthdateString(rs.getString("birthday"));

                Polis polis = new Polis();
                polis.setId(rs.getInt("polis_id"));
                polis.setName(rs.getString("polis_name"));
                user.setPolis(polis);

                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    
    // Retrieve all users from the database
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String query = "SELECT u.id, u.name, u.password, u.email, u.gender, u.birthday, u.polis_id, p.name as polis_name " +
                "FROM users u JOIN polis p ON u.polis_id = p.id";

        try (PreparedStatement statement = db.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setGender(rs.getString("gender"));
                user.setBirthdateString(rs.getString("birthday"));

                Polis polis = new Polis();
                polis.setId(rs.getInt("polis_id"));
                polis.setName(rs.getString("polis_name"));
                user.setPolis(polis);

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

}