package lesson4.click.controller;

import lesson4.click.entity.Result;
import lesson4.click.payload.TransferDto;
import lesson4.click.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/transfer")
public class TransferController {
    @Autowired
    TransferService transferService;
    @PostMapping()
    public HttpEntity<?> sendMoney(@RequestBody TransferDto transferDto, HttpServletRequest request){
        Result result = transferService.sendMoney(transferDto, request);
        if (result.isSuccess()){
           return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(409).body(result);
    }
}
