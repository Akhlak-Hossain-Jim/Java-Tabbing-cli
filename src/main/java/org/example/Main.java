package org.example;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		Scanner TerminalInput = new Scanner(System.in);
		String[] assigner = { "st", "nd", "rd" };

		// tournament options
		ArrayList<Participants> PARTICIPANTS = new ArrayList<>();
		ArrayList<Team> TEAMS = new ArrayList<Team>();
		ArrayList<Adjudicator> ADJUDICATORS = new ArrayList<Adjudicator>();

		int CURRENTROUND = 0;
		ArrayList<Team> CRTEAMS = new ArrayList<Team>();
		// ArrayList<Adjudicator> CRADJUDICATORS = new ArrayList<Adjudicator>();
		Round[] ROUNDS = new Round[5];
		boolean[] RoundGenerateState = { false, false, false, false, false };
		boolean[] RoundMotionState = { false, false, false, false, false };
		int[] RoundResultStat = { 0, 0, 0, 0, 0 };

		// main variables
		int AppShifter = 0;
		boolean AppRunner = true;
		String tournamentName = "";
		Team CHAMPION = new Team();

		// temporary variables
		int selectedRound = 0;

		Components.logo("open");
		Components.delay(() -> Components.gap(), 1200);

		while (AppRunner) {
			switch (AppShifter) {
				case 0: {
					Components.HomeScreen();
					int hms = Components.OptionChooser(TerminalInput, 9);
					switch (hms) {
						case 1: {
							AppShifter = 1;
							break;
						}
						case 2: {
							AppShifter = 2;
							break;
						}
						case 9: {
							AppShifter = 404;
							break;
						}
						default:
							AppShifter = 0;
							break;
					}
					break;
				}
				case 1: {// create a tournament
					if (Components.CreateNewTournament(TerminalInput) == "next") {
						tournamentName = DataReader.ReadBiStr(Consts.tournamentNames)
								.get(DataReader.ReadBiStr(Consts.tournamentNames).size() - 1);
						AppShifter = 3;
					} else {
						AppShifter = 0;
					}
					break;
				}
				case 2: {// select a tournament
					Components.PreviousTournaments();
					int ets = Components.OptionChooser(TerminalInput,
							DataReader.ReadBiStr(Consts.tournamentNames).size());
					if (ets > 0) {
						tournamentName = DataReader.ReadBiStr(Consts.tournamentNames).get(ets - 1);
						// Import tournament data
						AppShifter = 3;
					} else {
						AppShifter = 0;
					}
					break;
				}
				case 3: {// tournament options
					Components.TournamentOptions(tournamentName);
					int toh = Components.OptionChooser(TerminalInput, 14);
					if(toh == 14) {
						AppShifter = 20;
					} else if (toh != 0) {
						AppShifter = toh + 3;
					} else {
						AppShifter = 0;
					}
					break;
				}
				case 4: {// add Team
					System.out.println();
					System.out.print("\tEnter Institution Name: ");
					String TI = TerminalInput.nextLine();
					System.out.print("\tEnter Team Name: ");
					String TN = TerminalInput.nextLine();
					ArrayList<Debater> Tmem = new ArrayList<Debater>();
					for (int i = 0; i < 3; i++) {
						System.out.print("\t\t" + (i + 1) + assigner[i] + " Member's Name: ");
						String TMN = TerminalInput.nextLine();
						Debater tix = new Debater(TMN, TI);
						Tmem.add(tix);
						PARTICIPANTS.add(tix);
					}
					TEAMS.add(new Team(TN, TI, Tmem));
					System.out.println("\t*****SuccessFully added the team " + TN);
					System.out.println("\t*****Total teams added " + TEAMS.size());
					System.out.println();
					AppShifter = 3;
					break;
				}
				case 5: {// add Adjudicator
					System.out.println();
					System.out.print("\tEnter Institution Name: ");
					String AI = TerminalInput.nextLine();
					System.out.print("\tEnter Adjudicator's Name: ");
					String AN = TerminalInput.nextLine();
					Double AiP = -1.0;
					while (AiP < 0) {
						System.out.print("\tEnter Adjudicator's initial point(decimal between 0.0-0.9): ");
						AiP = NumberInput.isDouble(TerminalInput);
					}
					PARTICIPANTS.add(new Adjudicator(AN, AI, AiP));
					ADJUDICATORS.add(new Adjudicator(AN, AI, AiP));
					System.out.println("\t*****SuccessFully added the Adjudicator " + AN);
					System.out.println("\t*****Total Adjudicators added " + ADJUDICATORS.size());
					System.out.println();
					AppShifter = 3;
					break;
				}
				case 6: {// Import tab data
					System.out.println();
					System.err.println("\t*****There should not be any space in the file name.");
					System.out.print("\tEnter path to a .dat file to import data from: ");
					String DIFname = TerminalInput.next();
					File f = new File(DIFname);
					System.out.println("\n\t*****Searching your file.......");
					if (f.exists()) {
						try {
							FileInputStream DIFref = new FileInputStream(DIFname);
							Components.delay(() -> System.out.println("\n\tFile found, Scanning your file now."), 000);
							DataInputStream DIFdata = new DataInputStream(DIFref);
							int DIFTeams = DIFdata.readInt();
							for (int i = 0; i < DIFTeams; i++) {
								String DIFTI = DIFdata.readUTF();
								String DIFTN = DIFdata.readUTF();
								ArrayList<Debater> DIFTmem = new ArrayList<Debater>();
								for (int j = 0; j < 3; j++) {
									String DIFTMN = DIFdata.readUTF();
									Debater DIFtix = new Debater(DIFTMN, DIFTI);
									DIFTmem.add(DIFtix);
									PARTICIPANTS.add(DIFtix);
								}
								TEAMS.add(new Team(DIFTN, DIFTI, DIFTmem));
							}
							int DIFAdjs = DIFdata.readInt();
							for (int i = 0; i < DIFAdjs; i++) {
								String DIFAI = DIFdata.readUTF();
								// DIFdata.nextLine();
								// DIFdata.nextLine();
								String DIFAN = DIFdata.readUTF();
								double DIFAiP = DIFdata.readDouble();
								PARTICIPANTS.add(new Adjudicator(DIFAN, DIFAI, DIFAiP));
								ADJUDICATORS.add(new Adjudicator(DIFAN, DIFAI, DIFAiP));
							}
							DIFdata.close();
							Components.delay(() -> System.out.println("\n\t*****Total " + DIFTeams + " teams & "
									+ DIFAdjs + " Adjudicators Successfully added to the tournament."), 500);
						} catch (Exception e) {
							System.err.println(
									"\t*****An Error occured opening the file at " + DIFname + " " + e.getMessage());
							System.err.println(e);
						}
					} else {
						System.err.println("\t*****No file found at " + DIFname);
					}
					TerminalInput.nextLine();
					AppShifter = 3;
					break;
				}
				case 7: {// Round 1
					selectedRound = 1;
					if (TEAMS.size() > 0 && TEAMS.size() % 2 == 0 && ADJUDICATORS.size() >= TEAMS.size() / 2) {
						Components.RoundOption();
						int toh = Components.OptionChooser(TerminalInput, 9);
						if (toh == 9) {
							AppShifter = 3;
						} else if (toh == 0) {
							AppShifter = 0;
						} else if (toh >= 1 && toh <= 3) {
							AppShifter = 16 + toh;
						} else {
							AppShifter = 3;
						}
					} else if (TEAMS.size() < 0) {
						System.err.println("\tAdd some teams to see options.");
						AppShifter = 3;
					} else if (TEAMS.size() % 2 != 0) {
						System.err.println("\tAdd even Number of teams to see options.");
						AppShifter = 3;
					} else if (ADJUDICATORS.size() >= TEAMS.size() / 2) {
						System.err.println("\tNeed atleast half number of Adjudicator from the Team Numbers.");
						AppShifter = 3;
					}
					break;
				}
				case 8: {// Round 2
					selectedRound = 2;
					if (CURRENTROUND == 1) {
						Components.RoundOption();
						int toh = Components.OptionChooser(TerminalInput, 9);
						if (toh == 9) {
							AppShifter = 3;
						} else if (toh == 0) {
							AppShifter = 0;
						} else if (toh >= 1 && toh <= 3) {
							AppShifter = 16 + toh;
						} else {
							AppShifter = 3;
						}
					} else {
						System.err.println("\tNeed to finish round " + (CURRENTROUND + 1) + " to access round "
								+ selectedRound + " options.");
						AppShifter = 3;
					}
					break;
				}
				case 9: {// Round 3
					selectedRound = 3;
					if (CURRENTROUND == 2) {
						Components.RoundOption();
						int toh = Components.OptionChooser(TerminalInput, 9);
						if (toh == 9) {
							AppShifter = 3;
						} else if (toh == 0) {
							AppShifter = 0;
						} else if (toh >= 1 && toh <= 3) {
							AppShifter = 16 + toh;
						} else {
							AppShifter = 3;
						}
					} else {
						System.err.println("\tNeed to finish round " + (CURRENTROUND + 1) + " to access round "
								+ selectedRound + " options.");
						AppShifter = 3;
					}
					break;
				}
				case 10: {// SF
					selectedRound = 4;
					if (CURRENTROUND == 3) {
						Components.RoundOption();
						int toh = Components.OptionChooser(TerminalInput, 9);
						if (toh == 9) {
							AppShifter = 3;
						} else if (toh == 0) {
							AppShifter = 0;
						} else if (toh >= 1 && toh <= 3) {
							AppShifter = 16 + toh;
						} else {
							AppShifter = 3;
						}
					} else {
						System.err.println(
								"\tNeed to finish round " + Components.RoundNoDisplay(CURRENTROUND + 1)
										+ " to access SemiFinal round options.");
						AppShifter = 3;
					}
					break;
				}
				case 11: {// Final
					selectedRound = 5;
					if (CURRENTROUND == 4) {
						Components.RoundOption();
						int toh = Components.OptionChooser(TerminalInput, 9);
						if (toh == 9) {
							AppShifter = 3;
						} else if (toh == 0) {
							AppShifter = 0;
						} else if (toh >= 1 && toh <= 3) {
							AppShifter = 16 + toh;
						} else {
							AppShifter = 3;
						}
					} else {
						System.err.println(
								"\tNeed to finish round " + Components.RoundNoDisplay(CURRENTROUND + 1)
										+ " to access Final round options.");
						AppShifter = 3;
					}
					break;
				}
				case 12: {// Break
					if (CURRENTROUND >= 3) {
						Components.RankGenerate(TEAMS, ADJUDICATORS, "break");
					} else {
						System.out.println(
								"\tBreak only can be generated after All 3 preleminary rounds has been completed.");
					}
					AppShifter = 3;
					break;
				}
				case 13: {// Team Rank
					if (CURRENTROUND >= 3) {
						Components.RankGenerate(TEAMS, ADJUDICATORS, "team");
					} else {
						System.out.println(
								"\tAfter All 3 preleminary round is completion you will be able to see the Team rank here.");
					}
					AppShifter = 3;
					break;
				}
				case 14: {// Adj Rank
					if (CURRENTROUND >= 3) {
						Components.RankGenerate(TEAMS, ADJUDICATORS, "adj");
					} else {
						System.out.println(
								"\tAfter All 3 preleminary round is completion you will be able to see the Adjudicator rank here.");
					}
					AppShifter = 3;
					break;
				}
				case 15: {// All Participants
					Components.DisplayAllParticipants(PARTICIPANTS);
					AppShifter = 3;
					break;
				}
				case 16: {// All Institution
					Components.DisplayAllInstitutes(PARTICIPANTS);
					AppShifter = 3;
					break;
				}
				case 17: {// Draw Options
					Components.DrawOptions(selectedRound);
					int toh = Components.OptionChooser(TerminalInput, 13);
					switch (toh) {
						case 0: {
							AppShifter = 0;
							break;
						}
						case 1: {// generate draw
							if (RoundGenerateState[selectedRound - 1]) {
								System.out.println(
										"\n\tThe Match up for round " + selectedRound + " is already generated.");
								System.out.println("\tGo to Display option to Display Match up for round "
										+ selectedRound + ".\n");
							} else {
								if (CURRENTROUND == 3) {
									ROUNDS[selectedRound - 1] = new Round(selectedRound, CRTEAMS, ADJUDICATORS);
									ROUNDS[selectedRound - 1].Generate();
								} else if (CURRENTROUND == 4) {
									ROUNDS[selectedRound - 1] = new Round(selectedRound, CRTEAMS, ADJUDICATORS);
									ROUNDS[selectedRound - 1].Generate();
								} else {
									ROUNDS[selectedRound - 1] = new Round(selectedRound, TEAMS, ADJUDICATORS);
									ROUNDS[selectedRound - 1].Generate();
								}
								RoundGenerateState[selectedRound - 1] = true;
							}
							AppShifter = 17;
							break;
						}
						case 2: {// add motion
							if (RoundGenerateState[selectedRound - 1] && !RoundMotionState[selectedRound - 1]) {
								System.out.print("\n\tEnter motion for round: ");
								String motion = TerminalInput.nextLine();
								ROUNDS[selectedRound - 1].setMotion(motion);
								AppShifter = 18;
								RoundMotionState[selectedRound - 1] = true;
							} else if (RoundGenerateState[selectedRound - 1] && RoundMotionState[selectedRound - 1]) {
								System.out.println("\n\tThe Motion for round " + selectedRound + " is already added.");
								System.out.println("\tGo to Display option to Display the Motion for round "
										+ selectedRound + ".\n");
							} else {
								System.out.println();
								System.out.println("\tPlease Generate the Draw first to add motion.\n");
								System.out.println();
								AppShifter = 17;
							}
							break;
						}
						case 9:
							AppShifter = 6 + selectedRound;
							break;
						default:
							AppShifter = 3;
							break;
					}
					break;
				}
				case 18: {// Round Display options
					Components.DisplayOptions(selectedRound);
					int toh = Components.OptionChooser(TerminalInput, 13);
					switch (toh) {
						case 0: {
							AppShifter = 0;
							break;
						}
						case 1: {// display draw
							if (RoundGenerateState[selectedRound - 1]) {
								ROUNDS[selectedRound - 1].displayMatchUp();
							} else {
								System.out.println(
										"\n\tThe Match up for round " + selectedRound + " has not generated yet.");
								System.out.println(
										"\tGo to Draw option to Generate Match up for round " + selectedRound + ".\n");
							}
							AppShifter = 18;
							break;
						}
						case 2: {// display motion
							if (RoundMotionState[selectedRound - 1]) {
								ROUNDS[selectedRound - 1].displayMotion();
								AppShifter = 6 + selectedRound;
							} else {
								System.out.println("\n\tThe Motion for round " + selectedRound + " has not added yet.");
								System.out.println(
										"\tGo to Draw option to Generate Match up for round " + selectedRound + ".\n");
								AppShifter = 18;
							}
							break;
						}
						case 9:
							AppShifter = 6 + selectedRound;
							break;
						default:
							AppShifter = 3;
							break;
					}
					break;
				}
				case 19: {// Add result option
					if (RoundGenerateState[selectedRound - 1] && RoundMotionState[selectedRound - 1]) {
						Components.ResultOptions(ROUNDS[selectedRound - 1], RoundResultStat[selectedRound - 1]);
						int toh = Components.OptionChooser(TerminalInput, 13);
						switch (toh) {
							case 0: {
								AppShifter = 0;
								break;
							}
							case 1: {// add result option
								MatchUp SaMUp = ROUNDS[selectedRound - 1].getMatchUps()
										.get(RoundResultStat[selectedRound - 1]);
								int GovS = 0;
								int OppS = 0;
								while (GovS < 1) {
									System.out.print("\tEnter score for " + SaMUp.getGov().getName() + " (70-80): ");
									GovS = Components.OptionChooser(TerminalInput, 70, 80);
								}
								while (OppS < 1) {
									System.out.print("\tEnter score for " + SaMUp.getOpp().getName() + " (70-80): ");
									OppS = Components.OptionChooser(TerminalInput, 70, 80);
								}
								if (GovS == OppS) {
									System.out.println("\t****Gov's & Opp's result cannot be same. Try again.****");
									AppShifter = 19;
								} else if (GovS > OppS) {
									System.out.println("\t*****Gov: " + SaMUp.getGov().getName() + " is the winner.");
									for (int i = 0; i < TEAMS.size(); i++) {
										if (TEAMS.get(i).getName() == SaMUp.getGov().getName()) {
											if(CURRENTROUND==3) {
												Team el = TEAMS.get(i);
												el.getScore().addScore(selectedRound, GovS, 1);
												CRTEAMS.add(el);
											}else if(CURRENTROUND==4){
												Team el = TEAMS.get(i);
												el.getScore().addScore(selectedRound, GovS, 1);
												CHAMPION =el;
											}else {
												TEAMS.get(i).getScore().addScore(selectedRound, GovS, 1);
											}
										}
									}
									for (int i = 0; i < TEAMS.size(); i++) {
										if (TEAMS.get(i).getName() == SaMUp.getOpp().getName()) {
											TEAMS.get(i).getScore().addScore(selectedRound, OppS, 0);
										}
									}
									RoundResultStat[selectedRound - 1]++;
								} else if (GovS < OppS) {
									System.out.println("\t*****Opp: " + SaMUp.getOpp().getName() + " is the winner.");
									for (int i = 0; i < TEAMS.size(); i++) {
										if (TEAMS.get(i).getName() == SaMUp.getGov().getName()) {
											TEAMS.get(i).getScore().addScore(selectedRound, GovS, 0);
										}
									}
									for (int i = 0; i < TEAMS.size(); i++) {
										if (TEAMS.get(i).getName() == SaMUp.getOpp().getName()) {
											if(CURRENTROUND==3) {
												Team el = TEAMS.get(i);
												el.getScore().addScore(selectedRound, OppS, 1);
												CRTEAMS.add(el);
											}else if(CURRENTROUND==4){
												Team el = TEAMS.get(i);
												el.getScore().addScore(selectedRound, OppS, 1);
												CHAMPION =el;
											}else {												
												TEAMS.get(i).getScore().addScore(selectedRound, OppS, 1);
											}
										}
									}
									RoundResultStat[selectedRound - 1]++;
								}
								if (RoundResultStat[selectedRound - 1] == ROUNDS[selectedRound - 1].getMatchUps()
										.size()) {
									CURRENTROUND++;
									if (CURRENTROUND == 3) {
										CRTEAMS = Components.TopGenerate(TEAMS, 4);
									}
//									if (CURRENTROUND == 4) {
//										CRTEAMS = Components.TopGenerate(CRTEAMS, 2);
//									}
									// for(Team el: TEAMS) {
									// System.out.println(el.getScore().getTotalScores());
									// }
									System.out.println();
									if (CURRENTROUND == 4) {
										CRTEAMS = Components.TopGenerate(CRTEAMS, 2);
										System.out.println("\tThe tournament is is successfully conducted.");
										System.out.println("\tYou can now see the Champions.");
									}else {										
										System.out.println("\tAll scores for " + Components.RoundNoDisplay(CURRENTROUND)
										+ " is successfully added..");
										System.out.println(
												"\t" + Components.RoundNoDisplay(CURRENTROUND) + " marked as completed.");
										System.out.println("\tNow you can access "
												+ Components.RoundNoDisplay(CURRENTROUND + 1) + " options.");
									}
									System.out.println();
									AppShifter = 3;
								} else {
									AppShifter = 19;
								}
								break;
							}
							case 9:
								AppShifter = 6 + selectedRound;
								break;
							default:
								AppShifter = 3;
								break;
						}
					} else {
						System.out.println(
								"\n\tThe Match up for round " + selectedRound + " or Motion has not created yet.");
						System.out.println("\tGo to Draw option to Generate Match up or Add Motion for round "
								+ selectedRound + ".\n");
						AppShifter = 3;
					}
					break;
				}
				case 20:
					if(CURRENTROUND == 5 && CHAMPION!=null) {
						System.out.println();
						System.out.println("\t##################################################");
						System.out.println("\tChampion of the tournament:");
						System.out.print("\t\t");
						CHAMPION.display();
						System.out.println("\t##################################################");
					}else {
						System.out.println();
						System.out.println("\t*****Final round haven't been done yet.*****");
						System.out.println();
					}
					AppShifter = 3;
					break;
				case 404:
					Components.logo("exit");
					Components.Credit();
					AppRunner = false;
					break;
				default:
					break;
			}
		}

		// TerminalInputs closing
		TerminalInput.close();
	}
}