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
        System.out.printf("    %-5d  %-25s   Description: %-50s  Clue Valor: %-5d%n", getId(), getName(), getDescription(), getClueValor());
    }

    public void printPriceInfoValues(){
        System.out.printf("    %-5d  %-25s   Price: %.2f%n", getId(), getName(), getPrice());
    }

    public void printThemeValorValues(){
        System.out.printf("    %-5d  %-25s   Theme: %-25s   Price: %.2f%n", getId(), getName(), getTheme(), getPrice());
    }
}
