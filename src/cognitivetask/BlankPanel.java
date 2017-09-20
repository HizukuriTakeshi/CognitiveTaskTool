package cognitivetask;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BlankPanel extends JPanel {
	private JLabel lblPlus;

	/**
	 * Create the panel.
	 */
	public BlankPanel() {
		setBorder(null);
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		
		lblPlus = new JLabel("+");
		lblPlus.setFont(new Font("Lucida Grande", Font.BOLD, 99));
		lblPlus.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPlus);
	}

}
