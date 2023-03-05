
import static java.lang.Math.*;

public class Complex<T>
{
    private T reNumerator;
    private T reDenominator;

     private T imNumerator;
     private T imDenominator;
    private int refraction;
    private int imfraction;




    public Complex()
    {
        this.reNumerator = (T) Integer.valueOf(0);
        this.reDenominator = (T) Integer.valueOf(0);


        this.imNumerator = (T) Integer.valueOf(0);
        this.imDenominator = (T) Integer.valueOf(0);

    }

    public Complex(T reNumerator, T reDenominator,T imNumerator,T imDenominator)
    {
        this.reNumerator = reNumerator;
        this.reDenominator = reDenominator;
        this.imNumerator = imNumerator;
        this.imDenominator=imDenominator;
    }


    /* settery i gettery */
// rzeczywiste licznik
    public void setReNumerator(T re)
    {
        this.reNumerator = reNumerator;
    }


    public T getReNumerator()
    {
        return this.reNumerator;
    }


    // mianownik
    public void setReDenominator(T im)
    {
        this.reDenominator = reDenominator;
    }

    public T getReDenominator()
    {
        return this.reDenominator;
    }

    // imagine licznik
    public void setImNumerator(T imNumerator)
    {
        this.imNumerator = imNumerator;
    }

    public T getImNumerator()
    {
        return this.imNumerator;
    }
    // minaownik
    public void setImDenominator(T imDenominator)
    {
        this.imDenominator = imDenominator;
    }

    public T getImDenominator()
    {
        return this.imDenominator;
    }


    public void setReFraction(int reFraction){this.refraction=reFraction;}
    public int getReFraction(){return this.refraction=refraction;}
    public void setImFraction(int imFraction){this.imfraction=imFraction;}
    public int getImFraction(){return this.imfraction=imfraction;}
    /* metody publiczne */

    public double module()
    {
        Double reNumer = (Double)this.reNumerator;
        Double reDenomi = (Double)this.reDenominator;

        return Math.sqrt( reNumer*reNumer + reDenomi*reDenomi );

    }
    public void totalToFraction ()
    {
        if (refraction>0)
        {
            if(((Number)this.reNumerator).doubleValue()<0)
                this.reNumerator=(T) (Number)(((refraction*((Number)this.reDenominator).doubleValue())+abs(((Number)this.reNumerator).doubleValue()))*-1);
            else
                this.reNumerator=(T) (Number)(refraction*((Number)this.reDenominator).doubleValue()+(abs(((Number)this.reNumerator).doubleValue())));
            refraction =0;
        }
        if (imfraction>0)
        {
            if(((Number)this.imNumerator).doubleValue()<0)
                this.imNumerator=(T) (Number)(((imfraction*((Number)this.imDenominator).doubleValue())+abs(((Number)this.imNumerator).doubleValue()))*-1);
            else
                this.imNumerator=(T) (Number)(imfraction*((Number)this.imDenominator).doubleValue()+(abs(((Number)this.imNumerator).doubleValue())));
            imfraction =0;
        }
    }
    public void removePoint(Complex complex)
    {
        double a =(((Number) this.reNumerator).doubleValue());
        double b =(((Number) this.reDenominator).doubleValue());
        double c =(((Number) this.imNumerator).doubleValue());
        double d = (((Number) this.imDenominator).doubleValue());


            a=a*100000;
            a=round(a);
            b=b*100000;
            b=round(b);
            c=c*100000;
            c=round(c);
            d=d*100000;
            d=round(d);

            this.reNumerator=(T)(Number)a;
            this.reDenominator=(T)(Number)b;
            this.imNumerator=(T)(Number)c;
            this.imDenominator=(T)(Number)d;



            complex.setReDenominator (round(((Number) complex.getReDenominator()).doubleValue()*100000));
            complex.setReNumerator (round(((Number) complex.getReNumerator()).doubleValue()*100000));

            complex.setImDenominator (round(((Number) complex.getImDenominator()).doubleValue()*100000));
            complex.setImNumerator (round(((Number) complex.getImNumerator()).doubleValue()*100000));

        T e= gcd((T)complex.getReNumerator(), (T) complex.getReDenominator());
        T f= gcd((T)complex.getImNumerator(), (T) complex.getImDenominator());


       complex.setReNumerator((T)((Number)(((Number)complex.getReNumerator()).doubleValue()/((Number)e).doubleValue())));
        complex.setReDenominator((T)((Number)(((Number)complex.getReDenominator()).doubleValue()/((Number)e).doubleValue())));

        complex.setImNumerator((T)((Number)(((Number)complex.getImNumerator()).doubleValue()/((Number)f).doubleValue())));
        complex.setImDenominator((T)((Number)(((Number)complex.getImDenominator()).doubleValue()/((Number)f).doubleValue())));



    }

    public void add( Complex complex )
    {

       totalToFraction();

        removePoint(complex);
        T a=this.reDenominator;


        T k;

        this.reDenominator = (T) (Number)(((Number)complex.getReDenominator()).doubleValue()*((Number)this.reDenominator).doubleValue());

        k = (T)((Number)((((Number)complex.getReNumerator()).doubleValue() *((Number)a).doubleValue())));//6
        this.reNumerator= (T)((Number)(((Number)complex.getReDenominator()).doubleValue() *((Number)this.reNumerator).doubleValue()+((Number)k).doubleValue()));


        a=this.imDenominator;

        this.imDenominator = (T) (Number)(((Number)complex.getImDenominator()).doubleValue()*((Number)this.imDenominator).doubleValue());

        k = (T)((Number)((((Number)complex.getImNumerator()).doubleValue() *((Number)a).doubleValue())));//6
        this.imNumerator= (T)((Number)(((Number)complex.getImDenominator()).doubleValue() *((Number)this.imNumerator).doubleValue()+((Number)k).doubleValue()));


        T b= gcd((T) this.reNumerator, (T) this.reDenominator);


         this.reDenominator= (T)((Number)(((Number)this.reDenominator).doubleValue()/((Number)b).doubleValue()));
        this.reNumerator= (T)((Number)(((Number)this.reNumerator).doubleValue()/((Number)b).doubleValue()));

        refractionCalc(this.reNumerator,this.reDenominator);

        T c = gcd((T) this.imNumerator, (T) this.imDenominator);

        this.imDenominator= (T)((Number)(((Number)this.imDenominator).doubleValue()/((Number)c).doubleValue()));
        this.imNumerator= (T)((Number)(((Number)this.imNumerator).doubleValue()/((Number)c).doubleValue()));


        imfractionCalc(this.imNumerator,this.imDenominator);



    }
    public void subtract( Complex complex )
    {
        totalToFraction();

        removePoint(complex);



        T a=this.reDenominator;//6
        T k;
        this.reDenominator = (T) (Number)(((Number)complex.getReDenominator()).doubleValue()*((Number)this.reDenominator).doubleValue());


        k = (T)((Number)((((Number)complex.getReNumerator()).doubleValue() *((Number)a).doubleValue())));


        this.reNumerator= (T)((Number)((((Number)complex.getReDenominator()).doubleValue() *((Number)this.reNumerator).doubleValue())-((Number)k).doubleValue()));

       // System.out.println(this.re+"Nasze Re Po dzialaniach");
       // System.out.println(this.im+"Nasze IM Po dzialaniach");

        T b= gcd((T) this.reNumerator, (T) this.reDenominator);
        this.reDenominator = (T)((Number)(((Number)this.reDenominator).doubleValue()/((Number)b).doubleValue()));
        this.reNumerator= (T)((Number)(((Number)this.reNumerator).doubleValue()/((Number)b).doubleValue()));
        refractionCalc(this.reNumerator,this.reDenominator);


        a=this.imDenominator;//6

        this.imDenominator = (T) (Number)(((Number)complex.getImDenominator()).doubleValue()*((Number)this.imDenominator).doubleValue());


        k = (T)((Number)((((Number)complex.getImNumerator()).doubleValue() *((Number)a).doubleValue())));


        this.imNumerator= (T)((Number)((((Number)complex.getImDenominator()).doubleValue() *((Number)this.imNumerator).doubleValue())-((Number)k).doubleValue()));

        // System.out.println(this.re+"Nasze Re Po dzialaniach");
        // System.out.println(this.im+"Nasze IM Po dzialaniach");

        b= gcd((T) this.imNumerator, (T) this.imDenominator);
        this.imDenominator = (T)((Number)(((Number)this.imDenominator).doubleValue()/((Number)b).doubleValue()));
        this.imNumerator= (T)((Number)(((Number)this.imNumerator).doubleValue()/((Number)b).doubleValue()));
        imfractionCalc(this.imNumerator,this.imDenominator);
    }
    public void multiply( Complex complex )
    {

        totalToFraction();

        if (refraction>0)
        {
            if(((Number)this.reNumerator).doubleValue()<0)
                this.reNumerator=(T) (Number)(((refraction*((Number)this.reDenominator).doubleValue())+abs(((Number)this.reNumerator).doubleValue()))*-1);
            else
                this.reNumerator=(T) (Number)(refraction*((Number)this.reDenominator).doubleValue()+(abs(((Number)this.reNumerator).doubleValue())));


        }
        if (imfraction>0)
        {
            if(((Number)this.imNumerator).doubleValue()<0)
                this.imNumerator=(T) (Number)(((imfraction*((Number)this.imDenominator).doubleValue())+abs(((Number)this.imNumerator).doubleValue()))*-1);
            else
                this.imNumerator=(T) (Number)(imfraction*((Number)this.imDenominator).doubleValue()+(abs(((Number)this.imNumerator).doubleValue())));
        }
        removePoint(complex);

        //do sprawdzenia
        //reNumerator = this.reNumerator;
         //reDenominator = this.reDenominator;

        this.reNumerator = (T) (Number)(((Number)complex.getReNumerator()).doubleValue() * ((Number)this.reNumerator).doubleValue());
        this.reDenominator = (T) (Number)(((Number)complex.getReDenominator()).doubleValue() * ((Number)this.reDenominator).doubleValue());

        this.imNumerator = (T) (Number)(((Number)complex.getImNumerator()).doubleValue() * ((Number)this.imNumerator).doubleValue());
        this.imDenominator = (T) (Number)(((Number)complex.getImDenominator()).doubleValue() * ((Number)this.imDenominator).doubleValue());

        //sprawdzic jak skracac mianownik licznik
        T b= gcd((T) this.reNumerator, (T) this.reDenominator);
        T c= gcd((T) this.imNumerator, (T) this.imDenominator);

        this.reDenominator = (T)((Number)(((Number)this.reDenominator).doubleValue()/((Number)b).doubleValue()));
        this.reNumerator= (T)((Number)(((Number)this.reNumerator).doubleValue()/((Number)b).doubleValue()));

        this.imDenominator = (T)((Number)(((Number)this.imDenominator).doubleValue()/((Number)c).doubleValue()));
        this.imNumerator= (T)((Number)(((Number)this.imNumerator).doubleValue()/((Number)c).doubleValue()));

        refractionCalc(this.reNumerator,this.reDenominator);
        imfractionCalc(this.imNumerator,this.imDenominator);
    }
    public void divide( Complex complex )
    {
        totalToFraction();

        removePoint(complex);



        this.reNumerator = (T) (Number)(((Number)complex.getReDenominator()).doubleValue() * ((Number)this.reNumerator).doubleValue());
        this.imNumerator = (T) (Number)(((Number)complex.getImDenominator()).doubleValue() * ((Number)this.imNumerator).doubleValue());



        this.reDenominator = (T) (Number)(((Number)complex.getReNumerator()).doubleValue() * ((Number)this.reDenominator).doubleValue());
        this.imDenominator = (T) (Number)(((Number)complex.getImNumerator()).doubleValue() * ((Number)this.imDenominator).doubleValue());

        if(((Number)this.reNumerator).doubleValue()<0 && ((Number)this.reDenominator).doubleValue()<0)
        {
            this.reNumerator=(T)((Number)(((Number)this.reNumerator).doubleValue()*-1));
            this.reDenominator=(T)((Number)(((Number)this.reDenominator).doubleValue()*-1));
        }
        if(((Number)this.reNumerator).doubleValue()>0 && ((Number)this.reDenominator).doubleValue()<0)
        {
            this.reNumerator=(T)((Number)(((Number)this.reNumerator).doubleValue()*-1));
            this.reDenominator=(T)((Number)(((Number)this.reDenominator).doubleValue()*-1));
        }
        if(((Number)this.imNumerator).doubleValue()<0 && ((Number)this.imDenominator).doubleValue()<0)
        {
            this.imNumerator=(T)((Number)(((Number)this.imNumerator).doubleValue()*-1));
            this.imDenominator=(T)((Number)(((Number)this.imDenominator).doubleValue()*-1));
        }
        if(((Number)this.imNumerator).doubleValue()>0 && ((Number)this.imDenominator).doubleValue()<0)
        {
            this.imNumerator=(T)((Number)(((Number)this.imNumerator).doubleValue()*-1));
            this.imDenominator=(T)((Number)(((Number)this.imDenominator).doubleValue()*-1));
        }


        T b= gcd((T) this.reNumerator, (T) this.reDenominator);
        T c= gcd((T) this.imNumerator, (T) this.imDenominator);

        this.reDenominator = (T)((Number)(((Number)this.reDenominator).doubleValue()/((Number)b).doubleValue()));
        this.reNumerator= (T)((Number)(((Number)this.reNumerator).doubleValue()/((Number)b).doubleValue()));
        this.imDenominator = (T)((Number)(((Number)this.imDenominator).doubleValue()/((Number)c).doubleValue()));
        this.imNumerator= (T)((Number)(((Number)this.imNumerator).doubleValue()/((Number)c).doubleValue()));

        refractionCalc(this.reNumerator,this.reDenominator);
        imfractionCalc(this.imNumerator,this.imDenominator);
       // fractionCalc(this.reNumerator,this.reDenominator);
    }


    public  T gcd(T a, T b) {
        if ( ((Number)a).doubleValue() < 0)
            a = (T)(Number)(abs(((Number)a).doubleValue()));// zmieniamy na wartości dodatnie
        if (((Number)b).doubleValue() < 0) b=(T)(Number)(abs(((Number)b).doubleValue()));

        //

        while (((Number)a).doubleValue()  != ((Number)b).doubleValue() ) {
            if (((Number) a).doubleValue() > ((Number) b).doubleValue())
                a = (T) (Number) (((Number) a).doubleValue() - ((Number) b).doubleValue());
            else b = (T) (Number) (((Number) b).doubleValue() - ((Number) a).doubleValue());

        }
        return a;

    }
     public void refractionCalc(T a, T b)
    {
          refraction=0;

        if( abs(((Number)a).intValue())> abs(((Number)b).intValue()))
        {
            while(abs(((Number)a).intValue())> abs(((Number)b).intValue()))
            {
                if (((Number)a).intValue()> ((Number)b).intValue())
                {
                a = (T)(Number)(((Number)a).intValue()-abs(((Number)b).intValue()));
                }
                else {
                    a = (T)(Number)(((Number)a).intValue()+((Number)b).intValue());
                }
                refraction+=1;

            }
        }


        this.reNumerator=a;
    }
    public void imfractionCalc(T a, T b)
    {
        imfraction=0;

        if( abs(((Number)a).intValue())> abs(((Number)b).intValue()))
        {
            while(abs(((Number)a).intValue())> abs(((Number)b).intValue()))
            {
                if (((Number)a).intValue()> ((Number)b).intValue())
                {
                    a = (T)(Number)(((Number)a).intValue()-abs(((Number)b).intValue()));
                }
                else {
                    a = (T)(Number)(((Number)a).intValue()+((Number)b).intValue());
                }
                imfraction+=1;

            }
        }


        this.imNumerator=a;
    }

    @Override
    public String toString()
    {
        String result;
        if(refraction>0) {
            if (((Number) this.reNumerator).doubleValue() < 0)
                result= "     -     " + refraction + " " + ((((Number) this.reNumerator).doubleValue()) * -1) + " / " + this.reDenominator;
            result= refraction + "  " + this.reNumerator + " / " + this.reDenominator;

            }
            else
                result=  this.reNumerator + " / " + this.reDenominator ;

        if(imfraction>0) {
            if (((Number) this.imNumerator).doubleValue() < 0)
                return result + "     +      - " + imfraction + "i   " + ((((Number) this.imNumerator).doubleValue()) * -1) + "i / " + this.reDenominator+"i ";
            return result + "      +      "+ imfraction + "i   " + this.imNumerator + "i / " + this.imDenominator+"i";

        }
        else
            return result +"    "+ this.imNumerator + "i / " + this.imDenominator + "i";

        }






    public String printTypes()
    {
        return "real numerator: " + this.reNumerator.getClass().getName() + ", real denominator: " + this.reDenominator.getClass().getName()+
                " imagin numerator"+ this.imNumerator.getClass().getName()+", imagin denominator "+ this.imDenominator.getClass().getName();
    }

}