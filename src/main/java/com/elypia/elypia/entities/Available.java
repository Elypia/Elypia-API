package com.elypia.elypia.entities;

public class Available {

    private String type;
    private String parameter;
    private boolean result;

    public Available(String type, String parameter, boolean result) {
        this.type = type;
        this.parameter = parameter;
        this.result = result;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean response) {
        this.result = response;
    }
}
