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
}
