package com.codetutor.vitalsapp.util;

import android.util.Log;

import com.codetutor.vitalsapp.data.IRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Utils {

    private static final String TAG = Utils.class.getSimpleName();

    private static float convertBloodPressureToFloat(String value){
        String modifiedString = value.replace(":",".");
        return Float.parseFloat(modifiedString);
    }

    public static List<Float> getDataInFloats(List<String> data, String type){
        List<Float> values = new ArrayList<>();
        if(type.equals(IRepository.TYPE_BLOOD_PRESSURE)){
            for(String value: data){
                values.add(convertBloodPressureToFloat(value));
            }
        }else{
            for(String value: data){
                values.add(Float.parseFloat(value));
            }
        }
        return  values;
    }

    public static Date getStringDateInUtils(String dateInString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
        Date date = null;
        try{
            date = simpleDateFormat.parse(dateInString);
        }catch (ParseException e){
            Log.i(TAG,e.getMessage());
        }
        return date;
    }
}
