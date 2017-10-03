import java.util.Scanner;
public class main {
	public static void main(String[] args) {
		//nanti akan dibuat class Matrix
		Matrix M = new Matrix(100, 100);
		
		boolean loaded = false;
		Scanner sc = new Scanner(System.in);
		String choice;
		System.out.println("Program initialized");
		while(true){//penghentian program akan menggunakan break
			System.out.print(">>>");//bentuk dasar dari console
			choice = "";
			choice = sc.next();
			if(choice.equals("load")){
				//metode input bisa dari keyboard atau dari file
				System.out.print("Chose input source (file or keyboard) :");
				
				choice = sc.next();
				if(choice.equals("file")){
					System.out.print("Input filename : ");
					choice = sc.next();
					
					//akan dibuat method ParseFile seperti di TBFO dari class Matrix
					M.ParseFile(choice);
					M.ShowMatrix();
					loaded = true;
				}else if(choice.equals("keyboard")){
					System.out.println("Type your input :");
					
					//nanti akan dibuat method ParseKey dari class Matrix 
					M.ParseKey();
					loaded = true;
				}else{
					System.out.println("Input method invalid");
				}
			}else if(choice.equals("run")){
				if(loaded){
					System.out.println("Available Method :");
					System.out.println("1.Solve linear algebra");
					System.out.println("2.Interpolate");
					System.out.print("Type the number of the choice : ");
					choice = sc.next();
					while(!choice.equals("1") && !choice.equals("2")){
						System.out.println("Invalid Method!");
						System.out.println("Available Method :");
						System.out.println("1.Solve linear algebra");
						System.out.println("2.Interpolate");
						System.out.print("Type the number of the choice : ");
						choice = sc.next();
					}
					if(choice.equals("1")){
						//Akan dibuat Prosedur SolveLinear yang memanfaatkan metode Gauss dan Gauss Jordan
						//SolveLinear(M);
					}else if(choice.equals("2")){
						//Akan dibuat Prosedur Interpolate
						//Interpolate(M);
					}
				}else{
					System.out.println("No input is currently loaded, try loading the input first");
				}
			}else if(choice.equals("check")){
				if(loaded){
					//Akan dibuat method ShowMatrix yang menampilkan isi Matrix
					M.ShowMatrix();
				}else{
					System.out.println("The input is still empty, try loading the input first");
				}
			}else if(choice.equals("clear")){
				loaded = false;
				System.out.println("Matrix has been cleared");
			}else if(choice.equals("close")){
				break;
			}else{
				System.out.println("List of available commands :");
				System.out.println("    load   load the input to program");
				System.out.println("    run    run the available process");
				System.out.println("    check  check the currently loaded Matrix");
				System.out.println("    clear  clear the currently loaded Matrix");
				System.out.println("    close  close the program");
			}
		}
	}
}
