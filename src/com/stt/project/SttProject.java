package com.stt.project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.border.Border;

//import com.sun.speech.freetts.Voice;
//import com.sun.speech.freetts.VoiceManager;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import edu.cmu.sphinx.util.props.PropertyException;

public class SttProject {

	static int x = 0;
	static String message = "";
	static String to;
	static String resultText;
	static String answerToStr;
	static String textToSpeech;
	static Process pro;
	static Random ran = new Random();
	static Formatter y;
	static Scanner z;

	/**
	 * Launch the application.
	 * @throws PropertyException 
	 * @throws IOException 
	 * @throws InstantiationException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, PropertyException, InstantiationException, InterruptedException {
		

		// For creating the text.txt file.
		y = new Formatter("resources\\file\\text.txt");
		
		
		/**
		 * GUI Component
		 */
			JFrame SpeechFrame = new JFrame("Speech To Text");
			SpeechFrame.setSize(480,530);
			SpeechFrame.setLocationRelativeTo(null);
			SpeechFrame.setResizable(false);
			SpeechFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JLabel mainBgImage = new JLabel();
			SpeechFrame.add(mainBgImage);
			mainBgImage.setIcon(new ImageIcon("resources//images//bg.jpg"));
			SpeechFrame.setVisible(true);
			mainBgImage.setOpaque(false);
			SpeechFrame.setLayout(null);
			
			/*
			 * Info Button
			 */
			JButton btnInfo = new JButton(new ImageIcon("resources//images//info.jpg"));
			mainBgImage.add(btnInfo);
			btnInfo.setRolloverIcon(new ImageIcon ("resources//images//info-active.jpg"));
			btnInfo.setBounds(50,223,58,58);
			btnInfo.setToolTipText("About Application");
			btnInfo.setBorderPainted(false);
			
			/*
			 * Action Listener for Button Info
			 */
			btnInfo.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					JOptionPane.showMessageDialog(null, "<html>"
							+ "<center> "
							+ "<b>Speech To Text </b>"
							+ "<br><br> "
							+ "This application helps to convert the spoken "
							+ "<br> words into text and also can command your computer.  "
							+ "</center> "
							+ "<br> "
							+ "<u>Developed by: </u> <br>"
							+ "Anish Adhikari <br>"
							+ "Manoj Phaju <br>"
							+ "Niraj Thike <br>"
							+ "Sujan Gainju <br>"
							+ "Suman Kaiti <br> "
							+ "<br> "
							+ "As the 7th semester project of Batch 069."
							+ "</html>"
							);
				}
			});
			
			/*
			 * HowTo Button
			 */
			JButton btnHowTo = new JButton(new ImageIcon("resources//images//help.jpg"));
			mainBgImage.add(btnHowTo);
			btnHowTo.setRolloverIcon(new ImageIcon ("resources//images//help-active.jpg"));
			btnHowTo.setBounds(120,223,58,58);
			btnHowTo.setToolTipText("How To Use The Application");
			btnHowTo.setBorderPainted(false);
			
			/*
			 * Action Listener for Button HowTo
			 */
			btnHowTo.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					JOptionPane.showMessageDialog(null, "<html><center> <b>How To Use </b><br><br> Start the application and speak any of the following.  </center>"
							+ "<ul>"
							+ "<li> hi / hello / hey</li>"
							+ "<li>what's up</li>"
							+ "<li>make me laugh</li>"
							+ "<li>make me a coffee</li>"
							+ "<li>nice one</li>"
							+ "<li>what time is it</li>"
							+ "<li>what date is it</li>"
							+ "<li>how old are you</li>"
							+ "<li>what is your name</li>"
							+ "<li> open/close explorer, browser, word, paint</li>"
							+ "<li>site facebook, google, youtube, twitter, mail</li>"
							+ "<li> stop recognition / goodbye computer</li>"
							+ "</ul>"
							+ "</html>"
							);
				}
			});
			
			/*
			 * TextArea button
			 */
			JButton toggleBtn = new JButton(new ImageIcon("resources//images//edit.jpg"));
			mainBgImage.add(toggleBtn);
			toggleBtn.setRolloverIcon(new ImageIcon ("resources//images//edit-active.jpg"));
			toggleBtn.setBounds(348,186,99,70);
			toggleBtn.setToolTipText("Free Writing and Send Email");
			toggleBtn.setBorderPainted(false);
			

			/*
			 * Action Listener for toggleBtn Button
			 */

			JTextPane textPane = new JTextPane();
			JTextPane send_email = new JTextPane();
			
			toggleBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					Integer confirm = JOptionPane.showConfirmDialog(mainBgImage, "You won't be able to go back to Commands."
							+ " Do you want to continue?"
							);
					
					System.out.println(confirm);
					
//					if(confirm == 0){
//						x = 1;
//					}else{
//						x=0;
//					}
					
					if( confirm == 0){
						
						mainBgImage.remove(btnHowTo);
						mainBgImage.remove(btnInfo);
//						JTextPane send_email = new JTextPane();
						mainBgImage.add(send_email);
						send_email.setBounds(50,223,249,58);
						send_email.setBackground(new Color(80, 80, 255));
						send_email.setForeground(new Color(255, 255, 255));
						send_email.setFont(new Font("Times New Roman", Font.PLAIN, 16));
						send_email.setEditable(false);
						send_email.setText("Send eMail => SEND MAIL/EMAIL Save File => SAVE IT/FILE");
						
						
						//JTextPane textPane = new JTextPane();
						mainBgImage.add(textPane);
						textPane.setBounds(42, 290, 380, 160);
						textPane.setBackground(new Color(228, 228, 228));
						textPane.setForeground(new Color(2, 82, 167));
						textPane.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20));
						textPane.setEditable(true);

					}
					else{
						
					}
					
				}
			});

			/*
			 * Output Window
			 */
			
			//Pane to display User Query			
			
			JTextPane userSpeak = new JTextPane();
			mainBgImage.add(userSpeak);
			userSpeak.setBounds(60,300,355,65);
			userSpeak.setForeground(new Color(64, 64, 64));
			userSpeak.setFont(new Font("Cambria", Font.BOLD, 20));
			Border border = BorderFactory.createLineBorder(new Color(0, 84, 186));
			userSpeak.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5)));
			userSpeak.setBackground(new Color(228, 228, 228));
			userSpeak.setEditable(false);

			// Pane to Display answer

			JTextPane slnDisplay = new JTextPane();
			mainBgImage.add(slnDisplay);
			slnDisplay.setForeground(new Color(0, 102, 204));
			slnDisplay.setFont(new Font("Cambria", Font.BOLD, 16));
			slnDisplay.setBounds(60,380,355,65);
			slnDisplay.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5)));
			slnDisplay.setBackground(new Color(228, 228, 228));
			slnDisplay.setEditable(false);

			//Listening Notification

			JLabel listening = new JLabel();
			listening.setForeground(Color.BLACK);
			listening.setFont(new Font("Bookman Old Style", Font.BOLD, 25));
			mainBgImage.add(listening);
			listening.setBounds(30, 175, 290, 30);
			listening.setText("Loading ...");


			/*
			 * Speech Recognition Components
			 */
				
			// Loading configuration file
				
				ConfigurationManager cm;
				
				cm = new ConfigurationManager(SttProject.class.getResource("config.xml"));
				
				Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
				recognizer.allocate();
				listening.setText("Listening...");
				
			// start microphone or exit if not
				
				Microphone microphone = (Microphone) cm.lookup("microphone");
				if(!microphone.startRecording()){
					JOptionPane.showMessageDialog(null, "Cannot start Mic.","Error", JOptionPane.ERROR_MESSAGE);
					TimeUnit.SECONDS.sleep(2);
					recognizer.deallocate();
					System.exit(1);
				}
				
			// Loop the recognition until the program exits
				
				while(true){
					listening.setVisible(true);
					Result result = recognizer.recognize();
					
					if(result != null){ 
						
						resultText = result.getBestFinalResultNoFiller();
												
						/*
						 * Free writing + email send
						 */
												
						if(x == 1) {
							
							// Open text.txt file and append the text to the file.
							/*
							 * PrintWriter pw = new PrintWriter(new FileOutputStream("resources\\file\\text.txt", true));
							 * pw.append(resultText+" ");
							 * pw.close();
							*/
							//TextToSpeechConverter.speak(resultText);
							
//							textPane.setText(textPane.getText()+ resultText + " ");
							
							/*
							 * Exit the text Writing
							 */
							if(resultText.equalsIgnoreCase("exit")){
								
								textPane.setText(resultText + "\n \n" + " Clearing texts and Exiting......  ");
								TimeUnit.SECONDS.sleep(4); //wait for 4 seconds
								System.exit(1);
								
							}
							
							/*
							 * Ask for email to send
							 */
							else if(resultText.equalsIgnoreCase("send email") || resultText.equalsIgnoreCase("send mmail")){
								if(message.equalsIgnoreCase("")){
									message = textPane.getText();
								}
								//message = textPane.getText();
								String to_email = JOptionPane.showInputDialog(null, "Send Email to : ");

//								Send Email
								
//								SendEmail.send(to_email, "Test Email", message)
								if(to_email != null) {
									
									if (SendEmail.send(to_email, "Test Email", message)) {
										JOptionPane.showMessageDialog(textPane,
												"eMail send successfully!!");
	
										// clear data
										textPane.setText("");
									} else {
										JOptionPane.showMessageDialog(textPane,
												"eMail sending fail,plz check your email id!!");
									}
									
								}else {
									JOptionPane.showMessageDialog(textPane,
											"Sending Email operation has been cancelled!!");
								}
							
								//textPane.setText(textPane.getText()+a);
								
								TimeUnit.SECONDS.sleep(2);
							}
							
							
							/*
							 * Save the text in the TextPane in a File
							 */
							
							
							else if(resultText.equalsIgnoreCase("save file") ||
									resultText.equalsIgnoreCase("save it")){
								
//								  String filename = JOptionPane.showInputDialog("Name this file");
							        JFileChooser savefile = new JFileChooser();
							        savefile.setSelectedFile(new File("C:/Users/Suz Zan/Desktop/SaveFile.txt"));
//							        savefile.showSaveDialog(savefile);
							        BufferedWriter writer;
							        
							        int sf = savefile.showSaveDialog(null);
							        if(sf == JFileChooser.APPROVE_OPTION){
							            try {
							            	
							            	writer = new BufferedWriter(new FileWriter(savefile.getSelectedFile()));
							              
							                writer.append(textPane.getText());
							                writer.close();
							                JOptionPane.showMessageDialog(null, "File has been saved","File Saved",JOptionPane.INFORMATION_MESSAGE);
							                // true for rewrite, false for override

							            } catch (IOException e) {
							                e.printStackTrace();
							            }
							        }else if(sf == JFileChooser.CANCEL_OPTION){
							            JOptionPane.showMessageDialog(null, "File save has been canceled");
							        }
							}
							
							else if(resultText.equalsIgnoreCase("dot")){
								textPane.setText(textPane.getText()+". ");
							}
							else if(resultText.equalsIgnoreCase("com ma")){
								textPane.setText(textPane.getText()+", ");
							}
							else if(resultText.equalsIgnoreCase("question mark")){
								textPane.setText(textPane.getText()+"? ");
							}
							
							else{
								textPane.setText(textPane.getText()+ resultText + " ");
							}
						}
						
						/*
						 * Interactive Part
						 */
						
						else {
							
								
								//userSpeak.setText("You Said: "+'\n' + resultText);
								
								
					// Basic Introduction Part
								
						// Greeting
								if((resultText.equalsIgnoreCase("hey")) ||
									(resultText.equalsIgnoreCase("hi")) ||
									(resultText.equalsIgnoreCase("hello")))
								{
									userSpeak.setText("You Said: "+'\n' + resultText);
									//userSpeak.setText("You Said: "+'\n' + resultText);
									try{
										String[] greet = {
												"Hello there!",
												"Yes, How can I help you?",
												"What can i do for you?",
												"What can i help you with?",
												"What is it?"};
										
										String greet_ran = greet[ran.nextInt(greet.length)];
										slnDisplay.setText(greet_ran);
										answerToStr = slnDisplay.getText();
										textToSpeech=("Say "+"\""+answerToStr+"\"");
										Runtime.getRuntime().exec(textToSpeech);
										//TextToSpeechConverter ttsc = new TextToSpeechConverter();
										//ttsc.speak(greet_ran);
										
									} catch( Exception e){}
								}
								
						// About 
								if((resultText.equalsIgnoreCase("how are you")) ||
										(resultText.equalsIgnoreCase("what's up")))
									{
		
									userSpeak.setText("You Said: "+'\n' + resultText);
										try{
											String[] about = {
													"I am good.Thanks for asking"};
											String about_ran = about[ran.nextInt(about.length)];
											slnDisplay.setText(about_ran);
		
											//TextToSpeechConverter ttsc = new TextToSpeechConverter();
											//ttsc.speak(about_ran);
										} catch( Exception e){}
									}
								
						// Name
								if((resultText.equalsIgnoreCase("do you have a name")))
									{
		
									userSpeak.setText("You Said: "+'\n' + resultText);
										try{
											String[] intro = {
													"I have no name but you can call me computer."};
											String intro_ran = intro[ran.nextInt(intro.length)];
											slnDisplay.setText(intro_ran);
										
											//TextToSpeechConverter ttsc = new TextToSpeechConverter();
											//ttsc.speak(intro_ran);
											
										} catch( Exception e){}
									}
						// Age
								if((resultText.equalsIgnoreCase("how old are you")))
								{
		
									userSpeak.setText("You Said: "+'\n' + resultText);
									try{
										String[] age = {
												"No idea.... First thing I saw was a baby Dinosaur."};
										String age_ran = age[ran.nextInt(age.length)];
										slnDisplay.setText(age_ran);
										
										//TextToSpeechConverter ttsc = new TextToSpeechConverter();
										//ttsc.speak(age_ran);
										
									} catch( Exception e){}
								}
						
						// System time
								if((resultText.equalsIgnoreCase("what time is it")))
								{
									Calendar cal = Calendar.getInstance();
							        SimpleDateFormat sdf = new SimpleDateFormat("h:mm aaa");
							        
		
									userSpeak.setText("You Said: "+'\n' + resultText);
									try{
										String[] t = {
												"It is "+sdf.format(cal.getTime()) };
										String t_ran = t[ran.nextInt(t.length)];
										slnDisplay.setText(t_ran);
									
										//TextToSpeechConverter ttsc = new TextToSpeechConverter();
										//ttsc.speak(t_ran);
										
									} catch( Exception e){}
								}
								
						// Full Date
								if((resultText.equalsIgnoreCase("what date is it")))
								{
									Calendar cal = Calendar.getInstance();
							        SimpleDateFormat sdf = new SimpleDateFormat("h:mm aaa, EEE, MMM d, y ");
							        
		
									userSpeak.setText("You Said: "+'\n' + resultText);
									try{
										String[] d = {
												"It is "+sdf.format(cal.getTime()) };
										String d_ran = d[ran.nextInt(d.length)];
										slnDisplay.setText(d_ran);
									
										//TextToSpeechConverter ttsc = new TextToSpeechConverter();
										//ttsc.speak(d_ran);
										
									} catch( Exception e){}
								}
								
						// Humor
								if((resultText.equalsIgnoreCase("make me a coffee")))
								{
		
									userSpeak.setText("You Said: "+'\n' + resultText);
									try{
										String[] humor = {
												"WHAT? Make it yourself."};
										String humor_ran = humor[ran.nextInt(humor.length)];
										slnDisplay.setText(humor_ran);
										
										//TextToSpeechConverter ttsc = new TextToSpeechConverter();
										//ttsc.speak(humor_ran);
										
									} catch( Exception e){}
								}
								
								if((resultText.equalsIgnoreCase("make me laugh")))
								{
		
									userSpeak.setText("You Said: "+'\n' + resultText);
									try{
										String[] humor = {
												"What's the best thing about Switzerland? \n I don't know, but the flag is a big plus.",
												"Hear about the new restaurant called Karma? \n There’s no menu. You get what you deserve.",
												"Anton, do you think I’m a bad mother? \n My name is Paul.",
												"I heard women love a man in uniform. Can’t wait to start working at McDonal",
												"It is so cold outside I saw a politician with his hands in his own pockets.",
												"I can’t believe I forgot to go to the gym today. That’s 7 years in a row now."};
										
										String humor_ran = humor[ran.nextInt(humor.length)];
										slnDisplay.setText(humor_ran);
									
										//TextToSpeechConverter ttsc = new TextToSpeechConverter();
										//ttsc.speak(humor_ran);
										
									} catch( Exception e){}
								}
								
						// British Humour
								if((resultText.equalsIgnoreCase("nice one")))
								{
		
									userSpeak.setText("You Said: "+'\n' + resultText);
									try{
										String[] d = {
												"Just saying NICE ONE in an accent won't make you British!!! :/"};
										String d_ran = d[ran.nextInt(d.length)];
										slnDisplay.setText(d_ran);
									
										//TextToSpeechConverter ttsc = new TextToSpeechConverter();
										//ttsc.speak(d_ran);
										
									} catch( Exception e){}
								}
								
							
						
						// Stop recognition or Exit
								if((resultText.equalsIgnoreCase("stop recognition")) ||
										(resultText.equalsIgnoreCase("goodbye computer")))
								{
		
									userSpeak.setText("You Said: "+'\n' + resultText);
									try{
										String[] d = {
												"Good Bye!!!",
												"Bye. Have Fun",
												"You are one nice person to talk to. Ba-Bye"};
										String d_ran = d[ran.nextInt(d.length)];
										slnDisplay.setText(d_ran);
		
										//TextToSpeechConverter ttsc = new TextToSpeechConverter();
									//	ttsc.speak(d_ran);
										
										TimeUnit.SECONDS.sleep(2);
										System.exit(1);
										
									} catch( Exception e){}
								}
								
						// Commands
								
					// ********************************************************************************************************************** //		
						
						// Paint
								if(resultText.equalsIgnoreCase("open paint"))
								{	 
									userSpeak.setText("You Said: "+'\n' + resultText);
									try{
										pro = Runtime.getRuntime().exec("cmd /c start mspaint");
										slnDisplay.setText("Opening Paint ...");
									} catch(Exception ae){}
								}
								else if(resultText.equalsIgnoreCase("close paint"))
								{	 
									userSpeak.setText("You Said: "+'\n' + resultText);
									try{
										pro = Runtime.getRuntime().exec("cmd /c start taskkill /im mspaint.exe /f");
										slnDisplay.setText("Closing Paint ...");
									} catch(Exception ae){}
								}
		
								
						// Browser
								if(resultText.equalsIgnoreCase("open browser"))
								{	 
									userSpeak.setText("You Said: "+'\n' + resultText);
									try{
										pro = Runtime.getRuntime().exec("cmd /c start chrome.exe");
										slnDisplay.setText("Opening Chrome Browser ...");
									} catch(Exception ae){}
								}
								else if(resultText.equalsIgnoreCase("close browser"))
								{	 
									userSpeak.setText("You Said: "+'\n' + resultText);
									try{
										pro = Runtime.getRuntime().exec("cmd /c start taskkill /im chrome.exe /f");
										slnDisplay.setText("Closing Chrome Browser ...");
									} catch(Exception ae){}
								}
		
								
						// site commands
								if(resultText.equalsIgnoreCase("site goo girl"))
								{	 
									userSpeak.setText("You Said: "+'\n' + resultText);
									try{
										pro = Runtime.getRuntime().exec("cmd /c start chrome www.google.com");
									} catch(Exception ae){}
								}
								else if(resultText.equalsIgnoreCase("site face book"))
								{	 
									userSpeak.setText("You Said: "+'\n' + resultText);
									try{
										pro = Runtime.getRuntime().exec("cmd /c start chrome www.facebook.com");
									} catch(Exception ae){}
								}
								else if(resultText.equalsIgnoreCase("site mail"))
								{	 
									userSpeak.setText("You Said: "+'\n' + resultText);
									try{
										pro = Runtime.getRuntime().exec("cmd /c start chrome www.gmail.com");
									} catch(Exception ae){}
								}
								else if(resultText.equalsIgnoreCase("site you tube"))
								{	 
									userSpeak.setText("You Said: "+'\n' + resultText);
									try{
										pro = Runtime.getRuntime().exec("cmd /c start chrome www.youtube.com");
									} catch(Exception ae){}
								}
								else if(resultText.equalsIgnoreCase("site tweet err"))
								{	 
									userSpeak.setText("You Said: "+'\n' + resultText);
									try{
										pro = Runtime.getRuntime().exec("cmd /c start chrome www.twitter.com");
									} catch(Exception ae){}
								}
		
								
							// MS Word	
								if(resultText.equalsIgnoreCase("open word"))
								{	 
									userSpeak.setText("You Said: "+'\n' + resultText);
									try{
										pro = Runtime.getRuntime().exec("cmd /c start winword");
									} catch(Exception ae){}
								}
								else if(resultText.equalsIgnoreCase("close word"))
								{	 
									userSpeak.setText("You Said: "+'\n' + resultText);
									try{
										pro = Runtime.getRuntime().exec("cmd /c start taskkill /im winword.exe /f");
									} catch(Exception ae){}
								}	
								
								
							// File Manager
								if(resultText.equalsIgnoreCase("open explorer"))
								{	 
									userSpeak.setText("You Said: "+'\n' + resultText);
									try{
										pro = Runtime.getRuntime().exec("cmd /c start explorer");
									} catch(Exception ae){}
								}
								else if(resultText.equalsIgnoreCase("close explorer"))
								{	 
									userSpeak.setText("You Said: "+'\n' + resultText);
									try{
										pro = Runtime.getRuntime().exec("cmd /c start taskkill /im explorer.exe /f");
									} catch(Exception ae){}
								}
								// ********************************************************************************************************************** //	
		
								
						}
							
				}
						
		
			}
				
	}
	                        

}
