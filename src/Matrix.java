import java.util.*;
import java.io.*;
public class Matrix {
	private int kol;
	private int brs;
	private Double[][] M;
	private char[] x;
	private char[] y;
	
	//konstruktor kelas matrix
	public Matrix(int baris, int kolom){
		this.kol = kolom;
		this.brs = baris;
		this.M = new Double[baris][kolom];
		this.x = new char[baris];
		for(int i = 0; i < baris - 1; ++i){
			this.x[i] = (char)(i + 'a');
		}
		this.x[baris - 1] = '=';
		this.y = new char[kolom];
		for(int i = 0; i < kolom; ++i){
			this.y[i] = (char)(i + '1');
		}
	}
	
	//fungsi get untuk memasukan nama variabel ke persamaan 
	public char getVar(int j){
		return(this.x[j]);
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
	
	//fungsi menerima input dari file
	public void ParseFile(String filename){
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
	            System.out.print(this.M[i][j]);
	            System.out.print("\t");
	        }
	        System.out.println();
        }
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
			this.y[i-1] = temp[i][0].charAt(0);
		}
		for(int i = 1; i < once.length; ++i){
			for(int j = 1; j < once[0].split(" ").length; ++j){
				this.M[i-1][j-1] = Double.parseDouble(temp[i][j]);
			}
		}
		this.brs = once.length - 1;
		this.kol = once[0].split(" ").length - 1;
	}
}
