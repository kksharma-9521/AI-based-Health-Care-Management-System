package com.example.demo.api;

import com.example.demo.model.Bill;
import com.example.demo.service.BillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bills")
public class BillController {

    public static final Logger logger = (Logger) LoggerFactory.getLogger(BillController.class);

    @Autowired
    private BillService billService;

    @GetMapping
    public Page<Bill> getBills(@RequestParam (defaultValue = "0") int page,
                               @RequestParam (defaultValue = "2")int size){
        logger.info("Received request to fetch all bills");
        return billService.getAllBills(page, size);
    }
    @PostMapping
    public Bill addBill(@RequestBody Bill bill){
        logger.info("Received request to add bill");
        return billService.createBill(bill);
    }
    @GetMapping("/{id}")
    public Bill getBillById(@PathVariable Long id){
        logger.info("Received request to fetch bill by id");
        return billService.getBillById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteBillById(@PathVariable Long id){
        logger.info("Received request to delete bill");
        billService.deleteBillById(id);
    }
}
