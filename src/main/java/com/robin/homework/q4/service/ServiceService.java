package com.robin.homework.q4.service;

import com.robin.homework.q4.model.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Validated
public interface ServiceService {

    @NotNull Iterable<Service> getAllServices();

    Service getService(@Min(value = 1L, message = "Invalid service ID.") long id);

    Service save(Service service);
}
