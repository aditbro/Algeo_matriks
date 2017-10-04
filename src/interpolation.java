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
	
	public String makeEq(Matrix M, int Deg) {
		String s = "";
		solve = new Solver();
		MItpEq = new Matrix(Deg+1, Deg+2);
		ItpEq = new Double[Deg+1];
		if (M.getNCol() == 2) {
			for (int i = 0; i <= M.getNRow(); i++) {
				for (int j = 0; j <= Deg+2; j++) {
					if (j != Deg+1) {
						MItpEq.set(i, j, power(M.get(i, 0),j));
					}
					else {
						MItpEq.set(i, j, M.get(i, 1));
					}
				}
			}
			//MItpEq.ShowMatrix();
			solve.SolveLinear(MItpEq);
			//MItpEq.ShowMatrix();
			ItpEq = solve.singleSolution(MItpEq);
			/*for(int i = 0; i < ItpEq.length; ++i){
				System.out.printf("%.3f ", ItpEq[i]);
			}*/
			System.out.println();
			System.out.print("P(x) = ");
			s += "P(x) = ";
			for (int i = 0; i < Deg+1; i++) {
				if (i == 0) {
					System.out.printf("%.3f ",ItpEq[i]);
					s+=pembulatan(ItpEq[i]) + " ";
				}
				else if ((i == 1) && (ItpEq[i] > 0)) {
					System.out.printf("+ %.3fx ",ItpEq[i]);
					s+="+ "+pembulatan(ItpEq[i]) + " ";
				}
				else if ((i == 1) && (ItpEq[i] < 0)) {
					System.out.printf("- %.3fx ",(-ItpEq[i]));
					s+="- "+pembulatan(-ItpEq[i]) + "x ";
				}
				else if ((i != Deg+1) && (ItpEq[i] > 0)) {
					System.out.printf("+ %.3fx^%d ",ItpEq[i],i);
					s+="+ "+pembulatan(ItpEq[i]) + "x^" + i + " ";
				}
				else if ((i != Deg+1) && (ItpEq[i] < 0)) {
					System.out.printf("- %.3fx^%d ",-ItpEq[i],i);
					s+="- "+pembulatan(-ItpEq[i]) + "x^" + i + " ";
				}
				else if ((i == Deg+1) && (ItpEq[i] > 0)) {
					System.out.printf("+ %.3fx^%d ",ItpEq[i],i);
					s+="+ "+pembulatan(ItpEq[i]) + "x^" + i + " ";
				}
				else if ((i == Deg+1) && (ItpEq[i] < 0)) {
					System.out.printf("- %.3fx^%d ",-ItpEq[i],i);
					s+="- "+pembulatan(-ItpEq[i]) + "x^" + i + " ";
				} 
			}
			System.out.println();
			s += "\r\n";
		}
		else {
			System.out.println("The loaded Matrix size is not for interpolation");
		}
		return s;
	}
	
	public Double SolITP(Double X) {
		Double hasil = 0.0;
		for (int i = 0; i < ItpEq.length; i++) {
			hasil = hasil + ItpEq[i]*power(X,i);
		}
		return hasil;
	}
	
	public void clear(){
		MItpEq.setNRow(0);
		MItpEq.setNCol(0);
	}
	
	private Double pembulatan(Double D){
		D *= 100;
		int n = D.intValue();
		D = n/100.0;
		return D;
		
	}
}