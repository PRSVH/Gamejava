package comp1110.ass2.gui;

import javafx.scene.image.Image;

public class DieImages {
        Image pic1 = new Image("src/comp1110/ass2/gui/UI/Images/brick.png");
        Image pic2 = new Image("src/comp1110/ass2/gui/UI/Images/gold.png");
        Image pic3 = new Image("src/comp1110/ass2/gui/UI/Images/grain.png");
        Image pic4 = new Image("src/comp1110/ass2/gui/UI/Images/lumber.png");
        Image pic5 = new Image("src/comp1110/ass2/gui/UI/Images/ore.png");
        Image pic6 = new Image("src/comp1110/ass2/gui/UI/Images/wool.png");
        private int value;
        private Image dieImage = pic1;
        public void setImage(int sides)
        {
            int value = sides;
            if(value == 1)
                dieImage = pic1;
            if(value == 2)
                dieImage = pic2;
            if(value == 3)
                dieImage = pic3;
            if(value == 4)
                dieImage = pic4;
            if(value == 5)
                dieImage = pic5;
            if(value == 6)
                dieImage = pic6;
        }
        public Image getImage()
        {
            return dieImage;
        }
    }


