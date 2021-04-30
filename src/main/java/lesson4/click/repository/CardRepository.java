package lesson4.click.repository;

import lesson4.click.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, Integer> {
    Card findByNumber(Integer number);
    boolean existsByNumber(String number);
    Card findByUserName(String userName);
}
