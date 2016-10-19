package study.source.ch13.연습문제;
import java.io.*;
import java.util.*;
import java.text.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class Exercise13_5 extends Frame {
	Panel  pUp		  = new Panel();
	Button btnPrevMon = new Button("◀");
	Button btnNextMon = new Button("▶");
	Label  lblYearMon = new Label();

	Calendar curMon = Calendar.getInstance();

	Exercise13_5(String title) {
		super(title);

		pUp.setBackground(Color.yellow);
		pUp.setLayout(new FlowLayout(FlowLayout.CENTER));
		pUp.add(btnPrevMon);
		pUp.add(lblYearMon);
		pUp.add(btnNextMon);

		btnPrevMon.addActionListener(new BtnEventHandler());
		btnNextMon.addActionListener(new BtnEventHandler());
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				we.getWindow().setVisible(false);
				we.getWindow().dispose();
				System.exit(0);
			}
		});

		add(pUp);
		setBounds(200,200,200,70);
		setDays(curMon);
		setVisible(true);
	} // Exercise13_5

	void setDays(Calendar date) {
		int year  = date.get(Calendar.YEAR); 
		int month = date.get(Calendar.MONTH); 

		lblYearMon.setText(year+"년 "+(month+1)+"월");
	}

	class BtnEventHandler implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			/*
				(1) 알맞은 코드를 넣으시오.
				1. 눌러진 버튼이 btnPrevMon이면, curMon을 한 달 이전으로 변경한다.
				2. 눌러진 버튼이 btnNextMon이면, curMon을 한 달 이후로 변경한다.
				3. setDays()를 호출해서 변경된 내용이 레이블에 보이게 한다.
			*/
		}
	}

	public static void main(String[] args) {
		Exercise13_5 mainWin = new Exercise13_5("Scheduler");
	} // main
}
