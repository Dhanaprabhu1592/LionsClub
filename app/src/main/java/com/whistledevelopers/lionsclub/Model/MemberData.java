package com.whistledevelopers.lionsclub.Model;

public class MemberData {


    private String mjf;
    private String clubname;
    private String clubarea;
    private String name;
    private String lastname;
    private String mobileno;


    public MemberData(String clubname, String clubarea, String name, String lastname, String mjf,String mobileno) {
        this.clubname = clubname;
        this.clubarea = clubarea;
        this.name = name;
        this.lastname = lastname;
        this.mobileno=mobileno;

        this.mjf=mjf;

    }

    public String getClubarea() {
        return clubarea;
    }

    public void setClubarea(String clubarea) {
        this.clubarea = clubarea;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getClubname() {
        return clubname;
    }

    public void setClubname(String clubname) {
        this.clubname = clubname;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





    public String getMjf() {
        return mjf;
    }

    public void setMjf(String mjf) {
        this.mjf = mjf;
    }
}
