package cognitivetaskGUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;


public class LoginPanel extends JPanel {
	private JLabel lblNewLabel;
	public JComboBox<Object> comboBox;
	private JButton btnPractice;
	private JButton btnProduction;
	public JComboBox<Object> comboBox_1;
	public Action productionAction;
	public Action practiceAction;



	/**
	 * Create the panel.
	 */
	public LoginPanel(MainFrame mainFrame) {
		setBackground(Color.WHITE);


		productionAction = mainFrame.testAction;
		practiceAction = mainFrame.pracAction;

		lblNewLabel = new JLabel("視覚的認知課題");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 75));

		comboBox = new JComboBox<Object>();
		comboBox.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"1", "2", "3", "4", "5"}));

		btnPractice = new JButton("練習");
		btnPractice.setFont(new Font("Lucida Grande", Font.PLAIN, 50));
		btnPractice.setAction(practiceAction);

		btnProduction = new JButton("本番");
		btnProduction.setFont(new Font("Lucida Grande", Font.PLAIN, 50));
		btnProduction.setAction(productionAction);

		comboBox_1 = new JComboBox<Object>();
		comboBox_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		comboBox_1.setModel(new DefaultComboBoxModel<Object>(new String[] {"1", "2", "3"}));


		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(160)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
					.addGap(141))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(299)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(comboBox_1, Alignment.LEADING, 0, 426, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnPractice, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
							.addGap(46)
							.addComponent(btnProduction, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
						.addComponent(comboBox, 0, 476, Short.MAX_VALUE))
					.addGap(282))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(95)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
					.addGap(40)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addGap(86)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnProduction, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
						.addComponent(btnPractice, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
					.addContainerGap())
		);
		setLayout(groupLayout);

	}
}
