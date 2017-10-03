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
	
	//fungsi setter untuk memasukan nilai ke Matrix
	public void set(int i, int j, Double val){
		this.M[i][j] = val;
	}
	
	//fungsi getter untuk mendapatkan nilai Matrix
	public Double get(int i, int j){
		return this.M[i][j];
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
