package com.example.ThymeleafProject.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class SkierowanieDoLekarza {
    int iD;
    @NotEmpty
    String lekarz;
    @NotEmpty
    String pacjent;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
    Date termin;

    public SkierowanieDoLekarza(int iD, @NotEmpty String lekarz, @NotEmpty String pacjent, @NotEmpty Date termin) {
        this.iD = iD;
        this.lekarz = lekarz;
        this.pacjent = pacjent;
        this.termin = termin;
    }

    public SkierowanieDoLekarza() {

    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getLekarz() {
        return lekarz;
    }

    public void setLekarz(String lekarz) {
        this.lekarz = lekarz;
    }

    public String getPacjent() {
        return pacjent;
    }

    public void setPacjent(String pacjent) {
        this.pacjent = pacjent;
    }

    public Date getTermin() {
        return termin;
    }

    public void setTermin(Date termin) {
        this.termin = termin;
    }
}
