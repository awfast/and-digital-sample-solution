package com.example.demo.utils;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Phone;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by damyanrusinov on 17/06/2018.
 */
public class DataGenerator {

    private Set<Customer> allCustomers;

    public DataGenerator() {
        this.allCustomers = generateDummyData();
    }

    public Set<Customer> getAllCustomers() {
        return this.allCustomers;
    }
    public Set<Customer> generateDummyData() {
        Set<Customer> dummyCustomersData = new HashSet<>();

        final Customer customer1 = new Customer()
                .setId("123")
                .setName("Alice")
                .setPhoneDetails(generateDummyPhoneNumbers(1));
        final Customer customer2 = new Customer()
                .setId("1234")
                .setName("Bob")
                .setPhoneDetails(generateDummyPhoneNumbers(2));
        final Customer customer3 = new Customer()
                .setId("12345")
                .setName("John")
                .setPhoneDetails(generateDummyPhoneNumbers(5));

        dummyCustomersData.add(customer1);
        dummyCustomersData.add(customer2);
        dummyCustomersData.add(customer3);

        return dummyCustomersData;
    }

    public List<Phone> generateDummyPhoneNumbers(int numberOfPhoneNumbers) {
        List<Phone> phoneDetails = new ArrayList<>();
        Random rand = new Random();
        for(int i=0; i<numberOfPhoneNumbers; i++) {
            int num1 = (rand.nextInt(7) + 1) * 100 + (rand.nextInt(8) * 10) + rand.nextInt(8);
            int num2 = rand.nextInt(743);
            int num3 = rand.nextInt(10000);

            DecimalFormat dfCountryCode = new DecimalFormat("00"); // 3 zeros

            String phoneNumber = dfCountryCode.format(num1) + " " + num2 + " " + num3;

            Phone phone = new Phone()
                    .setNumber(phoneNumber)
                    .setActive(false);

            phoneDetails.add(phone);
        }
        return phoneDetails;
    }
}
