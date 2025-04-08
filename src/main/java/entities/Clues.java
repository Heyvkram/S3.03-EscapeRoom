
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
        System.out.println("Clues list:  " + getId() + " " + getTitle() + " " + getLevel() + " " + getTheme());
    }
}
