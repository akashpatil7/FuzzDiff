package benchmarks.Bess.bessy.NEq;
public class newV{
    public static double snippet (double n, double x) {
        double j =0;
        double by = 0;
        double bym= 0;
        double byp= 0;
        double tox= 0;
        if (n < 2 && n > -2)//change
            return -1000;
        tox=2.0*x;//change:delete the next line
        bym=bessy0(x);
        for (j=1;j<n;j++) {
            byp=j*tox*by-bym;
            bym=by;
            by=byp;
        }
        return by+100;//change
    }
    private static double bessy0(double x){
        double z,xx,y,ans,ans1,ans2;

        if (x < 8.0) {
            y=x*x;
            ans1 = -2957821389.0+y*(7062834065.0+y*(-512359803.6
                    +y*(10879881.29+y*(-86327.92757+y*228.4622733))));
            ans2=40076544269.0+y*(745249964.8+y*(7189466.438
                    +y*(47447.26470+y*(226.1030244+y*1.0))));
            ans=(ans1/ans2)+0.636619772*bessj0(x)*Math.log(x);
        } else {
            z=8.0/x;
            y=z*z;
            xx=x-0.785398164;
            ans1=1.0+y*(-0.1098628627e-2+y*(0.2734510407e-4
                    +y*(-0.2073370639e-5+y*0.2093887211e-6)));
            ans2 = -0.1562499995e-1+y*(0.1430488765e-3
                    +y*(-0.6911147651e-5+y*(0.7621095161e-6
                    +y*(-0.934945152e-7))));
            ans=Math.sqrt(0.636619772/x)*(Math.sin(xx)*ans1+z*Math.cos(xx)*ans2);
        }
        return ans;
    }

    private static double bessy1(double x){
        double z,xx,y,ans,ans1,ans2;

        if (x < 8.0) {
            y=x*x;
            ans1=x*(-0.4900604943e13+y*(0.1275274390e13
                    +y*(-0.5153438139e11+y*(0.7349264551e9
                    +y*(-0.4237922726e7+y*0.8511937935e4)))));
            ans2=0.2499580570e14+y*(0.4244419664e12
                    +y*(0.3733650367e10+y*(0.2245904002e8
                    +y*(0.1020426050e6+y*(0.3549632885e3+y)))));
            ans=(ans1/ans2)+0.636619772*(bessj1(x)*Math.log(x)-1.0/x);
        } else {
            z=8.0/x;
            y=z*z;
            xx=x-2.356194491;
            ans1=1.0+y*(0.183105e-2+y*(-0.3516396496e-4
                    +y*(0.2457520174e-5+y*(-0.240337019e-6))));
            ans2=0.04687499995+y*(-0.2002690873e-3
                    +y*(0.8449199096e-5+y*(-0.88228987e-6
                    +y*0.105787412e-6)));
            ans=Math.sqrt(0.636619772/x)*(Math.sin(xx)*ans1+z*Math.cos(xx)*ans2);
        }
        return ans;
    }
    private static double bessj0(double x){
        double ax,z,xx,y,ans,ans1,ans2;

        if ((ax=Math.abs(x)) < 8.0) {
            y=x*x;
            ans1=57568490574.0+y*(-13362590354.0+y*(651619640.7
                    +y*(-11214424.18+y*(77392.33017+y*(-184.9052456)))));
            ans2=57568490411.0+y*(1029532985.0+y*(9494680.718
                    +y*(59272.64853+y*(267.8532712+y*1.0))));
            ans=ans1/ans2;
        } else {
            z=8.0/ax;
            y=z*z;
            xx=ax-0.785398164;
            ans1=1.0+y*(-0.1098628627e-2+y*(0.2734510407e-4
                    +y*(-0.2073370639e-5+y*0.2093887211e-6)));
            ans2 = -0.1562499995e-1+y*(0.1430488765e-3
                    +y*(-0.6911147651e-5+y*(0.7621095161e-6
                    -y*0.934945152e-7)));
            ans=Math.sqrt(0.636619772/ax)*(Math.cos(xx)*ans1-z*Math.sin(xx)*ans2);
        }
        return ans;
    }

    private static double bessj1(double x){
        double  ax,z,xx,y,ans,ans1,ans2;

        if ((ax=Math.abs(x)) < 8.0) {
            y=x*x;
            ans1=x*(72362614232.0+y*(-7895059235.0+y*(242396853.1
                    +y*(-2972611.439+y*(15704.48260+y*(-30.16036606))))));
            ans2=144725228442.0+y*(2300535178.0+y*(18583304.74
                    +y*(99447.43394+y*(376.9991397+y*1.0))));
            ans=ans1/ans2;
        } else {
            z=8.0/ax;
            y=z*z;
            xx=ax-2.356194491;
            ans1=1.0+y*(0.183105e-2+y*(-0.3516396496e-4
                    +y*(0.2457520174e-5+y*(-0.240337019e-6))));
            ans2=0.04687499995+y*(-0.2002690873e-3
                    +y*(0.8449199096e-5+y*(-0.88228987e-6
                    +y*0.105787412e-6)));
            ans=Math.sqrt(0.636619772/ax)*(Math.cos(xx)*ans1-z*Math.sin(xx)*ans2);
            if (x < 0.0) ans = -ans;
        }
        return ans;
    }
}