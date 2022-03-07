import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class RSA {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please Enter Prime Number p: ");
        int p = scan.nextInt();
        System.out.println("Please Enter Prime Number q: ");
        int q = scan.nextInt();
        int phi = (p - 1)*(q - 1);
        System.out.println("This is your phi(n): " + phi);
        System.out.println("Please Select an e Relatively Prime to phi(n): ");
        int e = scan.nextInt();

        AtomicInteger x = new AtomicInteger(), y = new AtomicInteger();
        int gcd = extended_gcd(phi, e, x, y);
        if (gcd != 1){
            System.out.println("Invalid Input: Please Try Again!");
            return;
        }
        System.out.println("The GCD is " + gcd);
        System.out.printf("x = %d, y = %d\n", x.get(), y.get());
        if (e < phi){
            System.out.println("d = " + (phi + y.get()));
        }
        else{
            int d = y.get();
            while(d > phi){
                d = d - phi;
            }
            System.out.println("d = " + d);
        }

    }

    // Copy from Online
    // Citation: https://www.techiedelight.com/extended-euclidean-algorithm-implementation/
    public static int extended_gcd(int a, int b, AtomicInteger x, AtomicInteger y)
    {
        if (a == 0)
        {
            x.set(0);
            y.set(1);
            return b;
        }

        AtomicInteger _x = new AtomicInteger(), _y = new AtomicInteger();
        int gcd = extended_gcd(b % a, a, _x, _y);

        x.set(_y.get() - (b/a) * _x.get());
        y.set(_x.get());

        return gcd;
    }
}
