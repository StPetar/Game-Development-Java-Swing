package view;

import view.welcome.WelcomeScene;
import view.welcome.WelcomeThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * a class responsible for providing the user with a help message
 *
 * @author Nelson
 */
@SuppressWarnings("serial")
public class Help extends JPanel {
    private JButton exitButton;
    private JLabel showHelp;

    public Help() {
        // exit the welcome thread
        WelcomeThread.setWelcomeStop(true);
        // set the background colour
        Color color = new Color(51, 204, 255);
        setBackground(color);
        exitButton = new JButton("Exit Help");
        exitButton.setAlignmentX(CENTER_ALIGNMENT);
        String text = "<html>"
                + "Super Nario Game<br><br>"
                + "<p>Game Difficulty: Level 1<br>"
                + "In the first level, As the player you can kill opponents by shooting them or by stomping them. It is also advised to collect all the coins"
                + " available and kill some opponents to reach the points you need to win the level. You also need to reach the castle in the given time</p>"
                + "<br?<p>Game Difficulty: Level 2<br>"
                + "Same as before, however there are some new enemies - birds. Any contact with them results in game over."
                + "Shooting also requires 1 point. You only win the level if you reach the castle on time with the required number of points.</p>"
                + "<br><p>Game Difficulty: Level 3<br>"
                + "This level is complicated. Now you can only kill opponents by shooting. Shooting requires 2 points. Opponents are also cleverer as they try to dodge your bullets."
                + " Pipes now produce fire randomly and you lose if you get, so watch out!"
                + "on water. To win this level, you only need to reach the castle in the given time, no point limit. </p>"
                + "<html>";
        showHelp = new JLabel("", SwingConstants.CENTER);
        showHelp.setFont(new Font("Liberation Serif", Font.BOLD, 18));
        showHelp.setPreferredSize(new Dimension(Main.GAMEWIDTH - 30, Main.GAMEHEIGHT - 10));
        showHelp.setAlignmentX(CENTER_ALIGNMENT);
        showHelp.setAlignmentY(CENTER_ALIGNMENT);
        showHelp.setText(text);
        // Create a  vertical box
        Box vBox = Box.createVerticalBox();
        //
        // add the controls to the panel
        vBox.setAlignmentX(CENTER_ALIGNMENT);
        vBox.add(showHelp);
        vBox.add(exitButton);

        this.add(vBox);
        // return to the welcome Window
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // re-enable the welcome thread
                WelcomeThread.setWelcomeStop(false);
                // make the welcome scene regain focus
                WelcomeScene.setFocus(true);
                // restart the game. go back to the welcome page
                Main.threadManager.execute(new WelcomeThread());
                Main.welcomeForeground();
            }
        });
    }


}
