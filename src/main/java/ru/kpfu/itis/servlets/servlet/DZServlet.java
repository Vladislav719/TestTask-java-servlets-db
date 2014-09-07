package ru.kpfu.itis.servlets.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.kpfu.itis.servlets.dao.DZDAO;
import ru.kpfu.itis.servlets.model.Reports;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOError;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

/**
 * Created by vladislav on 15.05.14.
 */
public class DZServlet extends HttpServlet {

    private DZDAO dao;

    public DZServlet(){
        dao = new DZDAO();
    }

    public String simpleConvert(String sqlDate){
        SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");

        SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date date;
        String res = "";
        try {
            date = sqlFormat.parse(sqlDate);
            res = format.format(date);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return res;
    }


    //TODO привести в нормальный вид
    public HashSet<String> getPerformersList(){
        List<Reports> list = dao.getAllReports();
        HashSet<String> performerNames = new HashSet<String>();
        for (Reports reports : list) {
            performerNames.add(reports.getPerformer());
        }
        return performerNames;
    }

    //для SELECT > OPTION WHERE ALL_Performers



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException
    {

        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String performer = request.getParameter("performer");

        if (startDate.equals("") && endDate.equals("")){
            if (performer.equals("0")){
                request.setAttribute("data", dao.getAllReports());
            } else {
                request.setAttribute("data", dao.getByName(performer));
            }
        } else{
//            Date startD = null;
//            try {
//                startD = new SimpleDateFormat("yyyy-mm-dd", Locale.UK).parse(startDate);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            Date endD = null;
//            try {
//                endD = new SimpleDateFormat("yyyy-mm-dd", Locale.UK).parse(endDate);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
            request.setAttribute("data", dao.getByNameAndDate(java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate),performer));
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{

        if (request.getParameter("updateList").equals("get")) {
            //Send JSON to client with names of each performer
            ObjectMapper mapper = new ObjectMapper();
            response.setContentType("application/json");

            mapper.writeValue(response.getOutputStream(), getPerformersList());
        }
    }
}
