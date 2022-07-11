package com.example.cvservice.dto;

public class ResponseFlask {
    CvMatch CV1;
    CvMatch CV2;
    CvMatch CV3;

    public CvMatch getCV1() {
        return CV1;
    }

    public void setCV1(CvMatch CV1) {
        this.CV1 = CV1;
    }

    public CvMatch getCV2() {
        return CV2;
    }

    public void setCV2(CvMatch CV2) {
        this.CV2 = CV2;
    }

    public CvMatch getCV3() {
        return CV3;
    }

    public void setCV3(CvMatch CV3) {
        this.CV3 = CV3;
    }
}
