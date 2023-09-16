package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

interface Ann {
	void fun();
}

public class Components {
	
	public static void RankGenerate(ArrayList<Team> Ta, ArrayList<Adjudicator> Aa, String ty) {
		System.out.println();
		System.out.println("\t:.....Generating Break");
		ArrayList<Team> Teams= Ta;
		ArrayList<Adjudicator> Adj = Aa;
		for (int i = 0; i < Teams.size() - 1; i++) {
            for (int j = 0; j < Teams.size() - i - 1; j++) {
                if ((Teams.get(j).getScore().getTotalScores() > Teams.get(j + 1).getScore().getTotalScores()) &&
                        (Teams.get(j).getScore().getWins() > Teams.get(j + 1).getScore().getWins())) {
                    Team cpr = Teams.get(j);
                    Teams.set(i, Teams.get(i + 1));
                    Teams.set(i + 1, cpr);
                }
            }
        }
        for (int i = 0; i < Adj.size() - 1; i++) {
            for (int j = 0; j < Adj.size() - i - 1; j++) {
                if (Adj.get(j).getScore().getTotalScores() > Adj.get(j + 1).getScore().getTotalScores()) {
                    Adjudicator cpr = Adj.get(j);
                    Adj.set(i, Adj.get(i + 1));
                    Adj.set(i + 1, cpr);
                }
            }
        }
        switch (ty) {
			case "break": {
				Components.delay(()->System.out.println("\t::::::Break Generated"), 700);
				System.out.println();
				System.out.println("\tBreaking Teams:");
				System.out.println();
				System.out.println("\t\t1. Name: "+Teams.get(Teams.size()-1).getName()+"\t\twith: "+Teams.get(Teams.size()-1).getScore().getWins()+" wins & "+Teams.get(Teams.size()-1).getScore().getTotalScores()+" team score");
				System.out.println("\t\t1. Name: "+Teams.get(Teams.size()-2).getName()+"\t\twith: "+Teams.get(Teams.size()-2).getScore().getWins()+" wins & "+Teams.get(Teams.size()-2).getScore().getTotalScores()+" team score");
				System.out.println("\t\t1. Name: "+Teams.get(Teams.size()-3).getName()+"\t\twith: "+Teams.get(Teams.size()-3).getScore().getWins()+" wins & "+Teams.get(Teams.size()-3).getScore().getTotalScores()+" team score");
				System.out.println("\t\t1. Name: "+Teams.get(Teams.size()-4).getName()+"\t\twith: "+Teams.get(Teams.size()-4).getScore().getWins()+" wins & "+Teams.get(Teams.size()-4).getScore().getTotalScores()+" team score");
				System.out.println();
				break;
			}
			case "team":{
				System.out.println();
				System.out.println("\tTeam Rank:");
				for(int i = Teams.size()-1;i>0;i++) {
					System.out.println("\t\t1. Name: "+Teams.get(i).getName()+"\t\twith: "+Teams.get(i).getScore().getWins()+" wins & "+Teams.get(i).getScore().getTotalScores()+" team score");
				}
				System.out.println();
				break;
			}
			case "adj":{
				System.out.println();
				System.out.println("\tAdjudicator Rank:");
				for(int i = Adj.size()-1;i>0;i++) {
					System.out.println("\t\t1. Name: "+Adj.get(i).getName()+"\t\twith: "+Teams.get(i).getScore().getTotalScores()+" points.");
				}
				System.out.println();
				break;
			}
			default:
				break;
        }
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
				"Import data from a .txt file",
				"Round 1",
				"Round 2",
				"Round 3",
				"Break",
				"SF Round",
				"Final",
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

				int needOf = 1;

				while (file.available() > 0) {
					String a = file.readUTF();
					if (a == name) {
						needOf = 0;
						System.err.println("HERE");
						break;
					}
					data.add(a);
				}
				file.close();

				if (needOf == 1) {
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
					System.out.println("Redirecting you to " + name);
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

	private static String RoundNoDisplay(int rn) {
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

	public static void logo() {
		System.out.println("                                                 ");
		System.out.println(" _ _ _       _                          _        ");
		System.out.println("| | | | ___ | | ___  ___  _____  ___   | |_  ___ ");
		System.out.println("| | | || -_|| ||  _|| . ||     || -_|  |  _|| . |");
		System.out.println("|_____||___||_||___||___||_|_|_||___|  |_|  |___|");
		System.out.println("                                                 ");

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
}
