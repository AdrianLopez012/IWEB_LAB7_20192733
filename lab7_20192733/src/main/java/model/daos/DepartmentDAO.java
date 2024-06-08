package model.daos;

import model.beans.Employee;
import model.util.Ubicacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {
    public String obtenerNombreDepartamento(Integer departmentId) {
        String departmentName = null;

        try {
            Class.forName( "com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url ="jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "123456";

        String sql = "SELECT department_name, location_id FROM departments WHERE department_id = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, departmentId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    departmentName = rs.getString("department_name");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return departmentName;
    }
    public ArrayList<Ubicacion> obtenerUbicacionesDelDepartamento(Integer departmentId) {
        List<String> ubicaciones = new ArrayList<>();
        ArrayList<Ubicacion> ListaUbicacion = new ArrayList<Ubicacion>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url ="jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "123456";

        String sql = "WITH dept AS ( " +
                "    SELECT location_id " +
                "    FROM departments " +
                "    WHERE department_id = ? " +
                "), " +
                "loc AS ( " +
                "    SELECT street_address, postal_code, city, state_province, country_id " +
                "    FROM locations " +
                "    WHERE location_id = (SELECT location_id FROM dept) " +
                "), " +
                "ctry AS ( " +
                "    SELECT country_name, region_id " +
                "    FROM countries " +
                "    WHERE country_id = (SELECT country_id FROM loc) " +
                ") " +
                "SELECT " +
                "    loc.street_address, " +
                "    loc.postal_code, " +
                "    loc.city, " +
                "    loc.state_province, " +
                "    ctry.country_name, " +
                "    regions.region_name " +
                "FROM " +
                "    loc, " +
                "    ctry, " +
                "    regions " +
                "WHERE " +
                "    regions.region_id = ctry.region_id";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, departmentId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Ubicacion ubicacion = new Ubicacion();
                    ubicacion.setStreet_address(rs.getString("street_address"));
                    ubicacion.setPostal_code(rs.getString("postal_code"));
                    ubicacion.setCity(rs.getString("city"));
                    ubicacion.setState_province(rs.getString("state_province"));
                    ubicacion.setCountry_name(rs.getString("country_name"));
                    ubicacion.setRegion_name(rs.getString("region_name"));
                    ListaUbicacion.add(ubicacion);
                }


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ListaUbicacion;
    }


}
