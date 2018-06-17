package com.example.demo.service;

import com.example.demo.dto.ChangePhoneStatusDTO;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Phone;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by damyanrusinov on 17/06/2018.
 */
@Repository
public interface DemoService {

    Set<Customer> getAllDummyCustomerData();

    Optional<Customer> getCustomer(String customerId);

    void setDummyData();

    List<String> getAllPhoneNumbers();

    List<String> getAllPhoneNumbersForCustomerId(String id);

    List<Phone> getAllPhoneDetails();

    void changePhoneStatus(ChangePhoneStatusDTO dto);

}
