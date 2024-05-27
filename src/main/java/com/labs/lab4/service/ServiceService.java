package com.labs.lab4.service;

import com.labs.lab4.exception.IdNotFoundException;
import com.labs.lab4.model.Service;
import com.labs.lab4.repository.ServiceRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService {
    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<Service> findAllByIds(Collection<Integer> serviceIds) throws IdNotFoundException {
        List<Service> services = serviceRepository.findAllById(serviceIds);

        if (services.size() < serviceIds.size()) {
            List<Integer> notFoundIds = new ArrayList<>();
            for (Integer id : serviceIds)
                if (services.stream().noneMatch(service -> id.equals(service.getId())))
                    notFoundIds.add(id);

            StringBuilder sbNotFoundIds = new StringBuilder();
            notFoundIds.forEach(id -> sbNotFoundIds.append(id).append(", "));

            throw new IdNotFoundException(String.format("Services with ids: %s not found", sbNotFoundIds));
        }

        return services;
    }

    public List<Service> getAll() {
        return serviceRepository.findAll();
    }
}
