package com.allstate.payments.service;

import com.allstate.payments.domain.Payment;
import com.allstate.payments.exceptions.PaymentNotFoundException;

import java.util.List;

public interface PaymentService {

    public List<Payment> getAllPayments();
    public Payment getById(Integer id) throws PaymentNotFoundException;

    public List<Payment> getByCountry(String country);

    public List<Payment> getByOrder(String order);

    public List<String> getAllCountries();
}
