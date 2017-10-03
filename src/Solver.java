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
			
			if(Math.abs(M.get(p, p)) > 1e-8)
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
		double mult;
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
	
	
}
