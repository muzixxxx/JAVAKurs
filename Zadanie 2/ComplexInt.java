public class ComplexInt
{

    private int re;
    private int im;
    static int fraction;

    public ComplexInt()
    {
        this.re = 0;

    }
    public ComplexInt(int value) {
        fraction = value;
        re = 0;
        im = 1;
    }
    public ComplexInt(int fractionInteger, int numerator, int denominator) {
        // UWAGA, ten kod nie zabezpiecza nas przed mianownikiem równym zero!

        int gcd = gcd(numerator, denominator);

        // skracanie ułamka
        numerator = numerator / gcd;
        denominator = denominator / gcd;
        if (numerator > denominator){

            fractionInteger= numerator/denominator;
            numerator -= (numerator*fractionInteger);
        }
        if(numerator==denominator)
        {
            numerator=0;
            denominator=0;
            fractionInteger=1;

        }


        this.fraction = fractionInteger;
        this.re = numerator;
        this.im = denominator;
    }
    public ComplexInt(int re, int im)
    {
        this.im = im;
        this.re = re;

    }

    /* settery i gettery */

    public void setRe(int re)
    {
        this.re = re;
    }

    public int getRe()
    {
        return this.re;
    }

    public void setIm(int im)
    {
        this.im = im;
    }

    public int getIm()
    {
        return this.im;
    }

    /* metody publiczne */

    public double module()
    {
        return Math.sqrt( re*re + im*im );
    }

    public void add( ComplexInt complex )
    {
        int a=this.im;

        this.im = complex.getIm()*this.im;
        this.re = (complex.getRe()*a) + (this.re*complex.getIm());
        int b= gcd(this.re,this.im);
        this.im = this.im/b;
        this.re= this.re/b;


    }

    public void subtract( ComplexInt complex )
    {
        this.re = this.re - complex.getRe();
        this.im = this.im - complex.getIm();
    }

    public void multiply( ComplexInt complex )
    {
        int im = this.im;
        int re = this.re;

        this.re = re*complex.re - im*complex.im;
        this.im = im*complex.re + re*complex.im;
    }

    public void divide( ComplexInt complex )
    {
        int re = this.re;
        int im = this.im;

        int mianownik = complex.re*complex.re + complex.im*complex.im;

        this.re = (re*complex.re + im*complex.im) / mianownik ; // wynikiem będzie całość z dzielenia!!!
        this.im = (this.im = im*complex.re - re*complex.im) / mianownik; //wynikiem będzie całość z dzielenia!!!
    }
    public static int gcd(int a, int b) {
        if (a < 0) a = -a; // zmieniamy na wartości dodatnie
        if (b < 0) b = -b; //

        while (a != b)
            if (a > b) a -= b;
            else b -= a;

        return a;

    }

    @Override
    public String toString()
    {

        if ( this.im >= 0 )
        {
            return this.re + " / " + this.im ;
        }
        else
        {
            return this.re + " - " + Math.abs(this.im) + "i";
        }
    }
}