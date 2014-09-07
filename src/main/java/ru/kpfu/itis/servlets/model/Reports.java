package ru.kpfu.itis.servlets.model;

import java.util.Date;

/**
 * Created by vladislav on 15.05.14.
 */
public class Reports {

    private Integer id;

    private Date startDate;

    private Date endDate;

    private String performer;

    private String activity;

    private String convertedStartDate;

    private String convertedEndDate;

    public String getConvertedStartDate() {
        return convertedStartDate;
    }

    public void setConvertedStartDate(String convertedStartDate) {
        this.convertedStartDate = convertedStartDate;
    }

    public String getConvertedEndDate() {
        return convertedEndDate;
    }

    public void setConvertedEndDate(String convertedEndDate) {
        this.convertedEndDate = convertedEndDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
