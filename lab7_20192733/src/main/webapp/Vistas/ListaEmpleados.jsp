<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.daos.JobDAO" %>
<%@ page import="model.daos.EmployeeDAO" %>
<%@ page import="model.beans.Employee" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="model.daos.DepartmentDAO" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Lista de Empleados</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    </head>
    <body>

        <div class="container mt-5">
            <h2 class="mb-4">Lista de Empleados</h2>

            <%
                List<Employee> listaEmployees = (List<Employee>) request.getAttribute("listaEmployees");
                JobDAO jobDAO = new JobDAO();
                EmployeeDAO employeeDAO = new EmployeeDAO();
                DepartmentDAO departmentDAO = new DepartmentDAO();
            %>
            <button class="btn btn-primary mb-3" onclick="window.location.href = '${pageContext.request.contextPath}/Vistas/RegistrarEmpleado.jsp';">
                Agregar Docente
            </button><br>
            <table class="table table-striped table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Nombre Completo</th>
                        <th>Email</th>
                        <th>Contraseña</th>
                        <th>Teléfono</th>
                        <th>Fecha de Contratación</th>
                        <th>Trabajo</th>
                        <th>Salario</th>
                        <th>Comisión</th>
                        <th>Supervisor</th>
                        <th>Departamento</th>
                        <th>Estado</th>
                        <th>Editar</th>
                        <th>Borrar</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Employee employee : listaEmployees) { %>
                    <tr>
                        <td><%= employee.getEmployeeId() %></td>
                        <td><%= employee.getFullNameEmployee() %></td>
                        <td><%= employee.getEmail() %></td>
                        <td><%= employee.getPassword() %></td>
                        <td><%= employee.getPhoneNumber() %></td>
                        <td><%= employee.getHireDate() %></td>
                        <td><%= jobDAO.obtenerNombreTrabajo(employee.getJobId()) %></td>
                        <td><%= employee.getSalary() %></td>
                        <td><%= employee.getCommissionPct() != null ? employee.getCommissionPct().multiply(new BigDecimal(100)).toString() + "%" : "N/A" %></td>
                        <td>
                            <% if (employee.getManagerId() != null) { %>
                            <%= employeeDAO.obtenerNombreManager(employee.getManagerId()) %>
                            <% } else { %>
                            Es el jefazo
                            <% } %>
                        </td>
                        <td>
                            <%= departmentDAO.obtenerNombreDepartamento(employee.getDepartmentId()) %>
                            <a href ="${pageContext.request.contextPath}/Department?action=tablaUbicaciones&id=<%= employee.getDepartmentId() %>" >
                                <i class="fas fa-eye"></i>
                            </a>
                        </td>
                        <td><%= employee.getEnabled() %></td>
                        <td>
                            <a href ="${pageContext.request.contextPath}/home?action=editarEmpleado&id=<%= employee.getEmployeeId() %>">
                                <i class="fas fa-pencil-alt"></i>
                            </a>
                        </td>
                        <td>
                            <a href="#" onclick="return Eliminacion(<%= employee.getEmployeeId() %>, '<%= request.getContextPath() %>');">
                                <i class="fas fa-trash-alt"></i>
                            </a>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>

        <script>
            function Eliminacion(id, contextPath) {
                Swal.fire({
                    title: "¿Estás seguro?",
                    text: "Una vez eliminado, la información asociada al empleado será eliminada del sistema",
                    icon: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#00913f",
                    cancelButtonColor: "#d33",
                    confirmButtonText: "Sí, eliminar",
                }).then((result) => {
                    if (result.isConfirmed) {
                        // Crear un formulario para enviar la solicitud POST
                        const form = document.createElement('form');
                        form.method = 'post';
                        form.action = contextPath + '/home?action=eliminarEmpleado';

                        // Crear un input oculto para el ID del profesor
                        const input = document.createElement('input');
                        input.type = 'hidden';
                        input.name = 'id';
                        input.value = id;

                        form.appendChild(input);
                        document.body.appendChild(form);
                        form.submit();
                    }
                });

                return false;
            }
        </script>
    </body>
</html>
