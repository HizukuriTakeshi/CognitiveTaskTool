//package src.cognitivetask;
package cognitivetaskGUI;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.xml.bind.JAXB;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import xmlUtility.Click;
import xmlUtility.Result;
import xmlUtility.Task;

public class MainFrame extends JFrame {

	// Compornent
	private JPanel contentPane;
	// private JPanel loginPanel;
	private LoginPanel loginPanel;
	// private JComboBox comboBox;
	// private JPanel blankPanel_1;
	private BlankPanel blankPanel_1;
	private JPanel preImagePanel;
	// private JPanel blankPanel_2;
	private BlankPanel blankPanel_2;
	private JPanel postImagePanel;
	private RestingPanel restingPanel;
	// private JLabel loginlabel;
	// private JButton btnPractice;
	// private JButton btnTest;
	private JLabel lblPreImage;
	private JLabel lblPostimage;
	private JLabel lblAnnotation;
	// private JButton btnNext;
	private ScorePanel scorePanel;

	// File Operation
	private String currentDirectry;
	private int subjectID;
	private int partNum;
	private int itr;

	// XML io
	private Result result;
	private List<Task> tasks;
	private Task tmpTask;
	private List<Click> clicks;
	private Click tmpClick;

	// Task
	private int currentTaskID;

	// Time controll
	private Timer timer;
	final private int intervalTime = 1000;
	final private int showTime = 2000;
	private Timer limitTimer;
	final private int limitTime = 10000;

	long start;
	long end;

	// Panel Size
	private int panelWidth;
	private int panelHeight;
	private float mag;
	private int padding_x;
	// private float padding_y;

	List<Node> xminNode;
	List<Node> yminNode;
	List<Node> xmaxNode;
	List<Node> ymaxNode;
	List<Node> name;

	// task information
	public List<Integer> taskOrder;

	// score caliculate
	public long totaltime;

	public long getTotaltime() {
		return totaltime;
	}

	// music
	AudioClip correct = null;
	AudioClip incorrect = null;

	public int flag = 0;

	public void setTotaltime(long totaltime) {
		this.totaltime = totaltime;
	}

	public int getCorrectAnsNum() {
		return correctAnsNum;
	}

	public void setCorrectAnsNum(int correctAnsNum) {
		this.correctAnsNum = correctAnsNum;
	}

	public int correctAnsNum;

	public final Action testAction = new SwingAction_1();
	public final Action pracAction = new SwingAction_0();
	public final Action nextAction = new SwingAction_2();
	public final Action returnAction = new SwingAction_3();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setUndecorated(true);
		Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB), new Point(),"");
		setCursor(cursor);

		correct = Applet
				.newAudioClip(getClass().getResource("Quiz-Correct_Answer02-1 (online-audio-converter.com).wav"));
		incorrect = Applet
				.newAudioClip(getClass().getResource("Quiz-Wrong_Buzzer02-1 (online-audio-converter.com).wav"));

		currentDirectry = new File(".").getAbsoluteFile().getParent();
		System.out.println(currentDirectry);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 836, 703);
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle rect = env.getMaximumWindowBounds();
		setBounds(rect);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		loginPanel = new LoginPanel(this);
		// loginPanel.setBackground(Color.WHITE);
		contentPane.add(loginPanel);
		// loginPanel.setLayout(null);

		// loginlabel = new JLabel("変化検出課題課題");
		// loginlabel.setBounds(345, 273, 127, 16);
		// loginPanel.add(loginlabel);

		// btnPractice = new JButton("practice");
		// btnPractice.setBounds(345, 409, 127, 29);
		// loginPanel.add(btnPractice);

		// btnTest = new JButton("test");
		// btnTest.setAction(testAction);
		// btnTest.setBounds(345, 450, 127, 29);
		// loginPanel.add(btnTest);

		// comboBox = new JComboBox();
		// comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2",
		// "3", "4", "5"}));
		// comboBox.setBounds(345, 327, 127, 29);
		// loginPanel.add(comboBox);

		// blankPanel_1 = new JPanel();
		blankPanel_1 = new BlankPanel();
		// blankPanel_1.setOpaque( false ) ;
		// blankPanel_1.setBackground(Color.WHITE);
		// blankPanel_1.setBounds(0, 218, 233, 177);
		// contentPane.add(blankPanel_1);
		// blankPanel_1.setLayout(null);

		preImagePanel = new JPanel();
		preImagePanel.setOpaque(false);
		preImagePanel.setBackground(Color.WHITE);
		preImagePanel.setBounds(0, 422, 233, 177);
		// preImagePanel.setMaximumSize(new
		// Dimension(Short.MAX_VALUE,Short.MAX_VALUE));
		// contentPane.add(preImagePanel);
		preImagePanel.setLayout(null);

		lblPreImage = new JLabel("preimage");
		lblPreImage.setBounds(39, 37, 145, 107);
		preImagePanel.add(lblPreImage);

		blankPanel_2 = new BlankPanel();
		// blankPanel_2.setOpaque( false ) ;
		// blankPanel_2.setBackground(Color.WHITE);
		// blankPanel_2.setBounds(289, 21, 233, 177);
		// contentPane.add(blankPanel_2);
		// blankPanel_2.setLayout(null);

		postImagePanel = new JPanel();
		postImagePanel.setOpaque(false);
		postImagePanel.setBackground(Color.WHITE);
		postImagePanel.setBounds(289, 218, 233, 177);
		// contentPane.add(postImagePanel);
		postImagePanel.setLayout(null);
		postImagePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 4−4マス外を押させるようにする
				int btn = e.getButton();
				if (e.getX() <= padding_x || e.getX() >= panelWidth - padding_x) {
					limitTimer.stop();

					contentPane.remove(postImagePanel);
					contentPane.add(restingPanel);
					SwingUtilities.updateComponentTreeUI(contentPane);

					end = System.currentTimeMillis();
					// long time = end - start;
					long time = end;
					//System.out.println(time + "ms");

					tmpClick = new Click();
					tmpClick.setTime(time);
					// ここをなんとかする
					if (!name.get(0).getText().equals("None")) {
						tmpClick.setTorf(false);
						//System.out.println(name.get(0).getText());
						System.out.println("hhazure");
						incorrect.play();
						// totaltime += time;
						tmpTask.setEndPostTime(System.currentTimeMillis());
					} else {
						tmpClick.setTorf(true);
						System.out.println("sseikai");

						correct.play();

						// totaltime += time;
						correctAnsNum++;
						tmpTask.setEndPostTime(System.currentTimeMillis());
					}
					tmpClick.setX(e.getX() - padding_x);
					tmpClick.setY(e.getY());

					clicks.add(tmpClick);

				} else if (btn == MouseEvent.BUTTON1) {
					System.out.println("hazure");
					incorrect.play();
					end = System.currentTimeMillis();
					long time = end;
					Date date = new Date(time);
					DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
					//System.out.println(formatter.format(date));

					tmpClick = new Click();
					tmpClick.setTime(time);
					tmpClick.setTorf(false);
					tmpClick.setX(e.getX() - padding_x);
					tmpClick.setY(e.getY());
					clicks.add(tmpClick);

				} else if (btn == MouseEvent.BUTTON3) {

				}
			}
		});

		lblPostimage = new JLabel("");

		lblAnnotation = new JLabel("");
		lblAnnotation.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("seikai");

				limitTimer.stop();
				correct.play();
				contentPane.remove(postImagePanel);
				contentPane.add(restingPanel);
				SwingUtilities.updateComponentTreeUI(contentPane);

				end = System.currentTimeMillis();
				tmpTask.setEndPostTime(System.currentTimeMillis());

				// long time = end - start;
				long time = end;
				//System.out.println(time + "ms");
				// totaltime += time;
				correctAnsNum++;

				tmpClick = new Click();
				tmpClick.setTime(time);
				tmpClick.setTorf(true);
				tmpClick.setX(lblAnnotation.getX()+e.getX());
				tmpClick.setY(lblAnnotation.getY()+e.getY());

				clicks.add(tmpClick);
			}

		});

		// lblPostimage.setBounds(64, 58, 122, 95);
		// postImagePanel.add(lblPostimage);

		restingPanel = new RestingPanel(this);
		// restingPanel.setBounds(299, 422, 233, 177);
		// contentPane.add(restingPanel);
		// restingPanel.setOpaque(false);
		// restingPanel.setBackground(Color.WHITE);
		// restingPanel.setLayout(null);

		// btnNext = new JButton("next");
		// btnNext.setAction(nextAction);
		// btnNext.setBounds(55, 85, 117, 29);
		// restingPanel.add(btnNext);

		scorePanel = new ScorePanel(this);

		ActionListener action = new PanelChangerLogToBlank1();
		timer = new Timer(0, action);
		timer.setRepeats(false);

	}

	public class SwingAction_0 extends AbstractAction {
		public SwingAction_0() {
			putValue(NAME, "練習");
			putValue(SHORT_DESCRIPTION, "Some short description");
			flag = 0;
		}

		public void actionPerformed(ActionEvent e) {
			// ウィンドウサイズ取得
			panelWidth = contentPane.getWidth();
			panelHeight = contentPane.getHeight();

			itr = 0;
			totaltime = 1;
			correctAnsNum = 0;
			subjectID = 0;
			partNum = 0;

			try {
				File f = new File(currentDirectry + "/subject/practice.csv");
				BufferedReader br = new BufferedReader(new FileReader(f));

				taskOrder = new ArrayList<Integer>();
				String line = br.readLine();
				while (line != null) {
					taskOrder.add(Integer.parseInt(line));
					line = br.readLine();
				}
				br.close();

				// CSVから読み込んだ配列の中身を表示
				// for (int j = 0; j < tasks.size(); j++) {
				// System.out.println(tasks.get(j));
				// }

			} catch (IOException ea) {
				System.out.println(ea);
			}

			// 結果出力用のクラスインスタンス
			result = new Result();
			result.setSubjectID(subjectID);
			tasks = new ArrayList<Task>();
			clicks = new ArrayList<Click>();

			ActionListener action = new PanelChangerLogToBlank1();
			timer = new Timer(0, action);
			timer.setRepeats(false);

			timer.restart();
		}
	}

	// タイマースタートアクション
	public class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "本番");
			putValue(SHORT_DESCRIPTION, "Some short description");
			flag = 1;
		}

		public void actionPerformed(ActionEvent e) {

			// ウィンドウサイズ取得
			panelWidth = contentPane.getWidth();
			panelHeight = contentPane.getHeight();

			itr = 0;
			totaltime = 1;
			correctAnsNum = 0;
			subjectID = Integer.parseInt(loginPanel.comboBox.getSelectedItem().toString());
			partNum = Integer.parseInt(loginPanel.comboBox_1.getSelectedItem().toString());

			try {
				File f = new File(currentDirectry + "/subject/subject_" + subjectID + "_" + +partNum + ".csv");
				BufferedReader br = new BufferedReader(new FileReader(f));

				taskOrder = new ArrayList<Integer>();
				String line = br.readLine();
				while (line != null) {
					taskOrder.add(Integer.parseInt(line));
					line = br.readLine();
				}
				br.close();

				// CSVから読み込んだ配列の中身を表示
				// for (int j = 0; j < tasks.size(); j++) {
				// System.out.println(tasks.get(j));
				// }

			} catch (IOException ea) {
				System.out.println(ea);
			}

			// 結果出力用のクラスインスタンス
			result = new Result();
			result.setSubjectID(subjectID);
			tasks = new ArrayList<Task>();
			clicks = new ArrayList<Click>();

			ActionListener action = new PanelChangerLogToBlank1();
			timer = new Timer(0, action);
			timer.setRepeats(false);


			timer.restart();
		}
	}

	class PanelChangerLogToBlank1 implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			timer.stop();

			contentPane.remove(loginPanel);
			contentPane.add(blankPanel_1);
			SwingUtilities.updateComponentTreeUI(contentPane);

			ActionListener action = new PanelChangeBlank1ToPreImage();
			timer = new Timer(intervalTime, action);
			timer.setRepeats(false);
			timer.restart();
		}
	}

	class PanelChangeBlank1ToPreImage implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			currentTaskID = taskOrder.get(itr);
			tmpTask = new Task();
			tmpTask.setTaskID(currentTaskID);

			timer.stop();
			tmpTask.setStartPreTime(System.currentTimeMillis());
			//System.out.println("pre画像");

			ImageIcon preImage = new ImageIcon(
					currentDirectry + "/DataSet/" + currentTaskID + "/pre_" + currentTaskID + ".jpg");
			System.out.println("現在の課題ID" + currentTaskID);

			float maxSize = Math.max(preImage.getIconHeight(), preImage.getIconWidth());
			mag = (float) (Math.min(panelWidth, panelHeight)) / maxSize;
			padding_x = (int) ((panelWidth - mag * preImage.getIconWidth()) / 2);

			Image smallPreImage = preImage.getImage().getScaledInstance((int) (mag * preImage.getIconWidth()),
					(int) (mag * preImage.getIconHeight()), Image.SCALE_SMOOTH);

			ImageIcon smallPreImageIcon = new ImageIcon(smallPreImage);
			lblPreImage.setIcon(smallPreImageIcon);
			lblPreImage.setBounds(padding_x, 0, smallPreImageIcon.getIconWidth(), smallPreImageIcon.getIconHeight());

			preImagePanel.add(lblPreImage);

			SwingUtilities.updateComponentTreeUI(preImagePanel);

			contentPane.remove(blankPanel_1);
			contentPane.add(preImagePanel);
			SwingUtilities.updateComponentTreeUI(contentPane);

			ActionListener action = new PanelChangePreImageToBlank2();
			timer = new Timer(showTime, action);
			timer.setRepeats(false);
			timer.restart();

		}
	}

	class PanelChangePreImageToBlank2 implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			timer.stop();

			tmpTask.setEndPreTime(System.currentTimeMillis());
			//System.out.println("pre画像終了");
			contentPane.remove(preImagePanel);
			contentPane.add(blankPanel_2);
			SwingUtilities.updateComponentTreeUI(contentPane);
			ActionListener action = new PanelChangeBlank2ToPostImage();
			timer = new Timer(intervalTime, action);
			timer.setRepeats(false);
			timer.restart();

		}
	}

	class PanelChangeBlank2ToPostImage implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			timer.stop();

			SAXReader reader = new SAXReader();

			try {
				Document document = reader
						.read(currentDirectry + "/DataSet/" + currentTaskID + "/Post_" + currentTaskID + ".xml");

				xminNode = document.selectNodes("annotation/object/bndbox/xmin");
				yminNode = document.selectNodes("annotation/object/bndbox/ymin");
				xmaxNode = document.selectNodes("annotation/object/bndbox/xmax");
				ymaxNode = document.selectNodes("annotation/object/bndbox/ymax");
				name = document.selectNodes("annotation/object/name");
			} catch (DocumentException ea) {
				ea.printStackTrace();
			}

			int xmin = Integer.parseInt(xminNode.get(0).getText());
			int ymin = Integer.parseInt(yminNode.get(0).getText());
			int xmax = Integer.parseInt(xmaxNode.get(0).getText());
			int ymax = Integer.parseInt(ymaxNode.get(0).getText());

			// System.out.println(currentDirectry + "/DataSet/" + itr + "/post_"
			// + itr + ".png");
			ImageIcon postImage = new ImageIcon(
					currentDirectry + "/DataSet/" + currentTaskID + "/post_" + currentTaskID + ".jpg");

			Image smallPostImage = postImage.getImage().getScaledInstance((int) (mag * postImage.getIconWidth()),
					(int) (mag * postImage.getIconHeight()), Image.SCALE_SMOOTH);

			ImageIcon smallPostImageIcon = new ImageIcon(smallPostImage);

			lblPostimage.setIcon(smallPostImageIcon);
			lblPostimage.setBounds(padding_x, 0, smallPostImageIcon.getIconWidth(), smallPostImageIcon.getIconHeight());
			postImagePanel.add(lblPostimage);

			lblAnnotation.setBounds((int) (xmin * mag) + padding_x, (int) (ymin * mag), (int) ((xmax - xmin) * mag),
					(int) ((ymax - ymin) * mag));
			// System.out.println((int) (xmin * mag) + padding_x + " " + (int)
			// (ymin * mag) + " "+ (int) ((xmax - xmin) * mag) + " " + (int)
			// ((ymax - ymin) * mag));
			postImagePanel.add(lblAnnotation);

			SwingUtilities.updateComponentTreeUI(postImagePanel);

			contentPane.remove(blankPanel_2);
			contentPane.add(postImagePanel);
			SwingUtilities.updateComponentTreeUI(contentPane);

			// stopwatchスタート
			start = System.currentTimeMillis();
			tmpTask.setStartPostTime(System.currentTimeMillis());
			//System.out.println("post画像開始");

			// 制限時間スタート
			ActionListener action = new PanelChangerPostImageToRest();
			limitTimer = new Timer(limitTime, action);
			limitTimer.restart();
		}
	}

	class PanelChangerPostImageToRest implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			tmpTask.setEndPostTime(System.currentTimeMillis());
			//System.out.println("post終了");
			limitTimer.stop();

			contentPane.remove(postImagePanel);
			contentPane.add(restingPanel);
			SwingUtilities.updateComponentTreeUI(contentPane);
		}
	}

	class PanelChangerRestToBlank1 implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			timer.stop();

			contentPane.remove(restingPanel);
			contentPane.add(blankPanel_1);
			SwingUtilities.updateComponentTreeUI(contentPane);

			ActionListener action = new PanelChangeBlank1ToPreImage();
			timer = new Timer(intervalTime, action);
			timer.setRepeats(false);
			timer.restart();
		}
	}

	// Next
	public class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "次の問題へ");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {

			tmpTask.setClicks(clicks);
			tasks.add(tmpTask);
			clicks = null;
			clicks = new ArrayList<Click>();
			itr++;
			//System.out.println(itr);
			//System.out.println(taskOrder.size());
			//System.out.println(itr < taskOrder.size());
			if (itr < taskOrder.size()) {
				ActionListener action = new PanelChangerRestToBlank1();
				timer = new Timer(0, action);
				timer.setRepeats(false);

				// System.out.println("next");
				timer.restart();
			} else {
				itr = 0;
				scorePanel.changeScoreText(totaltime, correctAnsNum);

				ActionListener action = new PanelChangerRestToScore();
				//System.out.println("correctAnsNum:" + correctAnsNum);
				timer = new Timer(0, action);
				//新しく入れた
				timer.setRepeats(false);
				timer.restart();

				result.setTasks(tasks);
				// XML出力
				JAXB.marshal(result, System.out);

				try {
					JAXB.marshal(result, new FileOutputStream(
							currentDirectry + "/Result/subuject_" + subjectID + "_" + partNum + ".xml"));
				} catch (FileNotFoundException ea) {
					// TODO Auto-generated catch block
					ea.printStackTrace();
				}
			}

		}
	}

	class PanelChangerRestToLog implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			timer.stop();

			contentPane.remove(restingPanel);
			contentPane.add(loginPanel);
			SwingUtilities.updateComponentTreeUI(contentPane);
		}
	}

	class PanelChangerRestToScore implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			timer.stop();

			contentPane.remove(restingPanel);
			contentPane.add(scorePanel);
			SwingUtilities.updateComponentTreeUI(contentPane);
		}
	}

	//returnAction
	public class SwingAction_3 extends AbstractAction {
		public SwingAction_3() {
			putValue(NAME, "タイトル画面へ");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {

			 ActionListener action_ScoreToLog = new PanelChangerScoreToLog();
			 timer = new Timer(0, action_ScoreToLog);
			 timer.setRepeats(false);
			 timer.restart();
		}
	}

	class PanelChangerScoreToLog implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			timer.stop();
			contentPane.remove(scorePanel);
			contentPane.add(loginPanel);
			SwingUtilities.updateComponentTreeUI(contentPane);
		}
	}

}
