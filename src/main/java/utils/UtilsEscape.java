package utils;

import entities.CalculablePriceInterface;

import java.util.List;

public class UtilsEscape {

    public static double sumAllPrices(List<CalculablePriceInterface> itemsList){
        return itemsList.stream().mapToDouble(CalculablePriceInterface::getPrice).sum();
    }

    public static boolean isNotNullOrEmpty(String strObj){
        return !isNullOrEmpty(strObj);
    }
    public static boolean isNullOrEmpty(String strObj){
        return (strObj==null || strObj.isEmpty() || strObj.isBlank());
    }
}
