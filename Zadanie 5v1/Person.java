package AWT;


import java.util.*;

public class Person {

    public String forename="forename";
    public String surname="surname";

    public String phone="phone";

    public String mail="mail";
    public List<Person> listaKontaktow = new ArrayList<>();


    /**
     * Konstruktor domyślny
     */
    public Person() {

    }

    /**
     * Konstruktor z czterema parametrami
     */


    public Person(String forename, String surname, String phone,String mail) {
        this.forename = forename;
        this.surname = surname;
        this.phone = phone;
        this.mail = mail;
    }

    @Override

    public String toString() {
        return "Person\n" +
                "forename=" + forename + '\n' +
                "surname=" + surname + '\n' +
                "phone=" + phone + '\n' +
                "mail=" + mail + '\n' ;
    }
}