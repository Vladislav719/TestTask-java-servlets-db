package ru.kpfu.itis.servlets.dao;

import ru.kpfu.itis.servlets.db.DBConnect;
import ru.kpfu.itis.servlets.model.Reports;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by vladislav on 15.05.14.
 */
public class DZDAO {

    private Connection connection;


    public DZDAO(){
        connection = DBConnect.getConnection();
//        try {
////            connection = ConnectionFactory.getInstance().getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public List<Reports> getAllReports(){
        List<Reports> reportsList = new ArrayList<Reports>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from rep");
            String temp = "";
            while (rs.next()){
                Reports reports = new Reports();
                reports.setId(rs.getInt("ID"));
                Date stDate= rs.getDate("StartDate");
                Date endDate = rs.getDate("EndDate");
                String convertedStDate = simpleConvert(stDate);
                String convertedEndDate = simpleConvert(endDate);
                reports.setConvertedStartDate(convertedStDate);
                reports.setConvertedEndDate(convertedEndDate);
                reports.setStartDate(stDate);
                reports.setEndDate(endDate);
                reports.setPerformer(rs.getString("Performer"));
                reports.setActivity(rs.getString("Activity"));
                reportsList.add(reports);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return reportsList;

    }

    public List<Reports> getByNameAndDate(java.sql.Date start, java.sql.Date end, String name){
        List<Reports> reportsList = new ArrayList<Reports>();
        try {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM rep WHERE \"StartDate\" >= ? AND \"EndDate\" <= ? AND \"Performer\" = ? ");
            //SELECT * FROM rep WHERE "StartDate" >= '2014-02-13' AND "EndDate" <= '2014-05-16'  AND "Performer"='Nielsen'
            //WHERE ? BETWEEN StartDate AND EndDate
            statement.setDate(1,start);
            statement.setDate(2, end);
            statement.setString(3, name);
            ResultSet rs =  statement.executeQuery();
            if (rs.next()){
                Reports reports = new Reports();
                reports.setId(rs.getInt("ID"));
                Date stDate= rs.getDate("StartDate");
                Date endDate = rs.getDate("EndDate");
                String convertedStDate = simpleConvert(stDate);
                String convertedEndDate = simpleConvert(endDate);
                reports.setConvertedStartDate(convertedStDate);
                reports.setConvertedEndDate(convertedEndDate);
                reports.setStartDate(stDate);
                reports.setEndDate(endDate);
                reports.setPerformer(rs.getString("Performer"));
                reports.setActivity(rs.getString("Activity"));
                reportsList.add(reports);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  reportsList;
    }

    public List<Reports> getByName(String name){
        List<Reports> reportsList = new ArrayList<Reports>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM rep WHERE \"Performer\"=?");
            statement.setString(1,name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Reports reports = new Reports();
                reports.setId(rs.getInt("ID"));
                Date stDate= rs.getDate("StartDate");
                Date endDate = rs.getDate("EndDate");
                String convertedStDate = simpleConvert(stDate);
                String convertedEndDate = simpleConvert(endDate);
                reports.setConvertedStartDate(convertedStDate);
                reports.setConvertedEndDate(convertedEndDate);
                reports.setStartDate(stDate);
                reports.setEndDate(endDate);
                reports.setPerformer(rs.getString("Performer"));
                reports.setActivity(rs.getString("Activity"));
                reportsList.add(reports);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return reportsList;
    }

    private String simpleConvert(Date sqlDate){
        SimpleDateFormat format = new SimpleDateFormat("MMM dd, YYYY", Locale.UK);

        SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-mm-dd", Locale.UK);
        Date date;
        String res = null;
        //            date = sqlFormat.parse(sqlDate.toString());
        res = format.format(sqlDate);
        return res;
    }
}
