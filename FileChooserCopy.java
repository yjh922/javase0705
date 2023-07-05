package org.sp.app0705.copy;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FileChooserCopy extends JFrame implements ActionListener{
	JLabel la_ori, la_dest;
	JTextField t_ori, t_dest;
	JButton bt;
	JButton bt_find;//원본파일 찾기
	JButton bt_sel;//보사될 디렉토리 위치 지정
	JFileChooser chooser;
	
	public FileChooserCopy() {
		la_ori = new JLabel("원본위치");
		la_dest = new JLabel("복사위치");
		t_ori = new JTextField();
		t_dest = new JTextField();
		bt = new JButton("복사실행");
		bt_find = new JButton("파일찾기");
		bt_sel = new JButton("경로지정");//디렉토리 경로만
		chooser = new JFileChooser();
		
		//라벨의 크기
		la_ori.setPreferredSize(new Dimension(100,30));
		la_dest.setPreferredSize(new Dimension(100,30));
		//텍스트필드 크기
		t_ori.setPreferredSize(new Dimension(365,30));
		t_dest.setPreferredSize(new Dimension(365,30));
		
		setLayout(new FlowLayout());
		
		add(la_ori);
		add(t_ori);
		add(bt_find);
		add(la_dest);
		add(t_dest);
		add(bt_sel);
		add(bt);
		
		setSize(585,170);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);//윈도우를 화면 가운데 오게
		
		//버튼들과 리스너 연결
		bt_find.addActionListener(this);
		bt_sel.addActionListener(this);
		bt.addActionListener(this);
	}
	
	public void openOriginal() {
		int result=chooser.showOpenDialog(this);
		//System.out.println("탐색기창에서 유저가 선택한 결과는"+result);
		
		if(result==JFileChooser.APPROVE_OPTION) {//확인버튼을 무르면
			System.out.println("파일을 선택했군요");
			//유저가 선택한 파일의 디렉토리 full 경로 및 파일명이 출력되게처리
			File file=chooser.getSelectedFile();
			String path=file.getAbsolutePath();
			//텍스트필드에 채워넣기
			t_ori.setText(path);
			
		}
	}
	
	//복사될 파일의 디텍토리 위치를 탐색기로 찾아보기
	public void openDest() {
		int result=chooser.showSaveDialog(this);
		if(result==JFileChooser.APPROVE_OPTION) {
			//사용자가 입력한 파일명과  탐색기의 디렉토리를 조합한 풀 경로를 얻자
			File file=chooser.getSelectedFile();
			
			System.out.println(file.getAbsolutePath());
			
			t_dest.setText(file.getAbsolutePath());
		}
	}
	
	//복사란? 프로그래밍적 관점에서 본다면 실행중인 프로그램으로 읽어들인 데이터를
	//실행중인 프로그램에서 내보내는 작업
	public void copy() {
		FileInputStream fis=null;
		FileOutputStream fos=null;
		
		try {
			fis = new FileInputStream(t_ori.getText());
			fos = new FileOutputStream(t_dest.getText());
			
			//읽고 내뱉기(입출력)
			int data=-1;
			
			//퍼담기 위한 바가지 준비
			byte[] b = new byte[1024];
			
			while(true) {
				data=fis.read(b);//입력
				if(data==-1)break;
				fos.write(b);//출력
			}
			JOptionPane.showMessageDialog(this, "복사완료");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fos!=null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		//이벤트를 일으킨 컴포넌트 알아맞추기
		//ActionEvent 와 관련된 모든 이벤트 정보는 ActionEvent 인스턴스에 들어있다.
		//그 객체로 부터 어떤 컴포넌트가 이벤트를 일으켰는지를 추출하는 메서드가 바로
		//getSource() 메서드이다.
		JButton btn=(JButton)e.getSource();
		
		if(btn==bt_find) {//원본 찾기 버튼을 눌렀다면
			openOriginal();
		}else if(btn==bt_sel) {//목적지 디렉토리 선택 경로를 눌렀다면
			openDest();
		}else if(btn==bt) {//복사실행 버튼을 눌렀다면
			copy();
		}
	}

	public static void main(String[] args) {
		new FileChooserCopy();
	}
}
