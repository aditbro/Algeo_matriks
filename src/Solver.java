
//package spl_solve;



public class Solver {



	public void SolveLinear(Matrix M)
	{
		//Merekondisi matriks
		ReconMatrix(M);

		//Swap dkk
		int IMax;
		for(int p = 0; p < (M.getNCol()-1)/2; p++)
		{
			IMax = p;
			for(int i = p; i < M.getNRow(); i++)
			if(M.get(i, p) > M.get(IMax, p))
			{
				IMax = i;
			}

			M.SwapRow(IMax, p); //swap row imax dengan p

			/*
			System.out.println("After Swap");
			M.ShowMatrix();
			System.out.println();
			*/
			
			if(Math.abs(M.get(p, p)) > 0)
			{
				pivot (p,p, M);
			}
		}

	}

	public void ReconMatrix(Matrix M)
	{
		int i, j;
		int NewNRow, NewNCol, OldNCol;

		//Pemindahan = ke 2NKol - 1
		NewNRow = M.getNRow(); //Jumlah Row Baru
		OldNCol = M.getNCol(); //Indeks Kolom Akhir Lama
		NewNCol = M.getNCol()*2 - 1; //Jumlah Kolom Baru

		M.setNRow(NewNRow);
		M.setNCol(NewNCol);

		M.SwapColumn(OldNCol-1,NewNCol-1);


		//Pemasangan matriks identitas

		for(i = 0; i <= M.getNRow(); i++)
		{
			for(j = OldNCol-1 ; j <= M.getNCol()-2; j++)
			{
				if((j - OldNCol+1) == i)
				{
					M.set(i, j, 1.0);
				} else
				{
					M.set(i, j, 0.0);
				}
			}
		}

	}

	public void pivot(int p, int q, Matrix M)
	{
		Double mult;
		//Buat nol untuk semua row kecuali di p q.
		for(int i = 0; i < M.getNRow(); i++)
		{
			mult = M.get(i, q)/M.get(p, q); //multiplier pembuat nol
			for (int j = 0; j < M.getNCol(); j++)
			{
				if(i != p && j != q)
				{
					M.set(i, j, M.get(i, j) - M.get(p, j) * mult);
				}
			}

		}
		//Mengnolkan kolom q, agar tidak terjadi -0.00
		for (int i = 0; i < M.getNRow(); i++)
		{
			if(i!=p)
			{
				M.set(i, q, 0.0);
			}
		}

		//Scale P
		for(int j = 0; j < M.getNCol(); j++)
		{
			if (j != q)
			{
				M.set(p, j, M.get(p, j)/M.get(p, q));
			}
		}
		M.set(p, q, 1.0);
		/*
		System.out.println("After Pivot");
		M.ShowMatrix();
		System.out.println();
		*/

	}

	public boolean isSolutionUnique(Matrix M)
	{
		return (singleSolution(M) != null);
	}

	public Double[] singleSolution(Matrix M) //Kondisi Matriks: telah di gauss jordan
	{
		Double[] x = new Double[M.getNRow()];
		for (int i = 0; i < M.getNRow(); i++)
		{
			if(Math.abs(M.get(i, i)) > 0)
			{
				x[i] = M.get(i, M.getNCol()-1);
			} else if (Math.abs(M.get(i, M.getNCol()-1)) > 0)
			{
				return null; //no solution or many solutions.
			}
		}
		return x;
	}

	public String PrintSolution(Matrix M)
	/*FORMAT MATRIX JADI:
	+ 	var1 	var2 	var3 	var4 	=
	1 	1						4		1
	2			1				3		2
	3					1		2		3
	4
	*/
	{
		String s = "";
		System.out.println(); // enter sekali
		s += "\r\n";
		for(int i = 0; (i < M.getNRow()); i++)
		{
			boolean startPrint = true;
			for(int j = 0; j < (M.getNCol()-1)/2; j++)
			{
				if(Math.abs(M.get(i, j)) > 0)
				{
					if(M.get(i, j) > 0 && !startPrint)
					{
						System.out.format(" + ");
						s += " + ";
					}

					if(M.get(i, j) > 0)
					{
						if(M.get(i, j) == 1.00)
						{
							System.out.format("%c", M.getVar(j));
							s += M.getVar(j);
						}else
						{
							System.out.format("%.2f%c", M.get(i,j),M.getVar(j));
							s += ""+M.get(i,j)+M.getVar(j);
						}
					}
					else if(M.get(i, j) < 0)
					{
						if(!startPrint)
						{
							System.out.format(" ");
							s += " ";
						}
						System.out.format("- %.2f%c", -M.get(i, j), M.getVar(j));
						s += "- "+(-M.get(i, j))+M.getVar(j);
					}

					startPrint = false;

				}
			}

			//Print =
			if(!allColumnZero(i, M))
			{
				System.out.format(" = %.2f\n",M.get(i, M.getNCol()-1));
				s += " = "+M.get(i, M.getNCol()-1)+"\r\n";
			}

		}
		return s;
	}


	public int findOne(int i, Matrix M) //Mencari nilai 1 pertama pada baris i
	{
		int j;
		if(!(allColumnZero(i,M) && solutionZero(i,M)))
		{
			j = 0;
			while((j < ((M.getNCol()-1)/2)) && (M.get(i, j) <= 0))
			{
				if(M.get(i, j) <= 0)
				{
					j++;
				}
			}
			return j;
		} else
		{
			return 0;
		}
		
	}
	
	public void toComplement(boolean[] Idx)
	{
		for(int i = 0; i < Idx.length; i++)
		{
			Idx[i] = !Idx[i];
		}
	}
	
	public String printParam(Matrix M)
	{
		String s = "";
		char[] var = new char [(M.getNCol()-1)/2];
		boolean[] paramIdx = new boolean[(M.getNCol()-1)/2];
	
		for(int i = 0; (i < M.getNRow()); i++)
		{
			boolean startPrint = true;
			for(int j = 0; j < (M.getNCol()-1)/2; j++)
			{
				if(Math.abs(M.get(i, j)) > 0)
				{
					if(M.get(i, j) > 0 && !startPrint)
					{
						System.out.format(" + ");
						s += " + ";
					}

					if(M.get(i, j) > 0)
					{
						if(M.get(i, j) == 1.00)
						{
							System.out.format("%c", M.getVar(j));
							s += M.getVar(j);
						}else
						{
							System.out.format("%.2f%c", M.get(i,j),M.getVar(j));
							s += ""+M.get(i,j)+M.getVar(j);
						}
					}
					else if(M.get(i, j) < 0)
					{
						if(!startPrint)
						{
							System.out.format(" ");
							s += " ";
						}
						System.out.format("- %.2f%c", -M.get(i, j), M.getVar(j));
						s += "- "+(-M.get(i, j))+M.getVar(j);
					}

					startPrint = false;

				}
			}
			
			//Print =
			if(!allColumnZero(i, M))
			{
				System.out.format(" = %.2f\n",M.get(i, M.getNCol()-1));
				s += " = "+M.get(i, M.getNCol()-1)+"\r\n";
			}
			

		}
		
		System.out.format("\nJadi, dalam bentuk parametrik : \n");
		s += "\r\nJadi, dalam bentuk parametrik : \r\n";
		//Parametric Variable substitution
		
		for(int i = 0; i < M.getNRow();i++)
		{
			//recording
			if(!allColumnZero(i,M))
			{
				paramIdx[findOne(i,M)] = true;
			}
			
		}
		//Substitution
		toComplement(paramIdx);
		char InitVar = 'r';
		int N = 0;
		for(int j = 0; j < (M.getNCol()-1)/2; j++)
		{
			if(paramIdx[j])
			{
				var[j] = M.getVar(j);
				M.setVar(j, (char)((int)(InitVar) + N++));
				
				//Print Param Variables
				System.out.format("%c = %c\n", var[j], M.getVar(j));
				s += var[j] + " = " + M.getVar(j) + "\r\n";
			}
		}
		
		for(int i = 0; i < M.getNRow(); i++)
		{
			//print var
			if(!allColumnZero(i,M))
			{
				boolean startPrint = true;
				
				for(int j = 0; j < (M.getNCol()-1)/2; j++)
				{
					//kasus pertama, "x = "
					if(j == findOne(i, M))
					{
						System.out.format("%c =", M.getVar(findOne(i,M)));
						s += M.getVar(findOne(i,M)) +" =";
						startPrint = true;
					} else
					{
						if(M.get(i, j) > 0)
						{
							System.out.format(" - %.2f%c", M.get(i, j), M.getVar(j));
							s += " - " + M.get(i, j)+ M.getVar(j);
						} else if (M.get(i, j) < 0)
						{
							if(!startPrint)
							{
								System.out.format(" + %.2f%c", -M.get(i,j), M.getVar(j));
								s += " + " + -M.get(i, j)+ M.getVar(j);
							} else
							{
								System.out.format(" %.2f%c", -M.get(i,j), M.getVar(j));
								s += " " + -M.get(i, j)+ M.getVar(j);
							}
						}
						startPrint = false;
					}
					
					
				}
				//print =
				if(getSolution(i,M) < 0)
				{
					System.out.format(" - %.2f\n", -getSolution(i,M));
					s += " - " + -getSolution(i,M) + "\r\n";
				} else if (getSolution(i,M) > 0)
				{
					System.out.format(" + %.2f\n", getSolution(i,M));
					s += " + " + getSolution(i,M) + "\r\n";
				}
				
				//end of if
			}
		}

		
		return s;
		
		
	}
	
	public boolean allColumnZero(int i, Matrix M)
	{
		boolean foundNotZero = false;
		int j = 0;
		while (!foundNotZero && j < (M.getNCol() - 1)/2)
		{
			if (M.get(i, j) > 0)
			{
				foundNotZero = true;
			}else
			{
				j++;
			}

		}

		return !foundNotZero;
	}

	public boolean solutionZero(int i, Matrix M) //true jika paling kanan matriks = 0
	{
		return (M.get(i, M.getNCol()-1) == 0);
	}

	public boolean noSolution(Matrix M)
	{
		boolean noSol = false;
		for (int i = 0; ((i < M.getNRow()) && !noSol);i++)
		{
			if (allColumnZero(i, M) && !solutionZero(i, M))
			{
				noSol = true;
			}
		}

		return noSol;
	}
	
	public Double getSolution (int i, Matrix M)
	{
		return (M.get(i, M.getNCol()-1));
	}
}
