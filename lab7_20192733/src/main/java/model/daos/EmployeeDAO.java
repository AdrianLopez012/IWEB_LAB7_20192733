package model.daos;

import java.sql.*;
import model.beans.Employee;
import java.util.ArrayList;


public class EmployeeDAO {


    public ArrayList<Employee> listaEmployeesTabla() {

        ArrayList<Employee> ListaEmployees = new ArrayList<Employee>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "123456";

        String sql = "SELECT employee_id, CONCAT(first_name, ' ', last_name) AS full_name_employee, email, password, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id, enabled FROM employees";
        ;

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFullNameEmployee(rs.getString("full_name_employee"));
                employee.setEmail(rs.getString("email"));
                employee.setPassword(rs.getString("password"));
                employee.setPhoneNumber(rs.getString("phone_number"));
                employee.setHireDate(rs.getDate("hire_date"));
                employee.setJobId(rs.getString("job_id"));
                employee.setSalary(rs.getBigDecimal("salary"));
                employee.setCommissionPct(rs.getBigDecimal("commission_pct"));
                employee.setManagerId((Integer) rs.getObject("manager_id"));
                employee.setDepartmentId((Integer) rs.getObject("department_id"));
                employee.setEnabled(rs.getInt("enabled"));


                ListaEmployees.add(employee);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ListaEmployees;

    }

    public String obtenerNombreManager(Integer idManager) {
        String nombreManager = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "123456";

        String sql = "SELECT CONCAT(first_name, ' ', last_name) AS full_name_employee FROM employees WHERE employee_id = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idManager);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    nombreManager = rs.getString("full_name_employee");
                    System.out.println("Nombre del Manager: " + nombreManager);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return nombreManager;
    }
    public void eliminarEmpleado(int idEmpleado) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url ="jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "123456";

        String sql = "DELETE FROM employees WHERE employee_id = ?";

        try (Connection conn= DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idEmpleado);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Employee obtenerEmployeePorId(int id) {
        Employee employee = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "123456";

        String sql = "SELECT employee_id, first_name, last_name, email, password, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id, enabled FROM employees WHERE employee_id = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    employee = new Employee();
                    employee.setEmployeeId(rs.getInt("employee_id"));
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    employee.setFullNameEmployee(firstName + " " + lastName);
                    employee.setEmail(rs.getString("email"));
                    employee.setPassword(rs.getString("password"));
                    employee.setPhoneNumber(rs.getString("phone_number"));
                    employee.setHireDate(rs.getDate("hire_date"));
                    employee.setJobId(rs.getString("job_id"));
                    employee.setSalary(rs.getBigDecimal("salary"));
                    employee.setCommissionPct(rs.getBigDecimal("commission_pct"));
                    employee.setManagerId((Integer) rs.getObject("manager_id"));
                    employee.setDepartmentId((Integer) rs.getObject("department_id"));
                    employee.setEnabled(rs.getInt("enabled"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    public void actualizarEmpleado(Employee employee) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "123456";

        String sql = "UPDATE employees SET first_name = ?, last_name = ?, email = ?, password = ?, phone_number = ?, hire_date = ?, job_id = ?, salary = ?, commission_pct = ?, manager_id = ?, department_id = ?, enabled = ? WHERE employee_id = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Divide el fullNameEmployee en firstName y lastName
            String[] names = employee.getFullNameEmployee().split(" ", 2);
            String firstName = names.length > 0 ? names[0] : "";
            String lastName = names.length > 1 ? names[1] : "";

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, employee.getEmail());
            pstmt.setString(4, employee.getPassword());
            pstmt.setString(5, employee.getPhoneNumber());
            pstmt.setDate(6, new java.sql.Date(employee.getHireDate().getTime()));
            pstmt.setString(7, employee.getJobId());
            pstmt.setBigDecimal(8, employee.getSalary());
            pstmt.setBigDecimal(9, employee.getCommissionPct());
            pstmt.setInt(10, employee.getManagerId());
            pstmt.setInt(11, employee.getDepartmentId());
            pstmt.setInt(12, employee.getEnabled());
            pstmt.setInt(13, employee.getEmployeeId());

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Empleado actualizado con éxito!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void agregarEmployee(Employee employee) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url ="jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "123456";

        String sql = "INSERT INTO employees (first_name, last_name, email, password,phone_number,hire_date,job_id,salary,commission_pct,manager_id,department_id,enabled) VALUES (?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?)";
        try (Connection conn= DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String[] names = employee.getFullNameEmployee().split(" ", 2);
            String firstName = names.length > 0 ? names[0] : "";
            String lastName = names.length > 1 ? names[1] : "";

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, employee.getEmail());
            pstmt.setString(4, employee.getPassword());
            pstmt.setString(5, employee.getPhoneNumber());
            pstmt.setDate(6, new java.sql.Date(employee.getHireDate().getTime()));
            pstmt.setString(7, employee.getJobId());
            pstmt.setBigDecimal(8, employee.getSalary());
            pstmt.setBigDecimal(9, employee.getCommissionPct());
            pstmt.setInt(10, employee.getManagerId());
            pstmt.setInt(11, employee.getDepartmentId());
            pstmt.setInt(12, employee.getEnabled());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}

