package models;

import daos.DTO;

public class Cars implements DTO {
    private Integer id;
    private String make;
    private String model;
    private String model_yr;
    private String color;
    private String vin;

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel_yr() {
        return model_yr;
    }

    public void setModel_yr(String model_yr) {
        this.model_yr = model_yr;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }


    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.make + " " + this.model + " " + this.model_yr;
    }
}
