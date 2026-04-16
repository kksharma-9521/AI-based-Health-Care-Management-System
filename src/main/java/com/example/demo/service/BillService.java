package com.example.demo.service;

import com.example.demo.model.Bill;
import com.example.demo.repository.BillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class BillService {

    private static final Logger logger = LoggerFactory.getLogger(BillService.class);
    @Autowired
    private BillRepository billRepository;
    public Page<Bill> getAllBills(int page, int size){

        try {
            logger.info("Fetching all bills");
            Pageable pageable = PageRequest.of(page, size);
            return billRepository.findAll(pageable);
        }
        catch (Exception e){
            logger.error("Error fetching all bills: ",e);
            return null;
        }
    }

    public Bill getBillById(Long id){
        try {
            logger.info("Fetching bill by id");
            billRepository.findById(id);
            return null;
        }
        catch (Exception e){
            logger.error("Error fetching bill by id : ",e);
            return null;
        }
    }

    public Bill createBill(Bill bill){
        try {
            bill.setDate(LocalDate.now());
            bill.setTime(LocalTime.now());

            Bill savedBill = billRepository.save(bill);
            logger.info("Created bill {}", savedBill.getId());
            return savedBill;
        }
        catch (Exception e){
            logger.error("Error creating bill: ", e);
            return null;
        }
    }

    public void deleteBillById(Long id){
        try {
            logger.info("Delete bill");
            billRepository.deleteById(id);
        }
        catch (Exception e){
            logger.error("Error deleting bill: ",e);
        }
    }
}
