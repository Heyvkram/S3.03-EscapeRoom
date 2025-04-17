package entities;

import lombok.Data;

@Data

public class DecorationItem implements CalculablePriceInterface {
    private Long id;
    private String name;
    private String description;
    private String theme;
    private double price;
    private int clueValor;
    private String img;

    public String getDecorationItemData(){
        return name + " " + description;
    }

    public void printBasicInfoValues() {
        System.out.printf("     %-5d   %-25s   Description: %-50s   Clue Valor: %-5d%n", getId(), getName(), getDescription(), getClueValor());
    }

    public void printPriceInfoValues(){
        System.out.printf("    %-5d   Price: %.2f   Name: %-25s%n", getId(), getPrice(), getName());
    }

    public void printThemeValorValues(){
        System.out.printf("    %-5d   Price: %.2f   Name: %-25s   Theme: %-25s   %n", getId(), getPrice(), getName(), getTheme());
    }
}
