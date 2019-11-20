import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

class JButtonController implements DocumentListener {
	
	JButton button = new JButton();
  
  JButtonController(JButton button) {
	  this.button = button;
  }

  public void changedUpdate(DocumentEvent e) {
    checkFieldsFull();
  }

  public void insertUpdate(DocumentEvent e) {
	checkFieldsFull();
  }

  public void removeUpdate(DocumentEvent e) {
    checkFieldsFull();
  }
  
  private void checkFieldsFull()
  {
	  for (int i = 0; i < Rushee.textArray.length; i++) {
		  
		  if (Rushee.textArray[i].getText().trim().isEmpty())
		  {
			  button.setEnabled(false);
			  return;
		  } else {
			  button.setEnabled(true);
		  }
	  }
    }
  }