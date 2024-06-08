package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.beans.Employee;
import model.daos.DepartmentDAO;
import model.util.Ubicacion;


@WebServlet(name = "DepartmentServlet", value = "/Department")
public class DepartmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null? "tablaUbicaciones" : request.getParameter("action");

        String vista;
        RequestDispatcher rd;
        switch(action){
            case "tablaUbicaciones":
                int idDepartment = Integer.parseInt(request.getParameter("id"));
                DepartmentDAO departmentDAO = new DepartmentDAO();
                List<Ubicacion> listaUbicacion = departmentDAO.obtenerUbicacionesDelDepartamento(idDepartment);


                request.setAttribute("listaubicacion", listaUbicacion);
                vista = "/Vistas/ListaDepartamentos.jsp";
                rd = request.getRequestDispatcher(vista);
                rd.forward(request,response);
                break;
            case "pagPrincipal2":

                break;

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}