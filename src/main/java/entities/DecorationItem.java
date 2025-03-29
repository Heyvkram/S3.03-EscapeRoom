package entities;

import lombok.Data;
import utils.Themes;

@Data

public class DecorationItem {
    private Long id;
    private String name;
    private String description;
    //private Themes theme;
    private double price;
    private int clueValor;
    private String img;

    public String getDecorationItemData(){
        return name + " " + description;
    }

    public void printBasicInfoValues() {
        System.out.printf("     %-5d  %-25s   ,Description: %-25s%n", getId(), getName(), getDescription());
    }
}
