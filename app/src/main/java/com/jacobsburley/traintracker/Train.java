package com.jacobsburley.traintracker;

import android.util.Log;

import java.util.Date;

/**
 * Created by jacobburley on 11/04/2018.
 */

public class Train {

    private final static String ICM = "ICMm",
                        VIRM = "VIRM",
                        VIRMM = "VIRMm",
                        DDZ = "DDZ",
                        SLT = "SLT",
                        SGMM = "SGMm",
                        DDAR = "DD-AR",
                        ICE3M = "ICE 3M",
                        FLIRT = "FLIRT 3",
                        GTW = "GTW",
                        PROTOS = "Protos",
                        PBKA = "Thalys PBKA",   //there are two types of Thalys, NS only operates one type but maybe we should include both?
                        PBK = "Thalys PBK";

    private int number;
    private int[] invalidVIRMs =   {8609,
                                    8611, 8612, 8613, 8616, 8617, 8618, 8619,
                                    8620, 8622, 8623, 8625, 8626, 8627, 8629,
                                    8630, 8631, 8634,
                                    8643,
                                    8650,
                                    8668, 8669,
                                    8673,
                                    8704, 8706, 8708,
                                    8710, 8712, 8714, 8716, 8718,
                                    8720, 8722, 8724, 8725,
                                    8744,
                                    9408,
                                    9410, 9414, 9415,
                                    9421, 9424, 9428, 9429,
                                    9432, 9433, 9435, 9436, 9437, 9438, 9439,
                                    9440, 9441, 9442, 9444, 9445, 9446, 9447, 9448, 9449,
                                    9451, 9452, 9453, 9454, 9455, 9456, 9457, 9458, 9459,
                                    9460, 9461, 9462, 9463, 9464, 9465, 9466, 9467,
                                    9470, 9471, 9472, 9474, 9475, 9476,
                                    9500, 9501, 9502, 9503, 9505, 9507, 9509,
                                    9511, 9513, 9515, 9517, 9519,
                                    9521, 9523, 9526, 9527, 9528, 9529,
                                    9530, 9531, 9532, 9533, 9534, 9535, 9536, 9537, 9538, 9539,
                                    9540, 9541, 9542, 9543, 9545, 9546};
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
        }else if((number >= 8608 && number <= 8676) || (number >= 9504 && number <= 9597) || (number >= 8701 && number <= 8746) || (number >= 9401 && number < 9481)){
            /* VIRMs have set numbers 94xx and 86xx for 4 and 6 car first generation sets
             * second generation has 95xx and 87xx for 4 and 6 car sets
             * third generation are all 4 car, 9547-9597
             * refurbished units are 94xx (with the exception of 8637)
             * http://www.treinenweb.nl/materieelnummers/VIRM
             */
            if(isValidVirm(number)){
                if((number >= 9400 && number <= 9481) || number == 8637){
                    /*
                       Refurbished VIRMm
                       It is possible that there are trainsets in this range that aren't refurbished
                       TODO: check what those cases are
                     */
                    return VIRMM;
                }
                return VIRM;
            }
            throw new NoSuchTrainException("Invalid VIRM set selected");

        }else if(true){
            //DDZ
        }else if(true){
            //DD-AR
        }else if(true){
            //ICE 3M
        }else if(true){
            //flirt
        }else if(true){
            //gtw
        }else if(true){
            //protos
        }else if(true){
            //pbka
        }else if(true){
            //pbk
        }
        throw new NoSuchTrainException("The number listed isn't valid");
    }

    private Boolean isValidVirm(int number){
        for(int i = 0; i < invalidVIRMs.length; i++){
            if(number == invalidVIRMs[i]){
                return false;
            }
        }
        return true;
    }


}
