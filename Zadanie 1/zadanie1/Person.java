package zadanie1;

import java.util.ArrayList;
import java.util.List;

public class Person {

    public String forename;
    public String surname;

    public String phone;

    public String mail;
    public List<Person> listaKontaktow = new ArrayList<>();


    /**
     * Konstruktor domyślny
     */
    public Person() {

    }

    /**
     * Konstruktor z czterema parametrami
     */


    public Person(String forename, String surname, String phone, String mail) {
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