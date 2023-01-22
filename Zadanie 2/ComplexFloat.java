public class ComplexFloat implements ComplexInterface<Float>
{
    private Float re;
    private Float im;

    @Override
    public Float getReal()
    {
        return re;
    }

    @Override
    public void setReal(Float re)
    {
        this.re = re;
    }

    @Override
    public Float getImaginary()
    {
        return this.im;
    }

    @Override
    public void setImaginary(Float im)
    {
        this.im = im;
    }

    public ComplexFloat()
    {
        this.im = 0.0f;
        this.re = 0.0f;
    }

    public ComplexFloat(Float re, Float im)
    {
        this.im = im;
        this.re = re;
    }

    public void add(ComplexInterface complex)
    {
        this.re += ((Number)complex.getReal()).floatValue();
        this.im += ((Number)complex.getImaginary()).floatValue();
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