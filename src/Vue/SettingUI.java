package Vue;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class SettingUI extends JFrame {

	private JPanel contentPane;

	public SettingUI(MenuFrame menuFrame) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBounds(39, 27, 565, 470);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("General", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("IA....");
		lblNewLabel.setBounds(56, 35, 126, 36);
		panel.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"ia 1", "ia 2", "ia3", "ia4"}));
		comboBox.setBounds(56, 126, 126, 36);
		panel.add(comboBox);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("IA", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblSecond = new JLabel("SECOND");
		lblSecond.setBounds(104, 87, 188, 67);
		panel_1.add(lblSecond);
		
		JButton btnReturn = new JButton("RETURN");
		btnReturn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				menuFrame.setVisible(true);
				setVisible(false);
			}
		});
		btnReturn.setBounds(216, 401, 93, 23);
		panel_1.add(btnReturn);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("AFF", null, panel_2, null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Con", null, panel_3, null);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Bons", null, panel_4, null);
		this.setLocationRelativeTo(null);
	}
}
