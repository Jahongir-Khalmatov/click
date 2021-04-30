package lesson4.click.payload;

import lombok.Data;

import java.util.Date;

@Data
public class OutcomeDto {
private Integer id;
private Integer fromCardId;
private Integer ToCardId;
private Integer amount;
private Date date;
private Integer commissionAmount;
}
