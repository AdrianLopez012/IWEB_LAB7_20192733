package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import model.beans.Employee;
import model.daos.EmployeeDAO;
import org.mindrot.jbcrypt.BCrypt;


@WebServlet(name = "EmployeeServlet", value = "/home")
public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null? "home" : request.getParameter("action");

        String vista;
        RequestDispatcher rd;
        switch(action){
            case "home":
                EmployeeDAO employeeDAO = new EmployeeDAO();
                ArrayList<Employee> listaEmployeesTabla = employeeDAO.listaEmployeesTabla();

                request.setAttribute("listaEmployees", listaEmployeesTabla);
                vista = "/Vistas/ListaEmpleados.jsp";
                rd = request.getRequestDispatcher(vista);
                rd.forward(request,response);
                break;
            case "editarEmpleado":
                try {
                    int idEmpleado = Integer.parseInt(request.getParameter("id"));
                    System.out.println("ID del profesor recibido: " + idEmpleado);

                    EmployeeDAO employeeDAOedit = new EmployeeDAO();
                    Employee employee = employeeDAOedit.obtenerEmployeePorId(idEmpleado);

                    request.setAttribute("empleado", employee);
                    vista = "/Vistas/EditarEmpleado.jsp";
                    rd = request.getRequestDispatcher(vista);
                    rd.forward(request, response);
                } catch (NumberFormatException e) {
                    System.out.println("ID del empleado no es un número válido");
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID del empleado no es válido");
                }
                break;

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action.equals("eliminarEmpleado")) {

            int idEmpleado = Integer.parseInt(request.getParameter("id"));

            EmployeeDAO employeeDAO = new EmployeeDAO();

            employeeDAO.eliminarEmpleado(idEmpleado);

            response.sendRedirect(request.getContextPath() + "/home?action=home");
        }else if (action.equals("actualizarEmpleado")) {
            int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
            String fullNameEmployee = request.getParameter("full_name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String phoneNumber = request.getParameter("phone_number");
            String hireDateStr = request.getParameter("hireDate");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Formato de fecha esperado
            Date hireDate = null;
            try {
                hireDate = dateFormat.parse(hireDateStr);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            String jobId = request.getParameter("job_id");


            BigDecimal salary = null;
            String salaryStr = request.getParameter("salary");
            if (salaryStr != null && !salaryStr.isEmpty()) {
                if (salaryStr.matches("^\\d*\\.?\\d+$")) {
                    salary = new BigDecimal(salaryStr);
                }
            }

            BigDecimal commissionPct = BigDecimal.ZERO;
            String commissionPctStr = request.getParameter("commission_pct");
            if (commissionPctStr != null && !commissionPctStr.isEmpty()) {
                if (commissionPctStr.matches("^\\d*\\.?\\d+$")) {
                    commissionPct = new BigDecimal(commissionPctStr);
                }
            }

            Integer managerId = null;
            String managerIdStr = request.getParameter("manager_id");
            if (managerIdStr != null && !managerIdStr.isEmpty()) {
                if (managerIdStr.matches("^\\d+$")) {
                    managerId = Integer.parseInt(managerIdStr);
                }
            }

            Integer departmentId = null;
            String departmentIdStr = request.getParameter("department_id");
            if (departmentIdStr != null && !departmentIdStr.isEmpty()) {
                if (departmentIdStr.matches("^\\d+$")) {
                    departmentId = Integer.parseInt(departmentIdStr);
                }
            }

            int enabled = Integer.parseInt(request.getParameter("enabled"));


            Employee employee = new Employee();
            employee.setEmployeeId(idEmpleado);
            employee.setFullNameEmployee(fullNameEmployee);
            employee.setEmail(email);
            employee.setPassword(password);
            employee.setPhoneNumber(phoneNumber);
            employee.setHireDate(hireDate);
            employee.setJobId(jobId);
            employee.setSalary(salary);
            employee.setCommissionPct(commissionPct);
            employee.setManagerId(managerId);
            employee.setDepartmentId(departmentId);
            employee.setEnabled(enabled);


            EmployeeDAO employeeDAO = new EmployeeDAO();
            employeeDAO.actualizarEmpleado(employee);


            response.sendRedirect(request.getContextPath() + "/home?action=home");
        }if(action.equals("registrarEmpleado")) {
            String fullNameEmployee = request.getParameter("full_name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String phoneNumber = request.getParameter("phone_number");
            String hireDateStr = request.getParameter("hireDate");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date hireDate = null;
            try {
                hireDate = dateFormat.parse(hireDateStr);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            String jobId = request.getParameter("job_id");


            BigDecimal salary = null;
            String salaryStr = request.getParameter("salary");
            if (salaryStr != null && !salaryStr.isEmpty()) {
                if (salaryStr.matches("^\\d*\\.?\\d+$")) {
                    salary = new BigDecimal(salaryStr);
                }
            }

            BigDecimal commissionPct = BigDecimal.ZERO;
            String commissionPctStr = request.getParameter("commission_pct");
            if (commissionPctStr != null && !commissionPctStr.isEmpty()) {
                if (commissionPctStr.matches("^\\d*\\.?\\d+$")) {
                    commissionPct = new BigDecimal(commissionPctStr);
                }
            }

            Integer managerId = null;
            String managerIdStr = request.getParameter("manager_id");
            if (managerIdStr != null && !managerIdStr.isEmpty()) {
                if (managerIdStr.matches("^\\d+$")) {
                    managerId = Integer.parseInt(managerIdStr);
                }
            }

            Integer departmentId = null;
            String departmentIdStr = request.getParameter("department_id");
            if (departmentIdStr != null && !departmentIdStr.isEmpty()) {
                if (departmentIdStr.matches("^\\d+$")) {
                    departmentId = Integer.parseInt(departmentIdStr);
                }
            }

            int enabled = Integer.parseInt(request.getParameter("enabled"));

            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            Employee nuevoEmployee = new Employee();
            nuevoEmployee.setFullNameEmployee(fullNameEmployee);
            nuevoEmployee.setEmail(email);
            nuevoEmployee.setPassword(hashedPassword);
            nuevoEmployee.setPhoneNumber(phoneNumber);
            nuevoEmployee.setHireDate(hireDate);
            nuevoEmployee.setJobId(jobId);
            nuevoEmployee.setSalary(salary);
            nuevoEmployee.setCommissionPct(commissionPct);
            nuevoEmployee.setManagerId(managerId);
            nuevoEmployee.setDepartmentId(departmentId);
            nuevoEmployee.setEnabled(enabled);


            EmployeeDAO employeeDAO = new EmployeeDAO();
            employeeDAO.agregarEmployee(nuevoEmployee);


            response.sendRedirect(request.getContextPath() + "/home?action=home");
        }
    }
}
