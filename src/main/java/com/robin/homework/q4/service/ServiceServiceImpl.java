package com.robin.homework.q4.service;

import com.robin.homework.q4.exception.ResourceNotFoundException;
import com.robin.homework.q4.model.Service;
import com.robin.homework.q4.repository.ServiceRepository;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Service
@Transactional
public class ServiceServiceImpl implements ServiceService {

    private ServiceRepository serviceRepository;

    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Iterable<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public Service getService(long id) {
        return serviceRepository
          .findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Service not found"));
    }

    @Override
    public Service save(Service service) {
        return serviceRepository.save(service);
    }
}
