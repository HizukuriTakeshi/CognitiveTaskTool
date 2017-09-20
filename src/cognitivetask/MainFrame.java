//package src.cognitivetask;
package cognitivetask;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class MainFrame extends JFrame {

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
	//private JButton btnNext;

	private String currentDirectry;
	private int subjectNum;
	private int itr;

	private Timer timer;
	final private int intervalTime = 1000;
	final private int showTime = 2000;
	private Timer limitTimer;
	final private int limitTime = 5000;

	long start;
	long end;

	private int panelWidth;
	private int panelHeight;
	private float mag;
	private int padding_x;
	// private float padding_y;

	List<Node> xminNode;
	List<Node> yminNode;
	List<Node> xmaxNode;
	List<Node> ymaxNode;

	List<Integer> tasks;

	public final Action testAction = new SwingAction_1();
	public final Action nextAction = new SwingAction_2();



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
		currentDirectry = new File(".").getAbsoluteFile().getParent();
		System.out.println(currentDirectry);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 836, 703);
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
				int btn = e.getButton();
				if (btn == MouseEvent.BUTTON1) {
					System.out.println("hazure");
				} else if (btn == MouseEvent.BUTTON3) {

					limitTimer.stop();

					contentPane.remove(postImagePanel);
					contentPane.add(restingPanel);
					SwingUtilities.updateComponentTreeUI(contentPane);

					end = System.currentTimeMillis();
					System.out.println((end - start) + "ms");

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

				contentPane.remove(postImagePanel);
				contentPane.add(restingPanel);
				SwingUtilities.updateComponentTreeUI(contentPane);

				end = System.currentTimeMillis();
				System.out.println((end - start) + "ms");
			}

		});

		// lblPostimage.setBounds(64, 58, 122, 95);
		// postImagePanel.add(lblPostimage);

		restingPanel = new RestingPanel(this);
		//restingPanel.setBounds(299, 422, 233, 177);
		// contentPane.add(restingPanel);
		//restingPanel.setOpaque(false);
		//restingPanel.setBackground(Color.WHITE);
		//restingPanel.setLayout(null);

		//btnNext = new JButton("next");
		//btnNext.setAction(nextAction);
		//btnNext.setBounds(55, 85, 117, 29);
		//restingPanel.add(btnNext);

		ActionListener action = new PanelChangerLogToBlank1();
		timer = new Timer(0, action);
		timer.setRepeats(false);

	}

	// タイマースタートアクション
	public class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "本番");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			panelWidth = contentPane.getWidth();
			panelHeight = contentPane.getHeight();

			itr = 0;
			subjectNum = Integer.parseInt(loginPanel.comboBox.getSelectedItem().toString());

			try {
				File f = new File(currentDirectry + "/subject/subject_" + subjectNum + ".csv");
				BufferedReader br = new BufferedReader(new FileReader(f));

				tasks = new ArrayList<Integer>();
				String line = br.readLine();
				while (line != null) {
					tasks.add(Integer.parseInt(line));
					line = br.readLine();
				}
				br.close();

				// CSVから読み込んだ配列の中身を表示
				for (int j = 0; j < tasks.size(); j++) {
					System.out.println(tasks.get(j));
				}

			} catch (IOException ea) {
				System.out.println(ea);
			}

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
			timer = new Timer(1000, action);
			timer.setRepeats(false);
			timer.restart();
		}
	}

	class PanelChangeBlank1ToPreImage implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			timer.stop();

			ImageIcon preImage = new ImageIcon(currentDirectry + "/DataSet/" + itr + "/pre_" + itr + ".png");

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
			timer = new Timer(intervalTime, action);
			timer.setRepeats(false);
			timer.restart();

		}
	}

	class PanelChangePreImageToBlank2 implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			timer.stop();

			contentPane.remove(preImagePanel);
			contentPane.add(blankPanel_2);
			SwingUtilities.updateComponentTreeUI(contentPane);

			ActionListener action = new PanelChangeBlank2ToPostImage();
			timer = new Timer(showTime, action);
			timer.setRepeats(false);
			timer.restart();

		}
	}

	class PanelChangeBlank2ToPostImage implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			timer.stop();

			SAXReader reader = new SAXReader();

			try {
				Document document = reader.read(currentDirectry + "/DataSet/" + itr + "/Post_" + itr + ".xml");
				xminNode = document.selectNodes("annotation/object/bndbox/xmin");
				yminNode = document.selectNodes("annotation/object/bndbox/ymin");
				xmaxNode = document.selectNodes("annotation/object/bndbox/xmax");
				ymaxNode = document.selectNodes("annotation/object/bndbox/ymax");
			} catch (DocumentException ea) {
				ea.printStackTrace();
			}

			int xmin = Integer.parseInt(xminNode.get(0).getText());
			int ymin = Integer.parseInt(yminNode.get(0).getText());
			int xmax = Integer.parseInt(xmaxNode.get(0).getText());
			int ymax = Integer.parseInt(ymaxNode.get(0).getText());

			System.out.println(currentDirectry + "/DataSet/" + itr + "/post_" + itr + ".png");
			ImageIcon postImage = new ImageIcon(currentDirectry + "/DataSet/" + itr + "/post_" + itr + ".png");

			Image smallPostImage = postImage.getImage().getScaledInstance((int) (mag * postImage.getIconWidth()),
					(int) (mag * postImage.getIconHeight()), Image.SCALE_SMOOTH);

			ImageIcon smallPostImageIcon = new ImageIcon(smallPostImage);

			lblPostimage.setIcon(smallPostImageIcon);
			lblPostimage.setBounds(padding_x, 0, smallPostImageIcon.getIconWidth(), smallPostImageIcon.getIconHeight());
			postImagePanel.add(lblPostimage);

			lblAnnotation.setBounds((int) (xmin * mag) + padding_x, (int) (ymin * mag), (int) ((xmax - xmin) * mag),
					(int) ((ymax - ymin) * mag));
			System.out.println((int) (xmin * mag) + padding_x + " " + (int) (ymin * mag) + " "
					+ (int) ((xmax - xmin) * mag) + " " + (int) ((ymax - ymin) * mag));
			postImagePanel.add(lblAnnotation);

			SwingUtilities.updateComponentTreeUI(postImagePanel);

			contentPane.remove(blankPanel_2);
			contentPane.add(postImagePanel);
			SwingUtilities.updateComponentTreeUI(contentPane);

			// stopwatchスタート
			start = System.currentTimeMillis();

			//制限時間スタート
			ActionListener action = new PanelChangerPostImageToRest();
			limitTimer = new Timer(limitTime, action);
			limitTimer.restart();
		}
	}

	class PanelChangerPostImageToRest implements ActionListener {

		public void actionPerformed(ActionEvent e) {
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
			itr++;
			if (itr <= tasks.size()) {
				ActionListener action = new PanelChangerRestToBlank1();
				timer = new Timer(0, action);
				timer.setRepeats(false);

				System.out.println("next");
				timer.restart();
			} else {
				itr = 0;
				ActionListener action = new PanelChangerRestToLog();
				timer = new Timer(0, action);
				timer.restart();
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
}
