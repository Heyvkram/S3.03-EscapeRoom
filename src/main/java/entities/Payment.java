package entities;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Payment {
    Long id;
    Long userId;
    String paymentMode;
    private LocalDateTime paymentDate;

}
