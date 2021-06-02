package Vue;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MenuFrame extends JFrame {

	private JPanel contentPane;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuFrame frame = new MenuFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MenuFrame() {
		SettingUI frame = new SettingUI(me());
		frame.setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 619, 562);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button_0 = new JButton("jouer en local");
		button_0.setBounds(211, 153, 93, 23);
		contentPane.add(button_0);
		
		JButton button_1 = new JButton("jouer en ligne ");
		button_1.setBounds(211, 217, 93, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("configuration");
		button_2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(true);
				setVisible(false);
			}
		});
		button_2.setBounds(211, 289, 93, 23);
		contentPane.add(button_2);
		this.setLocationRelativeTo(null);

		JButton button_3 = new JButton("regle du jeu");
		button_3.setBounds(211,350 , 93, 23);
		contentPane.add(button_3);
		this.setLocationRelativeTo(null);
	}
	public MenuFrame me(){
		return this;
	}

}
