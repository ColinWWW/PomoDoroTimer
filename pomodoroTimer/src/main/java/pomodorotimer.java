import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class pomodorotimer extends JFrame implements ActionListener {

    private JButton start;
    private JButton reset;
    private JLabel time_label;
    private JLabel Title;
    private int sessiontime = 1500; // this is 25 minutes
    private int elapsedtime = sessiontime*1000;
    private int h = elapsedtime/3600000;
    private int m = (elapsedtime/60000) % 60;
    private int s = (elapsedtime/1000) % 60;

    boolean startClicked = false;
    String h_string = String.format("%02d", h);
    String m_string = String.format("%02d", m);
    String s_string = String.format("%02d", s);
    public pomodorotimer () {
        this.setSize(420, 420);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.Title = new JLabel("POMODORO TIMER");
        this.Title.setBounds(120,50,250,100);
        this.Title.setFont(new Font("Sans-serif", Font.BOLD, 18));
        this.add(this.Title);

        this.start = new JButton("Start");
        this.reset = new JButton("Reset");
        this.start.setBounds(100,200,100,50);
        this.reset.setBounds(200,200,100,50);

        this.add(this.start);
        this.add(this.reset);
        this.start.addActionListener(this);
        this.reset.addActionListener(this);

        this.time_label = new JLabel(h_string + ":" + m_string + ":" + s_string);
        this.time_label.setBounds(175,100,100,100);
        this.add(this.time_label);

    }
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (elapsedtime != 0) {
                elapsedtime = elapsedtime - 1000;
                h = (elapsedtime / 3600000);
                m = (elapsedtime / 60000) % 60;
                s = (elapsedtime / 1000) % 60;
                String h_String = String.format("%02d", h);
                String m_String = String.format("%02d", m);
                String s_String = String.format("%02d", s);
                time_label.setText(h_String + ":" + m_String + ":" + s_String);

            }
            else {
                stoptimerinzero();
            }
        }
    }
    );
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == start) {
            if(startClicked == false) {
                start.setText("Stop");
                startClicked = true;
                timer.start();
            }
            else {
                start.setText("Start");
                startClicked =  false;
                timer.stop();
            }
        }
        else if (e.getSource() == reset) {
            timer.stop();
            elapsedtime = sessiontime * 1000;
            h = (elapsedtime/3600000);
            m = (elapsedtime / 60000) % 60;
            s = (elapsedtime / 1000) % 60;
            String h_String = String.format("%02d", h);
            String m_String = String.format("%02d", m);
            String s_String = String.format("%02d", s);
            this.time_label.setBounds(175,100,100,100);
            this.time_label.setText(h_String + ":" + m_String + ":" + s_String);
            this.start.setText("start");
            startClicked = false;
        }
    }
    public void stoptimerinzero() {
        timer.stop();
        elapsedtime = sessiontime*1000;
        elapsedtime = sessiontime * 1000;
        h = (elapsedtime/3600000);
        m = (elapsedtime / 60000) % 60;
        s = (elapsedtime / 1000) % 60;
        String h_String = String.format("%02d", h);
        String m_String = String.format("%02d", m);
        String s_String = String.format("%02d", s);
        //this.time_label.setBounds(175,100,100,100);
        this.time_label.setText(h_string + ":" + m_string + ":" + s_string);
        this.start.setText("Start");
        startClicked = false;
        int choice = JOptionPane.showConfirmDialog(null, "Keep Going?", "Session End", JOptionPane.YES_NO_OPTION);
        if(choice == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Nice Session");
        }
        else if (choice == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Don't Give Up Good Luck in Next Session");
        }
    }
}
