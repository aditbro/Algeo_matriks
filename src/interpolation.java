
public class interpolation {
	Double[] ItpEq;
	public Matrix MItpEq;
	Solver solve;
	
	public Double power(Double X, int Deg) {
		Double temp = 1.0;
		for(int i = 1; i <= Deg; i++) {
			temp = temp * X;
		}
		return temp;
	}
	
	public void makeEq(Matrix M, int Deg) {
		solve = new Solver();
		MItpEq = new Matrix(Deg+1, Deg+2);
		ItpEq = new Double[Deg+2];
		if (M.getNCol() == 2) {
			for (int i = 0; i <= M.getNRow(); i++) {
				for (int j = 0; j <= Deg+2; j++) {
					if (j != Deg+2) {
						MItpEq.set(i, j, power(M.get(i, 0),j));
					}
					else {
						MItpEq.set(i, j, M.get(i, 1));
					}
				}
			}
			solve.SolveLinear(MItpEq);
			ItpEq = solve.singleSolution(MItpEq);
			System.out.print("P(x) = ");
			for (int i = 0; i <= Deg+1; i++) {
				if (i == 0) {
					System.out.print(ItpEq[i] + " ");
				}
				else if ((i == 1) && (ItpEq[i] > 0)) {
					System.out.print("+ " + ItpEq[i] + "x ");
				}
				else if ((i == 1) && (ItpEq[i] < 0)) {
					System.out.print("- " + (-ItpEq[i]) + "x ");
				}
				else if ((i != Deg+1) && (ItpEq[i] > 0)) {
					System.out.print("+ " + ItpEq[i] + "x^" + i + " ");
				}
				else if ((i != Deg+1) && (ItpEq[i] < 0)) {
					System.out.print("- " + (-ItpEq[i]) + "x^" + i + " ");
				}
				else if ((i == Deg+1) && (ItpEq[i] > 0)) {
					System.out.print("+ " + ItpEq[i] + "x^" + i + " ");
				}
				else if ((i == Deg+1) && (ItpEq[i] < 0)) {
					System.out.print("- " + (-ItpEq[i]) + "x^" + i);
				} 
			}
		}
		else {
			System.out.println("The loaded Matrix size is not for interpolation");
		}
	}
	
	public Double SolITP(Double X) {
		Double hasil = 0.0;
		for (int i = 0; i < ItpEq.length; i++) {
			hasil = hasil + ItpEq[i]*power(X,i);
		}
		return hasil;
	}
}
