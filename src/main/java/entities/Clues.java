
package entities;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Clues implements CalculablePriceInterface {
    private Long id;
    private String title;
    private String descriptionUser;
    private String descriptionAdmin;
    private String theme;
    private String level;
    private String gamePhase;
    private LocalDate clue_date_reg;
    private LocalDate clue_date_modify;
    private double price;
    private double value;


    public void printBasicInfoValues() {
        System.out.printf("    %-5d  Level: %-15s  Price: %4.2f   Theme: %-15s   Title: %-25s%n", getId(), getLevel(), getPrice(), getTheme(), getTitle());
    }

    public void printPriceInfoValues(){
        // System.out.printf("    %-5d  %-25s   Price: %.2f%n", getId(), getTitle(), getPrice());
        System.out.printf("    %-5d Price: %4.2f   Title: %-25s%n", getId(), getPrice(), getTitle());
    }
}
