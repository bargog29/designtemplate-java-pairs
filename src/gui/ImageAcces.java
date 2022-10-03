
package gui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class ImageAcces
{

    private static Image[][] images;
    private static int numOfImages;

    static
    {
        File[] resourceFiles = new File("src/res").listFiles();
        numOfImages = resourceFiles.length / 4;
        images = new Image[numOfImages][];
        for (int i = 0; i < images.length; i++)
        {
            images[i] = new Image[4];
            for (int j = 0; j < 4; j++)
            {
                File file = resourceFiles[i * 4 + j];
                try
                {
                    images[i][j] = ImageIO.read(file).getScaledInstance(MainPanel.IMAGE_SIZE, MainPanel.IMAGE_SIZE, Image.SCALE_SMOOTH);;
                }
                catch (IOException ex)
                {
                    Logger.getLogger(ImageAcces.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static Image[] getImages(int index)
    {
        return images[index];
    }

    public static int getNumOfImages()
    {
        return numOfImages;
    }

    private ImageAcces()
    {
    }
}
