
/**Write a primitive address book program. Give it the following functions:
 * Add a name and phone number entry, Delete an entry. 
 * List the directory in alphabetical order by last name, first name. 
 * Lookup an entry by last name, phone number
 * DOD:
 * show that you can insert and delete entries from your data structures.
 * show that you can traverse your data structures. Depending on your chosen representation, you might 
 * need to copy references into another data structure and sort it to get different orderings.
 * show that you can search your data structures. You might choose a more complex data structure 
 * (such as double-indexing) so that you can search by name or phone number with equal efficiency, 
 * or you can decide that one will be fast (constant or log time) and the other slow (linear time).
 * 
 *                                       DS - mini project
 *                                       Team members:
 *                                       Shivam Saini 21csu092
 *                                       Ujjwal 21csu106
 *                                       Shashwat 21csu088
 *                                       Varain 21csu143
 * **/

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String args[]) {
        int j = 0;
        Scanner obj = new Scanner(System.in);
        Addressbook2 ll = new Addressbook2();
        try {
            File fi = new File("info.txt");
            Scanner content = new Scanner(fi);
            int flag = 0;
            while (content.hasNextLine()) {
                String data = content.nextLine();
                String[] parts = data.split(" ");
                ll.insert(parts[0], parts[1], parts[2], parts[3], parts[4]);
            }
            content.close();
        } catch (Exception e) {
            System.out.println("Error.");
            e.printStackTrace();
        }
        System.out.println("Book Updated");

        if (j == 0) {
            opening();
            j++;
        }

        int a = 10;
        while (a != 0) {
            System.out.println("\nChoose One:\n0. Exit\n1. View Book\n2. Insert\n3. Delete\n4. Update\n5. Search by First Name\n6. Search by Last Name and Number");
            System.out.print("Enter your Choice: ");
            a = obj.nextInt();
            if (a == 1) {
                ll.viewbook();
            } else if (a == 2) {
                System.out.print("\nEnter\nFirst Name: ");
                String f = convertString(obj.next());
                System.out.print("Last Name: ");
                String l = convertString(obj.next());
                System.out.print("Number: ");
                String n = obj.next();
                while (n.length() != 10) {
                    System.out.println("Enter a valid number: ");
                    n = obj.next();
                }
                System.out.print("Email: ");
                String e = convertString(obj.next());
                System.out.print("Address: ");
                String d = convertString(obj.next());
                ll.exist(f, l, n, e, d);
            } else if (a == 3) {
                System.out.print("\nEnter\nNumber: ");
                String n = obj.next();
                ll.delete(n);
            } else if (a == 4) {
                ll.update();
            } else if (a == 5) {
                System.out.print("\nEnter\nFirst Name: ");
                String f = convertString(obj.next());
                ll.search(f);
            } else if (a == 6) {
                System.out.print("\nEnter\nLast Name: ");
                String l = convertString(obj.next());
                System.out.print("Number: ");
                String n = obj.next();
                ll.searchD(l, n);
            }
        }
    }

    static String convertString(String s) {
        int ctr = 0;
        int n = s.length();
        char ch[] = s.toCharArray();
        int c = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0)
                ch[i] = Character.toUpperCase(ch[i]);
            if (ch[i] == ' ') {
                ctr++;
                ch[i + 1] = Character.toUpperCase(ch[i + 1]);
                continue;
            } else
                ch[c++] = ch[i];
        }
        return String.valueOf(ch, 0, n - ctr);
    }

    static void opening(){
        System.out.println("\n");
        System.out.println("\t\t\t     ****************************************");
        System.out.println("\t\t\t     *                                      *");
        System.out.println("\t\t\t     *                                      *");
        System.out.println("\t\t\t     *     ----------------------------     *");
        System.out.println("\t\t\t     *        WELCOME TO ADDRESS BOOK       *");
        System.out.println("\t\t\t     *     ----------------------------     *");
        System.out.println("\t\t\t     *                                      *");
        System.out.println("\t\t\t     *                                      *");
        System.out.println("\t\t\t     ****************************************");
        System.out.println();
    }
}
