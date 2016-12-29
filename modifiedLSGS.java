import java.io.*;

// A Java code for solving linear system using Gauss-Seidel Method
// x^{n+1}_{i} = (b_{i} - sum(j>i){A_{ij}*x^{n}_{j}} - sum(j<i){A_{ij}*x^{n+1}_{j}})/A_{ii}

class modifiedLSGS{
    public static void main(String[] args) throws IOException{
        System.out.println("Enter the number of unknowns");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        int num = Integer.parseInt(str1);
        
        //unknowns
        double[] x; x = new double[num];
        for(int i=0; i<num; i++)
        {
            x[i] = 0;
        }
        
        double[][] A; A = new double[num][num];
        double[] b; b = new double[num];
        double[] sumAx; sumAx = new double[num];
        int itr = 0;
        
        int itrmax = 1000000;
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
        
        double tol = 1e-020;
        
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
            //solving linear system
            for(int i=0; i<num; i++)
            {
                sumAx[i] = 0;
            }
            for(int i=0; i<num; i++)
            {
                for(int j=0; j<num; j++)
                {
                    if(j!=i)
                    {
                        sumAx[i] += A[i][j]*x[j];
                    }
                }
                x[i] = (b[i] - sumAx[i])/A[i][i];     
            }
            
            //calculating residuals (sum(|Ax-b|)/sum(|b|))
            for(int i=0; i<num; i++)
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
                            
            if(itr==itrmax){
                System.out.println("Failed to converge");
                break;
            }
            
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
