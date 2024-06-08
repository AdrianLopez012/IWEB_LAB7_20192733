<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.daos.JobDAO" %>
<%@ page import="model.daos.EmployeeDAO" %>
<%@ page import="model.beans.Employee" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="model.daos.DepartmentDAO" %>
<%@ page import="model.util.Ubicacion" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Ubicación Departamento</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    </head>
    <body>
        <div class="container mt-5">
            <h2 class="mb-4">Ubicación Departamento</h2>
            <%
                List<Ubicacion> listaubicacion = (List<Ubicacion>) request.getAttribute("listaubicacion");
            %>
            <div class="table-responsive">
                <table class="table table-striped table-bordered">
                    <thead class="thead-dark">
                        <tr>
                            <th>Dirección</th>
                            <th>Código Postal</th>
                            <th>Ciudad</th>
                            <th>Provincia del Estado</th>
                            <th>País</th>
                            <th>Región</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Ubicacion ubicacion : listaubicacion) { %>
                        <tr>
                            <td><%= ubicacion.getStreet_address() %></td>
                            <td><%= ubicacion.getPostal_code()%></td>
                            <td><%= ubicacion.getCity() %></td>
                            <td><%= ubicacion.getState_province() %></td>
                            <td><%= ubicacion.getCountry_name() %></td>
                            <td><%= ubicacion.getRegion_name() %></td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
