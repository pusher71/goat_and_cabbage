package UI;

import Model.Game;
import Model.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller extends JFrame {

    private Game _game;
    private FieldWidget _fw; //виджет поля
    private EnergyWidget _enw; //виджет энергии козы
    private StatusWidget _exw; //виджет состояния игры

    public Controller() {

        JPanel content = (JPanel)getContentPane();
        content.setLayout( new BoxLayout(content, BoxLayout.Y_AXIS) );

        createFieldWidget();

        createGame();

        content.add(Box.createRigidArea(new Dimension(0,10)));

        createStartButton();
        createEnergyWidget();
        createStatusWidget();

        content.add(Box.createRigidArea(new Dimension(0,10)));

        pack(); // подгоняем размеры окна под его содержимое
        this.setResizable(false); // в играх редко приходится изменять размер окна
        _fw.addKeyListener( new Controller.KeyController() );

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        updateStatus();
    }

    //создать игру
    private void createGame() {

        _game = new Game();
        //создать игровые объекты и соответствующие виджеты
        Game.MazeCreator creator = _game.getMazeCreator();
        _fw.add(new WallWidget(creator.addWall(1, 1)));
        _fw.add(new BoxWidget(creator.addBox(2, 1)));
        _fw.add(new GoatWidget(creator.addGoat(3, 1, 50)));
        _fw.add(new CabbageWidget(creator.addCabbage(4, 1)));
    }

    //создать виджет поля
    private void createFieldWidget() {

        _fw =  new FieldWidget();
        add(_fw);
    }

    //создать кнопку запуска
    private void createStartButton() {

        JButton btnNewGame = new JButton();
        btnNewGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnNewGame.setText("Новая игра");
        btnNewGame.setFocusable(false);
        add( btnNewGame );

        btnNewGame.addActionListener(evt -> {
            _game.start();
            updateStatus();
            btnNewGame.setEnabled(false);
        });
    }

    //создать виджет энергии козы
    private void createEnergyWidget() {

        _enw = new EnergyWidget();
        _enw.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(_enw);
    }

    //создать виджет состояния игры
    private void createStatusWidget() {

        _exw = new StatusWidget();
        _exw.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(_exw);
    }

    //обновить статус игры
    private void updateStatus() {
        _enw.setEnergy(_game.getGoatEnergy());
        _exw.setStatus(_game.getStatus());
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Controller::new);
    }

    private class KeyController implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();

            // Управление козой
            if(code == KeyEvent.VK_UP) {         // перемещаемся вверх
                _game.moveGoat(Direction.north());
            }
            else if(code == KeyEvent.VK_DOWN) {  // перемещаемся вниз
                _game.moveGoat(Direction.south());
            }
            else if(code == KeyEvent.VK_LEFT) {  // перемещаемся влево
                _game.moveGoat(Direction.west());
            }
            else if(code == KeyEvent.VK_RIGHT) { // перемещаемся вправо
                _game.moveGoat(Direction.east());
            }

            updateStatus(); // Обновляем статус
            repaint(); // Просим операционную систему перерисовать себя
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }
}
