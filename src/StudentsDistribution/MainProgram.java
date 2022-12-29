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

import java.util.*;
import java.io.*;

public class MainProgram {

    public static void main(String[] args) throws FileNotFoundException {
        //CREATE FILLES TO READ AND PRINT INTO IT:
        File inInti = new File("intialInformation.txt");
        File inCom = new File("commands.txt");
        File outF = new File("output.txt");

        Scanner inI = new Scanner(inInti);
        Scanner inC = new Scanner(inCom);
        PrintWriter out = new PrintWriter(outF);
        // CHECK IF THE FILLES EXIST :
        if (!inCom.exists() || !inInti.exists()) {
            System.out.println(inCom + " or " + inInti + " doesn't exist");
            System.exit(0);
        }
        //PRINT THE SUBJECT OF THE PROGRAM
        out.println("\n        Welcome to the KAU Vaccination Centers Management System"
                + "\n       ---------------------------------------------------------"
                + "\nThe vaccination centers are:");
        //CREATE ARRAYLIST TO STORES CITIES INTO IT:
        int size = inI.nextInt();
        ArrayList<Center> CentersList = new ArrayList<>(size);
        //CREATE COMMAND TO DO METHODES ACCORDING TO COMMAND:
        String command = inC.next();

        do {
            //READ FROM FILE AND DO METHODES ACCORDING TO COMMAND:
            if (command.equalsIgnoreCase("STARTUP")) {
                STARTUP(inI, out, CentersList, size);

            } else if (command.equalsIgnoreCase("DISPLAY_ALL_CENTERS")) {
                DISPLAY_ALL_CENTERS(out, CentersList);
            } else if (command.equalsIgnoreCase("NUM_PRACTIONERS")) {
                NUM_PRACTIONERS(out, CentersList, inC);

            } else if (command.equalsIgnoreCase("DISPLAY_ALL_BASED_ON_STATUS")) {
                String stat = inC.next();
                DISPLAY_ALL_BASED_ON_STATUS(out, CentersList, stat);
            } else if (command.equalsIgnoreCase("DISPLAY_BASED_ON_STATUS")) {
                String statu = inC.next();
                int numOfCenter = inC.nextInt();
                DISPLAY_BASED_ON_STATUS(out, CentersList, statu, numOfCenter);
            } else if (command.equalsIgnoreCase("LEAVE_THE_JOB")) {
                String id = inC.next();
                LEAVE_THE_JOB(out, CentersList, id);
            } else if (command.equalsIgnoreCase("MOVE")) {
                String id = inC.next();
                int numOfCenter = inC.nextInt();
                MOVE(out, CentersList, id, numOfCenter);
            } else if (command.equalsIgnoreCase("DISPLAY")) {
                int numOfCenter = inC.nextInt();
                DISPLAY(out, CentersList, numOfCenter);
            } else if (command.equalsIgnoreCase("REMOVE_ALL_LEFT_PRACTITIONERS")) {
                REMOVE_ALL_LEFT_PRACTITIONERS(out, CentersList);
            } else if (command.equalsIgnoreCase("DELETE_CENTER")) {
                int numOfCenter = inC.nextInt();
                DELETE_CENTER(out, CentersList, numOfCenter);
            } else if (command.equalsIgnoreCase("MERGE")) {
                MERGE(out, CentersList);
            }

            command = inC.next();

        } while (!command.equalsIgnoreCase("QUIT"));
        //PRINT THE CONCULSION OF THE PROGRAM:
        out.println("			-------------------------------------  ");
        out.println("	   	       |	     Good Bye                 |");
        out.println("                        -------------------------------------                         ");
        out.println("");
        out.flush();
        out.close();

    }

    //THIS METHODE TO CREATE CENTERS AND ADD PRACTITIONERS IN CENTERS:
    public static void STARTUP(Scanner inI, PrintWriter out, ArrayList<Center> CentersList, int size) {

        for (int i = 0; i < size; i++) {
            //CREATE ALL CENTERS YOU NEED:
            Center C = new Center(i, inI.nextInt());
            CentersList.add(C);
        }
        //SET NAMES OF CENTERS :
        for (int i = 0; i < size; i++) {
            CentersList.get(i).setCenterName(inI.next().replace('_', ' '));
        }

        //ADD PRACTITIONERS INTO THECENTERS:
        for (int i = 0; i < CentersList.size(); i++) {
            for (int j = 0; j < CentersList.get(i).getCapacity(); j++) {
                if (inI.hasNext()) {
                    CentersList.get(i).addPractitioner(inI.next(), inI.next(), inI.next(), i, "Exist");
                }
            }
        }
        //PRINT VACCINATION CENTERS:
        for (int i = 0; i < CentersList.size(); i++) {
            out.println("   " + CentersList.get(i).getCenterName());
        }
        out.println();

    }

    //THIS METHOD TO DISPLAY ALL PRACTITIONERS OF CENTERS:
    public static void DISPLAY_ALL_CENTERS(PrintWriter out, ArrayList<Center> CentersList) {
        out.println("The first distribution for health practitioners among the vaccination centers ");
        out.println("===================================================================================================\n");
        out.println("       " + CentersList.get(0).getCenterName() + "		    " + CentersList.get(1).getCenterName() + "	     " + CentersList.get(2).getCenterName());
        out.println("\n--------------------------------------------------------------------------------------------------");
        //PRINTING ALL PRACTITIONERS FIRST ONE OF ALL CENTER THEN THE SECOND ONE AND SO:
        for (int i = 0; i < CentersList.get(0).getCapacity(); i++) {
            for (int j = 0; j < CentersList.size(); j++) {
                Practitioner help_Ptr = CentersList.get(j).getHead();
                int count = 0;
                while (count != i) {
                    if (help_Ptr != null) {
                        help_Ptr = help_Ptr.getNext();
                    }
                    count++;
                }
                if (help_Ptr != null) {
                    out.printf("%4s %s %s , %-6s \t", help_Ptr.getParctID(), help_Ptr.getFname(), help_Ptr.getLname(), help_Ptr.getStatus());

                } else {
                    out.print(" ");
                }

            }
            out.println();
        }
        out.println("===================================================================================================");
        out.println();
    }

    //METHODE TO PRINT CAPACITY OF THE CENTER:  
    public static void NUM_PRACTIONERS(PrintWriter out, ArrayList<Center> CentersList, Scanner inC) {
        int n = inC.nextInt();
        out.println("Number of practitioners in center "+n +": " + CentersList.get(n - 1).getCapacity());
        out.println("===================================================================================================");
        out.println();
        

    }
    //METHODE TO PRINT ALL PRACTITIONERS OF SPECIFIC STATUE :
    public static void DISPLAY_ALL_BASED_ON_STATUS(PrintWriter out, ArrayList<Center> CentersList, String stat) {
        boolean StatExs = false;
        // CHECK IF THERE IS ANY PRACTITIONERS WITH THE STATUE I WANT:
        for (int i = 0; i < CentersList.get(0).getCapacity(); i++) {
            for (int j = 0; j < CentersList.size(); j++) {
                Practitioner help_Ptr = CentersList.get(j).getHead();
                int count = 0;
                while (count != i) {
                    if (help_Ptr != null) {
                        help_Ptr = help_Ptr.getNext();
                    }
                    count++;
                }
                if (help_Ptr != null) {
                    if (help_Ptr.getStatus().equals(stat)) {
                        StatExs = true;
                    }
                } else {
                    out.print(" ");
                }

            }

        }
        //IF THERE IS NO PRACTITIONER FOUNED PRINT THE RESULET:
        if (!StatExs) {
            out.println();
            out.println("Not found any practitioners of the status " + stat);
            out.println("===================================================================================================");
        } else {
            // PRINT ALL PRACTITIONERS OF THE STATUS:
            out.println("\n        The practitioners of status " + stat + " are\n        -------------------------------------\n");
            out.println("       " + CentersList.get(0).getCenterName() + "		    " + CentersList.get(1).getCenterName() + "	     " + CentersList.get(2).getCenterName());
            out.println();
            out.println("--------------------------------------------------------------------------------------------------");
            for (int i = 0; i < CentersList.get(0).getCapacity(); i++) {
                for (int j = 0; j < CentersList.size(); j++) {
                    Practitioner help_Ptr = CentersList.get(j).getHead();
                    int count = 0;
                    while (count != i) {
                        if (help_Ptr != null) {
                            help_Ptr = help_Ptr.getNext();
                        }
                        count++;
                    }
                    if (help_Ptr != null && help_Ptr.getStatus().equals(stat)) {
                        out.printf("%4s %s %s , %-6s \t", help_Ptr.getParctID(), help_Ptr.getFname(), help_Ptr.getLname(), help_Ptr.getStatus());

                    } else {
                        out.print(" ");
                    }

                }
                out.println();
            }
            out.println("===================================================================================================");
            out.println();
        }

    }
    //METHOD TO DISPLAY PROCTITIONERS BASED ON STATUS:
    public static void DISPLAY_BASED_ON_STATUS(PrintWriter out, ArrayList<Center> CentersList, String statu, int numOfCenter) {
        boolean StatExs = false;
        //CHECK IF THERE IS ANY PRACTITIONERS WITH THE STATUE I WANT:
        Practitioner help_Ptr = CentersList.get(numOfCenter - 1).getHead();
        while (help_Ptr != null) {
            if (help_Ptr.getStatus().equals(statu)) {
                StatExs = true;
            }
            help_Ptr = help_Ptr.getNext();
        }
        ////IF THERE IS NO PRACTITIONER FOUNED PRINT THE RESULET:
        if (!StatExs) {
            out.println("Not found any practitioners of the status " + statu + " in center " + numOfCenter);
            out.println("===================================================================================================");
            out.println();
        } // // PRINT ALL PRACTITIONERS OF THE STATUS:
        else {
            out.println("	The practitioners of status " + statu + " in center " + numOfCenter + " are"
                    + "\n        -------------------------------------------------"
                    + "\n\n      			 " + CentersList.get(numOfCenter - 1).getCenterName()
                    + "\n\n-----------------------------------------------------");
            help_Ptr = CentersList.get(numOfCenter - 1).getHead();
            while (help_Ptr != null) {
                out.printf("		%4s %s %s , %-6s \t", help_Ptr.getParctID(), help_Ptr.getFname(), help_Ptr.getLname(), help_Ptr.getStatus());
                out.println();
                help_Ptr = help_Ptr.getNext();
            }
            out.println("=======================================================");
        }

    }
    //METHODE TO CHANGE STATUS OF PRACTITIONERS:
    public static void LEAVE_THE_JOB(PrintWriter out, ArrayList<Center> CentersList, String id) {
        int centerChange = 10;
        for (int i = 0; i < CentersList.size(); i++) {
            Practitioner help_Ptr = CentersList.get(i).getHead();
            while (help_Ptr != null) {
                if ((help_Ptr.getParctID().equals(id))) {
                    help_Ptr.setStatus("Left");
                    centerChange = i;
                }
                help_Ptr = help_Ptr.getNext();
            }
        }
        //PRINT AFTER CHANGE 
        out.println("\nThe practitioner of id " + id + " is left");
        out.println("\n	The practitioners of center " + (centerChange + 1) + " are");
        out.println("        -------------------------------------------------");
        out.println("\n      			 " + CentersList.get(centerChange).getCenterName());
        out.println("\n-----------------------------------------------------");
        Practitioner help_Ptr = CentersList.get(centerChange).getHead();
        while (help_Ptr != null) {
            out.printf("		%4s %s %s , %-6s \t", help_Ptr.getParctID(), help_Ptr.getFname(), help_Ptr.getLname(), help_Ptr.getStatus());
            out.println();
            help_Ptr = help_Ptr.getNext();
        }
        out.println("=======================================================");
    }
     //THE METHODE WILL SEARCH FOR THIS PRACTITIONER IN AA LINKED AND TRANSFER HIM TO ANOTHER LIST THIN CHANGE HIS STATU:
    public static void MOVE(PrintWriter out, ArrayList<Center> CentersList, String id, int numOfCenter) {
        int whichCenter = 10;
        Practitioner p = null;
        for (int i = 0; i < CentersList.size(); i++) {
            p = CentersList.get(i).searchByID(id);
            if (p != null) {
                whichCenter = i;
                break;
            }
        }
        CentersList.get(whichCenter).deletePractitionerById(id);
        CentersList.get(numOfCenter - 1).addPractitioner(p.getParctID(), p.getFname(), p.getLname(), numOfCenter - 1, "Moved");
        out.println("\n        The Practitioner of id " + id + " is moved to center " + numOfCenter);
        out.println("	------------------------------------------------");
        out.println("       " + CentersList.get(0).getCenterName() + "		    " + CentersList.get(1).getCenterName() + "	     " + CentersList.get(2).getCenterName());
        out.println("\n--------------------------------------------------------------------------------------------------");

        for (int i = 0; i < CentersList.get(0).getCapacity(); i++) {
            for (int j = 0; j < CentersList.size(); j++) {
                Practitioner help_Ptr = CentersList.get(j).getHead();
                int count = 0;
                while (count != i) {
                    if (help_Ptr != null) {
                        help_Ptr = help_Ptr.getNext();
                    }
                    count++;
                }
                if (help_Ptr != null) {
                    out.printf("%4s %s %s , %-6s \t", help_Ptr.getParctID(), help_Ptr.getFname(), help_Ptr.getLname(), help_Ptr.getStatus());

                } else {
                    out.print(" ");
                }

            }
            out.println();
        }
        out.println("===================================================================================================");

    }
    //PRINT SPECIFIC CENTER:
    public static void DISPLAY(PrintWriter out, ArrayList<Center> CentersList, int numOfCenter) {
        out.println("\n        The practitioners of center " + numOfCenter + " are");
        out.println("        -------------------------------------------------");
        out.println("\n     			 " + CentersList.get(numOfCenter - 1).getCenterName());
        out.println("\n-----------------------------------------------------");

        Practitioner help_Ptr = CentersList.get(numOfCenter - 1).getHead();
        while (help_Ptr != null) {
            out.printf("		%4s %s %s , %-6s \t", help_Ptr.getParctID(), help_Ptr.getFname(), help_Ptr.getLname(), help_Ptr.getStatus());
            out.println();
            help_Ptr = help_Ptr.getNext();
        }
        out.println("=======================================================");
        out.println();
    }
    //METHODE TO REMOVE ALL LEFT PRACTITONERS THIN ADD THEM TO NEW CENTER:
    public static void REMOVE_ALL_LEFT_PRACTITIONERS(PrintWriter out, ArrayList<Center> CentersList) {
        out.println("	All left Practitioners are moved to new linked list\n	---------------------------------------------------");
        Practitioner p;
        Center c = new Center("LeftList");
        CentersList.add(c);
        int i = 0;
        Practitioner help = CentersList.get(i).getHead();
        while (help != null && i < CentersList.size() - 1) {
            p = CentersList.get(i).searchByStatus("Left");
            CentersList.get(i).deletePractitionersBasedOnStatus("Left");
            if (p != null) {
                c.addPractitioner(p.getParctID(), p.getFname(), p.getLname(), CentersList.size() - 1, "Left");
                continue;
            }

            i++;

        }
        Practitioner help_Ptr = CentersList.get(CentersList.size() - 1).getHead();
        while (help_Ptr != null) {
            out.printf("   %4s %s %s , %-6s \t", help_Ptr.getParctID(), help_Ptr.getFname(), help_Ptr.getLname(), help_Ptr.getStatus());
            out.println();
            help_Ptr = help_Ptr.getNext();
        }
        out.println("\n        The remaining practitioners After remove the practitioners of status left");
        out.println("	------------------------------------------------");
        out.println("       " + CentersList.get(0).getCenterName() + "		    " + CentersList.get(1).getCenterName() + "	     " + CentersList.get(2).getCenterName());
        out.println("\n--------------------------------------------------------------------------------------------------");

        for (int k = 0; k < CentersList.get(0).getCapacity(); k++) {
            for (int j = 0; j < CentersList.size() - 1; j++) {
                Practitioner help_Ptr1 = CentersList.get(j).getHead();
                int count = 0;
                while (count != k) {
                    if (help_Ptr1 != null) {
                        help_Ptr1 = help_Ptr1.getNext();
                    }
                    count++;
                }
                if (help_Ptr1 != null) {
                    out.printf("%4s %s %s , %-6s \t", help_Ptr1.getParctID(), help_Ptr1.getFname(), help_Ptr1.getLname(), help_Ptr1.getStatus());

                } else {
                    out.print(" ");
                }

            }
            out.println();
        }
        out.println("===================================================================================================");
        CentersList.remove(CentersList.size() - 1);
    }
    //METHODE TO DELETE SPECIFIC CENTER:
    public static void DELETE_CENTER(PrintWriter out, ArrayList<Center> CentersList, int numOfCenter) {
        CentersList.remove(numOfCenter - 1);
        out.println("\n 			Center " + numOfCenter + " is Closed");
        out.println("===================================================================================================");
    }
    //METHODE TO CONNECT ALL LINKED LIST WITH TOGETHER:
    public static void MERGE(PrintWriter out, ArrayList<Center> CentersList) {
        out.println("\n			The remaing centers ar merged");
        out.println("\n--------------------------------------------------------------------------------------------------");
        Practitioner help_Ptr = CentersList.get(0).getHead();

        for (int i = 0; i < CentersList.size(); i++) {
            if (i + 1 < CentersList.size()) {
                Practitioner nextLL = CentersList.get(i + 1).getHead();
                while (help_Ptr.getNext() != null) {
                    help_Ptr = help_Ptr.getNext();
                }
                help_Ptr.setNext(nextLL);
            }
        }
        //PRINT AFTER MERGEING:
        Practitioner help_Ptr1 = CentersList.get(0).getHead();
        while (help_Ptr1 != null) {
            out.printf("%4s %s %s , %-6s \t", help_Ptr1.getParctID(), help_Ptr1.getFname(), help_Ptr1.getLname(), help_Ptr1.getStatus());
            out.println();
            help_Ptr1 = help_Ptr1.getNext();
        }
        out.println("===================================================================================================");
        out.println();
    }

}
