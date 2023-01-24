package zadanieJava.com.zad10;

import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Route("")
public class MainView extends VerticalLayout {
    public MainView(){
        add(new H1("Lista zakupów"));
        var addTo = new Button("Dodaj do listy");
        var delete = new Button("Usuń");
        TextField textField = new TextField();
        var  lista = new TextField();
        List <String> lista1= new ArrayList<String>();
        TextField [] text = new TextField[100];
        AtomicInteger licznik= new AtomicInteger(0);
        Button [] button1 = new Button[100];
        Paragraph [] p1= new Paragraph[100];
        if (lista1.size()>0)
        System.out.println("probuje"+lista1.get(0));

        for(int i=0;i<lista1.size();i++) {
            textField.setValue(lista1.get(i));
            addTo.click();
            System.out.println("probuje"+lista1.get(i));
        }
        add(new HorizontalLayout(textField,addTo));


        addTo.addClickListener(e->{

            p1[licznik.intValue()]=new Paragraph();

            lista1.add(textField.getValue());
            text[licznik.intValue()]=new TextField();
            text[licznik.intValue()].setValue(textField.getValue());
            System.out.println("texttt"+text[0].getValue());
            p1[licznik.intValue()].setText(text[licznik.intValue()].getValue());
            add(p1[licznik.intValue()]);


            button1[licznik.intValue()]=new Button("Usuń");
            add(button1[licznik.intValue()]);


            licznik.getAndIncrement();

            for(int i=0;i< licznik.intValue();i++)
            {
                int count = i;
                button1[i].addClickListener(ex->{

                    System.out.println("countd"+count);
                    p1[count].removeAll();
                    p1[count].remove(text[count]);
                    button1[count].setVisible(false);
                    p1[count].setVisible(false);
                    text[count].setVisible(false);
                    text[count].clear();

                });

            }



        });




    }



}
