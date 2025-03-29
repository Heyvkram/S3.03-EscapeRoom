
package entities;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Clues {
    private Long id;
    private String title;
    private String descriptionUser;
    private String descriptionAdmin;
    private Theme theme; //ver si utilizamos el mismo enum con Mark
    private int level;
    private int gamePhase;
    private LocalDate date;
    private double price;
    private double value;

    public String getWholeName() {
        return id + " : " + title;
    }
    public void printBasicInfoValues() {
        System.out.printf("     %-5d  %-25s  %-25s ,Nickname: %-25s%n", getId());
    }
    // printBasicInfoValues() modificar método con los valores correspondientes que quede bien hecho
}
