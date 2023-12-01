import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main1 {
    private JFrame frame;
    private JTextField textField;
    private double currentResult = 0.0;
    private String currentInput = "";
    private char currentOperator = ' ';

    private double memoryValue = 0.0;

    private static final double PI = Math.PI;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main1 window = new Main1();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Main1() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 390, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Используем BorderLayout для панели содержимого фрейма
        frame.getContentPane().setLayout(new BorderLayout());

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(350, 45));
        frame.getContentPane().add(textField, BorderLayout.NORTH);

        // Создаем панель для кнопок и используем GridLayout
        JPanel buttonPanel = new JPanel(new GridLayout(6, 5, 10, 10));
        frame.getContentPane().add(buttonPanel, BorderLayout.CENTER);

        // Создаем кнопки и добавляем их на панель
        JButton btn1 = createNumberButton("1");
        btn1.setPreferredSize(new Dimension(20, 10));
        JButton btn2 = createNumberButton("2");
        btn2.setPreferredSize(new Dimension(20, 10));
        JButton btn3 = createNumberButton("3");
        btn3.setPreferredSize(new Dimension(20, 10));
        JButton btn4 = createNumberButton("4");
        btn4.setPreferredSize(new Dimension(20, 10));
        JButton btn5 = createNumberButton("5");
        btn5.setPreferredSize(new Dimension(20, 10));
        JButton btn6 = createNumberButton("6");
        btn6.setPreferredSize(new Dimension(20, 10));
        JButton btn7 = createNumberButton("7");
        btn7.setPreferredSize(new Dimension(20, 10));
        JButton btn8 = createNumberButton("8");
        btn8.setPreferredSize(new Dimension(20, 10));
        JButton btn9 = createNumberButton("9");
        btn9.setPreferredSize(new Dimension(20, 10));
        JButton btn0 = createNumberButton("0");
        btn0.setPreferredSize(new Dimension(20, 10));

        JButton btnAdd = createOperatorButton("+");
        btnAdd.setPreferredSize(new Dimension(20, 10));
        JButton btnSubtract = createOperatorButton("-");
        btnSubtract.setPreferredSize(new Dimension(20, 10));
        JButton btnMultiply = createOperatorButton("*");
        btnMultiply.setPreferredSize(new Dimension(20, 10));
        JButton btnDivide = createOperatorButton("/");
        btnDivide.setPreferredSize(new Dimension(20, 10));
        JButton btnEquals = createEqualsButton();
        btnEquals.setPreferredSize(new Dimension(20, 10));
        JButton btnClear = createClearButton();
        btnClear.setPreferredSize(new Dimension(20, 10));

        JButton btnSqrt = createFunctionButton("√x");
        btnSqrt.setPreferredSize(new Dimension(20, 10));
        JButton btnSin = createFunctionButton("sin");
        btnSin.setPreferredSize(new Dimension(20, 10));
        JButton btnCos = createFunctionButton("cos");
        btnCos.setPreferredSize(new Dimension(20, 10));
        JButton btnTan = createFunctionButton("tan");
        btnTan.setPreferredSize(new Dimension(20, 10));
        JButton btnCtan = createFunctionButton("ctan");
        btnCtan.setPreferredSize(new Dimension(20, 10));
        JButton btnMemoryPlus = createMemoryButton("M+");
        btnMemoryPlus.setPreferredSize(new Dimension(20, 10));
        JButton btnMemoryRecall = createMemoryButton("MR");
        btnMemoryRecall.setPreferredSize(new Dimension(20, 10));
        JButton btnLog10 = createFunctionButton("log10");
        btnLog10.setPreferredSize(new Dimension(20, 10));
        JButton btnLog = createFunctionButton("log");
        btnLog.setPreferredSize(new Dimension(20, 10));
        JButton btnLn = createFunctionButton("ln");
        btnLn.setPreferredSize(new Dimension(20, 10));
        JButton btnExp = createFunctionButton("exp");
        btnExp.setPreferredSize(new Dimension(20, 10));
        JButton btnRadDeg = createFunctionButton("deg");
        btnRadDeg.setPreferredSize(new Dimension(20, 10));
        JButton btnPow = createFunctionButton("pow");
        btnPow.setPreferredSize(new Dimension(20, 10));
        JButton btnDot = createNumberButton(".");
        btnDot.setPreferredSize(new Dimension(20, 10));


        buttonPanel.add(btnExp);
        buttonPanel.add(btnRadDeg);
        buttonPanel.add(btnMemoryPlus);
        buttonPanel.add(btnMemoryRecall);
        buttonPanel.add(btnEquals);

        buttonPanel.add(btnLog10);
        buttonPanel.add(btn7);
        buttonPanel.add(btn8);
        buttonPanel.add(btn9);
        buttonPanel.add(btnDivide);

        buttonPanel.add(btnLog);
        buttonPanel.add(btn4);
        buttonPanel.add(btn5);
        buttonPanel.add(btn6);
        buttonPanel.add(btnMultiply);

        buttonPanel.add(btnLn);
        buttonPanel.add(btn1);
        buttonPanel.add(btn2);
        buttonPanel.add(btn3);
        buttonPanel.add(btnSubtract);

        buttonPanel.add(btnSqrt);
        buttonPanel.add(btnPow);
        buttonPanel.add(btn0);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnAdd);


        buttonPanel.add(btnSin);
        buttonPanel.add(btnCos);
        buttonPanel.add(btnTan);
        buttonPanel.add(btnCtan);
        buttonPanel.add(btnDot);

        JButton btnPi = createFunctionButton("π");
        btnPi.setPreferredSize(new Dimension(20, 10));
        buttonPanel.add(btnPi);


        frame.getContentPane().add(buttonPanel);

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                char inputChar = e.getKeyChar();
                if (Character.isDigit(inputChar)) {
                    currentInput += inputChar;
                    textField.setText(currentInput);
                } else if (inputChar == '+' || inputChar == '-' || inputChar == '*' || inputChar == '/') {
                    if (!currentInput.isEmpty()) {
                        performCalculation();
                    }
                    currentOperator = inputChar;
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    performCalculation();
                }
            }
        });
    }

    private JButton createNumberButton(final String number) {
        JButton button = new JButton(number);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (number.equals(".")) {
                    // Проверяем, что точка еще не добавлена
                    if (!currentInput.contains(".")) {
                        currentInput += number;
                    }
                } else {
                    currentInput += number;
                }
                textField.setText(currentInput);
            }
        });
        return button;
    }


    private JButton createOperatorButton(final String operator) {
        JButton button = new JButton(operator);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (!currentInput.isEmpty()) {
                    performCalculation();
                    currentOperator = operator.charAt(0);
                }
            }
        });
        return button;
    }

    private JButton createEqualsButton() {
        JButton button = new JButton("=");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performCalculation();
            }
        });
        return button;
    }

    private JButton createClearButton() {
        JButton button = new JButton("C");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentInput = "";
                currentResult = 0.0;
                currentOperator = ' ';
                textField.setText("");
            }
        });
        return button;
    }

    private void performCalculation() {
        if (!currentInput.isEmpty()) {
            double inputValue = Double.parseDouble(currentInput);
            switch (currentOperator) {
                case '+':
                    currentResult += inputValue;
                    break;
                case '-':
                    currentResult -= inputValue;
                    break;
                case '*':
                    currentResult *= inputValue;
                    break;
                case '/':
                    if (inputValue != 0) {
                        currentResult /= inputValue;
                    } else {
                        textField.setText("Error: Division by zero");
                        currentInput = "";
                        currentResult = 0.0;
                        currentOperator = ' ';
                        return;
                    }
                    break;
                default:
                    currentResult = inputValue;
                    break;
            }
            textField.setText(Double.toString(currentResult));
            currentInput = "";
        }
        currentOperator = ' ';
    }


    private JButton createFunctionButton(final String functionName) {
        JButton button = new JButton(functionName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (functionName.equals("π")) {
                    // Если нажата кнопка π, добавляем π к текущему вводу
                    currentInput += String.valueOf(PI);
                    textField.setText(currentInput);
                } else {
                    performFunction(functionName);
                }
            }
        });
        return button;
    }



    private JButton createMemoryButton(final String memoryAction) {
        JButton button = new JButton(memoryAction);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performMemoryAction(memoryAction);
            }
        });
        return button;
    }

    private void performFunction(String functionName) {
        double result = currentResult;
        double inputValue = Double.parseDouble(currentInput);

        switch (functionName) {
            case "√x":
                result = Math.sqrt(inputValue);
                break;
            case "sin":
                result = Math.sin(inputValue);
                break;
            case "cos":
                result = Math.cos(inputValue);
                break;
            case "tan":
                result = Math.tan(inputValue);
                break;
            case "ctan":
                result = 1.0 / Math.tan(inputValue);
                break;
            case "log10":
                result = Math.log10(inputValue);
                break;
            case "log":
                double secondInputValue = Double.parseDouble(JOptionPane.showInputDialog("Введите второе число:"));
                result = Math.log(secondInputValue) / Math.log(inputValue);
                break;
            case "ln":
                result = Math.log(inputValue);
                break;
            case "exp":
                result = Math.exp(inputValue);
                break;
            case "deg":
                // Переключение между радианами и градусами
                if (currentInput.equals("rad")) {
                    result = Math.toDegrees(inputValue);
                    currentInput = "deg";
                } else {
                    result = Math.toRadians(inputValue);
                    currentInput = "rad";
                }
                break;
            case "pow":
                double secondInputValueForPow = Double.parseDouble(JOptionPane.showInputDialog("Введите степень:"));
                result = Math.pow(inputValue, secondInputValueForPow);
                break;
        }

        currentResult = result;
        textField.setText(Double.toString(result));
        currentInput = "";
    }

    private void performMemoryAction(String memoryAction) {
        switch (memoryAction) {
            case "M+":
                // Запись в память
                memoryValue += currentResult;
                break;
            case "MR":
                // Извлечение из памяти
                currentResult = memoryValue;
                textField.setText(Double.toString(currentResult));
                currentInput = "";
                break;
        }
    }
}
