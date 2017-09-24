package cognitivetaskGUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ScorePanel extends JPanel {
	private JLabel label;
	private JLabel label_1;
	private JButton btnReturn;


	/**
	 * Create the panel.
	 */
	public ScorePanel(MainFrame mainFrame) {
		setBackground(Color.WHITE);
		Action returnAction = mainFrame.returnAction;



		label = new JLabel("あなたの得点は");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 50));

		label_1 = new JLabel(0 +"点です");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);

		label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 99));

		btnReturn = new JButton("タイトルに戻る");
		btnReturn.setAction(returnAction);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(272)
					.addComponent(label, GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
					.addGap(310))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(419)
					.addComponent(btnReturn, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
					.addGap(427))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(61)
					.addComponent(label_1, GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
					.addGap(117))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(174)
					.addComponent(label, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
					.addGap(62)
					.addComponent(label_1)
					.addGap(92)
					.addComponent(btnReturn, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
					.addGap(58))
		);
		setLayout(groupLayout);

	}

	public void changeScoreText(long totaltime, int correctAnsNum){
		label_1.setText(totaltime/correctAnsNum/1000 +"点");
	}

}
