package benchmarks.ModDiff.NEq.LoopMult2;
public class oldV {
	public static int snippet(int a, int b) {
		int c=0;
		if (a==b) {
			for (int i = 1; i <= a; ++i)
				c += b;
		}
		return c;
	}
}


