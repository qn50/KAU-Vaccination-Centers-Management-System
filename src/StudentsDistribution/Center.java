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


public class Center {

    private String centerName;
    private int centerID;
    private Practitioner head;
    private int capacity;

    public Center(String centerName) {
        this.centerName = centerName;

    }

    public Center(int centerID, int capacity) {
        this.centerID = centerID;
        this.capacity = capacity;

    }

    public Practitioner getHead() {
        return head;
    }

    public void setHead(Practitioner head) {
        this.head = head;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public int getCenterID() {
        return centerID;
    }

    public void setCenterID(int centerID) {
        this.centerID = centerID;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void addPractitioner(String parctID, String Fname, String lname, int Center, String status) {
        Practitioner practitioner = new Practitioner(parctID, Fname, lname, Center, status);
        practitioner.setNext(null);

        if (head == null) {
            head = practitioner;
        } else {
            Practitioner help_Ptr = head;
            while (help_Ptr.getNext() != null) {
                help_Ptr = help_Ptr.getNext();

            }
            help_Ptr.setNext(practitioner);

        }
    }

    public Practitioner searchByID(String id) {
        Practitioner p;
        Practitioner help_Ptr = head;
        while (help_Ptr != null) {
            if (help_Ptr.getParctID().equals(id)) {
                p = help_Ptr;
                return p;

            }
            help_Ptr = help_Ptr.getNext();
        }
        return null;

    }

    public Practitioner searchByStatus(String status) {
        Practitioner p;
        Practitioner help_Ptr = head;
        while (help_Ptr != null) {
            if (help_Ptr.getStatus().equals(status)) {
                p = help_Ptr;
                return p;

            }
            help_Ptr = help_Ptr.getNext();
        }
        return null;

    }

    public void deletePractitionersBasedOnStatus(String status) {
        if (head.getParctID().equals(status)) {
            head = head.getNext();
        } else {
            Practitioner help_Ptr = head;
            while (help_Ptr.getNext() != null) {
                if (help_Ptr.getNext().getStatus().equals(status)) {
                    if (help_Ptr.getNext().getNext() != null) {
                        help_Ptr.setNext(help_Ptr.getNext().getNext());
                    } else {
                        help_Ptr.setNext(null);
                    }
                    break;
                }
                help_Ptr = help_Ptr.getNext();
            }
        }
    }

    public void deletePractitionerById(String id) {

        if (head.getParctID().equals(id)) {
            head = head.getNext();
        } else {
            Practitioner help_Ptr = head;
            while (help_Ptr.getNext() != null) {
                if (help_Ptr.getNext().getParctID().equals(id)) {
                    help_Ptr.setNext(help_Ptr.getNext().getNext());
                    break;
                }
                help_Ptr = help_Ptr.getNext();
            }
        }
    }
}
