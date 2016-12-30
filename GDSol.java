import java.io.*;

// A Java code for solving linear system using Gradient Descent Method
//x_{n+1} = x_{n} - stp*AT(Ax-b)

class GDSol{
    public static void main(String[] args) throws IOException{
        System.out.println("Enter the number of unknowns");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        int num = Integer.parseInt(str1);
        
        //unknowns
        double[] x; x = new double[num];
        
        //initiating x
        for(int i=0; i<num; i++)
        {
            System.out.println("initial x_"+(i+1));
            String str = br.readLine();
            x[i] = Double.parseDouble(str);
        }
        System.out.println("Enter the step parameter");
        String str2 = br.readLine();
        double stp = Double.parseDouble(str2);   
        System.out.println("Enter the maximum iteration number");
        String str3 = br.readLine();
        int itrmax = Integer.parseInt(str3);          
        
        double[][] A; A = new double[num][num];
        double[] b; b = new double[num];
        double[] sumAx; sumAx = new double[num];
        double[] grad; grad = new double[num];
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
        
        double tol = 1e-013;
        for(int i=0; i<num;i++)
        {
            for(int j=0;j<num;j++)
            {
                sumAx[i] += A[i][j]*x[j];
            }
        }
        double res = calRes(sumAx,b,num);
        while(res>tol)
        {
            itr++;
            for(int i=0; i<num; i++)
            {
                sumAx[i] = 0;
            }
            for(int i=0; i<num; i++)
            {
                grad[i] = 0;
            }            
            for(int i=0; i<num; i++)
            {
            
                for(int j=0; j<num; j++)
                {
                    sumAx[i] += A[i][j]*x[j];
            
                }
                
                //calculating gradient
                for(int j=0;j<num;j++)
                {
                    grad[i]+=A[j][i]*(sumAx[i]-b[i]);
                }
                //solving linear system
                x[i] = x[i] - stp*grad[i];              
            }
            
            //calculating residuals (sum(|Ax-b|)/sum(|b|))
            res = calRes(sumAx,b,num);
            if(itr==itrmax)
                break;
        }
        System.out.print("iter: "+ itr);
        for(int i=0; i<num; i++)
        {
                System.out.print(", x_" + (i+1) + '=' + x[i]);
        }
        System.out.print(", res: " + res);
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
}
