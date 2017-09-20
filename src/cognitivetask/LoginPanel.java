package cognitivetask;

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


public class LoginPanel extends JPanel {
	private JLabel lblNewLabel;
	public JComboBox<Object> comboBox;
	private JButton btnPractice;
	private JButton btnProduction;



	/**
	 * Create the panel.
	 */
	public LoginPanel(MainFrame mainFrame) {
		setBackground(Color.WHITE);


		Action productionAction = mainFrame.testAction;

		lblNewLabel = new JLabel("視覚的認知課題");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 75));

		comboBox = new JComboBox<Object>();
		comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"1", "2", "3", "4", "5"}));

		btnPractice = new JButton("練習");

		btnProduction = new JButton("本番");
		btnProduction.setAction(productionAction);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(160)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(141))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(299)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnProduction, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
						.addComponent(btnPractice, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
						.addComponent(comboBox, 0, 245, Short.MAX_VALUE))
					.addGap(282))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(95)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
					.addGap(93)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(btnPractice)
					.addGap(45)
					.addComponent(btnProduction)
					.addGap(115))
		);
		setLayout(groupLayout);

	}
}
