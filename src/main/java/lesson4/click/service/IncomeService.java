package lesson4.click.service;

import lesson4.click.entity.Card;
import lesson4.click.entity.Income;
import lesson4.click.entity.Outcome;
import lesson4.click.repository.CardRepository;
import lesson4.click.repository.IncomeRepository;
import lesson4.click.repository.OutcomeRepository;
import lesson4.click.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class IncomeService {
@Autowired
    IncomeRepository incomeRepository;
@Autowired
CardRepository cardRepository;
@Autowired
    OutcomeRepository outcomeRepository;
@Autowired
    JwtProvider jwtProvider;

public List<Income> getUserIncomes(HttpServletRequest request){
    String authorization = request.getHeader("Authorization");
    String token = authorization.substring(7);
    String username = jwtProvider.getUsernameFromToken(token);
    Card card = cardRepository.findByUserName(username);
    if (card==null){
        return null;
    }
    List<Income> list = incomeRepository.findAllByReceivingCardId(card);
    return list;

}
}
