public class ComplexInteger implements ComplexInterface<Integer>
{
    private Integer re;
    private Integer im;

    @Override
    public Integer getReal()
    {
        return re;
    }

    @Override
    public void setReal(Integer re)
    {
        this.re = re;
    }

    @Override
    public Integer getImaginary() {
        return null;
    }

    @Override
    public void setImaginary(Integer im)
    {
        this.im = im;
    }

    public ComplexInteger()
    {
        this.im = 0;
        this.re = 0;
    }

    public ComplexInteger(Integer re, Integer im)
    {
        this.im = im;
        this.re = re;
    }

    public void add(ComplexInterface complex)
    {
        this.re += ((Number)complex.getReal()).intValue();
        this.im += ((Number)complex.getImaginary()).intValue();
    }

    public String toString()
    {
        return this.re + " + " + this.im ;
    }

    public String printTypes()
    {
        return "real: " + this.re.getClass().getName() + ", imaginary: " + this.im.getClass().getName();
    }

}