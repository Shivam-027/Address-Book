
import java.util.*;
import java.io.*;
import java.text.*;

class Addressbook {
    int size;
    Node head;
    Node tail;

    Addressbook() {
        size = 0;
        head = null;
        tail = null;
    }

    void viewbook() {
        Node t;
        int count = 1;
        if (head == null) {
            System.out.println("book is empty");
        } else {
            System.out.println("\nADDRESS BOOK: ");
            System.out.printf("%-6s%-20s %-20s %-20s %-20s %-20s", "S.No.", "Last Name", "First Name", "Number","Email", "Address");
            System.out.println();
            t = head;
            while (t != null) {
                System.out.printf("%-6s%-20s %-20s %-20s %-20s %-20s", count, t.Lastname, t.Firstname, t.number,
                        t.email, t.Address);
                System.out.println();
                count++;
                t = t.next;
            }
        }
    }

    void exist(String firstname, String lastname, String number, String email, String address) {
        Node t;
        t = head;
        int flag = 0;
        if (head == null) {
            return;
        } else {
            while (t != null) {
                String l = t.Lastname;
                String num = t.number;
                if (l.equals(lastname) && num.equals(number)) {
                    System.out.println("Person exists in the book");
                    System.out.println();
                    flag++;
                }
                t = t.next;
            }
            if (flag == 0) {
                insert(firstname, lastname, number, email, address);
            }
        }
    }

    void insert(String Firstname, String Lastname, String number, String email, String Address) {
        Node t;
        t = head;
        int count = 1;
        if (head == null) {
            insertAtPos(Firstname, Lastname, number, email, Address, count);
        } else {
            while (t != null) {
                String a = t.Lastname;
                String b = Lastname;

                int compare = a.compareTo(b);
                if (compare > 0 || compare == 0) {
                    insertAtPos(Firstname, Lastname, number, email, Address, count);
                    return;
                } else if (compare < 0) {
                    count++;
                    t = t.next;
                }
            }
            insertAtPos(Firstname, Lastname, number, email, Address, size + 1);
        }
        System.out.println("Person is Inserted in the book");
    }

    void insertAtPos(String Firstname, String Lastname, String number, String email, String Address, int pos) {
        Node n = new Node(Firstname, Lastname, number, email, Address);
        Node t;
        Node temp;
        t = head;
        if (pos == 1) {
            if (head == null) {
                tail = n;
                head = n;
            } else {
                t.prev = n;
            }
            n.next = t;
            head = n;
            size++;
        } else if (pos == size + 1) {
            if (head == null) {
                head = n;
            } else {
                tail.next = n;
            }
            n.prev = tail;
            tail = n;
            size++;
        } else {
            for (int i = 1; i < pos - 1; i++) {
                t = t.next;
            }
            temp = t.next;
            n.next = t.next;
            t.next = n;
            n.prev = t;
            temp.prev = n;
            size++;
        }
        fileupdate();
    }

    void delete(String number) {
        Node t;
        t = head;
        int count = 1;
        if (head == null) {
            System.out.println("Book is empty");
        } else {
            while (t != null) {
                String ch = t.number;

                if (number.equals(ch)) {
                    deleteAtPos(count);
                    return;
                } else {
                    count++;
                    t = t.next;
                }
            }
            System.out.println("Person does not exist in the Book");
        }
    }

    void deleteAtPos(int pos) {
        if (head == null)
            System.out.println("list is empty");
        else if (pos < 1 || pos > size)
            System.out.println("invalid position");
        else if (pos == 1) {
            if (head == null)
                System.out.println("List is empty");
            else {
                head = head.next;
                head.prev = null;
                size--;
            }
        } else if (pos == size) {
            if (head == null)
                System.out.println("List is empty");
            else if (size == 1) {
                head = null;
                size--;
            } else {
                Node t;
                t = head;
                for (int i = 1; i < size - 1; i++) {
                    t = t.next;
                }
                tail = t;
                t.next = null;
                size--;
            }
        } else {
            Node t, t1;
            t = head;
            for (int i = 1; i < pos - 1; i++) {
                t = t.next;
            }
            t1 = t.next;
            t.next = t1.next;
            t1.next.prev = t;
            size--;
        }
        fileupdate();
    }

    void update() {
        viewbook();
        System.out.println();
        Node t = head;
        Scanner obj = new Scanner(System.in);
        System.out.println("Enter the Phone Number: ");
        String phone = obj.next();
        while (t != null) {
            String data = t.number;
            if (data.equals(phone)) {
                int a = 5;
                while (a != 0) {
                    System.out.println("\nChoose One:\n1. Update Name\n2. Update Number\n3. Update Email\n4. Update Address\nEnter your Choice: ");
                    int ch = obj.nextInt();
                    if (ch == 1) {
                        System.out.println("Enter new First Name");
                        String n = convertString(obj.next());
                        t.Firstname = n;
                        System.out.println("Enter new Last Name");
                        n = convertString(obj.next());
                        t.Lastname = n;
                        a = 0;
                    } else if (ch == 2) {
                        System.out.println("Enter new Number");
                        String n = obj.next();
                        t.number = n;
                        a = 0;
                    } else if (ch == 3) {
                        System.out.println("Enter new Email");
                        String n = obj.next();
                        t.email = n;
                        a = 0;
                    } else if (ch == 4) {
                        System.out.println("Enter new Address");
                        String n = convertString(obj.next());
                        t.Address = n;
                        a = 0;
                    } else {
                        System.out.println("Invalid input");
                    }
                }
            }
            t = t.next;
        }
        fileupdate();
    }

    void search(String firstname) {
        Node t;
        t = head;
        int flag = 0;
        if (head == null) {
            System.out.println("Book is empty");
        } else {
            while (t != null) {
                String ch = t.Firstname;
                if (ch.equals(firstname)) {
                    flag++;
                    if (flag == 1) {
                        System.out.printf("%-6s%-20s %-20s %-20s %-20s %-20s", "S.No.", "Last Name", "First Name","Number", "Email", "Address");
                        System.out.println();
                    }
                    System.out.printf("%-6s%-20s %-20s %-20s %-20s %-20s", flag, t.Lastname, t.Firstname, t.number,t.email, t.Address);
                    System.out.println();
                }
                t = t.next;
            }
            if (flag == 0) {
                System.out.println("Person does not exist in the Book");
            }
        }
    }

    void searchD(String lastname, String n) {
        Node t;
        t = head;
        int flag = 0;
        if (head == null) {
            System.out.println("Book is empty");
        } else {
            while (t != null) {
                String ch = t.Lastname;
                String num = t.number;
                if (ch.equals(lastname) && num.equals(n)) {
                    System.out.println("Person exists in the book");
                    System.out.println();
                    flag++;
                    if (flag == 1) {
                        System.out.printf("%-6s%-20s %-20s %-20s %-20s %-20s", "S.No.", "Last Name", "First Name","Number", "Email", "Address");
                        System.out.println();
                    }
                    System.out.printf("%-6s%-20s %-20s %-20s %-20s %-20s", flag, t.Lastname, t.Firstname, t.number,t.email, t.Address);
                    System.out.println();
                }
                t = t.next;
            }
            if (flag == 0) {
                System.out.println("Person does not exist in the Book");
            }
        }
    }

    String convertString(String s) {
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

    void fileupdate() {
        Node t = head;
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("info.txt"));
            while (t != null) {
                String merge = t.Firstname + " " + t.Lastname + " " + t.number + " " + t.email + " " + t.Address;
                out.write(merge + "\n");
                t = t.next;
            }
            out.close();
        } catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
        System.out.println("Book Updated");
    }
}
