package lesson4.click.repository;

import lesson4.click.entity.Card;
import lesson4.click.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IncomeRepository extends JpaRepository<Income, Integer> {
List<Income> findAllByReceivingCardId(Card receivingCardId);
    //List<Income>findAllBy
}

