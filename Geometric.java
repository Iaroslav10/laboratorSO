/*	O aplicație care calculează proprietăți geometrice, 
cum ar fi aria și perimetrul și raza pentru diferite forme
(de exemplu, pătrate, dreptunghiuri, cercuri, triunghiuri).
Fiecare membru al echipei implementează o clasă care calculează 
o proprietate specifică pentru o anumită formă. În algoritmul principal (clasa),
 realizați relația dintre cel puțin două clase.*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GeometricShape {
    protected double area;
    protected double perimeter;

    public double getArea() {
        return area;
    }

    public double getPerimeter() {
        return perimeter;
    }
}

class Square extends GeometricShape {
    private double sideLength;

    public Square(double sideLength) {
        this.sideLength = sideLength;
        calculateProperties();
    }

    private void calculateProperties() {
        area = sideLength * sideLength;
        perimeter = 4 * sideLength;
    }
}

class Circle extends GeometricShape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
        calculateProperties();
    }

    private void calculateProperties() {
        area = Math.PI * radius * radius;
        perimeter = 2 * Math.PI * radius;
    }
}

public class Geometric extends JFrame implements ActionListener {
    private JTextField sideLengthField;
    private JTextField radiusField;
    private JButton squareButton;
    private JButton circleButton;
    private JTextArea resultArea;

    public Geometric() {
        setTitle("Calculator de Proprietăți Geometrice");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        sideLengthField = new JTextField(10);
        radiusField = new JTextField(10);
        squareButton = new JButton("Calculează Pătrat");
        circleButton = new JButton("Calculează Cerc");
        resultArea = new JTextArea(10, 20);
        resultArea.setEditable(false);

        squareButton.addActionListener(this);
        circleButton.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Lungimea laturii pătratului: "));
        panel.add(sideLengthField);
        panel.add(squareButton);
        panel.add(new JLabel("Raza cercului: "));
        panel.add(radiusField);
        panel.add(circleButton);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(panel, BorderLayout.NORTH);
        container.add(new JScrollPane(resultArea), BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == squareButton) {
            try {
                double sideLength = Double.parseDouble(sideLengthField.getText());
                Square square = new Square(sideLength);
                resultArea.setText("Pătrat: Arie = " + square.getArea() + ", Perimetru = " + square.getPerimeter());
            } catch (NumberFormatException ex) {
                resultArea.setText("Introduceți o valoare validă pentru lungimea laturii.");
            }
        } else if (e.getSource() == circleButton) {
            try {
                double radius = Double.parseDouble(radiusField.getText());
                Circle circle = new Circle(radius);
                resultArea.setText("Cerc: Arie = " + circle.getArea() + ", Circumferință = " + circle.getPerimeter());
            } catch (NumberFormatException ex) {
                resultArea.setText("Introduceți o valoare validă pentru raza cercului.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Geometric calculator = new Geometric();
            calculator.setVisible(true);
        });
    }
}