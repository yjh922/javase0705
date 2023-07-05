package org.sp.app0705.copy;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GuiCopy extends JFrame implements ActionListener{
	JLabel la_ori, la_dest;
	JTextField t_ori, t_dest;
	JButton bt;
	
	public GuiCopy() {
		la_ori = new JLabel("원본위치");
		la_dest = new JLabel("복사위치");
		t_ori = new JTextField();
		t_dest = new JTextField();
		bt = new JButton("복사실행");
		
		//라벨의 크기
		la_ori.setPreferredSize(new Dimension(100,30));
		la_dest.setPreferredSize(new Dimension(100,30));
		//텍스트필드 크기
		t_ori.setPreferredSize(new Dimension(365,30));
		t_dest.setPreferredSize(new Dimension(365,30));
		
		setLayout(new FlowLayout());
		
		add(la_ori);
		add(t_ori);
		add(la_dest);
		add(t_dest);
		add(bt);
		
		setSize(500,170);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);//윈도우를 화면 가운데 오게
		
		bt.addActionListener(this);//버튼과 리스너 연결
	}
	
	//파일복사
	public void copy() {
		FileInputStream fis=null;
		FileOutputStream fos=null;
		
		//지정한 경로의 원본파일에 입력스트림 생성하기
		try {
			fis = new FileInputStream(t_ori.getText());
			fos = new FileOutputStream(t_dest.getText());
			
			//생성된 입력스트림으로부터 데이터 읽기
			int data=-1;
			
			while(true) {
				data=fis.read();//1byte 읽기
				if(data==-1)break;
				fos.write(data);//출력스트림에 데이터 출력
				System.out.print((char)data);
			}
			
			JOptionPane.showMessageDialog(this, "복사완료");
			
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			JOptionPane.showMessageDialog(this, "파일이 존재하지 않습니다");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fos!=null) {//존재할때만
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fis!=null) {//인스턴스가 존재할때만
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		copy();
	}

	public static void main(String[] args) {
		new GuiCopy();
	}
}
