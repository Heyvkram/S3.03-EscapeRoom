
package entities;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Clues {
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
        System.out.printf("    %-5d  %-25s  Level: %-25s  Theme: %-25s  Price: %.2f%n", getId(), getTitle(), getLevel(), getTheme(), getPrice());
    }

    public void printPriceInfoValues(){
        System.out.printf("    %-5d  %-25s   Price: %.2f%n", getId(), getTitle(), getPrice());
    }
}
