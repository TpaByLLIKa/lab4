package com.labs.lab4.model;

import com.labs.lab4.model.enums.Status;
import jakarta.persistence.*;
import org.springframework.data.domain.Persistable;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity(name = "lab4$Order")
@Table(name = "LAB4_ORDER")
public class Order implements Persistable<BigInteger> {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    private BigInteger id;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "START_TIME")
    private LocalTime startTime;

    @JoinColumn(name = "CLIENT_ID")
    @ManyToOne
    private Client client;

    @JoinTable(name = "LAB4_ORDERS_SERVICES",
            joinColumns = @JoinColumn(name = "ORDER_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "SERVICE_ID", referencedColumnName = "id"))
    @ManyToMany
    private List<Service> services;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "CLOSE_DATE")
    private LocalDate closeDate;

    @Column(name = "DURATION")
    private Integer duration;

    @Override
    public BigInteger getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return id == null || client == null;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getCode() {
        return client.getId() + "/" + startDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }


    public String getStatus() {
        return status.getId();
    }

    public void setStatus(String status) {
        this.status = Status.fromId(status);
    }

    public LocalDate getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(LocalDate closeDate) {
        this.closeDate = closeDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
