
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class MyFrame extends JFrame{
    private JTextField reInput, imInput, reResult, imResult;
    //ew zamiast dać tutaj private to zrobić przy kazdym np. JTextField reInput = new JTextField();
    private Complex newtonComplex(Complex z, double epsilon) {
        while (true) {
            // f(z) = z^3 - 1
            Complex z2 = z.times(z);
            Complex z3 = z2.times(z);
            Complex f = z3.minus(new Complex(1, 0));

            // f'(z) = 3 * z^2
            Complex df = z2.scale(3);

            if (df.abs() == 0) return z; 

            Complex zNext = z.minus(f.divides(df));

            if (zNext.minus(z).abs() < epsilon) {
                return zNext;
            }

            z = zNext;
        }
    }

    public MyFrame() {
        super("Newton");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 400);
        setLocation(50, 50);
        setLayout(new BorderLayout());

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2,4, 5,5));

        p.add(new JLabel("Re:"));
        reInput = new JTextField();
        p.add(reInput);

        JLabel im = new JLabel("Im:");
        p.add(im);
        imInput = new JTextField();
        p.add(imInput);

        p.add(new JLabel("Re:"));
        reResult = new JTextField();
        reResult.setEditable(false);
        p.add(reResult);

        p.add(new JLabel("Im:"));
        imResult = new JTextField();
        imResult.setEditable(false);
        p.add(imResult);

        add(p, BorderLayout.CENTER);

        JButton b=new JButton("OK");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double re = Double.parseDouble(reInput.getText());
                double im = Double.parseDouble(imInput.getText());
                Complex start = new Complex(re, im);

                double epsilon = 1e-6;
                Complex wynik = newtonComplex(start, epsilon);

                reResult.setText(String.format("%.6f", wynik.re()));
                imResult.setText(String.format("%.6f", wynik.im()));
            }
        });
        add(b, BorderLayout.SOUTH);
        setVisible(true);
    }
}