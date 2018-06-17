package com.example.demo.service.impl;

import com.example.demo.dto.ChangePhoneStatusDTO;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Phone;
import com.example.demo.service.DemoService;
import com.example.demo.utils.DataGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toList;

/**
 * Created by damyanrusinov on 17/06/2018.
 */
@Service
public class DemoServiceImpl implements DemoService {

    private DataGenerator dataGenerator = new DataGenerator();

    private Set<Customer> allCustomers;

    @Override
    public void setDummyData() {
        this.allCustomers = dataGenerator.generateDummyData();
    }

    public Set<Customer> getAllDummyCustomerData() {
        return this.allCustomers;
    }

    @Override
    public List<Phone> getAllPhoneDetails() {
        return Optional.ofNullable(
                allCustomers
                        .stream()
                        .map(customer -> customer.getPhoneDetails())
                        .collect(toList()))
                .orElse(Collections.EMPTY_LIST);
    }

    @Override
    public List<String> getAllPhoneNumbers() {
         return allCustomers.stream()
                 .flatMap(customer -> customer.getPhoneDetails().stream())
                 .map(phone -> phone.getPhoneNumber())
                 .collect(toList());

    }

    @Override
    public List<String> getAllPhoneNumbersForCustomerId(String id) {
        Optional.ofNullable(id).orElseThrow( () -> new HttpClientErrorException(HttpStatus.NO_CONTENT));
        return Optional.ofNullable(
                allCustomers
                        .stream()
                        .filter(customer -> customer.getId().equals(id))
                        .flatMap(customer -> customer.getPhoneDetails()
                                .stream()
                                .map(c -> c.getPhoneNumber()))
                        .collect(toList()))
                .orElse(Collections.EMPTY_LIST);
    }

    @Override
    public void changePhoneStatus(ChangePhoneStatusDTO dto) {
        Optional.ofNullable(dto).orElseThrow( () -> new HttpClientErrorException(HttpStatus.NO_CONTENT));
        Optional.ofNullable(dto.getCustomerId()).orElseThrow( () -> new HttpClientErrorException(HttpStatus.NO_CONTENT));
        Optional.ofNullable(dto.getName()).orElseThrow( () -> new HttpClientErrorException(HttpStatus.NO_CONTENT));
        Optional.ofNullable(dto.getPhoneNumber()).orElseThrow( () -> new HttpClientErrorException(HttpStatus.NO_CONTENT));

        for(Customer customer: allCustomers) {
            if(customer.getId().equals(dto.getCustomerId()) && customer.getName().equals(dto.getName()))
                for(Phone phone: customer.getPhoneDetails())
                    phone.setActive(dto.getStatus());
        }
    }

    @Override
    public Optional<Customer> getCustomer(String customerId) {
        return Optional.ofNullable(allCustomers.stream()
                .filter(customer -> customer.getId().equals(customerId))
                .findFirst()).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NO_CONTENT));
    }
}
