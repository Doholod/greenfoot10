import greenfoot.*;
import java.lang.Math;;

public class MenuWorld extends World
{
    // Статическое поле, чтобы игра хранилась в памяти постоянно
    private static ForestHorrorWorld gameWorld;
    private static final int SCREEN_H = 700;
    private static final int SCREEN_W = 1400;
    private static final int COOLDOWN_MAX = 60;
    // Переменные для хранения настроек меню (например, громкость звука или сложность)
    private int soundVolume = 80; 

    // Элементы интерфейса
    private String[] menuItems = {"ПРОДОЛЖИТЬ ИГРУ", "ГРОМКОСТЬ: ", "ВЫХОД В ОС"};
    private int selectedIndex = 0; // Какая кнопка выбрана стрелочками
    
    private int glitchTimer = 0; // Эффект мерцания для хоррор-атмосферы
    private int cooldown_current = 0;

    public MenuWorld()
    {    
        super(1400, 700, 1); 
        renderMenu();
    }

private void handleInput()
    {
        if (cooldown_current<=0){
            // Навигация стрелочками (вверх/вниз)
            if (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w")) {
                selectedIndex = (selectedIndex - 1 + menuItems.length) % menuItems.length;
                cooldown_current = COOLDOWN_MAX;
                renderMenu();
            }
            if (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s")) {
                selectedIndex = (selectedIndex + 1) % menuItems.length;
                cooldown_current = COOLDOWN_MAX;
                renderMenu();
            }
        }

        // Изменение параметров внутри пунктов меню (влево/вправо)
        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) {
            modifySetting(-1);
        }
        if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) {
            modifySetting(1);
        }

        // Активация выбранного пункта (Enter или Пробел)
        if (Greenfoot.isKeyDown("enter") || Greenfoot.isKeyDown("space")) {
            executeAction();
        }
    }

    /**
     * Изменение настроек звука и графики
     */
    private void modifySetting(int dir)
    {
        if (selectedIndex == 1) { // Громкость
            soundVolume = Math.max(0, Math.min(100, soundVolume + (dir * 1)));
        }
        renderMenu();
    }

    /**
     * Действие при клике на пункт меню
     */
    private void executeAction()
    {
        if (selectedIndex == 0) { // Запуск / Продолжение игры
            if (gameWorld == null) {
                gameWorld = new ForestHorrorWorld(this, SCREEN_W, SCREEN_H);
            }
            Greenfoot.setWorld(gameWorld);
        }
        else if (selectedIndex == 3) { // Выход из игры
            Greenfoot.stop();
        }
    }

    private void renderMenu()
    {
        GreenfootImage bg = getBackground();
        
        // 1. Мрачный фон с эффектом шума (глубокий темно-серый/черный)
        bg.setColor(new Color(15, 15, 20));
        bg.fill();
        
        // Рисуем немного случайных «хоррор» полос на заднем фоне
        if (glitchTimer % 20 == 0) {
            bg.setColor(new Color(255, 0, 0, 15)); // Едва заметные красные полосы
            bg.fillRect(0, Greenfoot.getRandomNumber(700), 1400, Greenfoot.getRandomNumber(5));
        }

        // 2. Отрисовка ЗАГОЛОВКА игры
        Font titleFont = new Font("Courier New", true, false, 64);
        bg.setFont(titleFont);
        
        // Хоррор-эффект: двоящийся красный силуэт под заголовком (смещение из-за помех)
        int glitchOffset = (glitchTimer % 15 == 0) ? Greenfoot.getRandomNumber(4) - 2 : 0;
        bg.setColor(new Color(150, 0, 0, 200));
        bg.drawString("FOREST HORROR", 470 + glitchOffset, 182);
        
        // Основной белый текст заголовка
        bg.setColor(new Color(220, 220, 220));
        bg.drawString("FOREST HORROR", 468, 180);

        // Маленький подзаголовок
        bg.setFont(new Font("Courier New", false, true, 18));
        bg.setColor(new Color(100, 100, 110));
        bg.drawString("Атмосферный 3D кошмар", 545, 215);

        // 3. Отрисовка ПУНКТОВ МЕНЮ
        Font itemFont = new Font("Dialog", true, false, 26);
        bg.setFont(itemFont);

        int startY = 340; // Начальная высота для кнопок
        int spacing = 65; // Расстояние между кнопками

        for (int i = 0; i < menuItems.length; i++) {
            int currentY = startY + (i * spacing);
            String text = menuItems[i];

            // Модифицируем текст для вывода текущих настроек
            if (i == 1) text += soundVolume + "%";

            if (i == selectedIndex) {
                // Стиль для ВЫБРАННОГО пункта меню
                // Рисуем рамку вокруг активного пункта
                bg.setColor(new Color(80, 0, 0, 100));
                bg.fillRect(450, currentY - 32, 500, 45);
                bg.setColor(new Color(180, 0, 0));
                bg.fillRect(450, currentY - 32, 500, 45);
                
                // Подсвеченный текст с эффектом "кровавого" наведения
                bg.setColor(new Color(255, 60, 60));
                bg.drawString("> " + text + " <", 480, currentY);
            } else {
                // Стиль для обычных (неактивных) пунктов
                bg.setColor(new Color(140, 140, 150));
                bg.drawString("  " + text, 480, currentY);
            }
        }

        // 4. Панель подсказок внизу экрана
        bg.setFont(new Font("Courier New", false, false, 16));
        bg.setColor(new Color(70, 70, 80));
        bg.drawString("Управление: [W/S] или [Стрелочки] - выбор | [A/D] - изменение настроек | [ENTER] - подтверждение", 220, 640);
        bg.drawString("Во время игры нажмите [ESC], чтобы вернуться сюда", 445, 665);
    }

    public void act()
    {
        cooldown_current--;
        handleInput();
        // Каждые несколько кадров обновляем меню ради эффекта мерцания текста
        glitchTimer++;
        if (glitchTimer % 4 == 0) {
            renderMenu();
        }
    }
    
    // Геттеры, чтобы мир игры мог считывать настройки из меню в процессе геймплея
    public int getSoundVolume() { return soundVolume; }
}