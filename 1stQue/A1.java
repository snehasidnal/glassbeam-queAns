import java.util.*;
class A1{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        if(n%3==0){
            System.out.println("AE");
        }
        else if(n%3==1){
            System.out.println("EC");
        }
        else if(n%3==2){
            System.out.println(" CA");
        }
    }
}