package com.jacobsburley.traintracker;

import android.util.Log;

import java.util.Date;

/**
 * Created by jacobburley on 11/04/2018.
 */

public class Train {

    private final static String ICM = "ICM",
                        VIRM = "VIRMm",
                        DDZ = "DDZ",
                        SLT = "SLT",
                        SGMM = "SGMm";

    private int number;
    private String  type,
            date;

    Train(int number, String date){
        this.date = date;
        this.number = number;
        try{
            type = getTypeByNumber(number);
        }catch(NoSuchTrainException e){
            Log.e("Error", e.toString());
        }

    }

    private String getTypeByNumber(int number) throws NoSuchTrainException{
        if(number > 4000 && number <= 4250){
            //ICM
            if(number < 4011){
                //this is a prototype ICM! not valid
                throw new NoSuchTrainException("This is a prototype ICM!");
            }
            return ICM;
        }else if((number > 2110 && number < 2146) || (number > 2935 && number < 2995)){
            //SGMm
            return SGMM;
        }else if((number > 2400 && number < 2470) || (number > 2662 && number < 2600)){
            //SLT
            return SLT;
        }
        throw new NoSuchTrainException("The number listed isnt valid");
    }

}
