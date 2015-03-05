package gui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class EncryptionFrame extends JFrame implements ActionListener {
	
    private JTextField shiftFactor;
    private JTextArea inputTA;
    private JTextArea outputTA;
    
    public static void main(String[] args) {
        new EncryptionFrame().setVisible(true);
    }
	
	public EncryptionFrame() {
		setTitle("Encryption");
        setVisible(true);
        setDefaultCloseOperation(3);

        Container content = getContentPane();
        GridLayout layout = new GridLayout(3, 0, 0, 10);
        content.setLayout(layout);

        inputTA = new JTextArea("Insert the text to be encrypted/decrypted here, then press the appropriate button.", 12, 40);
        inputTA.setLineWrap(true);
        inputTA.setWrapStyleWord(true);
        inputTA.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        JScrollPane scroller = new JScrollPane(inputTA);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        content.add(scroller);
       
        outputTA = new JTextArea("Output text.",12, 40);
        outputTA.setLineWrap(true);
        outputTA.setWrapStyleWord(true);
        outputTA.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        JScrollPane scroller2 = new JScrollPane(outputTA);
        scroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        content.add(scroller2);
       
        JPanel box1 = new JPanel();
        box1.setLayout(new FlowLayout());
        JButton decryptButton = new JButton("Decrypt");
        JButton encryptButton = new JButton("Encrypt");
        decryptButton.addActionListener(this);
        encryptButton.addActionListener(this);
        box1.add(decryptButton);
        box1.add(encryptButton);
        box1.add(new JLabel("Shift Factor"));
        box1.add(this.shiftFactor = new JTextField(20));
        content.add(box1);
       
        pack();
	}
	
	private void encryptText() {
		String inputText = this.inputTA.getText();
		
		char[] charArray = inputText.toCharArray();
		
		long shift = 0;
		
		if (!shiftFactor.getText().equals("")) {
			shift = Integer.parseInt(shiftFactor.getText());
		}
		
		for (int i = 0; i < charArray.length; i++) {
			shift %= 128;
			charArray[i] += shift;
		}
		
		outputTA.setText(new String(charArray));
	}
	
	private void decryptText() {
		String inputText = this.inputTA.getText();
		
		char[] charArray = inputText.toCharArray();
		
		long shift = 0;
		
		if (!shiftFactor.getText().equals("")) {
			shift = Integer.parseInt(shiftFactor.getText());
		}
		
		for (int i = 0; i < charArray.length; i++) {
			shift %= 128;
			charArray[i] -= shift;
		}
		
		outputTA.setText(new String(charArray));
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Encrypt")) {
			encryptText();
		} else if (e.getActionCommand().equals("Decrypt")) {
			decryptText();
		}
	}

}
