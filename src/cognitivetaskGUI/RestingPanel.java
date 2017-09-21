package cognitivetaskGUI;

import java.awt.Color;

import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;

public class RestingPanel extends JPanel {
	private JButton btnNext;

	/**
	 * Create the panel.
	 */
	public RestingPanel(MainFrame mainFrame) {
		setBackground(Color.WHITE);
		Action nextAction = mainFrame.nextAction;

		btnNext = new JButton("次へ");
		btnNext.setAction(nextAction);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(334)
					.addComponent(btnNext, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
					.addGap(321))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(283)
					.addComponent(btnNext, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
					.addGap(273))
		);
		setLayout(groupLayout);

	}
}
