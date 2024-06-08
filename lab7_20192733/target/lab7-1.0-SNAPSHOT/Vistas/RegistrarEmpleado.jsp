<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.beans.Employee" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Registrar Empleado</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    </head>
    <body>
        <div class="container mt-5">
            <h2 class="mb-4">Registrar Empleado</h2>
            <form action="${pageContext.request.contextPath}/home?action=registrarEmpleado" method="post" class="needs-validation" novalidate>
                <div class="form-group">
                    <label for="full_name">Nombre Completo:</label>
                    <input type="text" class="form-control" id="full_name" name="full_name" placeholder="Nombre" maxlength="50" required>
                    <div class="invalid-feedback">Por favor, ingrese el nombre completo.</div>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="email" maxlength="25" required>
                    <div class="invalid-feedback">Por favor, ingrese un email válido.</div>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Contraseña" maxlength="65" required>
                    <div class="invalid-feedback">Por favor, ingrese una contraseña.</div>
                </div>
                <div class="form-group">
                    <label for="phone_number">Phone Number:</label>
                    <input type="text" class="form-control" id="phone_number" name="phone_number" placeholder="Número de teléfono" maxlength="20" required>
                    <div class="invalid-feedback">Por favor, ingrese un número de teléfono.</div>
                </div>
                <div class="form-group">
                    <label for="hireDate">Hire Date:</label>
                    <input type="date" class="form-control" id="hireDate" name="hireDate" required>
                    <div class="invalid-feedback">Por favor, ingrese la fecha de contratación.</div>
                </div>
                <div class="form-group">
                    <label for="job_id">Job ID:</label>
                    <input type="text" class="form-control" id="job_id" name="job_id" placeholder="ID del trabajo" maxlength="10" required>
                    <div class="invalid-feedback">Por favor, ingrese el ID del trabajo.</div>
                </div>
                <div class="form-group">
                    <label for="salary">Salary:</label>
                    <input type="number" class="form-control" id="salary" name="salary" placeholder="Salario" step="0.01" required>
                    <div class="invalid-feedback">Por favor, ingrese el salario.</div>
                </div>
                <div class="form-group">
                    <label for="commission_pct">Commission Pct:</label>
                    <input type="text" class="form-control" id="commission_pct" name="commission_pct" placeholder="Comisión" step="0.01" required>
                    <div class="invalid-feedback">Por favor, ingrese el porcentaje de comisión.</div>
                </div>
                <div class="form-group">
                    <label for="manager_id">Manager ID:</label>
                    <input type="number" class="form-control" id="manager_id" name="manager_id" placeholder="ID del supervisor" required>
                    <div class="invalid-feedback">Por favor, ingrese el ID del supervisor.</div>
                </div>
                <div class="form-group">
                    <label for="department_id">Department ID:</label>
                    <input type="text" class="form-control" id="department_id" name="department_id" placeholder="ID del departamento" required>
                    <div class="invalid-feedback">Por favor, ingrese el ID del departamento.</div>
                </div>
                <div class="form-group">
                    <label for="enabled">Enabled:</label>
                    <input type="text" class="form-control" id="enabled" name="enabled" placeholder="Estado" required>
                    <div class="invalid-feedback">Por favor, ingrese el estado.</div>
                </div>
                <button type="submit" class="btn btn-primary" onclick="return Confirmacion();">Registrar empleado</button>
            </form>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script>
            // Example starter JavaScript for disabling form submissions if there are invalid fields
            (function () {
                'use strict'
                window.addEventListener('load', function () {
                    // Fetch all the forms we want to apply custom Bootstrap validation styles to
                    var forms = document.getElementsByClassName('needs-validation')
                    // Loop over them and prevent submission
                    var validation = Array.prototype.filter.call(forms, function (form) {
                        form.addEventListener('submit', function (event) {
                            if (form.checkValidity() === false) {
                                event.preventDefault()
                                event.stopPropagation()
                            }
                            form.classList.add('was-validated')
                        }, false)
                    })
                }, false)
            })()

            function Confirmacion() {
                Swal.fire({
                    title: "¿Estás seguro?",
                    text: "El proceso se dará",
                    icon: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#00913f",
                    cancelButtonColor: "#d33",
                    confirmButtonText: "Sí, registrar",
                }).then((result) => {
                    if (result.isConfirmed) {
                        Swal.fire({
                            title: "¡Registrado!",
                            text: "El personal ha sido registrado con éxito",
                            icon: "success",
                        }).then(() => {
                            document.querySelector("form").submit();
                        });
                    }
                });

                return false;
            }
        </script>
    </body>
</html>
