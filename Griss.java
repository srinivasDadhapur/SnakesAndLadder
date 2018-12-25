import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Griss extends JFrame {

	final static int width = 1270;
	static final int height = 700;

	public static void main(String[] args) throws IOException {
		MainBoard board = new MainBoard();
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.setSize(width, height);
		board.setResizable(false);
		board.setUndecorated(true);
		board.setVisible(true);
	}
}

class MainBoard extends JFrame {
	private JButton RollButton, QuitButton, newgame;
	private JTextArea log;
	private JPanel FirstCorner, MainPanel = new JPanel(new BorderLayout()), LeftCenter;
	private JPanel NamePanel, WestPanel = new JPanel(new BorderLayout());
	private JPanel anoth = new JPanel(new GridLayout(2, 2)),EastPanel = new JPanel();
	private JScrollPane scrollpane;
	private JLabel spid, player1, player2, ft = new JLabel("Rolls");
	
	
	private JLabel dice, desk1, name, desk2, label[] = new JLabel[101];
	private Icon dices[] = new Icon[7],mix, Desk1Image, Desk2Image;
	

	ArrayList<Integer> SnakeHead = new ArrayList<Integer>(Arrays.asList(98, 95,92, 83, 64, 68, 69, 52, 59, 44, 46, 48));
	int SnakeTail[] = { 13, 37, 51, 22, 24, 2, 33, 11, 18, 22, 15, 9 };
	ArrayList<Integer> LadderTail = new ArrayList<Integer>(Arrays.asList(8, 19,28, 21, 36, 43, 50, 54, 62, 66, 80));
	int LadderHead[] = { 26, 38, 53, 82, 57, 77, 91, 88, 96, 87, 99 };

	public MainBoard() throws IOException {
		super("Doraeman The flying Robo");
		JPanel CenterPanel = new JPanelW("snaa.png");
		CenterPanel.setLayout(new GridLayout(10,10));
		for (int i = 100; i >= 1; i--) {
			label[i] = new JLabel();
		}
		for (int i = 9; i >= 0; i--) {
			for (int j = 0; j < 10; j++) {
				if (i % 2 == 0) {
					CenterPanel.add(label[(i * 10) + j + 1]);
				} else {
					CenterPanel.add(label[(i * 10) + (10 - j)]);
				}
			}
		}
		CenterPanel.setPreferredSize(new Dimension(800, 700));
		CenterPanel.setBorder(BorderFactory.createLineBorder(new Color(58,93,57), 12));
		//CenterPanel.setBackground(new Color(58,93,57));

		JLabel las = new JLabel("<html><font color='blue' size='6'>"+ "Log Details" + "</font> </html>");
		log = new JTextArea("History :\n\n", 36, 13);
		log.setEditable(false);
		log.setFont(new Font("Vemana200", Font.ITALIC, 13));
		log.setForeground(new Color(58,93,57));
		
		mix = new ImageIcon(getClass().getClassLoader().getResource("mix.png"));

		EastPanel.setPreferredSize(new Dimension(230, 100));
		ForAction ActionVar = new ForAction();
		RollButton = new JButton("Roll");
		RollButton.addActionListener(ActionVar);
		RollButton.setEnabled(false);
		RollButton.setPreferredSize(new Dimension(134, 134));
		QuitButton = new JButton("    Quit  ");
		QuitButton.addActionListener(ActionVar);

		newgame = new JButton("New Game");
		newgame.addActionListener(ActionVar);

		FirstCorner = new JPanel(new BorderLayout());
		FirstCorner.add(QuitButton, BorderLayout.WEST);
		FirstCorner.add(newgame, BorderLayout.EAST);
		FirstCorner.add(RollButton, BorderLayout.SOUTH);
		FirstCorner.setBorder(BorderFactory.createLineBorder(Color.blue, 1));

		ft.setPreferredSize(new Dimension(123, 134));

		Desk1Image = new ImageIcon(getClass().getClassLoader().getResource("images/blue.png"));
		Desk2Image = new ImageIcon(getClass().getClassLoader().getResource("images/pink.png"));
		desk1 = new JLabel();
		desk1.setIcon(Desk1Image);

		desk2 = new JLabel();
		desk2.setIcon(Desk2Image);

		dice = new JLabel();
		anoth.add(desk1);
		anoth.add(desk2);
		anoth.setBackground(new Color(119,200,132));

		LeftCenter = new JPanel();
		LeftCenter.setBackground(new Color(119,200,132));
		for(int u=0;u<7;u++){
		dices[u] = new ImageIcon(getClass().getClassLoader().getResource("dice/"+u+".png"));
		}
		dice.setIcon(dices[0]);
		player1 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("images/blue.png")));
		player1.setText("Player 1");
		player2 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("images/pink.png")));
		player2.setText("Player 2");
		player1.setPreferredSize(new Dimension(148, 48));
		player2.setPreferredSize(new Dimension(148, 48));
		LeftCenter.add(dice);
		LeftCenter.add(player1);
		LeftCenter.add(player2);

		WestPanel.add(FirstCorner, BorderLayout.BEFORE_FIRST_LINE);
		WestPanel.add(LeftCenter, BorderLayout.CENTER);
		WestPanel.setPreferredSize(new Dimension(200, 70));
		WestPanel.setBackground(Color.GREEN);
		WestPanel.add(anoth, BorderLayout.SOUTH);
		WestPanel.setBorder(BorderFactory.createLineBorder(new Color(58,93,57), 3));

		scrollpane = new JScrollPane(log);
		scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		EastPanel.add(las);
		EastPanel.add(scrollpane);
		EastPanel.setBorder(BorderFactory.createLineBorder(new Color(58,93,57), 3));
		name = new JLabel("Designed and Developed by Srinivas Dadhapur");
		name.setFont(new Font("Serif",Font.ITALIC,12));
		name.setForeground(Color.blue);
		NamePanel = new JPanel();
		NamePanel.add(name);
		NamePanel.setBackground(Color.gray);
		MainPanel.add(CenterPanel, BorderLayout.CENTER);
		MainPanel.add(WestPanel, BorderLayout.WEST);
		MainPanel.add(EastPanel, BorderLayout.EAST);
		MainPanel.add(NamePanel, BorderLayout.SOUTH);
		add(MainPanel);
	}


	public int roll() {
		Random random = new Random();
		int rand = 1 + random.nextInt(6);
		return rand;
	}

	public int Coin() {
		Random random = new Random();
		int face = random.nextInt(2);
		return face;
	}

	class ForAction implements ActionListener {

		int sum1 = 0, sum2 = 0, rr = 0, tem = 0;
		boolean state = (Coin() == 1) ? true : false;

		public void actionPerformed(ActionEvent ev) {
			int i = roll();
			if (ev.getSource() == QuitButton) {
				Object choic[] = { "Quit", "No" };
				if ((JOptionPane.showOptionDialog(null,
						"Are You Really Wanna Exit", "Confirmation",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, choic, choic[0])) == 0)
					System.exit(0);
			}
			if (ev.getSource() == newgame) {
				if (sum1 > 0 || sum2 > 0) {
					Object gem[] = { "Leave", "No" };
					if ((JOptionPane.showOptionDialog(null,
							"Are You Really wanna leave this game",
							"confirmation", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, gem, gem[0])) == 0) {
						if (sum1 > 0) {
							label[sum1].setIcon(null);
						}
						if (sum2 > 0) {
							label[sum2].setIcon(null);
						}

						sum1 = 0;
						sum2 = 0;
						desk1.setIcon(Desk1Image);
						dice.setIcon(dices[0]);
						desk2.setIcon(Desk2Image);
						log.setText("History : \n\n");
					}
				} else {
					RollButton.setEnabled(true);
				}
			}
			if (ev.getSource() == RollButton) {
				dice.setIcon(dices[i]);
				ft.setText("<html><font size=43 color='red'>" + i+ "</font></html>");
				if (state) {
					state = false;
					desk1.setIcon(null);
					desk1.setText("");

					sum1 = MakeChangeInBoard(sum1, i, Desk1Image, "Player1");
					if (sum1 == 100) {
						state = true;
					}
					if (sum1 == 0) {
						label[sum2].setIcon(null);
						sum2 = 0;
					}
				} else if (state == false) {
					state = true;
					desk2.setIcon(null);
					desk2.setText("");

					sum2 = MakeChangeInBoard(sum2, i, Desk2Image, "Player2");
					if (sum2 == 100) {
						state = false;
					}
					if (sum2 == 0) {
						label[sum1].setIcon(null);
						sum1 = 0;
					}
				}
			}
		}

	}

	int temsum = 0;

	public int MakeChangeInBoard(int sum, int i, Icon x, String player) {
		boolean bul = true, tem = true, pam = true, mixb = true;
		if (temsum == sum && temsum != 0) {
			tem = false;
			if (x == Desk1Image) {
				label[sum].setIcon(Desk2Image);
			}
			if (x == Desk2Image) {
				label[sum].setIcon(Desk1Image);
			}
		}
		if (SnakeHead.contains(sum + i)) {
			JOptionPane.showMessageDialog(null, player + " Snake Bite at"+ (sum + i),"GoneDown",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(getClass().getClassLoader().getResource("sad.png")));
			if ((x == Desk1Image) && temsum == sum && temsum != 0) {
				label[sum].setIcon(Desk2Image);
			}
			if ((x == Desk2Image) && temsum == sum && temsum != 0) {
				label[sum].setIcon(Desk1Image);
			} else if (temsum != sum && temsum != 0) {
				label[sum].setIcon(null);
			}
			log.append("\n" + player + "Snake bite at " + (sum + i));
			sum = SnakeTail[SnakeHead.indexOf(sum + i)];
			if (temsum == sum) {
				label[sum].setIcon(mix);
			} else {
				label[sum].setIcon(x);
			}
			bul = false;
			mixb = false;
			tem = false;
			pam = false;
		}
		if (LadderTail.contains(sum + i) && pam) {
			JOptionPane.showMessageDialog(null, player + " Ladder at"+ (sum + i),"GoneUp",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(getClass().getClassLoader().getResource("happy.png")));
			if ((x == Desk1Image) && temsum == sum && temsum != 0) {
				label[sum].setIcon(Desk2Image);
			}
			if ((x == Desk2Image) && temsum == sum && temsum != 0) {
				label[sum].setIcon(Desk1Image);
			} else if (temsum != sum && temsum != 0) {
				label[sum].setIcon(null);
			}
			log.append("\n" + player + "Ladder at" + (sum + i));
			sum = LadderHead[LadderTail.indexOf(sum + i)];
			if (temsum == sum) {
				label[sum].setIcon(mix);
				mixb = false;
				tem = false;
			} else {
				label[sum].setIcon(x);
			}
			bul = false;
		}
		if (sum > 0 && tem) {
			label[sum].setIcon(null);
		}
		if (((sum + i) <= 100) && bul) {
			if (sum < 100) {
				sum = sum + i;
				if (temsum != sum) {
					label[sum].setIcon(x);
				} else if (temsum == sum && temsum != 0) {
					label[sum].setIcon(mix);
				}
			}
			if (sum == 100) {
				int option = JOptionPane.showConfirmDialog(null, player
						+ " Won \n Wanna restart");
				if (option == 0) {
					desk1.setIcon(Desk1Image);
					desk2.setIcon(Desk2Image);

					desk2.setText("Player 1");
					label[sum].setIcon(null);
					log.setText("History :\n\n");
					sum = 0;
					dice.setIcon(dices[0]);
				}
				if (option == 1 || option == 2) {
					RollButton.setEnabled(false);
				}
				dice.setIcon(dices[0]);
			}
		} else if (mixb) {
			label[sum].setIcon(x);
		}
		if (sum != 0)
			log.append("\n" + player + " at " + (sum));
		temsum = sum;
		return sum;
	}
}
class JPanelW extends JPanel {

	  private Image backgroundImage;

	  // Some code to initialize the background image.
	  // Here, we use the constructor to load the image. This
	  // can vary depending on the use case of the panel.
	  public JPanelW(String fileName) throws IOException {
	    backgroundImage = ImageIO.read(getClass().getClassLoader().getResource(fileName));
	  }

	  public void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    // Draw the background image.
	    g.drawImage(backgroundImage, 0, 0, this);
	  }
	}
