/*
 * Title: the KAU Vaccination Centers Management System
 * Name: Ahmed Abdullah Qahtan
 * ID: 2046143
 * Course Number: CPCS204
 * Section: CE
 * Date: 2021/10/7
 * Emile: AAHMEDQAHTAN0001@stu.kau.edu.sa  ,   Qahtanqq99@icloud.com
 */
package StudentsDistribution;



public class Practitioner {

    String parctID;
    String Fname;
    String lname;
    int Center;
    String status;
    Practitioner next;

    public Practitioner(String parctID, String Fname, String lname, int Center, String status) {
        this.parctID = parctID;
        this.Fname = Fname;
        this.lname = lname;
        this.Center = Center;
        this.status = status;
    }

    public Practitioner(String parctID, String Fname, String lname, Practitioner next) {
        this.parctID = parctID;
        this.Fname = Fname;
        this.lname = lname;
        this.next = next;
    }

    public Practitioner(String parctID, String Fname, String lname, int Center, String status, Practitioner next) {
        this.parctID = parctID;
        this.Fname = Fname;
        this.lname = lname;
        this.Center = Center;
        this.status = status;
        this.next = next;
    }

    public String getParctID() {
        return parctID;
    }

    public void setParctID(String parctID) {
        this.parctID = parctID;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String Fname) {
        this.Fname = Fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getCenter() {
        return Center;
    }

    public void setCenter(int Center) {
        this.Center = Center;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Practitioner getNext() {
        return next;
    }

    public void setNext(Practitioner next) {
        this.next = next;
    }

}
