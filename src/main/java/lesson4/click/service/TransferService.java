package lesson4.click.service;

import lesson4.click.entity.Card;
import lesson4.click.entity.Income;
import lesson4.click.entity.Outcome;
import lesson4.click.entity.Result;
import lesson4.click.payload.TransferDto;
import lesson4.click.repository.CardRepository;
import lesson4.click.repository.IncomeRepository;
import lesson4.click.repository.OutcomeRepository;
import lesson4.click.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

@Service
public class TransferService {
@Autowired
    CardRepository cardRepository;
@Autowired
    IncomeRepository incomeRepository;
@Autowired
    OutcomeRepository outcomeRepository;
@Autowired
    JwtProvider jwtProvider;
double total=0;

//public HttpEntity<?> sendMoney(TransferDto transferDto, HttpServletRequest request){
//    String token = request.getHeader(("Authorization"));
//    token=token.substring(7);
//    String username = jwtProvider.getUsernameFromToken(token);
//
////    String authorization = request.getHeader("Authorization");
////    String usernameFromToken = jwtProvider.getUsernameFromToken(authorization);
//
//    Optional<Card> optionalFromCard = cardRepository.findByNumber(transferDto.getFromCardId());
//    Optional<Card> optionalToCard = cardRepository.findByNumber(transferDto.getToCardId());
//    if (!optionalFromCard.isPresent()) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sending Card id not founded");
//    }
//    if (!optionalToCard.isPresent()){
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sending Card id not founded");
//    }
//    if (optionalFromCard==optionalToCard){
//        return ResponseEntity.status(HttpStatus.CONFLICT).body("The card Numbers are similar");
//    }
//    Card toCard = optionalToCard.get();
//    Card fromCard = optionalFromCard.get();
//    if (!fromCard.getUserName().equals(username)) {
//        return ResponseEntity.status(HttpStatus.CONFLICT).body("Card is not yours");
//    }
//    Double balance = fromCard.getBalance();
//    Double amount = transferDto.getAmount();
//    Double commissionAmount=amount*0.01;
//    double total=commissionAmount+amount;
//    Double balance1 = toCard.getBalance();
//    if (total>balance){
//    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("The money does not enough to transfer");
//}
//    fromCard.setBalance(balance-total);
//    toCard.setBalance(balance1+amount);
//
//    cardRepository.save(fromCard);
//    cardRepository.save(toCard);
//return     ResponseEntity.status(201).body("Transferd");
//
//
//
//
//
//
//}
    public Result sendMoney(TransferDto transferDto,HttpServletRequest request){
        String token = request.getHeader(("Authorization"));
    token=token.substring(7);
    String username = jwtProvider.getUsernameFromToken(token);
        Card sendingCard = cardRepository.findByUserName(username);
        if (sendingCard==null){
            return new Result(false,"User Card not founded");
        }
       Optional<Card> optionalCard = cardRepository.findById(transferDto.getToCardId());
        if (!optionalCard.isPresent()){
            return new Result(false,"Siz yubormoqchi bo'lgan card mavjud emas");
        }
        Card receivedCard = optionalCard.get();

//Outcome ga ma'lumotlarni saqlash
        Double balance = sendingCard.getBalance();
        Double amount = transferDto.getAmount();
        Double commissionAmount=amount*0.01;
        double total=commissionAmount+amount;
        if (total>balance){
            return new Result(false,"Yetarli mablag mavjud emas");
        }
        if (sendingCard.getId().equals(receivedCard.getId())){
            return new Result(false,"o'zingizni kartangizga pul jonata olmaysiz");
        }
        Outcome outcome = new Outcome();
        outcome.setCommissionAmount(commissionAmount);
        outcome.setAmount(total);
        outcome.setDate(new Date());
        outcome.setFromCard(sendingCard);
        outcome.setToCard(receivedCard);
        outcomeRepository.save(outcome);
        //Yuborgan karta Balansini kamaytirish
        sendingCard.setBalance(balance-total);
        receivedCard.setBalance(receivedCard.getBalance()+amount);
        cardRepository.save(sendingCard);
        cardRepository.save(receivedCard);
        // Income un Service
        Income income = new Income();
        income.setAmount(amount);
        income.setDate(new Date());
        income.setSendingCardId(sendingCard);
        income.setReceivingCardId(receivedCard);
        incomeRepository.save(income);
        return new Result(true,"Success");
    }
}
