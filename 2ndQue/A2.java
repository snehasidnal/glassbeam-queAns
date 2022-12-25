import java.util.*;
class A2{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int m=sc.nextInt();
        int product=1;
        int count=0;
        while(m!=0){
            product =product+(m%10);
            m=m/10;
            if(m==0){
                count++;
                if(product/10==0){
                    break;
                }
                m=product;
                product=1;
            }
        }
        System.out.println("The total number of steps to reach the single digit is "+count);
    }
}