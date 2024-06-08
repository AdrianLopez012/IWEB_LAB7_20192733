package model.daos;

import java.sql.*;
import model.beans.Job;

public class JobDAO {


    public String obtenerNombreTrabajo(String jobId) {
        String jobTitle = null;

        try {
            Class.forName( "com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url ="jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "123456";

        String sql = "SELECT job_title FROM jobs WHERE job_id = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, jobId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    jobTitle = rs.getString("job_title");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return jobTitle;
    }
}
