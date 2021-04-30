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

public class Card {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
public String userName;
@Column(unique = true)
public String  number;
public Date expiredDate;
public boolean active=true;
private Double balance;

}
