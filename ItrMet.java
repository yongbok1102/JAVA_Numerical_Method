//Source code for solving nonlinear equation
//using Iterative Substitution method
//The Equation is given below
//x = sqrt(4-x)

class Func
{
    double x;
    double f(double x)
    {
        return Math.sqrt(4-x);
    }
}

public class ItrMet {
    public static void main(String[] args)
    {
    
        Func g = new Func();
        double x = 1;
        
        double res;
        double n;
        double man;
        int itr = 0;
        while(itr<=1000)
        {
            x = g.f(x);
            itr++;
            
            res = Math.abs(x-g.f(x));
            n = Math.floor(Math.log10(res));

            man = Math.log10(res) - n;
            System.out.println("Iteration Number: " + itr + ", x=" + x + ", Residual: " + Math.pow(10,man) + 'e' + n);
            if(Math.log10(res)<=-10)
                break;
        }
    }
