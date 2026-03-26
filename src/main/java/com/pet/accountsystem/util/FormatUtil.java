package com.pet.accountsystem.util;


import java.math.BigDecimal;
import java.math.RoundingMode;

public final class FormatUtil {


    public static String toScale(BigDecimal bigDecimal){
        return bigDecimal.setScale(3, RoundingMode.DOWN).toString();
    }


}
