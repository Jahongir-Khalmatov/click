package lesson4.click.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Outcome {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
@ManyToOne
public Card fromCard;
@ManyToOne
public Card ToCard;
public Double amount;
public Double commissionAmount;
public Date date;
}
