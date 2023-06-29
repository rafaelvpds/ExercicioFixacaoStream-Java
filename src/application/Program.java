package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import entities.Employee;



public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter full file path: ");
		String path = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			List<Employee> list = new ArrayList<>();
			String line = br.readLine();

			while (line != null) {

				String[] fields = line.split(",");
				list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));

				line = br.readLine();
			}

			System.out.println("Enter salary: ");
			double salary = sc.nextDouble();

			Comparator<String> comp = (e1, e2) -> e1.toUpperCase().compareTo(e2.toUpperCase());
			List<String> lisEmail = list.stream().filter(e -> e.getSalary() > salary).map(e -> e.getEmail())
					.sorted(comp).collect(Collectors.toList());

			System.out.println("Email of people whose salary is more than: " + String.format("%.2f", salary));
			lisEmail.forEach(System.out::println);

			double sum = list.stream().filter(e -> e.getName().charAt(0) == 'M').map(e -> e.getSalary()).reduce(0.00,
					(x, y) -> x + y);
			
			System.out.println();
			System.out.println("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", sum));
			/*
			 * for (Employee em : list) { System.out.println(em.getName() + ", " +
			 * em.getEmail() + ", " + em.getSalary()); }
			 */

		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("Erro " + e.getMessage());
		}

		sc.close();
	}

}
