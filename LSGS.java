import java.io.*;

// A Java code for solving linear system using Gauss-Seidel Method
// x^{n+1}_{i} = (b_{i} - sum(j>i){A_{ij}*x^{n}_{j}} - sum(j<i){A_{ij}*x^{n+1}_{j}})/A_{ii}

class LSGS{
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
        
        System.out.println("Enter the number of iteration");
        String str = br.readLine();
        int itr = Integer.parseInt(str);
        
        for(int m = 1; m<=itr; m++)
        {
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
        }
        for(int i=0; i<num; i++)
        {
            if(i!=num-1)
                System.out.print("x_" + (i+1) + '=' + x[i] + ", ");
            else
                System.out.print("x_" + (i+1) + '=' + x[i]);
        }
    }
}
