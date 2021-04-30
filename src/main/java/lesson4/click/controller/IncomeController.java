package lesson4.click.controller;

import lesson4.click.entity.Income;
import lesson4.click.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/income")
public class IncomeController {
    @Autowired
    IncomeService incomeService;
    @GetMapping
    public HttpEntity<?> getUserIncomes(HttpServletRequest request){
        List<Income> incomeList = incomeService.getUserIncomes(request);
    if (incomeList==null){
        return ResponseEntity.status(409).body(null);
    }
    return ResponseEntity.ok(incomeList);
    }

}
