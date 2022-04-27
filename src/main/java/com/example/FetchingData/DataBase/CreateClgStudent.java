package com.example.FetchingData.DataBase;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CreateClgStudent {

    private String nameA;
    private String cityA;
    private String mobileNumberA;
    private String passwordA;
    private String emailIdA;
    private String designationA;

    public String getNameA() {
        return nameA;
    }

    public void setNameA(String nameA) {
        this.nameA = nameA;
    }

    public String getCityA() {
        return cityA;
    }

    public void setCityA(String cityA) {
        this.cityA = cityA;
    }

    public String getMobileNumberA() {
        return mobileNumberA;
    }

    public void setMobileNumberA(String mobileNumberA) {
        this.mobileNumberA = mobileNumberA;
    }

    public String getPasswordA() {
        return passwordA;
    }

    public void setPasswordA(String passwordA) {
        this.passwordA = passwordA;
    }

    public String getEmailIdA() {
        return emailIdA;
    }

    public void setEmailIdA(String emailIdA) {
        this.emailIdA = emailIdA;
    }

    public String getDesignationA() {
        return designationA;
    }

    public void setDesignationA(String designationA) {
        this.designationA = designationA;
    }

    @Override
    public String toString() {
        return "CreateClgStudent{" +
                "nameA='" + nameA + '\'' +
                ", cityA='" + cityA + '\'' +
                ", mobileNumberA='" + mobileNumberA + '\'' +
                ", passwordA='" + passwordA + '\'' +
                ", emailIdA='" + emailIdA + '\'' +
                ", designationA='" + designationA + '\'' +
                '}';
    }
}