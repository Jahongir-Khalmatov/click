package lesson4.click.repository;

import lesson4.click.entity.Income;
import lesson4.click.entity.Outcome;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OutcomeRepository extends JpaRepository<Outcome, Integer> {
    List<Outcome> findAllByFromCardId(Integer fromCard_id);
}
