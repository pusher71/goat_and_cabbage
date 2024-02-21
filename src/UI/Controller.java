package UI;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

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

        content.add(javax.swing.Box.createRigidArea(new Dimension(0,10)));

        createStartButton();
        createEnergyWidget();
        createStatusWidget();

        content.add(javax.swing.Box.createRigidArea(new Dimension(0,10)));

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

        _fw.add(new WallWidget((Wall)creator.addToField(new Wall(), 1, 1)));
        _fw.add(new BoxWidget((Model.Box)creator.addToField(new Model.Box(), 2, 1)));
        _fw.add(new InterlockedBallWidget((InterlockedBall)creator.addToField(new Model.InterlockedBall(), 2, 2)));
        _fw.add(new GoatWidget((Goat)creator.addToField(new Goat(150), 3, 1)));
        _fw.add(new CabbageWidget((Cabbage)creator.addToField(new Cabbage(), 4, 1)));
        _fw.add(new BoxWidget((Model.Box)creator.addToField(new Model.Box(), 4, 2)));

        InterlockedBox ib23 = new InterlockedBox();
        ib23.setMetalProperty(new MagneticProperty(ib23, new HashMap<Direction, Pole>() {{
            put(Direction.north(), Pole.PLUS);
            put(Direction.west(), Pole.MINUS);
            put(Direction.south(), Pole.MINUS);
            put(Direction.east(), Pole.PLUS);
        }}));
        _fw.add(new InterlockedBoxWidget((Model.InterlockedBox)creator.addToField(ib23, 2, 3)));

        InterlockedBox ib34 = new InterlockedBox();
        ib34.setMetalProperty(new MagneticProperty(ib34, new HashMap<Direction, Pole>() {{
            put(Direction.north(), Pole.PLUS);
            put(Direction.west(), Pole.PLUS);
            put(Direction.south(), Pole.MINUS);
            put(Direction.east(), Pole.MINUS);
        }}));
        _fw.add(new InterlockedBoxWidget((Model.InterlockedBox)creator.addToField(ib34, 3, 4)));

        InterlockedBox ib63 = new InterlockedBox();
        ib63.setMetalProperty(new MagneticProperty(ib63, new HashMap<Direction, Pole>() {{
            put(Direction.north(), Pole.MINUS);
            put(Direction.west(), Pole.PLUS);
            put(Direction.south(), Pole.PLUS);
            put(Direction.east(), Pole.MINUS);
        }}));
        _fw.add(new InterlockedBoxWidget((Model.InterlockedBox)creator.addToField(ib63, 6, 3)));

        InterlockedBox ib74 = new InterlockedBox();
        ib74.setMetalProperty(new MagneticProperty(ib74, new HashMap<Direction, Pole>() {{
            put(Direction.north(), Pole.MINUS);
            put(Direction.west(), Pole.MINUS);
            put(Direction.south(), Pole.PLUS);
            put(Direction.east(), Pole.PLUS);
        }}));
        _fw.add(new InterlockedBoxWidget((Model.InterlockedBox)creator.addToField(ib74, 7, 4)));

        InterlockedBox ib85 = new InterlockedBox();
        ib85.setMetalProperty(new MetalProperty(ib85));
        _fw.add(new InterlockedBoxWidget((Model.InterlockedBox)creator.addToField(ib85, 8, 5)));

        InterlockedBall ib97 = new InterlockedBall();
        ib97.setMetalProperty(new MetalProperty(ib97));
        _fw.add(new InterlockedBallWidget((Model.InterlockedBall)creator.addToField(ib97, 9, 7)));
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

        boolean shiftPressed = false; //шифт зажат

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();

            // Управление козой
            if(code == KeyEvent.VK_UP) {         // перемещаемся вверх
                _game.moveGoat(Direction.north(), shiftPressed);
            }
            else if(code == KeyEvent.VK_DOWN) {  // перемещаемся вниз
                _game.moveGoat(Direction.south(), shiftPressed);
            }
            else if(code == KeyEvent.VK_LEFT) {  // перемещаемся влево
                _game.moveGoat(Direction.west(), shiftPressed);
            }
            else if(code == KeyEvent.VK_RIGHT) { // перемещаемся вправо
                _game.moveGoat(Direction.east(), shiftPressed);
            }
            else if (code == KeyEvent.VK_SHIFT) { //включить шифт
                shiftPressed = true;
            }

            updateStatus(); // Обновляем статус
            repaint(); // Просим операционную систему перерисовать себя
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_SHIFT) { //отключить шифт
                shiftPressed = false;
            }
        }
    }
}
