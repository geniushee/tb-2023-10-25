package com.ll;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    String cmd;
    String action;
    String queryString;
    Map<String, String> paramsMap;

    public Rq(String cmd) {
        paramsMap = new HashMap<>();
        this.cmd = cmd;

        String[] cmdBits = this.cmd.split("\\?", 2);
        action = cmdBits[0];
        if (cmdBits.length >= 2) {
            queryString = cmdBits[1];
            String[] queryStringBits = queryString.split("&");
            for (String Bits : queryStringBits) {
                String[] paramBits = Bits.split("=", 2);

                String paramName = paramBits[0];
                String paramValue = paramBits[1];

                paramsMap.put(paramName, paramValue);
            }
        }
    }

    public String getAction(){
        return action;
    }

    public int getParamAsInt(String paramName, int defaultValue){
        String paramvalue = paramsMap.get(paramName);

        if(paramvalue != null){
            try {
                return Integer.parseInt(paramvalue);
            }
            catch(NumberFormatException ignored){
            }
        }
        return defaultValue;
    }
}
