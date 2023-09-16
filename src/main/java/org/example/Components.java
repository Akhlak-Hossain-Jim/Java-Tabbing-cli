package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

interface Ann {
	void fun();
}

public class Components {
	
	public static void RankGenerate(ArrayList<Team> Ta, ArrayList<Adjudicator> Aa, String ty) {
		ArrayList<Team> Teams= Ta;
		ArrayList<Adjudicator> Adj = Aa;
		Comparator<Team> comparator = Comparator.comparingInt(Team::getTotalScore);
	   	Collections.sort(Teams, comparator);
	   	Comparator<Team> comparator2 = Comparator.comparingInt(Team::getWins);
	   	Collections.sort(Teams, comparator2);
	   	Comparator<Adjudicator> comparatorA = Comparator.comparingInt(Adjudicator::getTotalScore);
	   	Collections.sort(Adj, comparatorA);
	   	
        switch (ty) {
			case "break": {
				System.out.println();
				System.out.println("\t:.....Generating Break");
				System.out.println();
				System.out.println("\tBreaking Teams:");
				System.out.println();
				System.out.println("\t\t1. Name: "+Teams.get(Teams.size()-1).getName()+"\t\twith: "+Teams.get(Teams.size()-1).getWins()+" wins & "+Teams.get(Teams.size()-1).getTotalScore()+" team score");
				System.out.println("\t\t1. Name: "+Teams.get(Teams.size()-2).getName()+"\t\twith: "+Teams.get(Teams.size()-2).getWins()+" wins & "+Teams.get(Teams.size()-2).getTotalScore()+" team score");
				System.out.println("\t\t1. Name: "+Teams.get(Teams.size()-3).getName()+"\t\twith: "+Teams.get(Teams.size()-3).getWins()+" wins & "+Teams.get(Teams.size()-3).getTotalScore()+" team score");
				System.out.println("\t\t1. Name: "+Teams.get(Teams.size()-4).getName()+"\t\twith: "+Teams.get(Teams.size()-4).getWins()+" wins & "+Teams.get(Teams.size()-4).getTotalScore()+" team score");
				System.out.println();
				Components.delay(()->System.out.println("\t::::::Break Generated"), 700);
				break;
			}
			case "team":{
				System.out.println();
				System.out.println("\tTeam Rank:");
				int i=1;
				for(Team el: Teams) {
					System.out.println("\t\t"+i+". Name: "+el.getName()+"\t\twith: "+el.getWins()+" wins & "+el.getTotalScore()+" team score");
					i++;
				}
				System.out.println();
				break;
			}
			case "adj":{
				System.out.println();
				System.out.println("\tAdjudicator Rank:");
				int i=1;
				for(Adjudicator el: Adj) {
					System.out.println("\t\t"+i+". Name: "+el.getName()+"\t\twith: "+el.getTotalScore()+" points.");
					i++;
				}
				System.out.println();
				break;
			}
			default:
				break;
        }
	}

	public static ArrayList<Team> TopGenerate(ArrayList<Team> Ta, int n) {
		ArrayList<Team> Teams= Ta;
		Comparator<Team> comparator = Comparator.comparingInt(Team::getTotalScore);
	   	Collections.sort(Teams, comparator);
	   	Comparator<Team> comparator2 = Comparator.comparingInt(Team::getWins);
	   	Collections.sort(Teams, comparator2);
	   	
	   	ArrayList<Team> rTeams=new ArrayList<Team>();
	   	
	   	for(int i=1;i<=n;i++) {
	   		rTeams.add(Teams.get(Teams.size()-i));
	   	}
	   	return rTeams;
	}

	public static void ResultOptions(Round cr, int ra) {
		ArrayList<MatchUp> Matchups = cr.getMatchUps();
		System.out.println("\tChoose an option from below:");
		System.out.println();
		if (ra < Matchups.size()) {
			System.out.print("\t\t1. Add result for ");
			Matchups.get(ra).display();
		} else {
			System.out.println("\t*****All results have been added.");
		}
		System.out.println();
		System.out.println("\t\t9. Go back to previous menu.");
		System.out.println("\t\t0. Go back to main menu.");
		System.out.println();
		System.out.print("\tSelect an option: ");
	}

	public static void DisplayOptions(int cr) {
		System.out.println("\tChoose an option from below:");
		System.out.println();
		System.out.println("\t\t1. Display Draw for " + RoundNoDisplay(cr));
		System.out.println("\t\t2. Display Motion");
		System.out.println();
		System.out.println("\t\t9. Go back to previous menu.");
		System.out.println("\t\t0. Go back to main menu.");
		System.out.println();
		System.out.print("\tSelect an option: ");
	}

	public static void DrawOptions(int cr) {
		System.out.println("\tChoose an option from below:");
		System.out.println();
		System.out.println("\t\t1. Generate Draw for " + RoundNoDisplay(cr));
		System.out.println("\t\t2. Add Motion");
		System.out.println();
		System.out.println("\t\t9. Go back to previous menu.");
		System.out.println("\t\t0. Go back to main menu.");
		System.out.println();
		System.out.print("\tSelect an option: ");
	}

	public static void RoundOption() {
		System.out.println("\tChoose an option from below:");
		System.out.println();
		System.out.println("\t\t1. Draw");
		System.out.println("\t\t2. Display");
		System.out.println("\t\t3. Result");
		System.out.println();
		System.out.println("\t\t9. Go back to previous menu.");
		System.out.println("\t\t0. Go back to main menu.");
		System.out.println();
		System.out.print("\tSelect an option: ");
	}

	public static void DisplayAllParticipants(ArrayList<Participants> Pa) {
		System.out.println();
		if (Pa.size() > 0) {
			System.out.println("\t******Perticipant Information******");
			System.out.println();
			System.out.println("\tName\t\tPerticipating as\t\tInstitution");
			for (Participants dis : Pa) {
				dis.display();
			}
		} else {
			System.err.println("\tNo participants added yet.");
			System.out.println("\tTry Adding some.");
		}
		System.out.println();
	}

	public static void DisplayAllInstitutes(ArrayList<Participants> Pa) {
		System.out.println();
		if (Pa.size() > 0) {
			System.out.println("\t******Institutions Perticipated******");
			System.out.println();
			System.out.println("\tName");
			System.out.println();
			for (Participants dis : Pa) {
				System.out.println("\t"+dis.getInstitution());
			}
			System.out.println();
		} else {
			System.err.println("\tNo participants added yet.");
			System.out.println("\tTry Adding some.");
		}
		System.out.println();
	}

	public static void TournamentOptions(String name) {
		String[] ops = {
				"Add Team",
				"Add Adjudicator",
				"Import data from a .dat file",
				"Round 1",
				"Round 2",
				"Round 3",
				"SF Round",
				"Final",
				"Break",
				"Display Team Rank",
				"Display Adjudicator Rank",
				"Display Participants",
				"Display Institutions"
		};

		System.out.println("\tOptions for '" + name + "'");
		System.out.println();

		for (int i = 0; i < ops.length; i++) {
			System.out.println("\t\t" + (i + 1) + ".\t" + ops[i]);
		}

		System.out.println("\t\t0.\tGo back to main menu.");
		System.out.println();
		System.out.print("\tSelect an option: ");
	}

	public static void PreviousTournaments() {
		ArrayList<String> tournamnets = DataReader.ReadBiStr(Consts.tournamentNames);
		if (tournamnets.size() > 0) {
			System.out.println("\tChoose an option from below:");
			System.out.println();
			for (int i = 0; i < tournamnets.size(); i++) {
				System.out.println("\t\t" + (i + 1) + ". " + tournamnets.get(i));
			}
		} else {
			System.err.println("\n\t*****You have no previous tournament. Go back and create one.*****\n");
		}
		System.out.println("\t\t0. Go back to main menu.");
		System.out.println();
		System.out.print("\tSelect an option: ");
	}

	public static int OptionChooser(Scanner inp, int up) {
		try {
			String s = inp.nextLine();
			int x = Integer.parseInt(s);
			if (x <= up && x >= 0) {
				return x;
			} else {
				throw new Exception("\n\tYou have choosen an option that does not exist.\n");
			}
		} catch (Exception e) {
			System.out.println("\n\tYou have choosen an option that does not exist.\n");
			return 0;
		}
	}

	public static int OptionChooser(Scanner inp, int low, int up) {
		try {
			String s = inp.nextLine();
			int x = Integer.parseInt(s);
			if (x <= up && x >= low) {
				return x;
			} else {
				throw new Exception("\n\tYou have choosen an option that does not exist.\n");
			}
		} catch (Exception e) {
			System.out.println("\n\tYou have choosen an option that does not exist.\n");
			return 0;
		}
	}

	public static void gap() {
		System.out.println("\n\n\n\n\n\n\n");
	}

	private static void smallGap() {
		System.out.println("\n\n");
	}

	public static void delay(Ann fun, int timeInMS) {
		try {
			Thread.sleep(timeInMS);
			fun.fun();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	public static void HomeScreen() {
		System.out.println("\tChoose an option from below:");
		System.out.println();
		System.out.println("\t\t1. Create new tournament");
		System.out.println("\t\t2. Choose from existing tournaments");
		System.out.println("\t\t9. Exit");
		System.out.println();
		System.out.print("\tSelect an option: ");
	}

	public static String CreateNewTournament(Scanner inp) {
		System.out.println();
		System.out.print("\tEnter the new tournament name: ");
		String name = inp.nextLine();
		System.out.println("\t******Creating a tournament: " + name);

		String fRef = Consts.tournamentNames;

		if (new File(fRef).exists()) {
			try {
				FileInputStream f = new FileInputStream(fRef);
				DataInputStream file = new DataInputStream(f);

				ArrayList<String> data = new ArrayList<String>();

				while (file.available() > 0) {
					String a = file.readUTF();
					data.add(a);
				}
				file.close();
				
				if (!data.contains(name)) {
					FileOutputStream fo = new FileOutputStream(fRef);
					DataOutputStream writer = new DataOutputStream(fo);
					for (int i = 0; i < data.size(); i++) {
						writer.writeUTF(data.get(i));
					}
					writer.writeUTF(name);
					writer.close();
					delay(() -> System.out.println("\t******Successfully Created " + name + " tournament"), 2000);
				} else {
					delay(() -> System.err.println("\t******Tournament " + name + " Already Exists."), 500);
					System.out.println("\t******Redirecting you to " + name);
				}
				
				smallGap();
				return "next";
			} catch (Exception e) {
				System.err.println(
						"\t#################There was an error creating new tournament, which says: " + e.getMessage());
				System.err.println("\n\n\t---------Try again");
				smallGap();
				return "previous";
			}
		} else {
			try {
				FileOutputStream fo = new FileOutputStream(fRef);
				DataOutputStream writer = new DataOutputStream(fo);
				writer.writeUTF(name);
				writer.close();
				delay(() -> System.out.println("******Successfully Created " + name + " tournament"), 2000);
				smallGap();
				return "next";
			} catch (Exception e) {
				System.err.println(
						"\t#################There was an error creating new tournament, which says: " + e.getMessage());
				System.err.println("\n\n\t---------Try again");
				smallGap();
				return "previous";
			}
		}

	}

	public static String RoundNoDisplay(int rn) {
		switch (rn) {
			case 1:
				return "Round 1";
			case 2:
				return "Round 2";
			case 3:
				return "Round 3";
			case 4:
				return "Semi Finals";
			case 5:
				return "Finals";
			default:
				return "";
		}
	}

	public static void logo(String s) { 
		                                                                     
		System.out.println();
		switch (s) {
			case "open":{
				System.out.println(" _ _ _       _                          _        ");
				System.out.println("| | | | ___ | | ___  ___  _____  ___   | |_  ___ ");
				System.out.println("| | | || -_|| ||  _|| . ||     || -_|  |  _|| . |");
				System.out.println("|_____||___||_||___||___||_|_|_||___|  |_|  |___|");
				break;
			}
			case "exit": {			
				System.out.println(" ___                   __    _  _   _    ___  _      ___       __     ");
				System.out.println("  |  |_|  /\\  |\\ | |/ (_    |_ / \\ |_)    |  |_) \\_/  |  |\\ | /__     ");
				System.out.println("  |  | | /--\\ | \\| |\\ __)   |  \\_/ | \\    |  | \\  |  _|_ | \\| \\_| o o");
				break;
			}
			default:
				break;
		}
		System.out.println();

		System.out.println("               ______   _______  ______   _______ _________ _______ ");
		System.out.println("              (  __  \\ (  ____ \\(  ___ \\ (  ___  )\\__   __/(  ____ \\");
		System.out.println("              | (  \\  )| (    \\/| (   ) )| (   ) |   ) (   | (    \\/");
		System.out.println("              | |   ) || (__    | (__/ / | (___) |   | |   | (__    ");
		System.out.println("              | |   | ||  __)   |  __ (  |  ___  |   | |   |  __)   ");
		System.out.println("              | |   ) || (      | (  \\ \\ | (   ) |   | |   | (      ");
		System.out.println("              | (__/  )| (____/\\| )___) )| )   ( |   | |   | (____/\\");
		System.out.println("              (______/ (_______/|/ \\___/ |/     \\|   )_(   (_______/");
		System.out.println();
		System.out.println("         _________ _______  ______   ______  _________ _        _______ ");
		System.out.println("         \\__   __/(  ___  )(  ___ \\ (  ___ \\ \\__   __/( (    /|(  ____ \\");
		System.out.println("            ) (   | (   ) || (   ) )| (   ) )   ) (   |  \\  ( || (    \\/");
		System.out.println("            | |   | (___) || (__/ / | (__/ /    | |   |   \\ | || |    ");
		System.out.println("            | |   |  ___  ||  __ (  |  __ (     | |   | (\\ \\) || | ____ ");
		System.out.println("            | |   | (   ) || (  \\ \\ | (  \\ \\    | |   | | \\   || | \\_  )");
		System.out.println("            | |   | )   ( || )___) )| )___) )___) (___| )  \\  || (___) |");
		System.out.println("            )_(   |/     \\||/ \\___/ |/ \\___/ \\_______/|/    )_)(_______)");
	}
	
	public static void Credit() {
		System.out.println();
		System.out.println("\t*****Created and maintained by ©Akhlak Hossain Jim(https://ahjim).*****");
		System.out.println("\t*****This project is created for Object Oriented Programming Course Project Submission.*****");
	}
}
