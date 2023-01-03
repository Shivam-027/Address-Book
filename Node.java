import java.util.*;

class Node{
    String Firstname;
    String Lastname;
    String number;
    String email;
    String Address;
    Node next;
    Node prev;
    Node(){
        Firstname = "";
        Lastname = "";
        number = "";
        email = "";
        Address = "";
        next = null;
        prev = null;
    }
    Node(String Firstname, String Lastname, String number, String email, String Address){
        this.Firstname = Firstname;
        this.Lastname = Lastname;
        this.number = number;
        this.email = email;
        this.Address = Address;
        next = null;
        prev = null;
    }
}

