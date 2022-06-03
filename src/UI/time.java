package UI;
import java.awt.FlowLayout; 
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
// 디지털 stopwatch 
public class time extends JFrame implements ActionListener, Runnable {
 
    private static Thread th = null;
    // Frame 선언
    private JFrame frm;
    // 시작 , 중지, 리셋 버튼 선언
    private JButton start;
    private JButton end;
    private JButton reset;
    // 버튼을 담을 패널 선언
    private JPanel buttons;
    // time 정보 String을 담을 패널 선언 (시간 / 분 / 초 / 밀리초)
    private JPanel times;
 
    // time 정보를 담을 label 선언 (시간 / 분 / 초 / 밀리초)
    private JLabel hour_jl;
    private JLabel min_jl;
    private JLabel sec_jl;
    private JLabel msec_jl;
    // : , . 표시 정보를 담을 label 선언
    private JLabel colon_t1;
    private JLabel colon_t2;
    private JLabel colon_comma;
 
    private long hour = 0;
    private long min = 0;
    private long sec = 0;
    private long msec = 0;
 
    private String hour_str = "";
    private String min_str = "";
    private String sec_str = "";
    private String msec_str = "";
 
    private boolean is;
 
//    private int count = 0;
    Date start_date = new Date();
    
    // time 계산할 변수
    long start_time = 0;
    long check_time = 0;
    long compare_time = 0;
    
 
    // Reset 버튼을 클릭하였을 경우
    // 시간의 모든 정보를 00으로 초기화한후 재 배치
    // count를 0으로 초기화한다.
    // boolean 값은 false로 변경
 
    public void resetTimes() {
        hour_jl.setText("00");
        min_jl.setText("00");
        sec_jl.setText("00");
        msec_jl.setText("00");
//        count = 0;
        is = false;
        repaint();
    }
    
    public void setTimes() {
        hour = Integer.parseInt(hour_jl.getText());
 
        min = Integer.parseInt(min_jl.getText());
 
        sec = Integer.parseInt(sec_jl.getText());
 
        msec = Integer.parseInt(msec_jl.getText());
 
        while (is) {
 
            Date check_date = new Date();
            check_time = check_date.getTime();
 
            // time 처리 부분
            compare_time = check_time - start_time;
            String one = compare_time/1000 + "";
            int totalsec = Integer.parseInt(one, 10);
            msec = compare_time % 100;
            String two = totalsec / 60 + "";
            int totalmin = Integer.parseInt(two, 10);
            sec = totalsec % 60;
            String three = totalmin / 60 + "";
            hour = Integer.parseInt(three, 10);
            min = totalmin % 60;
 
            if(msec == 100) {
                sec++;
                msec = 0;
//                count = 0;
            }
            
            if(sec == 60) {
                sec = 0;
                min++;
            } 
            
            if(min == 60) {
                min = 0;
                hour++;
            }
            
 
            hour_str = String.format("%02d", hour);
            min_str = String.format("%02d", min);
            sec_str = String.format("%02d", sec);
            msec_str = String.format("%02d", msec);
 
            hour_jl.setText(hour_str);
            min_jl.setText(min_str);
            sec_jl.setText(sec_str);
            msec_jl.setText(msec_str);
 
//            count++;
        }
    }
 
    // Button 클릭 시 이벤트 처리
    // start / end / reset
    // boolean 값을 통해 true / false 를 설정하여 스탑워치를 동작하도록 한다.
 
    public void actionPerformed(ActionEvent e) {
        JButton jb = (JButton) e.getSource();
        if (jb == start) {
            start_time = start_date.getTime();
            is = true;
            reset.setEnabled(false);
            end.setEnabled(true);
        } else if (jb == end) {
            is = false;
            reset.setEnabled(true);
            end.setEnabled(false);
        } else if (jb == reset) {
            start_date = new Date();
            start_time = start_date.getTime();
            resetTimes();
            reset.setEnabled(false);
        }
    }
 
    // 생성자 선언
    public time() {
        // Frame 생성
        frm = new JFrame();
        frm.setTitle("디지털 스탑워치");
 
        // init() 메서드를 호출하여
        // Frame 에 컨텐츠를 배치한다.
        init();
        // x 버튼 등의 동작 시 -> 프로그램 종료
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Frame 크기
        frm.setSize(400, 150);
        // Frame 출력
        frm.setResizable(false);
        frm.setVisible(true);
    }
 
    // Frame 내 컨텐츠를 배치하기 위한 메서드
    // 프로그램이 실행된 버튼등을 배치하게 된다.
    public void init() {
 
        // String 값을 담을 Panel을 생성
        times = new JPanel();
 
        // 시간 정보를 담을 Label 생성
        hour_jl = new JLabel();
        min_jl = new JLabel();
        sec_jl = new JLabel();
        msec_jl = new JLabel();
        colon_t1 = new JLabel(":");
        colon_t2 = new JLabel(":");
        colon_comma = new JLabel(".");
 
        // String font 설정
        Font font = new Font("굴림", Font.PLAIN, 50); // font name, font type, font size
        Font font_sub = new Font("굴림", Font.PLAIN, 30);
 
        // font 설정
        hour_jl.setFont(font);
        min_jl.setFont(font);
        sec_jl.setFont(font);
        colon_t1.setFont(font);
        colon_t2.setFont(font);
        // 밀리초는 기존 초 크키보다 작게...
        msec_jl.setFont(font_sub);
        colon_comma.setFont(font_sub);
 
        // JLabel 에 각각의 입력 값(String)을 배치 (.setText)
        hour_str = String.format("%02d", hour);
        min_str = String.format("%02d", min);
        sec_str = String.format("%02d", sec);
        msec_str = String.format("%02d", msec);
 
        hour_jl.setText(hour_str);
        min_jl.setText(min_str);
        sec_jl.setText(sec_str);
        msec_jl.setText(msec_str);
 
        // String 내용을 패널에 담는다.
        times.add(hour_jl);
        times.add(colon_t1);
        times.add(min_jl);
        times.add(colon_t2);
        times.add(sec_jl);
        times.add(colon_comma);
        times.add(msec_jl);
 
        // Button 생성
        start = new JButton("start");
        end = new JButton("end");
        reset = new JButton("reset");
 
        // actionListener 설정
        start.addActionListener(this);
        end.addActionListener(this);
        reset.addActionListener(this);
 
        // Button을 담을 Panel을 생성
        buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
 
        // button을 패널에 담는다.
        buttons.add(start);
        buttons.add(end);
        buttons.add(reset);
        
        // 실행 초기에 버튼 enable 상태
        end.setEnabled(false);
        reset.setEnabled(false);
 
        // Frame에 Button이 담긴 Panel 배치
        frm.add("North", times);
        frm.add("South", buttons);
    }
 
    @Override
    public void run() {
        while (true) {
            if (is) {
                try {
                    setTimes();
                    repaint();
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }
 
    public static void main(String[] args) {
        // 생성자 호출하여 Frame을 출력한다.
        th = new Thread(new time());
        th.run();
    }
}