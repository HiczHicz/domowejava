import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MyFrame extends JFrame{
    private Complex newtonComplex(Complex z, double epsilon) {
        while (true) {
            //f(z)=z^3-1
            Complex z2 = z.times(z); //z do potęgi 2
            Complex z3 = z2.times(z); //z do potęgi 3
            Complex f = z3.minus(new Complex(1, 0)); //cała funkcja z^3-1

            //f'(z)=3*z^2
            Complex df = z2.scale(3); //z klasy Complex: scale():="return a new object whose value is (this * alpha)"

            if (df.abs() == 0) return z; //.abs() to moduł liczby zespolonej w klasie Complex

            Complex zNext = z.minus(f.divides(df)); // z-f(z)/f'(z)

            if (zNext.minus(z).abs() < epsilon) {
                return zNext; //jeśli jesteśmy wystarczająco blisko to zwracamy liczbę
            }
            z = zNext;
        }
    }
    public MyFrame() {
        super("Newton"); //tytuł
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocation(50, 50);
        setLayout(new BorderLayout());

        JPanel p = new JPanel(); //panel główny z okienkami do wpisywania
        p.setLayout(new GridLayout(2,4));

        p.add(new JLabel("Re:")); //skrócony - zeby nie musiec wpisywac w dwoch linijkach i definiować nowych zmiennych
        JTextField reInput = new JTextField();
        p.add(reInput);

        p.add(new JLabel("Im:"));
        JTextField imInput = new JTextField();
        p.add(imInput);

        p.add(new JLabel("Re:"));
        JTextField reResult = new JTextField();
        reResult.setEditable(false); //zeby nie mozna bylo nic wpisywac
        p.add(reResult);

        p.add(new JLabel("Im:"));
        JTextField imResult = new JTextField();
        imResult.setEditable(false);
        p.add(imResult);

        add(p, BorderLayout.CENTER);

        JButton b=new JButton("OK"); //przycisk który będzie na dole
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double re = Double.parseDouble(reInput.getText()); //wczytujemy z pola tekstowego double
                double im = Double.parseDouble(imInput.getText());
                Complex zespolona = new Complex(re, im); //przerabiamy z naszych pól tekstowych na liczbę zespoloną

                double epsilon = 1e-6; //deklaracja epsilon - 0.000001
                Complex wynik = newtonComplex(zespolona, epsilon);

                reResult.setText(String.format("%.6f", wynik.re())); //poniewaz epsilon to 0.000001, to zaokrąglamy do 6 miejsca po przecinku
                imResult.setText(String.format("%.6f", wynik.im()));
            }
        });
        add(b, BorderLayout.SOUTH);
        setVisible(true);
    }
}