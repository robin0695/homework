package com.robin.homework.q4.controller;

import com.robin.homework.q4.model.Service;
import com.robin.homework.q4.service.ServiceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping(value = { "", "/" })
    public @NotNull Iterable<Service> getServices() {
        return serviceService.getAllServices();
    }
}
