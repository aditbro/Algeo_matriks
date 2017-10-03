import java.util.Scanner;
public class Matrix {
	private int kol;
	private int brs;
	private Double[][] M;
	
	//konstruktor kelas matrix
	public Matrix(int kolom, int baris){
		this.kol = kolom;
		this.brs = baris;
		this.M = new Double[baris][kolom];
	}
	
	//fungsi untuk mendapatkan panjang kolom
	public int getColumnLength(){
		return this.kol;
	}
	
	//fungsi untuk mendapatkan panjang baris
	public int getRowLength(){
		return this.brs;
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
		for(int j = 0; j < this.brs; ++j){
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
		for(int i = 0; i < this.kol; ++j){
			this.M[i][j] = D[i];
		}
	}
	
	//fungsi setter untuk memasukan nilai ke Matrix
	public void set(int i, int j, Double val){
		this.M[i][j] = val;
	}
	
	//fungsi getter untuk mendapatkan nilai Matrix
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
		Scanner sc = new Scanner(System.in);
		int brs, kol;
		System.out.print("masukan jumlah baris : ");brs = sc.nextInt();
		System.out.print("masukan jumlah kolom : ");kol = sc.nextInt();
		this.M = new Double[brs][kol];
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
	
	//fungsi untuk menampilkan matrix ke layar
	public void ShowMatrix(){
		for(int i = 0; i < this.brs; ++i){
			for(int j = 0; j < this.kol; ++j){
				System.out.print(this.M[i][j]+ " ");
			}
			System.out.println();
		}
	}
}
