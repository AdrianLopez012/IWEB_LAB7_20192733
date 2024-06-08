<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.beans.Employee" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Editar Empleado</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    </head>
    <body>
        <div class="container mt-5">
            <h2 class="mb-4">Editar Empleado</h2>
            <%
                Employee employee = (Employee) request.getAttribute("empleado");
                if (employee == null) {
                    out.print("<p class='alert alert-danger'>Error: No se encontró el profesor.</p>");
                } else {
            %>
            <form action="${pageContext.request.contextPath}/home?action=actualizarEmpleado" method="post">
                <input type="hidden" name="action" value="actualizarEmpleado">
                <input type="hidden" name="idEmpleado" value="<%= employee.getEmployeeId() %>">

                <div class="form-group">
                    <label for="full_name">Nombre Completo:</label>
                    <input type="text" class="form-control" id="full_name" name="full_name" value="<%=employee.getFullNameEmployee() %>" maxlength="50">
                </div>

                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" class="form-control" id="email" name="email" value="<%= employee.getEmail()%>" maxlength="25" required>
                </div>

                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="text" class="form-control" id="password" name="password" value="<%=employee.getPassword() %>" maxlength="65">
                </div>

                <div class="form-group">
                    <label for="phone_number">Phone Number:</label>
                    <input type="text" class="form-control" id="phone_number" name="phone_number" value="<%=employee.getPhoneNumber()%>" maxlength="20">
                </div>

                <div class="form-group">
                    <label for="hireDate">Hire Date:</label>
                    <input type="text" class="form-control" id="hireDate" name="hireDate" value="<%=employee.getHireDate() %>" required>
                </div>

                <div class="form-group">
                    <label for="job_id">Job ID:</label>
                    <input type="text" class="form-control" id="job_id" name="job_id" value="<%= employee.getJobId()%>" maxlength="10" required>
                </div>

                <div class="form-group">
                    <label for="salary">Salary:</label>
                    <input type="number" class="form-control" id="salary" name="salary" value="<%=employee.getSalary() %>" step="0.01">
                </div>

                <div class="form-group">
                    <label for="commission_pct">Commission Pct:</label>
                    <input type="text" class="form-control" id="commission_pct" name="commission_pct" value="<%=employee.getCommissionPct() %>" step="0.01">
                </div>

                <div class="form-group">
                    <label for="manager_id">Manager ID:</label>
                    <input type="number" class="form-control" id="manager_id" name="manager_id" value="<%=employee.getManagerId() %>">
                </div>

                <div class="form-group">
                    <label for="department_id">Department ID:</label>
                    <input type="text" class="form-control" id="department_id" name="department_id" value="<%=employee.getDepartmentId() %>">
                </div>

                <div class="form-group">
                    <label for="enabled">Enabled:</label>
                    <input type="text" class="form-control" id="enabled" name="enabled" value="<%=employee.getEnabled() %>" required>
                </div>

                <button type="submit" class="btn btn-success" onclick="return Actualizar();">Actualizar personal</button>
            </form>
            <% } %>
        </div>
        <script>
            function Actualizar() {
                Swal.fire({
                    title: "¿Estás seguro?",
                    text: "El proceso de actualizar puede variar muchas cosas de la base de datos",
                    icon: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#00913f",
                    cancelButtonColor: "#d33",
                    confirmButtonText: "Sí, actualizar"
                }).then((result) => {
                    if (result.isConfirmed) {
                        Swal.fire({
                            title: "¡Actualizado!",
                            text: "El personal ha sido actualizado con éxito",
                            icon: "success"
                        }).then(() => {
                            document.querySelector("form").submit();
                        });
                    }
                });

                // Evitar que el formulario se envíe automáticamente
                return false;
            }
        </script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
