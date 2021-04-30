package lesson4.click.controller;

import lesson4.click.entity.Card;
import lesson4.click.repository.CardRepository;
import lesson4.click.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

@Autowired
    CardService cardService;
@GetMapping("/get")
public List<Card> getList(){
    return cardService.getList();
}
@GetMapping("/getById/{id}")
    public Card getById(@PathVariable Integer id){
    return cardService.getById(id);
}
@PostMapping("/addCard")
    public HttpEntity<?> addCard(@RequestBody Card card, HttpServletRequest request,  @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date d1
                                 ){
    return cardService.add(card, request);
}
@PutMapping("/editCard/{id}")
    public HttpEntity<?> editCard(@PathVariable Integer id,Card card,HttpServletRequest request){
    return cardService.edit(id, card, request);
}
}
