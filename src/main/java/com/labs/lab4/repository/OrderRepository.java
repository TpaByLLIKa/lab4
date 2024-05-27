package com.labs.lab4.repository;

import com.labs.lab4.model.Order;
import com.labs.lab4.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, BigInteger> {

    @Query(
            value = "SELECT MAX(id) " +
                    "FROM lab4_order " +
                    "WHERE (start_date, start_time) = (" +
                    "    SELECT o1.start_date, MAX(o1.start_time)" +
                    "    FROM lab4_order o1" +
                    "    WHERE o1.start_date = (" +
                    "        SELECT MAX(o2.start_date)" +
                    "        FROM lab4_order o2" +
                    "    )" +
                    "    GROUP BY o1.start_date" +
                    ")",
            nativeQuery = true)
    BigInteger getRecId();

    boolean existsById(@NonNull BigInteger id);

    List<Order> findAllByStartDateAfterAndStartDateBefore(LocalDate after, LocalDate before);
    List<Order> findAllByStartDateAfterAndStartDateBeforeAndServicesContains(LocalDate startDate, LocalDate startDate2, List<Service> services);
}
