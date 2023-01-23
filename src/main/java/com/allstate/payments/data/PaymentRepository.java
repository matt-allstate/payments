package com.allstate.payments.data;

import com.allstate.payments.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    public List<Payment> findAllByCountry(String country);
    public List<Payment> findAllByOrderId(String order);

    //This is just an example - may not be quite right
//   @Query("SELECT p from Payment p where p.country in ('FRA','DEU','IRL','NLD')")
//    public List<Payment> findAllPaymentsInEuCountries();

   @Query("SELECT distinct p.country from Payment p")
   public List<String> getAllCountries();

}
