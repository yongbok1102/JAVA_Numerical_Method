import java.io.*;

// A Java code for solving linear system using Gradient Descent Method
//x_{n+1} = x_{n} - stp*AT(Ax-b)
//stp is determined by
// stp = (x_{n}-x_{n-1})(grad(F)_{n}-grad(F)_{n-1})/(norm(F_{n}-F_{n-1}))^2

class GDSol{
    public static void main(String[] args) throws IOException{
        System.out.println("Enter the number of unknowns");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        int num = Integer.parseInt(str1);
        
        //unknowns
        double[] x; x = new double[num];
        
        //old values
        double[] xold; xold = new double[num];
        
        //old-old values
        double[] xoold; xoold = new double[num];
        
        //initiating x
        for(int i=0; i<num; i++)
        {
            System.out.println("initial x_"+(i+1));
            String str = br.readLine();
            x[i] = Double.parseDouble(str);
        }
        
        double stp;
        
        System.out.println("Enter the maximum iteration number");
        String str3 = br.readLine();
        int itrmax = Integer.parseInt(str3);          
        
        double[][] A; A = new double[num][num];
        double[] b; b = new double[num];
        double[] sumAx; sumAx = new double[num];
        double[] sumAxold; sumAxold = new double[num];
        double[] sumAxoold; sumAxoold = new double[num];        
        double[] grad; grad = new double[num];
        double[] gradold; gradold = new double[num];
        double[] gradoold; gradoold = new double[num];
        double[] difx; difx = new double[num];
        double[] difgrad; difgrad = new double[num];
        int itr = 0;
        for(int i=0; i<num; i++)
        {
            for(int j=0; j<num; j++)
            {
                System.out.println("A_" + i + ','+j);
                String str = br.readLine();
                A[i][j] = Double.parseDouble(str);
            }
        }
        for(int i=0; i<num; i++)
        {
                System.out.println("b_" + i);
                String str = br.readLine();
                b[i] = Double.parseDouble(str);            
        }
        
        System.out.println("Enter the initial step parameter.");
        String str4 = br.readLine();
        stp = Double.parseDouble(str4);   
        double tol = 1e-013;
        
        for(int i=0;i<num;i++)
        {
            sumAx[i] = 0;
            grad[i] = 0;
        }
        for(int i=0;i<num;i++)
        {
            xold[i] = x[i];
        }
        
        for(int i=0; i<num;i++)
        {
            for(int j=0;j<num;j++)
            {
                sumAx[i] += A[i][j]*x[j];
            }
        }
        for(int i=0;i<num;i++)
        {
            for(int j=0;j<num;j++)
            {
                grad[i] +=A[j][i]*(sumAx[j] - b[j]);
            }
        }
        for(int i=0;i<num;i++)
        {
            x[i] = x[i] - stp*grad[i];
        }
        for(int i=0;i<num;i++)
        {
            sumAx[i] = 0;
        }
        for(int i=0; i<num;i++)
        {
            for(int j=0;j<num;j++)
            {
                sumAx[i] += A[i][j]*x[j];
            }
        }
        double res = calRes(sumAx,b,num);
        itr = itr + 1;
        
        while(res>tol){
            itr++;
            for(int i=0; i<num; i++)
            {
                xoold[i] = xold[i];
            }
            
            for(int i=0; i<num; i++)
            {
                xold[i] = x[i];
            }
            
            for(int i=0; i<num; i++){
                sumAx[i] = 0;
                sumAxold[i] = 0;
                sumAxoold[i] = 0;
                grad[i] = 0;
                gradold[i] = 0;
                gradoold[i] = 0;
            }
            
            for(int i=0;i<num;i++)
            {
                for(int j=0;j<num;j++)
                {
                    sumAxold[i] += A[i][j]*xold[j];
                    sumAxoold[i] += A[i][j]*xoold[j];                    
                }
            }
            
            for(int i=0; i<num; i++)
            {
                for(int j=0; j<num; j++)
                {
                    gradold[i] += A[j][i]*(sumAxold[j] - b[j]);
                    gradoold[i] += A[j][i]*(sumAxoold[j] - b[j]);
                }
            }
            
            for(int i=0; i<num; i++)
            {
                difgrad[i] = gradold[i] - gradoold[i];
                difx[i] = xold[i] - xoold[i];
            }
            
            stp = innerProd(difx, difgrad,num)
            * Math.pow(Norm(difgrad,num),-2);
            
            for(int i=0; i<num; i++)
            {
                x[i] = xold[i] - stp*gradold[i];
            }
            
            for(int i=0;i<num;i++)
            {
                sumAx[i] = 0;
            }
            for(int i=0; i<num;i++)
            {
                for(int j=0;j<num;j++)
                {
                    sumAx[i] += A[i][j]*x[j];
                }
            }
            
            res = calRes(sumAx,b,num);
            
            if(itr==itrmax)
                break;
        }
        System.out.print("itr: " + itr + ", ");
        for(int i=0; i<num; i++){
            System.out.print("x_"+(i+1)+" = " + x[i] + ", ");
        }
        System.out.println("resid: " + res);
        
    }
    
    public static double calRes(double Ax[], double b[], int n)
    {
        double res = 0;
        for(int i=0; i<n;i++)
        {
            res+=(Ax[i]-b[i])*(Ax[i]-b[i]);
        }
        double sumB = 0;
        for(int i=0;i<n;i++)
        {
            sumB +=b[i]*b[i];
        }
        return Math.sqrt(res/sumB);
    }
    
    public static double Norm(double A[], int n)
    {
        double sum = 0;
        for(int i=0; i<n; i++)
        {
            sum += A[i]*A[i];
        }
        return Math.sqrt(sum);
    }
    
    public static double innerProd(double x[], double y[], int n)
    {
        double sum = 0;
        for(int i=0; i<n; i++)
        {
            sum += x[i]*y[i];
        }
        return sum;
    }
}
