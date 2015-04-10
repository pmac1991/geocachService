import javax.swing.*
import java.awt.*
import java.awt.event.*

frame = new JFrame(size: [300, 300], layout: new FlowLayout(), defaultCloseOperation: javax.swing.WindowConstants.EXIT_ON_CLOSE)

positionLabel = new JLabel("")
msgLabel = new JLabel("")
frame.contentPane.add positionLabel
frame.contentPane.add msgLabel

float currentDispValue = 0.0;
int mode = 0; //0 bez kropki 1 kropka zostala nacisnieta

def  stack = [];

positionLabel.setText(currentDispValue.toString());

for(int i=0;i < 9; i++)
{
	def j = i;
	button_$i = new JButton("$i")
	frame.contentPane.add button_$i;
	button_$i.addActionListener(
	  {  
		  if(mode == 0)
		  {
			  currentDispValue = currentDispValue * 10 + j;
		  }
		  else
		  {
			  currentDispValue = currentDispValue + j/10;
		  }
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

frame.contentPane.add button_plus
frame.contentPane.add button_minus
frame.contentPane.add button_mul
frame.contentPane.add button_div
frame.contentPane.add button_open
frame.contentPane.add button_close
frame.contentPane.add button_point

button_plus.addActionListener(
 { 
  	  currentDispValue = currentDispValue * 10 + 2;
  	  positionLabel.setText(currentDispValue.toString());	
  } as ActionListener
)

button_minus.addActionListener(
 { 
  	  currentDispValue = currentDispValue * 10 + 2;
  	  positionLabel.setText(currentDispValue.toString());	
  } as ActionListener
)

button_mul.addActionListener(
 { 
  	  currentDispValue = currentDispValue * 10 + 2;
  	  positionLabel.setText(currentDispValue.toString());	
  } as ActionListener
)

button_div.addActionListener(
 { 
  	  currentDispValue = currentDispValue * 10 + 2;
  	  positionLabel.setText(currentDispValue.toString());	
  } as ActionListener
)

button_point.addActionListener(
 { 
 	  if(mode == 0)
 	  {
 	  	  mode = 1;
 	  }
 	  if(mode == 1)
 	  {
 	  	  mode = 0;
 	  }
  	  positionLabel.setText(currentDispValue.toString());	
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
