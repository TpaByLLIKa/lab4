package com.labs.lab4.dto.request;

import java.time.LocalDate;

public class OrderReportRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer serviceId;

    public OrderReportRequest(LocalDate startDate, LocalDate endDate, Integer serviceId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.serviceId = serviceId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }
}
