package com.labs.lab4.service;

import com.labs.lab4.dto.request.OrderReportRequest;
import com.labs.lab4.dto.request.PatchOrderRequest;
import com.labs.lab4.dto.response.AppError;
import com.labs.lab4.dto.request.CreateOrderRequest;
import com.labs.lab4.dto.response.BarcodeResponse;
import com.labs.lab4.dto.response.IdResponse;
import com.labs.lab4.dto.response.OrderPdfResponse;
import com.labs.lab4.exception.IdNotFoundException;
import com.labs.lab4.model.Order;
import com.labs.lab4.model.enums.Status;
import com.labs.lab4.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientService clientService;
    private final ServiceService serviceService;

    public OrderService(OrderRepository orderRepository, ClientService clientService, ServiceService serviceService) {
        this.orderRepository = orderRepository;
        this.clientService = clientService;
        this.serviceService = serviceService;
    }

    public ResponseEntity<IdResponse> getRecId() {
        BigInteger id = orderRepository.getRecId().add(BigInteger.valueOf(1));

        if (id.intValue() < 0)
            id = BigInteger.valueOf(1);

        while (orderRepository.existsById(id))
            id = id.add(BigInteger.valueOf(1));

        return ResponseEntity.ok(new IdResponse(id));
    }

    public ResponseEntity<?> create(CreateOrderRequest createOrderRequest) {
        Order order = new Order();
        try {
            order.setId(createOrderRequest.getId());
            order.setStartDate(LocalDate.now());
            order.setStartTime(LocalTime.now());
            order.setStatus(Status.NEW.getId());
            order.setDuration(createOrderRequest.getDuration());
            order = orderRepository.save(order);

        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                    new AppError("Failed to create order: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        try {
            String barcode = String.valueOf(order.getId()) +
                    order.getStartDate().getDayOfMonth() +
                    order.getStartDate().getMonth().getValue() +
                    order.getStartDate().getYear() +
                    order.getStartTime().getHour() +
                    order.getStartTime().getMinute() +
                    order.getDuration() / 60 +
                    123456;
            return ResponseEntity.ok(new BarcodeResponse(barcode));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                    new AppError("Failed to create barcode: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    public ResponseEntity<?> patch(BigInteger id, PatchOrderRequest patchOrderRequest) {
        Optional<Order> orderOptional = orderRepository.findById(id);

        if (orderOptional.isEmpty())
            return new ResponseEntity<>(
                    new AppError("Order with id " + id + "does not exist"),
                    HttpStatus.NOT_FOUND
            );

        Order order = orderOptional.get();
        try {
            order.setClient(clientService.findById(patchOrderRequest.getClientId()));
            order.setServices(serviceService.findAllByIds(patchOrderRequest.getServiceIds()));
            order = orderRepository.save(order);
        } catch (IdNotFoundException e) {
            return new ResponseEntity<>(
                    new AppError(e.getMessage()),
                    HttpStatus.BAD_REQUEST
            );
        }

        OrderPdfResponse response = orderToPdf(order);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> close(BigInteger id) {
        Optional<Order> orderOptional = orderRepository.findById(id);

        if (orderOptional.isEmpty())
            return new ResponseEntity<>(
                    new AppError("Order with id " + id + "does not exist"),
                    HttpStatus.NOT_FOUND
            );

        Order order = orderOptional.get();
        order.setStatus(Status.CLOSED.getId());
        order.setCloseDate(LocalDate.now());
        orderRepository.save(order);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> getForReport(OrderReportRequest request) {
        if (request.getServiceId() != null) {
            try {
                return ResponseEntity.ok(
                        orderRepository.findAllByStartDateAfterAndStartDateBeforeAndServicesContains(
                                request.getStartDate(),
                                request.getEndDate(),
                                serviceService.findAllByIds(List.of(request.getServiceId()))
                        ).stream().map(this::orderToPdf).collect(Collectors.toList())
                );
            } catch (IdNotFoundException e) {
                return new ResponseEntity<>(
                        new AppError(e.getMessage()),
                        HttpStatus.BAD_REQUEST
                );
            }
        } else
            return ResponseEntity.ok(
                    orderRepository.findAllByStartDateAfterAndStartDateBefore(
                            request.getStartDate(),
                            request.getEndDate()
                    ).stream().map(this::orderToPdf).collect(Collectors.toList())
            );
    }

    private OrderPdfResponse orderToPdf(Order order) {
        OrderPdfResponse response = new OrderPdfResponse();
        response.setStartDate(order.getStartDate());
        response.setStartTime(order.getStartTime());
        response.setClientId(order.getClient().getId());
        response.setOrderId(order.getId());
        response.setClientName(order.getClient().getName());
        response.setClientAddress(order.getClient().getAddress());
        response.setServices(order.getServices());
        AtomicReference<Integer> price = new AtomicReference<>(0);
        order.getServices().forEach(service -> price.updateAndGet(v -> v + service.getCost()));
        response.setPrice((double) (price.get() * order.getDuration() / 60));
        return response;
    }
}
