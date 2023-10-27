package com.ll.domain;

import com.ll.standard.util.Ut;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    private String cmd;
    private String action;
    private String queryString;
    private Map<String, String> paramsMap;

    public Rq(String cmd) {
        paramsMap = new HashMap<>();
        this.cmd = cmd;

        String[] cmdBits = this.cmd.split("\\?", 2);
        action = cmdBits[0];
        if (cmdBits.length < 2) {
            return;
        }

        queryString = cmdBits[1];
        String[] queryStringBits = queryString.split("&");
        for (String Bits : queryStringBits) {
            String[] paramBits = Bits.split("=", 2);

            String paramName = paramBits[0];
            String paramValue = paramBits[1];

            paramsMap.put(paramName, paramValue);
        }

    }

    public String getAction() {
        return action;
    }

    public int getParamAsInt(String paramName, int defaultValue) {
        return Ut.str.parseInt(paramsMap.get(paramName),defaultValue);
    }
}
