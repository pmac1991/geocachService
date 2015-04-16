/**
 * Created by piotr_komp on 2015-04-14.
 */

import javax.swing.*
import java.awt.*
import java.awt.event.*

frame = new JFrame(size: [300, 300], layout: new FlowLayout(), defaultCloseOperation: javax.swing.WindowConstants.EXIT_ON_CLOSE)

positionLabel = new JLabel("")
msgLabel = new JLabel("")
frame.contentPane.add positionLabel
frame.contentPane.add msgLabel

float currentDispValue = 0.0;
String tempValue = '';
boolean mode = false; //false bez kropki true kropka zostala nacisnieta

def  stack = [];

positionLabel.setText(currentDispValue.toString());

for(int i=0;i < 9; i++)
{
    def j = i;
    button_$i = new JButton("$i")
    frame.contentPane.add button_$i;
    button_$i.addActionListener(
            {
                tempValue = tempValue.concat(j.toString());

                currentDispValue = Float.parseFloat(tempValue);
                positionLabel.setText(currentDispValue.toString());
            } as ActionListener
    )
};

button_plus = new JButton("+")
button_minus = new JButton("-")
button_mul = new JButton("*")
button_div = new JButton("/")
button_point = new JButton(".")
button_open = new JButton("(")
button_close = new JButton(")")
button_equal = new JButton("=")

frame.contentPane.add button_plus
frame.contentPane.add button_minus
frame.contentPane.add button_mul
frame.contentPane.add button_div
frame.contentPane.add button_open
frame.contentPane.add button_close
frame.contentPane.add button_point
frame.contentPane.add button_equal

button_plus.addActionListener(
        {
            stack.add(currentDispValue);
            stack.add("+");
            currentDispValue = 0.0;
            tempValue = '';
            mode = false;
            positionLabel.setText(currentDispValue.toString());
        } as ActionListener
)

button_minus.addActionListener(
        {
            stack.add(currentDispValue);
            stack.add("-");
            currentDispValue = 0.0;
            tempValue = '';
            mode = false;
            positionLabel.setText(currentDispValue.toString());
        } as ActionListener
)

button_mul.addActionListener(
        {
            stack.add(currentDispValue);
            stack.add("*");
            currentDispValue = 0.0;
            tempValue = '';
            mode = false;
            positionLabel.setText(currentDispValue.toString());
        } as ActionListener
)

button_div.addActionListener(
        {
            stack.add(currentDispValue);
            stack.add("/");
            currentDispValue = 0.0;
            tempValue = '';
            mode = false;
            positionLabel.setText(currentDispValue.toString());
        } as ActionListener
)

button_point.addActionListener(
        {
            if(mode == false)
            {
                mode = true;
                tempValue = tempValue.concat('.');
            }
            positionLabel.setText(currentDispValue.toString());
        } as ActionListener
)

button_equal.addActionListener(
        {
            stack.add(currentDispValue);
            String text = "";
            for(int k=0;k < stack.size();k++)
            {
                    text = text + stack[k].toString();
            }

            stack.clear();

            currentDispValue = 0.0;
            tempValue = '';
            positionLabel.setText(Eval.me(text).toString());

        } as ActionListener
)


handleFocus = [
        focusGained: { msgLabel.setText("Good to see you!") },
        focusLost: { msgLabel.setText("Come back soon!") }
]

//button_1.addFocusListener(handleFocus as FocusListener)

events = ['WindowListener', 'ComponentListener']
// Above list may be dynamic and may come from some input

handler = { msgLabel.setText("$it") }

for (event in events) {
    handleImpl = handler.asType(Class.forName("java.awt.event.${event}"))
}

frame.show()
