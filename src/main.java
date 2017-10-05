
//package spl_solve;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
public class main {
	private static Scanner sc;

	public static void main(String[] args) {
		//nanti akan dibuat class Matrix
		Matrix M = new Matrix(100, 100);
		interpolation inter = new interpolation();
		Solver solve = new Solver();
		boolean loaded = false;
		sc = new Scanner(System.in);
		String s = "";
		String choice;
		System.out.println("Program initialized");
		while(true){//penghentian program akan menggunakan break
			System.out.print(">>> ");//bentuk dasar dari console
			choice = "";
			s = "";
			choice = sc.next();
			if(choice.equals("load")){
				//metode input bisa dari keyboard atau dari file
				System.out.print("Choose input source (file or keyboard) : ");

				choice = sc.next();
				if(choice.equals("file")){
					System.out.print("Input filename : ");
					choice = sc.next();

					//akan dibuat method ParseFile seperti di TBFO dari class Matrix
					try{
						M.ParseFile(choice);
						M.ShowMatrix();
						System.out.println("Loading file finished");
					}catch(Exception e){
						System.out.println(e);
						System.out.println("Loading file failed");
					}
					loaded = true;
				}else if(choice.equals("keyboard")){
					System.out.println("Type your input : ");

					//nanti akan dibuat method ParseKey dari class Matrix
					M.ParseKey();
					M.ShowMatrix();
					loaded = true;
				}else{
					System.out.println("Input method invalid");
				}
			}else if(choice.equals("run")){
				if(loaded){
					System.out.println("Available Method : ");
					System.out.println("1.Solve linear algebra");
					System.out.println("2.Interpolate");
					System.out.println("3.Solve Hilbert Matrix");
					System.out.print("Type the number of the choice : ");
					choice = sc.next();
					while(!choice.equals("1") && !choice.equals("2") && !choice.equals("3")){
						System.out.println("Invalid Method!");
						System.out.println("Available Method :");
						System.out.println("1.Solve linear algebra");
						System.out.println("2.Interpolate");
						System.out.print("Type the number of the choice : ");
						choice = sc.next();
					}
					s += M.MatrixToString();
					if(choice.equals("1")){
						//Akan dibuat Prosedur SolveLinear yang memanfaatkan metode Gauss dan Gauss Jordan
						solve.SolveLinear(M);
						M.ShowMatrix();
						s = M.MatrixToString();
						if(solve.isSolutionUnique(M))
						{
							s += solve.PrintSolution(M);
						} else if(solve.noSolution(M))
						{
							System.out.format("Tidak ada solusi\n");
							s += "Tidak ada solusi\r\n";
						} else
						{
							s += solve.printParam(M);
						}
						//System.out.println(M.MatrixToString());
					}else if(choice.equals("2")){
						//Akan dibuat Prosedur Interpolate
						System.out.print("Insert the interpolation order : ");
						int n = sc.nextInt();
						s += inter.makeEq(M, n);
						choice = "Yes";
						Double O;
						while(choice.charAt(0) == 'Y' || choice.charAt(0) == 'y'){
							System.out.print("Insert the x that you want to interpolate : ");
							O = sc.nextDouble();
							s += "P(" + O +") = " + inter.SolITP(O * 1.00) + "\r\n";
							System.out.printf("%.3f\n",inter.SolITP(O * 1.00));
							System.out.print("Interpolate again? (Y/N)\n");
							choice = sc.next();
						}
					}else if(choice.equals("3")){
						System.out.print("Masukan ukuran Matrix Hilbert : ");
						int n = sc.nextInt();
						M.MakeHilbert(n);
						M.ShowMatrix();
						M.BuildHilbert();
						s = M.MatrixToString();
					}
					System.out.print("Save to File? (Y/N) \n");
					choice = sc.next();
					if(choice.charAt(0) == 'Y' || choice.charAt(0) == 'y'){
						System.out.print("Specify the output file : ");
						choice = sc.next();
						WriteFile(choice, s);
						System.out.println("File written successfully");
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
				M.setNCol(0);
				M.setNRow(0);
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
	public static void WriteFile(String filename, String s){
		try{
			File files = new File(filename);
			files.createNewFile();
			PrintWriter out = new PrintWriter(filename);
			out.println(s);
			out.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
