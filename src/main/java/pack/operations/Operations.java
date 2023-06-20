package pack.operations;
import java.lang.Math;

public class Operations {
    public float sum(float num1, float num2)
    {
        return num1 + num2;
    }

    public float subtraction(float num1, float num2)
    {
        return num1 - num2;
    }

    public float multiplie(float num1, float num2)
    {
        return num1 * num2;
    }

    public float divide(float num1, float num2)
    {
        return num1 / num2;
    }

    public float pow(float num1)
    {
        return num1 * num1;
    }

    public float squareRoot(float num1)
    {
        return (float)Math.sqrt(num1);
    }

    public float powCustom(float num1, float num2)
    {
        return (float) Math.pow(num1, num2);
    }

    public float customRoot(float num1, float num2)
    {
        return (float)Math.pow(num1, 1/num2);
    }

}
