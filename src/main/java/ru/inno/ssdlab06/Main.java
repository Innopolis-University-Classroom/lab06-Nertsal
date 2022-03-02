package ru.inno.ssdlab06;

import java.util.Optional;
import java.util.Scanner;

public class Main {

	public static void main(final String[] args) {
		Scanner scanner = new Scanner(System.in);
		ProgramState state = new ProgramState();
		System.out.println("Welcome!");
		while (true) {
			String input = scanner.nextLine();
			Optional<String> reply = state.Interact(input);
			reply.ifPresent(System.out::println);
		}
	}
}
