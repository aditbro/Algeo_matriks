
//package spl_solve;
import java.util.*;
import java.io.*;
public class Matrix {
	private int kol;
	private int brs;
	private Double[][] M;
	private char[] x;
	private String[] y;
	private Scanner sc;

	//konstruktor kelas matrix
	public Matrix(int baris, int kolom){
		this.kol = kolom;
		this.brs = baris;
		this.M = new Double[100][100];
		for(int i = 0; i < 100; ++i){
			for(int j = 0; j < 100; ++j){
				this.M[i][j] = 0.0;
			}
		}
		this.x = new char[kolom];
		for(int i = 0; i < kolom - 1; ++i){
			this.x[i] = (char)(i + 'a');
		}
		this.x[baris - 1] = '=';
		this.y = new String[baris];
		for(int i = 0; i < baris; ++i){
			this.y[i] = (i+1)+"";
		}
	}

	//fungsi get untuk memasukan nama variabel ke persamaan
	public char getVar(int j){
		return(this.x[j]);
	}
	
	//fungsi setter untuk memasukan nama variabe atas
	public void setVar(int j, char c){
		this.x[j] = c;
	}
	
	//fungsi untuk mendapatkan panjang kolom
	public int getNCol(){
		return this.kol;
	}

	//fungsi untuk mendapatkan panjang baris
	public int getNRow(){
		return this.brs;
	}

	//fungsi untuk set panjang kolom
	public void setNCol(int n){
		this.kol = n;
	}

	//fungsi untuk set panjang baris
	public void setNRow(int n){
		this.brs = n;
	}

	//fungsi getRow untuk mendapatkan row suatu Matrix
	public Double[] getRow(int i){
		Double[] D = new Double[this.kol];
		for(int j = 0; j < this.kol; ++j){
			D[j] = this.M[i][j];
		}
		return D;
	}

	//fungsi setRow untuk memberikan nilai row suatu Matrix
	public void setRow(int i, Double[] D){
		for(int j = 0; j < this.kol; ++j){
			this.M[i][j] = D[j];
		}
	}

	//fungsi getColumn untuk mendapatkan column suatu Matrix
	public Double[] getColumn(int j){
		Double[] D = new Double[this.brs];
		for(int i = 0; i < this.brs; ++i){
			D[i] = this.M[i][j];
		}
		return D;
	}

	//fungsi setColumn untuk memberikan nilai column suatu Matrix
	public void setColumn(int j, Double[] D){
		for(int i = 0; i < this.brs; ++i){
			this.M[i][j] = D[i];
		}
	}

	//fungsi setter untuk memasukan nilai elemen ke Matrix
	public void set(int i, int j, Double val){
		this.M[i][j] = val;
	}

	//fungsi getter untuk mendapatkan nilai elemen Matrix
	public Double get(int i, int j){
		return this.M[i][j];
	}

	//fungsi SwapRow untuk menukar nilai dua buah Row
	public void SwapRow(int i1, int i2){
		Double[] D = this.getRow(i1);
		this.setRow(i1, this.getRow(i2));
		this.setRow(i2, D);
	}

	//fungsi SwapColumn untuk menukar nilai dua buah Column
	public void SwapColumn(int j1, int j2){
		Double[] D = this.getColumn(j1);
		this.setColumn(j1, this.getColumn(j2));
		this.setColumn(j2, D);
	}

	//fungsi menerima input dari keyboard
	public void ParseKey(){
		sc = new Scanner(System.in);
		int brs, kol;
		System.out.print("masukan jumlah baris : ");brs = sc.nextInt();
		System.out.print("masukan jumlah kolom : ");kol = sc.nextInt();
		this.M = new Double[100][100];
		for(int i = 0; i < 100; ++i){
			for(int j = 0; j < 100; ++j){
				this.M[i][j] = 0.0;
			}
		}
		this.brs = brs;
		this.kol = kol;
		Double temp;
		for(int i = 0; i < brs; i++){
			for(int j = 0; j < kol; j++){
				temp = sc.nextDouble();
				this.M[i][j] = temp;
			}
		}
	}

	//fungsi menerima input dari file
	public void ParseFile(String filename){
		this.M = new Double[100][100];
		for(int i = 0; i < 100; ++i){
			for(int j = 0; j < 100; ++j){
				this.M[i][j] = 0.0;
			}
		}
		try{
			StringToMatrix(readFile(filename));
		}catch(Exception e){
			System.out.println(e);
		}
	}

	//fungsi untuk menampilkan matrix ke layar
	public void ShowMatrix(){
		for(int i = 0; i < this.brs; i++) {
			for (int j = 0; j < this.kol;j++) {
	            System.out.printf("%.3f",(Double)this.M[i][j]);
	            System.out.print("\t");
	        }
	        System.out.println();
        }
	}

	//fungsi menulis Matrix ke file
	public void WriteFile(String filename){
		try{
			File files = new File(filename);
			files.createNewFile();
			PrintWriter out = new PrintWriter(filename);
			out.println(MatrixToString());
			out.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}

	//Membuat Matrix Hilbert
	public void MakeHilbert(int n){
		this.M = new Double[100][100];
		this.kol = n+1;
		this.brs = n;
		for(int i = 0; i < n; ++i){
			for(int j = 0; j < n; ++j){
				this.M[i][j] =  1.00/(i + j + 1);
			}
			this.M[i][n] = 1.00;
		}
	}
	
	//Menyelesaikan soal Matrix Hilbert
	public void BuildHilbert(){
		Solver solve = new Solver();
		solve.SolveLinear(this);
		for(int i = 0; i < this.getNRow(); ++i){
			this.M[i][0] = this.M[i][this.getNCol()-1];
		}
		this.setNCol(1);
		this.ShowMatrix();
	}
	
	//Fungsi mereturn bentuk String dari Matrix
	//String akan berbentuk seperti Matrix
	public String MatrixToString(){
		String s = "+";
		boolean written = false;
		for(int j = 0; j < this.kol; ++j){
			s += '\t';
			if(j != 0 && (j%((this.kol)/2)) == 0 && !written){
				s += '|';
				s += '\t';
				written = true;
			}
			if(j == this.kol - 1){
				s += '=';
			}else{
				s += this.x[j%((this.kol)/2)];
			}
		}
		s += '\r';
		s += '\n';
		for(int i = 0; i < this.brs; i++){
			for(int j = 0; j-1 < this.kol; j++){
				if(j == 0){
					s += this.y[i];
				}else{
					s += '\t';
					if(j != 0 && (j%((this.kol + 1)/2)) == 0){
						s += '|';
						s += '\t';
					}
					s += get(i, j-1);
				}
			}
			s += '\r';
			s += '\n';
		}
		return s;
	}
	
	/*** Di bawah ini adalah list helper function yang tidak bisa diakses di luar kelas ***/
	/*------------------------------------------------------------------------------------*/

	//helper function untuk membaca dari file ke String
	private String readFile(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			return sb.toString();
		} finally {
			br.close();
		}
	}

	//helper function membaca String dan mengisi Matrix berdasarkan String
	//String yang valid adalah hasil return dari fungsi readFile
	private void StringToMatrix(String s){
		String[] once = s.split("\n");
		String[][] temp = new String[once.length][once[0].split(" ").length];
		for(int i = 0; i < once.length; ++i){
			temp[i] = once[i].split(" ");
		}
		for(int j = 1; j < once[0].split(" ").length; ++j){
			this.x[j-1] = temp[0][j].charAt(0);
		}
		for(int i = 1; i < once.length; ++i){
			this.y[i-1] = temp[i][0].charAt(0)+"";
		}
		for(int i = 1; i < once.length; ++i){
			for(int j = 1; j < once[0].split(" ").length; ++j){
				this.M[i-1][j-1] = Double.parseDouble(temp[i][j]);
			}
		}
		this.brs = once.length - 1;
		this.kol = once[0].split(" ").length - 1;
	}

	
	//helper function pembulatan bilangan Double
	private Double pembulatan(Double D){
		D *= 100;
		long n = D.intValue();
		D = n/100.0;
		return D;

	}
}
