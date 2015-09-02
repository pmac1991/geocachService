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

stackLabel = new JLabel("")
debugLabel = new JLabel("")

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

               // stack.add(currentDispValue);
                positionLabel.setText(currentDispValue.toString());
                stackLabel.setText(stack.toString());



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
frame.contentPane.add stackLabel
frame.contentPane.add debugLabel

boolean wait = false;

void hReduce()
{

}

button_plus.addActionListener(
        {
			
            if(stack.size() != 0) {
                if (stack[stack.size() - 1] == "*" || stack[stack.size() - 1] == "/" || stack[stack.size() - 1] == "+" || stack[stack.size() - 1] == "-" || stack[stack.size() - 1] == "(") {
                    stack.add(currentDispValue);
                }
            }
            else{
                stack.add(currentDispValue);
            }
            currentDispValue = 0.0;

            if((stack[stack.size() - 2] == "+" || stack[stack.size() - 2] == "-" )&& stack.size() >= 3)
            {
                String text = "";
				//debugLabel.setText(stack[stack.size() - 2].toString());
                /*for(int k=0;k < stack.size();k++)
                {
                    text = text + stack[k].toString();
                }*/
				for(int k=(stack.size() - 3);k < stack.size();k++)
                {
                    text = text + stack[k].toString();
                }
				stack.remove(stack.size()-1);
                stack.remove(stack.size()-1);
                stack.remove(stack.size()-1);
				//debugLabel.setText(text);
                stack.add(Eval.me(text));


                //def tempList = [];
                //tempList.add(Eval.me(text));
                //stack = tempList;
            }

            if((stack[stack.size() - 2] == "*" || stack[stack.size() - 2] == "/" )&& stack.size() >= 3)
            {
				
                String text = "";
                for(int k=(stack.size() - 3);k < stack.size();k++)
                {
                    text = text + stack[k].toString();
                }

                stack.remove(stack.size()-1);
                stack.remove(stack.size()-1);
                stack.remove(stack.size()-1);
                stack.add(Eval.me(text));

                String tempText = " ";
                /*if ((stack.size()-1) != 0) {
                    for(int k=0;k < stack.size();k++)
                    {
                        tempText = tempText + stack[k].toString();
                    }

                    stack.remove(stack.size()-1);
                    stack.remove(stack.size()-1);
                    stack.remove(stack.size()-1);
					debugLabel.setText(tempText);
                    stack.add(Eval.me(tempText));
                }*/
            }
			
			/*if(stack[stack.size() - 2] == "(" && stack.size() >= 3 )
			{
				debugLabel.setText("DEBUG((((((()))))))");
				String tmptext = "";
				if((stack[stack.size()] == "*" || stack[stack.size()] == "/" ))
				{
					for(int i = (stack.size() - 1); i <= stack.size(); i++)
					{
						tmptext = tmptext + stack[k];
					}
				    stack.remove(stack.size());
					stack.remove(stack.size());
					stack.remove(stack.size());
					stack.add(Eval.me(tmptext));
				}
			}*/


            stack.add("+");
            tempValue = '';
            mode = false;
            positionLabel.setText(stack[0].toString());
            stackLabel.setText(stack.toString());
        } as ActionListener
)

button_minus.addActionListener(
        {
            if(stack.size() != 0) {
                if (stack[stack.size() - 1] == "*" || stack[stack.size() - 1] == "/" || stack[stack.size() - 1] == "+" || stack[stack.size() - 1] == "-" || stack[stack.size() - 1] == "(") {
                    stack.add(currentDispValue);
                }
            }
            else{
                stack.add(currentDispValue);
            }

            if((stack[stack.size() - 2] == "+" || stack[stack.size() - 2] == "-")&& stack.size() >= 3)
            {
                String text = "";
                for(int k=0;k < stack.size();k++)
                {
                    text = text + stack[k].toString();
                }

                def tempList = [];
                tempList.add(Eval.me(text));
                stack = tempList;
            }

            if((stack[stack.size() - 2] == "*" || stack[stack.size() - 2] == "/")&& stack.size() >= 3)
            {
                String text = "";
                for(int k=(stack.size() - 3);k < stack.size();k++)
                {
                    text = text + stack[k].toString();
                }

                stack.remove(stack.size()-1);
                stack.remove(stack.size()-1);
                stack.remove(stack.size()-1);
                stack.add(Eval.me(text));

                String tempText = " ";
                /*if ((stack.size()-1) != 0) {
                    for(int k=0;k < stack.size();k++)
                {
                    tempText = tempText + stack[k].toString();
                }

                    stack.remove(stack.size()-1);
                    stack.remove(stack.size()-1);
                    stack.remove(stack.size()-1);
                    stack.add(Eval.me(tempText));
                }*/


            }


            stack.add("-");
            currentDispValue = 0.0;
            tempValue = '';
            mode = false;
            positionLabel.setText(stack[0].toString());
            stackLabel.setText(stack.toString());
        } as ActionListener
)

button_mul.addActionListener(
        {
            if(stack.size() != 0) {
                if (stack[stack.size() - 1] == "*" || stack[stack.size() - 1] == "/" || stack[stack.size() - 1] == "+" || stack[stack.size() - 1] == "-" || stack[stack.size() - 1] == "(") {
                    stack.add(currentDispValue);
                }
            }
            else{
                stack.add(currentDispValue);
            }

            if((stack[stack.size() - 2] == "*" || stack[stack.size() - 2] == "/")&& stack.size() >= 3)
            {
                String text = "";
                for(int k=0;k < stack.size();k++)
                {
                    text = text + stack[k].toString();
                }

                def tempList = [];
                tempList.add(Eval.me(text));
                stack = tempList;
            }

            if((stack[stack.size() - 2] == "+" || stack[stack.size() - 2] == "-")&& stack.size() >= 3)
            {

            }


            stack.add("*");
            currentDispValue = 0.0;
            tempValue = '';
            mode = false;
            positionLabel.setText(stack[0].toString());
            stackLabel.setText(stack.toString());
        } as ActionListener
)

button_div.addActionListener(
        {
            if(stack.size() != 0) {
                if (stack[stack.size() - 1] == "*" || stack[stack.size() - 1] == "/" || stack[stack.size() - 1] == "+" || stack[stack.size() - 1] == "-" || stack[stack.size() - 1] == "(") {
                    stack.add(currentDispValue);
                }
            }
            else{
                stack.add(currentDispValue);
            }

            if((stack[stack.size() - 2] == "*" || stack[stack.size() - 2] == "/")&& stack.size() >= 3)
            {
                String text = "";
                for(int k=0;k < stack.size();k++)
                {
                    text = text + stack[k].toString();
                }

                def tempList = [];
                tempList.add(Eval.me(text));
                stack = tempList;
            }

            if((stack[stack.size() - 2] == "+" || stack[stack.size() - 2] == "-")&& stack.size() >= 3)
            {

            }




            stack.add("/");
            currentDispValue = 0.0;
            tempValue = '';
            mode = false;
            positionLabel.setText(stack[0].toString());
            stackLabel.setText(stack.toString());
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

button_close.addActionListener(
        {
            if(stack.size() != 0) {
                if (stack[stack.size() - 1] == "*" || stack[stack.size() - 1] == "/" || stack[stack.size() - 1] == "+" || stack[stack.size() - 1] == "-" || stack[stack.size() - 1] == "(") {
                    stack.add(currentDispValue);
                }
            }
            else{
                stack.add(currentDispValue);
            }
            stack.add(")");
            currentDispValue = 0.0;
            tempValue = '';
            mode = false;


            int l = 0;
            String text = " ";

            String debug = " ";



            for(int k = (stack.size()-1);k>=0;k--)
            {
                debug = debug + "jestem w 1 pet";
                if(stack[k] == "(")
                {
                    l = k;
                    for(int j =k ; j < stack.size();j++)
                    {
                        text = text + stack[j].toString();

                    }
                    break;
                }
            }

            int stackTemp = stack.size();

            for(int z = l;z<stackTemp;z++)
            {

                stack.remove(stack.size()-1);
            }

            stack.add(Eval.me(text).toString());

            //stackLabel.setText(l.toString());

            positionLabel.setText(stack[0].toString());
            stackLabel.setText(stack.toString());
        } as ActionListener
)

button_open.addActionListener(
        {
           // 	stack.add(currentDispValue);
            stack.add("(");
            currentDispValue = 0.0;
            tempValue = '';
            mode = false;
            positionLabel.setText(currentDispValue.toString());
            stackLabel.setText(stack.toString());
        } as ActionListener
)

button_equal.addActionListener(
        {
            if(stack[stack.size()-1] == "*" || stack[stack.size()-1] == "/" || stack[stack.size()-1] == "+" || stack[stack.size()-1] == "-")
            {
                stack.add(currentDispValue);
            }
            String text = "";
            for(int k=0;k < stack.size();k++)
            {
                text = text + stack[k].toString();
            }

            stack.clear();

            currentDispValue = 0.0;
            tempValue = '';
            positionLabel.setText(Eval.me(text).toString());
            stack.add(Eval.me(text).toString());
            stackLabel.setText(stack.toString());

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