package lesson4.click.controller;

import lesson4.click.entity.Outcome;
import lesson4.click.service.OutcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/outcome")
public class OutcomeController {
    @Autowired
    OutcomeService outcomeService;
    @GetMapping
    public HttpEntity<?> getUserOutcome(HttpServletRequest request){
        List<Outcome> list = outcomeService.getUserOutcomes(request);
        if (list!=null){
            return ResponseEntity.ok(list);
        }
        return ResponseEntity.status(409).body(null);
    }

}
